package cl.duoc.rulloa.telollevobackend.endpoint.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestPostSolicitud {
    private String numeroDeOrden;
    private String origen;
    private String destino;
    private String descripcion;
    private String direccionOrigen;
    private String direccionDestino;
}
