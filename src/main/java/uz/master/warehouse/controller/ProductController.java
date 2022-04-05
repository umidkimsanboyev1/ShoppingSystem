package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.EditorAwareTag;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.criteria.ProductCriteria;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.product.ProductCreateDto;
import uz.master.warehouse.dto.product.ProductDto;
import uz.master.warehouse.dto.product.ProductUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.product.ProductService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController extends AbstractController {
    private final ProductService service;


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody ProductCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @DeleteMapping(PATH + "/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PutMapping(PATH + "/update")
    public void update(@Valid @RequestBody ProductUpdateDto dto) {
        service.update(dto);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping(PATH + "/list")
    public ResponseEntity<DataDto<List<ProductDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<ProductDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(PATH + "/search/{model}")
    public ResponseEntity<DataDto<List<ProductDto>>> search(@PathVariable String model) {
        DataDto<List<ProductDto>> search1 = service.search(model);
        return new ResponseEntity<>(search1, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(PATH + "/list")
    public ResponseEntity<DataDto<List<ProductDto>>> getWithCriteria(ProductCriteria criteria) {
        return new ResponseEntity<>(service.getWithCriteria(criteria), HttpStatus.OK);
    }
}
