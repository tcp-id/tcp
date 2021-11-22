package com.example.tcp.controller;

import com.example.tcp.domain.model.Animes;
import com.example.tcp.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/animes")
    public class AnimeController {

        @Autowired
        private AnimeRepository animeRepository;

        @GetMapping("/")
        public List<Animes> findAllMovies() {
            return animeRepository.findAll();
        }

        @PostMapping("/")
        public Animes createMovie(@RequestBody Animes animes) {
            return animeRepository.save(animes);
        }
    }

