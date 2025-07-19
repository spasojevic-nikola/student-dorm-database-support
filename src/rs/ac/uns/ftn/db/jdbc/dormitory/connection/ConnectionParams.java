package rs.ac.uns.ftn.db.jdbc.dormitory.connection;

public class ConnectionParams {
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    // Ako koristiš Oracle 21c XE i povezuješ se lokalno:
    public static final String LOCAL_CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    // Nije ti potreban ovaj connection string ako ne koristiš učionicu:
    public static final String CLASSROOM_CONNECTION_STRING = "jdbc:oracle:thin:@192.168.0.102:1522:db2016";

    // Tačno korisničko ime i lozinka
    public static final String USERNAME = "app_user";
    public static final String PASSWORD = "super";
}
