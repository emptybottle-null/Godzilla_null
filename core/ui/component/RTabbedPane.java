/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.EasyI18N;
import core.ui.component.listener.RTabbedPaneRemoveListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import util.RightClickMenu;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

public class RTabbedPane
extends JTabbedPane {
    private JPopupMenu rightClickMenu = new JPopupMenu();
    private ArrayList<Component> components = new ArrayList();
    private RTabbedPaneRemoveListener removeListener;

    public RTabbedPane() {
        this.init();
    }

    public RTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
        this.init();
    }

    public RTabbedPane(int tabPlacement) {
        super(tabPlacement);
        this.init();
    }

    private void init() {
        JMenuItem close = new JMenuItem("\u5173\u95ed\u5f53\u524d");
        close.setActionCommand("closeCurrent");
        JMenuItem closeOther = new JMenuItem("\u5173\u95ed\u5176\u5b83");
        closeOther.setActionCommand("closeOther");
        JMenuItem closeLeft = new JMenuItem("\u5173\u95ed\u5de6\u8fb9\u6240\u6709");
        closeLeft.setActionCommand("closeLeft");
        JMenuItem closeRight = new JMenuItem("\u5173\u95ed\u53f3\u8fb9\u6240\u6709");
        closeRight.setActionCommand("closeRight");
        JMenuItem copyNewWindow = new JMenuItem("\u590d\u5236\u5230\u65b0\u7a97\u53e3");
        copyNewWindow.setActionCommand("copyNewWindow");
        this.rightClickMenu.add(close);
        this.rightClickMenu.add(closeOther);
        this.rightClickMenu.add(closeLeft);
        this.rightClickMenu.add(closeRight);
        this.rightClickMenu.add(copyNewWindow);
        automaticBindClick.bindMenuItemClick(this.rightClickMenu, null, this);
        this.addMouseListener(new RightClickMenu(this.rightClickMenu));
        EasyI18N.installObject(this);
    }

    private void closeCurrentMenuItemClick(ActionEvent e) {
        int selected = this.getSelectedIndex();
        if (selected != -1) {
            this.remove(selected);
        }
    }

    private void closeOtherMenuItemClick(ActionEvent e) {
        int selected = this.getSelectedIndex();
        if (selected != -1) {
            int max = this.getTabCount();
            for (int i = max - 1; i >= 0; --i) {
                if (i == selected) continue;
                this.remove(i);
            }
        }
    }

    private void closeLeftMenuItemClick(ActionEvent e) {
        int selected = this.getSelectedIndex();
        if (selected != -1) {
            int max = this.getTabCount();
            for (int i = 0; i < selected; ++i) {
                this.remove(0);
            }
        }
    }

    private void closeRightMenuItemClick(ActionEvent e) {
        int selected = this.getSelectedIndex();
        if (selected != -1) {
            int max = this.getTabCount();
            for (int i = max - 1; i > selected; --i) {
                this.remove(i);
            }
        }
    }

    private void copyNewWindowMenuItemClick(ActionEvent e) {
        int selected = this.getSelectedIndex();
        if (selected != -1) {
            JFrame frame = new JFrame();
            frame.setTitle(this.getTitleAt(selected));
            frame.add(this.getComponent(selected));
            frame.setLocationRelativeTo(UiFunction.getParentFrame(this));
            functions.setWindowSize(frame, 1300, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(2);
            this.components.add(frame);
        }
    }

    @Override
    public synchronized void remove(int index) {
        int before = this.getTabCount();
        super.remove(index);
        int current = this.getTabCount();
        this.notifyRemoveListener(current, before - current);
    }

    @Override
    public synchronized void removeAll() {
        int before = this.getTabCount();
        super.removeAll();
        int current = this.getTabCount();
        this.notifyRemoveListener(current, before - current);
    }

    @Override
    public synchronized void removeTabAt(int index) {
        int before = this.getTabCount();
        super.removeTabAt(index);
        int current = this.getTabCount();
        this.notifyRemoveListener(current, before - current);
    }

    @Override
    public synchronized void remove(Component component) {
        int before = this.getTabCount();
        super.remove(component);
        int current = this.getTabCount();
        this.notifyRemoveListener(current, before - current);
    }

    protected void notifyRemoveListener(int currentSize, int removeSize) {
        if (this.removeListener != null) {
            this.removeListener.actionPerformed(currentSize, removeSize);
        }
    }

    public boolean removeStoreComponent(Component component) {
        return this.components.remove(component);
    }

    public void setRemoveListener(RTabbedPaneRemoveListener removeListener) {
        this.removeListener = removeListener;
    }

    @Override
    public void disable() {
        super.disable();
        for (Component component : this.components) {
            if (component == null) continue;
            component.setVisible(false);
            component.setEnabled(false);
            component.disable();
        }
    }
}

