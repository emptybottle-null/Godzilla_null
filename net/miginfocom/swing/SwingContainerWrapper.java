/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.swing.SwingComponentWrapper;

public final class SwingContainerWrapper
extends SwingComponentWrapper
implements ContainerWrapper {
    private static final Color DB_CELL_OUTLINE = new Color(255, 0, 0);

    public SwingContainerWrapper(Container c) {
        super(c);
    }

    @Override
    public ComponentWrapper[] getComponents() {
        Container c = (Container)this.getComponent();
        ComponentWrapper[] cws = new ComponentWrapper[c.getComponentCount()];
        for (int i = 0; i < cws.length; ++i) {
            cws[i] = new SwingComponentWrapper(c.getComponent(i));
        }
        return cws;
    }

    @Override
    public int getComponentCount() {
        return ((Container)this.getComponent()).getComponentCount();
    }

    @Override
    public Object getLayout() {
        return ((Container)this.getComponent()).getLayout();
    }

    @Override
    public final boolean isLeftToRight() {
        return ((Container)this.getComponent()).getComponentOrientation().isLeftToRight();
    }

    @Override
    public final void paintDebugCell(int x, int y, int width, int height) {
        Component c = (Component)this.getComponent();
        if (!c.isShowing()) {
            return;
        }
        Graphics2D g = (Graphics2D)c.getGraphics();
        if (g == null) {
            return;
        }
        g.setStroke(new BasicStroke(1.0f, 2, 0, 10.0f, new float[]{2.0f, 3.0f}, 0.0f));
        g.setPaint(DB_CELL_OUTLINE);
        g.drawRect(x, y, width - 1, height - 1);
    }

    @Override
    public int getComponentType(boolean disregardScrollPane) {
        return 1;
    }

    @Override
    public int getLayoutHashCode() {
        long n = System.nanoTime();
        int h = super.getLayoutHashCode();
        if (this.isLeftToRight()) {
            h += 416343;
        }
        return 0;
    }
}

