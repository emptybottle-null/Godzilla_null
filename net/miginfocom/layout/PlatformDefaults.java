/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.awt.Toolkit;
import java.util.HashMap;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.InCellGapProvider;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.UnitValue;

public final class PlatformDefaults {
    public static String VISUAL_PADDING_PROPERTY = "visualPadding";
    private static int DEF_H_UNIT = 1;
    private static int DEF_V_UNIT = 2;
    private static InCellGapProvider GAP_PROVIDER = null;
    private static volatile int MOD_COUNT = 0;
    private static final UnitValue LPX6 = new UnitValue(6.0f, 1, null);
    private static final UnitValue LPX7 = new UnitValue(7.0f, 1, null);
    private static final UnitValue LPX11 = new UnitValue(11.0f, 1, null);
    private static final UnitValue LPX12 = new UnitValue(12.0f, 1, null);
    private static final UnitValue LPX16 = new UnitValue(16.0f, 1, null);
    private static final UnitValue LPX18 = new UnitValue(18.0f, 1, null);
    private static final UnitValue LPX20 = new UnitValue(20.0f, 1, null);
    private static final UnitValue LPY6 = new UnitValue(6.0f, 2, null);
    private static final UnitValue LPY7 = new UnitValue(7.0f, 2, null);
    private static final UnitValue LPY11 = new UnitValue(11.0f, 2, null);
    private static final UnitValue LPY12 = new UnitValue(12.0f, 2, null);
    private static final UnitValue LPY16 = new UnitValue(16.0f, 2, null);
    private static final UnitValue LPY18 = new UnitValue(18.0f, 2, null);
    private static final UnitValue LPY20 = new UnitValue(20.0f, 2, null);
    public static final int WINDOWS_XP = 0;
    public static final int MAC_OSX = 1;
    public static final int GNOME = 2;
    private static int CUR_PLAF = 0;
    private static final UnitValue[] PANEL_INS = new UnitValue[4];
    private static final UnitValue[] DIALOG_INS = new UnitValue[4];
    private static String BUTTON_FORMAT = null;
    private static final HashMap<String, UnitValue> HOR_DEFS = new HashMap(32);
    private static final HashMap<String, UnitValue> VER_DEFS = new HashMap(32);
    private static BoundSize DEF_VGAP = null;
    private static BoundSize DEF_HGAP = null;
    static BoundSize RELATED_X = null;
    static BoundSize RELATED_Y = null;
    static BoundSize UNRELATED_X = null;
    static BoundSize UNRELATED_Y = null;
    private static UnitValue BUTT_WIDTH = null;
    private static UnitValue BUTT_PADDING = null;
    private static Float horScale = null;
    private static Float verScale = null;
    public static final int BASE_FONT_SIZE = 100;
    public static final int BASE_SCALE_FACTOR = 101;
    public static final int BASE_REAL_PIXEL = 102;
    private static int LP_BASE = 101;
    private static Integer BASE_DPI_FORCED = null;
    private static int BASE_DPI = 96;
    private static boolean dra = true;
    private static final HashMap<String, int[]> VISUAL_BOUNDS = new HashMap(64);

