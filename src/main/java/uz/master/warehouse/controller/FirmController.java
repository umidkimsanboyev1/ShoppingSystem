package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.dto.firm.FirmCreateDto;
import uz.master.warehouse.dto.firm.FirmDto;
import uz.master.warehouse.dto.firm.FirmUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.clientBar.ClientBarService;
import uz.master.warehouse.services.organization.FirmService;

import java.util.List;

@RestController
@RequestMapping("client/*")
@RequiredArgsConstructor
public class FirmController extends AbstractController {

    private final FirmService service;

    @PostMapping(value =PATH +  "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody FirmCreateDto createDto){
        return new ResponseEntity<>(service.create(createDto), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @PostMapping(value =PATH +  "/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody FirmUpdateDto createDto){
        return new ResponseEntity<>(service.update(createDto), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get-all")
    public ResponseEntity<DataDto<List<FirmDto>>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get/{id}")
    public ResponseEntity<DataDto<FirmDto>> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

}
