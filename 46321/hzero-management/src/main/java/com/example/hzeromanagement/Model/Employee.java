package com.example.hzeromanagement.Model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "join_date")
    private String joinDate;

    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "depID", referencedColumnName = "depID")
    private Department department;

    @ManyToOne(targetEntity = EmployeePosition.class)
    @JoinColumn(name = "posID", referencedColumnName = "posID")
    private EmployeePosition position;
    // Getters and setters

    public Long getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }
}
