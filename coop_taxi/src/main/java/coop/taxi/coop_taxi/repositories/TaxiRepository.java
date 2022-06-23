package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface TaxiRepository extends JpaRepository<Taxi, Long>{
    
}
