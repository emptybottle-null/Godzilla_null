/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.ui.FlatMarginBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.HSLColor;
import com.formdev.flatlaf.util.ScaledEmptyBorder;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class FlatUIDefaultsInspector {
    private static final int KEY_MODIFIERS_MASK = 960;
    private static JFrame inspectorFrame;
    private final PropertyChangeListener lafListener = this::lafChanged;
    private final PropertyChangeListener lafDefaultsListener = this::lafDefaultsChanged;
    private boolean refreshPending;
    private Properties derivedColorKeys;
    private JPanel panel;
    private JPanel filterPanel;
    private JLabel flterLabel;
    private JTextField filterField;
    private JLabel valueTypeLabel;
    private JComboBox<String> valueTypeField;
    private JScrollPane scrollPane;
    private JTable table;
    private JPopupMenu tablePopupMenu;
    private JMenuItem copyKeyMenuItem;
    private JMenuItem copyValueMenuItem;
    private JMenuItem copyKeyAndValueMenuItem;

    public static void install(String activationKeys) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(activationKeys);
        Toolkit.getDefaultToolkit().addAWTEventListener(e -> {
            if (e.getID() == 402 && ((KeyEvent)e).getKeyCode() == keyStroke.getKeyCode() && (((KeyEvent)e).getModifiersEx() & 0x3C0) == (keyStroke.getModifiers() & 0x3C0)) {
                FlatUIDefaultsInspector.show();
            }
        }, 8L);
    }

    public static void show() {
        if (inspectorFrame != null) {
            FlatUIDefaultsInspector.ensureOnScreen(inspectorFrame);
            inspectorFrame.toFront();
            return;
        }
        inspectorFrame = new FlatUIDefaultsInspector().createFrame();
        inspectorFrame.setVisible(true);
    }

    public static void hide() {
        if (inspectorFrame != null) {
            inspectorFrame.dispose();
        }
    }

    public static JComponent createInspectorPanel() {
        return new FlatUIDefaultsInspector().panel;
    }

    private FlatUIDefaultsInspector() {
        this.initComponents();
        this.panel.setBorder(new ScaledEmptyBorder(10, 10, 10, 10));
        this.filterPanel.setBorder(new ScaledEmptyBorder(0, 0, 10, 0));
        this.filterField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void removeUpdate(DocumentEvent e) {
                FlatUIDefaultsInspector.this.filterChanged();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                FlatUIDefaultsInspector.this.filterChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                FlatUIDefaultsInspector.this.filterChanged();
            }
        });
        this.delegateKey(38, "unitScrollUp");
        this.delegateKey(40, "unitScrollDown");
        this.delegateKey(33, "scrollUp");
        this.delegateKey(34, "scrollDown");
        this.table.setModel(new ItemsTableModel(this.getUIDefaultsItems()));
        this.table.setDefaultRenderer(String.class, new KeyRenderer());
        this.table.setDefaultRenderer(Item.class, new ValueRenderer());
        this.table.getRowSorter().setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        Preferences prefs = this.getPrefs();
        TableColumnModel columnModel = this.table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(prefs.getInt("column1width", 100));
        columnModel.getColumn(1).setPreferredWidth(prefs.getInt("column2width", 100));
        PropertyChangeListener columnWidthListener = e -> {
            if ("width".equals(e.getPropertyName())) {
                prefs.putInt("column1width", columnModel.getColumn(0).getWidth());
                prefs.putInt("column2width", columnModel.getColumn(1).getWidth());
            }
        };
        columnModel.getColumn(0).addPropertyChangeListener(columnWidthListener);
        columnModel.getColumn(1).addPropertyChangeListener(columnWidthListener);
        String filter = prefs.get("filter", "");
        String valueType = prefs.get("valueType", null);
        if (filter != null && !filter.isEmpty()) {
            this.filterField.setText(filter);
        }
        if (valueType != null) {
            this.valueTypeField.setSelectedItem(valueType);
        }
        this.panel.addPropertyChangeListener("ancestor", e -> {
            if (e.getNewValue() != null) {
                UIManager.addPropertyChangeListener(this.lafListener);
                UIManager.getDefaults().addPropertyChangeListener(this.lafDefaultsListener);
            } else {
                UIManager.removePropertyChangeListener(this.lafListener);
                UIManager.getDefaults().removePropertyChangeListener(this.lafDefaultsListener);
            }
        });
        this.panel.registerKeyboardAction(e -> this.refresh(), KeyStroke.getKeyStroke(116, 0, false), 1);
    }

    private JFrame createFrame() {
        final JFrame frame = new JFrame();
        frame.setTitle("UI Defaults Inspector");
        frame.setDefaultCloseOperation(2);
        frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosed(WindowEvent e) {
                inspectorFrame = null;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                FlatUIDefaultsInspector.this.saveWindowBounds(frame);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                FlatUIDefaultsInspector.this.saveWindowBounds(frame);
            }
        });
        FlatUIDefaultsInspector.updateWindowTitle(frame);
        frame.getContentPane().add((Component)this.panel, "Center");
        Preferences prefs = this.getPrefs();
        int x = prefs.getInt("x", -1);
        int y = prefs.getInt("y", -1);
        int width = prefs.getInt("width", UIScale.scale(600));
        int height = prefs.getInt("height", UIScale.scale(800));
        frame.setSize(width, height);
        if (x != -1 && y != -1) {
            frame.setLocation(x, y);
            FlatUIDefaultsInspector.ensureOnScreen(frame);
        } else {
            frame.setLocationRelativeTo(null);
        }
        ((JComponent)frame.getContentPane()).registerKeyboardAction(e -> frame.dispose(), KeyStroke.getKeyStroke(27, 0, false), 1);
        return frame;
    }

    private void delegateKey(int keyCode, final String actionKey) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, 0);
        String actionMapKey = "delegate-" + actionKey;
        this.filterField.getInputMap().put(keyStroke, actionMapKey);
        this.filterField.getActionMap().put(actionMapKey, new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Action action = FlatUIDefaultsInspector.this.scrollPane.getActionMap().get(actionKey);
                if (action != null) {
                    action.actionPerformed(new ActionEvent(FlatUIDefaultsInspector.this.scrollPane, e.getID(), actionKey, e.getWhen(), e.getModifiers()));
                }
            }
        });
    }

    private static void ensureOnScreen(JFrame frame) {
        Rectangle frameBounds = frame.getBounds();
        boolean onScreen = false;
        for (GraphicsDevice screen : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            GraphicsConfiguration gc = screen.getDefaultConfiguration();
            Rectangle screenBounds = FlatUIUtils.subtractInsets(gc.getBounds(), Toolkit.getDefaultToolkit().getScreenInsets(gc));
            if (!frameBounds.intersects(screenBounds)) continue;
            onScreen = true;
            break;
        }
        if (!onScreen) {
            frame.setLocationRelativeTo(null);
        }
    }

    private void lafChanged(PropertyChangeEvent e) {
        if ("lookAndFeel".equals(e.getPropertyName())) {
            this.refresh();
        }
    }

    private void lafDefaultsChanged(PropertyChangeEvent e) {
        if (this.refreshPending) {
            return;
        }
        this.refreshPending = true;
        EventQueue.invokeLater(() -> {
            this.refresh();
            this.refreshPending = false;
        });
    }

    private void refresh() {
        ItemsTableModel model = (ItemsTableModel)this.table.getModel();
        model.setItems(this.getUIDefaultsItems());
        JFrame frame = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this.panel);
        if (frame != null) {
            FlatUIDefaultsInspector.updateWindowTitle(frame);
        }
    }

    private Item[] getUIDefaultsItems() {
        UIDefaults defaults = UIManager.getDefaults();
        UIDefaults lafDefaults = UIManager.getLookAndFeelDefaults();
        Set defaultsSet = defaults.entrySet();
        ArrayList<Item> items = new ArrayList<Item>(defaultsSet.size());
        HashSet keys = new HashSet(defaultsSet.size());
        Color[] pBaseColor = new Color[1];
        for (Map.Entry e : defaultsSet) {
            Color resolvedColor;
            Color[] value;
            Object key = e.getKey();
            if (!(key instanceof String) || (value = defaults.get(key)) instanceof Class || !keys.add(key)) continue;
            if (value instanceof DerivedColor && (resolvedColor = this.resolveDerivedColor(defaults, (String)key, (DerivedColor)value, pBaseColor)) != value) {
                value = new Color[]{resolvedColor, pBaseColor[0], (Color)value};
            }
            Object lafValue = null;
            if (defaults.containsKey(key)) {
                lafValue = lafDefaults.get(key);
            }
            items.add(new Item(String.valueOf(key), value, lafValue));
        }
        return items.toArray(new Item[items.size()]);
    }

    private Color resolveDerivedColor(UIDefaults defaults, String key, Color color, Color[] pBaseColor) {
        Object baseKey;
        if (pBaseColor != null) {
            pBaseColor[0] = null;
        }
        if (!(color instanceof DerivedColor)) {
            return color;
        }
        if (this.derivedColorKeys == null) {
            this.derivedColorKeys = this.loadDerivedColorKeys();
        }
        if ((baseKey = this.derivedColorKeys.get(key)) == null) {
            return color;
        }
        if ("null".equals(baseKey)) {
            return color;
        }
        Color baseColor = defaults.getColor(baseKey);
        if (baseColor == null) {
            return color;
        }
        if (baseColor instanceof DerivedColor) {
            baseColor = this.resolveDerivedColor(defaults, (String)baseKey, baseColor, null);
        }
        if (pBaseColor != null) {
            pBaseColor[0] = baseColor;
        }
        Color newColor = FlatUIUtils.deriveColor(color, baseColor);
        return new Color(newColor.getRGB(), true);
    }

    private Properties loadDerivedColorKeys() {
        Properties properties = new Properties();
        try (InputStream in = this.getClass().getResourceAsStream("/com/formdev/flatlaf/extras/resources/DerivedColorKeys.properties");){
            properties.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    private static void updateWindowTitle(JFrame frame) {
        String sep;
        String title = frame.getTitle();
        int sepIndex = title.indexOf(sep = "  -  ");
        if (sepIndex >= 0) {
            title = title.substring(0, sepIndex);
        }
        frame.setTitle(title + sep + UIManager.getLookAndFeel().getName());
    }

    private void saveWindowBounds(JFrame frame) {
        Preferences prefs = this.getPrefs();
        prefs.putInt("x", frame.getX());
        prefs.putInt("y", frame.getY());
        prefs.putInt("width", frame.getWidth());
        prefs.putInt("height", frame.getHeight());
    }

    private Preferences getPrefs() {
        return Preferences.userRoot().node("flatlaf-uidefaults-inspector");
    }

    private void filterChanged() {
        Pattern[] patterns;
        String filter = this.filterField.getText().trim();
        String valueType = (String)this.valueTypeField.getSelectedItem();
        String[] filters = !filter.isEmpty() ? filter.split(" +") : null;
        Pattern[] patternArray = patterns = filters != null ? new Pattern[filters.length] : null;
        if (filters != null) {
            for (int i = 0; i < filters.length; ++i) {
                filters[i] = filters[i].toLowerCase(Locale.ENGLISH);
                String f = filters[i];
                boolean matchBeginning = f.startsWith("^");
                boolean matchEnd = f.endsWith("$");
                if (f.indexOf(42) < 0 && f.indexOf(63) < 0 && !matchBeginning && !matchEnd) continue;
                if (matchBeginning) {
                    f = f.substring(1);
                }
                if (matchEnd) {
                    f = f.substring(0, f.length() - 1);
                }
                String regex = ("\\Q" + f + "\\E").replace("*", "\\E.*\\Q").replace("?", "\\E.\\Q");
                if (!matchBeginning) {
                    regex = ".*" + regex;
                }
                if (!matchEnd) {
                    regex = regex + ".*";
                }
                patterns[i] = Pattern.compile(regex);
            }
        }
        ItemsTableModel model = (ItemsTableModel)this.table.getModel();
        model.setFilter(item -> {
            if (valueType != null && !valueType.equals("(any)") && !valueType.equals(this.typeOfValue(item.value))) {
                return false;
            }
            if (filters == null) {
                return true;
            }
            String lkey = item.key.toLowerCase(Locale.ENGLISH);
            String lvalue = item.getValueAsString().toLowerCase(Locale.ENGLISH);
            for (int i = 0; i < filters.length; ++i) {
                String f;
                Pattern p = patterns[i];
                if (!(p != null ? p.matcher(lkey).matches() || p.matcher(lvalue).matches() : lkey.contains(f = filters[i]) || lvalue.contains(f))) continue;
                return true;
            }
            return false;
        });
        Preferences prefs = this.getPrefs();
        prefs.put("filter", filter);
        prefs.put("valueType", valueType);
    }

    private String typeOfValue(Object value) {
        if (value instanceof Boolean) {
            return "Boolean";
        }
        if (value instanceof Border) {
            return "Border";
        }
        if (value instanceof Color || value instanceof Color[]) {
            return "Color";
        }
        if (value instanceof Dimension) {
            return "Dimension";
        }
        if (value instanceof Float) {
            return "Float";
        }
        if (value instanceof Font) {
            return "Font";
        }
        if (value instanceof Icon) {
            return "Icon";
        }
        if (value instanceof Insets) {
            return "Insets";
        }
        if (value instanceof Integer) {
            return "Integer";
        }
        if (value instanceof String) {
            return "String";
        }
        return "(other)";
    }

    private void tableMousePressed(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) {
            return;
        }
        int row = this.table.rowAtPoint(e.getPoint());
        if (row >= 0 && !this.table.isRowSelected(row)) {
            this.table.setRowSelectionInterval(row, row);
        }
    }

    private void copyKey() {
        this.copyToClipboard(0);
    }

    private void copyValue() {
        this.copyToClipboard(1);
    }

    private void copyKeyAndValue() {
        this.copyToClipboard(-1);
    }

    private void copyToClipboard(int column) {
        int[] rows = this.table.getSelectedRows();
        if (rows.length == 0) {
            return;
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < rows.length; ++i) {
            if (i > 0) {
                buf.append('\n');
            }
            if (column < 0 || column == 0) {
                buf.append(this.table.getValueAt(rows[i], 0));
            }
            if (column < 0) {
                buf.append(" = ");
            }
            if (column >= 0 && column != 1) continue;
            buf.append(this.table.getValueAt(rows[i], 1));
        }
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(buf.toString()), null);
    }

    private void initComponents() {
        this.panel = new JPanel();
        this.filterPanel = new JPanel();
        this.flterLabel = new JLabel();
        this.filterField = new JTextField();
        this.valueTypeLabel = new JLabel();
        this.valueTypeField = new JComboBox();
        this.scrollPane = new JScrollPane();
        this.table = new JTable();
        this.tablePopupMenu = new JPopupMenu();
        this.copyKeyMenuItem = new JMenuItem();
        this.copyValueMenuItem = new JMenuItem();
        this.copyKeyAndValueMenuItem = new JMenuItem();
        this.panel.setLayout(new BorderLayout());
        this.filterPanel.setLayout(new GridBagLayout());
        ((GridBagLayout)this.filterPanel.getLayout()).columnWidths = new int[]{0, 0, 0, 0, 0};
        ((GridBagLayout)this.filterPanel.getLayout()).rowHeights = new int[]{0, 0};
        ((GridBagLayout)this.filterPanel.getLayout()).columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)this.filterPanel.getLayout()).rowWeights = new double[]{0.0, 1.0E-4};
        this.flterLabel.setText("Filter:");
        this.flterLabel.setLabelFor(this.filterField);
        this.flterLabel.setDisplayedMnemonic('F');
        this.filterPanel.add((Component)this.flterLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 10), 0, 0));
        this.filterField.putClientProperty("JTextField.placeholderText", "enter one or more filter strings, separated by space characters");
        this.filterPanel.add((Component)this.filterField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 10), 0, 0));
        this.valueTypeLabel.setText("Value Type:");
        this.valueTypeLabel.setLabelFor(this.valueTypeField);
        this.valueTypeLabel.setDisplayedMnemonic('T');
        this.filterPanel.add((Component)this.valueTypeLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 10), 0, 0));
        this.valueTypeField.setModel(new DefaultComboBoxModel<String>(new String[]{"(any)", "Boolean", "Border", "Color", "Dimension", "Float", "Font", "Icon", "Insets", "Integer", "String", "(other)"}));
        this.valueTypeField.addActionListener(e -> this.filterChanged());
        this.filterPanel.add(this.valueTypeField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        this.panel.add((Component)this.filterPanel, "North");
        this.table.setAutoCreateRowSorter(true);
        this.table.setComponentPopupMenu(this.tablePopupMenu);
        this.table.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e) {
                FlatUIDefaultsInspector.this.tableMousePressed(e);
            }
        });
        this.scrollPane.setViewportView(this.table);
        this.panel.add((Component)this.scrollPane, "Center");
        this.copyKeyMenuItem.setText("Copy Key");
        this.copyKeyMenuItem.addActionListener(e -> this.copyKey());
        this.tablePopupMenu.add(this.copyKeyMenuItem);
        this.copyValueMenuItem.setText("Copy Value");
        this.copyValueMenuItem.addActionListener(e -> this.copyValue());
        this.tablePopupMenu.add(this.copyValueMenuItem);
        this.copyKeyAndValueMenuItem.setText("Copy Key and Value");
        this.copyKeyAndValueMenuItem.addActionListener(e -> this.copyKeyAndValue());
        this.tablePopupMenu.add(this.copyKeyAndValueMenuItem);
    }

    private static class SafeIcon
    implements Icon {
        private final Icon icon;

        SafeIcon(Icon icon) {
            this.icon = icon;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            int width = this.getIconWidth();
            int height = this.getIconHeight();
            try {
                g.setColor(UIManager.getColor("Panel.background"));
                g.fillRect(x, y, width, height);
                this.icon.paintIcon(c, g, x, y);
            } catch (Exception ex) {
                g.setColor(Color.red);
                g.drawRect(x, y, width - 1, height - 1);
            }
        }

        @Override
        public int getIconWidth() {
            return this.icon.getIconWidth();
        }

        @Override
        public int getIconHeight() {
            return this.icon.getIconHeight();
        }
    }

    private static class ValueRenderer
    extends Renderer {
        private Item item;

        private ValueRenderer() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String toolTipText;
            this.item = (Item)value;
            this.init(table, this.item.key, isSelected, row);
            if (!(this.item.value instanceof Color) && !(this.item.value instanceof Color[])) {
                this.setBackground(null);
                this.setForeground(null);
            }
            if (!(this.item.value instanceof Icon)) {
                this.setIcon(null);
            }
            value = this.item.getValueAsString();
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (this.item.value instanceof Color || this.item.value instanceof Color[]) {
                Color color = this.item.value instanceof Color[] ? ((Color[])this.item.value)[0] : (Color)this.item.value;
                boolean isDark = new HSLColor(color).getLuminance() < 70.0f && color.getAlpha() >= 128;
                this.setBackground(color);
                this.setForeground(isDark ? Color.white : Color.black);
            } else if (this.item.value instanceof Icon) {
                Icon icon = (Icon)this.item.value;
                this.setIcon(new SafeIcon(icon));
            }
            String string = toolTipText = this.item.value instanceof Object[] ? Arrays.toString((Object[])this.item.value).replace(", ", ",\n") : String.valueOf(this.item.value);
            if (this.item.lafValue != null) {
                toolTipText = toolTipText + "    \n\nLaF UI default value was overridden with UIManager.put(key,value):\n    " + Item.valueAsString(this.item.lafValue) + "\n    " + String.valueOf(this.item.lafValue);
            }
            this.setToolTipText(toolTipText);
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (this.item.value instanceof Color || this.item.value instanceof Color[]) {
                int width = this.getWidth();
                int height = this.getHeight();
                Color background = this.getBackground();
                this.fillRect(g, background, 0, 0, width, height);
                if (this.item.value instanceof Color[]) {
                    int width2 = height * 2;
                    this.fillRect(g, ((Color[])this.item.value)[1], width - width2, 0, width2, height);
                    Color defaultColor = ((Color[])this.item.value)[2];
                    if (defaultColor != null && !defaultColor.equals(background)) {
                        int width3 = height / 2;
                        this.fillRect(g, defaultColor, width - width3, 0, width3, height);
                    }
                    int width4 = height / 4;
                    g.setColor(Color.magenta);
                    g.fillRect(width - width4, 0, width4, height);
                }
                FontMetrics fm = this.getFontMetrics(this.getFont());
                String text = this.getText();
                Rectangle textR = new Rectangle();
                this.layoutLabel(fm, text, textR);
                int x = textR.x;
                int y = textR.y + fm.getAscent();
                g.setColor(this.getForeground());
                int hslIndex = text.indexOf("HSL");
                if (hslIndex > 0) {
                    String hexText = text.substring(0, hslIndex);
                    String hslText = text.substring(hslIndex);
                    int hexWidth = Math.max(fm.stringWidth(hexText), fm.stringWidth("#12345678  "));
                    FlatUIUtils.drawString(this, g, hexText, x, y);
                    FlatUIUtils.drawString(this, g, hslText, x + hexWidth, y);
                } else {
                    FlatUIUtils.drawString(this, g, text, x, y);
                }
            } else {
                super.paintComponent(g);
            }
            this.paintSeparator(g);
        }

        private void fillRect(Graphics g, Color color, int x, int y, int width, int height) {
            if (color.getAlpha() != 255) {
                g.setColor(Color.white);
                g.fillRect(x, y, width, height);
            }
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    private static class KeyRenderer
    extends Renderer {
        private String key;
        private boolean isOverridden;
        private Icon overriddenIcon;

        private KeyRenderer() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            this.key = (String)value;
            this.init(table, this.key, isSelected, row);
            Item item = (Item)table.getValueAt(row, 1);
            this.isOverridden = item.lafValue != null;
            String toolTipText = this.key;
            if (this.isOverridden) {
                toolTipText = toolTipText + "    \n\nLaF UI default value was overridden with UIManager.put(key,value).";
            }
            this.setToolTipText(toolTipText);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            FontMetrics fm = this.getFontMetrics(this.getFont());
            Rectangle textR = new Rectangle();
            String clippedText = this.layoutLabel(fm, this.key, textR);
            int x = textR.x;
            int y = textR.y + fm.getAscent();
            int dot = this.key.indexOf(46);
            if (dot > 0 && !this.selected) {
                g.setColor(FlatUIUtils.getUIColor("Label.disabledForeground", FlatUIUtils.getUIColor("Label.disabledText", Color.gray)));
                if (dot >= clippedText.length()) {
                    FlatUIUtils.drawString(this, g, clippedText, x, y);
                } else {
                    String prefix = clippedText.substring(0, dot + 1);
                    String subkey = clippedText.substring(dot + 1);
                    FlatUIUtils.drawString(this, g, prefix, x, y);
                    g.setColor(this.getForeground());
                    FlatUIUtils.drawString(this, g, subkey, x + fm.stringWidth(prefix), y);
                }
            } else {
                g.setColor(this.getForeground());
                FlatUIUtils.drawString(this, g, clippedText, x, y);
            }
            if (this.isOverridden) {
                if (this.overriddenIcon == null) {
                    this.overriddenIcon = new FlatAbstractIcon(16, 16, null){

                        @Override
                        protected void paintIcon(Component c, Graphics2D g2) {
                            g2.setColor(FlatUIUtils.getUIColor("Actions.Red", Color.red));
                            g2.setStroke(new BasicStroke(2.0f));
                            g2.draw(FlatUIUtils.createPath(false, 3.0, 10.0, 8.0, 5.0, 13.0, 10.0));
                        }
                    };
                }
                this.overriddenIcon.paintIcon(this, g, this.getWidth() - this.overriddenIcon.getIconWidth(), (this.getHeight() - this.overriddenIcon.getIconHeight()) / 2);
            }
            this.paintSeparator(g);
        }
    }

    private static class Renderer
    extends DefaultTableCellRenderer {
        protected boolean selected;
        protected boolean first;

        private Renderer() {
        }

        protected void init(JTable table, String key, boolean selected, int row) {
            this.selected = selected;
            this.first = false;
            if (row > 0) {
                String prefix;
                String previousKey = (String)table.getValueAt(row - 1, 0);
                int dot = key.indexOf(46);
                this.first = dot > 0 ? !previousKey.startsWith(prefix = key.substring(0, dot + 1)) : previousKey.indexOf(46) > 0;
            }
        }

        protected void paintSeparator(Graphics g) {
            if (this.first && !this.selected) {
                g.setColor(FlatLaf.isLafDark() ? Color.gray : Color.lightGray);
                g.fillRect(0, 0, this.getWidth() - 1, 1);
            }
        }

        protected String layoutLabel(FontMetrics fm, String text, Rectangle textR) {
            int width = this.getWidth();
            int height = this.getHeight();
            Insets insets = this.getInsets();
            Rectangle viewR = new Rectangle(insets.left, insets.top, width - (insets.left + insets.right), height - (insets.top + insets.bottom));
            Rectangle iconR = new Rectangle();
            return SwingUtilities.layoutCompoundLabel(this, fm, text, null, this.getVerticalAlignment(), this.getHorizontalAlignment(), this.getVerticalTextPosition(), this.getHorizontalTextPosition(), viewR, iconR, textR, this.getIconTextGap());
        }
    }

    private static class ItemsTableModel
    extends AbstractTableModel {
        private Item[] allItems;
        private Item[] items;
        private Predicate<Item> filter;

        ItemsTableModel(Item[] items) {
            this.items = items;
            this.allItems = items;
        }

        void setItems(Item[] items) {
            this.items = items;
            this.allItems = items;
            this.setFilter(this.filter);
        }

        void setFilter(Predicate<Item> filter) {
            this.filter = filter;
            if (filter != null) {
                ArrayList<Item> list = new ArrayList<Item>(this.allItems.length);
                for (Item item : this.allItems) {
                    if (!filter.test(item)) continue;
                    list.add(item);
                }
                this.items = list.toArray(new Item[list.size()]);
            } else {
                this.items = this.allItems;
            }
            this.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return this.items.length;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0: {
                    return "Name";
                }
                case 1: {
                    return "Value";
                }
            }
            return super.getColumnName(columnIndex);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0: {
                    return String.class;
                }
                case 1: {
                    return Item.class;
                }
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Item item = this.items[rowIndex];
            switch (columnIndex) {
                case 0: {
                    return item.key;
                }
                case 1: {
                    return item;
                }
            }
            return null;
        }
    }

    private static class Item {
        final String key;
        final Object value;
        final Object lafValue;
        private String valueStr;

        Item(String key, Object value, Object lafValue) {
            this.key = key;
            this.value = value;
            this.lafValue = lafValue;
        }

        String getValueAsString() {
            if (this.valueStr == null) {
                this.valueStr = Item.valueAsString(this.value);
            }
            return this.valueStr;
        }

        static String valueAsString(Object value) {
            if (value instanceof Color || value instanceof Color[]) {
                Color color = value instanceof Color[] ? ((Color[])value)[0] : (Color)value;
                HSLColor hslColor = new HSLColor(color);
                if (color.getAlpha() == 255) {
                    return String.format("%-9s HSL %3d %3d %3d", Item.color2hex(color), (int)hslColor.getHue(), (int)hslColor.getSaturation(), (int)hslColor.getLuminance());
                }
                return String.format("%-9s HSL %3d %3d %3d %2d", Item.color2hex(color), (int)hslColor.getHue(), (int)hslColor.getSaturation(), (int)hslColor.getLuminance(), (int)(hslColor.getAlpha() * 100.0f));
            }
            if (value instanceof Insets) {
                Insets insets = (Insets)value;
                return insets.top + "," + insets.left + "," + insets.bottom + "," + insets.right;
            }
            if (value instanceof Dimension) {
                Dimension dim = (Dimension)value;
                return dim.width + "," + dim.height;
            }
            if (value instanceof Font) {
                Font font = (Font)value;
                String s = font.getFamily() + " " + font.getSize();
                if (font.isBold()) {
                    s = s + " bold";
                }
                if (font.isItalic()) {
                    s = s + " italic";
                }
                return s;
            }
            if (value instanceof Icon) {
                Icon icon = (Icon)value;
                return icon.getIconWidth() + "x" + icon.getIconHeight() + "   " + icon.getClass().getName();
            }
            if (value instanceof Border) {
                Border border = (Border)value;
                if (border instanceof FlatLineBorder) {
                    FlatLineBorder lineBorder = (FlatLineBorder)border;
                    return Item.valueAsString(lineBorder.getUnscaledBorderInsets()) + "  " + Item.color2hex(lineBorder.getLineColor()) + "  " + lineBorder.getLineThickness() + "    " + border.getClass().getName();
                }
                if (border instanceof EmptyBorder) {
                    Insets insets = border instanceof FlatEmptyBorder ? ((FlatEmptyBorder)border).getUnscaledBorderInsets() : ((EmptyBorder)border).getBorderInsets();
                    return Item.valueAsString(insets) + "    " + border.getClass().getName();
                }
                if (border instanceof FlatBorder || border instanceof FlatMarginBorder) {
                    return border.getClass().getName();
                }
                return String.valueOf(value);
            }
            if (value instanceof GrayFilter) {
                GrayFilter grayFilter = (GrayFilter)value;
                return grayFilter.getBrightness() + "," + grayFilter.getContrast() + " " + grayFilter.getAlpha() + "    " + grayFilter.getClass().getName();
            }
            if (value instanceof ActionMap) {
                ActionMap actionMap = (ActionMap)value;
                return "ActionMap (" + actionMap.size() + ")";
            }
            if (value instanceof InputMap) {
                InputMap inputMap = (InputMap)value;
                return "InputMap (" + inputMap.size() + ")";
            }
            if (value instanceof Object[]) {
                return Arrays.toString((Object[])value);
            }
            if (value instanceof int[]) {
                return Arrays.toString((int[])value);
            }
            return String.valueOf(value);
        }

        private static String color2hex(Color color) {
            boolean useShortFormat;
            int rgb = color.getRGB();
            boolean hasAlpha = color.getAlpha() != 255;
            boolean bl = useShortFormat = (rgb & 0xF0000000) == (rgb & 0xF000000) << 4 && (rgb & 0xF00000) == (rgb & 0xF0000) << 4 && (rgb & 0xF000) == (rgb & 0xF00) << 4 && (rgb & 0xF0) == (rgb & 0xF) << 4;
            if (useShortFormat) {
                int srgb = (rgb & 0xF0000) >> 8 | (rgb & 0xF00) >> 4 | rgb & 0xF;
                return String.format(hasAlpha ? "#%03X%X" : "#%03X", srgb, rgb >> 24 & 0xF);
            }
            return String.format(hasAlpha ? "#%06X%02X" : "#%06X", rgb & 0xFFFFFF, rgb >> 24 & 0xFF);
        }

        public String toString() {
            return this.getValueAsString();
        }
    }
}

