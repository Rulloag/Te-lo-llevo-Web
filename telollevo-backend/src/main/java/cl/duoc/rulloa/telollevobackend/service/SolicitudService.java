package cl.duoc.rulloa.telollevobackend.service;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Solicitud;
import cl.duoc.rulloa.telollevobackend.endpoint.model.Transporte;
import cl.duoc.rulloa.telollevobackend.endpoint.request.RequestPostSolicitud;
import cl.duoc.rulloa.telollevobackend.repository.SolicitudEntity;
import cl.duoc.rulloa.telollevobackend.repository.SolicitudRepository;
import cl.duoc.rulloa.telollevobackend.repository.TransporteEntity;
import cl.duoc.rulloa.telollevobackend.repository.TransporteRepository;
import cl.duoc.rulloa.telollevobackend.service.exception.FoundException;
import cl.duoc.rulloa.telollevobackend.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private TransporteRepository transporteRepository;

    public String post(RequestPostSolicitud request) {

        solicitudRepository.findByNumeroDeOrden(request.getNumeroDeOrden())
                .ifPresent(p -> {
                    throw new FoundException("Ya existe una solicitud con el número de orden");
                });

        SolicitudEntity entity = new SolicitudEntity();
        entity.setCodigoSeguimiento(UUID.randomUUID().toString());
        entity.setDescripcion(request.getDescripcion());
        entity.setDireccionOrigen(request.getDireccionOrigen());
        entity.setDireccionDestino(request.getDireccionDestino());
        entity.setEstado("Ingresada");
        entity.setOrigen(request.getOrigen());
        entity.setDestino(request.getDestino());
        entity.setNumeroDeOrden(request.getNumeroDeOrden());
        solicitudRepository.save(entity);
        return entity.getCodigoSeguimiento();
    }

    public List<Solicitud> getAll() {
        return solicitudRepository.findAll()
                .stream()
                .map(this::toModel)
                .toList();
    }

    public String getEstado(String codigoSeguimiento) {
        SolicitudEntity entity = solicitudRepository.findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> new NotFoundException("No existe solicitud para este código de seguimiento"));
        return entity.getEstado();
    }

    public void patchEstado(String codigoSeguimiento, String estado) {
        SolicitudEntity entity = solicitudRepository.findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> new NotFoundException("No existe solicitud para este código de seguimiento"));
        entity.setEstado(estado);
        solicitudRepository.save(entity);
    }

    public String patchTransporte(String codigoSeguimiento, Integer transporteId) {
        SolicitudEntity entity = solicitudRepository.findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> new NotFoundException("No existe solicitud para este código de seguimiento"));
        TransporteEntity transporte = transporteRepository.findById(transporteId)
                .orElseThrow(() -> new NotFoundException("No existe transporte para el ID indicado"));
        entity.setEstado("Asiganda");
        entity.setTransporte(transporte);
        solicitudRepository.save(entity);
        return entity.getEstado();
    }

    public Solicitud get(String codigoSeguimiento) {
        SolicitudEntity entity = solicitudRepository.findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> new NotFoundException("No existe solicitud para este código de seguimiento"));
        return toModel(entity);
    }

    private Solicitud toModel(SolicitudEntity entity){
        return Solicitud.builder()
                .id(entity.getId())
                .codigoSeguimiento(entity.getCodigoSeguimiento())
                .descripcion(entity.getDescripcion())
                .destino(entity.getDestino())
                .direccionOrigen(entity.getDireccionOrigen())
                .direccionDestino(entity.getDireccionDestino())
                .estado(entity.getEstado())
                .numeroDeOrden(entity.getNumeroDeOrden())
                .origen(entity.getOrigen())
                .transporte(toModel(entity.getTransporte()))
                .build();
    }

    private Transporte toModel(TransporteEntity transporte) {
        if (transporte != null) {
            return Transporte.builder()
                    .anio(transporte.getAnio())
                    .id(transporte.getId())
                    .marca(transporte.getMarca())
                    .modelo(transporte.getModelo())
                    .patente(transporte.getPatente())
                    .build();
        }
        return null;
    }
}
