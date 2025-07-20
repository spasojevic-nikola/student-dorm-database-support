package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.util.Scanner;

public class MainUIHandler {

	public static Scanner sc = new Scanner(System.in);
	
	private final StudentDormUIHandler studentDormUIHandler = new StudentDormUIHandler();

	public void handleMainMenu() {

		String answer;
		do {
			System.out.println("\nSelect an option:");
			System.out.println("1 - Manage student dorms");        
			System.out.println("2 - Show student dorms grouped by city");
			System.out.println("X - Exit the program");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				studentDormUIHandler.handleDormMenu();
				break;
			case "2":
	            studentDormUIHandler.showDormsGroupedByCity();
				break;
			}
				

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}

}
