package cl.duoc.rulloa.telollevobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Integer> {
    Optional<SolicitudEntity> findByCodigoSeguimiento(String codigoSeguimiento);
    Optional<SolicitudEntity> findByNumeroDeOrden(String numeroDeOrden);
}
