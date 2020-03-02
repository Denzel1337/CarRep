package CarRep;

public class Meh {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String payhour;

    public Meh(Long id, String lastName, String firstName, String patronymic, String payhour) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.payhour = payhour;
    }
    public Long getId() {return id;}
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getPayHour() {
        return  payhour;
    }
    public String getFullName() { return lastName + " " + firstName; }
}
