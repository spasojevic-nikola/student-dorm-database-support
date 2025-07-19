package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.util.Scanner;

public class MainUIHandler {

	public static Scanner sc = new Scanner(System.in);

	public void handleMainMenu() {

		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Rukovanje studentskim domovima");
			System.out.println("X - Izlazak iz programa");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				break;
			case "2":
				break;
			}
				

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}

}
