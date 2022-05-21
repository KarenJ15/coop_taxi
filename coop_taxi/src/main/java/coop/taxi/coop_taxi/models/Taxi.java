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
@Table(name="TBL_TAXI" )
@Getter
@Setter 
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LICENSE_PLATE")
    private String licensePlate; 

    @Column(name = "MODEL")
    private String model; 

    @Column(name = "TRADEMARK")
    private String tradeMark; 

    @Column(name = "REGISTRATION")
    private String registration;
}
