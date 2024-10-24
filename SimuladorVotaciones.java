/*
 * Proyecto 3 
 * Espinosa de los Monteros Martínez Eric Omar
 * 7CM2
 */

import java.io.*;
import java.util.Random;

class SimuladorVotaciones {

    private static final String[] partidos = {"MC", "MORENA", "PAN", "PRD", "PRI", "PT", "PVEM"};
    private static final double[] probabilidades = {0.15, 0.4, 0.1, 0.05, 0.15, 0.05, 0.1}; //Probabilidades para cada partido
    private static final Random random = new Random();
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Por favor, proporciona el número de votos como argumento.");
            return;
        }
        
        String archivo = "VOTOS.dat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            System.out.println("Simulando votaciones");
            while (true) {
                for (int i = 0; i < Integer.parseInt(args[0]); i++){
                    int partidoSeleccionado = generarPartidoSegunProbabilidad();
                    writer.write(GeneradorCURP.getCURP() + "    " + partidos[partidoSeleccionado] + "\n");
                }
                writer.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static int generarPartidoSegunProbabilidad() {
        double rand = random.nextDouble();  
        double acumulado = 0.0;

        for (int i = 0; i < probabilidades.length; i++) {
            acumulado += probabilidades[i];  
            if (rand <= acumulado) {
                return i;  
            }
        }
        return partidos.length - 1;  //En caso de no coincidir, devolver el último partido
    }
}
