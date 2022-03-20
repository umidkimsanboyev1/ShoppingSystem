package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item extends Auditable {

    private String name;
    private String model;
    private String color;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;
//
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
}
