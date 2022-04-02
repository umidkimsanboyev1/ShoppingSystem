package uz.master.warehouse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.master.warehouse.services.products.OutComeProductsService;

import static org.junit.jupiter.api.Assertions.*;

class OutComeProductsControllerTest {
    @Autowired
    private  OutComeProductsService service;
    @Test
    void get() {
        System.out.println("service.get(1L) = " + service.get(1L));
    }

    @Test
    void testGet() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAllId() {
    }

    @Test
    void update() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}