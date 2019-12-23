package troubleshootsearch.util;

import java.util.List;

import troubleshootsearch.util.MyLogger.DebugLevel;

import java.util.ArrayList;
import java.util.Iterator;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    List<String> result;

    public Results(){
        result = new ArrayList<>();
    }

    public List<String> getResult(){
        return result;
    }

    public void writeToResult(List<String> inputIn){
        getResult().addAll(inputIn);
    }

    public void writeToResult(String inputIn){
        getResult().add(inputIn);
    }

        //interface methods
        public void writeToFile(FileProcessor outFile){

            try{
                MyLogger.writeMessage("Results: writeToFile method invoked", DebugLevel.RESULTS);
                Iterator<String> resultIterator = getResult().listIterator();

                outFile.setOutputFileHandler(outFile.getOutFile());
        
                while(resultIterator.hasNext()){
                    outFile.writeFile((String)resultIterator.next());
                }
                MyLogger.writeMessage("Results: writeToFile method conclued", DebugLevel.RESULTS);
            }
            catch(Exception ex){
                MyLogger.writeMessage("Encountered Error: " + ex + " in Results.writeToFile method", DebugLevel.NONE);
                ex.printStackTrace();
                System.exit(1);
            }

            outFile.closeOutFile();
        }
    
        public void writeToStdOut(){
    
            Iterator<String> resultIterator = getResult().listIterator();
            MyLogger.setDebugValue(DebugLevel.RESULTS);
            try{
                while(resultIterator.hasNext()){
                    MyLogger.writeMessage((String) resultIterator.next(), DebugLevel.RESULTS);
                }
            }
            catch(Exception ex){
                MyLogger.setDebugValue(DebugLevel.NONE);
                MyLogger.writeMessage("Encountered Error: " + ex + " in Result.writeToStdOut method", DebugLevel.NONE);
                ex.printStackTrace();
                System.exit(1);
            }
        }
	
}
