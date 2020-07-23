package com.ascending.training.repository;

import com.ascending.training.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith("Spring")
public class DepartmentDaoTest {

    @Test
    public void getDepartmentsTest() {
        DepartmentJDBCDao departmentJDBCDao = new DepartmentJDBCDao();
        List<Department> departmentList = departmentJDBCDao.getDepartments();
        assertEquals(4, departmentList.size());
    }
}
