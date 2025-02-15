/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.swing;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.IdentityHashMap;
import java.util.StringTokenizer;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.PlatformDefaults;
import net.miginfocom.swing.SwingContainerWrapper;

public class SwingComponentWrapper
implements ComponentWrapper {
    private static boolean maxSet = false;
    private static boolean vp = true;
    private static final Color DB_COMP_OUTLINE = new Color(0, 0, 200);
    private static final String VISUAL_PADDING_PROPERTY = PlatformDefaults.VISUAL_PADDING_PROPERTY;
    private final Component c;
    private int compType = -1;
    private Boolean bl = null;
    private boolean prefCalled = false;
    private static final IdentityHashMap<FontMetrics, Point2D.Float> FM_MAP = new IdentityHashMap(4);
    private static final Font SUBST_FONT = new Font("sansserif", 0, 11);
    private static boolean isJava9orLater;

    public SwingComponentWrapper(Component c) {
        this.c = c;
    }

    @Override
    public final int getBaseline(int width, int height) {
        int h = height;
        int[] visPad = this.getVisualPadding();
        if (h < 0) {
            h = this.c.getHeight();
        } else if (visPad != null) {
            h = height + visPad[0] + visPad[2];
        }
        int baseLine = this.c.getBaseline(Math.max(0, width < 0 ? this.c.getWidth() : width), Math.max(0, h));
        if (baseLine != -1 && visPad != null) {
            baseLine -= visPad[0];
        }
        return baseLine;
    }

    @Override
    public final Object getComponent() {
        return this.c;
    }

    @Override
    public final float getPixelUnitFactor(boolean isHor) {
        switch (PlatformDefaults.getLogicalPixelBase()) {
            case 100: {
                Font font = this.c.getFont();
                FontMetrics fm = this.c.getFontMetrics(font != null ? font : SUBST_FONT);
                Point2D.Float p = FM_MAP.get(fm);
                if (p == null) {
                    Rectangle2D r = fm.getStringBounds("X", this.c.getGraphics());
                    p = new Point2D.Float((float)r.getWidth() / 6.0f, (float)r.getHeight() / 13.277344f);
                    FM_MAP.put(fm, p);
                }
                return isHor ? p.x : p.y;
            }
            case 101: {
                Float s = isHor ? PlatformDefaults.getHorizontalScaleFactor() : PlatformDefaults.getVerticalScaleFactor();
                float scaleFactor = s != null ? s.floatValue() : 1.0f;
                Object lafScaleFactorObj = UIManager.get("laf.scaleFactor");
                if (lafScaleFactorObj instanceof Number) {
                    float lafScaleFactor = ((Number)lafScaleFactorObj).floatValue();
                    return scaleFactor * lafScaleFactor;
                }
                float screenScale = isJava9orLater ? 1.0f : (float)(isHor ? this.getHorizontalScreenDPI() : this.getVerticalScreenDPI()) / (float)PlatformDefaults.getDefaultDPI();
                return scaleFactor * screenScale;
            }
        }
        return 1.0f;
    }

    @Override
    public final int getX() {
        return this.c.getX();
    }

    @Override
    public final int getY() {
        return this.c.getY();
    }

    @Override
    public final int getHeight() {
        return this.c.getHeight();
    }

    @Override
    public final int getWidth() {
        return this.c.getWidth();
    }

    @Override
    public final int getScreenLocationX() {
        Point p = new Point();
        SwingUtilities.convertPointToScreen(p, this.c);
        return p.x;
    }

    @Override
    public final int getScreenLocationY() {
        Point p = new Point();
        SwingUtilities.convertPointToScreen(p, this.c);
        return p.y;
    }

    @Override
    public final int getMinimumHeight(int sz) {
        if (!this.prefCalled) {
            this.c.getPreferredSize();
            this.prefCalled = true;
        }
        return this.c.getMinimumSize().height;
    }

    @Override
    public final int getMinimumWidth(int sz) {
        if (!this.prefCalled) {
            this.c.getPreferredSize();
            this.prefCalled = true;
        }
        return this.c.getMinimumSize().width;
    }

    @Override
    public final int getPreferredHeight(int sz) {
        if (this.c.getWidth() == 0 && this.c.getHeight() == 0 && sz != -1) {
            this.c.setBounds(this.c.getX(), this.c.getY(), sz, 1);
        }
        return this.c.getPreferredSize().height;
    }

    @Override
    public final int getPreferredWidth(int sz) {
        if (this.c.getWidth() == 0 && this.c.getHeight() == 0 && sz != -1) {
            this.c.setBounds(this.c.getX(), this.c.getY(), 1, sz);
        }
        return this.c.getPreferredSize().width;
    }

    @Override
    public final int getMaximumHeight(int sz) {
        if (!this.isMaxSet(this.c)) {
            return Integer.MAX_VALUE;
        }
        return this.c.getMaximumSize().height;
    }

    @Override
    public final int getMaximumWidth(int sz) {
        if (!this.isMaxSet(this.c)) {
            return Integer.MAX_VALUE;
        }
        return this.c.getMaximumSize().width;
    }

    private boolean isMaxSet(Component c) {
        return c.isMaximumSizeSet();
    }

    @Override
    public final ContainerWrapper getParent() {
        Container p = this.c.getParent();
        return p != null ? new SwingContainerWrapper(p) : null;
    }

    @Override
    public final int getHorizontalScreenDPI() {
        try {
            return this.c.getToolkit().getScreenResolution();
        } catch (HeadlessException ex) {
            return PlatformDefaults.getDefaultDPI();
        }
    }

    @Override
    public final int getVerticalScreenDPI() {
        try {
            return this.c.getToolkit().getScreenResolution();
        } catch (HeadlessException ex) {
            return PlatformDefaults.getDefaultDPI();
        }
    }

    @Override
    public final int getScreenWidth() {
        try {
            return this.c.getToolkit().getScreenSize().width;
        } catch (HeadlessException ex) {
            return 1024;
        }
    }

    @Override
    public final int getScreenHeight() {
        try {
            return this.c.getToolkit().getScreenSize().height;
        } catch (HeadlessException ex) {
            return 768;
        }
    }

    @Override
    public final boolean hasBaseline() {
        if (this.bl == null) {
            try {
                this.bl = this.c instanceof JLabel && ((JComponent)this.c).getClientProperty("html") != null ? Boolean.FALSE : Boolean.valueOf(this.getBaseline(8192, 8192) > -1);
            } catch (Throwable ex) {
                this.bl = Boolean.FALSE;
            }
        }
        return this.bl;
    }

    @Override
    public final String getLinkId() {
        return this.c.getName();
    }

    @Override
    public final void setBounds(int x, int y, int width, int height) {
        this.c.setBounds(x, y, width, height);
    }

    @Override
    public boolean isVisible() {
        return this.c.isVisible();
    }

    @Override
    public final int[] getVisualPadding() {
        int[] padding = null;
        if (SwingComponentWrapper.isVisualPaddingEnabled() && this.c instanceof JComponent) {
            JComponent component = (JComponent)this.c;
            Object padValue = component.getClientProperty(VISUAL_PADDING_PROPERTY);
            if (padValue instanceof int[]) {
                padding = (int[])padValue;
            } else if (padValue instanceof Insets) {
                Insets padInsets = (Insets)padValue;
                padding = new int[]{padInsets.top, padInsets.left, padInsets.bottom, padInsets.right};
            }
            if (padding == null) {
                String classID;
                switch (this.getComponentType(false)) {
                    case 5: {
                        Border border = component.getBorder();
                        if (border != null && border.getClass().getName().startsWith("com.apple.laf.AquaButtonBorder")) {
                            if (PlatformDefaults.getPlatform() == 1) {
                                Object buttonType = component.getClientProperty("JButton.buttonType");
                                classID = buttonType == null ? (component.getHeight() < 33 ? "Button" : "Button.bevel") : "Button." + buttonType;
                                if (((AbstractButton)component).getIcon() == null) break;
                                classID = classID + ".icon";
                                break;
                            }
                            classID = "Button";
                            break;
                        }
                        classID = "";
                        break;
                    }
                    case 16: {
                        Border border = component.getBorder();
                        if (border != null && border.getClass().getName().startsWith("com.apple.laf.AquaButtonBorder")) {
                            Object size = component.getClientProperty("JComponent.sizeVariant");
                            size = size != null && !size.toString().equals("regular") ? "." + size : "";
                            if (component instanceof JRadioButton) {
                                classID = "RadioButton" + size;
                                break;
                            }
                            if (component instanceof JCheckBox) {
                                classID = "CheckBox" + size;
                                break;
                            }
                            classID = "ToggleButton" + size;
                            break;
                        }
                        classID = "";
                        break;
                    }
                    case 11: {
                        if (PlatformDefaults.getPlatform() == 1) {
                            if (((JComboBox)component).isEditable()) {
                                Object isSquare = component.getClientProperty("JComboBox.isSquare");
                                if (isSquare != null && isSquare.toString().equals("true")) {
                                    classID = "ComboBox.editable.isSquare";
                                    break;
                                }
                                classID = "ComboBox.editable";
                                break;
                            }
                            Object isSquare = component.getClientProperty("JComboBox.isSquare");
                            Object isPopDown = component.getClientProperty("JComboBox.isPopDown");
                            if (isSquare != null && isSquare.toString().equals("true")) {
                                classID = "ComboBox.isSquare";
                                break;
                            }
                            if (isPopDown != null && isPopDown.toString().equals("true")) {
                                classID = "ComboBox.isPopDown";
                                break;
                            }
                            classID = "ComboBox";
                            break;
                        }
                        classID = "ComboBox";
                        break;
                    }
                    case 1: {
                        classID = "Container";
                        break;
                    }
                    case 9: {
                        classID = "Image";
                        break;
                    }
                    case 2: {
                        classID = "Label";
                        break;
                    }
                    case 6: {
                        classID = "List";
                        break;
                    }
                    case 10: {
                        classID = "Panel";
                        break;
                    }
                    case 14: {
                        classID = "ProgressBar";
                        break;
                    }
                    case 17: {
                        classID = "ScrollBar";
                        break;
                    }
                    case 8: {
                        classID = "ScrollPane";
                        break;
                    }
                    case 18: {
                        classID = "Separator";
                        break;
                    }
                    case 12: {
                        classID = "Slider";
                        break;
                    }
                    case 13: {
                        classID = "Spinner";
                        break;
                    }
                    case 7: {
                        classID = "Table";
                        break;
                    }
                    case 19: {
                        classID = "TabbedPane";
                        break;
                    }
                    case 4: {
                        classID = "TextArea";
                        break;
                    }
                    case 3: {
                        Border border = component.getBorder();
                        if (!component.isOpaque() && border != null && border.getClass().getSimpleName().equals("AquaTextFieldBorder")) {
                            classID = "TextField";
                            break;
                        }
                        classID = "";
                        break;
                    }
                    case 15: {
                        classID = "Tree";
                        break;
                    }
                    case 0: {
                        classID = "Other";
                        break;
                    }
                    default: {
                        classID = "";
                    }
                }
                padValue = PlatformDefaults.getDefaultVisualPadding(classID + "." + VISUAL_PADDING_PROPERTY);
                if (padValue instanceof int[]) {
                    padding = (int[])padValue;
                } else if (padValue instanceof Insets) {
                    Insets padInsets = (Insets)padValue;
                    padding = new int[]{padInsets.top, padInsets.left, padInsets.bottom, padInsets.right};
                }
            }
        }
        return padding;
    }

    public static boolean isMaxSizeSetOn1_4() {
        return maxSet;
    }

    public static void setMaxSizeSetOn1_4(boolean b) {
        maxSet = b;
    }

    public static boolean isVisualPaddingEnabled() {
        return vp;
    }

    public static void setVisualPaddingEnabled(boolean b) {
        vp = b;
    }

    @Override
    public final void paintDebugOutline(boolean showVisualPadding) {
        int[] padding;
        if (!this.c.isShowing()) {
            return;
        }
        Graphics2D g = (Graphics2D)this.c.getGraphics();
        if (g == null) {
            return;
        }
        g.setPaint(DB_COMP_OUTLINE);
        g.setStroke(new BasicStroke(1.0f, 2, 0, 10.0f, new float[]{2.0f, 4.0f}, 0.0f));
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        if (showVisualPadding && SwingComponentWrapper.isVisualPaddingEnabled() && (padding = this.getVisualPadding()) != null) {
            g.setColor(Color.GREEN);
            g.drawRect(padding[1], padding[0], this.getWidth() - 1 - (padding[1] + padding[3]), this.getHeight() - 1 - (padding[0] + padding[2]));
        }
    }

    @Override
    public int getComponentType(boolean disregardScrollPane) {
        if (this.compType == -1) {
            this.compType = this.checkType(disregardScrollPane);
        }
        return this.compType;
    }

    @Override
    public int getLayoutHashCode() {
        String id;
        Dimension d = this.c.getMaximumSize();
        int hash = d.width + (d.height << 5);
        d = this.c.getPreferredSize();
        hash += (d.width << 10) + (d.height << 15);
        d = this.c.getMinimumSize();
        hash += (d.width << 20) + (d.height << 25);
        if (this.c.isVisible()) {
            hash += 1324511;
        }
        if ((id = this.getLinkId()) != null) {
            hash += id.hashCode();
        }
        return hash;
    }

    private int checkType(boolean disregardScrollPane) {
        Component c = this.c;
        if (disregardScrollPane) {
            if (c instanceof JScrollPane) {
                c = ((JScrollPane)c).getViewport().getView();
            } else if (c instanceof ScrollPane) {
                c = ((ScrollPane)c).getComponent(0);
            }
        }
        if (c instanceof JTextField || c instanceof TextField) {
            return 3;
        }
        if (c instanceof JLabel || c instanceof Label) {
            return 2;
        }
        if (c instanceof JCheckBox || c instanceof JRadioButton || c instanceof Checkbox) {
            return 16;
        }
        if (c instanceof AbstractButton || c instanceof Button) {
            return 5;
        }
        if (c instanceof JComboBox || c instanceof Choice) {
            return 11;
        }
        if (c instanceof JTextComponent || c instanceof TextComponent) {
            return 4;
        }
        if (c instanceof JPanel || c instanceof Canvas) {
            return 10;
        }
        if (c instanceof JList || c instanceof List) {
            return 6;
        }
        if (c instanceof JTable) {
            return 7;
        }
        if (c instanceof JSeparator) {
            return 18;
        }
        if (c instanceof JSpinner) {
            return 13;
        }
        if (c instanceof JTabbedPane) {
            return 19;
        }
        if (c instanceof JProgressBar) {
            return 14;
        }
        if (c instanceof JSlider) {
            return 12;
        }
        if (c instanceof JScrollPane) {
            return 8;
        }
        if (c instanceof JScrollBar || c instanceof Scrollbar) {
            return 17;
        }
        if (c instanceof Container) {
            return 1;
        }
        return 0;
    }

    public final int hashCode() {
        return this.getComponent().hashCode();
    }

    public final boolean equals(Object o) {
        if (!(o instanceof ComponentWrapper)) {
            return false;
        }
        return this.c.equals(((ComponentWrapper)o).getComponent());
    }

    @Override
    public int getContentBias() {
        return this.c instanceof JTextArea || this.c instanceof JEditorPane || this.c instanceof JComponent && Boolean.TRUE.equals(((JComponent)this.c).getClientProperty("migLayout.dynamicAspectRatio")) ? 0 : -1;
    }

    static {
        try {
            StringTokenizer st = new StringTokenizer(System.getProperty("java.version"), "._-+");
            int majorVersion = Integer.parseInt(st.nextToken());
            isJava9orLater = majorVersion >= 9;
        } catch (Exception exception) {
            // empty catch block
        }
    }
}