    public static int getCurrentPlatform() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac OS")) {
            return 1;
        }
        if (os.startsWith("Linux")) {
            return 2;
        }
        return 0;
    }

    private PlatformDefaults() {
    }

    public static void setPlatform(int plaf) {
        switch (plaf) {
            case 0: {
                PlatformDefaults.setDefaultVisualPadding("TabbedPane." + VISUAL_PADDING_PROPERTY, new int[]{1, 0, 1, 2});
                PlatformDefaults.setRelatedGap(LPX7, LPY7);
                PlatformDefaults.setUnrelatedGap(LPX11, LPY11);
                PlatformDefaults.setParagraphGap(LPX20, LPY20);
                PlatformDefaults.setIndentGap(LPX11, LPY11);
                PlatformDefaults.setGridCellGap(LPX7, LPY7);
                PlatformDefaults.setMinimumButtonWidth(new UnitValue(75.0f, 1, null));
                PlatformDefaults.setButtonOrder("L_E+U+YNBXOCAH_I_R");
                PlatformDefaults.setDialogInsets(LPY11, LPX11, LPY11, LPX11);
                PlatformDefaults.setPanelInsets(LPY7, LPX7, LPY7, LPX7);
                break;
            }
            case 1: {
                PlatformDefaults.setDefaultVisualPadding("Button." + VISUAL_PADDING_PROPERTY, new int[]{3, 6, 5, 6});
                PlatformDefaults.setDefaultVisualPadding("Button.icon." + VISUAL_PADDING_PROPERTY, new int[]{3, 2, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("Button.square." + VISUAL_PADDING_PROPERTY, new int[]{4, 4, 4, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.square.icon." + VISUAL_PADDING_PROPERTY, new int[]{4, 4, 4, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.gradient." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.gradient.icon." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.bevel." + VISUAL_PADDING_PROPERTY, new int[]{2, 2, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("Button.bevel.icon." + VISUAL_PADDING_PROPERTY, new int[]{2, 2, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("Button.textured." + VISUAL_PADDING_PROPERTY, new int[]{3, 2, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("Button.textured.icon." + VISUAL_PADDING_PROPERTY, new int[]{3, 2, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("Button.roundRect." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.roundRect.icon." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.recessed." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.recessed.icon." + VISUAL_PADDING_PROPERTY, new int[]{5, 4, 5, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.help." + VISUAL_PADDING_PROPERTY, new int[]{4, 3, 3, 4});
                PlatformDefaults.setDefaultVisualPadding("Button.help.icon." + VISUAL_PADDING_PROPERTY, new int[]{4, 3, 3, 4});
                PlatformDefaults.setDefaultVisualPadding("ComboBox." + VISUAL_PADDING_PROPERTY, new int[]{2, 4, 4, 5});
                PlatformDefaults.setDefaultVisualPadding("ComboBox.isPopDown." + VISUAL_PADDING_PROPERTY, new int[]{2, 5, 4, 5});
                PlatformDefaults.setDefaultVisualPadding("ComboBox.isSquare." + VISUAL_PADDING_PROPERTY, new int[]{1, 6, 5, 7});
                PlatformDefaults.setDefaultVisualPadding("ComboBox.editable." + VISUAL_PADDING_PROPERTY, new int[]{3, 3, 3, 2});
                PlatformDefaults.setDefaultVisualPadding("ComboBox.editable.isSquare." + VISUAL_PADDING_PROPERTY, new int[]{3, 3, 3, 1});
                PlatformDefaults.setDefaultVisualPadding("TextField." + VISUAL_PADDING_PROPERTY, new int[]{3, 3, 3, 3});
                PlatformDefaults.setDefaultVisualPadding("TabbedPane." + VISUAL_PADDING_PROPERTY, new int[]{4, 8, 11, 8});
                PlatformDefaults.setDefaultVisualPadding("Spinner." + VISUAL_PADDING_PROPERTY, new int[]{3, 3, 3, 1});
                PlatformDefaults.setDefaultVisualPadding("RadioButton." + VISUAL_PADDING_PROPERTY, new int[]{4, 6, 3, 5});
                PlatformDefaults.setDefaultVisualPadding("RadioButton.small." + VISUAL_PADDING_PROPERTY, new int[]{4, 6, 3, 5});
                PlatformDefaults.setDefaultVisualPadding("RadioButton.mini." + VISUAL_PADDING_PROPERTY, new int[]{5, 7, 4, 5});
                PlatformDefaults.setDefaultVisualPadding("CheckBox." + VISUAL_PADDING_PROPERTY, new int[]{5, 7, 4, 5});
                PlatformDefaults.setDefaultVisualPadding("CheckBox.small." + VISUAL_PADDING_PROPERTY, new int[]{5, 7, 4, 5});
                PlatformDefaults.setDefaultVisualPadding("CheckBox.mini." + VISUAL_PADDING_PROPERTY, new int[]{6, 7, 3, 5});
                PlatformDefaults.setRelatedGap(LPX7, LPY7);
                PlatformDefaults.setUnrelatedGap(LPX11, LPY11);
                PlatformDefaults.setParagraphGap(LPX20, LPY20);
                PlatformDefaults.setIndentGap(LPX11, LPY11);
                PlatformDefaults.setGridCellGap(LPX7, LPY7);
                PlatformDefaults.setMinimumButtonWidth(new UnitValue(70.0f, 1, null));
                PlatformDefaults.setMinimumButtonPadding(new UnitValue(8.0f, 1, null));
                PlatformDefaults.setButtonOrder("L_HE+U+NYBXCOA_I_R");
                PlatformDefaults.setDialogInsets(LPY20, LPX20, LPY20, LPX20);
                PlatformDefaults.setPanelInsets(LPY16, LPX16, LPY16, LPX16);
                break;
            }
            case 2: {
                PlatformDefaults.setRelatedGap(LPX6, LPY6);
                PlatformDefaults.setUnrelatedGap(LPX12, LPY12);
                PlatformDefaults.setParagraphGap(LPX18, LPY18);
                PlatformDefaults.setIndentGap(LPX12, LPY12);
                PlatformDefaults.setGridCellGap(LPX6, LPY6);
                PlatformDefaults.setMinimumButtonWidth(new UnitValue(85.0f, 1, null));
                PlatformDefaults.setButtonOrder("L_HE+UNYACBXO_I_R");
                PlatformDefaults.setDialogInsets(LPY12, LPX12, LPY12, LPX12);
                PlatformDefaults.setPanelInsets(LPY6, LPX6, LPY6, LPX6);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown platform: " + plaf);
            }
        }
        CUR_PLAF = plaf;
        BASE_DPI = BASE_DPI_FORCED != null ? BASE_DPI_FORCED : PlatformDefaults.getPlatformDPI(plaf);
    }

    public static void setDefaultVisualPadding(String key, int[] insets) {
        VISUAL_BOUNDS.put(key, insets);
    }

    public static int[] getDefaultVisualPadding(String key) {
        return VISUAL_BOUNDS.get(key);
    }

    public static int getPlatformDPI(int plaf) {
        switch (plaf) {
            case 0: 
            case 2: {
                return 96;
            }
            case 1: {
                try {
                    return Toolkit.getDefaultToolkit().getScreenResolution();
                } catch (Throwable t) {
                    return 72;
                }
            }
        }
        throw new IllegalArgumentException("Unknown platform: " + plaf);
    }

    public static int getPlatform() {
        return CUR_PLAF;
    }

    public static int getDefaultDPI() {
        return BASE_DPI;
    }

    public static void setDefaultDPI(Integer dpi) {
        BASE_DPI = dpi != null ? dpi : PlatformDefaults.getPlatformDPI(CUR_PLAF);
        BASE_DPI_FORCED = dpi;
    }

    public static Float getHorizontalScaleFactor() {
        return horScale;
    }

    public static void setHorizontalScaleFactor(Float f) {
        if (!LayoutUtil.equals(horScale, f)) {
            horScale = f;
            ++MOD_COUNT;
        }
    }

    public static Float getVerticalScaleFactor() {
        return verScale;
    }

    public static void setVerticalScaleFactor(Float f) {
        if (!LayoutUtil.equals(verScale, f)) {
            verScale = f;
            ++MOD_COUNT;
        }
    }

    public static int getLogicalPixelBase() {
        return LP_BASE;
    }

    public static void setLogicalPixelBase(int base) {
        if (LP_BASE != base) {
            if (base < 100 || base > 102) {
                throw new IllegalArgumentException("Unrecognized base: " + base);
            }
            LP_BASE = base;
            ++MOD_COUNT;
        }
    }

    public static void setRelatedGap(UnitValue x, UnitValue y) {
        PlatformDefaults.setUnitValue(new String[]{"r", "rel", "related"}, x, y);
        RELATED_X = new BoundSize(x, x, null, "rel:rel");
        RELATED_Y = new BoundSize(y, y, null, "rel:rel");
    }

    public static void setUnrelatedGap(UnitValue x, UnitValue y) {
        PlatformDefaults.setUnitValue(new String[]{"u", "unrel", "unrelated"}, x, y);
        UNRELATED_X = new BoundSize(x, x, null, "unrel:unrel");
        UNRELATED_Y = new BoundSize(y, y, null, "unrel:unrel");
    }

    public static void setParagraphGap(UnitValue x, UnitValue y) {
        PlatformDefaults.setUnitValue(new String[]{"p", "para", "paragraph"}, x, y);
    }

    public static void setIndentGap(UnitValue x, UnitValue y) {
        PlatformDefaults.setUnitValue(new String[]{"i", "ind", "indent"}, x, y);
    }

    public static void setGridCellGap(UnitValue x, UnitValue y) {
        if (x != null) {
            DEF_HGAP = new BoundSize(x, x, null, null);
        }
        if (y != null) {
            DEF_VGAP = new BoundSize(y, y, null, null);
        }
        ++MOD_COUNT;
    }

    public static void setMinimumButtonWidth(UnitValue width) {
        BUTT_WIDTH = width;
        ++MOD_COUNT;
    }

    public static UnitValue getMinimumButtonWidth() {
        return BUTT_WIDTH;
    }

    public static void setMinimumButtonPadding(UnitValue padding) {
        BUTT_PADDING = padding;
        ++MOD_COUNT;
    }

    public static UnitValue getMinimumButtonPadding() {
        return BUTT_PADDING;
    }

    public static float getMinimumButtonWidthIncludingPadding(float refValue, ContainerWrapper parent, ComponentWrapper comp) {
        int buttonMinWidth = PlatformDefaults.getMinimumButtonWidth().getPixels(refValue, parent, comp);
        if (comp != null && PlatformDefaults.getMinimumButtonPadding() != null) {
            return Math.max(comp.getMinimumWidth(comp.getWidth()) + PlatformDefaults.getMinimumButtonPadding().getPixels(refValue, parent, comp) * 2, buttonMinWidth);
        }
        return buttonMinWidth;
    }

    public static UnitValue getUnitValueX(String unit) {
        return HOR_DEFS.get(unit);
    }

    public static UnitValue getUnitValueY(String unit) {
        return VER_DEFS.get(unit);
    }

    public static void setUnitValue(String[] unitStrings, UnitValue x, UnitValue y) {
        for (String unitString : unitStrings) {
            String s = unitString.toLowerCase().trim();
            if (x != null) {
                HOR_DEFS.put(s, x);
            }
            if (y == null) continue;
            VER_DEFS.put(s, y);
        }
        ++MOD_COUNT;
    }

    static int convertToPixels(float value, String unit, boolean isHor, float ref, ContainerWrapper parent, ComponentWrapper comp) {
        UnitValue uv = (isHor ? HOR_DEFS : VER_DEFS).get(unit);
        return uv != null ? Math.round(value * (float)uv.getPixels(ref, parent, comp)) : -87654312;
    }

    public static String getButtonOrder() {
        return BUTTON_FORMAT;
    }

    public static void setButtonOrder(String order) {
        BUTTON_FORMAT = order;
        ++MOD_COUNT;
    }

    static String getTagForChar(char c) {
        switch (c) {
            case 'o': {
                return "ok";
            }
            case 'c': {
                return "cancel";
            }
            case 'h': {
                return "help";
            }
            case 'e': {
                return "help2";
            }
            case 'y': {
                return "yes";
            }
            case 'n': {
                return "no";
            }
            case 'a': {
                return "apply";
            }
            case 'x': {
                return "next";
            }
            case 'b': {
                return "back";
            }
            case 'i': {
                return "finish";
            }
            case 'l': {
                return "left";
            }
            case 'r': {
                return "right";
            }
            case 'u': {
                return "other";
            }
        }
        return null;
    }

    public static BoundSize getGridGapX() {
        return DEF_HGAP;
    }

    public static BoundSize getGridGapY() {
        return DEF_VGAP;
    }

    public static UnitValue getDialogInsets(int side) {
        return DIALOG_INS[side];
    }

    public static void setDialogInsets(UnitValue top, UnitValue left, UnitValue bottom, UnitValue right) {
        if (top != null) {
            PlatformDefaults.DIALOG_INS[0] = top;
        }
        if (left != null) {
            PlatformDefaults.DIALOG_INS[1] = left;
        }
        if (bottom != null) {
            PlatformDefaults.DIALOG_INS[2] = bottom;
        }
        if (right != null) {
            PlatformDefaults.DIALOG_INS[3] = right;
        }
        ++MOD_COUNT;
    }

    public static UnitValue getPanelInsets(int side) {
        return PANEL_INS[side];
    }

    public static void setPanelInsets(UnitValue top, UnitValue left, UnitValue bottom, UnitValue right) {
        if (top != null) {
            PlatformDefaults.PANEL_INS[0] = top;
        }
        if (left != null) {
            PlatformDefaults.PANEL_INS[1] = left;
        }
        if (bottom != null) {
            PlatformDefaults.PANEL_INS[2] = bottom;
        }
        if (right != null) {
            PlatformDefaults.PANEL_INS[3] = right;
        }
        ++MOD_COUNT;
    }

    public static float getLabelAlignPercentage() {
        return CUR_PLAF == 1 ? 1.0f : 0.0f;
    }

    static BoundSize getDefaultComponentGap(ComponentWrapper comp, ComponentWrapper adjacentComp, int adjacentSide, String tag, boolean isLTR) {
        if (GAP_PROVIDER != null) {
            return GAP_PROVIDER.getDefaultGap(comp, adjacentComp, adjacentSide, tag, isLTR);
        }
        if (adjacentComp == null) {
            return null;
        }
        return adjacentSide == 2 || adjacentSide == 4 ? RELATED_X : RELATED_Y;
    }

    public static InCellGapProvider getGapProvider() {
        return GAP_PROVIDER;
    }

    public static void setGapProvider(InCellGapProvider provider) {
        GAP_PROVIDER = provider;
    }

    public static int getModCount() {
        return MOD_COUNT;
    }

    public void invalidate() {
        ++MOD_COUNT;
    }

    public static int getDefaultHorizontalUnit() {
        return DEF_H_UNIT;
    }

    public static void setDefaultHorizontalUnit(int unit) {
        if (unit < 0 || unit > 27) {
            throw new IllegalArgumentException("Illegal Unit: " + unit);
        }
        if (DEF_H_UNIT != unit) {
            DEF_H_UNIT = unit;
            ++MOD_COUNT;
        }
    }

    public static int getDefaultVerticalUnit() {
        return DEF_V_UNIT;
    }

    public static void setDefaultVerticalUnit(int unit) {
        if (unit < 0 || unit > 27) {
            throw new IllegalArgumentException("Illegal Unit: " + unit);
        }
        if (DEF_V_UNIT != unit) {
            DEF_V_UNIT = unit;
            ++MOD_COUNT;
        }
    }

    public static boolean getDefaultRowAlignmentBaseline() {
        return dra;
    }

    public static void setDefaultRowAlignmentBaseline(boolean b) {
        dra = b;
    }

    static {
        PlatformDefaults.setPlatform(PlatformDefaults.getCurrentPlatform());
        MOD_COUNT = 0;
    }
}

