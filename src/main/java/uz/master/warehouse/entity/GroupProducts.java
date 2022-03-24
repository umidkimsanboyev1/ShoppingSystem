package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class GroupProducts extends Auditable {

    private Long companyId;

    private LocalDateTime date;

}
