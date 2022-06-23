package coop.taxi.coop_taxi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_TAXIS" )
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

    @ManyToOne
    @JoinColumn(name = "COOPERATIVE_ID", nullable = false )
    private Cooperative cooperative;

    @OneToMany(mappedBy = "taxi")
    private List<Travel> travels;

}
