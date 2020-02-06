import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The purpose of this class is to make printing and reading of messages to and from .txt files possible.
 */
public class EnigmaFile {

    private final static String FILE_NAME = "src\\EnigmaFileInput.txt";             //The directory of the Input file
    private final static String OUTPUT_FILE_NAME = "src\\EnigmaFileOutput.txt";     //The directory of the Output file
    private final static Charset ENCODING = StandardCharsets.UTF_8;                 //The encoding used for the text file.
    private EnigmaMachine enigmaMachine;                                            //Enigma object needed for the methods.


    /**
     * Constructs the class. Need an Enigma Machine.
     * @param enigmaMachine an already created Enigma that is going to be used for the printing and reading of messages.
     */
    public EnigmaFile(EnigmaMachine enigmaMachine){
        this.enigmaMachine = enigmaMachine;
    }


    /**
     * Method that is used to read the Input file.
     * @return Object of type Path that contains the text from the Input file.
     * @throws IOException needed by the Path class
     */
    protected Path readTexts() throws IOException {
        Path path = Paths.get(FILE_NAME);
        return path;
    }


    /**
     * Method that get the Text from the Input file, decoded it and writes it in the Output file.
     * First, it reads the content from the Input file, then it turns the contents into a String
     * and extracts each letter from it and sends it to the Enigma to read it. After the letter is decoded,
     * it is add to an List<Character>. This list is given as argument to the outputText method which
     * writes the contents of the list to the Output file.
     * @throws IOException due to the readText method
     */
    public void encryptText() throws IOException {

        List<Character> enigmaOutput = new ArrayList<>();

        try (Scanner scanner =  new Scanner(readTexts(), ENCODING.name())) {
            while (scanner.hasNextLine()) {

                String enigmaInput = scanner.next();

                for (char c : enigmaInput.toCharArray()) {
                    enigmaOutput.add(enigmaMachine.encodeLetter(c));
                }

            }
        }catch(IOException e) {
            e.fillInStackTrace();
        }

        outputText(enigmaOutput);

    }


    /**
     * Method that prints the messages from the List to the Output file.
     * @param EnigmaOutput List containing the decoded message
     * @throws IOException due to the FileWriter class
     */
    protected void outputText(List<Character> EnigmaOutput) throws IOException {

        FileWriter outputStream = null;

        try {
            outputStream = new FileWriter(OUTPUT_FILE_NAME);


            for (int i = 0; i < EnigmaOutput.size(); i++) {
                outputStream.write((EnigmaOutput.get(i).toString()));
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

}

