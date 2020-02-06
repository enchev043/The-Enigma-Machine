import java.util.ArrayList;

/**
 * The Bombe is a machine that was used to decrypt the messages sent through the German Enigmas. This is the
 * point of this class. It is a test harness for the Enigma and tries different methods to decrypt the hidden
 * messages by computing what is the missing information and tries to complete the messages.
 */
public class Bombe {

    EnigmaMachine enigmaMachine;            //The EnigmaMachine object used by the Bombe for the methods.
    String message;                         //Contains the message which is going to be decoded using the Bombe


    /**
     * Constructor for the Bombe that creates EnigmaMachine and also sets the message.
     * @param RotorI first Rotor for the Enigma
     * @param RotorII second Rotor for the Enigma
     * @param RotorIII third Rotor for the Enigma
     * @param reflector the Reflector of the Enigma
     * @param message the message for the Bombe
     */
    public Bombe(BasicRotor RotorI, BasicRotor RotorII, BasicRotor RotorIII, Reflector reflector, String message){
        createEnigma(RotorI,RotorII,RotorIII,reflector);
        this.message = message;
    }


    /**
     * Set method for the message variable of the class.
     * @param message String representation of the message
     */
    private void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the message of the Bombe.
     * @return String which contains the message which has been set in the Bombe
     */
    private String getMessage() {
        return message;
    }


    /**
     * Method that creates the EnigmaMachine for the Bombe
     * @param RotorI first Rotor for the Enigma
     * @param RotorII second Rotor for the Enigma
     * @param RotorIII third Rotor for the Enigma
     * @param reflector the Reflector of the Enigma
     * @return constructed EnigmaMachine
     */
    private EnigmaMachine createEnigma(BasicRotor RotorI, BasicRotor RotorII, BasicRotor RotorIII, Reflector reflector){

        this.enigmaMachine = new EnigmaMachine(RotorI,RotorII,RotorIII,reflector);

        RotorI.initialise(RotorI.getType());
        RotorII.initialise(RotorII.getType());
        RotorIII.initialise(RotorIII.getType());
        reflector.initialise(reflector.getType());

        return enigmaMachine;
    }


    /**
     * Method that is used by the Bombe to find missing the missing Plugs
     * of the Enigma in the message decoded by the Enigma. Does that by using 2 forLoops
     * to loop through every character possible for both endings of the given first endings
     * of the Plugs. When the word "ANSWER" is contained in the message, the loops break and
     * the Plugs are displayed. The Rotors and Plugs are reset after each cycle.
     * @return ArrayList containing all the Plugs
     */
    public ArrayList<Plug> findPlugs(){

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();       //Array with the alphabet

        String message = getMessage();


        outerloop:                                                          //Label so the forLoops can be stopped.
        for (int i = 0; i < alphabet.length; i++) {

            for (int j = 0; j < alphabet.length-1; j++) {

                enigmaMachine.clearPlugboard();

                enigmaMachine.getPlugboard().addPlug('S', alphabet[j]);
                enigmaMachine.getPlugboard().addPlug('D', 'T');


                String encoded = enigmaMachine.encodeLetters(getMessage());


                enigmaMachine.getRotor(0).setPosition(8);
                enigmaMachine.getRotor(1).setPosition(4);
                enigmaMachine.getRotor(2).setPosition(12);

                if (encoded.contains("ANSWER")){

                    System.out.println(encoded);
                    break outerloop;
                }


            }

        }

        return enigmaMachine.getPlugboard().plugs;

    }


    /**
     * Method that is used by the Bombe to find the missing positions of the Rotors.
     * The method accomplishes that by using 3 forLoops to loop through all possible
     * positions of all 3 Rotors. If the decoded message contains the word "ELECTRIC",
     * the decrypted message is displayed with the missing positions.
     * @return array containing all the missing positions
     */
    public Integer[] findPositions(){

        enigmaMachine.getRotor(0).setPosition(0);
        enigmaMachine.getRotor(1).setPosition(0);
        enigmaMachine.getRotor(2).setPosition(0);

        String message = getMessage();

        Integer[] rotorPositions = new Integer[3];
        
        enigmaMachine.getPlugboard().addPlug('H','L');
        enigmaMachine.getPlugboard().addPlug('G','P');

        outerloop:
        for (int i = 0; i < enigmaMachine.getRotor(0).getROTORSIZE(); i++) {

            for (int j = 0; j < enigmaMachine.getRotor(0).getROTORSIZE(); j++) {

                for (int k = 0; k < enigmaMachine.getRotor(0).getROTORSIZE(); k++) {

                    enigmaMachine.getRotor(0).setPosition(i);
                    enigmaMachine.getRotor(1).setPosition(j);
                    enigmaMachine.getRotor(2).setPosition(k);

                    String encode = enigmaMachine.encodeLetters(message);

                    if (encode.contains("ELECTRIC")){
                        System.out.println(encode);
                        rotorPositions[0] = enigmaMachine.getRotor(0).getPosition();
                        rotorPositions[1] = enigmaMachine.getRotor(1).getPosition();
                        rotorPositions[2] = enigmaMachine.getRotor(2).getPosition();
                        break outerloop;
                    }


                }
            }

        }

        return rotorPositions;
    }


    /**
     * Method that is used by the Bombe to find the missing mappings of the Rotors.
     * The method uses 3 forLoops to try all possible mappings for the 3 Rotors and
     * if the decrypted message contains the word "JAVA", the decrypted message is
     * printed.
     * @return ArrayList containing the mappings of the 3 Rotors.
     */
    public ArrayList<Integer[]> findMapping(){

        ArrayList<Integer[]> mappings = new ArrayList<>();          //ArrayList that would contain the mappings
        String[] name = new String[]{"I","II","III","IV","V"};      //Array with the all possible names for the Rotors
        Integer[] mappingRotorI;
        Integer[] mappingRotorII;
        Integer[] mappingRotorIII;


        outerloop:
        for (int i = 0; i < name.length; i++) {

            enigmaMachine.getRotor(0).initialise(name[i]);

            for (int j = 0; j < name.length; j++) {

                enigmaMachine.getRotor(1).initialise(name[j]);

                for (int k = 0; k < name.length; k++) {

                    enigmaMachine.getRotor(2).initialise(name[k]);


                    String encode = enigmaMachine.encodeLetters(getMessage());
                    System.out.println(encode);


                    if (encode.contains("JAVA")){

                        mappingRotorI = enigmaMachine.getRotor(0).getMapping().clone();
                        mappingRotorII = enigmaMachine.getRotor(1).getMapping().clone();
                        mappingRotorIII = enigmaMachine.getRotor(2).getMapping().clone();

                        mappings.add(mappingRotorI);
                        mappings.add(mappingRotorII);
                        mappings.add(mappingRotorIII);
                        break outerloop;
                    }

                    enigmaMachine.getRotor(0).setPosition(22);
                    enigmaMachine.getRotor(1).setPosition(24);
                    enigmaMachine.getRotor(2).setPosition(23);


                }
            }

        }
        return mappings;

    }

}
