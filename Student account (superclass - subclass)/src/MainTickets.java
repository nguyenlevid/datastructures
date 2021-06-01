// Test Class for Tickets

public class MainTickets {

	public static void main(String[] args) {
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		WalkUpTicket  ticket1 = new WalkUpTicket("5J");
		System.out.println("WalkUpTicket");
		System.out.println(ticket1);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		AdvanceTicket  ticket2 = new AdvanceTicket("7B", 6);
		System.out.println("AdvanceTicket");
		System.out.println(ticket2);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		AdvanceTicket ticket3 = new AdvanceTicket("8C", 10);
		System.out.println("AdvanceTicket");
		System.out.println(ticket3);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		AdvanceTicket ticket4 = new AdvanceTicket("2A", 12);
		System.out.println("AdvanceTicket");
		System.out.println(ticket4);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		StudentAdvanceTicket  ticket5 = new StudentAdvanceTicket("10J", 17);
		System.out.println("StudentAdvanceTicket");
		System.out.println(ticket5);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		StudentAdvanceTicket  ticket6 = new StudentAdvanceTicket("3G", 5);
		System.out.println("StudentAdvanceTicket");
		System.out.println(ticket6);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
		
		StudentAdvanceTicket  ticket7 = new StudentAdvanceTicket("4D", 10);
		System.out.println("StudentAdvanceTicket");
		System.out.println(ticket7);
		System.out.println("Total number of tickets sold: " + Ticket.getNumTicketsSold());
		System.out.println();
	}

}
