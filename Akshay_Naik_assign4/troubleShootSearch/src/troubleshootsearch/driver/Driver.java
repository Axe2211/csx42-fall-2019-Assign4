package troubleshootsearch.driver;

// import java.util.List;
// import java.util.ArrayList;

import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.Results;
import troubleshootsearch.util.MyLogger.DebugLevel;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.visitor.ExactMatchVisitor;
import troubleshootsearch.visitor.NaiveStemVisitor;
import troubleshootsearch.visitor.SemMatchVisitor;

import troubleshootsearch.element.MyArrayList;
import troubleshootsearch.element.MySynonyms;
import troubleshootsearch.util.MyLogger;
import troubleshootsearch.element.MyTree;

/**
 * @author Akshay Naik
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || args[3].equals("${arg3}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}
		try{
			String[] inputFiles = new String[3];
			inputFiles[0] = args[0];
			inputFiles[1] = args[1];
			inputFiles[2] = args[2];

			FileProcessor dataIO = new FileProcessor(inputFiles, args[3]);
			Results result = new Results();

			MyTree wordTree = new MyTree();
			wordTree.buildWordTree(dataIO);

			MyArrayList infoList = new MyArrayList();
			infoList.buildArrayList(dataIO);
			
			MySynonyms synonymMap = new MySynonyms();
			synonymMap.buildSynonyms(dataIO);

			infoList.setSynonyms(synonymMap);

			VisitorI exact = new ExactMatchVisitor();
			exact.setResultRef(result);
			VisitorI sem = new SemMatchVisitor();
			sem.setResultRef(result);
			VisitorI naive = new NaiveStemVisitor();
			naive.setResultRef(result);
			
			dataIO.setInputFileHandler(dataIO.getUserFile());
			String userInputString = null;

			while((userInputString = dataIO.readLine()) != null){
				result.writeToResult("user input - " + userInputString);
				exact.setKeyPhrase(userInputString);
				sem.setKeyPhrase(userInputString);
				naive.setKeyPhrase(userInputString);
				infoList.accept(exact);
				wordTree.accept(naive);
				infoList.accept(sem);
			}
			dataIO.closeInFile();
			result.writeToStdOut();
			result.writeToFile(dataIO);
		}
		catch(Exception ex){
			MyLogger.writeMessage("Encountered Error: " + ex + " in Driver.main", DebugLevel.NONE);
		}
	}
}
