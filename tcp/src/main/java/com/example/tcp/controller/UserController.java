package com.example.tcp.controller;

import com.example.tcp.domain.dto.RequestFav;
import com.example.tcp.domain.dto.ResponseList;
import com.example.tcp.domain.dto.ResponseMessage;
import com.example.tcp.domain.dto.UserRegisterRequest;
import com.example.tcp.domain.model.Favorite;
import com.example.tcp.domain.model.User;
import com.example.tcp.domain.model.projection.ProFavorite;
import com.example.tcp.domain.model.projection.ProUser;
import com.example.tcp.repository.FavRepository;
import com.example.tcp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private FavRepository favRepository;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByUsername(userRegisterRequest.username) == null) {
            User user = new User();
            user.username = userRegisterRequest.username;
            user.password = passwordEncoder.encode(userRegisterRequest.password);
            user.enabled = true;
            userRepository.save(user);
            return "OK";   // TODO
        }
        return "ERROR";    // TODO
    }

    @GetMapping("/")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok().body(new ResponseList(userRepository.findBy(ProUser.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getUser(@PathVariable UUID id){
        return ResponseEntity.ok().body(new ResponseList(userRepository.findBy(ProUser.class)));
    }

    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody RequestFav requestFavorite, Authentication authentication){
        if (authentication != null) {
            User authentificatedUser = userRepository.findByUsername(authentication.getName());

            if(authentificatedUser != null){
                Favorite favorite = new Favorite();
                favorite.animeid =requestFavorite.animeid;
                favorite.userid = authentificatedUser.userid;
                favRepository.save(favorite);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No autorizado"));
    }


    @GetMapping("/favorites")
    public  ResponseEntity<?> getFavorites(Authentication authentication){
        if (authentication != null){
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null){
                return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), ProFavorite.class));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No authorized"));
    }
    @DeleteMapping("/favorites")
    public ResponseEntity<?> delFavorite(@RequestBody RequestFav requestFavorite, Authentication authentication) {
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                Favorite favorite = new Favorite();
                favorite.animeid = requestFavorite.animeid;
                favorite.userid = authenticatedUser.userid;
                favRepository.delete(favorite);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No autorizado"));
    }
}
