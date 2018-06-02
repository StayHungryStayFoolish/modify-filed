package io.stayhungrystayfoolish.service.impl;

import io.stayhungrystayfoolish.service.CarService;
import io.stayhungrystayfoolish.domain.Car;
import io.stayhungrystayfoolish.repository.CarRepository;
import io.stayhungrystayfoolish.service.dto.CarDTO;
import io.stayhungrystayfoolish.service.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Car.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    /**
     * Save a car.
     *
     * @param carDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CarDTO save(CarDTO carDTO) {
        log.debug("Request to save Car : {}", carDTO);
        Car car = carMapper.toEntity(carDTO);
        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carRepository.findAll(pageable)
            .map(carMapper::toDto);
    }

    /**
     * Get one car by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CarDTO findOne(String id) {
        log.debug("Request to get Car : {}", id);
        Car car = carRepository.findById(id);
        return carMapper.toDto(car);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Car : {}", id);
        carRepository.deleteById(id);
    }
}
