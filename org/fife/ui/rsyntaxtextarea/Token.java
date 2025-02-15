/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Rectangle;
import javax.swing.text.TabExpander;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.TokenTypes;

public interface Token
extends TokenTypes {
    public StringBuilder appendHTMLRepresentation(StringBuilder var1, RSyntaxTextArea var2, boolean var3);

    public StringBuilder appendHTMLRepresentation(StringBuilder var1, RSyntaxTextArea var2, boolean var3, boolean var4);

    public char charAt(int var1);

    public boolean containsPosition(int var1);

    public int documentToToken(int var1);

    public boolean endsWith(char[] var1);

    public int getEndOffset();

    public String getHTMLRepresentation(RSyntaxTextArea var1);

    public int getLanguageIndex();

    public Token getLastNonCommentNonWhitespaceToken();

    public Token getLastPaintableToken();

    public String getLexeme();

    public int getListOffset(RSyntaxTextArea var1, TabExpander var2, float var3, float var4);

    public Token getNextToken();

    public int getOffset();

    public int getOffsetBeforeX(RSyntaxTextArea var1, TabExpander var2, float var3, float var4);

    public char[] getTextArray();

    public int getTextOffset();

    public int getType();

    public float getWidth(RSyntaxTextArea var1, TabExpander var2, float var3);

    public float getWidthUpTo(int var1, RSyntaxTextArea var2, TabExpander var3, float var4);

    public boolean is(char[] var1);

    public boolean is(int var1, char[] var2);

    public boolean is(int var1, String var2);

    public boolean isComment();

    public boolean isCommentOrWhitespace();

    public boolean isHyperlink();

    public boolean isIdentifier();

    public boolean isLeftCurly();

    public boolean isRightCurly();

    public boolean isPaintable();

    public boolean isSingleChar(char var1);

    public boolean isSingleChar(int var1, char var2);

    public boolean isWhitespace();

    public int length();

    public Rectangle listOffsetToView(RSyntaxTextArea var1, TabExpander var2, int var3, int var4, Rectangle var5);

    public void setHyperlink(boolean var1);

    public void setLanguageIndex(int var1);

    public void setType(int var1);

    public boolean startsWith(char[] var1);

    public int tokenToDocument(int var1);
}

