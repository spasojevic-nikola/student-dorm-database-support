package rs.ac.uns.ftn.db.jdbc.dormitory.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.dormitory.model.StudentDorm;

public interface StudentDormDAO extends CRUDDao<StudentDorm, Integer> {
	List<StudentDorm> findByCityId(int cityId) throws SQLException;
}
