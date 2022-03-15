package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ClientBar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Double payment;

    @Column(nullable = false)
    private boolean paid = false;

    @Column(nullable = false)
    private boolean taken = false;
}
