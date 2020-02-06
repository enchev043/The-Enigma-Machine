/**
 * The basic Rotors used for the Enigma. The machine uses 3 BasicRotors, positioned in different slots and on
 * different positions. There are 5 different BasicRotors and they are initialised when the Rotor is constructed.
 */
public class BasicRotor extends Rotor {


    private String type;                //The type of the BasicRotor so a mapping can be assigned.


    /**
     * Constructs a BasicRotor.
     * @param type of the Rotor(I, II, III, IV, V)
     */
    public BasicRotor(String type, int position){
        this.type = type;
        setPosition(position);
    }


    /**
     * Gets the type of the Rotor.
     * @return type of the Rotor
     */
    protected String getType(){
        return this.type;
    }


    /**
     * Initialises a Rotor by filling the mapping[] of the Rotor.
     * @param type takes the type of Rotor and then fills it with the corresponding mapping[].
     */
    protected void initialise(String type) {
        Integer[] I = new Integer[]{ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14,
                22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };

        Integer[] II = new Integer[]{ 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22,
                19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };

        Integer[] III = new Integer[]{ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25,
                13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };

        Integer[] IV = new Integer[]{4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17,
                7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };

        Integer[] V = new Integer[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13,
                7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };

        if (type.equals(this.getName()[0])){
            setMapping(I);
        }
        else if (type.equals(this.getName()[1])){
            setMapping(II);
        }
        else if (type.equals((this.getName()[2]))){
            setMapping(III);
        }
        else if (type.equals((this.getName()[3]))){
            setMapping(IV);
        }
        else {
            setMapping(V);
        }
    }


    /**
     * Takes a letter as an Integer and substitutes it with an Integer from the mapping[] using
     * the substitute method.
     * Returns the new value of the passed letter.
     * @param letter Integer representation of a letter, which will be substituted
     * @return the substituted letter
     */
    @Override
    protected Integer substitute(int letter) {

        letter = makeSubstitution(getMapping(), letter);
        return letter;

    }


    /**
     * Substitutes the passed letter(int) with the value from the mapping[]. It takes into account
     * the position of the rotor by subtracting the position of the rotor from the letter, so it
     * we rotate the rotor. If the value is more <0 add ROTORSIZE to rotate back the rotor. Then
     * the number is mapped from the mapping[] and the position of rotor is added again to compensate
     * for the first subtraction. If the number is >= ROTORSIZE, subtract ROTORSIZE to find the number.
     * @param mapping the mapping[] from the Rotor
     * @param substitute the passed letter to be substituted
     * @return
     */
    private int makeSubstitution(Integer[] mapping, int substitute) {

        int substitutePosition = substitute - getPosition();

        if (substitutePosition < 0) {
            substitutePosition += getROTORSIZE();
        }

        int mapped = mapping[substitutePosition];
        int rotated = mapped + getPosition();

        if (rotated >= getROTORSIZE()){
            rotated -= getROTORSIZE();
        }

        return rotated;
    }


    /**
     * Changes the passed Integer to a value from the mapping[].
     * @param substitute Integer representation of the letter being substituted again
     * @return the new value of switchBack(new letter)
     */
    protected Integer substituteBack(int substitute){

        substitute = makeSubstitution(inverseMapping(), substitute);
        return substitute;

    }


    /**
     * Inverts the mapping of the rotor by applying mapping[x] = y and inverseMapping[y] = x
     * @return inverseMapping[] with the inversed mapping of the Rotor
     */
    private Integer[] inverseMapping(){

        Integer[] inverseMapping = getMapping().clone();

        for (int i = 0; i < getMapping().length; i++) {
            inverseMapping[getMapping()[i]] = i;
        }

        return inverseMapping;
    }


    /**
     * Rotates the rotor by 1 position. If the rotor is at the last position,
     * the position is set to 0.
     * If the rotor is a TurnoverRotor, the method checks what is the type of the TurnoverRotor
     * and if the position of the TurnoverRotor equals the RotorTurnoverPosition, the method also
     * rotates the Rotor in the next slot of the Enigma. If the Rotor is at slot 2 (from 0-2),
     * no Rotor is rotated even if the TurnoverRotor is at TurnoverPosition.
     */
    protected void rotate(){
        setPosition(getPosition()+1);
        if (this instanceof TurnoverRotor){

            if (this.getType().equals("I")){
                TurnoverRotor rotateRotor = (TurnoverRotor) this;
                if(getPosition() == rotateRotor.getTurnoverPosition()) rotateRotor.getNextRotor().rotate();
            }
            if (this.getType().equals("II")){
                TurnoverRotor rotateRotor = (TurnoverRotor) this;
                if(getPosition() == rotateRotor.getTurnoverPosition()) rotateRotor.getNextRotor().rotate();
            }
            if (this.getType().equals("III")){
                TurnoverRotor rotateRotor = (TurnoverRotor) this;
                if(getPosition() == rotateRotor.getTurnoverPosition()) rotateRotor.getNextRotor().rotate();
            }
            if (this.getType().equals("IV")){
                TurnoverRotor rotateRotor = (TurnoverRotor) this;
                if(getPosition() == rotateRotor.getTurnoverPosition()) rotateRotor.getNextRotor().rotate();
            }
            if (this.getType().equals("V")){
                TurnoverRotor rotateRotor = (TurnoverRotor) this;
                if(getPosition() == rotateRotor.getTurnoverPosition()) rotateRotor.getNextRotor().rotate();
            }


        }
        if (getPosition() > getROTORSIZE()){
            setPosition(1);
        }
    }

}

