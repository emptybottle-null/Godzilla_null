/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.configure;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogTable;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationManager {
    private static final String CONFIG_FILE_NAME = "lf5_configuration.xml";
    private static final String NAME = "name";
    private static final String PATH = "path";
    private static final String SELECTED = "selected";
    private static final String EXPANDED = "expanded";
    private static final String CATEGORY = "category";
    private static final String FIRST_CATEGORY_NAME = "Categories";
    private static final String LEVEL = "level";
    private static final String COLORLEVEL = "colorlevel";
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    private static final String COLUMN = "column";
    private static final String NDCTEXTFILTER = "searchtext";
    private LogBrokerMonitor _monitor = null;
    private LogTable _table = null;

    public ConfigurationManager(LogBrokerMonitor monitor, LogTable table) {
        this._monitor = monitor;
        this._table = table;
        this.load();
    }

    public void save() {
        CategoryExplorerModel model = this._monitor.getCategoryExplorerTree().getExplorerModel();
        CategoryNode root = model.getRootCategoryNode();
        StringBuffer xml = new StringBuffer(2048);
        this.openXMLDocument(xml);
        this.openConfigurationXML(xml);
        this.processLogRecordFilter(this._monitor.getNDCTextFilter(), xml);
        this.processLogLevels(this._monitor.getLogLevelMenuItems(), xml);
        this.processLogLevelColors(this._monitor.getLogLevelMenuItems(), LogLevel.getLogLevelColorMap(), xml);
        this.processLogTableColumns(LogTableColumn.getLogTableColumns(), xml);
        this.processConfigurationNode(root, xml);
        this.closeConfigurationXML(xml);
        this.store(xml.toString());
    }

    public void reset() {
        this.deleteConfigurationFile();
        this.collapseTree();
        this.selectAllNodes();
    }

    public static String treePathToString(TreePath path) {
        StringBuffer sb = new StringBuffer();
        CategoryNode n = null;
        Object[] objects = path.getPath();
        for (int i = 1; i < objects.length; ++i) {
            n = (CategoryNode)objects[i];
            if (i > 1) {
                sb.append(".");
            }
            sb.append(n.getTitle());
        }
        return sb.toString();
    }

    protected void load() {
        File file = new File(this.getFilename());
        if (file.exists()) {
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);
                this.processRecordFilter(doc);
                this.processCategories(doc);
                this.processLogLevels(doc);
                this.processLogLevelColors(doc);
                this.processLogTableColumns(doc);
            } catch (Exception e) {
                System.err.println("Unable process configuration file at " + this.getFilename() + ". Error Message=" + e.getMessage());
            }
        }
    }

    protected void processRecordFilter(Document doc) {
        NodeList nodeList = doc.getElementsByTagName(NDCTEXTFILTER);
        Node n = nodeList.item(0);
        if (n == null) {
            return;
        }
        NamedNodeMap map = n.getAttributes();
        String text = this.getValue(map, NAME);
        if (text == null || text.equals("")) {
            return;
        }
        this._monitor.setNDCLogRecordFilter(text);
    }

    protected void processCategories(Document doc) {
        CategoryExplorerTree tree = this._monitor.getCategoryExplorerTree();
        CategoryExplorerModel model = tree.getExplorerModel();
        NodeList nodeList = doc.getElementsByTagName(CATEGORY);
        NamedNodeMap map = nodeList.item(0).getAttributes();
        int j = this.getValue(map, NAME).equalsIgnoreCase(FIRST_CATEGORY_NAME) ? 1 : 0;
        for (int i = nodeList.getLength() - 1; i >= j; --i) {
            Node n = nodeList.item(i);
            map = n.getAttributes();
            CategoryNode chnode = model.addCategory(new CategoryPath(this.getValue(map, PATH)));
            chnode.setSelected(this.getValue(map, SELECTED).equalsIgnoreCase("true"));
            if (this.getValue(map, EXPANDED).equalsIgnoreCase("true")) {
                // empty if block
            }
            tree.expandPath(model.getTreePathToRoot(chnode));
        }
    }

    protected void processLogLevels(Document doc) {
        NodeList nodeList = doc.getElementsByTagName(LEVEL);
        Map menuItems = this._monitor.getLogLevelMenuItems();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node n = nodeList.item(i);
            NamedNodeMap map = n.getAttributes();
            String name = this.getValue(map, NAME);
            try {
                JCheckBoxMenuItem item = (JCheckBoxMenuItem)menuItems.get(LogLevel.valueOf(name));
                item.setSelected(this.getValue(map, SELECTED).equalsIgnoreCase("true"));
                continue;
            } catch (LogLevelFormatException e) {
                // empty catch block
            }
        }
    }

    protected void processLogLevelColors(Document doc) {
        NodeList nodeList = doc.getElementsByTagName(COLORLEVEL);
        LogLevel.getLogLevelColorMap();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node n = nodeList.item(i);
            if (n == null) {
                return;
            }
            NamedNodeMap map = n.getAttributes();
            String name = this.getValue(map, NAME);
            try {
                LogLevel level = LogLevel.valueOf(name);
                int red = Integer.parseInt(this.getValue(map, RED));
                int green = Integer.parseInt(this.getValue(map, GREEN));
                int blue = Integer.parseInt(this.getValue(map, BLUE));
                Color c = new Color(red, green, blue);
                if (level == null) continue;
                level.setLogLevelColorMap(level, c);
                continue;
            } catch (LogLevelFormatException e) {
                // empty catch block
            }
        }
    }

    protected void processLogTableColumns(Document doc) {
        NodeList nodeList = doc.getElementsByTagName(COLUMN);
        Map menuItems = this._monitor.getLogTableColumnMenuItems();
        ArrayList<LogTableColumn> selectedColumns = new ArrayList<LogTableColumn>();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node n = nodeList.item(i);
            if (n == null) {
                return;
            }
            NamedNodeMap map = n.getAttributes();
            String name = this.getValue(map, NAME);
            try {
                LogTableColumn column = LogTableColumn.valueOf(name);
                JCheckBoxMenuItem item = (JCheckBoxMenuItem)menuItems.get(column);
                item.setSelected(this.getValue(map, SELECTED).equalsIgnoreCase("true"));
                if (item.isSelected()) {
                    selectedColumns.add(column);
                }
            } catch (LogTableColumnFormatException e) {
                // empty catch block
            }
            if (selectedColumns.isEmpty()) {
                this._table.setDetailedView();
                continue;
            }
            this._table.setView(selectedColumns);
        }
    }

    protected String getValue(NamedNodeMap map, String attr) {
        Node n = map.getNamedItem(attr);
        return n.getNodeValue();
    }

    protected void collapseTree() {
        CategoryExplorerTree tree = this._monitor.getCategoryExplorerTree();
        for (int i = tree.getRowCount() - 1; i > 0; --i) {
            tree.collapseRow(i);
        }
    }

    protected void selectAllNodes() {
        CategoryExplorerModel model = this._monitor.getCategoryExplorerTree().getExplorerModel();
        CategoryNode root = model.getRootCategoryNode();
        Enumeration all = root.breadthFirstEnumeration();
        CategoryNode n = null;
        while (all.hasMoreElements()) {
            n = (CategoryNode)all.nextElement();
            n.setSelected(true);
        }
    }

    protected void store(String s) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.getFilename()));
            writer.print(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void deleteConfigurationFile() {
        try {
            File f = new File(this.getFilename());
            if (f.exists()) {
                f.delete();
            }
        } catch (SecurityException e) {
            System.err.println("Cannot delete " + this.getFilename() + " because a security violation occured.");
        }
    }

    protected String getFilename() {
        String home = System.getProperty("user.home");
        String sep = System.getProperty("file.separator");
        return home + sep + "lf5" + sep + CONFIG_FILE_NAME;
    }

    private void processConfigurationNode(CategoryNode node, StringBuffer xml) {
        CategoryExplorerModel model = this._monitor.getCategoryExplorerTree().getExplorerModel();
        Enumeration all = node.breadthFirstEnumeration();
        CategoryNode n = null;
        while (all.hasMoreElements()) {
            n = (CategoryNode)all.nextElement();
            this.exportXMLElement(n, model.getTreePathToRoot(n), xml);
        }
    }

    private void processLogLevels(Map logLevelMenuItems, StringBuffer xml) {
        xml.append("\t<loglevels>\r\n");
        Iterator it = logLevelMenuItems.keySet().iterator();
        while (it.hasNext()) {
            LogLevel level = (LogLevel)it.next();
            JCheckBoxMenuItem item = (JCheckBoxMenuItem)logLevelMenuItems.get(level);
            this.exportLogLevelXMLElement(level.getLabel(), item.isSelected(), xml);
        }
        xml.append("\t</loglevels>\r\n");
    }

    private void processLogLevelColors(Map logLevelMenuItems, Map logLevelColors, StringBuffer xml) {
        xml.append("\t<loglevelcolors>\r\n");
        Iterator it = logLevelMenuItems.keySet().iterator();
        while (it.hasNext()) {
            LogLevel level = (LogLevel)it.next();
            Color color = (Color)logLevelColors.get(level);
            this.exportLogLevelColorXMLElement(level.getLabel(), color, xml);
        }
        xml.append("\t</loglevelcolors>\r\n");
    }

    private void processLogTableColumns(List logTableColumnMenuItems, StringBuffer xml) {
        xml.append("\t<logtablecolumns>\r\n");
        Iterator it = logTableColumnMenuItems.iterator();
        while (it.hasNext()) {
            LogTableColumn column = (LogTableColumn)it.next();
            JCheckBoxMenuItem item = this._monitor.getTableColumnMenuItem(column);
            this.exportLogTableColumnXMLElement(column.getLabel(), item.isSelected(), xml);
        }
        xml.append("\t</logtablecolumns>\r\n");
    }

    private void processLogRecordFilter(String text, StringBuffer xml) {
        xml.append("\t<").append(NDCTEXTFILTER).append(" ");
        xml.append(NAME).append("=\"").append(text).append("\"");
        xml.append("/>\r\n");
    }

    private void openXMLDocument(StringBuffer xml) {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
    }

    private void openConfigurationXML(StringBuffer xml) {
        xml.append("<configuration>\r\n");
    }

    private void closeConfigurationXML(StringBuffer xml) {
        xml.append("</configuration>\r\n");
    }

    private void exportXMLElement(CategoryNode node, TreePath path, StringBuffer xml) {
        CategoryExplorerTree tree = this._monitor.getCategoryExplorerTree();
        xml.append("\t<").append(CATEGORY).append(" ");
        xml.append(NAME).append("=\"").append(node.getTitle()).append("\" ");
        xml.append(PATH).append("=\"").append(ConfigurationManager.treePathToString(path)).append("\" ");
        xml.append(EXPANDED).append("=\"").append(tree.isExpanded(path)).append("\" ");
        xml.append(SELECTED).append("=\"").append(node.isSelected()).append("\"/>\r\n");
    }

    private void exportLogLevelXMLElement(String label, boolean selected, StringBuffer xml) {
        xml.append("\t\t<").append(LEVEL).append(" ").append(NAME);
        xml.append("=\"").append(label).append("\" ");
        xml.append(SELECTED).append("=\"").append(selected);
        xml.append("\"/>\r\n");
    }

    private void exportLogLevelColorXMLElement(String label, Color color, StringBuffer xml) {
        xml.append("\t\t<").append(COLORLEVEL).append(" ").append(NAME);
        xml.append("=\"").append(label).append("\" ");
        xml.append(RED).append("=\"").append(color.getRed()).append("\" ");
        xml.append(GREEN).append("=\"").append(color.getGreen()).append("\" ");
        xml.append(BLUE).append("=\"").append(color.getBlue());
        xml.append("\"/>\r\n");
    }

    private void exportLogTableColumnXMLElement(String label, boolean selected, StringBuffer xml) {
        xml.append("\t\t<").append(COLUMN).append(" ").append(NAME);
        xml.append("=\"").append(label).append("\" ");
        xml.append(SELECTED).append("=\"").append(selected);
        xml.append("\"/>\r\n");
    }
}

