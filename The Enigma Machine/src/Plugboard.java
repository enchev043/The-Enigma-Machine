import java.util.ArrayList;

/**
 * The Plugboard is a main part in the Enigma and it contains the Plugs. The Enigma uses the Plugboard at the start
 * and at the end of each decoding to switch any letters, if they are contained in the Plugs.
 */
public class Plugboard {


    ArrayList<Plug> plugs = new ArrayList<>(13);        //Array for the plugs


    /**
     * Tries to add a new Plug and checks if there is already a Plug that clashes with the new Plug.
     * @param endSocket1 first end of the Plug
     * @param endSocket2 second end of the Plug
     * @return true if it added a plug || false if it clashed and didn't add a plug
     */
    protected boolean addPlug(char endSocket1, char endSocket2) {
        Plug newPlug = new Plug(endSocket1, endSocket2);

        for (int i = 0; i < getNumPlugs(); i++) {

            if (plugs.get(i).clashesWith(newPlug)) {
                return false;
            }
        }
        plugs.add(newPlug);
        return true;
    }


    /**
     * Overloaded method for addPlug that uses Strings for Plugs.
     * Needed for convenience when returned typed is String and not char.
     * @param endSocket1 first end of the Plug
     * @param endSocket2 second end of the Plug
     * @return true if it added a plug || false if it clashed and didn't add a plug
     */
    protected boolean addPlug(String endSocket1, String endSocket2) {

        char charSocket1 = endSocket1.charAt(0);
        char charSocket2 = endSocket2.charAt(0);

        Plug newPlug = new Plug(charSocket1, charSocket2 );

        for (int i = 0; i < getNumPlugs(); i++) {

            if (plugs.get(i).clashesWith(newPlug)) {
                return false;
            }
        }
        plugs.add(newPlug);
        return true;
    }


    /**
     * Gets the number of plugs.
     * @return int length of Plugs[]
     */
    protected int getNumPlugs(){
        return plugs.size();
    }


    /**
     * Empties Plug[]
     */
    protected void clear(){
        plugs.clear();
    }


    /**
     * Encodes the input letter, if there exists a possible encoding. If not, it returns the input @letterIn.
     * @param letterIn the letter being encoded
     * @return letterIn the encoded letter
     */
    protected char substitute(char letterIn){

        for (int i = 0; i < plugs.size(); i++){
            letterIn = plugs.get(i).encode(letterIn);
        }
        return letterIn;
    }

}
