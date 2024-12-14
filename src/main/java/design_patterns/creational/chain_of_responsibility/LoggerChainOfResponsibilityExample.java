package design_patterns.creational.chain_of_responsibility;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerChainOfResponsibilityExample {

    private static Logger logger = Logger.getLogger(LoggerChainOfResponsibilityExample.class.getName());

    public static void main(String[] args) {

        // level to log at
        logger.setLevel(Level.FINER);

        ConsoleHandler consoleHandler = new ConsoleHandler();

        consoleHandler.setLevel(Level.FINER);
        logger.addHandler(consoleHandler);

        logger.finest("This is a finest message"); // false since we've set the level of the logger to FINER
        logger.finer("This is a finer message"); // true
        logger.fine("This is a fine message"); // true
    }
}
