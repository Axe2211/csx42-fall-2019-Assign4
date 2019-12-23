package troubleshootsearch.visitor;

import troubleshootsearch.util.MyLogger;
import troubleshootsearch.util.MyLogger.DebugLevel;

import java.util.ArrayList;
import java.util.List;

import troubleshootsearch.element.MyTree;
import troubleshootsearch.element.TreeNode;
import troubleshootsearch.element.NodeInterface;

public class NaiveStemVisitor extends Visitor{


    public NaiveStemVisitor(){}

    public void writeToResult(List<String> inputIn){
        super.getResultRef().writeToResult(inputIn);
    }

    public void visit(MyTree treeIn){
        //MyLogger.writeMessage("This is the MyTree class", debugLevel);
        List<String> result;
        NodeInterface checkerNode;

        try{
            MyLogger.writeMessage("Naive Stem: visit method invoked", DebugLevel.MATCHLEVEL);
            result = new ArrayList<>();
            result.add("Naive Stemming Match");
            result.add("--------------------");
            String firstWord = getKeyPhrase().split(" ")[0];
            firstWord = firstWord.toUpperCase();
            checkerNode = new TreeNode();
            checkerNode.setContent(firstWord);
            searchTree(treeIn.getRoot(), checkerNode);
            if(checkerNode.getWordCount() == 0){
                result.add("No naive stemming match");
            }
            else{
                result.add("Word Count = " + checkerNode.getWordCount());
                result.add("LineNumbers = " + checkerNode.getLineOccurences());
            }
            result.add("\n");
            writeToResult(result);
            MyLogger.writeMessage("Naive Stem: visit method concluded", DebugLevel.MATCHLEVEL);
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in NaiveStemVisitor.visit method", DebugLevel.NONE);
        }
    }

    public void searchTree(NodeInterface nodeIn, NodeInterface nodeCheckerIn){

        String currentContent;

        try{
            if(nodeIn == null){
                return;
            }
            currentContent = nodeIn.getContent().toUpperCase();
            MyLogger.writeMessage("Naive Stem: search Tree method invoked at node: " + currentContent, DebugLevel.MATCHLEVEL);
            if(currentContent.contains(nodeCheckerIn.getContent()) && !currentContent.equals(nodeCheckerIn.getContent())){
                nodeCheckerIn.getLineOccurences().addAll(nodeIn.getLineOccurences());
            }
            searchTree(nodeIn.getLeftChild(), nodeCheckerIn);
            searchTree(nodeIn.getRightChild(), nodeCheckerIn);
            MyLogger.writeMessage("Naive Stem: search Tree method concluded at node: " + currentContent, DebugLevel.MATCHLEVEL);
        }
        catch(Exception ex){
            MyLogger.writeMessage("Encountered Error: " + ex + " in NaiveStemVisitor.searchTree method", DebugLevel.NONE);
        }
    }
}