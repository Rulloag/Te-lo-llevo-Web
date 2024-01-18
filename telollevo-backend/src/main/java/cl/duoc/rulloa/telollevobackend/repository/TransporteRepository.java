package cl.duoc.rulloa.telollevobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporteRepository extends JpaRepository<TransporteEntity, Integer> {
    List<TransporteEntity> findByPatenteContainsOrMarcaContainsOrModeloContains(String patente, String marca, String modelo);
    boolean existsById(Integer id);
}
