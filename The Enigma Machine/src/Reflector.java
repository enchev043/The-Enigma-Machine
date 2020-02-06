/**
 * The most simple Rotor. It can not rotate and is always at position 0. There are 2 main types of Reflectors
 * and only one of them is used in the Enigma. The Reflector reflects the letter after it has gone through
 * each Rotor.
 */
public class Reflector extends Rotor {

    String type;                //Reflectors can either be of type I or II

    /**
     * Constructor for the reflector.
     * @param type the type of the Reflector which is going to be used for the mapping.
     */
    public Reflector(String type){
        this.type = type;
    }


    /**
     * Gets the type of the Reflector.
     * @return String representation of the type
     */
    protected String getType() {
        return type;
    }


    /**
     * Sets the type of the Reflector.
     * @param type String representation of the type
     */
    protected void setType(String type) {
        this.type = type;
    }


    /**
     * Fills the mapping of either Reflector using its name.
     * @param type of the Reflector
     */
    @Override
    protected void initialise(String type) {
        Integer[] ReflectorI = new Integer[]{ 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14,
                10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
        Integer[] ReflectorII = new Integer[]{ 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22,
                6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };

        if (type.equals(this.getName()[5])){
            setMapping(ReflectorI);
        }
        else{
            setMapping(ReflectorII);
        }
    }


    /**
     * Returns the number corresponding to that element in the mapping[]
     * @param substitute position of the number(letter)
     * @return the number in mapping[] corresponding to the passed number
     */
    @Override
    protected Integer substitute(int substitute) {
        return getMapping()[substitute];
    }
}
