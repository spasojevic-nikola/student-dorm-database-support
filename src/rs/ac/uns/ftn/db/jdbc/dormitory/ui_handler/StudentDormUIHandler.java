package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dto.CityDormsDTO;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.City;
import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;
import rs.ac.uns.ftn.db.jdbc.dormitory.service.StudentDormService;

public class StudentDormUIHandler {

    private static final StudentDormService dormService = new StudentDormService();

    public void handleDormMenu() {
        String answer;
        do {
            System.out.println("\nSelect an option for managing student dorms:");
            System.out.println("1 - Show all dorms");
            System.out.println("2 - Show dorm by ID");
            System.out.println("3 - Insert single dorm");
            System.out.println("4 - Insert multiple dorms");
            System.out.println("5 - Update dorm by ID");
            System.out.println("6 - Delete dorm by ID");
            System.out.println("X - Exit dorm management");

            answer = MainUIHandler.sc.nextLine();

            switch (answer) {
                case "1":
                    showAll();
                    break;
                case "2":
                    showById();
                    break;
                case "3":
                    handleSingleInsert();
                    break;
                case "4":
                    handleMultipleInserts();
                    break;
                case "5":
                    handleUpdate();
                    break;
                case "6":
                    handleDelete();
                    break;
            }

        } while (!answer.equalsIgnoreCase("X"));
    }

    private void showAll() {
        System.out.println(StudentDorm.getFormattedHeader());

        try {
            for (StudentDorm dorm : dormService.getAll()) {
                System.out.println(dorm);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showById() {
        System.out.print("Dorm ID: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            StudentDorm dorm = dormService.getById(id);
            System.out.println(StudentDorm.getFormattedHeader());
            System.out.println(dorm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSingleInsert() {
        System.out.print("Dorm ID: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        System.out.print("Name: ");
        String name = MainUIHandler.sc.nextLine();

        System.out.print("Address: ");
        String address = MainUIHandler.sc.nextLine();

        System.out.print("Capacity: ");
        int capacity = Integer.parseInt(MainUIHandler.sc.nextLine());

        System.out.print("City ID: ");
        int cityId = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            dormService.save(new StudentDorm(id, name, address, capacity, cityId));
            System.out.println("Dorm successfully added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        System.out.print("Dorm ID: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            if (!dormService.existsById(id)) {
                System.out.println("The entered dorm does not exist!");
                return;
            }

            System.out.print("Name: ");
            String name = MainUIHandler.sc.nextLine();

            System.out.print("Address: ");
            String address = MainUIHandler.sc.nextLine();

            System.out.print("Capacity: ");
            int capacity = Integer.parseInt(MainUIHandler.sc.nextLine());

            System.out.print("City ID: ");
            int cityId = Integer.parseInt(MainUIHandler.sc.nextLine());

            boolean success = dormService.save(new StudentDorm(id, name, address, capacity, cityId));
            if (success) {
                System.out.println("Dorm successfully updated.");
            } else {
                System.out.println("Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete() {
        System.out.print("Dorm ID: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            boolean success = dormService.deleteById(id);
            if (success) {
                System.out.println("Dorm successfully deleted.");
            } else {
                System.out.println("Deletion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMultipleInserts() {
        List<StudentDorm> dormList = new ArrayList<>();
        String answer;
        do {
            System.out.print("Dorm ID: ");
            int id = Integer.parseInt(MainUIHandler.sc.nextLine());

            System.out.print("Name: ");
            String name = MainUIHandler.sc.nextLine();

            System.out.print("Address: ");
            String address = MainUIHandler.sc.nextLine();

            System.out.print("Capacity: ");
            int capacity = Integer.parseInt(MainUIHandler.sc.nextLine());

            System.out.print("City ID: ");
            int cityId = Integer.parseInt(MainUIHandler.sc.nextLine());

            dormList.add(new StudentDorm(id, name, address, capacity, cityId));

            System.out.println("Stop inserting? Press X to confirm.");
            answer = MainUIHandler.sc.nextLine();
        } while (!answer.equalsIgnoreCase("X"));

        try {
            int rowsSaved = dormService.saveAll(dormList);
            System.out.println(rowsSaved + " dorm(s) successfully inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void showDormsGroupedByCity() {
        try {
            List<CityDormsDTO> dtos = dormService.getDormsGroupedByCity();
            for (CityDormsDTO dto : dtos) {
                City city = dto.getCity();
                System.out.println("City: " + city.getName() + " (" + city.getPostalCode() + ")");
                System.out.println("Dormitories:");

                for (StudentDorm dorm : dto.getDorms()) {
                    System.out.println("- " + dorm.getName() + " (Capacity: " + dorm.getCapacity() + ")");
                }

                System.out.println("------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching dorms grouped by city.");
            e.printStackTrace();
        }
    }

}
