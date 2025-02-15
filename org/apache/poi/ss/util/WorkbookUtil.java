/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

public class WorkbookUtil {
    public static String createSafeSheetName(String nameProposal) {
        return WorkbookUtil.createSafeSheetName(nameProposal, ' ');
    }

    public static String createSafeSheetName(String nameProposal, char replaceChar) {
        if (nameProposal == null) {
            return "null";
        }
        if (nameProposal.length() < 1) {
            return "empty";
        }
        int length = Math.min(31, nameProposal.length());
        String shortenname = nameProposal.substring(0, length);
        StringBuilder result = new StringBuilder(shortenname);
        block4: for (int i = 0; i < length; ++i) {
            char ch = result.charAt(i);
            switch (ch) {
                case '\u0000': 
                case '\u0003': 
                case '*': 
                case '/': 
                case ':': 
                case '?': 
                case '[': 
                case '\\': 
                case ']': {
                    result.setCharAt(i, replaceChar);
                    continue block4;
                }
                case '\'': {
                    if (i != 0 && i != length - 1) continue block4;
                    result.setCharAt(i, replaceChar);
                    continue block4;
                }
            }
        }
        return result.toString();
    }

    public static void validateSheetName(String sheetName) {
        if (sheetName == null) {
            throw new IllegalArgumentException("sheetName must not be null");
        }
        int len = sheetName.length();
        if (len < 1 || len > 31) {
            throw new IllegalArgumentException("sheetName '" + sheetName + "' is invalid - character count MUST be greater than or equal to 1 and less than or equal to 31");
        }
        block3: for (int i = 0; i < len; ++i) {
            char ch = sheetName.charAt(i);
            switch (ch) {
                case '*': 
                case '/': 
                case ':': 
                case '?': 
                case '[': 
                case '\\': 
                case ']': {
                    break;
                }
                default: {
                    continue block3;
                }
            }
            throw new IllegalArgumentException("Invalid char (" + ch + ") found at index (" + i + ") in sheet name '" + sheetName + "'");
        }
        if (sheetName.charAt(0) == '\'' || sheetName.charAt(len - 1) == '\'') {
            throw new IllegalArgumentException("Invalid sheet name '" + sheetName + "'. Sheet names must not begin or end with (').");
        }
    }
}

