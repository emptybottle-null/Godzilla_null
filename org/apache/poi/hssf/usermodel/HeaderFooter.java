/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

public abstract class HeaderFooter
implements org.apache.poi.ss.usermodel.HeaderFooter {
    protected HeaderFooter() {
    }

    protected abstract String getRawText();

    private String[] splitParts() {
        String text = this.getRawText();
        String _left = "";
        String _center = "";
        String _right = "";
        block5: while (text.length() > 1) {
            if (text.charAt(0) != '&') {
                _center = text;
                break;
            }
            int pos = text.length();
            switch (text.charAt(1)) {
                case 'L': {
                    if (text.contains("&C")) {
                        pos = Math.min(pos, text.indexOf("&C"));
                    }
                    if (text.contains("&R")) {
                        pos = Math.min(pos, text.indexOf("&R"));
                    }
                    _left = text.substring(2, pos);
                    text = text.substring(pos);
                    continue block5;
                }
                case 'C': {
                    if (text.contains("&L")) {
                        pos = Math.min(pos, text.indexOf("&L"));
                    }
                    if (text.contains("&R")) {
                        pos = Math.min(pos, text.indexOf("&R"));
                    }
                    _center = text.substring(2, pos);
                    text = text.substring(pos);
                    continue block5;
                }
                case 'R': {
                    if (text.contains("&C")) {
                        pos = Math.min(pos, text.indexOf("&C"));
                    }
                    if (text.contains("&L")) {
                        pos = Math.min(pos, text.indexOf("&L"));
                    }
                    _right = text.substring(2, pos);
                    text = text.substring(pos);
                    continue block5;
                }
            }
            _center = text;
            break;
        }
        return new String[]{_left, _center, _right};
    }

    @Override
    public final String getLeft() {
        return this.splitParts()[0];
    }

    @Override
    public final void setLeft(String newLeft) {
        this.updatePart(0, newLeft);
    }

    @Override
    public final String getCenter() {
        return this.splitParts()[1];
    }

    @Override
    public final void setCenter(String newCenter) {
        this.updatePart(1, newCenter);
    }

    @Override
    public final String getRight() {
        return this.splitParts()[2];
    }

    @Override
    public final void setRight(String newRight) {
        this.updatePart(2, newRight);
    }

    private void updatePart(int partIndex, String newValue) {
        String[] parts = this.splitParts();
        parts[partIndex] = newValue == null ? "" : newValue;
        this.updateHeaderFooterText(parts);
    }

    private void updateHeaderFooterText(String[] parts) {
        String _left = parts[0];
        String _center = parts[1];
        String _right = parts[2];
        if (_center.length() < 1 && _left.length() < 1 && _right.length() < 1) {
            this.setHeaderFooterText("");
            return;
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append("&C");
        sb.append(_center);
        sb.append("&L");
        sb.append(_left);
        sb.append("&R");
        sb.append(_right);
        String text = sb.toString();
        this.setHeaderFooterText(text);
    }

    protected abstract void setHeaderFooterText(String var1);

    public static String fontSize(short size) {
        return "&" + size;
    }

    public static String font(String font, String style) {
        return "&\"" + font + "," + style + "\"";
    }

    public static String page() {
        return MarkupTag.PAGE_FIELD.getRepresentation();
    }

    public static String numPages() {
        return MarkupTag.NUM_PAGES_FIELD.getRepresentation();
    }

    public static String date() {
        return MarkupTag.DATE_FIELD.getRepresentation();
    }

    public static String time() {
        return MarkupTag.TIME_FIELD.getRepresentation();
    }

    public static String file() {
        return MarkupTag.FILE_FIELD.getRepresentation();
    }

    public static String tab() {
        return MarkupTag.SHEET_NAME_FIELD.getRepresentation();
    }

    public static String startBold() {
        return MarkupTag.BOLD_FIELD.getRepresentation();
    }

    public static String endBold() {
        return MarkupTag.BOLD_FIELD.getRepresentation();
    }

    public static String startUnderline() {
        return MarkupTag.UNDERLINE_FIELD.getRepresentation();
    }

    public static String endUnderline() {
        return MarkupTag.UNDERLINE_FIELD.getRepresentation();
    }

    public static String startDoubleUnderline() {
        return MarkupTag.DOUBLE_UNDERLINE_FIELD.getRepresentation();
    }

    public static String endDoubleUnderline() {
        return MarkupTag.DOUBLE_UNDERLINE_FIELD.getRepresentation();
    }

    public static String stripFields(String pText) {
        if (pText == null || pText.length() == 0) {
            return pText;
        }
        String text = pText;
        for (MarkupTag mt : MarkupTag.values()) {
            int pos;
            String seq = mt.getRepresentation();
            while ((pos = text.indexOf(seq)) >= 0) {
                text = text.substring(0, pos) + text.substring(pos + seq.length());
            }
        }
        text = text.replaceAll("\\&\\d+", "");
        text = text.replaceAll("\\&\".*?,.*?\"", "");
        return text;
    }

    private static enum MarkupTag {
        SHEET_NAME_FIELD("&A", false),
        DATE_FIELD("&D", false),
        FILE_FIELD("&F", false),
        FULL_FILE_FIELD("&Z", false),
        PAGE_FIELD("&P", false),
        TIME_FIELD("&T", false),
        NUM_PAGES_FIELD("&N", false),
        PICTURE_FIELD("&G", false),
        BOLD_FIELD("&B", true),
        ITALIC_FIELD("&I", true),
        STRIKETHROUGH_FIELD("&S", true),
        SUBSCRIPT_FIELD("&Y", true),
        SUPERSCRIPT_FIELD("&X", true),
        UNDERLINE_FIELD("&U", true),
        DOUBLE_UNDERLINE_FIELD("&E", true);

        private final String _representation;
        private final boolean _occursInPairs;

        private MarkupTag(String sequence, boolean occursInPairs) {
            this._representation = sequence;
            this._occursInPairs = occursInPairs;
        }

        public String getRepresentation() {
            return this._representation;
        }

        public boolean occursPairs() {
            return this._occursInPairs;
        }
    }
}

