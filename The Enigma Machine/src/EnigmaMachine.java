import java.io.Console;

/**
 * This is the Enigma. This class combines all previous classes and constructs an Enigma. Uses 3 BasicRotors and
 * one Reflector. It has a Plugboard and 3 slots for Rotors. The Enigma decodes messages by passing the letters
 * to the Rotors and Reflectors. On the way in, the letter is switched using the Plugs in the Plugboard. When the
 * letter is carried out, it is switched once again using the Plugs.
 */
public class EnigmaMachine {

    private BasicRotor[] slot = new BasicRotor[3];              //Array of Rotors. The Enigma has 3 Rotors.
    private Reflector Reflector;                                //The Enigma need one Reflector to work.
    private Plugboard plugboard;                                //Plugboard with the plugs.




    /**
     * Constructs and Enigma and creates an empty plugboard.
     * @param RotorI this is the first Rotor
     * @param RotorII this is the second Rotor
     * @param RotorIII this is the third Rotor
     * @param reflector this is the needed Reflector
     */
    public EnigmaMachine(BasicRotor RotorI, BasicRotor RotorII, BasicRotor RotorIII, Reflector reflector){

        addRotor(RotorI,0);
        addRotor(RotorII,1);
        addRotor(RotorIII,2);
        addReflector(reflector);

        if (slot[0] instanceof TurnoverRotor){
            TurnoverRotor turnoverRotor = (TurnoverRotor)slot[0];
            turnoverRotor.setNextRotor(slot[1]);
        }
        if (slot[1] instanceof TurnoverRotor){
            TurnoverRotor turnoverRotor = (TurnoverRotor)slot[1];
            turnoverRotor.setNextRotor(slot[2]);
        }

        plugboard = new Plugboard();

    }


    /**
     * Gets the Plugboard of the Enigma.
     * @return Plugboard with Plugs of the Enigma
     */
    public Plugboard getPlugboard() {
        return plugboard;
    }


    /**
     * Adds a new Plug to the plugboard[] if there isn't already a plug with such endSockets.
     * @param endSocket1 the first end of the Plug
     * @param endSocket2 the second end of the Plug
     */
    public void addPlug(char endSocket1, char endSocket2){
        plugboard.addPlug(endSocket1, endSocket2);
    }


    /**
     * Clears the plugboard.
     */
    public void clearPlugboard(){
        this.plugboard.clear();
    }


    /**
     * Adds a new Rotor to the Enigma.
     * @param rotor the Rotor which is going to be added
     * @param slot the slot in which the Rotor is going to be added to slot[]
     */
    private void addRotor(BasicRotor rotor, int slot){
        if (slot == 0){
            this.slot[0] = rotor;

        }
        else if(slot == 1){
            this.slot[1] = rotor;

        }
        else if(slot == 2){
            this.slot[2] = rotor;

        }
    }


    /**
     * Gets the Rotor from the given slot.
     * @param slot the slot of the Rotor in slot[]
     * @return the Rotor from this slot
     */
    protected BasicRotor getRotor(int slot){

        if(slot == 1){
            return this.slot[0];
        }
        else if(slot == 2){
            return this.slot[1];
        }
        else {
            return this.slot[2];
        }
    }


    /**
     * Gets the slot[] of the Enigma.
     * @return the array of slots[] with Rotors
     */
    protected BasicRotor[] getSlot(){
        return slot;
    }


    /**
     * Adds a Reflector to the Enigma.
     * @param reflector the reflector that is going to be added.
     */
    private void addReflector(Reflector reflector){
        this.Reflector = reflector;
    }


    /**
     * Gets the Reflector used by the Enigma.
     * @return the Reflector of the Enigma
     */
    protected Reflector getReflector(){
        return this.Reflector;
    }


    /**
     * Sets the position of a Rotor from a slot.
     * @param slot the slot of the Rotor in slots[] of the Enigma
     * @param position the position of the Rotor
     */
    protected void setPosition(int slot, int position){
        getRotor(slot).setPosition(position);
    }


    /**
     * Encodes a letter in the Enigma using the Rotors from the different slots and the appropriate methods.
     * @param letter the letter which is going to be encoded
     * @return the encoded letter
     */
    protected char encodeLetter(char letter){

        letter = plugboard.substitute(letter);

        int letterNumber = (int)letter - 'A';


        letterNumber = slot[0].substitute(letterNumber);
        letterNumber = slot[1].substitute(letterNumber);
        letterNumber = slot[2].substitute(letterNumber);

        letterNumber = Reflector.substitute(letterNumber);

        letterNumber = slot[2].substituteBack(letterNumber);
        letterNumber = slot[1].substituteBack(letterNumber);
        letterNumber = slot[0].substituteBack(letterNumber);


        letter = (char)(letterNumber + 'A');

        letter = plugboard.substitute(letter);

        slot[0].rotate();

        return letter;
    }


    /**
     * Encodes a whole message (String) in the Enigma using the Rotors from the different slots and the appropriate methods.
     * @param letters the String representation of the message which is going to be encoded
     * @return the encoded message
     */
    protected String encodeLetters(String letters){

        char[] charLetters = letters.toCharArray();

        for (int i = 0; i < charLetters.length; i++) {

            char letter = charLetters[i];
            letter = encodeLetter(letter);
            charLetters[i] = letter;
        }

        letters = new String(charLetters);
        return letters;
    }


    /**
     * UserInterface using console method. The user creates the Rotors and the Plugs for the Enigma.
     */
    private void UserInterface(){


        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        c.printf("Set the number of plugs");
        String numberOfPlugs = c.readLine();

        for (int i = 0; i <= Integer.parseInt(numberOfPlugs) ; i++) {
            String plugEnd1 = c.readLine("Set first end of the plug: ");
            String plugEnd2 = c.readLine("Set second end of the plug: ");
            this.plugboard.addPlug(plugEnd1,plugEnd2);

        }

        String rotorIType = c.readLine("Set type of the first rotor: ");
        String positionRotorI = c.readLine("Set the position of the first rotor: ");
        BasicRotor rotorI = new BasicRotor(rotorIType,Integer.parseInt(positionRotorI));

        String rotorIIType = c.readLine("Set type of the second rotor: ");
        String positionRotorII = c.readLine("Set the position of the second rotor: ");
        BasicRotor rotorII = new BasicRotor(rotorIIType,Integer.parseInt(positionRotorII));

        String rotorIIIType = c.readLine("Set type of the third rotor: ");
        String positionRotorIII = c.readLine("Set the position of the third rotor: ");
        BasicRotor rotorIII = new BasicRotor(rotorIIIType,Integer.parseInt(positionRotorIII));

        String reflectorType = c.readLine("Set Set type of the first reflector: ");
        Reflector reflector = new Reflector(reflectorType);

        this.addRotor(rotorI,0);
        this.addRotor(rotorII,1);
        this.addRotor(rotorIII,2);
        this.addReflector(reflector);



    }


}
