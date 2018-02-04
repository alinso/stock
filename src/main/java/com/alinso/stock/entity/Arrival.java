package com.alinso.stock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Created by KHAN on 4.02.2018.
 */
@Entity
@Table(name="arrival")
public class Arrival extends BaseEntity {

    @Column(name="arrivalDate")
    @NotEmpty(message = "Bu alan boş geçilemez!")
    private String arrivalDate;

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
