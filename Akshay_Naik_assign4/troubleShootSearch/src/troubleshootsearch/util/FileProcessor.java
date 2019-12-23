package troubleshootsearch.util;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.Exception;

import troubleshootsearch.util.MyLogger.DebugLevel;

public class FileProcessor{

    DebugLevel debugLevel;

    private String[] inputFileName;
    private String outputFileName;
    private File inTechFile, inUserFile, inSynFile, outFile;
    private Scanner inputFileHandler;
    private PrintWriter outputFileHandler;
    
    //constructor
    public FileProcessor(String[] inputFileNamesIn, String outputFileNameIn){

        debugLevel = DebugLevel.FILE_PROCESSOR;

        if(inputFileNamesIn.length == 0 || outputFileNameIn == null){
            MyLogger.writeMessage("Error: File Names not provided..", DebugLevel.NONE);
            System.exit(1);
        }
        else{
            this.inputFileName = inputFileNamesIn;
            this.outputFileName = outputFileNameIn;
        }
        //input mode
        
        try{
            if(this.inputFileName[0].equals("") || 
                   this.inputFileName[1].equals("") || 
                   this.inputFileName[2].equals("") ||
                   this.outputFileName.equals("")){
                throw new Exception("Empty file name entered in parameters..");
            }
            this.inTechFile = new File(this.inputFileName[0]);
            this.inSynFile = new File(this.inputFileName[1]);
            this.inUserFile = new File(this.inputFileName[2]);
            this.outFile = new File(this.outputFileName);
        }
        catch(Exception ex){
            MyLogger.writeMessage("File Name not passed in FileProcessor constructor ..", DebugLevel.NONE);
            System.exit(1);
        }
    }

    public void setInputFileHandler(File inputFileIn){
        try{
            if(inputFileIn.length() == 0){
                throw new IOException("File is empty or file does not exist..");
            }
            this.inputFileHandler = new Scanner(inputFileIn);
        }
        catch(IOException ex){
            MyLogger.writeMessage("File Not Found Exception in setInputFileHandler method", DebugLevel.NONE);
            System.exit(1);
        }
    }

    public void setOutputFileHandler(File outputFileIn){
        try{
            this.outputFileHandler = new PrintWriter(outputFileIn);
        }
        catch(IOException ex){
            MyLogger.writeMessage("File Not Found Exception in setOutputFileHandler method", DebugLevel.NONE);
            System.exit(1);
        }
    }

    public File getTechFile(){
        return inTechFile;
    }

    public File getUserFile(){
        return inUserFile;
    }

    public File getSynFile(){
        return inSynFile;
    }

    public File getOutFile(){
        return outFile;
    }

    public String readLine(){
        String currentLine = null;

        try{
            if(inputFileHandler.hasNextLine()){
                currentLine = inputFileHandler.nextLine();
                return currentLine;
            }
            MyLogger.writeMessage("File has no more lines", DebugLevel.FILE_PROCESSOR);
        }
        catch(Exception e){
            MyLogger.writeMessage("Error: Unable to read next line.. ", DebugLevel.NONE);
            System.exit(0);
        }
        currentLine = null;
        return currentLine;
    }

    public void writeFile(String output){
        outputFileHandler.println(output);
    }

    public void closeOutFile(){
        outputFileHandler.close();
    }

    public void closeInFile(){
        inputFileHandler.close();
    }	
}
