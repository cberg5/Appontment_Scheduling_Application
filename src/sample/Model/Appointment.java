package sample.Model;

import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int userId;
    private int customerId;
    private int contactId;


    public Appointment(int id, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int userId, int customerId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.userId = userId;
        this.customerId = customerId;
        this.contactId = contactId;
    }

    public Appointment(String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int userId, int customerId, int contactId) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.userId = userId;
        this.customerId = customerId;
        this.contactId = contactId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getContactId() {
        return contactId;
    }

}
