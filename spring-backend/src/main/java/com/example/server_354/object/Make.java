package com.example.server_354.object;

import jakarta.persistence.*;
import org.springframework.boot.Banner;

import java.util.List;

@Entity
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int make_id;
    private String make;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    private List<Model> models;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMake_id() {
        return make_id;
    }

    public void setMake_id(int make_id) {
        this.make_id = make_id;
    }

}
