/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterChoicesProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletionContext;
import org.fife.ui.autocomplete.SortByRelevanceComparator;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.rsyntaxtextarea.PopupWindowDecorator;

public class ParameterizedCompletionChoicesWindow
extends JWindow {
    private AutoCompletion ac;
    private JList<Completion> list;
    private DefaultListModel<Completion> model;
    private List<List<Completion>> choicesListList;
    private JScrollPane sp;
    private static final Comparator<Completion> SORT_BY_RELEVANCE_COMPARATOR = new SortByRelevanceComparator();

    public ParameterizedCompletionChoicesWindow(Window parent, AutoCompletion ac, final ParameterizedCompletionContext context) {
        super(parent);
        this.ac = ac;
        ComponentOrientation o = ac.getTextComponentOrientation();
        this.model = new DefaultListModel();
        this.list = new JList<Completion>(this.model);
        if (ac.getParamChoicesRenderer() != null) {
            this.list.setCellRenderer(ac.getParamChoicesRenderer());
        }
        this.list.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    context.insertSelectedChoice();
                }
            }
        });
        this.sp = new JScrollPane(this.list);
        this.setContentPane(this.sp);
        this.applyComponentOrientation(o);
        this.setFocusableWindowState(false);
        PopupWindowDecorator decorator = PopupWindowDecorator.get();
        if (decorator != null) {
            decorator.decorate(this);
        }
    }

    public String getSelectedChoice() {
        Completion c = this.list.getSelectedValue();
        return c == null ? null : c.toString();
    }

    public void incSelection(int amount) {
        int selection = this.list.getSelectedIndex();
        selection = (selection += amount) < 0 ? this.model.getSize() - 1 : (selection %= this.model.getSize());
        this.list.setSelectedIndex(selection);
        this.list.ensureIndexIsVisible(selection);
    }

    public void initialize(ParameterizedCompletion pc) {
        CompletionProvider provider = pc.getProvider();
        ParameterChoicesProvider pcp = provider.getParameterChoicesProvider();
        if (pcp == null) {
            this.choicesListList = null;
            return;
        }
        int paramCount = pc.getParamCount();
        this.choicesListList = new ArrayList<List<Completion>>(paramCount);
        JTextComponent tc = this.ac.getTextComponent();
        for (int i = 0; i < paramCount; ++i) {
            ParameterizedCompletion.Parameter param = pc.getParam(i);
            List<Completion> choices = pcp.getParameterChoices(tc, param);
            this.choicesListList.add(choices);
        }
    }

    public void setLocationRelativeTo(Rectangle r) {
        Rectangle screenBounds = Util.getScreenBoundsForPoint(r.x, r.y);
        int y = r.y + r.height + 5;
        int x = r.x;
        if (x < screenBounds.x) {
            x = screenBounds.x;
        } else if (x + this.getWidth() > screenBounds.x + screenBounds.width) {
            x = screenBounds.x + screenBounds.width - this.getWidth();
        }
        this.setLocation(x, y);
    }

    public void setParameter(int param, String prefix) {
        this.model.clear();
        ArrayList<Completion> temp = new ArrayList<Completion>();
        if (this.choicesListList != null && param >= 0 && param < this.choicesListList.size()) {
            List<Completion> choices = this.choicesListList.get(param);
            if (choices != null) {
                for (Completion completion : choices) {
                    String string = completion.getReplacementText();
                    if (prefix != null && !Util.startsWithIgnoreCase(string, prefix)) continue;
                    temp.add(completion);
                }
            }
            Comparator<Completion> c = null;
            c = SORT_BY_RELEVANCE_COMPARATOR;
            temp.sort(c);
            for (Completion completion : temp) {
                this.model.addElement(completion);
            }
            int n = Math.min(this.model.size(), 10);
            this.list.setVisibleRowCount(n);
            if (n == 0 && this.isVisible()) {
                this.setVisible(false);
            } else if (n > 0) {
                Dimension dimension = this.getPreferredSize();
                if (dimension.width < 150) {
                    this.setSize(150, dimension.height);
                } else {
                    this.pack();
                }
                if (this.sp.getVerticalScrollBar() != null && this.sp.getVerticalScrollBar().isVisible()) {
                    Dimension dimension2 = this.getSize();
                    int w = dimension2.width + this.sp.getVerticalScrollBar().getWidth() + 5;
                    this.setSize(w, dimension2.height);
                }
                this.list.setSelectedIndex(0);
                this.list.ensureIndexIsVisible(0);
                if (!this.isVisible()) {
                    this.setVisible(true);
                }
            }
        } else {
            this.setVisible(false);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible != this.isVisible()) {
            if (visible && this.model.size() == 0) {
                return;
            }
            super.setVisible(visible);
        }
    }

    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}

