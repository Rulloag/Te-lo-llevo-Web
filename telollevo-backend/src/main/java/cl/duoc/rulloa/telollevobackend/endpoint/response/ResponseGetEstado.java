package cl.duoc.rulloa.telollevobackend.endpoint.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseGetEstado {
    private String estado;
}
