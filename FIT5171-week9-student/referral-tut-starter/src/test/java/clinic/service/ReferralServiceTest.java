package clinic.service;

import clinic.model.EligibilityDecision;
import clinic.model.Patient;
import clinic.model.ReferralRequest;
import clinic.model.ReferralResult;
import clinic.policy.EligibilityPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class ReferralServiceTest {
    private EligibilityPolicy eligibilityPolicy;
    private SpecialistDirectory specialistDirectory;
    private BookingService bookingService;
    private NotificationService notificationService;
    private ReferralService referralService;

    private Patient patient;
    private ReferralRequest request;

    @BeforeEach
    public void setUp() {
        eligibilityPolicy = mock(EligibilityPolicy.class);
        specialistDirectory = mock(SpecialistDirectory.class);
        bookingService = mock(BookingService.class);
        notificationService = mock(NotificationService.class);
        referralService = new ReferralService(
                eligibilityPolicy,
                specialistDirectory,
                bookingService,
                notificationService);

        patient = new Patient(1001L, "Asha Nguyen", true);
        request = new ReferralRequest("cardiology", "Chest pain with abnormal ECG result", false);
    }

    @Test
    public void nullPatientIsRejectedBeforeCollaboratorsRun() {
        assertThrows(IllegalArgumentException.class,
                () -> referralService.createReferral(null, request));

        verifyNoInteractions(eligibilityPolicy, specialistDirectory, bookingService, notificationService);
    }

    @Test
    public void approvedReferralReservesSlotAndSendsSuccess() {
        when(eligibilityPolicy.check(patient, request)).thenReturn(EligibilityDecision.APPROVED);
        when(specialistDirectory.hasSpecialistFor("cardiology")).thenReturn(true);
        when(bookingService.reserveSlot(patient, request)).thenReturn(true);

        ReferralResult result = referralService.createReferral(patient, request);

        assertEquals(ReferralResult.Status.APPROVED, result.getStatus());
        verify(bookingService).reserveSlot(patient, request);
        verify(notificationService).sendSuccess(patient, request);
        verify(notificationService, never()).sendFailure(patient, request);
    }

    @Test
    public void rejectedReferralDoesNotReserveSlotAndSendsFailure() {
        when(eligibilityPolicy.check(patient, request)).thenReturn(EligibilityDecision.REJECTED);

        ReferralResult result = referralService.createReferral(patient, request);

        assertEquals(ReferralResult.Status.REJECTED, result.getStatus());
        verifyNoInteractions(specialistDirectory, bookingService);
        verify(notificationService).sendFailure(patient, request);
        verify(notificationService, never()).sendSuccess(patient, request);
    }

    @Test
    public void approvedReferralWithNoSlotSendsWaitlist() {
        when(eligibilityPolicy.check(patient, request)).thenReturn(EligibilityDecision.APPROVED);
        when(specialistDirectory.hasSpecialistFor("cardiology")).thenReturn(true);
        when(bookingService.reserveSlot(patient, request)).thenReturn(false);

        ReferralResult result = referralService.createReferral(patient, request);

        assertEquals(ReferralResult.Status.WAITLISTED, result.getStatus());
        verify(bookingService).reserveSlot(patient, request);
        verify(notificationService).sendWaitlist(patient, request);
        verify(notificationService, never()).sendSuccess(patient, request);
    }

    @Test
    public void approvedReferralWithNoSpecialistSendsWaitlistAndSkipsBooking() {
        when(eligibilityPolicy.check(patient, request)).thenReturn(EligibilityDecision.APPROVED);
        when(specialistDirectory.hasSpecialistFor("cardiology")).thenReturn(false);

        ReferralResult result = referralService.createReferral(patient, request);

        assertEquals(ReferralResult.Status.WAITLISTED, result.getStatus());
        verify(specialistDirectory).hasSpecialistFor("cardiology");
        verify(bookingService, never()).reserveSlot(patient, request);
        verify(notificationService).sendWaitlist(patient, request);
        verify(notificationService, never()).sendSuccess(patient, request);
    }
}
