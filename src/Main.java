import com.lkamdem.db.DatabaseConnection;

import java.sql.Connection;

public class Main {
    static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getDatabaseConnection();
        Connection conn = db.getConnection();
        if(conn != null){
            System.out.println("Connection successful");
        }else{
            System.out.println("Connection failed");
        }
    }
}