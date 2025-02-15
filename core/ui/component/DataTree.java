/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.ui.component.listener.ActionDblClick;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class DataTree
extends JTree {
    private DefaultMutableTreeNode rootNode = null;
    private RightClickEvent rightClickEvent;
    private ImageIcon leafIcon;

    public DataTree(String fileString, DefaultMutableTreeNode root_Node) {
        super(root_Node);
        this.rootNode = root_Node;
        this.initJTree();
    }

    private void initJTree() {
        this.rightClickEvent = new RightClickEvent(this);
        this.addMouseListener(this.rightClickEvent);
        this.getSelectionModel().setSelectionMode(1);
    }

    public void setActionDbclick(ActionDblClick actionDblClick) {
        this.rightClickEvent.setActionDblClick(actionDblClick);
    }

    public void setChildPopupMenu(JPopupMenu popupMenu) {
        this.rightClickEvent.setChildPopupMenu(popupMenu);
    }

    public void setParentPopupMenu(JPopupMenu popupMenu) {
        this.rightClickEvent.setParentPopupMenu(popupMenu);
    }

    public void deleteNote(String fileString) {
        DefaultMutableTreeNode defaultMutableTreeNode = this.rootNode;
        String[] paths = this.parseFile(fileString);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)defaultMutableTreeNode.getPath()[0];
        DefaultMutableTreeNode lasTreeNode = null;
        for (int i = 0; i < paths.length; ++i) {
            if ((node = this.FindTreeNote(node, paths[i])) == null) {
                return;
            }
            if (i != paths.length - 2) continue;
            lasTreeNode = node;
        }
        if (lasTreeNode != null) {
            lasTreeNode.remove(lasTreeNode);
        }
    }

    public void setLeafIcon(ImageIcon imageIcon) {
        this.leafIcon = imageIcon;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.leafIcon != null) {
            DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer)this.getCellRenderer();
            cellRenderer.setLeafIcon(cellRenderer.getClosedIcon());
        }
    }

    @Override
    public void removeAll() {
        super.removeAll();
        this.rootNode.removeAllChildren();
        this.updateUI();
    }

    public void MoveNoteName(String fileString, String rename) {
        DefaultMutableTreeNode defaultMutableTreeNode = this.rootNode;
        String[] paths = this.parseFile(fileString);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)defaultMutableTreeNode.getPath()[0];
        for (int i = 0; i < paths.length; ++i) {
            if ((node = this.FindTreeNote(node, paths[i])) != null) continue;
            return;
        }
        node.setUserObject(rename);
    }

    public String GetSelectFile() {
        TreePath paths = this.getSelectionPath();
        ArrayList<String> pathList = new ArrayList<String>();
        TreePath lastTreePath = paths;
        DefaultMutableTreeNode lastNode = null;
        do {
            try {
                lastNode = (DefaultMutableTreeNode)lastTreePath.getLastPathComponent();
            } catch (Exception e) {
                return "";
            }
            pathList.add((String)lastNode.getUserObject());
        } while ((lastTreePath = lastTreePath.getParentPath()) != null);
        pathList.remove(pathList.size() - 1);
        Collections.reverse(pathList);
        if (pathList.size() > 0) {
            return this.parseFile(pathList);
        }
        return "";
    }

    public void setSelectNote(String pathString) {
        DefaultMutableTreeNode node;
        if (pathString == null || pathString.trim().isEmpty()) {
            return;
        }
        DefaultMutableTreeNode defaultMutableTreeNode = this.rootNode;
        String[] paths = this.parseFile(pathString);
        DefaultMutableTreeNode lastTreeNode = node = (DefaultMutableTreeNode)defaultMutableTreeNode.getPath()[0];
        for (int i = 0; i < paths.length; ++i) {
            if ((node = this.FindTreeNote(node, paths[i])) == null) {
                return;
            }
            lastTreeNode = node;
        }
        if (lastTreeNode != null) {
            this.setSelectionPath(new TreePath(lastTreeNode.getPath()));
        }
        this.updateUI();
    }

    public void AddNote(String pathString) {
        DefaultMutableTreeNode node;
        if (pathString == null || pathString.trim().isEmpty()) {
            return;
        }
        DefaultMutableTreeNode defaultMutableTreeNode = this.rootNode;
        String[] paths = this.parseFile(pathString);
        DefaultMutableTreeNode lastTreeNode = node = (DefaultMutableTreeNode)defaultMutableTreeNode.getPath()[0];
        boolean findSate = true;
        for (int i = 0; i < paths.length; ++i) {
            DefaultMutableTreeNode _note;
            if (findSate) {
                if ((node = this.FindTreeNote(node, paths[i])) == null) {
                    findSate = false;
                    _note = new DefaultMutableTreeNode(paths[i]);
                    lastTreeNode.add(_note);
                    lastTreeNode = _note;
                    continue;
                }
                lastTreeNode = node;
                continue;
            }
            _note = new DefaultMutableTreeNode(paths[i]);
            lastTreeNode.add(_note);
            lastTreeNode = _note;
        }
        if (lastTreeNode != null) {
            this.expandPath(new TreePath(((DefaultMutableTreeNode)lastTreeNode.getParent()).getPath()));
        }
        this.updateUI();
    }

    protected String[] parseFile(String fileString) {
        fileString = this.replaceSpecial(fileString);
        String[] retStrings = null;
        if (fileString.charAt(0) == '/') {
            retStrings = fileString.split("/");
            if (retStrings.length == 0) {
                retStrings = new String[]{"/"};
            } else {
                retStrings[0] = "/";
            }
        } else {
            retStrings = fileString.split("/");
        }
        return retStrings;
    }

    protected String parseFile(List<String> pathList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pathList.size(); ++i) {
            builder.append("/");
            builder.append(pathList.get(i));
        }
        return this.replaceSpecial(builder.substring(1));
    }

    protected String parseFile2(String file) {
        return this.parseFile(new CopyOnWriteArrayList<String>(this.parseFile(file)));
    }

    private String replaceSpecial(String string) {
        return string.replaceAll("\\\\+", "/").trim().replaceAll("/+", "/").trim();
    }

    private DefaultMutableTreeNode FindTreeNote(DefaultMutableTreeNode node, String noteString) {
        Enumeration e = node.children();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode)e.nextElement();
            if (!n.getUserObject().equals(noteString)) continue;
            return n;
        }
        return null;
    }

    private class RightClickEvent
    extends MouseAdapter {
        private ActionDblClick actionDblClick;
        private JPopupMenu childPopupMenu;
        private JPopupMenu parentPopupMenu;
        private final DataTree jTree;

        public RightClickEvent(DataTree tree) {
            this.jTree = tree;
        }

        public ActionDblClick getActionDblClick() {
            return this.actionDblClick;
        }

        public void setActionDblClick(ActionDblClick actionDblClick) {
            this.actionDblClick = actionDblClick;
        }

        public void setChildPopupMenu(JPopupMenu popupMenu) {
            this.childPopupMenu = popupMenu;
        }

        public void setParentPopupMenu(JPopupMenu parentPopupMenu) {
            this.parentPopupMenu = parentPopupMenu;
        }

        @Override
        public void mouseClicked(MouseEvent paramMouseEvent) {
            if (SwingUtilities.isRightMouseButton(paramMouseEvent)) {
                if ((this.childPopupMenu != null || this.parentPopupMenu != null) && this.jTree.getSelectionPath() != null) {
                    String selectedPath;
                    if (((DefaultMutableTreeNode)this.jTree.getLastSelectedPathComponent()).getChildCount() == 0 && this.childPopupMenu != null) {
                        String selectedPath2 = this.jTree.GetSelectFile();
                        if (selectedPath2 != null && !selectedPath2.trim().isEmpty()) {
                            this.childPopupMenu.show(this.jTree, paramMouseEvent.getX(), paramMouseEvent.getY());
                        }
                    } else if (this.parentPopupMenu != null && (selectedPath = this.jTree.GetSelectFile()) != null && !selectedPath.trim().isEmpty()) {
                        this.parentPopupMenu.show(this.jTree, paramMouseEvent.getX(), paramMouseEvent.getY());
                    }
                }
            } else if (paramMouseEvent.getClickCount() == 2 && this.actionDblClick != null && !DataTree.this.GetSelectFile().trim().isEmpty()) {
                this.actionDblClick.dblClick(paramMouseEvent);
            }
        }
    }
}

