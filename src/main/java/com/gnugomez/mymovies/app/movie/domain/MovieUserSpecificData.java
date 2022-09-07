package com.gnugomez.mymovies.app.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie_user_specific_data")
public class MovieUserSpecificData {

    @EmbeddedId
    private MovieUserSpecificDataId id;

    private Boolean favorite;

    @Column(name = "personal_rating")
    @Range(min = 0, max = 5, message = "Rating must be between 0 and 5")
    private Integer rating;

    private String notes;

}
