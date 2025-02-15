/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

public enum FlatIconColors {
    ACTIONS_RED(14375008, "Actions.Red", true, false),
    ACTIONS_RED_DARK(13063248, "Actions.Red", false, true),
    ACTIONS_YELLOW(15573504, "Actions.Yellow", true, false),
    ACTIONS_YELLOW_DARK(15771442, "Actions.Yellow", false, true),
    ACTIONS_GREEN(5875817, "Actions.Green", true, false),
    ACTIONS_GREEN_DARK(4824148, "Actions.Green", false, true),
    ACTIONS_BLUE(3710934, "Actions.Blue", true, false),
    ACTIONS_BLUE_DARK(3510980, "Actions.Blue", false, true),
    ACTIONS_GREY(0x6E6E6E, "Actions.Grey", true, false),
    ACTIONS_GREY_DARK(11514291, "Actions.Grey", false, true),
    ACTIONS_GREYINLINE(8358801, "Actions.GreyInline", true, false),
    ACTIONS_GREYINLINE_DARK(8358801, "Actions.GreyInline", false, true),
    OBJECTS_GREY(10135472, "Objects.Grey"),
    OBJECTS_BLUE(4241120, "Objects.Blue"),
    OBJECTS_GREEN(6468931, "Objects.Green"),
    OBJECTS_YELLOW(16035645, "Objects.Yellow"),
    OBJECTS_YELLOW_DARK(14263107, "Objects.YellowDark"),
    OBJECTS_PURPLE(12164088, "Objects.Purple"),
    OBJECTS_PINK(16354206, "Objects.Pink"),
    OBJECTS_RED(15885602, "Objects.Red"),
    OBJECTS_RED_STATUS(0xE05555, "Objects.RedStatus"),
    OBJECTS_GREEN_ANDROID(10798649, "Objects.GreenAndroid"),
    OBJECTS_BLACK_TEXT(2301728, "Objects.BlackText");

    public final int rgb;
    public final String key;
    public final boolean light;
    public final boolean dark;

    private FlatIconColors(int rgb, String key) {
        this(rgb, key, true, true);
    }

    private FlatIconColors(int rgb, String key, boolean light, boolean dark) {
        this.rgb = rgb;
        this.key = key;
        this.light = light;
        this.dark = dark;
    }
}

