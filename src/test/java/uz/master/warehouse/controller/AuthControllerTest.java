package uz.master.warehouse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.master.warehouse.services.products.OutComeProductsService;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {



    @Test
    void profile() {
//        RestTemplate restTemplate=new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Bearer ", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDkwL2FwaS9sb2dpbiIsImV4cCI6MTY0OTcxOTUzOX0.csvu2lzWTTUCY4EmQWDfgPh9VdyZ-BWLQNMJ4FSQDps");
//
//        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                "localhost:9090/api/v1/auth/profile", HttpMethod.GET, requestEntity, String.class);
//        System.out.println(response.toString());
    }

    @Test
    void login() {

    }

    @Test
    void create() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}