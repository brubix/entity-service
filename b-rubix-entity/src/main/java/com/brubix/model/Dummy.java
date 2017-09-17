package com.brubix.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "DUMMY")
public class Dummy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DUM_ID")
    private Long id;

    @Column(name = "DATA")
    private String data;
}