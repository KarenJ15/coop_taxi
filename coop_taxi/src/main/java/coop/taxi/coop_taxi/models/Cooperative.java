package coop.taxi.coop_taxi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_COOPERATIVES" )
@Getter
@Setter 
public class Cooperative {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;  
    
    @Column(name = "UBICATION")
    private String ubication;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "cooperative")
    private List<Taxi> taxis;

    public void setTravel(Travel travel) {
        
    }


    
}