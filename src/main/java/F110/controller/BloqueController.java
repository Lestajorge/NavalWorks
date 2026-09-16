package F110.controller;

import F110.dto.BloqueDTO;
import F110.model.Bloque;
import F110.service.IBloqueService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/bloques")
@RequiredArgsConstructor
public class BloqueController {

    private final IBloqueService bloqueService;

    @GetMapping("/HolaMundo")
    public String holaMundo() {
        return "Hola mundo";
    }

    @GetMapping
    public List<BloqueDTO> findAll() {
        return bloqueService.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public BloqueDTO findById(@PathVariable Long id) {

        return toDTO(bloqueService.findById(id));
    }

    @GetMapping("/codigo/{codigoBloque}")
    public BloqueDTO findByCodigo(@PathVariable String codigoBloque) {
        return toDTO(bloqueService.findByCodigoBloque(codigoBloque));
    }

    @PostMapping
    public ResponseEntity<BloqueDTO> save(@Valid @RequestBody BloqueDTO dto) {
        Bloque bloque = new Bloque();
        bloque.setCodigoBloque(dto.getCodigoBloque());
        bloque.setZona(dto.getZona());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDTO(bloqueService.save(bloque)));
    }

    @PutMapping("/{id}")
    public BloqueDTO update(@PathVariable Long id, @Valid @RequestBody BloqueDTO dto) {
        Bloque bloque = new Bloque();
        bloque.setCodigoBloque(dto.getCodigoBloque());
        bloque.setZona(dto.getZona());
        return toDTO(bloqueService.update(id, bloque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bloqueService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private BloqueDTO toDTO(Bloque bloque) {
        return BloqueDTO.builder()
                .id(bloque.getId())
                .codigoBloque(bloque.getCodigoBloque())
                .zona(bloque.getZona())
                .build();
    }
}
