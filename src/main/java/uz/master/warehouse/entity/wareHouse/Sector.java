package uz.master.warehouse.entity.wareHouse;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Sector extends Auditable {

    private String name;

    private String color;

    private Long wareHouseId;
}
