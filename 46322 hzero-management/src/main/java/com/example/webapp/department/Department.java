package com.example.webapp.department;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(nullable = false, unique = true, length = 45)
    private String departmentCode;

    @Column(nullable = false, unique = true, length = 45)
    private String departmentName;

    @Override
    public String toString() {
        return "Department{" +
                "departmentCode=" + getDepartmentCode() +
                ", departmentName='" + getDepartmentName() + '\'' +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
