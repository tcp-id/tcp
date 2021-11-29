package com.example.tcp.repository;

import com.example.tcp.domain.dto.FileResult;
import com.example.tcp.domain.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("select fileid from File")
    List<String> getFileIds();



    List<FileResult> findBy();
}
