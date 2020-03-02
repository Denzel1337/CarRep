package CarRep;

import java.util.Date;

public class Order {
    private Long id;
    private String description;
    private String client;
    private String meh;
    private Date createDate;
    private Date endDate;
    private String cost;
    private String status;

    public Order(Long id, String description, String client, String meh, Date createDate, Date endDate, String cost, String status) {
        this.id = id;
        this.description = description;
        this.client = client;
        this.meh = meh;
        this.createDate = createDate;
        this.endDate = endDate;
        this.cost = cost;
        this.status = status;
    }
    
    public Long getId() {return id;}
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
        return  createDate;
    }
    public Date getEndDate() { return endDate; }
    public String getCost() { return cost; }
    public String getStatus() { return status; }
}

