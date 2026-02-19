package com.example.server_354.object;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CarPartGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;
    private String group_name;

    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "carPartGroup", cascade = CascadeType.ALL)
    private List<CarPartType> parts;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<CarPartType> getParts() {
        return parts;
    }

    public void setParts(List<CarPartType> parts) {
        this.parts = parts;
    }

}


