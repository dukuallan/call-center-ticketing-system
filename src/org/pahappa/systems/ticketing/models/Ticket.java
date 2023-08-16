package org.pahappa.systems.ticketing.models;

/**
 * A ticket refers to a unit of work or a request that is submitted
 * by a user or customer to seek assistance, report an issue, or request a service.
 * It serves as a record or a container that captures all the relevant information
 * related to the user's request or issue.
 */
public class Ticket {
    String customerName;
    String contact;
    String ticketCategory;
    String description;
    String status;
    String priority;


    public Ticket() {
    }

    
    public Ticket(String customerName, String contact, String ticketCategory, String description, String status,
            String priority) {
        this.customerName = customerName;
        this.contact = contact;
        this.ticketCategory = ticketCategory;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }


    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTicketCategory() {
        return this.ticketCategory;
    }

    public void setTicketCategory(String ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }





}
