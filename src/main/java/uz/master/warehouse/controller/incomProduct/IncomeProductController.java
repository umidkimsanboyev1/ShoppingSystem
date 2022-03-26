package uz.master.warehouse.controller.incomProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.InComeProducts.InComeProductsCreateDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.InComeProducts.InComeProductsUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.products.InComeProducts;
import uz.master.warehouse.services.products.InComeProductsService;

import java.util.List;

@RestController
@RequestMapping("income/*")
@RequiredArgsConstructor
public class IncomeProductController extends AbstractController {

    private final InComeProductsService inComeProductsService;

    @PostMapping(PATH + "create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody InComeProductsCreateDto dto) {
        return new ResponseEntity<>( inComeProductsService.create( dto ), HttpStatus.OK );
    }

    @GetMapping(PATH + "get-all")
    public ResponseEntity<DataDto<List<InComeProductsDto>>> getAll() {
        return new ResponseEntity<>( inComeProductsService.getAll(), HttpStatus.OK );
    }

    @GetMapping(PATH + "get/{id}")
    public ResponseEntity<DataDto<InComeProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>( inComeProductsService.get( id ), HttpStatus.OK );

    }

    @DeleteMapping(PATH + "delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        inComeProductsService.delete( id );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );

    }

    @PutMapping(PATH + "create")
    public ResponseEntity<DataDto<Long>> update(@RequestBody InComeProductsUpdateDto dto) {

        return new ResponseEntity<>( inComeProductsService.update( dto ), HttpStatus.OK );
    }



}
