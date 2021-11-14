package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Post;
import com.example.demo.Model.SubRedit;

public interface SubReditReository extends JpaRepository<SubRedit,Long> {

}
