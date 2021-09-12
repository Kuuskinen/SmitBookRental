package com.example.smitbookrental.service;

import com.example.smitbookrental.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Modifying
    @Query("UPDATE BookEntity e SET e.status = ?1 WHERE e.id = ?2")
    void changeStatus(String bookStatus, Long bookId);
}
