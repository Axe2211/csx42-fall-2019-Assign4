package troubleshootsearch.util;

public class MyLogger{

    public static enum DebugLevel { 
            CONSTRUCTOR, 
            RESULTS,
            MATCHLEVEL,
            ELEMENT,
            FILE_PROCESSOR,
            NONE
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue(int levelIn){
        switch (levelIn) {
            case 5: debugLevel = DebugLevel.ELEMENT; break;
            case 4: debugLevel = DebugLevel.MATCHLEVEL; break;
            case 3: debugLevel = DebugLevel.FILE_PROCESSOR; break;
            case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 1: debugLevel = DebugLevel.RESULTS; break;
            default: debugLevel = DebugLevel.NONE; break;
	    }
    }

    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage (String message, DebugLevel levelIn) {

        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}