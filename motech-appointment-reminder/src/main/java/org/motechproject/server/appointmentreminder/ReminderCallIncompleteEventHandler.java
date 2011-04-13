/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2011 Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, nor its respective contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA AND ITS CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION USA OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 */
package org.motechproject.server.appointmentreminder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.motechproject.context.Context;
import org.motechproject.metrics.MetricsAgent;
import org.motechproject.model.MotechEvent;
import org.motechproject.server.appointmentreminder.service.AppointmentReminderService;
import org.motechproject.server.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles Remind Appointment Events
 * 
 * @author Igor
 * 
 */
public class ReminderCallIncompleteEventHandler implements EventListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    public final static String HANDLER_ID = "CallReminderIncompleted";

    @Autowired
    AppointmentReminderService appointmentReminderService;

    private MetricsAgent metricsAgent = Context.getInstance().getMetricsAgent();

	@Override
	public void handle(MotechEvent event) {

        String appointmentId = EventKeys.getAppointmentId(event);
        if (appointmentId == null) {
            log.error("Can not handle the Call Incomplete Event: " + event +
                     ". The event is invalid - missing the " + EventKeys.APPOINTMENT_ID_KEY + " parameter");
            return;
        }

        Date callDate = EventKeys.getCallDate(event);
        if (callDate == null) {
            log.error("Can not handle the Call Incomplete Event: " + event +
                     ". The event is invalid - missing the " + EventKeys.CALL_DATE_KEY + " parameter");
            return;
        }
        appointmentReminderService.reminderCallIncompleted(appointmentId, callDate);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("appointmentId", appointmentId);
        metricsAgent.logEvent("motech.appointment-reminder.call.incomplete", parameters);
    }

	@Override
	public String getIdentifier() {
		return HANDLER_ID;
	}

}