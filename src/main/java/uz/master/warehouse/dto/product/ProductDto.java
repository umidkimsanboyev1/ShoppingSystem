package uz.master.warehouse.dto.product;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import java.security.GeneralSecurityException;

public class ProductDto extends GenericDto {

    private Integer item_count;

    private String model;

    private String color;

    private Long firmId;

    private Double default_price;

}
