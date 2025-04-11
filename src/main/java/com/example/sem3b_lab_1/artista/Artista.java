package com.example.sem3b_lab_1.artista;

import com.example.sem3b_lab_1.cancion.Cancion;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @ManyToMany(mappedBy = "artistas")
    private List<Cancion> canciones;
}
