package com.example.smitbookrental.service;

import com.example.smitbookrental.entity.ReservationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookService bookService;

    //STATUS IS IMPORTANT
    public void addReservation(ReservationEntity reservationEntity) {
        reservationRepository.save(reservationEntity);
        //CHECK IF RESERVATION REALLY SUCCEEDED
        ReservationEntity newEntity = getBookReservation(reservationEntity.getBookId());
        if (newEntity != null) {
            bookService.changeStatus("Reserved", newEntity.getBookId());
        }
    }

    public ReservationEntity getBookReservation(Long bookId) {
        return reservationRepository.getReservation(bookId);
    }

    //STATUS IS IMPORTANT
    public void deleteReservation(Long reservationId, Long bookId) {
        reservationRepository.deleteById(reservationId);
        if (reservationRepository.getReservation(bookId) == null) {
            bookService.changeStatus("Available", bookId);
        }
    }

    public List<ReservationEntity> getAllReservationsByUser(Integer userId) {
        List<ReservationEntity> reservationEntities = reservationRepository.getReservationsByUserId(userId);

        /*
        List<Long> bookIds = new ArrayList<>();
        for (int i = 0; i < reservationEntities.size(); i++) {
            ReservationEntity entity = reservationEntities.get(i);
            bookIds.add(entity.getBookId());
        }
        return bookIds;
        */
        return reservationEntities;

    }

    public void deleteReservationByBookId(long id) {
        ReservationEntity entity = reservationRepository.getReservation(id);
        reservationRepository.delete(entity);
        if (reservationRepository.getReservation(id) == null) {
            bookService.changeStatus("Available", id);
        }
    }
}
