package sample.Model;

import java.time.LocalDateTime;

/**
 * Appointment class. Used to set and retrieve data for an appointment object
 */
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

    /**
     * Constructor for appointment with all data parameters
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param userId
     * @param customerId
     * @param contactId
     */
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

    /**
     * Constructor for appointment without the appointment ID parameter.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param userId
     * @param customerId
     * @param contactId
     */
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

    /**
     * Retrieves ID of an appointment
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves title of an appointment
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves description of an appointment
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves location of an appointment
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Retrieves type of an appointment
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves start date/time of appointment
     * @return start date/time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Retrieves end date/time of appointment
     * @return end date/time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Retrieves user ID from appointment.
     * @return user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves customer ID from appointment.
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Retrieves contact ID from appointment
     * @return contact ID
     */
    public int getContactId() {
        return contactId;
    }

}
