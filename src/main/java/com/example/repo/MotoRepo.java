package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Moto;
@Repository
public interface MotoRepo extends JpaRepository<Moto, Long> {

}
