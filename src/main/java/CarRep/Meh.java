package CarRep;

import java.util.ArrayList;
import java.util.List;

public class Meh extends Person {
    public static List<Meh> mehList = new ArrayList<>();
    private final String payhour;

    public Meh(long id, String lastName, String firstName, String patronymic, String payhour) {
        super(id, lastName, firstName, patronymic);
        this.payhour = payhour;
    }

    public String getPayHour() {
        return payhour;
    }
}
