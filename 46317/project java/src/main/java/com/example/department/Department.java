package com.example.department;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 8)
    private String deptCode;

    @Column(nullable = false, unique = true, length = 8)
    private String deptName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    @Override
    public String toString() {
        return "department{" +
                "id=" + id +
                ", deptCode='" + deptCode + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

}

