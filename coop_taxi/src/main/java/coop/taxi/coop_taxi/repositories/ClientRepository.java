package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
