package Controller;

import Model.TypingTest;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ManageStatistics {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL=dotenv.get("DB_URL");
    private static final String dbUser=dotenv.get("DB_USER");
    private static final String dbPassword=dotenv.get("DB_PASSWORD");
    TypingTest typingTest=new TypingTest();
    public void storeStatistics() {
        try{
            Class.forName("com.jdbc.mysql.cj.Driver");
            Connection connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
            long timeTaken=typingTest.getStatistics().get("Time Taken");

        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
