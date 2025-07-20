package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.util.Scanner;

public class MainUIHandler {

	public static Scanner sc = new Scanner(System.in);
	
	private final StudentDormUIHandler studentDormUIHandler = new StudentDormUIHandler();
	private final ComplexQueryUIHandler complexQueryUIHandler = new ComplexQueryUIHandler();
    private final StudentPaymentTransactionUIHandler transactionUIHandler = new StudentPaymentTransactionUIHandler(); // nov

	public void handleMainMenu() {

		String answer;
		do {
			System.out.println("\nSelect an option:");
			System.out.println("1 - Manage student dorms");        
			System.out.println("2 - Show student dorms grouped by city");
	        System.out.println("3 - Complex query: View students with faculty, dorm, room, city, and inventory details");
	        System.out.println("4 - Complex query: View payments per Student with Faculty, Dorm, Room, and City");
            System.out.println("5 - Transaction: Add/update/delete student payment and related data");  // nova opcija

			System.out.println("X - Exit the program");

			answer = sc.nextLine();

			switch (answer) {
				case "1":
					studentDormUIHandler.handleDormMenu();
					break;
				case "2":
		            studentDormUIHandler.showDormsGroupedByCity();
					break;
				case "3":
	                complexQueryUIHandler.showStudentsWithDetails();
	                break;
				case "4":
				    complexQueryUIHandler.showPaymentsWithDetails();
				    break;
				case "5":
                    transactionUIHandler.handleTransactionMenu();  // poziv metode za transakcije
                    break;
			}
				

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}

}
