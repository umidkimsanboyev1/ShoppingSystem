package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.enums.Location;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Product extends Auditable {

    private Long  itemId;
    private Double price;
    private Integer count;
    private Integer firstCount;
    @Enumerated(value = EnumType.STRING)
    private Location location;
}
