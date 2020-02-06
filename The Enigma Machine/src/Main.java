import java.util.Scanner;

public class Main {

    /**
     * Main method of the project. Contains all test methods, including:
     * test1 for BADGER
     * test2 for SNAKE
     * test2.1 for print file (prints SNAKE in EnigmaFileOutput.txt)
     * testTurnoverRotors for testing the TurnoverRotors
     * bombeChallenges
     * @param args empty
     * @throws Exception IOException from the printFile test
     */
    public static void main(String[] args) throws Exception {

       // startTest1();
       // startTest2();
       // startTest2printFile();
       // testTurnoverRotors();
       // bombeChallengeOne();
       // bombeChallengeTwo();
       // bombeChallengeThree();
       // startTest2Extension();


    }


    /**
     * First test of the enigma. The Enigma object is already created and the output of the method
     * should be BADGER.
     */
    private static void startTest1() {
        BasicRotor rotorI = new BasicRotor("I",6);
        BasicRotor rotorII = new BasicRotor("II",12);
        BasicRotor rotorIII = new BasicRotor("III",5);
        Reflector reflector = new Reflector("ReflectorI");
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorI,rotorII,rotorIII,reflector);

        rotorI.initialise(rotorI.getType());
        rotorII.initialise(rotorII.getType());
        rotorIII.initialise(rotorIII.getType());
        reflector.initialise(reflector.getType());

        enigmaMachine.getPlugboard().addPlug('A','M');
        enigmaMachine.getPlugboard().addPlug('G','L');
        enigmaMachine.getPlugboard().addPlug('E','T');

