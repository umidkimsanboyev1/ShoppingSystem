package uz.master.warehouse.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.products.InComeProductsService;
import uz.master.warehouse.services.products.WareHouseProductsService;

import java.util.List;

@RestController
@RequestMapping("/groupproducts/*")
@RequiredArgsConstructor
public class WareHouseProductsController extends AbstractController {

    private final WareHouseProductsService inComeProductsService;

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody WareHouseProductsCreateDto dto) {
        return new ResponseEntity<>( inComeProductsService.create( dto ), HttpStatus.OK );
    }

    @GetMapping(PATH + "/get-all")
    public ResponseEntity<DataDto<List<WareHouseProductsDto>>> getAll() {
        return new ResponseEntity<>( inComeProductsService.getAll(), HttpStatus.OK );
    }

    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<WareHouseProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>( inComeProductsService.get( id ), HttpStatus.OK );

    }

    @DeleteMapping(PATH + "/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        inComeProductsService.delete( id );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );

    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody WareHouseProductsUpdateDto dto) {
        return new ResponseEntity<>( inComeProductsService.update( dto ), HttpStatus.OK );
    }

}
