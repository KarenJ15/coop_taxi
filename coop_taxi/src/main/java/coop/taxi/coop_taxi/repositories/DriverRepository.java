package coop.taxi.coop_taxi.repositories;

import coop.taxi.coop_taxi.models.Driver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface DriverRepository extends JpaRepository<Driver, Long>{
    public List<Driver> findByName(String criteria);
}
