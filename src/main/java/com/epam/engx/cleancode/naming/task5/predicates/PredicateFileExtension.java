package com.epam.engx.cleancode.naming.task5.predicates;


import com.epam.engx.cleancode.naming.task5.thirdpartyjar.Predicate;

public class PredicateFileExtension implements Predicate<String> {

    private String[] extensions;

    public PredicateFileExtension(String[] extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean test(String fileName) {
        for (String extension : extensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
