package com.example.tcp.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProAnime {
    UUID getAnimeId();
    String getName();

    @JsonIgnoreProperties("animes")
    Set<ProAuthor> getAuthors();
}
