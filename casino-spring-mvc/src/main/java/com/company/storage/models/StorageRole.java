package com.company.storage.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
@AllArgsConstructor
public class StorageRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name")
    private String name;

    public StorageRole() {

    }
}
