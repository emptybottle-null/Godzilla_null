/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml;

import org.fife.rsta.ac.xml.ValidationConfig;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.Token;

public class ValidationConfigSniffer {
    public ValidationConfig sniff(RSyntaxDocument doc) {
        ValidationConfig config = null;
        block4: for (Token token : doc) {
            switch (token.getType()) {
                case 30: {
                    break block4;
                }
                case 26: {
                    break block4;
                }
                default: {
                    continue block4;
                }
            }
        }
        return config;
    }
}

