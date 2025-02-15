/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.cli;

final class OptionValidator {
    OptionValidator() {
    }

    static void validateOption(String opt) throws IllegalArgumentException {
        if (opt == null) {
            return;
        }
        if (opt.length() == 1) {
            char ch = opt.charAt(0);
            if (!OptionValidator.isValidOpt(ch)) {
                throw new IllegalArgumentException("Illegal option name '" + ch + "'");
            }
        } else {
            for (char ch : opt.toCharArray()) {
                if (OptionValidator.isValidChar(ch)) continue;
                throw new IllegalArgumentException("The option '" + opt + "' contains an illegal character : '" + ch + "'");
            }
        }
    }

    private static boolean isValidOpt(char c) {
        return OptionValidator.isValidChar(c) || c == '?' || c == '@';
    }

    private static boolean isValidChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}

