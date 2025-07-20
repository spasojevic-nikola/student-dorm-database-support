package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import rs.ac.uns.ftn.db.jdbc.dormitory.model.Payment;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.Student;
import rs.ac.uns.ftn.db.jdbc.dormitory.service.StudentPaymentTransactionService;

public class StudentPaymentTransactionUIHandler {

    private static final Scanner sc = new Scanner(System.in);
    private final StudentPaymentTransactionService transactionService = new StudentPaymentTransactionService();

    public void handleTransactionMenu() {
        String choice;
        do {
            System.out.println("\nTransaction Menu:");
            System.out.println("1 - Add new payment with student");
            System.out.println("2 - Update payment and student info");
            System.out.println("3 - Delete payment and related student data");
            System.out.println("X - Return to main menu");

            choice = sc.nextLine();

            switch (choice) {
			    case "1":
			        addPaymentAndStudent();
			        break;
			    case "2":
			        updatePaymentAndStudent();
			        break;
			    case "3":
			        deletePaymentAndStudent();
			        break;
			}

        } while (!choice.equalsIgnoreCase("X"));
    }

    private void addPaymentAndStudent() {
        System.out.println("\n--- Add New Student and Payment ---");

        try {
            // Student
            System.out.print("Enter student ID: ");
            int studentId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter student first name: ");
            String firstName = sc.nextLine();
            System.out.print("Enter student last name: ");
            String lastName = sc.nextLine();
            System.out.print("Enter student gender (M/F): ");
            String gender = sc.nextLine();
            System.out.print("Enter student phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter student address: ");
            String address = sc.nextLine();
            System.out.print("Enter room ID: ");
            int roomId = Integer.parseInt(sc.nextLine());

            Student student = new Student(studentId, firstName, lastName, gender, phone, address, roomId);

            // Payment
            System.out.print("Enter payment ID: ");
            int paymentId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter payment amount: ");
            double amount = Double.parseDouble(sc.nextLine());
            System.out.print("Enter payment type: ");
            String type = sc.nextLine();
            System.out.print("Enter payment description: ");
            String description = sc.nextLine();
            
            Date currentDate = Date.valueOf(LocalDate.now());

            Payment payment = new Payment(paymentId, amount, type, currentDate, description, studentId);

            transactionService.addPaymentWithStudent(student, payment);

            System.out.println("Student and payment saved successfully (transaction committed).");
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("Transaction failed: Duplicate key error. Student or payment ID already exists.");
        } catch (SQLException e) {
            System.out.println("Transaction failed due to database error: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter correct numeric values.");
        }
    }


    private void updatePaymentAndStudent() {
        System.out.println("--- Update Existing Student and Payment ---");
        try {
            System.out.print("Enter existing student ID: ");
            int studentId = Integer.parseInt(sc.nextLine());

            if (!transactionService.studentExists(studentId)) { //check if student exists in db
                System.out.println("Student with ID " + studentId + " does not exist.");
                return;
            }

            Student existingStudent = transactionService.getStudentById(studentId);
            if (existingStudent == null) {
                System.out.println("Failed to load existing student details.");
                return;
            }

            System.out.print("Enter new first name: ");
            String firstName = sc.nextLine();
            System.out.print("Enter new last name: ");
            String lastName = sc.nextLine();
            System.out.print("Enter new phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter new address: ");
            String address = sc.nextLine();

            System.out.print("Enter existing payment ID: ");
            int paymentId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new amount: ");
            double amount = Double.parseDouble(sc.nextLine());
            System.out.print("Enter new payment type: ");
            String type = sc.nextLine();
            System.out.print("Enter new payment description: ");
            String description = sc.nextLine();

            //update fields which we changed
            existingStudent.setFirstName(firstName);
            existingStudent.setLastName(lastName);
            existingStudent.setPhone(phone);
            existingStudent.setAddress(address);

            Payment payment = new Payment();
            payment.setId(paymentId);
            payment.setTotalAmount(amount);
            payment.setPaymentType(type);
            payment.setDescription(description);
            payment.setStudentId(studentId);

            transactionService.updatePaymentWithStudent(existingStudent, payment);
            System.out.println("Student and payment updated successfully.");

        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-02291")) {
                System.out.println("Update failed: Foreign key constraint violated. Please check if related records exist.");
            } else {
                System.out.println("Transaction failed due to SQL error: " + e.getMessage());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input format. Please enter valid numbers.");
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void deletePaymentAndStudent() {
        try {
            System.out.println("\n--- Delete Payment and Student ---");

            System.out.print("Enter payment ID: ");
            int paymentId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter student ID: ");
            int studentId = Integer.parseInt(sc.nextLine());

            transactionService.deletePaymentWithStudent(studentId, paymentId);

            System.out.println("Student and payment deleted successfully (transaction committed).");

        } catch (SQLException e) {
            System.out.println("Transaction failed: " + e.getMessage());
            // e.printStackTrace(); // ovo stoji za debug
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter valid numeric IDs.");
        }
    }

}
