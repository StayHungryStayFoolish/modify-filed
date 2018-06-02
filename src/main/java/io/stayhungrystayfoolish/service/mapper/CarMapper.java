package io.stayhungrystayfoolish.service.mapper;

import io.stayhungrystayfoolish.domain.*;
import io.stayhungrystayfoolish.service.dto.CarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Car and its DTO CarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarMapper extends EntityMapper<CarDTO, Car> {



    default Car fromId(String id) {
        if (id == null) {
            return null;
        }
        Car car = new Car();
        car.setId(id);
        return car;
    }
}
