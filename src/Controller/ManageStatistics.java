package Controller;

import Model.TypingTest;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ManageStatistics {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL=dotenv.get("DB_URL");
    private static final String dbUser=dotenv.get("DB_USER");
    private static final String dbPassword=dotenv.get("DB_PASSWORD");
    public ManageStatistics() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver not found");
        }
    }
    public void storeStatistics(String email,TypingTest typingTest) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        try{
            connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
            //Email exists
            preparedStatement=connection.prepareStatement("SELECT * FROM typing_test_statistics where email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            //Inserting new statistics
            if (!resultSet.next()) {
                preparedStatement=connection.prepareStatement("INSERT INTO typing_test_statistics(email,no_of_games,wpm,accuracy) VALUES(?,?,?,?)");
                preparedStatement.setString(1,email);
                preparedStatement.setInt(2,1);
                double wpm=typingTest.getStatistic("WPM");
                preparedStatement.setInt(3,(int)wpm);
                double accuracy =typingTest.getStatistic("Accuracy");
                preparedStatement.setDouble(4, accuracy);
                preparedStatement.executeUpdate();
                return;
            }
            //Updating statistics
            double previousWpm=resultSet.getDouble("wpm");
            double previousErrors=resultSet.getDouble("accuracy");
            double thisWpm=typingTest.getStatistic("WPM");
            double thisAccuracy=typingTest.getStatistic("Accuracy");
            int noOfGames=resultSet.getInt("no_of_games")+1;
            double newWpm=(previousWpm*(noOfGames-1)+thisWpm)/noOfGames;
            double newAccuracy =(previousErrors*(noOfGames-1)+thisAccuracy)/noOfGames;
            preparedStatement=connection.prepareStatement("UPDATE typing_test_statistics SET no_of_games=?, wpm=?,accuracy=? WHERE email=?");
            preparedStatement.setInt(1,noOfGames);
            preparedStatement.setInt(2,(int)newWpm);
            preparedStatement.setDouble(3, newAccuracy);
            preparedStatement.setString(4,email);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println("Invalid query");
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close resources");
            }
        }
    }
    public void getStatistics(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
            //fetching statistics
            preparedStatement=connection.prepareStatement("SELECT * FROM typing_test_statistics WHERE email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("No records found");
                return;
            }
            System.out.println("No of Games: "+resultSet.getInt(2));
            System.out.println("Average WPM: "+resultSet.getInt(3));
            System.out.printf("Average Accuracy:%.2f%%\n",resultSet.getDouble(4));
        }
        catch (SQLException e){
            System.out.println("Invalid query");
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close resources");
            }
        }
    }
    public boolean deleteStatistics(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try{
            connection=DriverManager.getConnection(dbURL, dbUser, dbPassword);
            preparedStatement=connection.prepareStatement("DELETE FROM typing_test_statistics WHERE email=?");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            return true;
        }
        catch(SQLException e){
            System.out.println("Failed to delete user");
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close resources");
            }
        }
        return false;
    }
}
