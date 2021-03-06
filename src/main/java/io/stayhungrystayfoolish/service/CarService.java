package io.stayhungrystayfoolish.service;

import io.stayhungrystayfoolish.service.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Car.
 */
public interface CarService {

    /**
     * Save a car.
     *
     * @param carDTO the entity to save
     * @return the persisted entity
     */
    CarDTO save(CarDTO carDTO);

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CarDTO> findAll(Pageable pageable);

    /**
     * Get the "id" car.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CarDTO findOne(String id);

    /**
     * Delete the "id" car.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
