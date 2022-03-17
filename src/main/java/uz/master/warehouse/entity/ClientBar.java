package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ClientBar extends Auditable {
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;
    private String clientName;
    private Double overAllPrice;
    private Integer productCount;
    private boolean paid;
    private boolean taken;
}
