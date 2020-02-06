/**
 * Parent class to all Rotors. Declares the names, the properties and the main methods used for the
 * other Rotors. The Rotor contains mapping that is used to map one letter to another. This is what makes
 * the Enigma messages hard to decrypt.
 */
public abstract class Rotor {

    //Names for the Rotors of the Enigma
    private String[] name = new String[]{"I","II","III","IV","V","ReflectorI","ReflectorII"};
    private int position;                           //Position of the Rotor for the mapping
    private Integer[] mapping = new Integer[25];    //Array for the mappings
    private int ROTORSIZE = 26;                     //The size of all Rotors


    /**
     * Method that gets the name[] containing the name of the rotors.
     * @return name[] with names
     */
    protected String[] getName(){
        return this.name;
    }

    /**
     * Sets the position of the Rotor.
     * @param position of the Rotor
     */
    protected void setPosition(int position){
        this.position = position;
    }


    /**
     * Gets the position of the Rotor.
     * @return position of the Rotor
     */
    protected Integer getPosition(){
        return position;
    }

    /**
     * Method that gets the mapping of the Rotor.
     * @return mapping of this Rotor
     */
    protected Integer[] getMapping(){
        return this.mapping;
    }

    /**
     * Method that sets the mapping of the Rotor.
     * @param mapping [] the array that has the values for the mapping
     */
    protected void setMapping(Integer[] mapping){
        this.mapping = mapping;
    }

    /**
     * Method gives the size of the Rotors.
     * @return the size of the rotors (26)
     */
    protected int getROTORSIZE(){
        return ROTORSIZE;
    }


    /**
     * Abstract method for initialising the Rotors returning
     * the appropriate mapping for a specific Rotor.
     * @param name of the Rotor
     */
    protected abstract void initialise(String name);

    /**
     * Abstract method.
     * Substitutes the appropriate letter using its place in the mapping.
     * @param substitute position of the letter
     * @return the corresponding letter in the mapping[] of the Rotor
     */
    protected abstract Integer substitute(int substitute);


}
