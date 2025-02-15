/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.text.View;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.EmptyIcon;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.autocomplete.VariableCompletion;

public class CompletionCellRenderer
extends DefaultListCellRenderer {
    private static Color altBG;
    private Font font;
    private boolean showTypes;
    private String typeColor;
    private boolean selected;
    private Color realBG;
    private String paramColor;
    private Icon emptyIcon;
    private Rectangle paintTextR;
    private DefaultListCellRenderer delegate;
    private static final String SUBSTANCE_RENDERER_CLASS_NAME = "org.pushingpixels.substance.api.renderer.SubstanceDefaultListCellRenderer";
    private static final String PREFIX = "<html><nobr>";

    public CompletionCellRenderer() {
        this.init();
    }

    public CompletionCellRenderer(DefaultListCellRenderer delegate) {
        this.setDelegateRenderer(delegate);
        this.init();
    }

    protected Icon createEmptyIcon() {
        return new EmptyIcon(16);
    }

    private String createParamColor() {
        return Util.isLightForeground(this.getForeground()) ? Util.getHexString(Util.getHyperlinkForeground()) : "#aa0077";
    }

    private String createTypeColor() {
        return "#808080";
    }

    public void delegateToSubstanceRenderer() throws Exception {
        Class<?> clazz = Class.forName(SUBSTANCE_RENDERER_CLASS_NAME);
        DefaultListCellRenderer delegate = (DefaultListCellRenderer)clazz.newInstance();
        this.setDelegateRenderer(delegate);
    }

    public static Color getAlternateBackground() {
        return altBG;
    }

    public DefaultListCellRenderer getDelegateRenderer() {
        return this.delegate;
    }

    public Font getDisplayFont() {
        return this.font;
    }

    protected Icon getEmptyIcon() {
        if (this.emptyIcon == null) {
            this.emptyIcon = this.createEmptyIcon();
        }
        return this.emptyIcon;
    }

    protected Icon getIcon(String resource) {
        URL url = this.getClass().getResource(resource);
        if (url == null) {
            File file = new File(resource);
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            }
        }
        return url != null ? new ImageIcon(url) : null;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean hasFocus) {
        super.getListCellRendererComponent((JList<?>)list, value, index, selected, hasFocus);
        if (this.font != null) {
            this.setFont(this.font);
        }
        this.selected = selected;
        this.realBG = altBG != null && (index & 1) == 1 ? altBG : list.getBackground();
        Completion c = (Completion)value;
        this.setIcon(c.getIcon());
        if (c instanceof FunctionCompletion) {
            FunctionCompletion fc = (FunctionCompletion)value;
            this.prepareForFunctionCompletion(list, fc, index, selected, hasFocus);
        } else if (c instanceof VariableCompletion) {
            VariableCompletion vc = (VariableCompletion)value;
            this.prepareForVariableCompletion(list, vc, index, selected, hasFocus);
        } else if (c instanceof TemplateCompletion) {
            TemplateCompletion tc = (TemplateCompletion)value;
            this.prepareForTemplateCompletion(list, tc, index, selected, hasFocus);
        } else if (c instanceof MarkupTagCompletion) {
            MarkupTagCompletion mtc = (MarkupTagCompletion)value;
            this.prepareForMarkupTagCompletion(list, mtc, index, selected, hasFocus);
        } else {
            this.prepareForOtherCompletion(list, c, index, selected, hasFocus);
        }
        if (this.delegate != null) {
            this.delegate.getListCellRendererComponent((JList<?>)list, this.getText(), index, selected, hasFocus);
            this.delegate.setFont(this.getFont());
            this.delegate.setIcon(this.getIcon());
            return this.delegate;
        }
        if (!selected && (index & 1) == 1 && altBG != null) {
            this.setBackground(altBG);
        }
        return this;
    }

    public boolean getShowTypes() {
        return this.showTypes;
    }

    private void init() {
        this.setShowTypes(true);
        this.typeColor = this.createTypeColor();
        this.paramColor = this.createParamColor();
        this.paintTextR = new Rectangle();
    }

    @Override
    protected void paintComponent(Graphics g) {
        String text;
        g.setColor(this.realBG);
        int iconW = 0;
        if (this.getIcon() != null) {
            iconW = this.getIcon().getIconWidth();
        }
        if (this.selected && iconW > 0) {
            g.fillRect(0, 0, iconW, this.getHeight());
            g.setColor(this.getBackground());
            g.fillRect(iconW, 0, this.getWidth() - iconW, this.getHeight());
        } else {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (this.getIcon() != null) {
            Icon icon = this.getIcon();
            icon.paintIcon(this, g, 0, (this.getHeight() - icon.getIconHeight()) / 2);
        }
        if ((text = this.getText()) != null) {
            this.paintTextR.setBounds(iconW, 0, this.getWidth() - iconW, this.getHeight());
            this.paintTextR.x += 3;
            int space = this.paintTextR.height - g.getFontMetrics().getHeight();
            View v = (View)this.getClientProperty("html");
            if (v != null) {
                this.paintTextR.y += space / 2;
                this.paintTextR.height -= space;
                v.paint(g, this.paintTextR);
            } else {
                int textX = this.paintTextR.x;
                int textY = this.paintTextR.y;
                g.drawString(text, textX, textY);
            }
        }
    }

    protected void prepareForFunctionCompletion(JList list, FunctionCompletion fc, int index, boolean selected, boolean hasFocus) {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(fc.getName());
        char paramListStart = fc.getProvider().getParameterListStart();
        if (paramListStart != '\u0000') {
            sb.append(paramListStart);
        }
        int paramCount = fc.getParamCount();
        for (int i = 0; i < paramCount; ++i) {
            ParameterizedCompletion.Parameter param = fc.getParam(i);
            String type = param.getType();
            String name = param.getName();
            if (type != null) {
                if (!selected) {
                    sb.append("<font color='").append(this.paramColor).append("'>");
                }
                sb.append(type);
                if (!selected) {
                    sb.append("</font>");
                }
                if (name != null) {
                    sb.append(' ');
                }
            }
            if (name != null) {
                sb.append(name);
            }
            if (i >= paramCount - 1) continue;
            sb.append(fc.getProvider().getParameterListSeparator());
        }
        char paramListEnd = fc.getProvider().getParameterListEnd();
        if (paramListEnd != '\u0000') {
            sb.append(paramListEnd);
        }
        if (this.getShowTypes() && fc.getType() != null) {
            sb.append(" : ");
            if (!selected) {
                sb.append("<font color='").append(this.typeColor).append("'>");
            }
            sb.append(fc.getType());
            if (!selected) {
                sb.append("</font>");
            }
        }
        this.setText(sb.toString());
    }

    protected void prepareForMarkupTagCompletion(JList list, MarkupTagCompletion mc, int index, boolean selected, boolean hasFocus) {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(mc.getName());
        this.setText(sb.toString());
    }

    protected void prepareForOtherCompletion(JList list, Completion c, int index, boolean selected, boolean hasFocus) {
        String definition;
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(c.getInputText());
        if (c instanceof BasicCompletion && (definition = ((BasicCompletion)c).getShortDescription()) != null) {
            sb.append(" - ");
            if (!selected) {
                sb.append("<font color='").append(this.typeColor).append("'>");
            }
            sb.append(definition);
            if (!selected) {
                sb.append("</font>");
            }
        }
        this.setText(sb.toString());
    }

    protected void prepareForTemplateCompletion(JList list, TemplateCompletion tc, int index, boolean selected, boolean hasFocus) {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(tc.getInputText());
        String definition = tc.getShortDescription();
        if (definition != null) {
            sb.append(" - ");
            if (!selected) {
                sb.append("<font color='").append(this.typeColor).append("'>");
            }
            sb.append(definition);
            if (!selected) {
                sb.append("</font>");
            }
        }
        this.setText(sb.toString());
    }

    protected void prepareForVariableCompletion(JList list, VariableCompletion vc, int index, boolean selected, boolean hasFocus) {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(vc.getName());
        if (this.getShowTypes() && vc.getType() != null) {
            sb.append(" : ");
            if (!selected) {
                sb.append("<font color='").append(this.typeColor).append("'>");
            }
            sb.append(vc.getType());
            if (!selected) {
                sb.append("</font>");
            }
        }
        this.setText(sb.toString());
    }

    public static void setAlternateBackground(Color altBG) {
        CompletionCellRenderer.altBG = altBG;
    }

    public void setDelegateRenderer(DefaultListCellRenderer delegate) {
        this.delegate = delegate;
    }

    public void setDisplayFont(Font font) {
        this.font = font;
    }

    protected void setIconWithDefault(Completion completion) {
        this.setIconWithDefault(completion, this.getEmptyIcon());
    }

    protected void setIconWithDefault(Completion completion, Icon defaultIcon) {
        Icon icon = completion.getIcon();
        this.setIcon(icon != null ? icon : (defaultIcon != null ? defaultIcon : this.emptyIcon));
    }

    public void setParamColor(Color color) {
        if (color != null) {
            this.paramColor = Util.getHexString(color);
        }
    }

    public void setShowTypes(boolean show) {
        this.showTypes = show;
    }

    public void setTypeColor(Color color) {
        if (color != null) {
            this.typeColor = Util.getHexString(color);
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.delegate != null) {
            SwingUtilities.updateComponentTreeUI(this.delegate);
        }
        this.paramColor = this.createParamColor();
    }
}

