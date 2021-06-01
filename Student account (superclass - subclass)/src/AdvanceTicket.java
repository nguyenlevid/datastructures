/**
 * Name: Viet Nguyen
 * Date: 18th Mar 2021
 * CSC 202--Mini Lab 5
 * 
 * AdvanceTicket represents a ticket bought before an event
 *
 */
public class AdvanceTicket extends Ticket {
	public static final double ADVANCE_EARLY_PRICE = 30.00;
	public static final double ADVANCE_PRICE = 40.00;
	public static final int DAYS_EARLY = 10;
	
	//instance data field
	private int days;
	
	
	/**
	 * Constructs a ticket bought before an event
	 * @param seatNumber- the seat number for this ticket
	 * @param days the number of days before the event that the ticket was purchased
	 */
	public AdvanceTicket(String seatNumber, int days) {
		
		super(seatNumber);
		this.days = days;
	}
	
	
//	@Override
	public double getPrice() {
		return (days >= DAYS_EARLY ? ADVANCE_EARLY_PRICE : ADVANCE_PRICE);
	}
	
}
