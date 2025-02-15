/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import org.fife.ui.rtextarea.RTextArea;

public class RtfGenerator {
    private Color mainBG;
    private List<Font> fontList;
    private List<Color> colorList;
    private StringBuilder document;
    private boolean lastWasControlWord;
    private int lastFontIndex;
    private int lastFGIndex;
    private boolean lastBold;
    private boolean lastItalic;
    private int lastFontSize;
    private int screenRes;
    private static final int DEFAULT_FONT_SIZE = 12;

    public RtfGenerator(Color mainBG) {
        this.mainBG = mainBG;
        this.fontList = new ArrayList<Font>(1);
        this.colorList = new ArrayList<Color>(1);
        this.document = new StringBuilder();
        this.reset();
    }

    public void appendNewline() {
        this.document.append("\\line");
        this.document.append('\n');
        this.lastWasControlWord = false;
    }

    public void appendToDoc(String text, Font f, Color fg, Color bg) {
        this.appendToDoc(text, f, fg, bg, false);
    }

    public void appendToDocNoFG(String text, Font f, Color bg, boolean underline) {
        this.appendToDoc(text, f, null, bg, underline, false);
    }

    public void appendToDoc(String text, Font f, Color fg, Color bg, boolean underline) {
        this.appendToDoc(text, f, fg, bg, underline, true);
    }

    public void appendToDoc(String text, Font f, Color fg, Color bg, boolean underline, boolean setFG) {
        if (text != null) {
            int fontIndex;
            int n = fontIndex = f == null ? 0 : RtfGenerator.getFontIndex(this.fontList, f) + 1;
            if (fontIndex != this.lastFontIndex) {
                this.document.append("\\f").append(fontIndex);
                this.lastFontIndex = fontIndex;
                this.lastWasControlWord = true;
            }
            if (f != null) {
                int fontSize = this.fixFontSize(f.getSize2D() * 2.0f);
                if (fontSize != this.lastFontSize) {
                    this.document.append("\\fs").append(fontSize);
                    this.lastFontSize = fontSize;
                    this.lastWasControlWord = true;
                }
                if (f.isBold() != this.lastBold) {
                    this.document.append(this.lastBold ? "\\b0" : "\\b");
                    this.lastBold = !this.lastBold;
                    this.lastWasControlWord = true;
                }
                if (f.isItalic() != this.lastItalic) {
                    this.document.append(this.lastItalic ? "\\i0" : "\\i");
                    this.lastItalic = !this.lastItalic;
                    this.lastWasControlWord = true;
                }
            } else {
                if (this.lastFontSize != 12) {
                    this.document.append("\\fs").append(12);
                    this.lastFontSize = 12;
                    this.lastWasControlWord = true;
                }
                if (this.lastBold) {
                    this.document.append("\\b0");
                    this.lastBold = false;
                    this.lastWasControlWord = true;
                }
                if (this.lastItalic) {
                    this.document.append("\\i0");
                    this.lastItalic = false;
                    this.lastWasControlWord = true;
                }
            }
            if (underline) {
                this.document.append("\\ul");
                this.lastWasControlWord = true;
            }
            if (setFG) {
                int fgIndex = 0;
                if (fg != null) {
                    fgIndex = RtfGenerator.getColorIndex(this.colorList, fg) + 1;
                }
                if (fgIndex != this.lastFGIndex) {
                    this.document.append("\\cf").append(fgIndex);
                    this.lastFGIndex = fgIndex;
                    this.lastWasControlWord = true;
                }
            }
            if (bg != null) {
                int pos = RtfGenerator.getColorIndex(this.colorList, bg);
                this.document.append("\\highlight").append(pos + 1);
                this.lastWasControlWord = true;
            }
            if (this.lastWasControlWord) {
                this.document.append(' ');
                this.lastWasControlWord = false;
            }
            this.escapeAndAdd(this.document, text);
            if (bg != null) {
                this.document.append("\\highlight0");
                this.lastWasControlWord = true;
            }
            if (underline) {
                this.document.append("\\ul0");
                this.lastWasControlWord = true;
            }
        }
    }

