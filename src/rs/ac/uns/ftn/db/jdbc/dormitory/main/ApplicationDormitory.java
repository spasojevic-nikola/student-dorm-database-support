package rs.ac.uns.ftn.db.jdbc.dormitory.main;

import rs.ac.uns.ftn.db.jdbc.dormitory.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.dormitory.ui_handler.MainUIHandler;

public class ApplicationDormitory {

	public static void main(String[] args) {

		// set application log level
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

		MainUIHandler mainUIHandler = new MainUIHandler();
		try {
			mainUIHandler.handleMainMenu();
			ConnectionUtil_HikariCP.closeDataSource();
		} catch (Exception e) {
			ConnectionUtil_HikariCP.closeDataSource();
		}
	}
}
