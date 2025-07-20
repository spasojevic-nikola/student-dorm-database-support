package rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.dto.StudentFullDetailsDTO;
import rs.ac.uns.ftn.db.jdbc.dormitory.service.ComplexQueryService;

public class ComplexQueryUIHandler {

    private final ComplexQueryService service = new ComplexQueryService();

    public void showStudentsWithDetails() {
        try {
            List<StudentFullDetailsDTO> students = service.getStudentsWithFullDetails();

            System.out.println(StudentFullDetailsDTO.getFormattedHeader());
            for (StudentFullDetailsDTO dto : students) {
                System.out.println(dto);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while fetching student details.");
            e.printStackTrace();  // Dodaj ovo da vidiš tačno gde puca
        }
    }
}
