package com.example.demo.department;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(length = 10, nullable = false)
    private Integer departmentCode;

    @Column(length = 35, nullable = false)
    private String departmentName;

    public Integer getId() {
        return departmentId;
    }

    public void setId(Integer id) {
        this.departmentId = departmentId;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "User{" +
                "departmentId=" + departmentId +
                ", departmentCode='" + departmentCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
