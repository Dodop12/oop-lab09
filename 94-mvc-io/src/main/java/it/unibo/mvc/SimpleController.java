package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 */
public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> stringHistory;

    public SimpleController() {
        this.stringHistory = new ArrayList<>();
    }

    @Override
    public void setNextStringToPrint(final String nextString) {
        Objects.requireNonNull(nextString);
        this.nextString = nextString;
    }

    @Override
    public String getNextStringToPrint() {
        return nextString;
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return Collections.unmodifiableList(stringHistory);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("No string set");
        }
        System.out.println(this.nextString);
        this.stringHistory.add(nextString);
    }
}
