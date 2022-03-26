package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.CompanyService;

import java.util.List;

/**
 * @author Panjiyev Javohir, сб 18:13. 26.03.2022
 */
@RestController
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService service;


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody CompanyCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PutMapping("/update")
    public void update(@RequestBody CompanyUpdateDto dto) {
        service.update(dto);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<CompanyDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<CompanyDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }
}
