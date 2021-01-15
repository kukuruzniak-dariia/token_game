package com.game.token.entities;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonApiResource(type = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Token token;
    private Status status;

    public Token getToken() {
        return token;
    }
}
