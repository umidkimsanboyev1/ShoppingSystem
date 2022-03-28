package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.master.warehouse.services.clientBar.ClientBarService;

@RestController
@RequestMapping("client/*")
@RequiredArgsConstructor
public class ClientBarController {

    private final ClientBarService service;



}
