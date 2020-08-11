package CarRep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    public static List<Order> orderList = new ArrayList<>();
    private final long id;
    private final String description;
    private final String client;
    private final String meh;
    private final Date createDate;
    private final Date endDate;
    private final String cost;
    private final String status;

    public Order(long id, String description, String client, String meh, Date createDate, Date endDate, String cost, String status) {
        this.id = id;
        this.description = description;
        this.client = client;
        this.meh = meh;
        this.createDate = createDate;
        this.endDate = endDate;
        this.cost = cost;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getClient() {
        return client;
    }

    public String getMeh() {
        return meh;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }
}

