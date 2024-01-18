package cl.duoc.rulloa.telollevobackend.endpoint;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Transporte;
import cl.duoc.rulloa.telollevobackend.endpoint.response.ResponseGetTransportes;
import cl.duoc.rulloa.telollevobackend.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transportes")
public class TransporteController {
    @Autowired
    private TransporteService transporteService;

    @GetMapping
    public ResponseEntity<ResponseGetTransportes> getAllTransportes(){
        List<Transporte> alls = transporteService.getAll();
        return ResponseEntity.ok(ResponseGetTransportes.builder().transportes(alls).build());
    }

    @GetMapping("/busqueda")
    public ResponseEntity<ResponseGetTransportes> busquedaTransportes(@RequestParam String textoBusqueda){
        List<Transporte> transportes = transporteService.busqueda(textoBusqueda);
        return ResponseEntity.ok(ResponseGetTransportes.builder().transportes(transportes).build());
    }
}
