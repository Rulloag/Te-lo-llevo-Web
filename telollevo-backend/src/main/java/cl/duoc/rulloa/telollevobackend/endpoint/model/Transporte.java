package cl.duoc.rulloa.telollevobackend.endpoint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transporte {
    private Integer id;
    private String patente;
    private Integer anio;
    private String marca;
    private String modelo;
}
