package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.CompanyService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Panjiyev Javohir, сб 18:13. 26.03.2022
 */
@RestController
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController extends AbstractController {

    private final CompanyService service;

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody CompanyCreateDto dto) {
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
    public ResponseEntity<DataDto<Long>> update(@Valid @RequestBody CompanyUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
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
