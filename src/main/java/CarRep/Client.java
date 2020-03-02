package CarRep;

public class Client {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phoneNumber;

    public Client(Long id, String lastName, String firstName, String patronymic, String phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
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
    public String getPhoneNumber() {
        return  phoneNumber;
    }
    public String getFullName() {return lastName + " " + firstName;}
}

