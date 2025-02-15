/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.util;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.util.AbstractUnitConverter;
import com.jgoodies.forms.util.FormUtils;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class DefaultUnitConverter
extends AbstractUnitConverter {
    public static final String PROPERTY_AVERAGE_CHARACTER_WIDTH_TEST_STRING = "averageCharacterWidthTestString";
    public static final String PROPERTY_DEFAULT_DIALOG_FONT = "defaultDialogFont";
    public static final String OLD_AVERAGE_CHARACTER_TEST_STRING = "X";
    public static final String MODERN_AVERAGE_CHARACTER_TEST_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String BALANCED_AVERAGE_CHARACTER_TEST_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Logger LOGGER = Logger.getLogger(DefaultUnitConverter.class.getName());
    private static DefaultUnitConverter instance;
    private String averageCharWidthTestString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Font defaultDialogFont;
    private DialogBaseUnits cachedGlobalDialogBaseUnits = null;
    private DialogBaseUnits cachedDialogBaseUnits = null;
    private FontMetrics cachedFontMetrics = null;
    private Font cachedDefaultDialogFont = null;

    private DefaultUnitConverter() {
    }

    public static DefaultUnitConverter getInstance() {
        if (instance == null) {
            instance = new DefaultUnitConverter();
        }
        return instance;
    }

    public String getAverageCharacterWidthTestString() {
        return this.averageCharWidthTestString;
    }

    public void setAverageCharacterWidthTestString(String newTestString) {
        Preconditions.checkNotBlank(newTestString, "The %1$s must not be null, empty, or whitespace.", "test string");
        String oldTestString = this.averageCharWidthTestString;
        this.averageCharWidthTestString = newTestString;
        this.firePropertyChange(PROPERTY_AVERAGE_CHARACTER_WIDTH_TEST_STRING, oldTestString, newTestString);
    }

    public Font getDefaultDialogFont() {
        return this.defaultDialogFont != null ? this.defaultDialogFont : this.getCachedDefaultDialogFont();
    }

    public void setDefaultDialogFont(Font newFont) {
        Font oldFont = this.defaultDialogFont;
        this.defaultDialogFont = newFont;
        this.clearCache();
        this.firePropertyChange(PROPERTY_DEFAULT_DIALOG_FONT, oldFont, newFont);
    }

    @Override
    protected double getDialogBaseUnitsX(Component component) {
        return this.getDialogBaseUnits((Component)component).x;
    }

    @Override
    protected double getDialogBaseUnitsY(Component component) {
        return this.getDialogBaseUnits((Component)component).y;
    }

    private DialogBaseUnits getGlobalDialogBaseUnits() {
        if (this.cachedGlobalDialogBaseUnits == null) {
            this.cachedGlobalDialogBaseUnits = this.computeGlobalDialogBaseUnits();
        }
        return this.cachedGlobalDialogBaseUnits;
    }

    private DialogBaseUnits getDialogBaseUnits(Component c) {
        FormUtils.ensureValidCache();
        if (c == null) {
            return this.getGlobalDialogBaseUnits();
        }
        FontMetrics fm = c.getFontMetrics(this.getDefaultDialogFont());
        if (fm.equals(this.cachedFontMetrics)) {
            return this.cachedDialogBaseUnits;
        }
        DialogBaseUnits dialogBaseUnits = this.computeDialogBaseUnits(fm);
        this.cachedFontMetrics = fm;
        this.cachedDialogBaseUnits = dialogBaseUnits;
        return dialogBaseUnits;
    }

    private DialogBaseUnits computeDialogBaseUnits(FontMetrics metrics) {
        double averageCharWidth = this.computeAverageCharWidth(metrics, this.averageCharWidthTestString);
        int ascent = metrics.getAscent();
        double height = ascent > 14 ? (double)ascent : (double)(ascent + (15 - ascent) / 3);
        DialogBaseUnits dialogBaseUnits = new DialogBaseUnits(averageCharWidth, height);
        if (LOGGER.isLoggable(Level.CONFIG)) {
            LOGGER.config("Computed dialog base units " + dialogBaseUnits + " for: " + metrics.getFont());
        }
        return dialogBaseUnits;
    }

    private DialogBaseUnits computeGlobalDialogBaseUnits() {
        LOGGER.config("Computing global dialog base units...");
        Font dialogFont = this.getDefaultDialogFont();
        FontMetrics metrics = DefaultUnitConverter.createDefaultGlobalComponent().getFontMetrics(dialogFont);
        DialogBaseUnits globalDialogBaseUnits = this.computeDialogBaseUnits(metrics);
        return globalDialogBaseUnits;
    }

    private Font getCachedDefaultDialogFont() {
        FormUtils.ensureValidCache();
        if (this.cachedDefaultDialogFont == null) {
            this.cachedDefaultDialogFont = DefaultUnitConverter.lookupDefaultDialogFont();
        }
        return this.cachedDefaultDialogFont;
    }

    private static Font lookupDefaultDialogFont() {
        Font buttonFont = UIManager.getFont("Button.font");
        return buttonFont != null ? buttonFont : new JButton().getFont();
    }

    private static Component createDefaultGlobalComponent() {
        return new JPanel(null);
    }

    void clearCache() {
        this.cachedGlobalDialogBaseUnits = null;
        this.cachedFontMetrics = null;
        this.cachedDefaultDialogFont = null;
    }

    private static final class DialogBaseUnits {
        final double x;
        final double y;

        DialogBaseUnits(double dialogBaseUnitsX, double dialogBaseUnitsY) {
            this.x = dialogBaseUnitsX;
            this.y = dialogBaseUnitsY;
        }

        public String toString() {
            return "DBU(x=" + this.x + "; y=" + this.y + ")";
        }
    }
}

