package com.example.sem3b_lab_1.artista;

import com.example.sem3b_lab_1.dto.NewArtistaDto;
import com.example.sem3b_lab_1.exceptions.ResourceConflictException;
import com.example.sem3b_lab_1.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    ArtistaRepository artistaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    ResponseEntity<Artista> createArtista(@Valid @RequestBody NewArtistaDto artista) {
        Artista newArtista = new Artista();
        modelMapper.map(artista, newArtista);

        Optional<Artista> foundArtistaByUsername = artistaRepository.findByUsername(newArtista.getUsername());

        if (foundArtistaByUsername.isEmpty())
            return ResponseEntity.ok(artistaRepository.save(newArtista));

        throw new ResourceConflictException("El artista con username " + artista.getUsername() + " ya existe");
    }

    @GetMapping("/{id}")
    ResponseEntity<Artista> getArtista(@PathVariable Long id) {
        Optional<Artista> foundArtista = artistaRepository.findById(id);
        if (foundArtista.isPresent()) {
            return ResponseEntity.ok(artistaRepository.save(foundArtista.get()));
        }
        throw new ResourceNotFoundException("El artista " + id + " no fue encontrado");
    }


}
