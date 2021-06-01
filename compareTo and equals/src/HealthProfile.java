/*
 * Name: Viet Nguyen
 * Date: 24th Mar 2021
 * CSC 202
 * Lab06 -- HealthProfile.java
 * 
 * HeathProfile defines a data type for the basic health information of a patient 
 * including name, birth date, age, height, weight, heart rate information, and bmi information.
 */

public class HealthProfile implements Comparable<HealthProfile>{
	// class constants
	private static final int MAX_RATE = 220;
	private static final double MIN_RANGE_PERCENT = .50;
	private static final double MAX_RANGE_PERCENT = .85;
	private static final double BMI_LOW_CUT = 18.5;
	private static final double BMI_MID_CUT = 25;
	private static final double BMI_HIGH_CUT = 30;
	private static final int CONVERSION_FACTOR = 703;
	
	// fields
	private String idNum;
	private String lastName;
	private String firstName;
	private SimpleDate birthDate;
	private int height;
	private int weight;

	/**
	 * Constructs a health profile for a patient
	 * @param idNum id number of the patient
	 * @param lastName last name of the patient
	 * @param firstName first name of the patient
	 * @param birthDate birth date of the patient
	 * @param height height of the patient measured in inches
	 * @param weight weight of the patient measured in pounds
	 */
	public HealthProfile(String idNum, String lastName, String firstName,
			SimpleDate birthDate, int height, int weight) {
		this.idNum = idNum;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		setHeight(height);
		setWeight(weight);
	}
	
	
	/**
	 * @return the id number of this patient
	 */
	public String getIdNum(){
		return idNum;
	}

	/**
	 * @return the last name of this patient
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the first name of this patient
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return birth date of this patient
	 */
	public SimpleDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @return height of this patient in inches
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return weight of this patient in pounds
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * changes the height of this patient
	 * @param height the new height of this patient in inches
	 * @throws IllegalArgumentException when the height is not positive
	 */
	public void setHeight(int height) throws IllegalArgumentException {
		if (height <= 0) {
			throw new IllegalArgumentException();
		}
		this.height = height;
	}

	/**
	 * changes the weight of this patient
	 * @param weight the new weight of this patient in pounds
	 * @throws IllegalArgumentException
	 */
	public void setWeight(int weight) throws IllegalArgumentException {
		if (weight <= 0) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}

	/**
	 * @return the age of this patient in years
	 */
	public int getAge() {
		SimpleDate today = new SimpleDate();
		
		int age = today.getYear() - birthDate.getYear();

		if (today.getMonth() < birthDate.getMonth()) {
			age--;
		} else if (today.getMonth() == birthDate.getMonth() && today.getDay() < birthDate.getDay()){
			age--;
		}
		return age;
	}

	/**
	 * @return the minimum heart rate in acceptable range for this patient
	 */
	public int getMinTargetRange() {
		return (int) Math.round(MIN_RANGE_PERCENT * getMaxHeartRate());
	}

	/**
	 * @return the maximum heart rate in acceptable range for this patient
	 */
	public int getMaxTargetRange() {
		return (int) Math.round(MAX_RANGE_PERCENT * getMaxHeartRate());
	}

	// helper method to compute maximum heart rate based on this patient's age
	private int getMaxHeartRate() {
		return MAX_RATE - getAge();
	}
	
	/**
	 * @return the body mass index (BMI) of this patient
	 */
	public double getBmi() {
		double bmi = (double) (weight * CONVERSION_FACTOR) / (height * height);
		return Math.round(bmi * 10) / 10.0;
	}

	/**
	 * @return this patient's weight category based on BMI
	 */
	public String getBmiCategory() {
		double bmi = getBmi();
		if (bmi < BMI_LOW_CUT) {
			return "Underweight";
		} else if (bmi < BMI_MID_CUT) {
			return "normal";
		} else if (bmi < BMI_HIGH_CUT) {
			return "Overweight";
		} else {
			return "Obese";
		}
	}
	
	/**
	 * Add your equals method here!
	 */
	//TODO
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof HealthProfile)) {
			return false;
		}
		HealthProfile other = (HealthProfile) object;
		if (this.idNum.equals(other.idNum) && this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.birthDate.equals(other.birthDate) && this.height == other.height && this.weight == other.weight) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Add your compareTo method here!
	 */
	//TODO
	public int compareTo(HealthProfile other) {
		if (!this.lastName.equals(other.lastName)) {
			return this.lastName.compareTo(other.lastName);
		}
		if (!this.firstName.equals(other.firstName)) {
			return this.firstName.compareTo(other.firstName);
		}
		if (this.height != other.height) {
			return this.height - other.height;
		}
		return this.weight - other.weight;
	}
	
	@Override
	public String toString() {
		return "HealthProfile [" + idNum + " " + lastName + ", " + firstName + " "
				+ birthDate + " " + height + " in. "
				+ weight + " lbs.]";
	}
	
	
}
