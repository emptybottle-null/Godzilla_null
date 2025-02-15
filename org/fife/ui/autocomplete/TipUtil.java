/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JToolTip;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.html.HTMLDocument;
import org.fife.ui.autocomplete.Util;

final class TipUtil {
    private TipUtil() {
    }

    public static Color getToolTipBackground() {
        Color c = UIManager.getColor("ToolTip.background");
        boolean isNimbus = TipUtil.isNimbusLookAndFeel();
        if ((c == null || isNimbus) && ((c = UIManager.getColor("info")) == null || isNimbus && TipUtil.isDerivedColor(c))) {
            c = SystemColor.info;
        }
        if (c instanceof ColorUIResource) {
            c = new Color(c.getRGB());
        }
        return c;
    }

    public static Border getToolTipBorder() {
        Border border = UIManager.getBorder("ToolTip.border");
        if ((border == null || TipUtil.isNimbusLookAndFeel()) && (border = UIManager.getBorder("nimbusBorder")) == null) {
            border = BorderFactory.createLineBorder(SystemColor.controlDkShadow);
        }
        return border;
    }

    static Color getToolTipHyperlinkForeground() {
        Color fg = UIManager.getColor("ToolTip.foreground");
        if (fg == null || TipUtil.isNimbusLookAndFeel()) {
            fg = new JToolTip().getForeground();
        }
        return Util.isLightForeground(fg) ? Util.LIGHT_HYPERLINK_FG : Color.blue;
    }

    private static boolean isDerivedColor(Color c) {
        return c != null && (c.getClass().getName().endsWith(".DerivedColor") || c.getClass().getName().endsWith(".DerivedColor$UIResource"));
    }

    private static boolean isNimbusLookAndFeel() {
        return UIManager.getLookAndFeel().getName().equals("Nimbus");
    }

    public static void tweakTipEditorPane(JEditorPane textArea) {
        boolean isNimbus = TipUtil.isNimbusLookAndFeel();
        if (isNimbus) {
            Color selBG = textArea.getSelectionColor();
            Color selFG = textArea.getSelectedTextColor();
            textArea.setUI(new BasicEditorPaneUI());
            textArea.setSelectedTextColor(selFG);
            textArea.setSelectionColor(selBG);
        }
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textArea.getCaret().setSelectionVisible(true);
        Color fg = UIManager.getColor("ToolTip.foreground");
        if (fg == null) {
            fg = UIManager.getColor("Label.foreground");
        }
        if (fg == null || isNimbus && TipUtil.isDerivedColor(fg)) {
            fg = SystemColor.textText;
        }
        textArea.setForeground(fg);
        textArea.setBackground(TipUtil.getToolTipBackground());
        Font font = UIManager.getFont("Label.font");
        if (font == null) {
            font = new Font("SansSerif", 0, 12);
        }
        HTMLDocument doc = (HTMLDocument)textArea.getDocument();
        doc.getStyleSheet().addRule("body { font-family: " + font.getFamily() + "; font-size: " + font.getSize() + "pt" + "; color: " + Util.getHexString(fg) + "; }");
        Color linkFG = TipUtil.getToolTipHyperlinkForeground();
        doc.getStyleSheet().addRule("a { color: " + Util.getHexString(linkFG) + "; }");
        URL url = TipUtil.class.getResource("bullet_black.png");
        if (url != null) {
            doc.getStyleSheet().addRule("ul { list-style-image: '" + url.toString() + "'; }");
        }
    }
}

