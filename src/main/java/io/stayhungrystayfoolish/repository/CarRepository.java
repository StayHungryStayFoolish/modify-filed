package io.stayhungrystayfoolish.repository;

import io.stayhungrystayfoolish.domain.Car;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Car entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findById(String id);

    void deleteById(String id);
}
