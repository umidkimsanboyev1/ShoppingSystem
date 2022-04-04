package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.controller.base.AbstractController;
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
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody OrganizationCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(PATH + "/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody OrganizationUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(PATH + "/list")
    public ResponseEntity<DataDto<List<OrganizationDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<OrganizationDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(PATH + "/updateLogo/{id}")
    public ResponseEntity<DataDto<Void>> loadLogo(@PathVariable("id") Long id,@RequestParam("file") MultipartFile file) {
        boolean result = service.loadLogo(id, file);
        if (result) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else return new ResponseEntity<>(new DataDto<>(false), HttpStatus.BAD_REQUEST);
    }



}
