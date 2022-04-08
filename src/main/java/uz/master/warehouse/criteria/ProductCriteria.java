package uz.master.warehouse.criteria;

import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ParameterObject
public class ProductCriteria extends GenericCriteria {

    private String model;

    private String color;

    private Long firmId;

}
