/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

import java.awt.Color;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.poi.util.Removal;

public class HSSFColor
implements org.apache.poi.ss.usermodel.Color {
    private static Map<Integer, HSSFColor> indexHash;
    private static Map<HSSFColorPredefined, HSSFColor> enumList;
    private Color color;
    private int index;
    private int index2;

    public HSSFColor() {
        this(64, -1, Color.BLACK);
    }

    public HSSFColor(int index, int index2, Color color) {
        this.index = index;
        this.index2 = index2;
        this.color = color;
    }

    public static synchronized Map<Integer, HSSFColor> getIndexHash() {
        if (indexHash == null) {
            indexHash = Collections.unmodifiableMap(HSSFColor.createColorsByIndexMap());
        }
        return indexHash;
    }

    public static Map<Integer, HSSFColor> getMutableIndexHash() {
        return HSSFColor.createColorsByIndexMap();
    }

    private static Map<Integer, HSSFColor> createColorsByIndexMap() {
        Map<HSSFColorPredefined, HSSFColor> eList = HSSFColor.mapEnumToColorClass();
        HashMap<Integer, HSSFColor> result = new HashMap<Integer, HSSFColor>(eList.size() * 3 / 2);
        for (Map.Entry<HSSFColorPredefined, HSSFColor> colorRef : eList.entrySet()) {
            Integer index2;
            Integer index1 = colorRef.getKey().getIndex();
            if (!result.containsKey(index1)) {
                result.put(index1, colorRef.getValue());
            }
            if ((index2 = Integer.valueOf(colorRef.getKey().getIndex2())) == -1 || result.containsKey(index2)) continue;
            result.put(index2, colorRef.getValue());
        }
        return result;
    }

    public static Map<String, HSSFColor> getTripletHash() {
        return HSSFColor.createColorsByHexStringMap();
    }

    private static Map<String, HSSFColor> createColorsByHexStringMap() {
        Map<HSSFColorPredefined, HSSFColor> eList = HSSFColor.mapEnumToColorClass();
        HashMap<String, HSSFColor> result = new HashMap<String, HSSFColor>(eList.size());
        for (Map.Entry<HSSFColorPredefined, HSSFColor> colorRef : eList.entrySet()) {
            String hexString = colorRef.getKey().getHexString();
            if (result.containsKey(hexString)) continue;
            result.put(hexString, colorRef.getValue());
        }
        return result;
    }

    @Deprecated
    @Removal(version="3.18")
    private static synchronized Map<HSSFColorPredefined, HSSFColor> mapEnumToColorClass() {
        if (enumList == null) {
            enumList = new EnumMap<HSSFColorPredefined, HSSFColor>(HSSFColorPredefined.class);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.BLACK);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.BROWN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.OLIVE_GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.DARK_GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.DARK_TEAL);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.DARK_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.INDIGO);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GREY_80_PERCENT);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.ORANGE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.DARK_YELLOW);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.TEAL);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.BLUE_GREY);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GREY_50_PERCENT);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.RED);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_ORANGE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIME);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.SEA_GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.AQUA);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.VIOLET);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GREY_40_PERCENT);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.PINK);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GOLD);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.YELLOW);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.BRIGHT_GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.TURQUOISE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.DARK_RED);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.SKY_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.PLUM);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.GREY_25_PERCENT);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.ROSE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_YELLOW);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_GREEN);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_TURQUOISE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.PALE_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LAVENDER);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.WHITE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.CORNFLOWER_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LEMON_CHIFFON);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.MAROON);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.ORCHID);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.CORAL);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.ROYAL_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE);
            HSSFColor.addHSSFColorPredefined(HSSFColorPredefined.TAN);
        }
        return enumList;
    }

    private static void addHSSFColorPredefined(HSSFColorPredefined color) {
        enumList.put(color, color.getColor());
    }

    public short getIndex() {
        return (short)this.index;
    }

    public short getIndex2() {
        return (short)this.index2;
    }

    public short[] getTriplet() {
        return new short[]{(short)this.color.getRed(), (short)this.color.getGreen(), (short)this.color.getBlue()};
    }

    public String getHexString() {
        return (Integer.toHexString(this.color.getRed() * 257) + ":" + Integer.toHexString(this.color.getGreen() * 257) + ":" + Integer.toHexString(this.color.getBlue() * 257)).toUpperCase(Locale.ROOT);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        HSSFColor hssfColor = (HSSFColor)o;
        if (this.index != hssfColor.index) {
            return false;
        }
        if (this.index2 != hssfColor.index2) {
            return false;
        }
        return this.color != null ? this.color.equals(hssfColor.color) : hssfColor.color == null;
    }

    public int hashCode() {
        return Objects.hash(this.color, this.index, this.index2);
    }

    public static HSSFColor toHSSFColor(org.apache.poi.ss.usermodel.Color color) {
        if (color != null && !(color instanceof HSSFColor)) {
            throw new IllegalArgumentException("Only HSSFColor objects are supported, but had " + color.getClass());
        }
        return (HSSFColor)color;
    }

    public static enum HSSFColorPredefined {
        BLACK(8, -1, 0),
        BROWN(60, -1, 0x993300),
        OLIVE_GREEN(59, -1, 0x333300),
        DARK_GREEN(58, -1, 13056),
        DARK_TEAL(56, -1, 13158),
        DARK_BLUE(18, 32, 128),
        INDIGO(62, -1, 0x333399),
        GREY_80_PERCENT(63, -1, 0x333333),
        ORANGE(53, -1, 0xFF6600),
        DARK_YELLOW(19, -1, 0x808000),
        GREEN(17, -1, 32768),
        TEAL(21, 38, 32896),
        BLUE(12, 39, 255),
        BLUE_GREY(54, -1, 0x666699),
        GREY_50_PERCENT(23, -1, 0x808080),
        RED(10, -1, 0xFF0000),
        LIGHT_ORANGE(52, -1, 0xFF9900),
        LIME(50, -1, 0x99CC00),
        SEA_GREEN(57, -1, 0x339966),
        AQUA(49, -1, 0x33CCCC),
        LIGHT_BLUE(48, -1, 0x3366FF),
        VIOLET(20, 36, 0x800080),
        GREY_40_PERCENT(55, -1, 0x969696),
        PINK(14, 33, 0xFF00FF),
        GOLD(51, -1, 0xFFCC00),
        YELLOW(13, 34, 0xFFFF00),
        BRIGHT_GREEN(11, -1, 65280),
        TURQUOISE(15, 35, 65535),
        DARK_RED(16, 37, 0x800000),
        SKY_BLUE(40, -1, 52479),
        PLUM(61, 25, 0x993366),
        GREY_25_PERCENT(22, -1, 0xC0C0C0),
        ROSE(45, -1, 0xFF99CC),
        LIGHT_YELLOW(43, -1, 0xFFFF99),
        LIGHT_GREEN(42, -1, 0xCCFFCC),
        LIGHT_TURQUOISE(41, 27, 0xCCFFFF),
        PALE_BLUE(44, -1, 0x99CCFF),
        LAVENDER(46, -1, 0xCC99FF),
        WHITE(9, -1, 0xFFFFFF),
        CORNFLOWER_BLUE(24, -1, 0x9999FF),
        LEMON_CHIFFON(26, -1, 0xFFFFCC),
        MAROON(25, -1, 0x7F0000),
        ORCHID(28, -1, 0x660066),
        CORAL(29, -1, 0xFF8080),
        ROYAL_BLUE(30, -1, 26316),
        LIGHT_CORNFLOWER_BLUE(31, -1, 0xCCCCFF),
        TAN(47, -1, 0xFFCC99),
        AUTOMATIC(64, -1, 0);

        private HSSFColor color;

        private HSSFColorPredefined(int index, int index2, int rgb) {
            this.color = new HSSFColor(index, index2, new Color(rgb));
        }

        public short getIndex() {
            return this.color.getIndex();
        }

        public short getIndex2() {
            return this.color.getIndex2();
        }

        public short[] getTriplet() {
            return this.color.getTriplet();
        }

        public String getHexString() {
            return this.color.getHexString();
        }

        public HSSFColor getColor() {
            return new HSSFColor(this.getIndex(), this.getIndex2(), this.color.color);
        }
    }
}

