package uz.master.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.products.OutComeProductsService;

import java.util.List;

@RestController
@RequestMapping("/OutComeProducts/")
public class OutComeProductsController extends AbstractController {

    private final OutComeProductsService service;

    public OutComeProductsController(OutComeProductsService service) {
        this.service = service;
    }


    @GetMapping(PATH + "/getByDate")
    public ResponseEntity<DataDto<List<OutComeProductsDto>>> get(String date) {
        DataDto<List<OutComeProductsDto>> byDate = service.getByDate(date);
        return new ResponseEntity<>(byDate, HttpStatus.OK);

    }
}
