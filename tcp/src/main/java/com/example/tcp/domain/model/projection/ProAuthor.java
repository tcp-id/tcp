package com.example.tcp.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProAuthor {
    UUID getAuthorid();
    String getName();
    @JsonIgnoreProperties("authors")
    Set<ProAnime> getAnimes();
}
