package CarRep;

public class Person {
    private final long id;
    private final String lastName;
    private final String firstName;
    private final String patronymic;

    public Person(long id, String lastName, String firstName, String patronymic) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

}
