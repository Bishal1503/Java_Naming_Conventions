package com.epam.engx.cleancode.naming.task6;

public class Formatter {

    private static final String CORNER_SYMBOL = "+";
    private static final String HORIZONTAL_BORDER_SYMBOL = "|";
    private static final String VERTICAL_BORDER_SYMBOL = "-";
    private static final String DELIMETER = " _ ";


    public String formatKeyValue(String key, String value) {
        String content = key + DELIMETER + value;
        String border = repeat(VERTICAL_BORDER_SYMBOL, content.length());
        return CORNER_SYMBOL +  border + CORNER_SYMBOL + "\n"
                + HORIZONTAL_BORDER_SYMBOL + content + HORIZONTAL_BORDER_SYMBOL + "\n"
                + CORNER_SYMBOL + border + CORNER_SYMBOL + "\n";
    }

    private String repeat(String symbol, int times) {
        String result = "";
        for (int i = 0; i < times; i++)
            result += symbol;
        return result;
    }
}
