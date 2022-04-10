package com.pages.ufazerp.domain;

import javax.persistence.*;

@Entity
@Table(name = "corpora")
public class Corpus extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String address;
}
