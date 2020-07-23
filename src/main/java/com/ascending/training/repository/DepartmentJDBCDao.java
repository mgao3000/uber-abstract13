package com.ascending.training.repository;

import com.ascending.training.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBCDao {
    private Logger logger = LoggerFactory.getLogger(DepartmentJDBCDao.class);

    //STEP 1: Database information
//    static final String DB_URL = "jdbc:postgresql://localhost:5431/training_dev";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/debug12";
    static final String USER = "admin";
    static final String PASS = "password";

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
//            System.out.println("Creating statement...");
            logger.debug("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM department";
            rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");

                //Fill the object
                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setDescription(description);
                department.setLocation(location);
                departments.add(department);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }

        return departments;
    }

    public static void main(String [] args) {
        DepartmentJDBCDao departmentJDBCDao = new DepartmentJDBCDao();
        System.out.println(departmentJDBCDao.getDepartments());
    }
}

