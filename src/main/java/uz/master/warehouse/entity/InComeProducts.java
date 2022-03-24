package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class InComeProducts extends Auditable {

    private int count;

    private Long productId;

    private Double itemPrice;

    private Long groupProductsId;
}
