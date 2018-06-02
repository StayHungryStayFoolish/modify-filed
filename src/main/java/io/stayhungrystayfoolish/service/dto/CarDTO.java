package io.stayhungrystayfoolish.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Car entity.
 */
public class CarDTO implements Serializable {

    private String id;

    private BigDecimal price;

    @NotNull
    private Long createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarDTO carDTO = (CarDTO) o;
        if(carDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", createdAt=" + getCreatedAt() +
            "}";
    }
}
