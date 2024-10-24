/*
 * Proyecto 3 
 * Espinosa de los Monteros Martínez Eric Omar
 * 7CM2
 */

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GraficoVotos extends JPanel {

    private HashMap<String, Integer> conteoVotos = new HashMap<>(); //Partido, Cantidad de votos
    private String[] partidos = {"MC", "MORENA", "PAN", "PRD", "PRI", "PT", "PVEM"};
    private Color[] colores = {Color.ORANGE, new Color(128, 0, 0), Color.BLUE, Color.YELLOW, Color.GRAY, Color.RED, Color.GREEN};
    private int totalVotos;

    public GraficoVotos() {
        leerVotosDesdeArchivo();
        
        Timer timer = new Timer(1000, e -> {
            leerVotosDesdeArchivo();  
            repaint();  
        });
        timer.start();
    }

    private void leerVotosDesdeArchivo() {
        String archivo = "VOTOS.dat";
        conteoVotos.clear();  // Limpiar el mapa de votos
        totalVotos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalVotos++;
                if (linea.length() > 22) {
                    String partido = linea.substring(22);
                    conteoVotos.put(partido, conteoVotos.getOrDefault(partido, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 30;
        int anchoMax = getWidth() - 200;  // Usar el ancho de la ventana disponible, dejando espacio
        
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        for (int i = 0; i < partidos.length; i++) {
            String partido = partidos[i];
            int votos = conteoVotos.getOrDefault(partido, 0);

            // Dibujar nombre del partido
            g.setColor(Color.BLACK);
            g.drawString(partido, 20, y + 15);
            
            // Dibujar votos
            g.drawString(String.valueOf(votos), 100, y + 15);
            
            // Dibujar barra de color
            g.setColor(colores[i]);
            int anchoBarra = (int) ((votos / (double) totalVotos) * anchoMax * 1.5);
            g.fillRect(180, y, anchoBarra, 20);

            y += 40;  // Ajustar la posición para el siguiente partido
        }
        
        // Dibujar total de votos
        g.setColor(Color.BLACK);
        g.drawString("TOTAL:", 20, y + 15);
        g.drawString(String.valueOf(totalVotos), 100, y + 15);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujar con Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);  // Ajustar tamaño inicial
        frame.add(new GraficoVotos());
        frame.setVisible(true);
    }
}
