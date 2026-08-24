package com.calculator.history;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculationHistory {
    private static final int MAX_ENTRIES = 10;
    private final LinkedList<String> entries = new LinkedList<>();

    public void add(String entry) {
        entries.addFirst(entry);
        while (entries.size() > MAX_ENTRIES) {
            entries.removeLast();
        }
    }

    public List<String> getEntries() {
        return new ArrayList<>(entries);
    }
}
