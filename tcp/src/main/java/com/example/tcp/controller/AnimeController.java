package com.example.tcp.controller;

import com.example.tcp.domain.dto.ResponseList;
import com.example.tcp.domain.model.Anime;
import com.example.tcp.domain.model.Author;
import com.example.tcp.repository.AnimeRepository;
import com.example.tcp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnime() {
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy()));
    }
    @GetMapping("/{id}")
    public List<?> findAnimePorId(@PathVariable UUID animeid) {
        return animeRepository.findAll();
    }

    @PostMapping("/")
    public Anime createMovie(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }


}

