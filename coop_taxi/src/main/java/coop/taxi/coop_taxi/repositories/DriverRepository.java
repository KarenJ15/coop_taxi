package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface DriverRepository extends JpaRepository<Driver, Long>{
    
}
