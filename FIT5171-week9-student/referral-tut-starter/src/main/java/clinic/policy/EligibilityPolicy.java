package clinic.policy;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;

public class EligibilityPolicy {
    public EligibilityDecision check(Patient patient, ReferralRequest request) {
        if (patient == null || request == null) {
            return EligibilityDecision.REJECTED;
        }
        if (!patient.hasMedicareNumber()) {
            return EligibilityDecision.REJECTED;
        }
        if (isBlank(request.getSpecialty()) || isBlank(request.getClinicalNotes())) {
            return EligibilityDecision.REJECTED;
        }
        return EligibilityDecision.APPROVED;
    }

    protected boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
