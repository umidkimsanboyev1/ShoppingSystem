package uz.master.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsDto;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.products.OutComeProductsService;

import java.nio.file.Path;
import java.util.List;

@RestController
public class OutComeProductsController extends AbstractController {

    private final OutComeProductsService service;

    public OutComeProductsController(OutComeProductsService service) {
        this.service = service;
    }


    @GetMapping(PATH + "/OutComeProducts/getByDate")
    public ResponseEntity<DataDto<List<OutComeProductsDto>>> get(String date) {
        DataDto<List<OutComeProductsDto>> byDate = service.getByDate(date);
        return new ResponseEntity<>(byDate, HttpStatus.OK);
    }

    @GetMapping(PATH + "/OutComeProducts/get/{id}")
    public ResponseEntity<DataDto<OutComeProductsDto>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/OutComeProducts/listById/{id}")
    public ResponseEntity<DataDto<List<OutComeProductsDto>>> getAllId(@PathVariable Long id) {
        return new ResponseEntity<>(service.getAll(id), HttpStatus.OK);
    }

    @PutMapping(PATH + "/OutComeProducts/update")
    public ResponseEntity<DataDto<Long>>update(@RequestBody OutComeProductsUpdateDto dto){
        return new ResponseEntity<>(service.update(dto),HttpStatus.OK);
    }


    @PostMapping(PATH+"/OutComeProducts/create")
    public ResponseEntity<DataDto<Long>>create(@RequestBody OutComeProductsCreateDto dto){
        return new ResponseEntity<>(service.create(dto),HttpStatus.OK);
    }

    @DeleteMapping(PATH+"/OutComeProducts/delete/{id}")
    public ResponseEntity<DataDto>delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }


}
