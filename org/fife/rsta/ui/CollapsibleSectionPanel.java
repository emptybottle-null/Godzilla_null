/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CollapsibleSectionPanel
extends JPanel {
    private List<BottomComponentInfo> bottomComponentInfos = new ArrayList<BottomComponentInfo>();
    private BottomComponentInfo currentBci;
    private boolean animate;
    private Timer timer;
    private int tick;
    private int totalTicks = 10;
    private boolean down;
    private boolean firstTick;
    private static final int FRAME_MILLIS = 10;

    public CollapsibleSectionPanel() {
        this(true);
    }

    public CollapsibleSectionPanel(boolean animate) {
        super(new BorderLayout());
        this.installKeystrokes();
        this.animate = animate;
    }

    public void addBottomComponent(JComponent comp) {
        this.addBottomComponent(null, comp);
    }

    public Action addBottomComponent(KeyStroke ks, JComponent comp) {
        BottomComponentInfo bci = new BottomComponentInfo(comp);
        this.bottomComponentInfos.add(bci);
        ShowBottomComponentAction action = null;
        if (ks != null) {
            InputMap im = this.getInputMap(1);
            im.put(ks, ks);
            action = new ShowBottomComponentAction(ks, bci);
            this.getActionMap().put(ks, action);
        }
        return action;
    }

    private void createTimer() {
        this.timer = new Timer(10, e -> {
            ++this.tick;
            if (this.tick == this.totalTicks) {
                this.timer.stop();
                this.timer = null;
                this.tick = 0;
                Dimension finalSize = this.down ? new Dimension(0, 0) : this.currentBci.getRealPreferredSize();
                this.currentBci.component.setPreferredSize(finalSize);
                if (this.down) {
                    this.remove(this.currentBci.component);
                    this.currentBci = null;
                }
            } else {
                if (this.firstTick) {
                    if (this.down) {
                        this.focusMainComponent();
                    } else {
                        this.currentBci.component.requestFocusInWindow();
                    }
                    this.firstTick = false;
                }
                float proportion = !this.down ? (float)this.tick / (float)this.totalTicks : 1.0f - (float)this.tick / (float)this.totalTicks;
                Dimension size = new Dimension(this.currentBci.getRealPreferredSize());
                size.height = (int)((float)size.height * proportion);
                this.currentBci.component.setPreferredSize(size);
            }
            this.revalidate();
            this.repaint();
        });
        this.timer.setRepeats(true);
    }

    private void focusMainComponent() {
        Component center = ((BorderLayout)this.getLayout()).getLayoutComponent("Center");
        if (center instanceof JScrollPane) {
            center = ((JScrollPane)center).getViewport().getView();
        }
        center.requestFocusInWindow();
    }

    public JComponent getDisplayedBottomComponent() {
        if (!(this.currentBci == null || this.timer != null && this.timer.isRunning())) {
            return this.currentBci.component;
        }
        return null;
    }

    public void hideBottomComponent() {
        if (this.currentBci == null) {
            return;
        }
        if (!this.animate) {
            this.remove(this.currentBci.component);
            this.revalidate();
            this.repaint();
            this.currentBci = null;
            this.focusMainComponent();
            return;
        }
        if (this.timer != null) {
            if (this.down) {
                return;
            }
            this.timer.stop();
            this.tick = this.totalTicks - this.tick;
        }
        this.down = true;
        this.firstTick = true;
        this.createTimer();
        this.timer.start();
    }

    private void installKeystrokes() {
        InputMap im = this.getInputMap(1);
        ActionMap am = this.getActionMap();
        im.put(KeyStroke.getKeyStroke(27, 0), "onEscape");
        am.put("onEscape", new HideBottomComponentAction());
    }

    public void setAnimationTime(int millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("millis must be >= 0");
        }
        this.totalTicks = Math.max(millis / 10, 1);
    }

    private void showBottomComponent(BottomComponentInfo bci) {
        if (bci.equals(this.currentBci)) {
            this.currentBci.component.requestFocusInWindow();
            return;
        }
        if (this.currentBci != null) {
            this.remove(this.currentBci.component);
        }
        this.currentBci = bci;
        this.add((Component)this.currentBci.component, "South");
        if (!this.animate) {
            this.currentBci.component.requestFocusInWindow();
            this.revalidate();
            this.repaint();
            return;
        }
        if (this.timer != null) {
            this.timer.stop();
        }
        this.tick = 0;
        this.down = false;
        this.firstTick = true;
        this.createTimer();
        this.timer.start();
    }

    public void showBottomComponent(JComponent comp) {
        BottomComponentInfo info = null;
        for (BottomComponentInfo bci : this.bottomComponentInfos) {
            if (bci.component != comp) continue;
            info = bci;
            break;
        }
        if (info != null) {
            this.showBottomComponent(info);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.bottomComponentInfos != null) {
            for (BottomComponentInfo info : this.bottomComponentInfos) {
                if (!info.component.isDisplayable()) {
                    SwingUtilities.updateComponentTreeUI(info.component);
                }
                info.uiUpdated();
            }
        }
    }

    private class ShowBottomComponentAction
    extends AbstractAction {
        private BottomComponentInfo bci;

        ShowBottomComponentAction(KeyStroke ks, BottomComponentInfo bci) {
            this.putValue("AcceleratorKey", ks);
            this.bci = bci;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CollapsibleSectionPanel.this.showBottomComponent(this.bci);
        }
    }

    private class HideBottomComponentAction
    extends AbstractAction {
        private HideBottomComponentAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CollapsibleSectionPanel.this.hideBottomComponent();
        }
    }

    private static class BottomComponentInfo {
        private JComponent component;
        private Dimension preferredSize;

        BottomComponentInfo(JComponent component) {
            this.component = component;
        }

        Dimension getRealPreferredSize() {
            if (this.preferredSize == null) {
                this.preferredSize = this.component.getPreferredSize();
            }
            return this.preferredSize;
        }

        private void uiUpdated() {
            this.component.setPreferredSize(null);
        }
    }
}

