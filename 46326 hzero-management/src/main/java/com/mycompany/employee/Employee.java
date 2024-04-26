package com.mycompany.employee;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 8)
    private String empCode;

    @Column(nullable = false, unique = true, length = 8)
    private String empName;

    @Column(nullable = false, length = 10)
    private String joiningDate;

    @Column(nullable = false, length = 25)
    private String departmentName;

    @Column(nullable = false, length = 25)
    private String positionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empCode='" + empCode + '\'' +
                ", empName='" + empName + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }

}
