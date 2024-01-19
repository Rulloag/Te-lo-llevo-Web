package cl.duoc.rulloa.telollevobackend.endpoint;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Solicitud;
import cl.duoc.rulloa.telollevobackend.endpoint.request.RequestPatchSolicitud;
import cl.duoc.rulloa.telollevobackend.endpoint.request.RequestPostSolicitud;
import cl.duoc.rulloa.telollevobackend.endpoint.response.ResponseGetEstado;
import cl.duoc.rulloa.telollevobackend.endpoint.response.ResponseGetSolicitud;
import cl.duoc.rulloa.telollevobackend.endpoint.response.ResponsePostSolicitud;
import cl.duoc.rulloa.telollevobackend.service.SolicitudService;
import cl.duoc.rulloa.telollevobackend.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private TransporteService transporteService;

    @GetMapping
    public ResponseEntity<List<Solicitud>> getSolicitudes(){
        List<Solicitud> solicitud = solicitudService.getAll();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/{codigoSeguimiento}")
    public ResponseEntity<ResponseGetSolicitud> getSolicitud(@PathVariable String codigoSeguimiento){
        Solicitud solicitud = solicitudService.get(codigoSeguimiento);
        return ResponseEntity.ok(ResponseGetSolicitud.builder().solicitud(solicitud).build());
    }

    @GetMapping("/{codigoSeguimiento}/estado")
    public ResponseEntity<ResponseGetEstado> getEstado(@PathVariable String codigoSeguimiento){
        String estado = solicitudService.getEstado(codigoSeguimiento);
        return ResponseEntity.ok(ResponseGetEstado.builder().estado(estado).build());
    }

    @PostMapping
    public ResponseEntity<ResponsePostSolicitud> postSolicitud(@RequestBody RequestPostSolicitud request) {
        String codigoSeguimiento = solicitudService.post(request);
        ResponsePostSolicitud response = ResponsePostSolicitud.builder().codigoSeguimiento(codigoSeguimiento).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{codigoSeguimiento}")
    public ResponseEntity<Void> patchEstado(@PathVariable String codigoSeguimiento,
                                                         @RequestBody RequestPatchSolicitud request){
        solicitudService.patchEstado(codigoSeguimiento, request.getEstado());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{codigoSeguimiento}/asignacion")
    public ResponseEntity<ResponseGetEstado> patchTransporte(@PathVariable String codigoSeguimiento,
                                                         @RequestBody RequestPatchSolicitud request){
        transporteService.exists(request.getTransporteId());
        String estado = solicitudService.patchTransporte(codigoSeguimiento, request.getTransporteId());
        return ResponseEntity.ok(ResponseGetEstado.builder().estado(estado).build());
    }
}
