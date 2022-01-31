package com.example.tcp.repository;

import com.example.tcp.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavRepository extends JpaRepository<Favorite, UUID> {
}
