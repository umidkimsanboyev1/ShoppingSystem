package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.dto.organization.OrganizationCreateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.organization.OrganizationUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.OrganizationService;

import java.util.List;


@RestController
@RequestMapping("/organization/*")
@RequiredArgsConstructor
public class OrganizationController extends AbstractController {

    private final OrganizationService service;


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody OrganizationCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody OrganizationUpdateDto dto) {
        service.update(dto);
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<OrganizationDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<OrganizationDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }


}
