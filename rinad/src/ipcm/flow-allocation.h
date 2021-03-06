/*
 * IPC Manager - Flow allocation/deallocation
 *
 *    Vincenzo Maffione     <v.maffione@nextworks.it>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

#ifndef __IPCM_FLOW_ALLOCATION_H__
#define __IPCM_FLOW_ALLOCATION_H__

#include <librina/common.h>
#include "event-loop.h"


namespace rinad {

void flow_allocation_requested_event_handler(rina::IPCEvent *event,
					     EventLoopData *opaque);
void allocate_flow_request_result_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void allocate_flow_response_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void flow_deallocation_requested_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void deallocate_flow_response_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void flow_deallocated_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void ipcm_deallocate_flow_response_event_handler(rina::IPCEvent *event,
					EventLoopData *opaque);
void ipcm_allocate_flow_request_result_handler(rina::IPCEvent *event,
					EventLoopData *opaque);

}
#endif  /* __IPCM_FLOW_ALLOCATION_H__ */
