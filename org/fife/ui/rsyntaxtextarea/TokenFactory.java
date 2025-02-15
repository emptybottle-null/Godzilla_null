/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

interface TokenFactory {
    public TokenImpl createToken();

    public TokenImpl createToken(Segment var1, int var2, int var3, int var4, int var5);

    public TokenImpl createToken(char[] var1, int var2, int var3, int var4, int var5);

    public void resetAllTokens();
}

