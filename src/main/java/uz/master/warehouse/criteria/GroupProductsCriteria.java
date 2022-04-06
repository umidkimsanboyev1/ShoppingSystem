package uz.master.warehouse.criteria;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupProductsCriteria extends GenericCriteria {

    private Long companyId;

    private LocalDateTime date;

}
