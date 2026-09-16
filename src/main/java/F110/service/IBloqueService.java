package F110.service;

import F110.model.Bloque;

import java.util.List;

public interface IBloqueService {

    List<Bloque> findAll();

    Bloque findById(Long id);

    Bloque findByCodigoBloque(String codigoBloque);

    Bloque save(Bloque bloque);

    Bloque update(Long id, Bloque bloque);

    void delete(Long id);
}
