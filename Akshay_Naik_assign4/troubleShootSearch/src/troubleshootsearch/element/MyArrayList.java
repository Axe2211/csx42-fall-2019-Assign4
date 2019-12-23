package troubleshootsearch.element;

import java.util.List;
import java.util.ArrayList;

import troubleshootsearch.util.MyLogger.DebugLevel;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.util.FileProcessor;

public class MyArrayList implements Element{

    //private DebugLevel debugLevel;

    private List<String> info;
    private MySynonyms synonymRef;

    public MyArrayList(){
        //debugLevel = DebugLevel.MARRL;
        info = new ArrayList<String>();
    }

    //get and set methods
    public List<String> getInfo(){
        return info;
    }

    public MySynonyms getSynonyms(){
        return synonymRef;
    }

    public void setSynonyms(MySynonyms synonymsIn){
        synonymRef = synonymsIn;
    }

    public void buildArrayList(FileProcessor inputFile){

        String currentLine = null;
        
        try{
            inputFile.setInputFileHandler(inputFile.getTechFile());
            currentLine = inputFile.readLine();

            while(currentLine != null){
                getInfo().add(currentLine);
                currentLine = inputFile.readLine();
            }
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MyArrayList.buildArrayList method", DebugLevel.NONE);
        }
        finally{
            inputFile.closeInFile();
        }
    }

    public void addInfo(String infoIn){
        getInfo().add(infoIn);
    }
    
    public void accept(VisitorI visitorIn){
        visitorIn.visit(this);
    }
}