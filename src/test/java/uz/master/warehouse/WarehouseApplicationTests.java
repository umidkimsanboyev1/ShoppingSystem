package uz.master.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.services.product.ProductService;

@SpringBootTest
class WarehouseApplicationTests  {

    @Autowired
    private  ProductService service;

    @Test
    void contextLoads() {

        service.create(new ProductCreateDto(10,"1001","qizil",-1L,12.0d)
        );

    }

}
