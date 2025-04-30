package Controller;

import Model.TypingTest;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ManageStatistics {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL=dotenv.get("DB_URL");
    private static final String dbUser=dotenv.get("DB_USER");
    private static final String dbPassword=dotenv.get("DB_PASSWORD");
    Connection connection;
    public ManageStatistics() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void storeStatistics(String email,TypingTest typingTest) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM typing_test_statistics where email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) {
                preparedStatement=connection.prepareStatement("INSERT INTO typing_test_statistics(email,no_of_games,wpm,errors) VALUES(?,?,?,?)");
                preparedStatement.setString(1,email);
                preparedStatement.setInt(2,1);
                int wpm=typingTest.getStatistic("WPM");
                preparedStatement.setDouble(3,wpm);
                int errors=typingTest.getStatistic("Errors");
                preparedStatement.setDouble(4,errors);
                preparedStatement.executeUpdate();
                return;
            }
            double previousWpm=resultSet.getDouble("wpm");
            double previousErrors=resultSet.getDouble("errors");
            double thisWpm=typingTest.getStatistic("WPM");
            double thisErrors=typingTest.getStatistic("Errors");
            int noOfGames=resultSet.getInt("no_of_games")+1;
            double newWpm=(previousWpm*(noOfGames-1)+thisWpm)/noOfGames;
            double newErrors=(previousErrors*(noOfGames-1)+thisErrors)/noOfGames;
            preparedStatement=connection.prepareStatement("UPDATE typing_test_statistics SET no_of_games=?, wpm=?,errors=? WHERE email=?");
            preparedStatement.setInt(1,noOfGames);
            preparedStatement.setDouble(2,newWpm);
            preparedStatement.setDouble(3,newErrors);
            preparedStatement.setString(4,email);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void getStatistics(String email) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM typing_test_statistics WHERE email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No records found");
                return;
            }
            System.out.println("No of Games: "+resultSet.getInt(2));
            System.out.println("Average WPM: "+resultSet.getInt(3));
            System.out.println("Average Errors: "+resultSet.getInt(4));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
