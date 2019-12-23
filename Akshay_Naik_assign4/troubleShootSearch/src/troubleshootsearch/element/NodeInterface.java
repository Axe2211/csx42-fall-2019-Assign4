package troubleshootsearch.element;

import java.util.List;

public interface NodeInterface{

    //set/get methods

    public void setContent(String contentIn);

    public void setLeftChild(NodeInterface nodeIn);

    public void setRightChild(NodeInterface nodeIn);

    public void addLineOccurrence(int lineNumIn);

    public String getContent();

    public NodeInterface getLeftChild();

    public NodeInterface getRightChild();

    public int getWordCount();

    public List<Integer> getLineOccurences();

}