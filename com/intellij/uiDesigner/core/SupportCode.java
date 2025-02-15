/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import java.lang.reflect.Method;
import javax.swing.JComponent;

public final class SupportCode {
    public static TextWithMnemonic parseText(String textWithMnemonic) {
        if (textWithMnemonic == null) {
            throw new IllegalArgumentException("textWithMnemonic cannot be null");
        }
        int index = -1;
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < textWithMnemonic.length(); ++i) {
            char ch = textWithMnemonic.charAt(i);
            if (ch == '&') {
                if (++i >= textWithMnemonic.length()) break;
                ch = textWithMnemonic.charAt(i);
                if (ch != '&') {
                    index = plainText.length();
                }
            }
            plainText.append(ch);
        }
        return new TextWithMnemonic(plainText.toString(), index);
    }

    public static void setDisplayedMnemonicIndex(JComponent component, int index) {
        try {
            Method method = component.getClass().getMethod("setDisplayedMnemonicIndex", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(component, index);
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public static final class TextWithMnemonic {
        public final String myText;
        public final int myMnemonicIndex;

        private TextWithMnemonic(String text, int index) {
            if (text == null) {
                throw new IllegalArgumentException("text cannot be null");
            }
            if (index != -1 && (index < 0 || index >= text.length())) {
                throw new IllegalArgumentException("wrong index: " + index + "; text = '" + text + "'");
            }
            this.myText = text;
            this.myMnemonicIndex = index;
        }

        public char getMnemonicChar() {
            if (this.myMnemonicIndex == -1) {
                throw new IllegalStateException("text doesn't contain mnemonic");
            }
            return Character.toUpperCase(this.myText.charAt(this.myMnemonicIndex));
        }
    }
}

