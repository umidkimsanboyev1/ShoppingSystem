package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Market extends Auditable {

    private String name;

    private Long ownerId;

    @ManyToOne(targetEntity = Organization.class,fetch = FetchType.LAZY)
    private Organization organization;

    private String location;

    private String description;
}
