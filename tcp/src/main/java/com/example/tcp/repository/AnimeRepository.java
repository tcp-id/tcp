package com.example.tcp.repository;

import com.example.tcp.domain.model.Anime;
import com.example.tcp.domain.model.projection.ProAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
public List<ProAnime> findBy();
}
