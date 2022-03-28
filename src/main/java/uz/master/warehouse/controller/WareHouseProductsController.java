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
@RequestMapping("/wareHouseProducts/*")
@RequiredArgsConstructor
public class WareHouseProductsController extends AbstractController {

    private final WareHouseProductsService warehouseProductsService;

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody WareHouseProductsCreateDto dto) {
        return new ResponseEntity<>(warehouseProductsService.create(dto), HttpStatus.OK);
    }

    @GetMapping(PATH + "/get-all")
    public ResponseEntity<DataDto<List<WareHouseProductsDto>>> getAll() {
        return new ResponseEntity<>(warehouseProductsService.getAll(), HttpStatus.OK);
    }

    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<WareHouseProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(warehouseProductsService.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getByModel")
    public ResponseEntity<DataDto<List<WareHouseProductsDto>>> getByModel(String model) {
        return new ResponseEntity<>(warehouseProductsService.getByModel(model), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getByModelAndColor")
    public ResponseEntity<DataDto<WareHouseProductsDto>> getByModelAndColor(String color, String model) {
        return new ResponseEntity<>(warehouseProductsService.getByModelAndColor(model, color), HttpStatus.OK);
    }


    @DeleteMapping(PATH + "/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        warehouseProductsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody WareHouseProductsUpdateDto dto) {
        return new ResponseEntity<>(warehouseProductsService.update(dto), HttpStatus.OK);
    }

}
