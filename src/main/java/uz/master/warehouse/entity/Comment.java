package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Comment extends Auditable {
    private Long clientBarId;
    private Long authorId;
    private String text;
}
