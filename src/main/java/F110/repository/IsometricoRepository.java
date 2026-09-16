package F110.repository;

import F110.model.Isometrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IsometricoRepository extends JpaRepository<Isometrico, Long> {
    Optional<Isometrico> findByCodigoIsometrico(String codigoIsometrico);
    List<Isometrico> findByBloqueId(Long bloqueId);
    List<Isometrico> findByLineaTuberia(String lineaTuberia);
}