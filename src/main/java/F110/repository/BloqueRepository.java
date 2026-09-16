package F110.repository;

import F110.model.Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueRepository extends JpaRepository<Bloque, Long> {

    Optional<Bloque> findByCodigoBloque(String codigoBloque);

    boolean existsByCodigoBloque(String codigoBloque);
}