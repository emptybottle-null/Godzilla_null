/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Component;
import javax.swing.JOptionPane;

class MessageDialogWrapper {
    MessageDialogWrapper() {
    }

    public static void showMessageDialog(Component parent, String msg, String title, int flags) {
        if (msg.length() > 60) {
            StringBuilder buf = new StringBuilder();
            int len = msg.length();
            int j = 0;
            int i = 0;
            while (i < len) {
                char c = msg.charAt(i);
                buf.append(c);
                if (Character.isWhitespace(c)) {
                    int nextWordLen;
                    int k;
                    for (k = i + 1; k < len && !Character.isWhitespace(msg.charAt(k)); ++k) {
                    }
                    if (k < len && j + (nextWordLen = k - i) > 60) {
                        buf.append('\n');
                        j = 0;
                    }
                }
                ++i;
                ++j;
            }
            msg = buf.toString();
        }
        JOptionPane.showMessageDialog(parent, msg, title, flags);
    }
}

