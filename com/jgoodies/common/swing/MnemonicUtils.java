/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.swing;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import java.text.StringCharacterIterator;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JLabel;

public final class MnemonicUtils {
    static final char MNEMONIC_MARKER = '&';

    private MnemonicUtils() {
    }

    public static void configure(AbstractButton target, String markedText) {
        Preconditions.checkNotNull(target, "The %1$s must not be null.", "target");
        MnemonicUtils.configure0(target, new MnemonicText(markedText, '&'));
    }

    public static void configure(Action target, String markedText) {
        Preconditions.checkNotNull(target, "The %1$s must not be null.", "target");
        MnemonicUtils.configure0(target, new MnemonicText(markedText, '&'));
    }

    public static void configure(JLabel target, String markedText) {
        Preconditions.checkNotNull(target, "The %1$s must not be null.", "target");
        MnemonicUtils.configure0(target, new MnemonicText(markedText, '&'));
    }

    public static String plainText(String markedText) {
        return new MnemonicText((String)markedText, (char)'&').text;
    }

    static int mnemonic(String markedText) {
        return new MnemonicText((String)markedText, (char)'&').key;
    }

    static int mnemonicIndex(String markedText) {
        return new MnemonicText((String)markedText, (char)'&').index;
    }

    private static void configure0(AbstractButton button, MnemonicText mnemonicText) {
        button.setText(mnemonicText.text);
        button.setMnemonic(mnemonicText.key);
        button.setDisplayedMnemonicIndex(mnemonicText.index);
    }

    private static void configure0(Action action, MnemonicText mnemonicText) {
        Integer keyValue = mnemonicText.key;
        Integer indexValue = mnemonicText.index == -1 ? null : Integer.valueOf(mnemonicText.index);
        action.putValue("Name", mnemonicText.text);
        action.putValue("MnemonicKey", keyValue);
        action.putValue("SwingDisplayedMnemonicIndexKey", indexValue);
    }

    private static void configure0(JLabel label, MnemonicText mnemonicText) {
        label.setText(mnemonicText.text);
        label.setDisplayedMnemonic(mnemonicText.key);
        label.setDisplayedMnemonicIndex(mnemonicText.index);
    }

    private static final class MnemonicText {
        String text;
        int key;
        int index;

        private MnemonicText(String markedText, char marker) {
            int i;
            if (markedText == null || markedText.length() <= 1 || (i = markedText.indexOf(marker)) == -1) {
                this.text = markedText;
                this.key = 0;
                this.index = -1;
                return;
            }
            boolean html = Strings.startsWithIgnoreCase(markedText, "<html>");
            StringBuilder builder = new StringBuilder();
            int begin = 0;
            int quotedMarkers = 0;
            int markerIndex = -1;
            boolean marked = false;
            char markedChar = '\u0000';
            StringCharacterIterator sci = new StringCharacterIterator(markedText);
            do {
                builder.append(markedText.substring(begin, i));
                char current = sci.setIndex(i);
                char next = sci.next();
                if (html) {
                    int entityEnd = MnemonicText.indexOfEntityEnd(markedText, i);
                    if (entityEnd == -1) {
                        marked = true;
                        builder.append("<u>").append(next).append("</u>");
                        begin = i + 2;
                        markedChar = next;
                        continue;
                    }
                    builder.append(markedText.substring(i, entityEnd));
                    begin = entityEnd;
                    continue;
                }
                if (next == marker) {
                    builder.append(next);
                    begin = i + 2;
                    ++quotedMarkers;
                    continue;
                }
                if (Character.isWhitespace(next)) {
                    builder.append(current).append(next);
                    begin = i + 2;
                    continue;
                }
                if (next == '\uffff') {
                    builder.append(current);
                    begin = i + 2;
                    continue;
                }
                builder.append(next);
                begin = i + 2;
                markerIndex = i - quotedMarkers;
                marked = true;
                markedChar = next;
            } while ((i = markedText.indexOf(marker, begin)) != -1 && !marked);
            if (begin < markedText.length()) {
                builder.append(markedText.substring(begin));
            }
            this.text = builder.toString();
            this.index = markerIndex;
            this.key = marked ? MnemonicText.mnemonicKey(markedChar) : 0;
        }

        private static int indexOfEntityEnd(String htmlText, int start) {
            char c;
            StringCharacterIterator sci = new StringCharacterIterator(htmlText, start);
            do {
                if ((c = sci.next()) == ';') {
                    return sci.getIndex();
                }
                if (Character.isLetterOrDigit(c)) continue;
                return -1;
            } while (c != '\uffff');
            return -1;
        }

        private static int mnemonicKey(char c) {
            int vk = c;
            if (vk >= 97 && vk <= 122) {
                vk -= 32;
            }
            return vk;
        }
    }
}

