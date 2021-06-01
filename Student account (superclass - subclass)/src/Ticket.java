/**
 * Name: Viet Nguyen
 * Date: 18th Mar 2021
 * CSC 202--Mini Lab 5
 * 
 *An abstract class to represent a ticket for an event storing the
 *seat number and the total number of tickets sold.
 */
public abstract class Ticket {
	//static data field
	private static int numTicketsSold = 0;
	
	//instance data field
	private String seatNumber;
	
	/**
	 * Constructs a ticket with a seat number
	 * @param seatNumber- the seat number for this ticket
	 */
	public Ticket(String seatNumber){
		this.seatNumber = seatNumber;
		numTicketsSold += 1;
	}
	
	/**
	 * Provides the price of this ticket
	 * @return the ticket price
	 */
	public abstract double getPrice();
	
	/**
	 * Provides a string representation of a ticket
	 * @return a string representation of a ticket
	 */
	public String toString(){
		return String.format("Seat number: %s, Price: $%.2f", seatNumber, getPrice());
	}
	
	/**
	 * Provides the number of tickets sold
	 * @return the number of tickets
	 */
	public static int getNumTicketsSold() {
		return numTicketsSold;
	}

}
