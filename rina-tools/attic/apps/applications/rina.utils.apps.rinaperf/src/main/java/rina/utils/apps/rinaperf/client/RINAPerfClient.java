package rina.utils.apps.rinaperf.client;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import rina.utils.LogHelper;
import rina.cdap.api.CDAPSessionManager;
import rina.cdap.api.message.CDAPMessage;
import rina.cdap.api.message.CDAPMessage.Opcode;
import rina.cdap.api.message.ObjectValue;
import rina.cdap.impl.CDAPSessionManagerImpl;
import rina.cdap.impl.googleprotobuf.GoogleProtocolBufWireMessageProviderFactory;
import rina.utils.apps.rinaperf.TestInformation;
import rina.utils.apps.rinaperf.protobuf.EchoTestMessageEncoder;
import rina.utils.apps.rinaperf.utils.ApplicationRegistrationListener;
import rina.utils.apps.rinaperf.utils.FlowAllocationListener;
import rina.utils.apps.rinaperf.utils.FlowDeallocationListener;
import rina.utils.apps.rinaperf.utils.IPCEventConsumer;
import eu.irati.librina.AllocateFlowRequestResultEvent;
import eu.irati.librina.ApplicationProcessNamingInformation;
import eu.irati.librina.ApplicationRegistrationInformation;
import eu.irati.librina.ApplicationRegistrationType;
import eu.irati.librina.Flow;
import eu.irati.librina.FlowDeallocatedEvent;
import eu.irati.librina.FlowSpecification;
import eu.irati.librina.RegisterApplicationResponseEvent;
import eu.irati.librina.UnregisterApplicationResponseEvent;
import eu.irati.librina.rina;

/**
 * Implements the behavior of a RINABand Client
 * @author eduardgrasa
 *
 */
