package clinic.service;

import clinic.model.Patient;
import clinic.model.ReferralRequest;

public interface BookingService {
    boolean reserveSlot(Patient patient, ReferralRequest request);
}
