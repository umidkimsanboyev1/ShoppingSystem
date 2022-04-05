package uz.master.warehouse.criteria;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class FirmCriteria extends GenericCriteria {

    private Long CompanyId;
}
