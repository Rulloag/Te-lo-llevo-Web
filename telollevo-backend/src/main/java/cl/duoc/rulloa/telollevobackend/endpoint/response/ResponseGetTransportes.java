package cl.duoc.rulloa.telollevobackend.endpoint.response;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Transporte;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResponseGetTransportes {
    private List<Transporte> transportes;
}
