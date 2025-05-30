package Practica6;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class GestionJuego {
	//C:\Users\Eduardo\eclipse-workspace\Practica_Feria\src\Practica6
    public LinkedHashSet<String> cargarPalabras() {
        LinkedHashSet<String> ls = new LinkedHashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Palabras5L.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(", ");
                for (String palabra : palabras) {
                    ls.add(palabra);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public Guardar cargarPartidaDesdeArchivo(File archivo) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Guardar) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void guardarPartidaEnArchivo(Guardar guardarPartida, File archivo) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(archivo))) {
            os.writeObject(guardarPartida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String consultarPuntuaciones() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Puntuaciones.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.append("Error al leer el archivo.");
        }
        return sb.toString();
    }
    
    public void registrarPuntuaciones(int puntuacion, String nombre) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Puntuaciones.csv", true))) {
            bw.write(nombre + "," + puntuacion);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


