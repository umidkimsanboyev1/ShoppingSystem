package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.Firm;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.enums.Location;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Product extends Auditable {

//    private Double price;

    private Integer count;

    private Integer item_count;

    private String model;

    private String color;

    @OneToOne
    private Firm firm;

    private Double default_price;

}
