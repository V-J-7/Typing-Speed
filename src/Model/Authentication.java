package Model;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Authentication {
        private static final Dotenv dotenv = Dotenv.configure().load();
        private static final String dbURL=dotenv.get("DB_URL");
        private static final String dbUser=dotenv.get("DB_USER");
        private static final String dbPassword=dotenv.get("DB_PASSWORD");
    public static boolean authenticate(String email, String password) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_details WHERE email=?");
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                   if (resultSet.next()) {
                        boolean correctPassword = Hashing.checkPassword(password, resultSet.getString("password"));
                        if (correctPassword){
                            System.out.println("Logged in Successfully!");
                            return true;
                       }
                   }
                }
                catch (ClassNotFoundException e) {
                        System.out.println("Class Not Found");
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Wrong password");
                return false;
        }
}
