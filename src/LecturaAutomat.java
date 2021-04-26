import acm.program.CommandLineProgram;


import java.util.Arrays;

public class LecturaAutomat extends CommandLineProgram {
    /*Ansi color codes*/
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /*Main function*/
    public void run() {
        System.out.println(ANSI_CYAN + "---------- Data reading ----------");
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "NOTE: the states are numbered as follows: 0, 1, 2, ..., n");

        /*States number introduction*/
        int statesNumber = readInt(ANSI_RESET + "Enter the number of states of the automaton: \n");

        /*Final states number introduction*/
        int finalStatesNumber = readInt("How many finals states has the automaton? \n");

        /*Final states introduction*/
        int[] finalStates = finalStatesIntroduction(finalStatesNumber);

        /*Introduction if the number of symbols of the alphabet*/
        int AlphabetSymbolsNumber = readInt("How many symbols has the alphabet? \n");

        /*Alphabet introduction*/
        String[] alphabet = alphabetIntroduction(AlphabetSymbolsNumber);

        /*Introduction of the transition function*/
        int[][] transitionFunction = transitionFunctionMethod(statesNumber, AlphabetSymbolsNumber, alphabet);

        /*Data viewing*/
        System.out.println(ANSI_CYAN + "---------- Data viewing ----------" + ANSI_RESET);
        dataViewing(statesNumber, finalStatesNumber, finalStates, AlphabetSymbolsNumber, alphabet, transitionFunction);

        /*Process of demand of the reading word*/
        String word = readLine("Write a word to enter into the automaton: ");

        /*Word treatment*/
        System.out.println(ANSI_CYAN + "---------- Processing the word ----------");
        System.out.println(ANSI_YELLOW + "---------- Steps of the automaton ----------" + ANSI_RESET);
        int lastState = wordReading(word, transitionFunction, alphabet);
        System.out.println(ANSI_CYAN + "---------- Execution results ----------");
        if (wordAcceptation(lastState, finalStates)) {
            System.out.println(ANSI_GREEN + "The word: '" + word + "' has been accepted.");
        } else {
            System.out.println(ANSI_RED + "The word: '" + word + "' hasn't been accepted.");
        }

    }

    /**
     * This function reads the introduced word
     **/
    private int wordReading(String word, int[][] transitionFunction, String[] alphabet) {
        int currentState = 0; //This variable will save the actual state
        String symbol; //This String will content the symbol read
        int nextState; //This variable will save the next state
        for (int i = 0; i < word.length(); i++) { //While the word has symbols to read
            try {
                symbol = word.substring(i, i + 1); //Substring of the introduced word => Only 1 letter/symbol
                int symbolIndex = index(alphabet, symbol); //Obtaining the symbol index in the alphabet array
                nextState = transitionFunction[currentState][symbolIndex]; //Searching the new state
                System.out.println(ANSI_PURPLE + "Actual state: " + ANSI_RESET + currentState + ANSI_PURPLE + ", symbol read: " + ANSI_RESET + symbol + ANSI_PURPLE + ", next state: " + ANSI_RESET + nextState);
                currentState = nextState; //currentState updating
            } catch (Exception e) {
                throw new IllegalArgumentException("Transition not defined"); //If the transition is not defined and the introduced word needs that transition the program throws an IllegalStateException with the message "Transition not defined"
            }

        }
        System.out.println(ANSI_PURPLE + "The automaton ends in the state: " + ANSI_RESET + currentState);
        return currentState;
    }

    /**
     * This function decides if the word has to be accepted
     **/
    private boolean wordAcceptation(int currentState, int[] finalStates) {
        for (int finalState : finalStates) { //This loop is equivalent to: for (int i = 0; i < finalStates.length; i++)
            if (currentState == finalState) { //if (currentState == finalStates[i]){...}
                return true;
            }
        }
        return false;
    }

    /**
     * This word returns the index of the symbol in the alphabet array
     **/
    private int index(String[] alphabet, String symbol) {
        int index = 0; //index of the symbol in the alphabet
        for (int i = 0; i < alphabet.length; i++) { //Traversing the alphabet
            if (alphabet[i].equalsIgnoreCase(symbol)) { //If the symbol read is equal to one of the symbols of the alphabet...
                index = i; //Saving the index
            }
        }
        return index;
    }

    /**
     * This function returns a matrix with the transition function
     **/
    private int[][] transitionFunctionMethod(int statesNumber, int AlphabetSymbolsNumber, String[] alphabet) {
        int[][] transitionFunctionVariable = new int[statesNumber][AlphabetSymbolsNumber]; //Matrix that will content the transition function
        System.out.println(ANSI_BOLD + ANSI_BLUE + "If any transition is not defined, write -1 on this way if you introduce a word that needs that transition the program will throw an exception" + ANSI_RESET);
        for (int i = 0; i < statesNumber; i++) { //Traversing the matrix while the user introduces the pertinent values of the transition function
            for (int j = 0; j < AlphabetSymbolsNumber; j++) {
                transitionFunctionVariable[i][j] = readInt("From the state " + (i) + " and reading the symbol " + "'" + alphabet[j] + "'" + " we go to the state => ");
            }
        }
        return transitionFunctionVariable;
    }

    /**
     * This function prints the data
     **/
    private void dataViewing(int statesNumber, int finalStatesNumber, int[] finalStates, int AlphabetSymbolsNumber, String[] alphabet, int[][] transitionFunctionVariable) {
        System.out.println(ANSI_PURPLE + "Number of states: " + ANSI_RESET + statesNumber);
        System.out.println(ANSI_PURPLE + "Number of final states: " + ANSI_RESET + finalStatesNumber);
        System.out.println(ANSI_PURPLE + "Final states: " + ANSI_RESET + Arrays.toString(finalStates));
        System.out.println(ANSI_PURPLE + "Number of symbols of the alphabet: " + ANSI_RESET + AlphabetSymbolsNumber);
        System.out.println(ANSI_PURPLE + "Alphabet: " + ANSI_RESET + Arrays.toString(alphabet));
        System.out.println(ANSI_PURPLE + "Transition function: ");
        for (int i = 0; i <= finalStatesNumber; i++) { //Printing the header of the matrix (alphabet symbols)
            print(ANSI_GREEN + "\t" + alphabet[i] + "  ");
        }
        print("\n");
        for (int i = 0; i < statesNumber; i++) { //Printing the transition function matrix
            print(ANSI_GREEN + i + "\t"); //Printing the number of states
            for (int j = 0; j < AlphabetSymbolsNumber; j++) {
                print(ANSI_RESET + transitionFunctionVariable[i][j] + "\t");
            }
            print("\n");
        }

    }

    /**
     * This function returns an array with the alphabet
     **/
    private String[] alphabetIntroduction(int AlphabetSymbolsNumber) {
        String[] alphabet = new String[AlphabetSymbolsNumber]; //Array that will content the alphabet
        for (int i = 0; i < AlphabetSymbolsNumber; i++) { //Traversing the alphabet while the user introduces the pertinent values of the alphabet symbols
            alphabet[i] = readLine("Introduce the symbol " + (i + 1) + ": \n");
        }
        return alphabet;
    }


    /**
     * This function returns an array with the final states
     **/
    private int[] finalStatesIntroduction(int nombreFinals) {
        int[] finals = new int[nombreFinals]; //Array that will content the final states
        for (int i = 0; i < nombreFinals; i++) { //Asking for final states
            finals[i] = readInt("Introduce the final state " + (i + 1) + " \n");
        }
        return finals;
    }


}
