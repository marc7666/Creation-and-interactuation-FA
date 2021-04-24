import acm.program.CommandLineProgram;

import java.util.Arrays;
import java.util.StringTokenizer;

public class LecturaAutomat extends CommandLineProgram {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public void run() {
        System.out.println(ANSI_CYAN + "---------- Lectura de dades ----------");
        System.out.println(ANSI_YELLOW + "NOTA: els estats es numeren de la següent manera: 0, 1, 2, ..., n");
        /**
         * Introducció del nombre d'estats
         **/
        int nombreEstats = readInt(ANSI_RESET + "Introdueix el nombre d'estats de l'autòmat: \n");
        /**
         * Introducció del nombre d'estats finals
         **/
        int estatsFinalsNombre = readInt("Quants estats finals té l'autòmat? \n");
        /**
         * Introducció dels estats finals
         **/
        int[] estatsFinals = introduccioestatsFinals(estatsFinalsNombre);
        /**
         * Introducció del nombre de símbols de l'alfabet
         **/
        int alfabetNombre = readInt("Quants símbols té l'alfabet? \n");
        /**
         * Introducció de l'alfabet
         **/
        String[] alfabet = introduccioAlfabet(alfabetNombre);
        /**
         * Introducció de la funció de transició
         **/
        int[][] funcioTrnasicio = funcioDeTrnasicio(nombreEstats, alfabetNombre, alfabet);
        /**
         * Visualitzacio de dades per pantalla
         **/
        System.out.println(ANSI_CYAN + "---------- Visualitzacio de dades ----------" + ANSI_RESET);
        veureDades(nombreEstats, estatsFinalsNombre, estatsFinals, alfabetNombre, alfabet, funcioTrnasicio); //Visualitzacio de dades per pantall
        /**
         * Proces de demanda de la paraula a llegir
         **/
        String paraula = readLine("Introdueix una paraula per entrar a la maquina: ");
        /**
         * Tractament de la paraula
         **/
        int ultimEstat = lecturaParaula(paraula, estatsFinals, funcioTrnasicio, alfabet);
        if (acceptacioParaula(ultimEstat, estatsFinals)) {
            System.out.println(ANSI_GREEN + "La paraula: '" + paraula + "' ha estat acceptada.");
        } else {
            System.out.println(ANSI_RED + "La paraula: '" + paraula + "' no ha estat acceptada.");
        }

    }

    private int lecturaParaula(String paraula, int[] estatsFinals, int[][] funcioTrnasicio, String[] alfabet) {
        int estatActual = 0;
        for (int i = 0; i < paraula.length(); i++) {
            String simbol = paraula.substring(i, i + 1);
            int indexSimbol = index(alfabet, simbol);
            int estatSeguent = funcioTrnasicio[estatActual][indexSimbol];
            System.out.println("Estat actual: " + estatActual + ", simbol llegit: " + simbol + ", estat seguent: " + estatSeguent);
            estatActual = estatSeguent;
        }
        return estatActual;
    }

    private boolean acceptacioParaula(int estatActual, int[] estatsFinals) {
        for (int i = 0; i < estatsFinals.length; i++) {
            if (estatActual == estatsFinals[i]) {
                return true;
            }
        }
        return false;
    }

    private int index(String[] alfabet, String simbol) {
        int index = 0;
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i].equalsIgnoreCase(simbol)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Aquesta funció retorna una matriu que conté la funció de transició de l'autòmat
     **/
    private int[][] funcioDeTrnasicio(int nombreEstats, int alfabetNombre, String[] alfabet) {
        int[][] funciotransicio = new int[nombreEstats][alfabetNombre];
        for (int i = 0; i < nombreEstats; i++) {
            for (int j = 0; j < alfabetNombre; j++) {
                funciotransicio[i][j] = readInt("Desde el estat " + (i) + " i llegint el simbol " + "'" + alfabet[j] + "'" + " anem al estat => ");
            }
        }
        return funciotransicio;
    }

    /**
     * Aquesta funcio treu per pantalla totes les dades per pantalla
     **/
    private void veureDades(int nombreEstats, int estatsFinalsNombre, int[] estatsFinals, int alfabetNombre, String[] alfabet, int[][] funciotransicio) {
        System.out.println(ANSI_YELLOW + "NOTA: els estats es numeren de la següent manera: 0, 1, 2, ..., n");
        System.out.println(ANSI_PURPLE + "Nombre d'estats: " + ANSI_RESET + nombreEstats);
        System.out.println(ANSI_PURPLE + "Nombre d'estats finals: " + ANSI_RESET + estatsFinalsNombre);
        System.out.println(ANSI_PURPLE + "Estats finals: " + ANSI_RESET + Arrays.toString(estatsFinals));
        System.out.println(ANSI_PURPLE + "Nombre de simbols de l'alfabet: " + ANSI_RESET + alfabetNombre);
        System.out.println(ANSI_PURPLE + "Alfabet: " + ANSI_RESET + Arrays.toString(alfabet));
        System.out.println(ANSI_PURPLE + "Funcio de transicio: ");
        for (int i = 0; i <= estatsFinalsNombre; i++) {
            print(ANSI_GREEN + "\t" + alfabet[i] + "  ");
        }
        print("\n");
        for (int i = 0; i < nombreEstats; i++) {
            print(ANSI_GREEN + i + "\t");
            for (int j = 0; j < alfabetNombre; j++) {
                print(ANSI_RESET + funciotransicio[i][j] + "\t");
            }
            print("\n");
        }

    }

    /**
     * Aquesta funcio retorna una array de strings amb l'alfabet que l'usuari vulgui
     **/
    private String[] introduccioAlfabet(int alfabetNombre) {
        String[] alfabet = new String[alfabetNombre];
        for (int i = 0; i < alfabetNombre; i++) {
            alfabet[i] = readLine("Introdueix el símbol " + (i + 1) + ": \n");
        }
        return alfabet;
    }


    /**
     * Aquesta funcio retorna una array de strings amb els estats finals
     **/
    private int[] introduccioestatsFinals(int nombreFinals) {
        int[] finals = new int[nombreFinals];
        for (int i = 0; i < nombreFinals; i++) {
            finals[i] = readInt("Introdueix l'estat final " + (i + 1) + " \n");
        }
        return finals;
    }


}
