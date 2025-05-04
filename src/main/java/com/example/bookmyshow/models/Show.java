package com.example.bookmyshow.models;

import com.example.bookmyshow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="shows")
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;

    private Long startTime;
    private Long endTime;

    @ManyToOne
    private Screen screen;

    @ElementCollection(targetClass = Feature.class)
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;
}
