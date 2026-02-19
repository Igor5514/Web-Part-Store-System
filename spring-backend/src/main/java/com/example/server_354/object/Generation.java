package com.example.server_354.object;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int generation_id;
    public String generation;

    public Generation(int generation_id, String generation) {
        this.generation_id = generation_id;
        this.generation = generation;
    }

    public Generation() {
    }

    public int getGeneration_id() {
        return generation_id;
    }

    public void setGeneration_id(int generation_id) {
        this.generation_id = generation_id;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "Generation{" +
                "generation_id=" + generation_id +
                ", generation='" + generation + '\'' +
                '}';
    }
}

