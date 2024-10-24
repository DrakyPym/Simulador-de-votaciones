/*
 * Proyecto 3 
 * Espinosa de los Monteros Martínez Eric Omar
 * 7CM2
 */

class GeneradorCURP {
    static String getCURP() {
        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Numero = "0123456789";
        String Sexo = "HM";
        String Entidad[] = { "AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC",
                "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS" };
        int indice;

        StringBuilder sb = new StringBuilder(18);

        for (int i = 1; i < 5; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }

        // Año
        for (int i = 5; i < 7; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }
        boolean febrero = false;
        // Mes
        indice = (int) (Math.random() * 2);
        sb.append(Numero.charAt(indice));
        if (indice == 1) {
            indice = (int) (Math.random() * 3);
            sb.append(Numero.charAt(indice));
        } else {
            indice = (int) (((Numero.length() - 1) * Math.random()) + 1);
            sb.append(Numero.charAt(indice));
            if (indice == 2) {
                febrero = true;
            }
        }

        // Dia
        if (febrero == true) {
            indice = (int) (Math.random() * 3);
            sb.append(Numero.charAt(indice));
            if (indice == 0) {
                indice = (int) ((Math.random() * 7) + 1);
                sb.append(Numero.charAt(indice));
            }else{
                indice = (int) (Math.random() * 8);
                sb.append(Numero.charAt(indice));
            }
        } else {
            indice = (int) (Math.random() * 4);
            sb.append(Numero.charAt(indice));
            if (indice == 3) {
                sb.append('0');
            } else if (indice == 0) {
                indice = (int) ((Math.random() * 8) + 1);
                sb.append(Numero.charAt(indice));
            } else {
                indice = (int) (Numero.length() * Math.random());
                sb.append(Numero.charAt(indice));
            }
        }

        indice = (int) (Sexo.length() * Math.random());
        sb.append(Sexo.charAt(indice));

        sb.append(Entidad[(int) (Math.random() * 32)]);
        for (int i = 14; i < 17; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));
        }
        for (int i = 17; i < 19; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));
        }

        return sb.toString();
    }
}
