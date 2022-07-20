package coop.taxi.coop_taxi.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

    @OneToMany(mappedBy = "driver")
    private List<Travel> travels;

    public void setClient(Client client) {
    }

}
