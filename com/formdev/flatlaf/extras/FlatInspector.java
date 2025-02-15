/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras;

import com.formdev.flatlaf.ui.FlatToolTipUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;

public class FlatInspector {
    private static final Integer HIGHLIGHT_LAYER = 401;
    private static final Integer TOOLTIP_LAYER = 402;
    private static final int KEY_MODIFIERS_MASK = 960;
    private final JRootPane rootPane;
    private final MouseMotionListener mouseMotionListener;
    private final AWTEventListener keyListener;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private boolean enabled;
    private Component lastComponent;
    private int lastX;
    private int lastY;
    private int inspectParentLevel;
    private boolean wasCtrlOrShiftKeyPressed;
    private JComponent highlightFigure;
    private JToolTip tip;

    public static void install(String activationKeys) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(activationKeys);
        Toolkit.getDefaultToolkit().addAWTEventListener(e -> {
            Window activeWindow;
            if (e.getID() == 402 && ((KeyEvent)e).getKeyCode() == keyStroke.getKeyCode() && (((KeyEvent)e).getModifiersEx() & 0x3C0) == (keyStroke.getModifiers() & 0x3C0) && (activeWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow()) instanceof RootPaneContainer) {
                JRootPane rootPane = ((RootPaneContainer)((Object)activeWindow)).getRootPane();
                FlatInspector inspector = (FlatInspector)rootPane.getClientProperty(FlatInspector.class);
                if (inspector == null) {
                    inspector = new FlatInspector(rootPane);
                    rootPane.putClientProperty(FlatInspector.class, inspector);
                    inspector.setEnabled(true);
                } else {
                    inspector.uninstall();
                    rootPane.putClientProperty(FlatInspector.class, null);
                }
            }
        }, 8L);
    }

    public FlatInspector(JRootPane rootPane) {
        this.rootPane = rootPane;
        this.mouseMotionListener = new MouseMotionAdapter(){

            @Override
            public void mouseMoved(MouseEvent e) {
                FlatInspector.this.lastX = e.getX();
                FlatInspector.this.lastY = e.getY();
                FlatInspector.this.inspect(FlatInspector.this.lastX, FlatInspector.this.lastY);
            }
        };
        rootPane.getGlassPane().addMouseMotionListener(this.mouseMotionListener);
        this.keyListener = e -> {
            KeyEvent keyEvent = (KeyEvent)e;
            int keyCode = keyEvent.getKeyCode();
            int id = e.getID();
            if (id == 401) {
                if (keyCode == 17 || keyCode == 16) {
                    this.wasCtrlOrShiftKeyPressed = true;
                }
            } else if (id == 402 && this.wasCtrlOrShiftKeyPressed) {
                int parentLevel;
                if (keyCode == 17) {
                    ++this.inspectParentLevel;
                    parentLevel = this.inspect(this.lastX, this.lastY);
                    if (this.inspectParentLevel > parentLevel) {
                        this.inspectParentLevel = parentLevel;
                    }
                } else if (keyCode == 16 && this.inspectParentLevel > 0) {
                    --this.inspectParentLevel;
                    parentLevel = this.inspect(this.lastX, this.lastY);
                    if (this.inspectParentLevel > parentLevel) {
                        this.inspectParentLevel = Math.max(parentLevel - 1, 0);
                        this.inspect(this.lastX, this.lastY);
                    }
                }
            }
            if (keyCode == 27) {
                keyEvent.consume();
                if (id == 401) {
                    FlatInspector inspector = (FlatInspector)rootPane.getClientProperty(FlatInspector.class);
                    if (inspector == this) {
                        this.uninstall();
                        rootPane.putClientProperty(FlatInspector.class, null);
                    } else {
                        this.setEnabled(false);
                    }
                }
            }
        };
    }

    private void uninstall() {
        this.setEnabled(false);
        this.rootPane.getGlassPane().setVisible(false);
        this.rootPane.getGlassPane().removeMouseMotionListener(this.mouseMotionListener);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        this.propertyChangeSupport.removePropertyChangeListener(l);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        ((JComponent)this.rootPane.getGlassPane()).setOpaque(false);
        this.rootPane.getGlassPane().setVisible(enabled);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (enabled) {
            toolkit.addAWTEventListener(this.keyListener, 8L);
        } else {
            toolkit.removeAWTEventListener(this.keyListener);
        }
        if (enabled) {
            Point pt = new Point(MouseInfo.getPointerInfo().getLocation());
            SwingUtilities.convertPointFromScreen(pt, this.rootPane);
            this.lastX = pt.x;
            this.lastY = pt.y;
            this.inspect(this.lastX, this.lastY);
        } else {
            this.lastComponent = null;
            this.inspectParentLevel = 0;
            if (this.highlightFigure != null) {
                this.highlightFigure.getParent().remove(this.highlightFigure);
            }
            this.highlightFigure = null;
            if (this.tip != null) {
                this.tip.getParent().remove(this.tip);
            }
            this.tip = null;
        }
        this.propertyChangeSupport.firePropertyChange("enabled", !enabled, enabled);
    }

    public void update() {
        if (!this.rootPane.getGlassPane().isVisible()) {
            return;
        }
        EventQueue.invokeLater(() -> {
            this.setEnabled(false);
            this.setEnabled(true);
            this.inspect(this.lastX, this.lastY);
        });
    }

    private int inspect(int x, int y) {
        Container parent;
        Point pt = SwingUtilities.convertPoint(this.rootPane.getGlassPane(), x, y, this.rootPane);
        Component c = this.getDeepestComponentAt(this.rootPane, pt.x, pt.y);
        int parentLevel = 0;
        for (int i = 0; i < this.inspectParentLevel && c != null && (parent = c.getParent()) != null; ++i) {
            c = parent;
            ++parentLevel;
        }
        if (c == this.lastComponent) {
            return parentLevel;
        }
        this.lastComponent = c;
        this.highlight(c);
        this.showToolTip(c, x, y, parentLevel);
        return parentLevel;
    }

    private Component getDeepestComponentAt(Component parent, int x, int y) {
        if (!parent.contains(x, y)) {
            return null;
        }
        if (parent instanceof Container) {
            for (Component child : ((Container)parent).getComponents()) {
                Component c;
                if (child == null || !child.isVisible()) continue;
                int cx = x - child.getX();
                int cy = y - child.getY();
                Component component = c = child instanceof Container ? this.getDeepestComponentAt(child, cx, cy) : child.getComponentAt(cx, cy);
                if (c == null || !c.isVisible() || c == this.highlightFigure || c == this.tip || c.getParent() instanceof JRootPane && c == ((JRootPane)c.getParent()).getGlassPane() || "com.formdev.flatlaf.ui.FlatWindowResizer".equals(c.getClass().getName())) continue;
                return c;
            }
        }
        return parent;
    }

    private void highlight(Component c) {
        if (this.highlightFigure == null) {
            this.highlightFigure = this.createHighlightFigure();
            this.rootPane.getLayeredPane().add((Component)this.highlightFigure, HIGHLIGHT_LAYER);
        }
        this.highlightFigure.setVisible(c != null);
        if (c != null) {
            Insets insets = this.rootPane.getInsets();
            this.highlightFigure.setBounds(new Rectangle(SwingUtilities.convertPoint(c, -insets.left, -insets.top, this.rootPane), c.getSize()));
        }
    }

    private JComponent createHighlightFigure() {
        JComponent c = new JComponent(){

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(this.getBackground());
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
                super.paintBorder(g);
                FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
            }
        };
        c.setBackground(new Color(255, 0, 0, 32));
        c.setBorder(new LineBorder(Color.red));
        return c;
    }

    private void showToolTip(Component c, int x, int y, int parentLevel) {
        if (c == null) {
            if (this.tip != null) {
                this.tip.setVisible(false);
            }
            return;
        }
        if (this.tip == null) {
            this.tip = new JToolTip(){

                @Override
                public void updateUI() {
                    this.setUI(FlatToolTipUI.createUI(this));
                }
            };
            this.rootPane.getLayeredPane().add((Component)this.tip, TOOLTIP_LAYER);
        } else {
            this.tip.setVisible(true);
        }
        this.tip.setTipText(FlatInspector.buildToolTipText(c, parentLevel));
        int tx = x + UIScale.scale(8);
        int ty = y + UIScale.scale(16);
        Dimension size = this.tip.getPreferredSize();
        Rectangle visibleRect = this.rootPane.getVisibleRect();
        if (tx + size.width > visibleRect.x + visibleRect.width) {
            tx -= size.width + UIScale.scale(16);
        }
        if (ty + size.height > visibleRect.y + visibleRect.height) {
            ty -= size.height + UIScale.scale(32);
        }
        if (tx < visibleRect.x) {
            tx = visibleRect.x;
        }
        if (ty < visibleRect.y) {
            ty = visibleRect.y;
        }
        this.tip.setBounds(tx, ty, size.width, size.height);
        this.tip.repaint();
    }

    private static String buildToolTipText(Component c, int parentLevel) {
        LayoutManager layout;
        String name = c.getClass().getName();
        name = name.substring(name.lastIndexOf(46) + 1);
        String text = "Class: " + name + " (" + c.getClass().getPackage().getName() + ")\nSize: " + c.getWidth() + ',' + c.getHeight() + "  @ " + c.getX() + ',' + c.getY() + '\n';
        if (c instanceof Container) {
            text = text + "Insets: " + FlatInspector.toString(((Container)c).getInsets()) + '\n';
        }
        Insets margin = null;
        if (c instanceof AbstractButton) {
            margin = ((AbstractButton)c).getMargin();
        } else if (c instanceof JTextComponent) {
            margin = ((JTextComponent)c).getMargin();
        } else if (c instanceof JMenuBar) {
            margin = ((JMenuBar)c).getMargin();
        } else if (c instanceof JToolBar) {
            margin = ((JToolBar)c).getMargin();
        }
        if (margin != null) {
            text = text + "Margin: " + FlatInspector.toString(margin) + '\n';
        }
        Dimension prefSize = c.getPreferredSize();
        Dimension minSize = c.getMinimumSize();
        Dimension maxSize = c.getMaximumSize();
        text = text + "Pref size: " + prefSize.width + ',' + prefSize.height + '\n' + "Min size: " + minSize.width + ',' + minSize.height + '\n' + "Max size: " + maxSize.width + ',' + maxSize.height + '\n';
        if (c instanceof JComponent) {
            text = text + "Border: " + FlatInspector.toString(((JComponent)c).getBorder()) + '\n';
        }
        text = text + "Background: " + FlatInspector.toString(c.getBackground()) + '\n' + "Foreground: " + FlatInspector.toString(c.getForeground()) + '\n' + "Font: " + FlatInspector.toString(c.getFont()) + '\n';
        if (c instanceof JComponent) {
            try {
                Field f = JComponent.class.getDeclaredField("ui");
                f.setAccessible(true);
                Object ui = f.get(c);
                text = text + "UI: " + (ui != null ? ui.getClass().getName() : "null") + '\n';
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException f) {
                // empty catch block
            }
        }
        if (c instanceof Container && (layout = ((Container)c).getLayout()) != null) {
            text = text + "Layout: " + layout.getClass().getName() + '\n';
        }
        text = text + "Enabled: " + c.isEnabled() + '\n';
        text = text + "Opaque: " + c.isOpaque() + (c instanceof JComponent && FlatUIUtils.hasOpaqueBeenExplicitlySet((JComponent)c) ? " EXPLICIT" : "") + '\n';
        if (c instanceof AbstractButton) {
            text = text + "ContentAreaFilled: " + ((AbstractButton)c).isContentAreaFilled() + '\n';
        }
        text = text + "Focusable: " + c.isFocusable() + '\n';
        text = text + "Left-to-right: " + c.getComponentOrientation().isLeftToRight() + '\n';
        text = text + "Parent: " + (c.getParent() != null ? c.getParent().getClass().getName() : "null");
        if (parentLevel > 0) {
            text = text + "\n\nParent level: " + parentLevel;
        }
        text = parentLevel > 0 ? text + "\n(press Ctrl/Shift to increase/decrease level)" : text + "\n\n(press Ctrl key to inspect parent)";
        return text;
    }

    private static String toString(Insets insets) {
        if (insets == null) {
            return "null";
        }
        return insets.top + "," + insets.left + ',' + insets.bottom + ',' + insets.right + (insets instanceof UIResource ? " UI" : "");
    }

    private static String toString(Color c) {
        if (c == null) {
            return "null";
        }
        String s = Long.toString((long)c.getRGB() & 0xFFFFFFFFL, 16);
        if (c instanceof UIResource) {
            s = s + " UI";
        }
        return s;
    }

    private static String toString(Font f) {
        if (f == null) {
            return "null";
        }
        return f.getFamily() + " " + f.getSize() + " " + f.getStyle() + (f instanceof UIResource ? " UI" : "");
    }

    private static String toString(Border b) {
        if (b == null) {
            return "null";
        }
        String s = b.getClass().getName();
        if (b instanceof EmptyBorder) {
            s = s + '(' + FlatInspector.toString(((EmptyBorder)b).getBorderInsets()) + ')';
        }
        if (b instanceof UIResource) {
            s = s + " UI";
        }
        return s;
    }
}

