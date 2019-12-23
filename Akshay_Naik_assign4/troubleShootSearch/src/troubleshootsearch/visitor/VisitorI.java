package troubleshootsearch.visitor;

import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MyTree;
import troubleshootsearch.util.Results;

public interface VisitorI{

    public void visit(MyArrayList arrayListIn);
    public void visit(MyTree treeIn);
    public void setKeyPhrase(String keyPhraseIn);
    public void setResultRef(Results resultIn);
    public String getKeyPhrase();
    public Results getResultRef();
    
}