package uz.master.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.wareHouse.WareHouseCreateDto;
import uz.master.warehouse.dto.wareHouse.WareHouseDto;
import uz.master.warehouse.dto.wareHouse.WareHouseUpdateDto;
import uz.master.warehouse.dto.wareHouseProducts.WareHouseProductsCreateDto;
import uz.master.warehouse.entity.wareHouse.WareHouse;
import uz.master.warehouse.services.wareHouse.WareHouseService;

import java.nio.file.Path;
import java.util.List;

@RestController
public class WareHouseController extends AbstractController {
    private final WareHouseService service;

    public WareHouseController(WareHouseService service) {
        this.service = service;
    }

    @RequestMapping(value = PATH+"/warehouse/get/{id}",method = RequestMethod.GET)
    public ResponseEntity<DataDto<WareHouseDto>>get(@PathVariable Long id ){
        DataDto<WareHouseDto> wareHouseDtoDataDto = service.get(id);
        return new ResponseEntity<>(wareHouseDtoDataDto,HttpStatus.OK);
    }

    @RequestMapping(PATH+"/warehouse/list")
    public ResponseEntity<DataDto<List<WareHouseDto>>>list(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @PostMapping(PATH+"/warehouse/create")
    public ResponseEntity<DataDto<Long>>create(@RequestBody WareHouseCreateDto dto){
        return new ResponseEntity<>(service.create(dto),HttpStatus.OK);
    }

    @PutMapping(PATH+"/warehouse/update")
    public ResponseEntity<DataDto<Long>>update(@RequestBody WareHouseUpdateDto dto){
        return new ResponseEntity<>(service.update(dto),HttpStatus.OK);
    }

    @DeleteMapping(PATH+"/warehouse/delete")
    public ResponseEntity<DataDto>delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }

}
