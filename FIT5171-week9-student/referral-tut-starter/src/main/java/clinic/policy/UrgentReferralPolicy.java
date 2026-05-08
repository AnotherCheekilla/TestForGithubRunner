package clinic.policy;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;

public class UrgentReferralPolicy extends EligibilityPolicy {
    private static final int MIN_URGENT_NOTE_LENGTH = 20;

    @Override
    public EligibilityDecision check(Patient patient, ReferralRequest request) {
        EligibilityDecision parentDecision = super.check(patient, request);
        if (parentDecision == EligibilityDecision.REJECTED) {
            return EligibilityDecision.REJECTED;
        }
        if (request.isUrgent() && request.getClinicalNotes().trim().length() < MIN_URGENT_NOTE_LENGTH) {
            return EligibilityDecision.REJECTED;
        }
        return EligibilityDecision.APPROVED;
    }
}
