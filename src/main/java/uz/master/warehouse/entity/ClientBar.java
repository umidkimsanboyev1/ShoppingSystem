package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ClientBar extends Auditable {

    private Long itemId;
    private String clientName;
    private Double price;
    private Integer count;
    private Double payment;
    private boolean paid;
    private boolean taken;
}
