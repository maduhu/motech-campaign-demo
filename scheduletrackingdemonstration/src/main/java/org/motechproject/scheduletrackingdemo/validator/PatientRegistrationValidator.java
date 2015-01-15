//package org.motechproject.scheduletrackingdemo.validator;
//
//import java.util.List;
//
//import org.motechproject.scheduletrackingdemo.domain.PatientRegistration;
//import org.motechproject.scheduletrackingdemo.openmrs.OpenMrsClient;
//import org.motechproject.mobileforms.api.domain.FormError;
//import org.motechproject.mrs.model.MRSPatient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PatientRegistrationValidator extends AbstractMobileValidator<PatientRegistration> {
//
//	private OpenMrsClient openmrsClient;
//
//	@Autowired
//	public PatientRegistrationValidator(OpenMrsClient openmrsClient) {
//		this.openmrsClient = openmrsClient;
//	}
//
//	@Override
//	public List<FormError> validate(PatientRegistration formBean) {
//		List<FormError> errors = super.validate(formBean);
//		validatePhoneNumberFormat(formBean.getPhoneNumber(), errors);
//		validateUniqueMotechId(formBean.getMotechId(), errors);
//
//		return errors;
//	}
//
//	protected void validateUniqueMotechId(String motechId, List<FormError> errors) {
//		MRSPatient existingPatient = openmrsClient.getPatientByMotechId(motechId);
//		if (existingPatient != null) {
//			errors.add(new FormError("motechId", "Already a patient with this MoTeCH Id"));
//		}
//	}
//}
