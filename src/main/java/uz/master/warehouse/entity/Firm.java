package uz.master.warehouse.entity;


import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Firm extends Auditable {

    private String name;

    @ManyToOne
    private Company company;
}
