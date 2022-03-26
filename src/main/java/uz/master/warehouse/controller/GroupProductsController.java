package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class GroupProductsController {
    private final GroupProductsService service;


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody GroupProductsCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PutMapping("/update")
    public void update(@RequestBody GroupProductsUpdateDto dto) {
        service.update(dto);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<GroupProductsDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<GroupProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

}
