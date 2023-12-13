package com.grupo2.flysky.repository;

import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Ticket;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {

}
