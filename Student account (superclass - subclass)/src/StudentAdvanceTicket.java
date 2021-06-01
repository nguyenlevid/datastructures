/**
 * Name: Viet Nguyen
 * Date: 18th Mar 2021
 * CSC 202--Mini Lab 5
 * 
 * StudentAdvanceTicket represents a ticket purchased
 * by a ticket before an event by a student with a student ID.
 *
 */
public class StudentAdvanceTicket extends AdvanceTicket {

	/**
	 * Constructs a student ticket bought before an event
	 * @param seatNumber- the ticket number for this ticket
	 * @param days the number of days before the event that the ticket was purchased
	 */
	public StudentAdvanceTicket(String seatNumber, int days) {
		super(seatNumber, days);
	}
	
	
	
//	@Override
	public double getPrice() {
		return super.getPrice()/2;
	}
	
	
//	@Override
	public String toString() {
		return super.toString() + " (ID required)";
	}
	
}
