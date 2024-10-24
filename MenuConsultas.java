/*
 * Proyecto 3 
 * Espinosa de los Monteros Martínez Eric Omar
 * 7CM2
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Scanner;

public class MenuConsultas {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            menu();
        }
    }

    static void menu() {
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido al programa para consultar las estadísticas de las elecciones");
        System.out.println("Selecciona una opción:\n");
        System.out.println("1. Votos totales por sexo.");
        System.out.println("2. Votos totales por cada estado de la república.");
        System.out.println("3. Votos totales por edad.");
        System.out.println("4. Votos totales por partido y votos totales en general.");
        System.out.println("0. Salir.");
        System.out.print("Opción: ");
        
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 0:
                scanner.close();
                System.exit(0);
                break;
            case 1:
                votosPorSexo();
                pausa();
                break;
            case 2:
                votosPorEstado();
                pausa();
                break;
            case 3:
                votosPorEdad();
                pausa();
                break;
            case 4:
                votosPorPartido();
                pausa();
                break;

            default:
                break;
        }
    }

    static void votosPorSexo() {
        String archivo = "VOTOS.dat";
        long noMujeres = 0;
        long noHombres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.length() > 18) {
                    if (linea.charAt(10) == 'H') {
                        noHombres++;
                    } else {
                        noMujeres++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nHombres: " + noHombres + " votos");
        System.out.println("Mujeres: " + noMujeres + " votos");
    }

    static void votosPorEstado() {
        HashMap<String, String> diccionario = new HashMap<>();
        diccionario.put("AS", "Aguascalientes");
        diccionario.put("BC", "Baja California");
        diccionario.put("BS", "Baja California Sur");
        diccionario.put("CC", "Campeche");
        diccionario.put("CL", "Coahuila");
        diccionario.put("CM", "Colima");
        diccionario.put("CS", "Chiapas");
        diccionario.put("CH", "Chihuahua");
        diccionario.put("DF", "Distrito Federal");
        diccionario.put("DG", "Durango");
        diccionario.put("GT", "Guanajuato");
        diccionario.put("GR", "Guerrero");
        diccionario.put("HG", "Hidalgo");
        diccionario.put("JC", "Jalisco");
        diccionario.put("MC", "México");
        diccionario.put("MN", "Michoacán");
        diccionario.put("MS", "Morelos");
        diccionario.put("NT", "Nayarit");
        diccionario.put("NL", "Nuevo León");
        diccionario.put("OC", "Oaxaca");
        diccionario.put("PL", "Puebla");
        diccionario.put("QT", "Querétaro");
        diccionario.put("QR", "Quintana Roo");
        diccionario.put("SP", "San Luis Potosí");
        diccionario.put("SL", "Sinaloa");
        diccionario.put("SR", "Sonora");
        diccionario.put("TC", "Tabasco");
        diccionario.put("TS", "Tamaulipas");
        diccionario.put("TL", "Tlaxcala");
        diccionario.put("VZ", "Veracruz");
        diccionario.put("YN", "Yucatán");
        diccionario.put("ZS", "Zacatecas");
        diccionario.put("NE", "Nacido en el extranjero");

        HashMap<String, Integer> conteoVotos = new HashMap<>(); //Estado, cantidad de votos

        String archivo = "VOTOS.dat";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String estado;
            while ((linea = br.readLine()) != null) {
                if (linea.length() > 18) {
                    estado = String.valueOf(linea.charAt(11)) + linea.charAt(12);
                    conteoVotos.put(estado, conteoVotos.getOrDefault(estado, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nConteo de votos por estado:\n");
        for (String clave : conteoVotos.keySet()) {
            String nombreEstado = diccionario.get(clave);
            System.out.println(nombreEstado + ": " + conteoVotos.get(clave) + " votos");
        }
        System.out.println();
    }

    static void votosPorEdad() {
        String archivo = "VOTOS.dat";
        HashMap<Integer, Integer> conteoVotos = new HashMap<>(); //Año, Cantidad de votos

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String anioNacimiento;
            String mesNacimiento;
            String diaNacimiento;

            while ((linea = br.readLine()) != null) {
                if (linea.length() > 18) {
                    anioNacimiento = String.valueOf(linea.charAt(4)) + linea.charAt(5);
                    if (Integer.parseInt(anioNacimiento) < 6) { //Mayor a 18 años
                        anioNacimiento = "20" + anioNacimiento;
                    } else {
                        anioNacimiento = "19" + anioNacimiento;
                    }
                    mesNacimiento = String.valueOf(linea.charAt(6)) + linea.charAt(7);
                    diaNacimiento = String.valueOf(linea.charAt(8)) + linea.charAt(9);

                    LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(anioNacimiento),
                            Integer.parseInt(mesNacimiento), Integer.parseInt(diaNacimiento));
                    LocalDate fechaActual = LocalDate.now();
                    Period edad = Period.between(fechaNacimiento, fechaActual);

                    conteoVotos.put(edad.getYears(), conteoVotos.getOrDefault(edad.getYears(), 0) + 1);
                }
            }
            System.out.println("\nConteo de votos por edad:\n");
            for (Integer iEdad : conteoVotos.keySet()) {
                System.out.println(iEdad + " Años: " + conteoVotos.get(iEdad) + " votos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void votosPorPartido() {
        HashMap<String, Integer> conteoVotos = new HashMap<>(); //Partido, Cantidad de votos
        String archivo = "VOTOS.dat";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int votosTotales = 0;
            while ((linea = br.readLine()) != null) {
                votosTotales++;
                if (linea.length() > 22) {
                    linea = linea.substring(22);
                    conteoVotos.put(linea, conteoVotos.getOrDefault(linea, 0) + 1);
                }
            }
            System.out.println("\nConteo de votos por partido:\n");
            for (String partido : conteoVotos.keySet()) {
                System.out.println(partido + ": " + conteoVotos.get(partido) + " votos");
            }
            System.out.println("\nVotos totales: " + votosTotales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void pausa() {
        System.out.println("\nPreciona ENTER para continuar");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
