package uz.master.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.wareHouse.WareHouseDto;
import uz.master.warehouse.entity.wareHouse.WareHouse;
import uz.master.warehouse.services.wareHouse.WareHouseService;

@RestController
public class WareHouseController extends AbstractController {
    private final WareHouseService service;

    public WareHouseController(WareHouseService service) {
        this.service = service;
    }

    @RequestMapping(PATH+"/warehouse/get/{id}")
    public ResponseEntity<DataDto<WareHouseDto>>get(@PathVariable Long id ){
        DataDto<WareHouseDto> wareHouseDtoDataDto = service.get(id);
        return new ResponseEntity<>(wareHouseDtoDataDto,HttpStatus.OK);
    }
}
