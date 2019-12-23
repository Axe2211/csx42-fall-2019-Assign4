package troubleshootsearch.visitor;

import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.MyLogger.DebugLevel;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.util.Results;

public abstract class Visitor implements VisitorI{

    private String keyPhrase;
    private Results resultRef;

    public void visit(MyArrayList arrayListIn){
        MyLogger.writeMessage("Abstract Class implementation for: MyArrayList", DebugLevel.NONE);
    }

    public void visit(MyTree treeIn){
        MyLogger.writeMessage("Abstract Class implementation for: MyTree", DebugLevel.NONE);
    }

    public void setKeyPhrase(String keyPhraseIn){
        keyPhrase = keyPhraseIn;
    }

    public void setResultRef(Results resultIn){
        resultRef = resultIn;
    }

    public String getKeyPhrase(){
        return keyPhrase;
    }

    public Results getResultRef(){
        return resultRef;
    }

    // public void setDebugLevel(DebugLevel debugLevelIn){
    //     debugLevel = debugLevelIn;
    // }
}