package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface TravelRepository extends JpaRepository<Travel, Long>{
    
}
