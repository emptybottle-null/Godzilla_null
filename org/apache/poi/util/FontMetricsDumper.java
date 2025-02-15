/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.util.SuppressForbidden;

public class FontMetricsDumper {
    @SuppressForbidden(value="command line tool")
    public static void main(String[] args) throws IOException {
        Font[] allFonts;
        Properties props = new Properties();
        for (Font allFont : allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            int c;
            char c2;
            String fontName = allFont.getFontName();
            Font font = new Font(fontName, 1, 10);
            FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
            int fontHeight = fontMetrics.getHeight();
            props.setProperty("font." + fontName + ".height", fontHeight + "");
            StringBuilder characters = new StringBuilder();
            for (c2 = 'a'; c2 <= 'z'; c2 = (char)(c2 + '\u0001')) {
                characters.append(c2).append(", ");
            }
            for (c2 = 'A'; c2 <= 'Z'; c2 = (char)(c2 + '\u0001')) {
                characters.append(c2).append(", ");
            }
            for (c2 = '0'; c2 <= '9'; c2 = (char)(c2 + '\u0001')) {
                characters.append(c2).append(", ");
            }
            StringBuilder widths = new StringBuilder();
            for (c = 97; c <= 122; c = (int)((char)(c + 1))) {
                widths.append(fontMetrics.getWidths()[c]).append(", ");
            }
            for (c = 65; c <= 90; c = (int)((char)(c + 1))) {
                widths.append(fontMetrics.getWidths()[c]).append(", ");
            }
            for (c = 48; c <= 57; c = (int)((char)(c + 1))) {
                widths.append(fontMetrics.getWidths()[c]).append(", ");
            }
            props.setProperty("font." + fontName + ".characters", characters.toString());
            props.setProperty("font." + fontName + ".widths", widths.toString());
        }
        try (FileOutputStream fileOut = new FileOutputStream("font_metrics.properties");){
            props.store(fileOut, "Font Metrics");
        }
    }
}

