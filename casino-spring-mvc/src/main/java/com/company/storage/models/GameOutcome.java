package com.company.storage.models;

import javax.persistence.*;

@Entity
public class GameOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view")
    private String view;
}
