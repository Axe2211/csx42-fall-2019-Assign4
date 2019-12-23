package troubleshootsearch.element;

import java.util.List;
import java.util.ArrayList;

public class TreeNode implements NodeInterface{

    private String content;
    private List<Integer> lineOccurrence;
    private NodeInterface rightChild, leftChild;

    public TreeNode(){
        
        content = null;
        lineOccurrence = new ArrayList<>();
        rightChild = null;
        leftChild = null;
    }

    //set/get methods

    public void setContent(String contentIn){
        content = contentIn;
    }

    public void setLeftChild(NodeInterface nodeIn){
        leftChild = nodeIn;
    }

    public void setRightChild(NodeInterface nodeIn){
        rightChild = nodeIn;
    }

    public void addLineOccurrence(int lineNumIn){
        getLineOccurences().add(lineNumIn);
    }

    public String getContent(){
        return content;
    }

    public NodeInterface getLeftChild(){
        return leftChild;
    }

    public NodeInterface getRightChild(){
        return rightChild;
    }

    public int getWordCount(){
        return getLineOccurences().size();
    }

    public List<Integer> getLineOccurences(){
        return lineOccurrence;
    }    

}