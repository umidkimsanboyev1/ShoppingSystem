package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Item extends Auditable {

    private String name;
    private String model;
    private String color;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Company company;

}
