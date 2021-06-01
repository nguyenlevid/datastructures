/**
 * 
 * A simple date class to check the validity of a date and store it
 *
 */
import java.util.GregorianCalendar;

public class SimpleDate implements Comparable<SimpleDate>{

	public static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final int JAN = 1;
	public static final int FEB = 2;
	public static final int DEC = 12;
	public static final int MIN_DAY = 1;
	public static final int LEAP_DAY = 29;
	public static final int MIN_YEAR = 1752;

	private int month;
	private int day;
	private int year;

	/**
	 * constructs a SimpleDate object with the given month, day, and year
	 * @param month number of the month 1-12
	 * @param day number of the day valid for the month
	 * @param year the year of this date
	 * @throws IllegalArgumentException when the month, day, or year are invalid
	 */
	public SimpleDate(int month, int day, int year) {
		if (month < JAN || month > DEC) {
			throw new IllegalArgumentException("Month number invalid");
		}

		if (year < MIN_YEAR) {
			throw new IllegalArgumentException("Year invalid");
		}

		if (day < MIN_DAY
				|| (isLeapYear(year)
						&& (month == FEB && day > LEAP_DAY || month != FEB && day > DAYS_IN_MONTH[month]))
				|| (!isLeapYear(year) && day > DAYS_IN_MONTH[month])) {
			throw new IllegalArgumentException("Day number invalid");
		}

		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * constructs a SimpleDate object of the current date from the 
	 * compute clock when the constructor is executed
	 */
	public SimpleDate() {
		GregorianCalendar today = new GregorianCalendar();
		year = today.get(GregorianCalendar.YEAR);
		month = today.get(GregorianCalendar.MONTH) + 1;
		day = today.get(GregorianCalendar.DATE);
	}

	/**
	 * @return the number of the month of this SimpleDate
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * @return the year of this SimpleDate
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the number of the day of this SimpleDate
	 */
	public int getDay() {
		return day;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof SimpleDate) {
			SimpleDate other = (SimpleDate) object;
			return day == other.day && month == other.month && year == other.year;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(SimpleDate other) {
		return daysToDate() - other.daysToDate();
	}
	
	// returns the number of days until this date from Jan 1
	private int daysToDate() {
		int total = 0;
		for (int numMonth = 1; numMonth < month; numMonth++) {
			if (numMonth == 2 && isLeapYear(year)) {
				total = total + LEAP_DAY;
			} else {
				total = total + DAYS_IN_MONTH[numMonth];
			}
		}
		total = total + day;
		return total;
	}

	/**
	 * isLeapYear determines if a year is a leap year or not
	 * 
	 * @param year--the
	 *            year which will be evaluated
	 * @return true if the year is a leap year, false otherwise
	 */

	private boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		} else if (year % 100 == 0) {
			return false;
		} else if (year % 4 == 0) {
			return true;
		} else {
			return false;
		}

	}
	

	/**
	 * @return a string representation momth/day/year of this SimpleDate
	 */
	public String toString() {
		return month + "/" + day + "/" + year;
	}
}
