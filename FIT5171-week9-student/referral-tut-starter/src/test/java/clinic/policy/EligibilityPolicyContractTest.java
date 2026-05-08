package clinic.policy;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EligibilityPolicyContractTest {
    @Test
    public void basePolicySatisfiesParentContract() {
        assertParentContract(new EligibilityPolicy());
    }

    @Test
    public void urgentPolicySatisfiesParentContract() {
        assertParentContract(new UrgentReferralPolicy());
    }

    private void assertParentContract(EligibilityPolicy policy) {
        Patient validPatient = new Patient(1001L, "Asha Nguyen", true);
        ReferralRequest validRequest = new ReferralRequest(
                "cardiology",
                "Chest pain with abnormal ECG result",
                false);

        assertNotNull(policy.check(validPatient, validRequest),
                "Policies must never return null");

        ReferralRequest missingSpecialty = new ReferralRequest(
                "",
                "Clinical notes are present",
                false);
        assertEquals(EligibilityDecision.REJECTED,
                policy.check(validPatient, missingSpecialty),
                "Missing mandatory request data must be rejected");

        Patient patientBeforeCheck = new Patient(1002L, "Mei Chen", true);
        policy.check(patientBeforeCheck, validRequest);
        assertEquals(1002L, patientBeforeCheck.getId());
        assertEquals("Mei Chen", patientBeforeCheck.getName());
        assertEquals(true, patientBeforeCheck.hasMedicareNumber(),
                "Policies must not mutate patient records");
    }
}
