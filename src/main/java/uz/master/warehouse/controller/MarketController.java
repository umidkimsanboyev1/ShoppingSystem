package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.MarketService;

import java.util.List;

@RestController
@RequestMapping("/market/*")
@RequiredArgsConstructor
public class MarketController extends AbstractController {

    private final MarketService service;


    @PostMapping(PATH+"/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody MarketCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }


    @DeleteMapping(PATH+"/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(PATH+"/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody MarketUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }


    @GetMapping(PATH+"/list")
    public ResponseEntity<DataDto<List<MarketDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @GetMapping(PATH+"/get/{id}")
    public ResponseEntity<DataDto<MarketDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }


}
