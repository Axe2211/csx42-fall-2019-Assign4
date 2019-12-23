package troubleshootsearch.element;

import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger.DebugLevel;
import troubleshootsearch.util.MyLogger;
import java.util.Map;
import java.util.HashMap;

public class MySynonyms{

    private Map<String, String> synonym;

    public MySynonyms(){
        synonym = new HashMap<String, String>();
    }

    public String getSynonym(String wordIn){
        String returnString;
        returnString = synonym.get(wordIn);
        return returnString;
    }

    public void setSynonym(String wordIn, String synonymIn){
        synonym.put(wordIn, synonymIn);
        synonym.put(synonymIn, wordIn);
    }

    public void buildSynonyms(FileProcessor inputFile){

        String currentLine = null;
        String[] wordPair = null;
        int counter = 0;

        try{
            inputFile.setInputFileHandler(inputFile.getSynFile());

            currentLine = inputFile.readLine();

            while(currentLine != null){
                counter = counter + 1;
                wordPair = currentLine.replaceAll("[^a-zA-Z0-9=]", "").split("=");
                setSynonym(wordPair[0], wordPair[1]);
                currentLine = inputFile.readLine();
            }
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MySynonyms.buildSynonyms method", DebugLevel.NONE);
        }
        finally{
            inputFile.closeInFile();
        }
    }
}