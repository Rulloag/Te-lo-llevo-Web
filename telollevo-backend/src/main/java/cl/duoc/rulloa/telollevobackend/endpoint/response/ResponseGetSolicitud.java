package cl.duoc.rulloa.telollevobackend.endpoint.response;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Solicitud;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseGetSolicitud {
    private Solicitud solicitud;
}
