package clinic.service;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;
import clinic.model.ReferralResult;
import clinic.policy.EligibilityPolicy;

public class ReferralService {
    private final EligibilityPolicy eligibilityPolicy;
    private final SpecialistDirectory specialistDirectory;
    private final BookingService bookingService;
    private final NotificationService notificationService;

    public ReferralService(EligibilityPolicy eligibilityPolicy,
                           SpecialistDirectory specialistDirectory,
                           BookingService bookingService,
                           NotificationService notificationService) {
        this.eligibilityPolicy = eligibilityPolicy;
        this.specialistDirectory = specialistDirectory;
        this.bookingService = bookingService;
        this.notificationService = notificationService;
    }

    public ReferralResult createReferral(Patient patient, ReferralRequest request) {
        // TODO: Implement the Week 9 OO testing exercise.
        // Let the failing tests drive the expected behaviour:
        // 1. validate input at the class boundary;
        // 2. ask EligibilityPolicy for the decision;
        // 3. rejected decisions: send failure notification and skip booking;
        // 4. approved decisions: check whether a specialist exists for the requested specialty;
        // 5. if no specialist available: send waitlist notification (skip booking), return WAITLISTED;
        // 6. if specialist available: ask BookingService to reserve a slot;
        //    - reserved slot -> send success, return APPROVED;
        //    - no slot       -> send waitlist, return WAITLISTED.

        

        throw new UnsupportedOperationException("TODO: implement createReferral");
    }
}
