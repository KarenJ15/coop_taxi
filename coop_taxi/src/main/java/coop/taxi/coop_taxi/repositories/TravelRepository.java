package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Client;
import coop.taxi.coop_taxi.models.Driver;
import coop.taxi.coop_taxi.models.Taxi;
import coop.taxi.coop_taxi.models.Travel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface TravelRepository extends JpaRepository<Travel, Long>{
    public List<Travel> findByClient(Client client);
    public List<Travel> findByDriver(Driver driver);
    public List<Travel> findByTaxi(Taxi taxi);
}
