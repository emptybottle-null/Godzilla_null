/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Rectangle;
import javax.swing.text.BadLocationException;

interface RSTAView {
    public int yForLine(Rectangle var1, int var2) throws BadLocationException;

    public int yForLineContaining(Rectangle var1, int var2) throws BadLocationException;
}

