package com.example.tcp.repository;

import com.example.tcp.domain.dto.FileResult;
import com.example.tcp.domain.model.File;
import com.example.tcp.domain.model.projection.ProAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<File, UUID> {
    List<ProAuthor> findBy();
}