/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Graphics2D;
import javax.swing.text.TabExpander;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;

interface TokenPainter {
    public float paint(Token var1, Graphics2D var2, float var3, float var4, RSyntaxTextArea var5, TabExpander var6);

    public float paint(Token var1, Graphics2D var2, float var3, float var4, RSyntaxTextArea var5, TabExpander var6, float var7);

    public float paint(Token var1, Graphics2D var2, float var3, float var4, RSyntaxTextArea var5, TabExpander var6, float var7, boolean var8);

    public float paintSelected(Token var1, Graphics2D var2, float var3, float var4, RSyntaxTextArea var5, TabExpander var6, boolean var7);

    public float paintSelected(Token var1, Graphics2D var2, float var3, float var4, RSyntaxTextArea var5, TabExpander var6, float var7, boolean var8);
}

