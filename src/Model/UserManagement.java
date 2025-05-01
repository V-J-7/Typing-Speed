package Model;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class UserManagement {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL = dotenv.get("DB_URL");
    private static final String dbUser = dotenv.get("DB_USER");
    private static final String dbPassword = dotenv.get("DB_PASSWORD");

    public boolean addUser(String email, String username, String password) {
        try {
            //Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Inserting new user statement
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details(email,name,password) values(?,?,?)");

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
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("User Already in the database");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("User could not be added");
        }
        return true;
    }

    public boolean updateUser(String email, String newUsername, String oldPassword, String newPassword) {
        if (email == null) {
            System.out.println("Email not provided");
            return false;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Get all the old details
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER_DETAILS WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            //User not registered
            if (!resultSet.next()) {
                System.out.println("Email not found in database");
                return false;
            }
            String oldUsername = resultSet.getString("name");
            String hashedOldPassword = resultSet.getString("password");
            boolean correctPassword = Hashing.checkPassword(oldPassword, hashedOldPassword);
            if (correctPassword){
                preparedStatement = connection.prepareStatement("UPDATE user_details SET password=?,name=? WHERE email=?");
                if (newPassword.equals("n")) {
                    preparedStatement.setString(1, hashedOldPassword);
                }
                else {
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
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailRegistered(String email) {
        try {
            //Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Inserting new user statement
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_details where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return false;
    }
    public boolean correctPassword(String email,String password) {
        try {
            //Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            //Inserting new user statement
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_details where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Hashing.checkPassword(password, resultSet.getString("password"));
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        catch (SQLException e) {
            System.out.println("SQL Error");
        }
        return false;
    }
}