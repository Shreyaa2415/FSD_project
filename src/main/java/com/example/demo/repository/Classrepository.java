package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Class;
@Repository
public interface Classrepository extends JpaRepository<Class, Integer> {}
