package com.grupo2.flysky.repository;

import com.grupo2.flysky.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByPaymentDate(LocalDate date);
}
