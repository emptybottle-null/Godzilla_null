/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.extensions;

import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.helpers.HeaderFooterHelper;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public abstract class XSSFHeaderFooter
implements org.apache.poi.ss.usermodel.HeaderFooter {
    private HeaderFooterHelper helper;
    private CTHeaderFooter headerFooter;
    private boolean stripFields;

    public XSSFHeaderFooter(CTHeaderFooter headerFooter) {
        this.headerFooter = headerFooter;
        this.helper = new HeaderFooterHelper();
    }

    @Internal
    public CTHeaderFooter getHeaderFooter() {
        return this.headerFooter;
    }

    public String getValue() {
        String value = this.getText();
        if (value == null) {
            return "";
        }
        return value;
    }

    public boolean areFieldsStripped() {
        return this.stripFields;
    }

    public void setAreFieldsStripped(boolean stripFields) {
        this.stripFields = stripFields;
    }

    public static String stripFields(String text) {
        return HeaderFooter.stripFields(text);
    }

    public abstract String getText();

    protected abstract void setText(String var1);

    @Override
    public String getCenter() {
        String text = this.helper.getCenterSection(this.getText());
        if (this.stripFields) {
            return XSSFHeaderFooter.stripFields(text);
        }
        return text;
    }

    @Override
    public String getLeft() {
        String text = this.helper.getLeftSection(this.getText());
        if (this.stripFields) {
            return XSSFHeaderFooter.stripFields(text);
        }
        return text;
    }

    @Override
    public String getRight() {
        String text = this.helper.getRightSection(this.getText());
        if (this.stripFields) {
            return XSSFHeaderFooter.stripFields(text);
        }
        return text;
    }

    @Override
    public void setCenter(String newCenter) {
        this.setText(this.helper.setCenterSection(this.getText(), newCenter));
    }

    @Override
    public void setLeft(String newLeft) {
        this.setText(this.helper.setLeftSection(this.getText(), newLeft));
    }

    @Override
    public void setRight(String newRight) {
        this.setText(this.helper.setRightSection(this.getText(), newRight));
    }
}

