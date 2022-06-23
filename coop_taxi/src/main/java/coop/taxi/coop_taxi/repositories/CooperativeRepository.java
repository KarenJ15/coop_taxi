package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface CooperativeRepository extends JpaRepository<Cooperative, Long>{
    
}
