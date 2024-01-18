package cl.duoc.rulloa.telollevobackend.endpoint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Solicitud {
    private Integer id;
    private String numeroDeOrden;
    private String origen;
    private String destino;
    private String descripcion;
    private String direccionOrigen;
    private String direccionDestino;
    private String codigoSeguimiento;
    private String estado;
    private Transporte transporte;
}
