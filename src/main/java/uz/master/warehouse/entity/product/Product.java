package uz.master.warehouse.entity.product;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.entity.organization.Firm;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Product extends Auditable {


    private Integer item_count;

    private String model;

    private String color;

    private Long firmId;

    private Double default_price;

}
