/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import java.awt.Color;
import java.util.Objects;
import javax.swing.JComponent;

public interface FlatClientProperties {
    public static final String BUTTON_TYPE = "JButton.buttonType";
    public static final String BUTTON_TYPE_SQUARE = "square";
    public static final String BUTTON_TYPE_ROUND_RECT = "roundRect";
    public static final String BUTTON_TYPE_TAB = "tab";
    public static final String BUTTON_TYPE_HELP = "help";
    public static final String BUTTON_TYPE_TOOLBAR_BUTTON = "toolBarButton";
    public static final String SELECTED_STATE = "JButton.selectedState";
    public static final String SELECTED_STATE_INDETERMINATE = "indeterminate";
    public static final String SQUARE_SIZE = "JButton.squareSize";
    public static final String MINIMUM_WIDTH = "JComponent.minimumWidth";
    public static final String MINIMUM_HEIGHT = "JComponent.minimumHeight";
    public static final String COMPONENT_ROUND_RECT = "JComponent.roundRect";
    public static final String OUTLINE = "JComponent.outline";
    public static final String OUTLINE_ERROR = "error";
    public static final String OUTLINE_WARNING = "warning";
    public static final String POPUP_DROP_SHADOW_PAINTED = "Popup.dropShadowPainted";
    public static final String POPUP_FORCE_HEAVY_WEIGHT = "Popup.forceHeavyWeight";
    public static final String PROGRESS_BAR_LARGE_HEIGHT = "JProgressBar.largeHeight";
    public static final String PROGRESS_BAR_SQUARE = "JProgressBar.square";
    public static final String MENU_BAR_EMBEDDED = "JRootPane.menuBarEmbedded";
    public static final String SCROLL_BAR_SHOW_BUTTONS = "JScrollBar.showButtons";
    public static final String SCROLL_PANE_SMOOTH_SCROLLING = "JScrollPane.smoothScrolling";
    public static final String TABBED_PANE_SHOW_TAB_SEPARATORS = "JTabbedPane.showTabSeparators";
    public static final String TABBED_PANE_SHOW_CONTENT_SEPARATOR = "JTabbedPane.showContentSeparator";
    public static final String TABBED_PANE_HAS_FULL_BORDER = "JTabbedPane.hasFullBorder";
    public static final String TABBED_PANE_HIDE_TAB_AREA_WITH_ONE_TAB = "JTabbedPane.hideTabAreaWithOneTab";
    public static final String TABBED_PANE_MINIMUM_TAB_WIDTH = "JTabbedPane.minimumTabWidth";
    public static final String TABBED_PANE_MAXIMUM_TAB_WIDTH = "JTabbedPane.maximumTabWidth";
    public static final String TABBED_PANE_TAB_HEIGHT = "JTabbedPane.tabHeight";
    public static final String TABBED_PANE_TAB_INSETS = "JTabbedPane.tabInsets";
    public static final String TABBED_PANE_TAB_AREA_INSETS = "JTabbedPane.tabAreaInsets";
    public static final String TABBED_PANE_TAB_CLOSABLE = "JTabbedPane.tabClosable";
    public static final String TABBED_PANE_TAB_CLOSE_TOOLTIPTEXT = "JTabbedPane.tabCloseToolTipText";
    public static final String TABBED_PANE_TAB_CLOSE_CALLBACK = "JTabbedPane.tabCloseCallback";
    public static final String TABBED_PANE_TABS_POPUP_POLICY = "JTabbedPane.tabsPopupPolicy";
    public static final String TABBED_PANE_SCROLL_BUTTONS_POLICY = "JTabbedPane.scrollButtonsPolicy";
    public static final String TABBED_PANE_POLICY_NEVER = "never";
    public static final String TABBED_PANE_POLICY_AS_NEEDED = "asNeeded";
    public static final String TABBED_PANE_POLICY_AS_NEEDED_SINGLE = "asNeededSingle";
    public static final String TABBED_PANE_SCROLL_BUTTONS_PLACEMENT = "JTabbedPane.scrollButtonsPlacement";
    public static final String TABBED_PANE_PLACEMENT_BOTH = "both";
    public static final String TABBED_PANE_PLACEMENT_TRAILING = "trailing";
    public static final String TABBED_PANE_TAB_AREA_ALIGNMENT = "JTabbedPane.tabAreaAlignment";
    public static final String TABBED_PANE_TAB_ALIGNMENT = "JTabbedPane.tabAlignment";
    public static final String TABBED_PANE_ALIGN_LEADING = "leading";
    public static final String TABBED_PANE_ALIGN_TRAILING = "trailing";
    public static final String TABBED_PANE_ALIGN_CENTER = "center";
    public static final String TABBED_PANE_ALIGN_FILL = "fill";
    public static final String TABBED_PANE_TAB_WIDTH_MODE = "JTabbedPane.tabWidthMode";
    public static final String TABBED_PANE_TAB_WIDTH_MODE_PREFERRED = "preferred";
    public static final String TABBED_PANE_TAB_WIDTH_MODE_EQUAL = "equal";
    public static final String TABBED_PANE_TAB_WIDTH_MODE_COMPACT = "compact";
    public static final String TABBED_PANE_TAB_ICON_PLACEMENT = "JTabbedPane.tabIconPlacement";
    public static final String TABBED_PANE_LEADING_COMPONENT = "JTabbedPane.leadingComponent";
    public static final String TABBED_PANE_TRAILING_COMPONENT = "JTabbedPane.trailingComponent";
    public static final String SELECT_ALL_ON_FOCUS_POLICY = "JTextField.selectAllOnFocusPolicy";
    public static final String SELECT_ALL_ON_FOCUS_POLICY_NEVER = "never";
    public static final String SELECT_ALL_ON_FOCUS_POLICY_ONCE = "once";
    public static final String SELECT_ALL_ON_FOCUS_POLICY_ALWAYS = "always";
    public static final String PLACEHOLDER_TEXT = "JTextField.placeholderText";
    public static final String TAB_BUTTON_UNDERLINE_HEIGHT = "JToggleButton.tab.underlineHeight";
    public static final String TAB_BUTTON_UNDERLINE_COLOR = "JToggleButton.tab.underlineColor";
    public static final String TAB_BUTTON_SELECTED_BACKGROUND = "JToggleButton.tab.selectedBackground";
    public static final String TREE_WIDE_SELECTION = "JTree.wideSelection";
    public static final String TREE_PAINT_SELECTION = "JTree.paintSelection";

    public static boolean clientPropertyEquals(JComponent c, String key, Object value) {
        return Objects.equals(c.getClientProperty(key), value);
    }

    public static boolean clientPropertyBoolean(JComponent c, String key, boolean defaultValue) {
        Object value = c.getClientProperty(key);
        return value instanceof Boolean ? (Boolean)value : defaultValue;
    }

    public static Boolean clientPropertyBooleanStrict(JComponent c, String key, Boolean defaultValue) {
        Object value = c.getClientProperty(key);
        return value instanceof Boolean ? (Boolean)value : defaultValue;
    }

    public static int clientPropertyInt(JComponent c, String key, int defaultValue) {
        Object value = c.getClientProperty(key);
        return value instanceof Integer ? (Integer)value : defaultValue;
    }

    public static Color clientPropertyColor(JComponent c, String key, Color defaultValue) {
        Object value = c.getClientProperty(key);
        return value instanceof Color ? (Color)value : defaultValue;
    }
}

