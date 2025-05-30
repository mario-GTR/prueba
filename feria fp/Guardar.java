package Practica6;

import java.io.Serializable;

// Clase que facilita guardar una partida
public class Guardar implements Serializable {
    int intentos;
    int puntuacion;	
    char[] palabraAdivinar;
    String textoGuardar;

    public Guardar(int intentos, int puntuacion, char[] palabraAdivinar, String textoGuardar) {
        this.intentos = intentos;
        this.puntuacion = puntuacion;
        this.palabraAdivinar = palabraAdivinar;
        this.textoGuardar = textoGuardar;
    }
}
