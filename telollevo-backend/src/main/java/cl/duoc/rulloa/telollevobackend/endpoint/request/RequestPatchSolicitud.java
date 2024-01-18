package cl.duoc.rulloa.telollevobackend.endpoint.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPatchSolicitud {
    private String estado;
    private Integer transporteId;
}
