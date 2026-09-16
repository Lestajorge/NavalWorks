package F110.enums.model;

import lombok.Getter;

@Getter
public enum EstadoTrabajo {
    PENDIENTE("Pendiente de inicio", "gris"),
    EN_EJECUCION("En proceso de montaje/soldadura", "naranja"),
    REVISADO("Pendiente de inspección de calidad", "amarillo"),
    ACEPTADO("Trabajo validado y finalizado", "verde");

    private final String descripcion;
    private final String color;

    EstadoTrabajo(String descripcion, String color) {
        this.descripcion = descripcion;
        this.color = color;
    }
}