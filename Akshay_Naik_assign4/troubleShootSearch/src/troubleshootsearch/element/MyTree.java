package troubleshootsearch.element;

import troubleshootsearch.util.MyLogger.DebugLevel;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.element.NodeInterface;
import troubleshootsearch.element.TreeNode;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.MyLogger;

public class MyTree implements Element{

    //private DebugLevel debugLevel;
    private NodeInterface root;

    public MyTree(){
        //debugLevel = DebugLevel.MTREE;
        root = null;
    }

    public void buildWordTree(FileProcessor inputFile){

        String[] tokens;
        String currentLine;

        try{
            inputFile.setInputFileHandler(inputFile.getTechFile());

            currentLine = inputFile.readLine();

            for(int lineNo = 1; currentLine != null; lineNo++){
                tokens = currentLine.replaceAll("[^a-zA-Z0-9 ]", "").split(" ");
                for(int tokenNo = 0; tokenNo < tokens.length; tokenNo++){
                    addWord(tokens[tokenNo], lineNo);
                }
                currentLine = inputFile.readLine();
            }
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MyTree.buildWordtree method", DebugLevel.NONE);
        }
        finally{
            inputFile.closeInFile();
        }
    }

    public NodeInterface getRoot(){
        return root;
    }

    public void setRoot(NodeInterface nodeIn){
        root = nodeIn;
    }

    public void accept(VisitorI visitorIn){

        visitorIn.visit(this);
        
    }

    public NodeInterface initiateWordSearch(String wordIn){
        NodeInterface nodeOut = null;
        try{
            nodeOut = searchWord(wordIn, getRoot());
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MyTree.initiateWordSearch method", DebugLevel.NONE);
        }

        return nodeOut;
    }

    public NodeInterface searchWord(String wordIn, NodeInterface nodeIn){
        String wordInUpper = null;
        NodeInterface currentNode = nodeIn;
        try{
            wordInUpper = wordIn.toUpperCase();
            if(currentNode == null){
                return currentNode;
            }
            String currentContentUpper = currentNode.getContent().toUpperCase();
            if(currentContentUpper.equals(wordInUpper)){
                return currentNode;
            }
            else if(currentContentUpper.compareTo(wordInUpper) < 0){
                return searchWord(wordIn, nodeIn.getRightChild());
            }
            else{
                return searchWord(wordIn, nodeIn.getLeftChild());
            }
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MyTree.searchWord method", DebugLevel.NONE);
        }

        return null;
    }

    public void addWord(String wordIn, int lineNumIn){
        String wordInUpper = null;
        NodeInterface currentNode = root, prevNode = root;
        int LRTracker = -1;

        try{
            wordInUpper = wordIn.toUpperCase();
            while(currentNode != null){
                String currentContentUpper = currentNode.getContent().toUpperCase();
            
                if(currentContentUpper.equals(wordInUpper)){
                    currentNode.addLineOccurrence(lineNumIn);
                    break;
                }
                else if(currentContentUpper.compareTo(wordInUpper) < 0){
                    prevNode = currentNode;
                    currentNode = currentNode.getRightChild();
                    LRTracker = 1;
                }
                else{
                    prevNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                    LRTracker = 0;
                }
            }
            
            if(currentNode == null){
                NodeInterface newNode = new TreeNode();
                newNode.setContent(wordIn);
                newNode.addLineOccurrence(lineNumIn);
                if(LRTracker == 1){
                    prevNode.setRightChild((newNode));
                }
                else if(LRTracker == 0){
                    prevNode.setLeftChild(newNode);
                }
                else{
                    setRoot(newNode);
                }
            }
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in MyTree.addWord method", DebugLevel.NONE);
        }
    }
}