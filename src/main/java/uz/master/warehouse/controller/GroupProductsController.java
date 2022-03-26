package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.groupProducts.GroupProductsService;

import java.util.List;

/**
 * @author Panjiyev Javohir, сб 17:14. 26.03.2022
 */

@RestController
@RequestMapping("/groupproducts/*")
@RequiredArgsConstructor
public class GroupProductsController extends AbstractController {
    private final GroupProductsService service;


    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody GroupProductsCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @PutMapping("/update")
    public void update(@RequestBody GroupProductsUpdateDto dto) {
        service.update(dto);
    }


    @GetMapping("/list")
    public ResponseEntity<DataDto<List<GroupProductsDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<GroupProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

}
