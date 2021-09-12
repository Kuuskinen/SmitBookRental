package com.example.smitbookrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Column(name = "owner")
    Integer owner;

    @Column(name = "rentable")
    Boolean reservable;

    @Column(name = "status")
    String status;
}
