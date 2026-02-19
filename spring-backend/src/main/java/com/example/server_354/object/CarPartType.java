package com.example.server_354.object;

import jakarta.persistence.*;

@Entity
public class CarPartType{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_type_id")
    private int partTypeId;
    private String partTypeName;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private CarPartGroup carPartGroup;

    public int getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(int partTypeId) {
        this.partTypeId = partTypeId;
    }

    public String getPartTypeName() {
        return partTypeName;
    }

    public void setPartTypeName(String partTypeName) {
        this.partTypeName = partTypeName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CarPartGroup getCarPartGroup() {
        return carPartGroup;
    }

    public void setCarPartGroup(CarPartGroup carPartGroup) {
        this.carPartGroup = carPartGroup;
    }
}
