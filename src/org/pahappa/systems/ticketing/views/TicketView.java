package org.pahappa.systems.ticketing.views;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;
import org.pahappa.systems.ticketing.services.impl.TicketServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketView implements BaseTicketView {

    private final TicketService ticketService;
    private final Scanner scanner;

    public TicketView() {
        this.ticketService = new TicketServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("********* Call Center Ticket System *********\n\n");
        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Ticket");
            System.out.println("2. Get All Tickets");
            System.out.println("3. Get Tickets of Status");
            System.out.println("4. Update Ticket");
            System.out.println("5. Delete Ticket");
            System.out.println("6. Exit");
            System.out.println();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createTicket();
                    break;
                case 2:
                    getAllTickets();
                    break;
                case 3:
                    getTicketsOfStatus();
                    break;
                case 4:
                    updateTicket();
                    break;
                case 5:
                    deleteTicket();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    @Override
    public void createTicket() {
        Ticket ticketObject = new Ticket();
        System.out.println("Please create ticket");
        //Customer name
        System.out.println("Enter customer name: ");
        ticketObject.setCustomerName(scanner.nextLine());
        //Contact
        System.out.println("Enter contact: ");
        ticketObject.setContact(scanner.nextLine());
        //Ticket Category
        System.out.println("Enter ticket Category: ");
        ticketObject.setTicketCategory(scanner.nextLine());
        //Ticket issue
        System.out.println("Please describe ticket issue: ");
        ticketObject.setDescription(scanner.nextLine());
        //Ticket Priority
        boolean input= false;
        while(!input){
            try {
                System.out.println("Please select ticket priority");
                List<String> priorityChoice = new ArrayList<String>();
                priorityChoice.add(0, "Low");
                priorityChoice.add(1,"Moderate");
                priorityChoice.add(2, "High");

                for(int i = 0; i<priorityChoice.size(); i++){
                    System.out.println(i + ". " + priorityChoice.get(i));
                }
                int choiceSelected = scanner.nextInt();

                ticketObject.setPriority(priorityChoice.get(choiceSelected)); 
                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input.. , try again!");
                scanner.nextLine();
            }
        }
   

        //Ticket Status

        input = false;
        while(!input){
            try {
                System.out.println("Choose ticket status ");

                for(int i = 0; i<TicketStatus.values().length; i++){
                    System.out.println( i + " " + TicketStatus.values()[i]);
                }
                int selectedStatus = scanner.nextInt();
                ticketObject.setStatus(TicketStatus.values()[selectedStatus].toString());
                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input.. , try again!");
                scanner.nextLine();
            }
        }
        

        //Ticket Creation
        

        Ticket ticket = new Ticket(ticketObject.getCustomerName(), ticketObject.getContact(),
                                    ticketObject.getTicketCategory(), ticketObject.getDescription(), 
                                    ticketObject.getStatus(), ticketObject.getPriority());
        
        ticketService.createTicket(ticket);
        
    }
    

    @Override
    public void getAllTickets() {
        for (Ticket eachTicket : ticketService.getAllTickets()) {
            System.out.println(eachTicket);
            
        } 
         
    }

    @Override
    public void getTicketsOfStatus() {
        boolean input = false;
        while(!input){
            try {
                System.out.println("Choose ticket status ");

                for(int i = 0; i<TicketStatus.values().length; i++){
                    System.out.println( i + " " + TicketStatus.values()[i]);
                }
                int selectedStatus = scanner.nextInt();
                TicketStatus selected = TicketStatus.values()[selectedStatus];
                List<Ticket> groupTickets = ticketService.getTicketsOfStatus(selected);
                
                for (Ticket ticket : groupTickets) {
                    System.out.println(ticket);      
                }
                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input.. , try again!");
                scanner.nextLine();
            }
        }   
    }


    @Override
    public void updateTicket() {
        boolean input = false;
        while(!input){
            try {
                System.out.println("Select TicketID.. ");
                int i = 0;
                List<Ticket> selectedTicket = ticketService.getAllTickets();
                for (Ticket eachTicket : selectedTicket) {
                    System.out.println("Ticket ID " + (++i) + ".  " + eachTicket);    
                } 

                int selectedChoice = scanner.nextInt();
                ticketService.updateTicket(selectedTicket.get(selectedChoice));
                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input .. try again \n");
                
            }
            

        }
        
    }

    @Override
    public void deleteTicket() {
        boolean input = false;
        while(!input){
            try {
                System.out.println("Select Ticket ID from ticket lists below..");
                int i = 0;
                List<Ticket> selectedTicket = ticketService.getAllTickets();
                for (Ticket eachTicket : selectedTicket) {
                    System.out.println("Ticket ID " + (++i) + ".  " + eachTicket);    
                } 

                int selectedChoice = scanner.nextInt();
                ticketService.deleteTicket(selectedChoice-1);
                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input .. try again \n");
                
            } 

        }

    }
}
