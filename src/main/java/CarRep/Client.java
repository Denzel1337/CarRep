package CarRep;


import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    public static List<Client> clientList = new ArrayList<>();
    private final String phoneNumber;

    public Client(long id, String lastName, String firstName, String patronymic, String phoneNumber) {
        super(id, lastName, firstName, patronymic);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

