package com.mybookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor   // generates no-args constructor
@AllArgsConstructor  // generates all-args constructor
public class BookDTO {

    private int id;
    private String name;
    private int year;
    private long price;
    private String author;
}
