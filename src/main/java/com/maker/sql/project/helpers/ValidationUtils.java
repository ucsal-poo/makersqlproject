package com.maker.sql.project.helpers;

import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {

    private static final Set<String> RESERVED_KEYWORDS = new HashSet<>();

    static {
        RESERVED_KEYWORDS.add("abstract");
        RESERVED_KEYWORDS.add("assert");
        RESERVED_KEYWORDS.add("boolean");
    }

    public static boolean isEntityNameValid(String entityName) {
        if (entityName.isEmpty()) {
            return false;
        }
        if (!Character.isLetter(entityName.charAt(0)) && entityName.charAt(0) != '_') {
            return false;
        }
        for (int i = 1; i < entityName.length(); i++) {
            char ch = entityName.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }
        if (RESERVED_KEYWORDS.contains(entityName)) {
            return false;
        }
        return true;
    }
}
