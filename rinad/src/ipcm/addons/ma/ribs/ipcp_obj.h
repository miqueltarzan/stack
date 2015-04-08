/**
* @file ribdv1.h
* @author Bernat Gaston <bernat (dot) gaston (at) i2cat (dot) net>
*
* @brief Management Agent RIB daemon v1
*/


#ifndef __RINAD_IPCP_OBJ_H__
#define __RINAD_IPCP_OBJ_H__

#include <librina/rib_v2.h>
#include <librina/patterns.h>
#include <librina/common.h>

namespace rinad{
namespace mad{
namespace rib_v1{


//fwd decl
class IPCPObj;


//Struct containing the message
//It is a bit redundant, but hold GPB in the encoder only
typedef struct ipcp_msg{
  int32_t process_id;
  std::string name;
  //TODO: add missing info
}ipcp_msg_t;

/**
* Encoder
*/
class IPCPEncoder : public rina::rib::Encoder<ipcp_msg_t>{
  virtual void encode(const ipcp_msg_t &obj,
                              rina::cdap_rib::SerializedObject& serobj) const;
  virtual void decode(const rina::cdap_rib::SerializedObject &serobj,
                                            ipcp_msg_t& des_obj) const;

  virtual std::string get_type() const{ return "ipcp"; };
};




/**
* IPCP object
*/
class IPCPObj : public rina::rib::RIBObject<ipcp_msg_t>{

public:
  IPCPObj(std::string name, long instance, int ipcp_id);
  virtual ~IPCPObj(){};

  //We only support deletion
  virtual bool deleteObject(const void* value);

  //Name of the class
  const static std::string class_name;

  //Process ID
  int processID_;

private:
  IPCPEncoder encoder;
};


}; //namespace rib_v1
}; //namespace mad
}; //namespace rinad


#endif  /* __RINAD_IPCP_OBJ_H__ */
