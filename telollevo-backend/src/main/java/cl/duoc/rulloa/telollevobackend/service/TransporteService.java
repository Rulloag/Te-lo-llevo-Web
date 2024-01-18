package cl.duoc.rulloa.telollevobackend.service;

import cl.duoc.rulloa.telollevobackend.endpoint.model.Transporte;
import cl.duoc.rulloa.telollevobackend.repository.TransporteEntity;
import cl.duoc.rulloa.telollevobackend.repository.TransporteRepository;
import cl.duoc.rulloa.telollevobackend.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {
    @Autowired
    private TransporteRepository transporteRepository;

    public List<Transporte> getAll() {
        List<TransporteEntity> entities = transporteRepository.findAll();
        return entities.stream().map(this::toModel).toList();
    }

    public List<Transporte> busqueda(String textoBusqueda) {
        List<TransporteEntity> entities =
                transporteRepository.findByPatenteContainsOrMarcaContainsOrModeloContains(textoBusqueda, textoBusqueda, textoBusqueda);
        return entities.stream().map(this::toModel).toList();
    }

    private Transporte toModel(TransporteEntity entity) {
        return Transporte.builder()
                .anio(entity.getAnio())
                .id(entity.getId())
                .marca(entity.getMarca())
                .modelo(entity.getModelo())
                .patente(entity.getPatente())
                .build();
    }

    public void exists(Integer transporteId) {
        if(!transporteRepository.existsById(transporteId)){
            throw new NotFoundException("El transporte indicado no existe");
        }
    }
}
