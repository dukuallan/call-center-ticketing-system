package org.pahappa.systems.ticketing.services.impl;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketServiceImpl implements TicketService {
    List<Ticket> ticketList = new ArrayList<>();
    @Override
    public void createTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketList;
    }

    
    @Override
    public List<Ticket> getTicketsOfStatus(TicketStatus ticketStatus) {
        
        List<Ticket> groupedList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if(ticket.getStatus() == ticketStatus.toString()){
                groupedList.add(ticket);
            }           
        }             
        return groupedList;
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {
        System.out.println("Selected the attribute to update..");
        System.out.println("\n 1. status ");
        Scanner scanner = new Scanner(System.in);
        int selectedValue = scanner.nextInt();
        if(selectedValue == 1){
        boolean input = false;
        while(!input){
            try {
                System.out.println("Choose ticket status ");

                for(int i = 0; i<TicketStatus.values().length; i++){
                    System.out.println( i + " " + TicketStatus.values()[i]);
                }
                int selectedStatus = scanner.nextInt();
                updatedTicket.setStatus(TicketStatus.values()[selectedStatus].toString());
                ticketList.set(ticketList.indexOf(updatedTicket), updatedTicket);

                input = true;
                
            } catch (Exception e) {
                System.out.println("Invalid input.. , try again!");
                scanner.nextLine();
            }
        }
            
        }

    }

    @Override
    public void deleteTicket(int index) {
        ticketList.remove(index); 
        System.out.println("Ticket successfully deleted...\n");
    }
}
