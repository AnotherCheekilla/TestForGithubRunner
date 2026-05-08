package clinic.model;

public class ReferralRequest {
    private final String specialty;
    private final String clinicalNotes;
    private final boolean urgent;

    public ReferralRequest(String specialty, String clinicalNotes, boolean urgent) {
        this.specialty = specialty;
        this.clinicalNotes = clinicalNotes;
        this.urgent = urgent;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public boolean isUrgent() {
        return urgent;
    }
}
