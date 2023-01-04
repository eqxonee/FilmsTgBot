package org.example.Model.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "style_films")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StyleFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "style_film")
    private String styleFilm;


}