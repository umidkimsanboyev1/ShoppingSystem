package uz.master.warehouse.entity.product;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.entity.organization.Firm;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@Indexed
public class Product extends Auditable {

    private Integer item_count;

    @FullTextField
    @Unique
    private String model;

    private String color;

    private Long firmId;

    private Double default_price;

    private Long sectorId;

    private Long orgId;

}
