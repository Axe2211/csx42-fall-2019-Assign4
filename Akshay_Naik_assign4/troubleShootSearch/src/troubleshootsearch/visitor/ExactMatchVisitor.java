package troubleshootsearch.visitor;

import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.MyLogger.DebugLevel;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ExactMatchVisitor extends Visitor{

    public ExactMatchVisitor(){}

    public void writeToResult(List<String> inputIn){
        super.getResultRef().writeToResult(inputIn);
    }

    public void visit(MyArrayList arrayListIn){
        List<String> result = new ArrayList<>();
        
        String currentLine;
        String keyPhraseUpper;
        int searchResult = 0;
        int lineCounter = 0;

        try{
            MyLogger.writeMessage("Exact Match: visit method invoked", DebugLevel.MATCHLEVEL);
            result.add(new String("Exact Match"));
            result.add(new String("-----------"));
            keyPhraseUpper = getKeyPhrase().toUpperCase();

            Iterator<String> infoItr = arrayListIn.getInfo().listIterator();
            while(infoItr.hasNext()){
                currentLine = (String)infoItr.next();
                lineCounter = lineCounter + 1;
                if(currentLine.toUpperCase().contains(keyPhraseUpper)){
                    result.add(new String(lineCounter + ". " + currentLine));
                    searchResult = searchResult + 1;
                }
            }
            if(searchResult == 0){
                result.add(new String("No exact match"));
            }
            result.add("\n");
            writeToResult(result);
            MyLogger.writeMessage("Exact Match: visit method concluded", DebugLevel.MATCHLEVEL);
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in ExactMatchVisitor.vist method", DebugLevel.NONE);
        }
    }
}