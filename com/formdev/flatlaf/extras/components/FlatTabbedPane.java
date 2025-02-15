/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import java.awt.Component;
import java.awt.Insets;
import java.util.function.BiConsumer;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class FlatTabbedPane
extends JTabbedPane
implements FlatComponentExtension {
    public boolean isShowTabSeparators() {
        return this.getClientPropertyBoolean((Object)"JTabbedPane.showTabSeparators", "TabbedPane.showTabSeparators");
    }

    public void setShowTabSeparators(boolean showTabSeparators) {
        this.putClientProperty("JTabbedPane.showTabSeparators", showTabSeparators);
    }

    public boolean isShowContentSeparators() {
        return this.getClientPropertyBoolean((Object)"JTabbedPane.showContentSeparator", true);
    }

    public void setShowContentSeparators(boolean showContentSeparators) {
        this.putClientPropertyBoolean("JTabbedPane.showContentSeparator", showContentSeparators, true);
    }

    public boolean isHasFullBorder() {
        return this.getClientPropertyBoolean((Object)"JTabbedPane.hasFullBorder", "TabbedPane.hasFullBorder");
    }

    public void setHasFullBorder(boolean hasFullBorder) {
        this.putClientProperty("JTabbedPane.hasFullBorder", hasFullBorder);
    }

    public boolean isHideTabAreaWithOneTab() {
        return this.getClientPropertyBoolean((Object)"JTabbedPane.hideTabAreaWithOneTab", false);
    }

    public void setHideTabAreaWithOneTab(boolean hideTabAreaWithOneTab) {
        this.putClientPropertyBoolean("JTabbedPane.hideTabAreaWithOneTab", hideTabAreaWithOneTab, false);
    }

    public int getMinimumTabWidth() {
        return this.getClientPropertyInt((Object)"JTabbedPane.minimumTabWidth", "TabbedPane.minimumTabWidth");
    }

    public void setMinimumTabWidth(int minimumTabWidth) {
        this.putClientProperty("JTabbedPane.minimumTabWidth", minimumTabWidth >= 0 ? Integer.valueOf(minimumTabWidth) : null);
    }

    public int getMinimumTabWidth(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        return FlatClientProperties.clientPropertyInt(c, "JTabbedPane.minimumTabWidth", 0);
    }

    public void setMinimumTabWidth(int tabIndex, int minimumTabWidth) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.minimumTabWidth", minimumTabWidth >= 0 ? Integer.valueOf(minimumTabWidth) : null);
    }

    public int getMaximumTabWidth() {
        return this.getClientPropertyInt((Object)"JTabbedPane.maximumTabWidth", "TabbedPane.maximumTabWidth");
    }

    public void setMaximumTabWidth(int maximumTabWidth) {
        this.putClientProperty("JTabbedPane.maximumTabWidth", maximumTabWidth >= 0 ? Integer.valueOf(maximumTabWidth) : null);
    }

    public int getMaximumTabWidth(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        return FlatClientProperties.clientPropertyInt(c, "JTabbedPane.maximumTabWidth", 0);
    }

    public void setMaximumTabWidth(int tabIndex, int maximumTabWidth) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.maximumTabWidth", maximumTabWidth >= 0 ? Integer.valueOf(maximumTabWidth) : null);
    }

    public int getTabHeight() {
        return this.getClientPropertyInt((Object)"JTabbedPane.tabHeight", "TabbedPane.tabHeight");
    }

    public void setTabHeight(int tabHeight) {
        this.putClientProperty("JTabbedPane.tabHeight", tabHeight >= 0 ? Integer.valueOf(tabHeight) : null);
    }

    public Insets getTabInsets() {
        return this.getClientPropertyInsets("JTabbedPane.tabInsets", "TabbedPane.tabInsets");
    }

    public void setTabInsets(Insets tabInsets) {
        this.putClientProperty("JTabbedPane.tabInsets", tabInsets);
    }

    public Insets getTabInsets(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        return (Insets)c.getClientProperty("JTabbedPane.tabInsets");
    }

    public void setTabInsets(int tabIndex, Insets tabInsets) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.tabInsets", tabInsets);
    }

    public Insets getTabAreaInsets() {
        return this.getClientPropertyInsets("JTabbedPane.tabAreaInsets", "TabbedPane.tabAreaInsets");
    }

    public void setTabAreaInsets(Insets tabAreaInsets) {
        this.putClientProperty("JTabbedPane.tabAreaInsets", tabAreaInsets);
    }

    public boolean isTabsClosable() {
        return this.getClientPropertyBoolean((Object)"JTabbedPane.tabClosable", false);
    }

    public void setTabsClosable(boolean tabClosable) {
        this.putClientPropertyBoolean("JTabbedPane.tabClosable", tabClosable, false);
    }

    public Boolean isTabClosable(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        Object value = c.getClientProperty("JTabbedPane.tabClosable");
        return value instanceof Boolean ? ((Boolean)value).booleanValue() : this.isTabsClosable();
    }

    public void setTabClosable(int tabIndex, boolean tabClosable) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.tabClosable", tabClosable);
    }

    public String getTabCloseToolTipText() {
        return (String)this.getClientProperty("JTabbedPane.tabCloseToolTipText");
    }

    public void setTabCloseToolTipText(String tabCloseToolTipText) {
        this.putClientProperty("JTabbedPane.tabCloseToolTipText", tabCloseToolTipText);
    }

    public String getTabCloseToolTipText(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        return (String)c.getClientProperty("JTabbedPane.tabCloseToolTipText");
    }

    public void setTabCloseToolTipText(int tabIndex, String tabCloseToolTipText) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.tabCloseToolTipText", tabCloseToolTipText);
    }

    public BiConsumer<JTabbedPane, Integer> getTabCloseCallback() {
        return (BiConsumer)this.getClientProperty("JTabbedPane.tabCloseCallback");
    }

    public void setTabCloseCallback(BiConsumer<JTabbedPane, Integer> tabCloseCallback) {
        this.putClientProperty("JTabbedPane.tabCloseCallback", tabCloseCallback);
    }

    public BiConsumer<JTabbedPane, Integer> getTabCloseCallback(int tabIndex) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        return (BiConsumer)c.getClientProperty("JTabbedPane.tabCloseCallback");
    }

    public void setTabCloseCallback(int tabIndex, BiConsumer<JTabbedPane, Integer> tabCloseCallback) {
        JComponent c = (JComponent)this.getComponentAt(tabIndex);
        c.putClientProperty("JTabbedPane.tabCloseCallback", tabCloseCallback);
    }

    public TabsPopupPolicy getTabsPopupPolicy() {
        return this.getClientPropertyEnumString("JTabbedPane.tabsPopupPolicy", TabsPopupPolicy.class, "TabbedPane.tabsPopupPolicy", TabsPopupPolicy.asNeeded);
    }

    public void setTabsPopupPolicy(TabsPopupPolicy tabsPopupPolicy) {
        this.putClientPropertyEnumString("JTabbedPane.tabsPopupPolicy", tabsPopupPolicy);
    }

    public ScrollButtonsPolicy getScrollButtonsPolicy() {
        return this.getClientPropertyEnumString("JTabbedPane.scrollButtonsPolicy", ScrollButtonsPolicy.class, "TabbedPane.scrollButtonsPolicy", ScrollButtonsPolicy.asNeededSingle);
    }

    public void setScrollButtonsPolicy(ScrollButtonsPolicy scrollButtonsPolicy) {
        this.putClientPropertyEnumString("JTabbedPane.scrollButtonsPolicy", scrollButtonsPolicy);
    }

    public ScrollButtonsPlacement getScrollButtonsPlacement() {
        return this.getClientPropertyEnumString("JTabbedPane.scrollButtonsPlacement", ScrollButtonsPlacement.class, "TabbedPane.scrollButtonsPlacement", ScrollButtonsPlacement.both);
    }

    public void setScrollButtonsPlacement(ScrollButtonsPlacement scrollButtonsPlacement) {
        this.putClientPropertyEnumString("JTabbedPane.scrollButtonsPlacement", scrollButtonsPlacement);
    }

    public TabAreaAlignment getTabAreaAlignment() {
        return this.getClientPropertyEnumString("JTabbedPane.tabAreaAlignment", TabAreaAlignment.class, "TabbedPane.tabAreaAlignment", TabAreaAlignment.leading);
    }

    public void setTabAreaAlignment(TabAreaAlignment tabAreaAlignment) {
        this.putClientPropertyEnumString("JTabbedPane.tabAreaAlignment", tabAreaAlignment);
    }

    public TabAlignment getTabAlignment() {
        return this.getClientPropertyEnumString("JTabbedPane.tabAlignment", TabAlignment.class, "TabbedPane.tabAlignment", TabAlignment.center);
    }

    public void setTabAlignment(TabAlignment tabAlignment) {
        this.putClientPropertyEnumString("JTabbedPane.tabAlignment", tabAlignment);
    }

    public TabWidthMode getTabWidthMode() {
        return this.getClientPropertyEnumString("JTabbedPane.tabWidthMode", TabWidthMode.class, "TabbedPane.tabWidthMode", TabWidthMode.preferred);
    }

    public void setTabWidthMode(TabWidthMode tabWidthMode) {
        this.putClientPropertyEnumString("JTabbedPane.tabWidthMode", tabWidthMode);
    }

    public int getTabIconPlacement() {
        return this.getClientPropertyInt((Object)"JTabbedPane.tabIconPlacement", 10);
    }

    public void setTabIconPlacement(int tabIconPlacement) {
        this.putClientProperty("JTabbedPane.tabIconPlacement", tabIconPlacement >= 0 ? Integer.valueOf(tabIconPlacement) : null);
    }

    public Component getLeadingComponent() {
        return (Component)this.getClientProperty("JTabbedPane.leadingComponent");
    }

    public void setLeadingComponent(Component leadingComponent) {
        this.putClientProperty("JTabbedPane.leadingComponent", leadingComponent);
    }

    public Component getTrailingComponent() {
        return (Component)this.getClientProperty("JTabbedPane.trailingComponent");
    }

    public void setTrailingComponent(Component trailingComponent) {
        this.putClientProperty("JTabbedPane.trailingComponent", trailingComponent);
    }

    public static enum TabWidthMode {
        preferred,
        equal,
        compact;

    }

    public static enum TabAlignment {
        leading,
        trailing,
        center;

    }

    public static enum TabAreaAlignment {
        leading,
        trailing,
        center,
        fill;

    }

    public static enum ScrollButtonsPlacement {
        both,
        trailing;

    }

    public static enum ScrollButtonsPolicy {
        never,
        asNeeded,
        asNeededSingle;

    }

    public static enum TabsPopupPolicy {
        never,
        asNeeded;

    }
}

