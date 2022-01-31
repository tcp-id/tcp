package com.example.tcp.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProFavorite {
    @JsonIgnoreProperties("favoriteby")
    Set<ProAnimeFav> getFavorite();
}
