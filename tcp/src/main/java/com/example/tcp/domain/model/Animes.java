package com.example.tcp.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "animes")
public class Animes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public int year;
    public String imageurl;
}