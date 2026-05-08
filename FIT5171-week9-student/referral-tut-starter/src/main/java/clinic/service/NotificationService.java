package clinic.service;

import clinic.model.Patient;
import clinic.model.ReferralRequest;

public interface NotificationService {
    void sendSuccess(Patient patient, ReferralRequest request);

    void sendFailure(Patient patient, ReferralRequest request);

    void sendWaitlist(Patient patient, ReferralRequest request);
}
