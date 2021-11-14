package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Post;
import com.example.demo.Model.SubRedit;
import com.example.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