        System.out.println(enigmaMachine.encodeLetters("GFWIQH"));

    }


    /**
     * Second test of the enigma. The Enigma object is already created and the output of the method
     * should be SNAKE.
     */
    private static void startTest2() {
        BasicRotor rotorI = new BasicRotor("IV",23);
        BasicRotor rotorII = new BasicRotor("V",4);
        BasicRotor rotorIII = new BasicRotor("II",9);
        Reflector reflector = new Reflector("ReflectorII");
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorI,rotorII,rotorIII,reflector);

        rotorI.initialise(rotorI.getType());
        rotorII.initialise(rotorII.getType());
        rotorIII.initialise(rotorIII.getType());
        reflector.initialise(reflector.getType());

        enigmaMachine.getPlugboard().addPlug('B','C');
        enigmaMachine.getPlugboard().addPlug('R','I');
        enigmaMachine.getPlugboard().addPlug('S','M');
        enigmaMachine.getPlugboard().addPlug('A','F');

        System.out.println(enigmaMachine.encodeLetters("GACIG"));

    }


    /**
     * This tests the printFile method for the Enigma.
     * It takes input from EnigmaFileInput.txt (found in \src\ of the project)
     * and outputs the encoded message in EnigmaFileOutput.txt (found in \src\ of the project).
     * @throws Exception IOException
     */
    private static void startTest2printFile() throws Exception {
        BasicRotor rotorI = new BasicRotor("IV",23);
        BasicRotor rotorII = new BasicRotor("V",4);
        BasicRotor rotorIII = new BasicRotor("II",9);
        Reflector reflector = new Reflector("ReflectorII");
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorI,rotorII,rotorIII,reflector);

        rotorI.initialise(rotorI.getType());
        rotorII.initialise(rotorII.getType());
        rotorIII.initialise(rotorIII.getType());
        reflector.initialise(reflector.getType());

        enigmaMachine.getPlugboard().addPlug('B','C');
        enigmaMachine.getPlugboard().addPlug('R','I');
        enigmaMachine.getPlugboard().addPlug('S','M');
        enigmaMachine.getPlugboard().addPlug('A','F');

        printFile(enigmaMachine);


    }


    /**
     * A separate method calling the encryptText method from the EnigmaFile class. Separate method for convenience.
     * @param enigmaMachine already created enigmaMachine with properly configured Rotors, Plugs and Reflector
     */
    private static void printFile(EnigmaMachine enigmaMachine){
        EnigmaFile enigmaFile = new EnigmaFile(enigmaMachine);

        try {
            enigmaFile.encryptText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Test method for the TurnoverRotors.
     * Tests if the output is "THE QUICK BROWN FOX JUMPED OVER THE LAZY DOG" (without space).
     */
    private static void testTurnoverRotors() {

        BasicRotor rotorI = new TurnoverRotor("I",23);
        BasicRotor rotorII = new TurnoverRotor("II",11);
        BasicRotor rotorIII = new TurnoverRotor("III",7);
        Reflector reflector = new Reflector("ReflectorI");
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorI,rotorII,rotorIII,reflector);

        rotorI.initialise(rotorI.getType());
        rotorII.initialise(rotorII.getType());
        rotorIII.initialise(rotorIII.getType());
        reflector.initialise(reflector.getType());

        enigmaMachine.getPlugboard().addPlug('Q','F');


        System.out.println(enigmaMachine.encodeLetters("OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT"));

    }


    /**
     * First test of the Bombe. Tries to find the missing plugs. Prints plugs at the end. Prints decrypted message.
     */
    private static void bombeChallengeOne(){

        BasicRotor rotorI = new BasicRotor("IV",8);
        BasicRotor rotorII = new BasicRotor("III",4);
        BasicRotor rotorIII = new BasicRotor("II",21);
        Reflector reflector = new Reflector("ReflectorI");

        Bombe bombe = new Bombe(rotorI,rotorII,rotorIII,reflector,"JBZAQVEBRPEVPUOBXFLCPJQSYFJI");
        for (Plug plug : bombe.findPlugs()) {
            System.out.println(plug.getEnd1() + " and " + plug.getEnd2());
        }

    }


    /**
     * Second test of the Bombe. Tries to find the proper positions of the rotors. Prints decrypted message.
     */
    private static void bombeChallengeTwo(){
        BasicRotor rotorI = new BasicRotor("V",0);
        BasicRotor rotorII = new BasicRotor("III",0);
        BasicRotor rotorIII = new BasicRotor("II",0);
        Reflector reflector = new Reflector("ReflectorI");

        Bombe bombe = new Bombe(rotorI,rotorII,rotorIII,reflector,"AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD");

        bombe.enigmaMachine.addPlug('H','L');
        bombe.enigmaMachine.addPlug('G','P');


        for (Integer position : bombe.findPositions()){
            System.out.println(position);
        }


    }


    /**
     * Third test of the Bombe. Tries to find the correct mappings for the rotors. TRIES to print decrypted message.
     */
    private static void bombeChallengeThree(){

        BasicRotor rotorI = new BasicRotor("V",22);
        BasicRotor rotorII = new BasicRotor("III",24);
        BasicRotor rotorIII = new BasicRotor("II",23);
        Reflector reflector = new Reflector("ReflectorI");


        Bombe bombe2 = new Bombe(rotorI,rotorII,rotorIII,reflector,"WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW");

        bombe2.enigmaMachine.getPlugboard().addPlug('M','F');
        bombe2.enigmaMachine.getPlugboard().addPlug('O','I');


        for (Integer[] mappings : bombe2.findMapping()){
            System.out.println(mappings.length);
            System.out.println("No");
        }


    }


    /**
     * Extension of the Enigma Machine which is designed to allow user input for setting the message, which
     * should be decoded. Outputs the corresponding encoding of each typed character
     * @param enigmaMachine
     */
    private static void ExtensionUserInput(EnigmaMachine enigmaMachine)  {

        System.out.print("Set the lenght of message: ");
        System.out.println();
        System.out.println();

        Character userChar = 'A';
        Integer lenghtOfMessage = 0;
        lenghtOfMessage = userInput(lenghtOfMessage);
        char[] decodedMessage = new char[lenghtOfMessage];

        int counter = 0;
        do {
            System.out.print("ENTRY LETTER: ");
            userChar = userInput(userChar);

            System.out.print("OUTPUT LETTER: ");
            decodedMessage[counter] = enigmaMachine.encodeLetter(userChar);

            System.out.println(decodedMessage[counter]);
            System.out.println();

            counter++;

        } while (counter != lenghtOfMessage);

        System.out.print("Decoded message: ");
        String message = new String(decodedMessage);
        System.out.println(message);

    }


    /**
     * Method that uses the Extension with the second test.
     */
    private static void startTest2Extension() {
        BasicRotor rotorI = new BasicRotor("IV",23);
        BasicRotor rotorII = new BasicRotor("V",4);
        BasicRotor rotorIII = new BasicRotor("II",9);
        Reflector reflector = new Reflector("ReflectorII");
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorI,rotorII,rotorIII,reflector);

        rotorI.initialise(rotorI.getType());
        rotorII.initialise(rotorII.getType());
        rotorIII.initialise(rotorIII.getType());
        reflector.initialise(reflector.getType());

        enigmaMachine.getPlugboard().addPlug('B','C');
        enigmaMachine.getPlugboard().addPlug('R','I');
        enigmaMachine.getPlugboard().addPlug('S','M');
        enigmaMachine.getPlugboard().addPlug('A','F');

        ExtensionUserInput(enigmaMachine);


    }


    /**
     * Simple user input method. Used for char.
     * @param input variable that contains char entered by the operator, which represents a letter in the message
     * @return the entered character
     */
    private static char userInput(char input){
        Scanner scan = new Scanner(System.in);
        input = scan.next().charAt(0);
        return input;
    }


    /**
     * Simple user input method. Used for int.
     * @param input variable that contains char entered by the operator, which represents a letter in the message
     * @return the entered character
     */
    private static Integer userInput(Integer input) {
        Scanner scan = new Scanner(System.in);
        input = Integer.parseInt(scan.next());
        return input;
    }

}
