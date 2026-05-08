package clinic.model;

public class ReferralResult {
    public enum Status {
        APPROVED,
        REJECTED,
        WAITLISTED
    }

    private final Status status;
    private final String message;

    private ReferralResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ReferralResult approved(String message) {
        return new ReferralResult(Status.APPROVED, message);
    }

    public static ReferralResult rejected(String message) {
        return new ReferralResult(Status.REJECTED, message);
    }

    public static ReferralResult waitlisted(String message) {
        return new ReferralResult(Status.WAITLISTED, message);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
