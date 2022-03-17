package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class ClientBar extends Auditable {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_bar_product",
            joinColumns = @JoinColumn(name = "clientbar_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    private String clientName;
    private Double overAllPrice;
    private Integer productCount;
    private Double overAllPayment;
    private boolean paid;
    private boolean taken;
}
