/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import net.miginfocom.layout.ContainerWrapper;

public interface ComponentWrapper {
    public static final int TYPE_UNSET = -1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_CONTAINER = 1;
    public static final int TYPE_LABEL = 2;
    public static final int TYPE_TEXT_FIELD = 3;
    public static final int TYPE_TEXT_AREA = 4;
    public static final int TYPE_BUTTON = 5;
    public static final int TYPE_LIST = 6;
    public static final int TYPE_TABLE = 7;
    public static final int TYPE_SCROLL_PANE = 8;
    public static final int TYPE_IMAGE = 9;
    public static final int TYPE_PANEL = 10;
    public static final int TYPE_COMBO_BOX = 11;
    public static final int TYPE_SLIDER = 12;
    public static final int TYPE_SPINNER = 13;
    public static final int TYPE_PROGRESS_BAR = 14;
    public static final int TYPE_TREE = 15;
    public static final int TYPE_CHECK_BOX = 16;
    public static final int TYPE_SCROLL_BAR = 17;
    public static final int TYPE_SEPARATOR = 18;
    public static final int TYPE_TABBED_PANE = 19;

    public Object getComponent();

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public int getScreenLocationX();

    public int getScreenLocationY();

    public int getMinimumWidth(int var1);

    public int getMinimumHeight(int var1);

    public int getPreferredWidth(int var1);

    public int getPreferredHeight(int var1);

    public int getMaximumWidth(int var1);

    public int getMaximumHeight(int var1);

    public void setBounds(int var1, int var2, int var3, int var4);

    public boolean isVisible();

    public int getBaseline(int var1, int var2);

    public boolean hasBaseline();

    public ContainerWrapper getParent();

    public float getPixelUnitFactor(boolean var1);

    public int getHorizontalScreenDPI();

    public int getVerticalScreenDPI();

    public int getScreenWidth();

    public int getScreenHeight();

    public String getLinkId();

    public int getLayoutHashCode();

    public int[] getVisualPadding();

    public void paintDebugOutline(boolean var1);

    public int getComponentType(boolean var1);

    public int getContentBias();
}

