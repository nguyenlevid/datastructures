/**
 * 
 * Test code for checking HealthProfiles for equality and ordering
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.SimpleDateFormat; 
import java.io.*;

public class HealthProfileClient {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);

		ArrayList<HealthProfile> patientList = getData();
		outputList(patientList);
		
		testForEquality(patientList.get(0));

		System.out.println("\nSorting by names using compareTo in the HealthProfile class:");
		Collections.sort(patientList);
		outputList(patientList);
	}
	

	/*
     * getData method reads a file of patient health profiles storing each individual's profile
     * as an element in an array list returning the array list.
     */
	public static ArrayList<HealthProfile> getData() throws FileNotFoundException {
		Scanner input = new Scanner(new File("patientProfiles.txt"));
	
		ArrayList<HealthProfile> patientList = new ArrayList<HealthProfile>();

		while (input.hasNextLine()) {
			String idNumber = input.next();
			String lastName = input.next();
			String firstName = input.next();
			int month = input.nextInt();
			int day = input.nextInt();
			int year = input.nextInt();
			SimpleDate birthDate = new SimpleDate(month, day, year);
			int height = input.nextInt();
			int weight = input.nextInt();

			patientList.add(new HealthProfile(idNumber, lastName, firstName,
					birthDate, height, weight));
		}

		return patientList;

	}
	
	/**
	 * outputs the array list one element per line
	 * @param list - the array list to be printed
	 */
	public static void outputList(ArrayList<HealthProfile> list){
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * Compares the accepts HealthProfile object with others for equality
	 * @param profile the HealthProfile to be compared
	 */
	public static void testForEquality(HealthProfile profile) {
		HealthProfile patient1 = new HealthProfile("123456789", "Slim", "Jim", new SimpleDate(6, 12, 1946), 74, 140); // equal
		equalityTester(1, profile, patient1);
		HealthProfile patient2 = new HealthProfile("987654321", "Slim", "Jim", new SimpleDate(6, 12, 1946), 74, 140); // NOT equal
		equalityTester(2, profile, patient2);
		HealthProfile patient3 = new HealthProfile("123456789", "Smith", "Jim", new SimpleDate(6, 12, 1946), 74, 140);// NOT equal
		equalityTester(3, profile, patient3);
		HealthProfile patient4 = new HealthProfile("123456789", "Slim", "Jim", new SimpleDate(7, 12, 1946), 74, 140); // NOT equal
		equalityTester(4, profile, patient4);
		HealthProfile patient5 = new HealthProfile("123456789", "Slim", "Jim", new SimpleDate(6, 12, 1946), 68, 140); // NOT equal
		equalityTester(5, profile, patient5);
		HealthProfile patient6 = new HealthProfile("123456789", "Slim", "Jim", new SimpleDate(7, 12, 1946), 74, 158); // NOT equal
		equalityTester(6, profile, patient6);
		Object patient7 = new HealthProfile("123456789", "Slim", "Jim", new SimpleDate(6, 12, 1946), 74, 140); // equal
		if (profile.equals(patient7)) {
			System.out.println("\nTest #7: Correct The profiles are equal");
		} else {
			System.out.println("\nTest #7: Incorrect");
		}
		String words = "Not even a HealthProfile object";// NOT equal
		if (profile.equals(words)) {
			System.out.println("\nTest #8: NOT CORRECT Strings and HealthProfiles are equal?");
		} else {
			System.out.println("\nTest #8: Correct Strings and HealthProfiles are not equal.");
		}
		HealthProfile patient9 = null;
		if (profile.equals(patient9)) {
			System.out.println("\nTest #9: NOT CORRECT null can't be equal to a HealthProfile");
		} else {
			System.out.println("\nTest #9: Correct null and a HealthProfile are not equal");
		}
	}
	
	// compares your equals method for two HealthProfile objects
	private static void equalityTester(int num, HealthProfile profile, HealthProfile other) {
		System.out.print("\nTest #" + num + "  ");
		if (profile.equals(other)) {
			System.out.println("The profiles are equal");
		} else {
			System.out.println("The profiles are NOT equal");
		}
	}
}
