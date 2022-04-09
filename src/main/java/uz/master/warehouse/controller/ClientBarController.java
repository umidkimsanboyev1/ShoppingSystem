package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.clientBar.ClientBarCreateDto;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.dto.clientBar.ClientBarUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.clientBar.ClientBarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client/*")
@RequiredArgsConstructor
public class ClientBarController extends AbstractController {

    private final ClientBarService service;

    @PostMapping(value =PATH +  "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody ClientBarCreateDto createDto){
        return new ResponseEntity<>(service.create(createDto), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @PostMapping(value =PATH +  "/update")
    public ResponseEntity<DataDto<Long>> update(@Valid @RequestBody ClientBarUpdateDto createDto){
        return new ResponseEntity<>(service.update(createDto), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/getAll")
    public ResponseEntity<DataDto<List<ClientBarDto>>> getAll(GenericCriteria criteria){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get-all")
    public ResponseEntity<DataDto<List<ClientBarDto>>> getAllWithCriteria(GenericCriteria criteria){
        return new ResponseEntity<>(service.getWithCriteria(criteria), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get/{id}")
    public ResponseEntity<DataDto<ClientBarDto>> get(@PathVariable Long id){
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get-active")
    public ResponseEntity<DataDto<List<ClientBarDto>>> getActive(){
        return new ResponseEntity<>(service.getByOrgId(), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/get-today")
    public ResponseEntity<DataDto<List<ClientBarDto>>> getToday(){
        return new ResponseEntity<>(service.getThisDay(), HttpStatus.OK);
    }



}
