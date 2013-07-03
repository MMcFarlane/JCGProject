/*
 * Class JCGDatabase will be the connection class to the MySQL database
 * and will also allow the user to change their password upon their 
 * first login.
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.InvalidUserException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author taylor
 */
public class JCGDatabase {
  static final String DATABASE_URL = "jdbc:mysql://localhost/tester";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    private PreparedStatement loginStatus = null;
    private PreparedStatement empType = null;
    private PreparedStatement update_password = null;
    private PreparedStatement update_status = null;
    private int code;
    
    // Constructor test for connection and user/password
    public JCGDatabase(String username, String password) 
            throws InvalidUserException, BadConnectionException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, username, password);            
             
            loginStatus = connection.prepareStatement(
                "SELECT FirstLog from Employee where Username = ?");
        
            empType = connection.prepareStatement(
                "SELECT EmpType from Employee where Username = ?");
        
            update_password = connection.prepareStatement(
                "SET PASSWORD FOR ?@'localhost' = PASSWORD(?)");
    
            update_status = connection.prepareStatement(
                "UPDATE Employee SET FIRSTLOG = 1 WHERE Username = ?");
            }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1044 || sqlE.getErrorCode() == 1045)
                throw(new InvalidUserException("Invalid Username or Password"));
            else
                throw(new BadConnectionException("Connection Failed"));
        }
    }
    
/******************************************************************************
 * Checks for new user, then gets the employee type
 * @param username
 * @param password
 * @return integer for employee type or 55 for new password screen 
 ******************************************************************************/
    public int login(String username, String password) {
        code = getLoginStatus(username);
        if(code == 0)
            return 55;
        code = getEmpType(username);
            return code;
    }
    
    
/******************************************************************************
 * Used by login2 to get check for new user
 * @param username
 * @returns returns 1 for success or SQL error code number
 ******************************************************************************/ 
    int getLoginStatus(String username) {
        try {
            loginStatus.setString(1, username);
            resultSet = loginStatus.executeQuery();
            while(resultSet.next())
            {
                code = resultSet.getInt("FirstLog");
            }
            return code;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    
/******************************************************************************
 * Used by login2 to get the employee type
 * @param username
 * @returns integer empType or SQL error code number 
 ******************************************************************************/
    private int getEmpType(String username) {
        try {
            empType.setString(1, username);
            resultSet = empType.executeQuery();
            while(resultSet.next())
            {
                code = resultSet.getInt("EmpType");
            }
            return code;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    
 /*****************************************************************************
  * Used to update the password for a given user in the system.
  * @param username
  * @param password
  * @returns 1 for success or SQL error code. 
  *****************************************************************************/    
    int updatePassword(String username, String password) {
        try {
            update_password.setString(1, username);
            update_password.setString(2, password);
            update_password.execute();
            code = updateLogStatus(username);
            return code;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
    
    
 /*****************************************************************************
  * Used to by updatePassword to set the user to non-new user.
  * @param username
  * @returns 1 for success or SQL error code. 
  *****************************************************************************/
    private int updateLogStatus(String username) {
        try {
            update_status.setString(1, username);
            update_status.execute();
            return 1;
        }
        catch(SQLException sqlE)
        {
            return sqlE.getErrorCode();
        }
    }
    
    
/******************************************************************************
 * Closes the connection to the database for current user.
 ******************************************************************************/
    public void logOff() {
        try {
                connection.close();
            }catch(Exception exception) {
            }
    }    
}//end JCGDatabase