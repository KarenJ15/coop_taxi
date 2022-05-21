package coop.taxi.coop_taxi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_VIAJE" )
@Getter
@Setter

public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ORIGIN")
    private String origin; 

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "COST")
    private Float cost;
}
