package uz.master.warehouse.entity.payment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment extends Auditable {

    private Long organizationId;
    private Long companyId;
    private Long sum;

    @CreatedDate
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", updatable = false )
    private LocalDateTime dateTime;

}
