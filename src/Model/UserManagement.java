package Model;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class UserManagement {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL=dotenv.get("DB_URL");
    private static final String dbUser=dotenv.get("DB_USER");
    private static final String dbPassword=dotenv.get("DB_PASSWORD");
    public boolean addNewUser(String email,String username, String password) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into user_details(email,name,password) values(?,?,?)");
            if (Validator.validatePassword(password)) {
                String hash=Hashing.hashPassword(password);
                preparedStatement.setString(3,hash);
            }
            else return false;
            if (Validator.validateEmail(email)) {
                preparedStatement.setString(1,email);
            }
            else{
                System.out.println("Invalid email");
                return false;
            }
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            System.out.println("User Registered");
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("User Already in the database");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean updateUser(String email, String newUsername, String oldPassword, String newPassword) {
        if (email==null){
            System.out.println("Email not provided");
            return false;
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection(dbURL,dbUser,dbPassword);
            PreparedStatement preparedStatement=connection.prepareStatement("select * from user_details where email=? AND password=?");
            String hashedPassword=Hashing.hashPassword(oldPassword);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,hashedPassword);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                preparedStatement=connection.prepareStatement("update user_details set name=?,password=? where email=?");
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2,newPassword);
                preparedStatement.setString(3,email);
                preparedStatement.executeUpdate();
                System.out.println("Updated User");
                return true;
            }
            else{
                System.out.println("Could not update user");
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
