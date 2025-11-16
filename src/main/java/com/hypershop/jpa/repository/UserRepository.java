package com.hypershop.jpa.repository;


 import com.hypershop.jpa.model.User;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMobile(String phone);
    Optional<User> findByEmail(String email);


}