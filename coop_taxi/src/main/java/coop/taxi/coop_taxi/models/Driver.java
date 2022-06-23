package coop.taxi.coop_taxi.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_DRIVERS" )
@Getter
@Setter

public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name; 

    @Column(name = "LICENCE_TYPE")
    private String licenceType;

    @Column(name = "DOCUMENT")
    private String document;

    @OneToMany(mappedBy = "driver")
    private List<Travel> travels;
}
