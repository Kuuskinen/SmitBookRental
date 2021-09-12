package com.example.smitbookrental.service;

import com.example.smitbookrental.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("SELECT entity FROM ReservationEntity entity WHERE entity.bookId = ?1")
    ReservationEntity getReservation(Long bookId);

    @Query("SELECT entity FROM ReservationEntity entity WHERE entity.lenderId = ?1")
    List<ReservationEntity> getReservationsByUserId(Integer userId);
}
