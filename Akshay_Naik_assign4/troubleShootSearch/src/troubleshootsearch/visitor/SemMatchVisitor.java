package troubleshootsearch.visitor;

import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.MyLogger.DebugLevel;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class SemMatchVisitor extends Visitor{

    public SemMatchVisitor(){}

    public void writeToResult(List<String> inputIn){
        getResultRef().writeToResult(inputIn);
    }

    public void visit(MyArrayList arrayListIn){
        String[] tokenizedKeyPhrase = null;
        int last_element;
        String synonym;
        String newKeyPhrase;
        
        try{
            MyLogger.writeMessage("Semantic Match: visit method invoked", DebugLevel.MATCHLEVEL);
            tokenizedKeyPhrase = getKeyPhrase().split(" ");
            last_element = tokenizedKeyPhrase.length - 1;
            synonym = arrayListIn.getSynonyms().getSynonym(tokenizedKeyPhrase[last_element]);
            if(synonym == null){
                newKeyPhrase = new String("-1Syn");
            }
            else{
                newKeyPhrase = getKeyPhrase().replace(tokenizedKeyPhrase[last_element], synonym).toUpperCase();
            }
            visitSub(arrayListIn, newKeyPhrase);
            MyLogger.writeMessage("Semantic Match: visit method concluded", DebugLevel.MATCHLEVEL);
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in SemMatchVisitor.visit method", DebugLevel.NONE);
        }
    }

    private void visitSub(MyArrayList arrayListIn, String keyPhraseIn){
        List<String> result = new ArrayList<>();
        String currentLine;
        int searchResult = 0;
        int lineCounter = 0;

        try{
            result.add(new String("Semantic Match"));
            result.add(new String("--------------"));

            MyLogger.writeMessage("Semantic Match: visitSub method invoked", DebugLevel.MATCHLEVEL);
            if(keyPhraseIn.equals("-1Syn")){
                result.add(new String("No semantic match"));
            }
            else{
                Iterator<String> infoItr = arrayListIn.getInfo().listIterator();
                while(infoItr.hasNext()){
                    lineCounter = lineCounter + 1;
                    currentLine = (String)infoItr.next();
                    if(currentLine.toUpperCase().contains(keyPhraseIn)){
                        result.add(new String(lineCounter + ". " + currentLine));
                        searchResult = searchResult + 1;
                    }
                }
            }

            result.add("\n");
            writeToResult(result);
            MyLogger.writeMessage("Semantic Match: visitSub method concluded", DebugLevel.MATCHLEVEL);
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in ExactMatchVisitor.vist method", DebugLevel.NONE);
        }
    }
}