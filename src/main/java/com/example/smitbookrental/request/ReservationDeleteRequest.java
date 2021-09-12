package com.example.smitbookrental.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDeleteRequest {
    Long reservationId;
    Long bookId;
}
