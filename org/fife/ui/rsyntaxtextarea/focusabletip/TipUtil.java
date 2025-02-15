/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.focusabletip;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.html.HTMLDocument;
import org.fife.ui.rsyntaxtextarea.HtmlUtil;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rtextarea.RTextArea;

public final class TipUtil {
    private TipUtil() {
    }

    public static Rectangle getScreenBoundsForPoint(int x, int y) {
        GraphicsDevice[] devices;
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (GraphicsDevice device : devices = env.getScreenDevices()) {
            GraphicsConfiguration[] configs;
            for (GraphicsConfiguration config : configs = device.getConfigurations()) {
                Rectangle gcBounds = config.getBounds();
                if (!gcBounds.contains(x, y)) continue;
                return gcBounds;
            }
        }
        return env.getMaximumWindowBounds();
    }

    public static Color getToolTipBackground() {
        return TipUtil.getToolTipBackground(null);
    }

    public static Color getToolTipBackground(RTextArea textArea) {
        if (textArea != null && !Color.WHITE.equals(textArea.getBackground())) {
            return textArea.getBackground();
        }
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
        return TipUtil.getToolTipBorder(null);
    }

    public static Border getToolTipBorder(RTextArea textArea) {
        Color color;
        if (textArea != null && !Color.WHITE.equals(textArea.getBackground()) && (color = textArea.getBackground()) != null) {
            return BorderFactory.createLineBorder(color.brighter());
        }
        Border border = UIManager.getBorder("ToolTip.border");
        if ((border == null || TipUtil.isNimbusLookAndFeel()) && (border = UIManager.getBorder("nimbusBorder")) == null) {
            border = BorderFactory.createLineBorder(SystemColor.controlDkShadow);
        }
        return border;
    }

    private static boolean isDerivedColor(Color c) {
        return c != null && c.getClass().getName().endsWith(".DerivedColor");
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
        Color fg = UIManager.getColor("Label.foreground");
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
        TipUtil.setFont(doc, font, fg);
        Color linkFG = RSyntaxUtilities.getHyperlinkForeground();
        doc.getStyleSheet().addRule("a { color: " + HtmlUtil.getHexString(linkFG) + "; }");
    }

    public static void setFont(HTMLDocument doc, Font font, Color fg) {
        doc.getStyleSheet().addRule("body { font-family: " + font.getFamily() + "; font-size: " + font.getSize() + "pt; color: " + HtmlUtil.getHexString(fg) + "; }");
    }
}

