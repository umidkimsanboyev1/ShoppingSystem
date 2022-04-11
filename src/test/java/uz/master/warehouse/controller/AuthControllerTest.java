package uz.master.warehouse.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import uz.master.warehouse.dto.auth.AuthCreateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.mapper.auth.AuthUserMapper;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.services.auth.AuthUserService;
import uz.master.warehouse.services.organization.OrganizationService;
import uz.master.warehouse.services.products.OutComeProductsService;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private AuthUserRepository repository=Mockito.mock(AuthUserRepository.class);
    @Autowired
    private OrganizationService organizationService;
    private AuthUserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthUserMapper mapper;

    @BeforeEach
    public void init(){
//        service=new AuthUserService(repository,organizationService,mapper,passwordEncoder);
    }
//    @Test
//    void create() {
////        AuthCreateDto dto=new AuthCreateDto(1L,"jafar","1","+998936432434","123","ADMIN");
//
//        DataDto<Long> user = service.createUser(dto);
//        System.out.println("user.getData() = " + user.getData());
//    }

    @Test
    void profile() {
        UserDetails userDetails = service.loadUserByUsername("1");

        System.out.println(userDetails.getAuthorities());
    }

    @Test
    void login() {

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