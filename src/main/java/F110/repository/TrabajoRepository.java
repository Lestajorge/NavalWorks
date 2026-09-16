package F110.repository;

import F110.model.Trabajo;
import F110.enums.model.EstadoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    List<Trabajo> findByIsometricoId(Long isometricoId);
    List<Trabajo> findByEstado(EstadoTrabajo estado);
    List<Trabajo> findByOperariosId(Long operarioId);
}