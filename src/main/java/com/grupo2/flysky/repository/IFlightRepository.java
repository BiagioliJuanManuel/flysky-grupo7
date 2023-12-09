package com.grupo2.flysky.repository;

import com.grupo2.flysky.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight,Long> {

}