    private void escapeAndAdd(StringBuilder sb, String text) {
        int count = text.length();
        block4: for (int i = 0; i < count; ++i) {
            char ch = text.charAt(i);
            switch (ch) {
                case '\t': {
                    sb.append("\\tab");
                    while (++i < count && text.charAt(i) == '\t') {
                        sb.append("\\tab");
                    }
                    sb.append(' ');
                    --i;
                    continue block4;
                }
                case '\\': 
                case '{': 
                case '}': {
                    sb.append('\\').append(ch);
                    continue block4;
                }
                default: {
                    if (ch <= '\u007f') {
                        sb.append(ch);
                        continue block4;
                    }
                    sb.append("\\u").append((int)ch).append(' ');
                }
            }
        }
    }

    private int fixFontSize(float pointSize) {
        if (this.screenRes != 72) {
            pointSize = Math.round(pointSize * 72.0f / (float)this.screenRes);
        }
        return (int)pointSize;
    }

    private static int getColorIndex(List<Color> list, Color item) {
        int pos = list.indexOf(item);
        if (pos == -1) {
            list.add(item);
            pos = list.size() - 1;
        }
        return pos;
    }

    private String getColorTableRtf() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\\colortbl ;");
        for (Color c : this.colorList) {
            sb.append("\\red").append(c.getRed());
            sb.append("\\green").append(c.getGreen());
            sb.append("\\blue").append(c.getBlue());
            sb.append(';');
        }
        sb.append("}");
        return sb.toString();
    }

    private static int getFontIndex(List<Font> list, Font font) {
        String fontName = font.getFamily();
        for (int i = 0; i < list.size(); ++i) {
            Font font2 = list.get(i);
            if (!font2.getFamily().equals(fontName)) continue;
            return i;
        }
        list.add(font);
        return list.size() - 1;
    }

    private String getFontTableRtf() {
        StringBuilder sb = new StringBuilder();
        String monoFamilyName = RtfGenerator.getMonospacedFontFamily();
        sb.append("{\\fonttbl{\\f0\\fnil\\fcharset0 ").append(monoFamilyName).append(";}");
        for (int i = 0; i < this.fontList.size(); ++i) {
            Font f = this.fontList.get(i);
            String familyName = f.getFamily();
            if (familyName.equals("Monospaced")) {
                familyName = monoFamilyName;
            }
            sb.append("{\\f").append(i + 1).append("\\fnil\\fcharset0 ");
            sb.append(familyName).append(";}");
        }
        sb.append('}');
        return sb.toString();
    }

    private static String getMonospacedFontFamily() {
        String family = RTextArea.getDefaultFont().getFamily();
        if ("Monospaced".equals(family)) {
            family = "Courier";
        }
        return family;
    }

    public String getRtf() {
        int mainBGIndex = RtfGenerator.getColorIndex(this.colorList, this.mainBG);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\\rtf1\\ansi\\ansicpg1252");
        sb.append("\\deff0");
        sb.append("\\deflang1033");
        sb.append("\\viewkind4");
        sb.append("\\uc\\pard\\f0");
        sb.append("\\fs20");
        sb.append(this.getFontTableRtf()).append('\n');
        sb.append(this.getColorTableRtf()).append('\n');
        int bgIndex = mainBGIndex + 1;
        sb.append("\\cb").append(bgIndex).append(' ');
        this.lastWasControlWord = true;
        if (this.document.length() > 0) {
            this.document.append("\\line");
        }
        sb.append((CharSequence)this.document);
        sb.append("}");
        return sb.toString();
    }

    public void reset() {
        this.fontList.clear();
        this.colorList.clear();
        this.document.setLength(0);
        this.lastWasControlWord = false;
        this.lastFontIndex = 0;
        this.lastFGIndex = 0;
        this.lastBold = false;
        this.lastItalic = false;
        this.lastFontSize = 12;
        this.screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
    }
}

