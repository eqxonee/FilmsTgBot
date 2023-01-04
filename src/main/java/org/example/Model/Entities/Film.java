package org.example.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "films")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_length")
    private int timeLength;

    @Column(name = "link_film")
    private String linkFilm;

    @Column(name = "release_year")
    private int releaseFilm;

    @Column(name = "style_film_id")
    private int styleFilmToId;

    public Film(String receivedText, int i, String s, int i1) {
    }
}


