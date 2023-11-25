package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void setNextStringToPrint(String nextString);

    String getNextStringToPrint();

    List<String> getPrintedStringsHistory();

    void printCurrentString();
}
