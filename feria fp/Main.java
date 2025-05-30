package Practica6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedHashSet;

public class Main {

    private JFrame frame;
    private JEditorPane textPane;
    private GestionJuego gestion = new GestionJuego();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        initialize();
    }
    
    private void initialize() {
        frame = new JFrame("Juego de Palabras - GUI");
        frame.setSize(1200, 800); // Tamaño grande
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrado en pantalla
        frame.getContentPane().setLayout(null);

        JButton btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setBounds(20, 20, 220, 50);
        frame.getContentPane().add(btnNuevaPartida);

        JButton btnCargarPartida = new JButton("Cargar Partida");
        btnCargarPartida.setBounds(20, 90, 220, 50);
        frame.getContentPane().add(btnCargarPartida);

        JButton btnVerPuntuaciones = new JButton("Ver Puntuaciones");
        btnVerPuntuaciones.setBounds(20, 160, 220, 50);
        frame.getContentPane().add(btnVerPuntuaciones);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(20, 230, 220, 50);
        frame.getContentPane().add(btnSalir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(260, 20, 900, 720);
        frame.getContentPane().add(scrollPane);

        textPane = new JEditorPane("text/html", "");
        textPane.setEditable(false);
        scrollPane.setViewportView(textPane);
        textPane.setText("<html><body style='font-family: Arial; font-size: 20px;'></body></html>");

        btnNuevaPartida.addActionListener(e -> jugar(false, null));
        btnCargarPartida.addActionListener(e -> cargarPartida());
        btnVerPuntuaciones.addActionListener(e -> {
            String datos = gestion.consultarPuntuaciones();
            mostrarTexto("Puntuaciones:<br>" + datos.replace("\n", "<br>"));
        });
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void mostrarTexto(String contenidoHtml) {
        textPane.setText("<html><body style='font-family: Arial; font-size: 20px;'>" + contenidoHtml + "</body></html>");
    }

    private void appendTexto(String linea) {
        String actual = textPane.getText().replace("</body></html>", "");
        textPane.setText(actual + linea + "<br></body></html>");
    }

    private void jugar(boolean cargado, Guardar partidaCargada) {
        LinkedHashSet<String> palabras = gestion.cargarPalabras();
        int puntuacion = (cargado && partidaCargada != null) ? partidaCargada.puntuacion : 0;
        char[] palabraAdivinar = null;
        int intentos = 6;

        if (cargado && partidaCargada != null) {
            palabraAdivinar = partidaCargada.palabraAdivinar;
            intentos = partidaCargada.intentos;
        } else {
            int index = (int) (Math.random() * palabras.size());
            palabraAdivinar = palabras.stream().skip(index).findFirst().get().toCharArray();
        }

        mostrarTexto("Comienza el juego. ¡Adivina la palabra!");

        while (intentos > 0) {
            String entrada = JOptionPane.showInputDialog(frame, "Introduce una palabra de 5 letras o 0 para guardar:");

            if (entrada == null) return; // Cancelado

            if (entrada.equals("0")) {
                Guardar g = new Guardar(intentos, puntuacion, palabraAdivinar, "...");
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    gestion.guardarPartidaEnArchivo(g, fc.getSelectedFile());
                    JOptionPane.showMessageDialog(frame, "Partida guardada.");
                }
                return;
            }

            if (entrada.length() != 5) {
                JOptionPane.showMessageDialog(frame, "La palabra debe tener 5 letras.");
                continue;
            }

            StringBuilder resultado = new StringBuilder();
            int letrasCorrectas = 0;
            char[] escrita = entrada.toCharArray();

            for (int i = 0; i < 5; i++) {
                char c = escrita[i];
                if (c == palabraAdivinar[i]) {
                    resultado.append("<span style='background-color:lime; color:black; padding:6px; margin:4px;'>")
                            .append(c).append("</span>");
                    letrasCorrectas++;
                } else if (new String(palabraAdivinar).indexOf(c) != -1) {
                    resultado.append("<span style='background-color:gold; color:black; padding:6px; margin:4px;'>")
                            .append(c).append("</span>");
                } else {
                    resultado.append("<span style='background-color:lightgray; color:black; padding:6px; margin:4px;'>")
                            .append(c).append("</span>");
                }
            }

            appendTexto("Intento: " + entrada.toUpperCase() + " → " + resultado);

            if (letrasCorrectas == 5) {
                JOptionPane.showMessageDialog(frame, "¡Has ganado! Puntos: " + (intentos * 100));
                puntuacion += intentos * 100;

                int resp = JOptionPane.showConfirmDialog(frame, "¿Deseas guardar tu puntuación?", "Guardar", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    String nombre = JOptionPane.showInputDialog("Nombre del jugador:");
                    if (nombre != null && !nombre.isEmpty()) {
                        gestion.registrarPuntuaciones(puntuacion, nombre);
                        JOptionPane.showMessageDialog(frame, "Puntuación guardada.");
                    }
                }
                return;
            }

            intentos--;
        }

        JOptionPane.showMessageDialog(frame, "Has perdido. La palabra era: " + new String(palabraAdivinar));
    }

    private void cargarPartida() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            Guardar g = gestion.cargarPartidaDesdeArchivo(fc.getSelectedFile());
            if (g != null) {
                mostrarTexto("Partida cargada.<br>Intentos restantes: " + g.intentos);
                jugar(true, g);
            } else {
                JOptionPane.showMessageDialog(frame, "Error al cargar la partida.");
            }
        }
    }
}
