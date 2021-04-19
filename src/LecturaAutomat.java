import acm.program.CommandLineProgram;

import java.util.Arrays;

public class LecturaAutomat extends CommandLineProgram {
    public void run() {
        int nombreEstats = readInt("Introdueix el nombre d'estats de l'autòmat: \n");
        int estatsFinalsNombre = readInt("Quants estats finals té l'autòmat? \n");
        int[] estatsFinals = introduccioestatsFinals(estatsFinalsNombre);
        int alfabetNombre = readInt("Quants símbols té l'alfabet? \n");
        boolean esBinari = readBoolean("És una alfabet binari? \n");
        int[] alfabet1 = new int[0];
        String[] alfabet2 = new String[0];
        if (esBinari) {
            alfabet1 = introduccioAlfabetBinari(alfabetNombre);
        } else {
            alfabet2 = introduccioAlfabetLletres(alfabetNombre);
        }
        veureDades(nombreEstats, estatsFinalsNombre, estatsFinals, alfabetNombre, esBinari, alfabet1, alfabet2);
    }

    private void veureDades(int nombreEstats, int estatsFinalsNombre, int[] estatsFinals, int alfabetNombre, boolean esBinari, int[] alfabet1, String[] alfabet2) {
        print("NOTA: els estats es numeren de la següent manera: 1, 2, 3, ..., n \n");
        print("Nombre d'estats: " + nombreEstats + "\n");
        print("Nombre d'estats finals: " + estatsFinalsNombre + "\n");
        print("Estats finals: " + Arrays.toString(estatsFinals) + "\n");
        print("Nombre de simbols de l'alfabet: " + alfabetNombre + "\n");
        print("Binari?" + esBinari + "\n");
        if (esBinari) {
            print("Alfabet: " + Arrays.toString(alfabet1) + "\n");
        } else {
            print("Alfabet: " + Arrays.toString(alfabet2) + "\n");
        }
    }

    private String[] introduccioAlfabetLletres(int alfabetNombre) {
        String[] alfabet = new String[alfabetNombre];
        for (int i = 0; i < alfabetNombre; i++) {
            alfabet[i] = readLine("Introdueix el símbol " + (i + 1) + ": \n");
        }
        return alfabet;
    }

    private int[] introduccioAlfabetBinari(int alfabetNombre) {
        int[] alfabet = new int[alfabetNombre];
        for (int i = 0; i < alfabetNombre; i++) {
            alfabet[i] = readInt("Introdueix el símbol " + (i + 1) + ": \n");
        }
        return alfabet;
    }

    private int[] introduccioestatsFinals(int nombreFinals) {
        int[] finals = new int[nombreFinals];
        for (int i = 0; i < nombreFinals; i++) {
            finals[i] = readInt("Introdueix l'estat final " + (i + 1) + " \n");
        }
        return finals;
    }
}
