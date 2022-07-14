package coop.taxi.coop_taxi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import java.util.Calendar;
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
    
    @Column(name = "CREATED_DATE")    
    private Calendar createdDate;
    @Column(name = "CREATED_BY")    
    private String createdBy;  

    @Column(name = "UPDATED_DATE")    
    private Calendar updatedDate;
    @Column(name = "UPDATED_BY")    
    private String updatedBy;  

    @PrePersist
    public void prePersist(){
        createdDate = Calendar.getInstance();
        createdBy = "user1";
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate = Calendar.getInstance();
        updatedBy = "user2";
    }
    
    @ManyToOne
    @JoinColumn(name = "COOPERATIVE_ID", nullable = false )
    private Cooperative cooperative;

    @OneToMany(mappedBy = "taxi")
    private List<Travel> travels;

    public void setTravel(Travel travel) {
    }

}
