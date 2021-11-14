package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

}
