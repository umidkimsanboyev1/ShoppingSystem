package uz.master.warehouse.entity.products;


import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class OutComeProducts extends Auditable {

    private int count;

    private Long productId;

    private Long clientBarId;

    private Double productPrice;

    private boolean taken;
}
