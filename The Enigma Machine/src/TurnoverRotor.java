/**
 * The TurnoverRotors are the most complicated Rotors of the Enigma. Each TurnoverRotor has it's own TurnoverPosition
 * which rotates the Rotor in the next slot of the Enigma. This further ensures that the Enigma is unreadable for
 * anyone else, who doesn't have the correct settings.
 */
public class TurnoverRotor extends BasicRotor {

    private int turnoverPosition = 0;           //Specific TurnoverPosition for each TurnoverRotor
    private BasicRotor nextRotor;               //Variable that contains the Rotor right of this Rotor

    /**
     * Constructor for the TurnoverRotor. Uses the constructor of the BasicRotor, but also
     * needs some new parameters. Depending on the type of the Rotor, appropriate TurnoverPosition is set.
     * @param type the type of the Rotor so mapping can be assigned
     * @param position  the starting position of the Rotor
     */
    public TurnoverRotor(String type, int position){
        super(type,position);

        if (type.equals("I")){
            this.turnoverPosition = 24;
        }
        else if (type.equals("II")){
            this.turnoverPosition = 12;
        }
        else if (type.equals("III")){
            this.turnoverPosition = 3;
        }
        else if (type.equals("VI")){
            this.turnoverPosition = 17;
        }
        else {
            this.turnoverPosition = 7;
        }

    }

    /**
     * Method that sets the nextRotor.
     * @return nextRotor for this TurnoverRotor
     */
    protected BasicRotor getNextRotor() {
        return nextRotor;
    }


    /**
     * Sets the nextRotor.
     * @param nextRotor the Rotor right of this Rotor
     */
    protected void setNextRotor(BasicRotor nextRotor) {
        this.nextRotor = nextRotor;
    }


    /**
     * Gets the TurnoverPosition of this Rotor.
     * @return the TurnoverPosition of the Rotor
     */
    protected int getTurnoverPosition() {
        return turnoverPosition;
    }

}
