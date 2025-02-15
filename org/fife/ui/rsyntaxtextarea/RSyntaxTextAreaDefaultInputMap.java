/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.KeyStroke;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rtextarea.RTADefaultInputMap;
import org.fife.ui.rtextarea.RTextArea;

public class RSyntaxTextAreaDefaultInputMap
extends RTADefaultInputMap {
    public RSyntaxTextAreaDefaultInputMap() {
        int defaultMod = RTextArea.getDefaultModifier();
        int shift = 64;
        int defaultShift = defaultMod | shift;
        this.put(KeyStroke.getKeyStroke(9, shift), "RSTA.DecreaseIndentAction");
        this.put(KeyStroke.getKeyStroke('}'), "RSTA.CloseCurlyBraceAction");
        this.put(KeyStroke.getKeyStroke('/'), "RSTA.CloseMarkupTagAction");
        int os = RSyntaxUtilities.getOS();
        if (os == 1 || os == 2) {
            this.put(KeyStroke.getKeyStroke(47, defaultMod), "RSTA.ToggleCommentAction");
        }
        this.put(KeyStroke.getKeyStroke(91, defaultMod), "RSTA.GoToMatchingBracketAction");
        this.put(KeyStroke.getKeyStroke(109, defaultMod), "RSTA.CollapseFoldAction");
        this.put(KeyStroke.getKeyStroke(107, defaultMod), "RSTA.ExpandFoldAction");
        this.put(KeyStroke.getKeyStroke(111, defaultMod), "RSTA.CollapseAllFoldsAction");
        this.put(KeyStroke.getKeyStroke(106, defaultMod), "RSTA.ExpandAllFoldsAction");
        this.put(KeyStroke.getKeyStroke(32, defaultShift), "RSTA.TemplateAction");
    }
}

