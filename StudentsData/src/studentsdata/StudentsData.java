/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsdata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maryam
 */
public class StudentsData {
int regno=1;
String name="Maryam";
String address="Islamabad";
int semester=6;
    public void statement(Connection myConn) throws SQLException{
        Statement stmt1=(Statement) myConn.createStatement();
      String sql = "TRUNCATE students";
    // Execute deletion
    stmt1.executeUpdate(sql);
    myConn.setAutoCommit(false);
     long startTime = System.currentTimeMillis();
     
      for(int i = 1; i <= 5000; i++) {
         int rs=stmt1.executeUpdate("INSERT INTO Students values('"+name+"','"+regno+"','"+semester+"','"+address+"')");
         myConn.commit();
         regno++;
         name="Maryam"+regno;
      }
         
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by Statement is:"+elapsedTime);
    }
    public void preparedStatement(Connection myConn) throws SQLException{
      Statement stmt1=(Statement) myConn.createStatement();
        String sql = "TRUNCATE students";
    // Execute deletion
    myConn.setAutoCommit(false);
    stmt1.executeUpdate(sql);
     long startTime = System.currentTimeMillis();
  
      for(int i = 1; i <= 5000; i++) {
       //  int rs=stmt1.executeUpdate("INSERT INTO Students values('"+name+"','"+regno+"','"+semester+"','"+address+"')");
       String     insertSQL = "INSERT INTO Students VALUES (?, ?,?,?)";
PreparedStatement preparedStatement = myConn.prepareStatement(insertSQL);
preparedStatement.setString(1, name);
preparedStatement.setInt(2, regno);
preparedStatement.setInt(3, semester);
preparedStatement.setString(4, address);
preparedStatement.executeUpdate();
        myConn.commit(); 
         regno++;
         name="Maryam"+regno;
         
      }
         
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by PreparedStatement is:"+elapsedTime);
    }
    
    //Batch
    
     public void batchUpdate(Connection myConn) throws SQLException{
      Statement stmt1=(Statement) myConn.createStatement();
        String sql = "TRUNCATE students";
   
    stmt1.executeUpdate(sql);
     long startTime = System.currentTimeMillis();
     PreparedStatement preparedStatement = null;
      myConn.setAutoCommit(false);
       String insertSQL = "INSERT INTO Students VALUES (?, ?,?,?)";
        preparedStatement = myConn.prepareStatement(insertSQL);
      for(int i = 1; i <= 5000; i++) {
     regno=i;
        name="Maryam"+regno;
     

preparedStatement.setString(1, name);
preparedStatement.setInt(2, regno);
preparedStatement.setInt(3, semester);
preparedStatement.setString(4, address);

preparedStatement.addBatch();
         
         
          
      }
      int[] inserted = preparedStatement.executeBatch();
       myConn.commit();
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by Batch Update is:"+elapsedTime);
    }
     
      
     
     
    //Auto commit true
      public void statement2(Connection myConn) throws SQLException{
        Statement stmt1=(Statement) myConn.createStatement();
      String sql = "TRUNCATE students";

    stmt1.executeUpdate(sql);
    myConn.setAutoCommit(true);
     long startTime = System.currentTimeMillis();
     
      for(int i = 1; i <= 5000; i++) {
         int rs=stmt1.executeUpdate("INSERT INTO Students values('"+name+"','"+regno+"','"+semester+"','"+address+"')");
         regno++;
         name="Maryam"+regno;
      }
         
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by Statement is:"+elapsedTime);
    }
    public void preparedStatement2(Connection myConn) throws SQLException{
      Statement stmt1=(Statement) myConn.createStatement();
        String sql = "TRUNCATE students";

    myConn.setAutoCommit(true);
    stmt1.executeUpdate(sql);
     long startTime = System.currentTimeMillis();
  
      for(int i = 1; i <= 5000; i++) {
       //  int rs=stmt1.executeUpdate("INSERT INTO Students values('"+name+"','"+regno+"','"+semester+"','"+address+"')");
       String     insertSQL = "INSERT INTO Students VALUES (?, ?,?,?)";
PreparedStatement preparedStatement = myConn.prepareStatement(insertSQL);
preparedStatement.setString(1, name);
preparedStatement.setInt(2, regno);
preparedStatement.setInt(3, semester);
preparedStatement.setString(4, address);
preparedStatement.executeUpdate();
         
         regno++;
         name="Maryam"+regno;
         
      }
         
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by PreparedStatement is:"+elapsedTime);
    }
    
    //Batch
    
     public void batchUpdate2(Connection myConn) throws SQLException{
      Statement stmt1=(Statement) myConn.createStatement();
        String sql = "TRUNCATE students";
   
    stmt1.executeUpdate(sql);
     long startTime = System.currentTimeMillis();
     PreparedStatement preparedStatement = null;
      myConn.setAutoCommit(true);
     String insertSQL = "INSERT INTO Students VALUES (?, ?,?,?)";
        preparedStatement = myConn.prepareStatement(insertSQL);
      for(int i = 1; i <= 5000; i++) {
     regno=i;
        name="Maryam"+regno;
       //  int rs=stmt1.executeUpdate("INSERT INTO Students values('"+name+"','"+regno+"','"+semester+"','"+address+"')");
      

preparedStatement.setString(1, name);
preparedStatement.setInt(2, regno);
preparedStatement.setInt(3, semester);
preparedStatement.setString(4, address);

preparedStatement.addBatch();
         
         
          
      }
      int[] inserted = preparedStatement.executeBatch();
       
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("Time taken by Batch Update is:"+elapsedTime);
      

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {
        try {
            // TODO code application logic here
            StudentsData obj=new StudentsData();
            //loading the jdbc driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBase","root","");
        System.out.println("With auto commit false");
        obj.statement(myConn);
        obj.preparedStatement(myConn);
        obj.batchUpdate(myConn);
         
         System.out.println("With auto commit true");
        obj.statement2(myConn);
        obj.preparedStatement2(myConn);
        obj.batchUpdate2(myConn);
     
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentsData.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
    }
    
}
