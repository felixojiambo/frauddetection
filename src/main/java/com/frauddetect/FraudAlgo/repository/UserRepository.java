package com.frauddetect.FraudAlgo.repository;
import com.frauddetect.FraudAlgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
