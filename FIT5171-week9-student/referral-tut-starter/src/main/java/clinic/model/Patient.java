package clinic.model;

public class Patient {
    private final long id;
    private final String name;
    private boolean medicareNumberPresent;

    public Patient(long id, String name, boolean medicareNumberPresent) {
        this.id = id;
        this.name = name;
        this.medicareNumberPresent = medicareNumberPresent;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasMedicareNumber() {
        return medicareNumberPresent;
    }

    public void setMedicareNumberPresent(boolean medicareNumberPresent) {
        this.medicareNumberPresent = medicareNumberPresent;
    }
}
