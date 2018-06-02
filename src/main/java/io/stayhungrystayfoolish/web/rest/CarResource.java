package io.stayhungrystayfoolish.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.stayhungrystayfoolish.service.CarService;
import io.stayhungrystayfoolish.web.rest.errors.BadRequestAlertException;
import io.stayhungrystayfoolish.web.rest.util.HeaderUtil;
import io.stayhungrystayfoolish.web.rest.util.PaginationUtil;
import io.stayhungrystayfoolish.service.dto.CarDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Car.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "car";

    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    /**
     * POST  /cars : Create a new car.
     *
     * @param carDTO the carDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carDTO, or with status 400 (Bad Request) if the car has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cars")
    @Timed
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws URISyntaxException {
        log.debug("REST request to save Car : {}", carDTO);
        if (carDTO.getId() != null) {
            throw new BadRequestAlertException("A new car cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarDTO result = carService.save(carDTO);
        return ResponseEntity.created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cars : Updates an existing car.
     *
     * @param carDTO the carDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carDTO,
     * or with status 400 (Bad Request) if the carDTO is not valid,
     * or with status 500 (Internal Server Error) if the carDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cars")
    @Timed
    public ResponseEntity<CarDTO> updateCar(@Valid @RequestBody CarDTO carDTO) throws URISyntaxException {
        log.debug("REST request to update Car : {}", carDTO);
        if (carDTO.getId() == null) {
            return createCar(carDTO);
        }
        CarDTO result = carService.save(carDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cars : get all the cars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cars in body
     */
    @GetMapping("/cars")
    @Timed
    public ResponseEntity<List<CarDTO>> getAllCars(Pageable pageable) {
        log.debug("REST request to get a page of Cars");
        Page<CarDTO> page = carService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cars");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cars/:id : get the "id" car.
     *
     * @param id the id of the carDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cars/{id}")
    @Timed
    public ResponseEntity<CarDTO> getCar(@PathVariable String id) {
        log.debug("REST request to get Car : {}", id);
        CarDTO carDTO = carService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(carDTO));
    }

    /**
     * DELETE  /cars/:id : delete the "id" car.
     *
     * @param id the id of the carDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cars/{id}")
    @Timed
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        log.debug("REST request to delete Car : {}", id);
        carService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
