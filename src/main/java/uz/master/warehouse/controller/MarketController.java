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
public class MarketController {

    private final MarketService service;


    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody MarketCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @PutMapping("/update")
    public void update(@RequestBody MarketUpdateDto dto) {
        service.update(dto);
    }


    @GetMapping("/list")
    public ResponseEntity<DataDto<List<MarketDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<MarketDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }


}
