/**
 * Name: Viet Nguyen
 * Date: 18th Mar 2021
 * CSC 202--Mini Lab 5
 * 
 * WalkUpTicket represents a ticket purchased
 * the day of the event from the ticket office.
 *
 */
public class WalkUpTicket extends Ticket {
	public static final double WALKUP_PRICE = 50.00;
	
	/**
	 * Constructs a ticket purchased the day of the event
	 * @param seatNumber- the ticket number for this ticket
	 */
	public WalkUpTicket(String seatNumber) {
		super(seatNumber);
	}
	
	
//	@Override
	public double getPrice() {
		return WALKUP_PRICE;
	}
}
