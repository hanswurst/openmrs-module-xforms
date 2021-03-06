/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.xforms.serialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.reporting.AbstractReportObject;


/**
 * NOT SURE THIS WILL EVEN WORK
 * Daniel;
 * We should switch to the new reporting module as most of the reporting compatibility stuff
 * are {@link Deprecated}
 * 
 * @author Samuel Mbugua
 *
 */
public class DefaultSavedSearchSerializer {

    private Log log = LogFactory.getLog(this.getClass());

    public DefaultSavedSearchSerializer() {

    }

    public void serialize(OutputStream os, Object data) {
        try {
        	DataOutputStream dos = new DataOutputStream(os);
        	
        	if(data == null){
        		dos.writeInt(0);
        		return;
        	}
        	
        	List<AbstractReportObject> reportObjects = (List<AbstractReportObject>) data;
        	

            dos.writeInt(reportObjects.size());
            for (AbstractReportObject reportObject : reportObjects)
                serialize(reportObject, dos);

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    protected void serialize(AbstractReportObject reportObject, OutputStream os) {
        try {
        	DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(reportObject.getReportObjectId());
            dos.writeUTF(reportObject.getName());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
