package uz.master.warehouse.entity.organization;


import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Organization extends Auditable {

    @Column(unique = true, nullable = false)
    private String name;

    private String logoPath;

    @Column(nullable = false)
    private Long ownerId;
}
