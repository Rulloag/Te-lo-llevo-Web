package cl.duoc.rulloa.telollevobackend.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transportes")
@Getter
@Setter
public class TransporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "patente")
    private String patente;
    @Column(nullable = false, name = "anio")
    private Integer anio;
    @Column(nullable = false, name = "marca")
    private String marca;
    @Column(nullable = false, name = "modelo")
    private String modelo;
}
