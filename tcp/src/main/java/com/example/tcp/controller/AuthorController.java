package com.example.tcp.controller;

import com.example.tcp.domain.dto.ResponseList;
import com.example.tcp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAuthor() {
        return ResponseEntity.ok().body(new ResponseList(authorRepository.findBy()));
    }

       /* @GetMapping("/{id}")
        public List<Author> findAnimePorId(@PathVariable UUID animeid) {
            return authorRepository.findBy();
        }

        @PostMapping("/")
        public Author createAuthor(@RequestBody Author author) {
            return authorRepository.save(author);
        }
        */

}

