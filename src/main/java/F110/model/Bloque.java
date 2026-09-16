package F110.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bloques")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bloque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_bloque", nullable = false, unique = true, length = 50)
    private String codigoBloque;

    @Min(value = 1, message = "La zona debe ser como mínimo 1")
    @Max(value = 9, message = "La zona debe ser como máximo 9")
    @Column(name = "zona", nullable = false)
    private int zona;

    @OneToMany(mappedBy = "bloque")
    @Builder.Default
    private List<Isometrico> isometricos = new ArrayList<>();
}
