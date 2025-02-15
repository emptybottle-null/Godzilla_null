/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.Action;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.Token;

public interface TokenMaker {
    public void addNullToken();

    public void addToken(char[] var1, int var2, int var3, int var4, int var5);

    public int getClosestStandardTokenTypeForInternalType(int var1);

    public boolean getCurlyBracesDenoteCodeBlocks(int var1);

    public int getLastTokenTypeOnLine(Segment var1, int var2);

    public String[] getLineCommentStartAndEnd(int var1);

    public Action getInsertBreakAction();

    public boolean getMarkOccurrencesOfTokenType(int var1);

    public OccurrenceMarker getOccurrenceMarker();

    public boolean getShouldIndentNextLineAfter(Token var1);

    public Token getTokenList(Segment var1, int var2, int var3);

    public boolean isIdentifierChar(int var1, char var2);

    public boolean isMarkupLanguage();
}

