package com.example.smitbookrental.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    Long id;

    String name;

    String author;

    Boolean reservable;

    //Integer owner;
}
