package com.example.tcp.repository;

import com.example.tcp.domain.model.Animes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Animes, UUID> {

}
