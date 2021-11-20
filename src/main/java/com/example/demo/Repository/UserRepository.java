package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Post;
import com.example.demo.Model.SubRedit;
import com.example.demo.Model.User;
import com.example.demo.Model.VerificationToken;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);

}