public class RINAPerfClient implements  ApplicationRegistrationListener, 
FlowAllocationListener, FlowDeallocationListener {
	
	public static final int MAX_SDU_SIZE = 10000;
	public static final String TEST_OBJECT_CLASS = "RINAPerf test";
	public static final String TEST_OBJECT_NAME = "/rina/utils/apps/rinaperf/test";
	
	private TestInformation testInformation = null;
	
	private static final Log log = LogFactory.getLog(RINAPerfClient.class);
	
	/**
	 * The APNamingInfo associated to the control AE of the Echo application
	 */
	private ApplicationProcessNamingInformation rinaperfApNamingInfo = null;
	/**
	 * The client AP Naming Information
	 */
	private ApplicationProcessNamingInformation clientApNamingInfo = null;
	
	/**
	 * The flow to the Echo server AE
	 */
	private Flow flow = null;
	
	/**
	 * The thread pool
	 */
	private ExecutorService executorService = null;
	
	private IPCEventConsumer ipcEventConsumer = null;
	private long handle = -1;
	private CDAPSessionManager cdapSessionManager = null;
	private int maxAllowableGapInSDUs = -1;
	
	private Timer timer = null;
	
	private boolean stop = false;
	private Object stopLock = new Object();
	
	public RINAPerfClient(int sduSize, 
			ApplicationProcessNamingInformation rinaperfApNamingInfo, 
			ApplicationProcessNamingInformation clientApNamingInfo, 
			int time, int rate, int gap){
		try {
			rina.initialize(LogHelper.getLibrinaLogLevel(), 
					LogHelper.getLibrinaLogFile());
		} catch(Exception ex){
			log.error("Problems initializing librina, exiting: "+ex.getMessage());
			System.exit(-1);
		}
		
		testInformation = new TestInformation();
		
		if (sduSize > MAX_SDU_SIZE) {
			sduSize = MAX_SDU_SIZE;
		}
		testInformation.setSduSize(sduSize);
		
		this.rinaperfApNamingInfo = rinaperfApNamingInfo;
		this.clientApNamingInfo = clientApNamingInfo;
		this.maxAllowableGapInSDUs = gap;
		
		testInformation.setTime(time);
		testInformation.setRate(rate);
		
		cdapSessionManager = new CDAPSessionManagerImpl(
				new GoogleProtocolBufWireMessageProviderFactory());
		
		timer = new Timer();
		
		ipcEventConsumer = new IPCEventConsumer();
		ipcEventConsumer.addApplicationRegistrationListener(this, clientApNamingInfo);
		executorService = Executors.newCachedThreadPool();
		executorService.execute(ipcEventConsumer);
	}
	
	public boolean isStop(){
		boolean result = false;
		synchronized(stopLock) {
			result = stop;
		}
		return result;
	}
	
	public void stop(){
		synchronized(stopLock) {
			stop = true;
		}
	}
	
	public void execute(){
		//0 Register client application (otherwise we cannot use the shim DIF)
		try{
			ApplicationRegistrationInformation appRegInfo = 
					new ApplicationRegistrationInformation(
							ApplicationRegistrationType.APPLICATION_REGISTRATION_ANY_DIF);
			appRegInfo.setAppName(clientApNamingInfo);
			rina.getIpcManager().requestApplicationRegistration(appRegInfo);
			log.info("Requested registration of AE: "+clientApNamingInfo.toString());
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void cancelTest(int status) {
		System.exit(status);
	}
	
	public synchronized void dispatchApplicationRegistrationResponseEvent(
			RegisterApplicationResponseEvent event) {
		if (event.getResult() == 0) {
			try {
				rina.getIpcManager().commitPendingRegistration(
						event.getSequenceNumber(), event.getDIFName());
				log.info("Succesfully registered AE " + event.getApplicationName().toString() 
						+ " to DIF" + event.getDIFName().getProcessName());
				
				//1 Allocate a flow to the Echo Server AE
				FlowSpecification qosSpec = new FlowSpecification();
				qosSpec.setMaxAllowableGap(maxAllowableGapInSDUs);
				handle = rina.getIpcManager().requestFlowAllocation(
						this.clientApNamingInfo, this.rinaperfApNamingInfo, qosSpec);
				ipcEventConsumer.addFlowAllocationListener(this, handle);
			} catch (Exception ex){
				log.error(ex.getMessage());
				System.exit(-1);
			}	
		} else {
			try{
				log.error("Problems registering AE "+ event.getApplicationName().toString() 
						+ ". Error code: " + event.getResult());
				rina.getIpcManager().withdrawPendingRegistration(event.getSequenceNumber());
			}catch(Exception ex) {
				log.error(ex.getMessage());
			}
			System.exit(event.getResult());
		}
	}
	
	public synchronized void dispatchApplicationUnregistrationResponseEvent(
			UnregisterApplicationResponseEvent event) {
		boolean success = false;
		
		if (event.getResult() == 0){
			success = true;
		}
		
		try {
			rina.getIpcManager().appUnregistrationResult(event.getSequenceNumber(), success);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
	
	@Override
	public synchronized void dispatchAllocateFlowRequestResultEvent(
			AllocateFlowRequestResultEvent event) {
		ipcEventConsumer.removeFlowAllocationListener(event.getSequenceNumber());

		if (event.getSequenceNumber() == handle){
			if (event.getPortId() > 0){
				flow = commitPendingFlow(event);
				negotiateTestWithServer(flow);
				int sentSDUs = sendSDUs(flow);
				waitForStatsAndTerminate(flow, sentSDUs);
				System.exit(0);
			} else {
				withdrawPendingFlow(event);
				System.exit(-1);
			}
		}
	}
	
	private Flow commitPendingFlow(AllocateFlowRequestResultEvent event) {
		Flow allocatedFlow = null;
		
		try{
			allocatedFlow = rina.getIpcManager().commitPendingFlow(event.getSequenceNumber(), 
					event.getPortId(), event.getDifName());
		}catch(Exception ex){
			log.error(ex.getMessage());
			System.exit(-1);
		}
		
		ipcEventConsumer.addFlowDeallocationListener(this, event.getPortId());
		
		return allocatedFlow;
	}
	
	private void withdrawPendingFlow(AllocateFlowRequestResultEvent event) {
		log.error("Problems allocating flow to control AE: " + rinaperfApNamingInfo.toString());
		
		try{
			rina.getIpcManager().withdrawPendingFlow(event.getSequenceNumber());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
	
	private void negotiateTestWithServer(Flow flow) {
		byte[] buffer = null;
		int bytesRead = 0;
		try{
			ObjectValue objectValue = new ObjectValue();
			objectValue.setByteval(EchoTestMessageEncoder.encode(testInformation));
			CDAPMessage cdapMessage = CDAPMessage.getStartObjectRequestMessage(
					null, null, TEST_OBJECT_CLASS, objectValue, 0, TEST_OBJECT_NAME, 0);
			cdapMessage.setInvokeID(1);
			buffer = cdapSessionManager.encodeCDAPMessage(cdapMessage);
			flow.writeSDU(buffer, buffer.length);
			log.info("Requested RINAperf server to start a test with the following parameters: \n" 
					+ testInformation.toString());
			
			CancelTestTimerTask timerTask = new CancelTestTimerTask(this);
			timer.schedule(timerTask, 5000);
			
			buffer = new byte[MAX_SDU_SIZE];
			bytesRead = flow.readSDU(buffer, buffer.length);
			timerTask.cancel();
			byte[] sdu = new byte[bytesRead];
			for(int i=0; i<sdu.length; i++) {
				sdu[i] = buffer[i];
			}
			cdapMessage = cdapSessionManager.decodeCDAPMessage(sdu);
			if (cdapMessage.getOpCode() != Opcode.M_START_R) {
				throw new Exception("Received wrong CDAP message code: "+cdapMessage.getOpCode());
			} else if (cdapMessage.getResult() != 0) {
				throw new Exception("Echo server rejected the test");
			}
			
			log.info("RINAperf server accepted the test, starting...");
		} catch(Exception ex) {
			log.error("Error initiating test: "+ex.getMessage() 
					+ ". Deallocating flow and terminating");
			try {
				rina.getIpcManager().requestFlowDeallocation(flow.getPortId());
			} catch(Exception e) {
				log.error("Problems requesting flow deallocation " +e.getMessage());
				System.exit(-1);
			}
			
			System.exit(-1);
		}
	}
	
	private int sendSDUs(Flow flow) {
		StopTestTimerTask stopTask = new StopTestTimerTask(this);
		timer.schedule(stopTask, testInformation.getTime()*1000);
		
		//Calculate rate
		/* B/ms = packet size in bytes / time unit in millis
           send it every x ms

           Kbps --> KB/s = /8 
           LB/s --> B/s = *1024
           B/s --> B/ms = /1000

           time to send packet at max rate = packet size / B/ms

           Get current time, check if we are allowed to send it
           We can send after startTime + numOfPacketsSend*timeToSend
		 */
		double byteMillisRate = testInformation.getRate() * 1024 / 8000;
        double timeToSend = testInformation.getSduSize() / byteMillisRate;
        long firstSDUSentTime = 0;

		//3 Send SDUs to server
		byte[] buffer = new byte[testInformation.getSduSize()];
		int sentSDUs = 0;
		firstSDUSentTime = Calendar.getInstance().getTimeInMillis();
		while(!isStop()) {
			try{
				flow.writeSDU(buffer, buffer.length);
				sentSDUs ++;
				while (Calendar.getInstance().getTimeInMillis() < 
						firstSDUSentTime + sentSDUs * timeToSend) {
                 }
			}catch(Exception ex){
				log.error("Error writing SDU to port-id "+flow.getPortId());
				break;
			}
		}
		
		log.info("Test completed, sent " + sentSDUs + " SDUs in "+ 
				testInformation.getTime()+" seconds");
		log.info("Waiting for stats from the server");
		
		return sentSDUs;
	}
	
	private void waitForStatsAndTerminate(Flow flow, int sentSDUs) {
		CancelTestTimerTask timerTask = new CancelTestTimerTask(this);
		timer.schedule(timerTask, 5000);

		try{
			byte[] buffer = new byte[MAX_SDU_SIZE];
			int bytesRead = flow.readSDU(buffer, buffer.length);
			timerTask.cancel();
			byte[] sdu = new byte[bytesRead];
			for(int i=0; i<sdu.length; i++) {
				sdu[i] = buffer[i];
			}

			CDAPMessage cdapMessage = cdapSessionManager.decodeCDAPMessage(sdu);
			if (cdapMessage.getObjValue() != null && 
					cdapMessage.getObjValue().getByteval() != null) {
				TestInformation testInfo = EchoTestMessageEncoder.decode(cdapMessage.getObjValue().getByteval());
				if (testInfo != null) {
					int sdusReceived = testInfo.getSdusSent();
					log.info("Server received "+sdusReceived + " SDUs of "+ 
							testInformation.getSduSize() + " bytes in "+testInformation.getTime()+" seconds");
					double goodput = (double) sdusReceived*testInformation.getSduSize()/testInformation.getTime();
					double goodputInMbps = goodput*8/(1024*1024);
					log.info("RINAperf goodput: "+goodput+" bytes per second ( " + 
							goodputInMbps + " Mbps)");
				} else {
					log.error("Problems decoding TestInformation object");
				}
			} else {
				log.error("Received bogus CDAP message from server");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		
		if (flow.isAllocated()) {
			try{
				rina.getIpcManager().requestFlowDeallocation(flow.getPortId());
			} catch (Exception ex) {
				log.error("Problems deallocating flow: "+ex.getMessage());
			}
		}
	}

	@Override
	public void dispatchFlowDeallocatedEvent(FlowDeallocatedEvent event) {
	}
}
