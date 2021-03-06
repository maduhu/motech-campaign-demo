package org.motechproject.scheduletrackingdemo.dao;

import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;
import org.motechproject.scheduletrackingdemo.model.Patient;

/**
 * The DAO for the Patient entity. Implementation generated by MDS.
 */
public interface PatientDataService extends MotechDataService<Patient> {

    @Lookup
    Patient findByExternalId(@LookupField(name = "externalId") String externalId);
}
