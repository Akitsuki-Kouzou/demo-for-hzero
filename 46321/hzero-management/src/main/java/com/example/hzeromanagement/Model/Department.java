package com.example.hzeromanagement.Model;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depID;

    @Column(name = "dep_name", nullable = false)
    private String depName;

    // Getters and setters

    public Long getDepID() {

        return depID;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }


}
