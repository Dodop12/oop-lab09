package it.unibo.mvc;

import java.util.List;

/**
 * Application controller responsible of the I/O access. Print the strings given
 * in standard output.
 */
public interface Controller {

    /**
     * Sets the next string to print
     * 
     * @throws NullPointerException if the string is null
     * @param nextString
     * 
     */
    void setNextStringToPrint(String nextString);

    /**
     * @return the next string to print
     */
    String getNextStringToPrint();

    /**
     * @return the list of all the strings printed
     */
    List<String> getPrintedStringsHistory();

    /**
     * Print the last string set
     * 
     * @throws IllegalStateException if no strings are set
     */
    void printCurrentString();
}
