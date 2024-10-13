package com.example.demorecord.repository;

import com.example.demorecord.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("select case when count(b)> 0 then true else false end from Booking b " +
            "join b.book bo join b.user u where b.book.id = :bookId and b.user.id = :userId")
    boolean existsByBookIdAndUserId(UUID bookId, UUID userId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Booking b WHERE b.endTime < :now")
    void deleteExpiredReservations(LocalDateTime now);
}
