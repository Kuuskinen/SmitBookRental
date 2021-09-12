package com.example.smitbookrental.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {

    Long bookId;

    Integer lenderId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate endDate;
}
