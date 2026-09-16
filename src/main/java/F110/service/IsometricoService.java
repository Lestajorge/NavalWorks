package F110.service;

import F110.model.Isometrico;
import F110.repository.IsometricoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IsometricoService implements IIsometricoService {

    private static final Path PDF_DIRECTORY = Paths.get("uploads", "isometricos");

    private final IsometricoRepository isometricoRepository;

    @Override
    public List<Isometrico> findAll() {
        return isometricoRepository.findAll();
    }

    @Override
    public Isometrico findById(Long id) {
        return isometricoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe el isométrico con id: " + id));
    }

    @Override
    public Isometrico findByCodigoIsometrico(String codigoIsometrico) {
        return isometricoRepository.findByCodigoIsometrico(codigoIsometrico)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe el isométrico con código: " + codigoIsometrico));
    }

    @Override
    public List<Isometrico> findByBloqueId(Long bloqueId) {
        return isometricoRepository.findByBloqueId(bloqueId);
    }

    @Override
    public List<Isometrico> findByLineaTuberia(String lineaTuberia) {
        return isometricoRepository.findByLineaTuberia(lineaTuberia);
    }

    @Override
    public Isometrico save(Isometrico isometrico) {
        return isometricoRepository.save(isometrico);
    }

    @Override
    public Isometrico update(Long id, Isometrico isometrico) {
        Isometrico isometricoExistente = findById(id);
        isometricoExistente.setCodigoIsometrico(isometrico.getCodigoIsometrico());
        isometricoExistente.setLineaTuberia(isometrico.getLineaTuberia());
        isometricoExistente.setBloque(isometrico.getBloque());
        return isometricoRepository.save(isometricoExistente);
    }

    @Override
    public Isometrico uploadPdf(Long id, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("El archivo PDF no puede estar vacío");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("El archivo debe tener extensión .pdf");
        }

        Isometrico isometrico = findById(id);
        Files.createDirectories(PDF_DIRECTORY);

        String filename = id + "-" + Path.of(originalFilename).getFileName();
        Path destination = PDF_DIRECTORY.resolve(filename);
        file.transferTo(destination);

        isometrico.setPdfUrl("/uploads/isometricos/" + filename);
        return isometricoRepository.save(isometrico);
    }

    @Override
    public Path getPdfPath(Long id) {
        Isometrico isometrico = findById(id);
        if (isometrico.getPdfUrl() == null || isometrico.getPdfUrl().isBlank()) {
            throw new EntityNotFoundException("El isométrico no tiene un PDF asociado");
        }

        Path pdfPath = Paths.get(isometrico.getPdfUrl().substring(1));
        if (!Files.exists(pdfPath)) {
            throw new EntityNotFoundException("No se encuentra el archivo PDF del isométrico");
        }

        return pdfPath;
    }

    @Override
    public void delete(Long id) {
        isometricoRepository.delete(findById(id));
    }
}
