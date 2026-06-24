/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapp;
import java.util.*;
import java.sql.*;
/**
 *
 * @author olenk
 */
public class DatabaseConn {
    public static final String URL = Config.URL;
    public static final String USER = Config.USER;
    public static final String PASSWORD = Config.PASSWORD;
    
    public void add(String name, String number)
    {
        String sql = "INSERT INTO Contacts VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, number);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
                {
                e.printStackTrace();
                }
    }
    
    public void delete(String name)
    {
        String sql = "DELETE FROM Contacts WHERE Name_ = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
                {
                    e.printStackTrace();
                }
    }
    
    public void update(String name, String newName)
    {
        String sql = "UPDATE Contacts SET Name_ = ? WHERE Name_ = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, newName);
            pstmt.setString(2, name);
            pstmt.executeUpdate();       
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public List<Contact> getContact()
    {
        List<Contact> contact = new ArrayList<>();
        String sql = "SELECT *FROM Contacts";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                contact.add(new Contact(rs.getString("Name_"), rs.getString("Number")));
            }
        }
        catch (SQLException e)
                {
                    e.printStackTrace();
                }
        return contact;
    }
    
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[A-Za-zА-Яа-я][A-Za-zА-Яа-я\\s\\d\\-]*");
    }
    
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return phone.matches("\\d{11}");
    }
    
    public boolean contactExists(String name) {
        String sql = "SELECT COUNT(*) FROM Contacts WHERE Name_ = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean phoneExists(String phone) {
        String sql = "SELECT COUNT(*) FROM Contacts WHERE Phone = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
