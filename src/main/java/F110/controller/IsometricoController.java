package F110.controller;

import F110.dto.IsometricoDTO;
import F110.model.Isometrico;
import F110.service.IBloqueService;
import F110.service.IIsometricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/isometricos")
@RequiredArgsConstructor
public class IsometricoController {

    private final IIsometricoService isometricoService;
    private final IBloqueService bloqueService;

    @GetMapping
    public List<IsometricoDTO> findAll() {
        return isometricoService.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public IsometricoDTO findById(@PathVariable Long id) {
        return toDTO(isometricoService.findById(id));
    }

    @GetMapping("/codigo/{codigoIsometrico}")
    public IsometricoDTO findByCodigo(@PathVariable String codigoIsometrico) {
        return toDTO(isometricoService.findByCodigoIsometrico(codigoIsometrico));
    }

    @GetMapping("/bloque/{bloqueId}")
    public List<IsometricoDTO> findByBloque(@PathVariable Long bloqueId) {
        return isometricoService.findByBloqueId(bloqueId).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/linea/{lineaTuberia}")
    public List<IsometricoDTO> findByLinea(@PathVariable String lineaTuberia) {
        return isometricoService.findByLineaTuberia(lineaTuberia).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> getPdf(@PathVariable Long id) throws IOException {
        Path pdfPath = isometricoService.getPdfPath(id);
        Resource resource = new UrlResource(pdfPath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping
    public ResponseEntity<IsometricoDTO> save(@RequestBody IsometricoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDTO(isometricoService.save(toEntity(dto))));
    }

    @PostMapping(value = "/{id}/pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IsometricoDTO uploadPdf(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        return toDTO(isometricoService.uploadPdf(id, file));
    }

    @PutMapping("/{id}")
    public IsometricoDTO update(@PathVariable Long id, @RequestBody IsometricoDTO dto) {
        return toDTO(isometricoService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        isometricoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Isometrico toEntity(IsometricoDTO dto) {
        Isometrico isometrico = new Isometrico();
        isometrico.setCodigoIsometrico(dto.getCodigoIsometrico());
        isometrico.setLineaTuberia(dto.getLineaTuberia());
        isometrico.setBloque(bloqueService.findById(dto.getBloqueId()));
        isometrico.setPdfUrl(dto.getPdfUrl());
        return isometrico;
    }

    private IsometricoDTO toDTO(Isometrico isometrico) {
        return IsometricoDTO.builder()
                .id(isometrico.getId())
                .codigoIsometrico(isometrico.getCodigoIsometrico())
                .lineaTuberia(isometrico.getLineaTuberia())
                .bloqueId(isometrico.getBloque().getId())
                .pdfUrl(isometrico.getPdfUrl())
                .trabajoIds(isometrico.getTrabajos().stream()
                        .map(trabajo -> trabajo.getId())
                        .toList())
                .build();
    }
}
