package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Company extends Auditable {

    @Column(nullable = false,unique = true)
    private String name;

    private Long registrationNumber;
}
