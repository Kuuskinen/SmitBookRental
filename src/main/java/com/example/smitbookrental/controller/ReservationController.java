package com.example.smitbookrental.controller;

import com.example.smitbookrental.entity.ReservationEntity;
import com.example.smitbookrental.request.ReservationDeleteRequest;
import com.example.smitbookrental.request.ReservationRequest;
import com.example.smitbookrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/new_reservation")
    public void addNewReservation(@RequestBody ReservationRequest request) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setBookId(request.getBookId());
        reservationEntity.setLenderId(request.getLenderId());
        reservationEntity.setStartDate(LocalDate.now());
        reservationEntity.setEndDate(request.getEndDate());
        reservationService.addReservation(reservationEntity);
    }

    @GetMapping("reservations_of_book/{id}")
    public ReservationEntity getReservation(@PathVariable String id) {
        return reservationService.getBookReservation(Long.valueOf(id));
    }

    @DeleteMapping("delete_reservation")
    public void deleteReservation(@RequestBody ReservationDeleteRequest request) {
        reservationService.deleteReservation(request.getReservationId(), request.getBookId());
    }
}
