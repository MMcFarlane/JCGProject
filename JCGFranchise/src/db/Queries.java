/*
 * 
 */
package db;

import JCGExceptions.BadConnectionException;
import JCGExceptions.UnauthorizedUserException;
import bp.Employee;
import bp.Franchise;
import bp.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @authors Taylor Reighard and Miles Leavens-Russell
 */
public class Queries {
    
    private Connection con = null;
    private QStrings qs;
    
    public Queries(Connection con) {
        
        setConnection(con);
        
    }
    
    private void setConnection(Connection con) {
        this.con = con;
    }
      
/******************************************************************************
 *          All Queries to the Franchise table.                               * 
 *          Inserts, Updates authored by Taylor                               * 
 *          Selects, Deletes authored by Miles                                *
 ******************************************************************************/
    /*
    public void updateFranchise(Franchise fran) 
            throws UnauthorizedUserException, BadConnectionException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirport());
            pStmnt.setInt(8, fran.getFranchiseID());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    public void insertFranchise(Franchise fran) 
            throws UnauthorizedUserException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_fran);
            pStmnt.setString(1, fran.getAddress());
            pStmnt.setString(2, fran.getCity());
            pStmnt.setString(3, fran.getState());
            pStmnt.setInt(4, fran.getZip());
            pStmnt.setString(5, fran.getPhone());
            pStmnt.setString(6, fran.getEmail());
            pStmnt.setInt(7, fran.getAirport());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
/******************************************************************************
 *          All Queries for the Membership table                             *
 ******************************************************************************/
    //public void updateMemeberShip()
    
/******************************************************************************
 *          All Queries for the Vehicle table                                 *
 ******************************************************************************/
    /*
    public void insertVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException {
        try
        {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_vehicle);
            pStmnt.setInt(1, car.getVehicleID());
            pStmnt.setString(2, car.getVin());
            pStmnt.setString(3, car.getMake());
            pStmnt.setString(4, car.getModel());
            pStmnt.setString(5, car.getYear());
            pStmnt.setInt(6, car.getMileage());
            pStmnt.setInt(7, car.getCapacity());
            pStmnt.setString(8, car.getCondition());
            pStmnt.setString(9, car.getTablet());
            pStmnt.setDouble(10, car.getRate());
            pStmnt.setInt(11, car.getFranchiseNumber());
            pStmnt.executeUpdate();             
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    
    public void updateVehicle(Vehicle car) 
            throws UnauthorizedUserException, BadConnectionException {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.update_vehicle);
            pStmnt.setInt(1, car.getMileage());
            pStmnt.setString(2, car.getCondition());
            pStmnt.setString(3, car.getTablet());
            pStmnt.setDouble(4, car.getRate());
            pStmnt.setInt(5, car.getFranchiseNumber());
            pStmnt.setInt(6, car.getVehicleID());
            pStmnt.executeUpdate();
            
        } catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
/******************************************************************************
 *          All Queries for the Employee table                                *
 *          includes Create and Grant Queries                                 *
 ******************************************************************************/
    /* 
     
     public void insertEmployee(Employee emp)
            throws UnauthorizedUserException, BadConnectionException
     {
        String username;
        username = generateEmpUsername(emp.getFirstName(), emp.getLastName(),emp.getPhone());
        
         try {
            PreparedStatement pStmnt = con.prepareStatement(qs.insert_employee);
            pStmnt.setString(1, emp.getFirstName());
            pStmnt.setString(2, emp.getLastName());
            pStmnt.setString(3, emp.getAddress());
            pStmnt.setString(4, emp.getCity());
            pStmnt.setString(5, emp.getState());
            pStmnt.setInt(6, emp.getZip());
            pStmnt.setString(7, emp.getPhone());
            pStmnt.setString(8, emp.getEmail());
            pStmnt.setInt(9, emp.getEmpType());
            pStmnt.setInt(10, emp.getFranchiseNumber());
            pStmnt.setString(11, username);
            pStmnt.executeUpdate();
            
            int code = createUser(username, username);
            //if(type == 2 && < 100)
            //    code = grantManagerRole(username);
            //else if(type == 3 && < 100)
            //    code = grantDriverRole(username);
            
        }catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
     */
    
    private int createUser(String userID, String password) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.create_user);
            pStmnt.setString(1, userID);
            pStmnt.setString(2, password);
            pStmnt.execute();
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
     
    private String generateEmpUsername(
                String f_name, String l_name, String phone) {
        return new StringBuilder()
                   .append(f_name.charAt(0))
                   .append(l_name.charAt(0))
                   .append(phone.substring(phone.length()-4))
                   .toString();       
    }
    
    
     
    // Grants
    
    private int grantTempRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.grant_temp);
            pStmnt.setString(1, username);
            pStmnt.executeQuery();                    
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    private int revokeTempRole(String username) {
        try {
            PreparedStatement pStmnt = con.prepareStatement(qs.revoke_temp);
            pStmnt.setString(1, username);
            pStmnt.executeQuery();
            return 1;
        }
        catch(SQLException sqlE) {
            return sqlE.getErrorCode();
        }
    }
    
    
     
    
/******************************************************************************
 *          All Queries for the Maintenance table                             *
 ******************************************************************************/
    /*
    public void insertMaintenance(Maintenance fixit) 
            throws UnauthorizedUserException, BadConnectionException {
    
        try {
           PreparedStatement pStmnt = 
                   con.prepareStatement(qs.insert_maintenance);
           pStmnt.setString(1, fixit.getServiceNumber());
           pStmnt.setInt(2, fixit.getVehicleID());
           pStmnt.setString(3, fixit.getServiceType());
           pStmnt.setDouble(4, fixit.getServiceCost());
           pStmnt.executeQuery();
        }
        catch(SQLException sqlE) {
            if(sqlE.getErrorCode() == 1142)
                throw(new UnauthorizedUserException("AccessDenied"));
            else 
                throw(new BadConnectionException("BadConnection"));
        }
    }
    */
    
}