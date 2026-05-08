package clinic.policy;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrgentReferralPolicyTest {
    private final Patient patient = new Patient(1001L, "Asha Nguyen", true);
    private final UrgentReferralPolicy policy = new UrgentReferralPolicy();

    @Test
    public void urgentReferralWithThinNotesIsRejected() {
        ReferralRequest request = new ReferralRequest("cardiology", "urgent", true);

        EligibilityDecision decision = policy.check(patient, request);

        assertEquals(EligibilityDecision.REJECTED, decision);
    }

    @Test
    public void urgentReferralWithDetailedNotesIsApproved() {
        ReferralRequest request = new ReferralRequest(
                "cardiology",
                "Severe chest pain with abnormal ECG result",
                true);

        EligibilityDecision decision = policy.check(patient, request);

        assertEquals(EligibilityDecision.APPROVED, decision);
    }

    @Test
    public void nonUrgentReferralUsesParentEligibilityRule() {
        ReferralRequest request = new ReferralRequest("dermatology", "Routine skin review", false);

        EligibilityDecision decision = policy.check(patient, request);

        assertEquals(EligibilityDecision.APPROVED, decision);
    }
}
