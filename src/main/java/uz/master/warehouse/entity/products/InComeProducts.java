package uz.master.warehouse.entity.products;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
public class InComeProducts extends Auditable {

    private int count;

    private Long productId;

    private Double itemPrice;

    private Long groupProductsId;
}
