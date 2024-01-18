package cl.duoc.rulloa.telollevobackend.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "codigo_seguimiento")
    private String codigoSeguimiento;

    @Column(nullable = false, name = "numero_de_orden", unique = true)
    private String numeroDeOrden;

    @Column(nullable = false, name = "origen")
    private String origen;

    @Column(nullable = false, name = "direccion_origen")
    private String direccionOrigen;

    @Column(nullable = false, name = "destino")
    private String destino;

    @Column(nullable = false, name = "direccio_destinon")
    private String direccionDestino;

    @Column(nullable = false, name = "descripcion")
    private String descripcion;

    @Column(nullable = false, name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "transporte_id")
    private TransporteEntity transporte;
}
