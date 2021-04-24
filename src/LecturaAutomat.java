import acm.program.CommandLineProgram;

import java.util.Arrays;

public class LecturaAutomat extends CommandLineProgram {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void run() {
        /*Lectura de dades de l'autòmat*/
        System.out.println(ANSI_CYAN + "---------- Lectura de dades ----------");
        int nombreEstats = readInt(ANSI_RESET + "Introdueix el nombre d'estats de l'autòmat: \n"); //Introduccio nombre estats autòmat
        int estatsFinalsNombre = readInt("Quants estats finals té l'autòmat? \n"); //Introduccio nombre estats finals
        String[] estatsFinals = introduccioestatsFinals(estatsFinalsNombre); //Introduccio estats finals
        int alfabetNombre = readInt("Quants símbols té l'alfabet? \n"); //Introduccio nombre símbols alfabet
        String[] alfabet = introduccioAlfabet(alfabetNombre); //Introduccio alfabet
        /*--------------------------------------------------------------------------------------------------------------------*/
        /*Visualitzacio de dades per pantalla*/
        System.out.println(ANSI_CYAN + "---------- Visualitzacio de dades ----------" + ANSI_RESET);
        veureDades(nombreEstats, estatsFinalsNombre, estatsFinals, alfabetNombre, alfabet); //Visualitzacio de dades per pantalla
    }


    /**
     * Aquesta funcio treu per pantalla totes les dades per pantalla
     **/
    private void veureDades(int nombreEstats, int estatsFinalsNombre, String[] estatsFinals, int alfabetNombre, String[] alfabet) {
        System.out.println(ANSI_YELLOW + "NOTA: els estats es numeren de la següent manera: 1, 2, 3, ..., n");
        System.out.println(ANSI_PURPLE + "Nombre d'estats: " + ANSI_RESET + nombreEstats);
        System.out.println(ANSI_PURPLE + "Nombre d'estats finals: " + ANSI_RESET + estatsFinalsNombre);
        System.out.println(ANSI_PURPLE + "Estats finals: " + ANSI_RESET + Arrays.toString(estatsFinals));
        System.out.println(ANSI_PURPLE + "Nombre de simbols de l'alfabet: " + ANSI_RESET + alfabetNombre);
        System.out.println(ANSI_PURPLE + "Alfabet: " + ANSI_RESET + Arrays.toString(alfabet));

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
    private String[] introduccioestatsFinals(int nombreFinals) {
        String[] finals = new String[nombreFinals];
        for (int i = 0; i < nombreFinals; i++) {
            finals[i] = readLine("Introdueix l'estat final " + (i + 1) + " \n");
        }
        return finals;
    }


}
