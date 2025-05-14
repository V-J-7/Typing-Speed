package Model;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class UserManager {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL = dotenv.get("DB_URL");
    private static final String dbUser = dotenv.get("DB_USER");
    private static final String dbPassword = dotenv.get("DB_PASSWORD");
    public UserManager() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
    }
    public boolean addUser(String email, String username, String password) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try {
            //Connection
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Inserting new user statement
            preparedStatement = connection.prepareStatement("insert into user_details(email,name,password) values(?,?,?)");

            //password follows the format
            if (Validator.validatePassword(password)) {
                String hash = Hashing.hashPassword(password);
                preparedStatement.setString(3, hash);
            } else return false;

            //email follows the format
            if (Validator.validateEmail(email)) {
                preparedStatement.setString(1, email);
            } else {
                System.out.println("Invalid email");
                return false;
            }

            //set username
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            System.out.println("User Registered");
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("User Already in the database");
        }
        catch (SQLException e) {
            System.out.println("User could not be added");
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close resources");
            }
        }
        return true;
    }

    public boolean updateUser(String email, String newUsername, String newPassword) {
        if (email == null) {
            System.out.println("Email not provided");
            return false;
        }
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Get all the old details
            preparedStatement = connection.prepareStatement("SELECT * FROM USER_DETAILS WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            //User not registered
            if (!resultSet.next()) {
                System.out.println("Email not found in database");
                return false;
            }
            String oldUsername = resultSet.getString("name");
            String hashedOldPassword = resultSet.getString("password");
                //Update new details
                preparedStatement = connection.prepareStatement("UPDATE user_details SET password=?,name=? WHERE email=?");
                if (newPassword.equals("n")) {
                    preparedStatement.setString(1, hashedOldPassword);
                } else {
                    if (!Validator.validatePassword(newPassword)) {
                        System.out.println("Invalid password format");
                        return false;
                    }
                    preparedStatement.setString(1, Hashing.hashPassword(newPassword));
                }
                if (newUsername.equals("n")) {
                    preparedStatement.setString(2, oldUsername);
                } else {
                    preparedStatement.setString(2, newUsername);
                }
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                System.out.println("Updated details");
                return true;
        }
        catch (SQLException e) {
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
        return false;
    }
    public boolean deleteUser(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try{
            connection=DriverManager.getConnection(dbURL, dbUser, dbPassword);
            preparedStatement=connection.prepareStatement("DELETE FROM user_details WHERE email=?");
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