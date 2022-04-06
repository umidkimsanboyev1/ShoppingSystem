package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.dto.sector.SectorCreateDto;
import uz.master.warehouse.dto.sector.SectorDto;
import uz.master.warehouse.dto.sector.SectorUpdateDto;
import uz.master.warehouse.services.wareHouse.SectorService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Panjiyev Javohir, вт 16:15. 05.04.2022
 */
@RestController
@RequestMapping("/sector/*")
@RequiredArgsConstructor
public class SectorController extends AbstractController {

    private final SectorService service;

    @PostMapping(PATH + "create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody SectorCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @GetMapping(PATH + "get-all")
    public ResponseEntity<DataDto<List<SectorDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(PATH + "get/{id}")
    public ResponseEntity<DataDto<SectorDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @DeleteMapping(PATH + "delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(PATH + "update")
    public ResponseEntity<DataDto<Long>> update(@Valid @RequestBody SectorUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }
}
