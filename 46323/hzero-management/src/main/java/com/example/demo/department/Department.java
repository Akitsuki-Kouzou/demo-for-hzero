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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer department_id) {
        this.departmentId = department_id;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer department_code) {
        this.departmentCode = department_code;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String department_name) {
        this.departmentName = department_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentCode='" + departmentCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
