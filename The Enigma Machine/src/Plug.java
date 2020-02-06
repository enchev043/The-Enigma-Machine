/**
 * The Plugs are part of the Plugboard. They have 2 ends and each end has a unique letter. They are used to
 * switch letters. If the messages contains a letter which equals one of the Plugs' ends, it is switched with
 * the letter on the other end. This is essential for the Enigma. The Plugs are configured and they contribute
 * to making the messages hard to decrypt.
 */
public class Plug {
    private char endSocket1;            //first end of a Plug
    private char endSocket2;            //second end of a Plug

    /**
     * Constructs a Plug.
     * @param endSocket1 sets the first end of the Plug
     * @param endSocket2 sets the second end of the Plug
     */
    protected Plug(char endSocket1, char endSocket2){
        this.endSocket1 = endSocket1;
        this.endSocket2 = endSocket2;
    }


    /**
     * Gets first end of the Plug.
     * @return endSocket1
     */
    protected char getEnd1(){
        return this.endSocket1;
    }


    /**
     * Gets second end of the Plug.
     * @return endSocket2
     */
    protected char getEnd2(){
        return this.endSocket2;
    }


    /**
     * Sets value for the first end of the Plug.
     * @param endSocket1 the letter to be set as @endSocket
     * @return endSocket1 the new first end of the Plug
     */
    private char setEnd1(char endSocket1){
        return this.endSocket1=endSocket1;
    }


    /**
     * Sets value for the second end of the Plug.
     * @param endSocket2 the letter to be set as @endSocket
     * @return endSocket2 the new second end of the Plug
     */
    private char setEnd2(char endSocket2){
        return this.endSocket2=endSocket2;
    }


    /**
     * Encodes the passed letterIn. If the @letterIn equals one of the @socketEnds, the other @socketEnd is returned.
     * If letterIn doesn't match any of the @socketEnds, the method returns @letterIn.
     * @param letterIn the letter being encoded
     * @return letterIn || endSocket1 || endSocket2
     */
    protected char encode(char letterIn){
        if (letterIn != getEnd1() && letterIn != getEnd2()){
            return letterIn;
        }
        else if(letterIn == getEnd1()){
            return getEnd2();
        }
        else{
            return getEnd1();
        }
    }


    /**
     * Checks if there already exists such connection between plugs which would result in a clash.
     * @param plugin the Plug being checked if it clashes with another Plug
     * @return true || false
     */
    protected boolean clashesWith(Plug plugin){
        boolean clashes;

        if (plugin.getEnd1() == this.endSocket1 || plugin.getEnd2() == this.endSocket2){
            clashes = true;
        }
        else{
            clashes = false;
        }
        return clashes;

    }
}
