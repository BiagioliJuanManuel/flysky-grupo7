package com.grupo2.flysky.repository;


import com.grupo2.flysky.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {

}
