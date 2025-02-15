/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.Grid;
import net.miginfocom.layout.LC;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.PlatformDefaults;
import net.miginfocom.layout.UnitValue;
import net.miginfocom.swing.SwingComponentWrapper;
import net.miginfocom.swing.SwingContainerWrapper;

public class MigLayout
implements LayoutManager2,
Externalizable {
    private final Map<Component, Object> scrConstrMap = new IdentityHashMap<Component, Object>(8);
    private Object layoutConstraints = "";
    private Object colConstraints = "";
    private Object rowConstraints = "";
    private transient ContainerWrapper cacheParentW = null;
    private final transient Map<ComponentWrapper, CC> ccMap = new HashMap<ComponentWrapper, CC>(8);
    private transient Timer debugTimer = null;
    private transient LC lc = null;
    private transient AC colSpecs = null;
    private transient AC rowSpecs = null;
    private transient Grid grid = null;
    private transient int lastModCount = PlatformDefaults.getModCount();
    private transient int lastHash = -1;
    private transient Dimension lastInvalidSize = null;
    private transient boolean lastWasInvalid = false;
    private transient Dimension lastParentSize = null;
    private transient ArrayList<LayoutCallback> callbackList = null;
    private transient boolean dirty = true;
    private long lastSize = 0L;

    public MigLayout() {
        this("", "", "");
    }

    public MigLayout(String layoutConstraints) {
        this(layoutConstraints, "", "");
    }

    public MigLayout(String layoutConstraints, String colConstraints) {
        this(layoutConstraints, colConstraints, "");
    }

    public MigLayout(String layoutConstraints, String colConstraints, String rowConstraints) {
        this.setLayoutConstraints(layoutConstraints);
        this.setColumnConstraints(colConstraints);
        this.setRowConstraints(rowConstraints);
    }

    public MigLayout(LC layoutConstraints) {
        this(layoutConstraints, null, null);
    }

    public MigLayout(LC layoutConstraints, AC colConstraints) {
        this(layoutConstraints, colConstraints, null);
    }

    public MigLayout(LC layoutConstraints, AC colConstraints, AC rowConstraints) {
        this.setLayoutConstraints(layoutConstraints);
        this.setColumnConstraints(colConstraints);
        this.setRowConstraints(rowConstraints);
    }

    public Object getLayoutConstraints() {
        return this.layoutConstraints;
    }

    public void setLayoutConstraints(Object constr) {
        if (constr == null || constr instanceof String) {
            constr = ConstraintParser.prepare((String)constr);
            this.lc = ConstraintParser.parseLayoutConstraint((String)constr);
        } else if (constr instanceof LC) {
            this.lc = (LC)constr;
        } else {
            throw new IllegalArgumentException("Illegal constraint type: " + constr.getClass().toString());
        }
        this.layoutConstraints = constr;
        this.dirty = true;
    }

    public Object getColumnConstraints() {
        return this.colConstraints;
    }

    public void setColumnConstraints(Object constr) {
        if (constr == null || constr instanceof String) {
            constr = ConstraintParser.prepare((String)constr);
            this.colSpecs = ConstraintParser.parseColumnConstraints((String)constr);
        } else if (constr instanceof AC) {
            this.colSpecs = (AC)constr;
        } else {
            throw new IllegalArgumentException("Illegal constraint type: " + constr.getClass().toString());
        }
        this.colConstraints = constr;
        this.dirty = true;
    }

    public Object getRowConstraints() {
        return this.rowConstraints;
    }

    public void setRowConstraints(Object constr) {
        if (constr == null || constr instanceof String) {
            constr = ConstraintParser.prepare((String)constr);
            this.rowSpecs = ConstraintParser.parseRowConstraints((String)constr);
        } else if (constr instanceof AC) {
            this.rowSpecs = (AC)constr;
        } else {
            throw new IllegalArgumentException("Illegal constraint type: " + constr.getClass().toString());
        }
        this.rowConstraints = constr;
        this.dirty = true;
    }

    public Map<Component, Object> getConstraintMap() {
        return new IdentityHashMap<Component, Object>(this.scrConstrMap);
    }

    public void setConstraintMap(Map<Component, Object> map) {
        this.scrConstrMap.clear();
        this.ccMap.clear();
        for (Map.Entry<Component, Object> e : map.entrySet()) {
            this.setComponentConstraintsImpl(e.getKey(), e.getValue(), true);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object getComponentConstraints(Component comp) {
        Object object = comp.getParent().getTreeLock();
        synchronized (object) {
            return this.scrConstrMap.get(comp);
        }
    }

    public void setComponentConstraints(Component comp, Object constr) {
        this.setComponentConstraintsImpl(comp, constr, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void setComponentConstraintsImpl(Component comp, Object constr, boolean noCheck) {
        Container parent = comp.getParent();
        Object object = parent != null ? parent.getTreeLock() : new Object();
        synchronized (object) {
            if (!noCheck && !this.scrConstrMap.containsKey(comp)) {
                throw new IllegalArgumentException("Component must already be added to parent!");
            }
            SwingComponentWrapper cw = new SwingComponentWrapper(comp);
            if (constr == null || constr instanceof String) {
                String cStr = ConstraintParser.prepare((String)constr);
                this.scrConstrMap.put(comp, constr);
                this.ccMap.put(cw, ConstraintParser.parseComponentConstraint(cStr));
            } else if (constr instanceof CC) {
                this.scrConstrMap.put(comp, constr);
                this.ccMap.put(cw, (CC)constr);
            } else {
                throw new IllegalArgumentException("Constraint must be String or ComponentConstraint: " + constr.getClass().toString());
            }
            this.dirty = true;
        }
    }

    public boolean isManagingComponent(Component c) {
        return this.scrConstrMap.containsKey(c);
    }

    public void addLayoutCallback(LayoutCallback callback) {
        if (callback == null) {
            throw new NullPointerException();
        }
        if (this.callbackList == null) {
            this.callbackList = new ArrayList(1);
        }
        this.callbackList.add(callback);
        this.grid = null;
    }

    public void removeLayoutCallback(LayoutCallback callback) {
        if (this.callbackList != null) {
            this.callbackList.remove(callback);
        }
    }

    private void setDebug(ComponentWrapper parentW, boolean b) {
        if (b && (this.debugTimer == null || this.debugTimer.getDelay() != this.getDebugMillis())) {
            ContainerWrapper pCW;
            if (this.debugTimer != null) {
                this.debugTimer.stop();
            }
            final Component parent = (pCW = parentW.getParent()) != null ? (Component)pCW.getComponent() : null;
            this.debugTimer = new Timer(this.getDebugMillis(), new MyDebugRepaintListener());
            if (parent != null) {
                SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                        Container p = parent.getParent();
                        if (p != null) {
                            if (p instanceof JComponent) {
                                ((JComponent)p).revalidate();
                            } else {
                                parent.invalidate();
                                p.validate();
                            }
                        }
                    }
                });
            }
            this.debugTimer.setInitialDelay(100);
            this.debugTimer.start();
        } else if (!b && this.debugTimer != null) {
            this.debugTimer.stop();
            this.debugTimer = null;
        }
    }

    private boolean getDebug() {
        return this.debugTimer != null;
    }

    private int getDebugMillis() {
        int globalDebugMillis = LayoutUtil.getGlobalDebugMillis();
        return globalDebugMillis > 0 ? globalDebugMillis : this.lc.getDebugMillis();
    }

    private void checkCache(Container parent) {
        if (parent == null) {
            return;
        }
        if (this.dirty) {
            this.grid = null;
        }
        this.cleanConstraintMaps(parent);
        int mc = PlatformDefaults.getModCount();
        if (this.lastModCount != mc) {
            this.grid = null;
            this.lastModCount = mc;
        }
        if (!parent.isValid()) {
            if (!this.lastWasInvalid) {
                this.lastWasInvalid = true;
                int hash = 0;
                boolean resetLastInvalidOnParent = false;
                for (ComponentWrapper wrapper : this.ccMap.keySet()) {
                    Object component = wrapper.getComponent();
                    if (component instanceof JTextArea || component instanceof JEditorPane) {
                        resetLastInvalidOnParent = true;
                    }
                    hash ^= wrapper.getLayoutHashCode();
                    hash += 285134905;
                }
                if (resetLastInvalidOnParent) {
                    this.resetLastInvalidOnParent(parent);
                }
                if (hash != this.lastHash) {
                    this.grid = null;
                    this.lastHash = hash;
                }
                Dimension ps = parent.getSize();
                if (this.lastInvalidSize == null || !this.lastInvalidSize.equals(ps)) {
                    this.grid = null;
                    this.lastInvalidSize = ps;
                }
            }
        } else {
            this.lastWasInvalid = false;
        }
        ContainerWrapper par = this.checkParent(parent);
        this.setDebug(par, this.getDebugMillis() > 0);
        if (this.grid == null) {
            this.grid = new Grid(par, this.lc, this.rowSpecs, this.colSpecs, this.ccMap, this.callbackList);
        }
        this.dirty = false;
    }

    private void cleanConstraintMaps(Container parent) {
        HashSet<Component> parentCompSet = new HashSet<Component>(Arrays.asList(parent.getComponents()));
        Iterator<Map.Entry<ComponentWrapper, CC>> it = this.ccMap.entrySet().iterator();
        while (it.hasNext()) {
            Component c = (Component)it.next().getKey().getComponent();
            if (parentCompSet.contains(c)) continue;
            it.remove();
            this.scrConstrMap.remove(c);
        }
    }

    private void resetLastInvalidOnParent(Container parent) {
        while (parent != null) {
            LayoutManager layoutManager = parent.getLayout();
            if (layoutManager instanceof MigLayout) {
                ((MigLayout)layoutManager).lastWasInvalid = false;
            }
            parent = parent.getParent();
        }
    }

    private ContainerWrapper checkParent(Container parent) {
        if (parent == null) {
            return null;
        }
        if (this.cacheParentW == null || this.cacheParentW.getComponent() != parent) {
            this.cacheParentW = new SwingContainerWrapper(parent);
        }
        return this.cacheParentW;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void layoutContainer(Container parent) {
        Object object = parent.getTreeLock();
        synchronized (object) {
            long newSize;
            this.checkCache(parent);
            Insets i = parent.getInsets();
            int[] b = new int[]{i.left, i.top, parent.getWidth() - i.left - i.right, parent.getHeight() - i.top - i.bottom};
            if (this.grid.layout(b, this.lc.getAlignX(), this.lc.getAlignY(), this.getDebug())) {
                this.grid = null;
                this.checkCache(parent);
                this.grid.layout(b, this.lc.getAlignX(), this.lc.getAlignY(), this.getDebug());
            }
            if (this.lastSize != (newSize = (long)this.grid.getHeight()[1] + ((long)this.grid.getWidth()[1] << 32))) {
                this.lastSize = newSize;
                final ContainerWrapper containerWrapper = this.checkParent(parent);
                Window win = (Window)SwingUtilities.getAncestorOfClass(Window.class, (Component)containerWrapper.getComponent());
                if (win != null) {
                    if (win.isVisible()) {
                        SwingUtilities.invokeLater(new Runnable(){

                            @Override
                            public void run() {
                                MigLayout.this.adjustWindowSize(containerWrapper);
                            }
                        });
                    } else {
                        this.adjustWindowSize(containerWrapper);
                    }
                }
            }
            this.lastInvalidSize = null;
        }
    }

    private void adjustWindowSize(ContainerWrapper parent) {
        BoundSize wBounds = this.lc.getPackWidth();
        BoundSize hBounds = this.lc.getPackHeight();
        if (wBounds == BoundSize.NULL_SIZE && hBounds == BoundSize.NULL_SIZE) {
            return;
        }
        Container packable = this.getPackable((Component)parent.getComponent());
        if (packable != null) {
            Container c;
            Component pc = (Component)parent.getComponent();
            Container container = c = pc instanceof Container ? (Container)pc : pc.getParent();
            while (c != null) {
                LayoutManager layout = c.getLayout();
                if (layout instanceof BoxLayout || layout instanceof OverlayLayout) {
                    ((LayoutManager2)layout).invalidateLayout(c);
                }
                c = c.getParent();
            }
            Dimension prefSize = packable.getPreferredSize();
            int targW = this.constrain(this.checkParent(packable), packable.getWidth(), prefSize.width, wBounds);
            int targH = this.constrain(this.checkParent(packable), packable.getHeight(), prefSize.height, hBounds);
            Point p = packable.isShowing() ? packable.getLocationOnScreen() : packable.getLocation();
            int x = Math.round((float)p.x - (float)(targW - packable.getWidth()) * (1.0f - this.lc.getPackWidthAlign()));
            int y = Math.round((float)p.y - (float)(targH - packable.getHeight()) * (1.0f - this.lc.getPackHeightAlign()));
            if (packable instanceof JPopupMenu) {
                JPopupMenu popupMenu = (JPopupMenu)packable;
                popupMenu.setVisible(false);
                popupMenu.setPopupSize(targW, targH);
                Component invoker = popupMenu.getInvoker();
                Point popPoint = new Point(x, y);
                SwingUtilities.convertPointFromScreen(popPoint, invoker);
                ((JPopupMenu)packable).show(invoker, popPoint.x, popPoint.y);
                packable.setPreferredSize(null);
            } else {
                packable.setBounds(x, y, targW, targH);
            }
        }
    }

    private Container getPackable(Component comp) {
        JPopupMenu popup = MigLayout.findType(JPopupMenu.class, comp);
        if (popup != null) {
            for (Container popupComp = popup; popupComp != null; popupComp = popupComp.getParent()) {
                if (!popupComp.getClass().getName().contains("HeavyWeightWindow")) continue;
                return popupComp;
            }
            return popup;
        }
        return MigLayout.findType(Window.class, comp);
    }

    public static <E> E findType(Class<E> clazz, Component comp) {
        while (comp != null && !clazz.isInstance(comp)) {
            comp = comp.getParent();
        }
        return (E)comp;
    }

    private int constrain(ContainerWrapper parent, int winSize, int prefSize, BoundSize constrain) {
        if (constrain == null) {
            return winSize;
        }
        int retSize = winSize;
        UnitValue wUV = constrain.getPreferred();
        if (wUV != null) {
            retSize = wUV.getPixels(prefSize, parent, parent);
        }
        retSize = constrain.constrain(retSize, prefSize, parent);
        return constrain.getGapPush() ? Math.max(winSize, retSize) : retSize;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Object object = parent.getTreeLock();
        synchronized (object) {
            return this.getSizeImpl(parent, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Object object = parent.getTreeLock();
        synchronized (object) {
            if (this.lastParentSize == null || !parent.getSize().equals(this.lastParentSize)) {
                for (ComponentWrapper wrapper : this.ccMap.keySet()) {
                    if (wrapper.getContentBias() == -1) continue;
                    this.layoutContainer(parent);
                    break;
                }
            }
            this.lastParentSize = parent.getSize();
            return this.getSizeImpl(parent, 1);
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container parent) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private Dimension getSizeImpl(Container parent, int sizeType) {
        this.checkCache(parent);
        Insets i = parent.getInsets();
        int w = LayoutUtil.getSizeSafe(this.grid != null ? this.grid.getWidth() : null, sizeType) + i.left + i.right;
        int h = LayoutUtil.getSizeSafe(this.grid != null ? this.grid.getHeight() : null, sizeType) + i.top + i.bottom;
        return new Dimension(w, h);
    }

    @Override
    public float getLayoutAlignmentX(Container parent) {
        return this.lc != null && this.lc.getAlignX() != null ? (float)this.lc.getAlignX().getPixels(1.0f, this.checkParent(parent), null) : 0.0f;
    }

    @Override
    public float getLayoutAlignmentY(Container parent) {
        return this.lc != null && this.lc.getAlignY() != null ? (float)this.lc.getAlignY().getPixels(1.0f, this.checkParent(parent), null) : 0.0f;
    }

    @Override
    public void addLayoutComponent(String s, Component comp) {
        this.addLayoutComponent(comp, s);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        Object object = comp.getParent().getTreeLock();
        synchronized (object) {
            this.setComponentConstraintsImpl(comp, constraints, true);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeLayoutComponent(Component comp) {
        Object object = comp.getParent().getTreeLock();
        synchronized (object) {
            this.scrConstrMap.remove(comp);
            this.ccMap.remove(new SwingComponentWrapper(comp));
            this.grid = null;
        }
    }

    @Override
    public void invalidateLayout(Container target) {
        this.dirty = true;
    }

    private Object readResolve() throws ObjectStreamException {
        return LayoutUtil.getSerializedObject(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        LayoutUtil.setSerializedObject(this, LayoutUtil.readAsXML(in));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this.getClass() == MigLayout.class) {
            LayoutUtil.writeAsXML(out, this);
        }
    }

    private class MyDebugRepaintListener
    implements ActionListener {
        private MyDebugRepaintListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Component comp;
            if (MigLayout.this.grid != null && (comp = (Component)MigLayout.this.grid.getContainer().getComponent()).isShowing()) {
                MigLayout.this.grid.paintDebug();
                return;
            }
            MigLayout.this.debugTimer.stop();
            MigLayout.this.debugTimer = null;
        }
    }
}

