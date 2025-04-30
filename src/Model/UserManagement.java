package Model;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class UserManagement {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String dbURL=dotenv.get("DB_URL");
    private static final String dbUser=dotenv.get("DB_USER");
    private static final String dbPassword=dotenv.get("DB_PASSWORD");
    public boolean addUser(String email,String username, String password) {
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
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT password FROM USER_DETAILS WHERE email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()){
                System.out.println("Email not found in database");
                return false;
            }
            String hashedOldPassword=resultSet.getString("password");
            boolean correctPassword=Hashing.checkPassword(oldPassword,hashedOldPassword);
            if (correctPassword){
                preparedStatement=connection.prepareStatement("UPDATE USER_DETAILS SET password=?,name=? WHERE email=?");
                preparedStatement.setString(1,Hashing.hashPassword(newPassword));
                preparedStatement.setString(2,newUsername);
                preparedStatement.setString(3,email);
                preparedStatement.executeUpdate();
                System.out.println("Updated details");
                return true;
            }
            System.out.println("Password does not match");
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
