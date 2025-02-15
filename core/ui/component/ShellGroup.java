/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.Db;
import core.EasyI18N;
import core.ui.MainActivity;
import core.ui.component.DataTree;
import core.ui.component.dialog.GOptionPane;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;

public class ShellGroup
extends DataTree {
    protected JPopupMenu childPopupMenu = new JPopupMenu();

    public ShellGroup() {
        this("", new DefaultMutableTreeNode(EasyI18N.getI18nString("\u5206\u7ec4")));
    }

    public ShellGroup(String fileString, DefaultMutableTreeNode root_Node) {
        super(fileString, root_Node);
        JMenuItem newGroupItem = new JMenuItem("\u65b0\u5efa\u7ec4");
        newGroupItem.setActionCommand("newGroup");
        JMenuItem renameItem = new JMenuItem("\u79fb\u52a8/\u91cd\u547d\u540d");
        renameItem.setActionCommand("rename");
        JMenuItem copyPathItem = new JMenuItem("\u590d\u5236\u5f53\u524d\u7ec4\u8def\u5f84");
        copyPathItem.setActionCommand("copyPath");
        JMenuItem refreshItem = new JMenuItem("\u5237\u65b0");
        refreshItem.setActionCommand("refresh");
        JMenuItem deleteCurrentGroupItem = new JMenuItem("\u5220\u9664\u5f53\u524d\u7ec4");
        deleteCurrentGroupItem.setActionCommand("deleteGroup");
        JMenuItem deleteCurrentGroupAndDeleteWebshellItem = new JMenuItem("\u5220\u9664\u5f53\u524d\u7ec4\u5e76\u5220\u9664\u6240\u6709\u6210\u5458");
        deleteCurrentGroupAndDeleteWebshellItem.setActionCommand("deleteCurrentGroupAndDeleteWebshell");
        JMenuItem deleteCurrentGroupDontDeleteWebshellItem = new JMenuItem("\u5220\u9664\u5f53\u524d\u7ec4\u4f46\u4e0d\u5220\u9664\u6240\u6709\u6210\u5458");
        deleteCurrentGroupDontDeleteWebshellItem.setActionCommand("deleteCurrentGroupDontDeleteWebshell");
        this.childPopupMenu.add(newGroupItem);
        this.childPopupMenu.add(renameItem);
        this.childPopupMenu.add(copyPathItem);
        this.childPopupMenu.add(refreshItem);
        this.childPopupMenu.add(deleteCurrentGroupItem);
        this.childPopupMenu.add(deleteCurrentGroupAndDeleteWebshellItem);
        this.childPopupMenu.add(deleteCurrentGroupDontDeleteWebshellItem);
        this.setChildPopupMenu(this.childPopupMenu);
        this.setParentPopupMenu(this.childPopupMenu);
        automaticBindClick.bindMenuItemClick(this.childPopupMenu, null, this);
        this.setLeafIcon(new ImageIcon(this.getClass().getResource("/images/folder.png")));
        this.refreshMenuItemClick(null);
        EasyI18N.installObject(this);
    }

    protected void newGroupMenuItemClick(ActionEvent e) {
        String inputString = GOptionPane.showInputDialog("\u8bf7\u8f93\u5165\u65b0\u7ec4\u540d", (Object)"newGroup");
        if (inputString != null && !"/".equals(inputString.trim())) {
            String selectedString = this.GetSelectFile();
            String newGroup = this.parseFile2(selectedString + "/" + inputString);
            if (Db.addGroup(newGroup) > 0) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u6dfb\u52a0\u6210\u529f!");
                this.refreshMenuItemClick(e);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u6dfb\u52a0\u5931\u8d25 \u8bf7\u68c0\u67e5\u7ec4\u662f\u5426\u5b58\u5728 \u63a7\u5236\u53f0\u662f\u5426\u6709\u62a5\u9519!");
            }
        } else {
            Log.error("\u7528\u6237\u672a\u8f93\u5165\u6570\u636e");
        }
    }

    protected void renameMenuItemClick(ActionEvent e) {
        String inputString = GOptionPane.showInputDialog("\u8bf7\u8f93\u5165\u65b0\u7ec4\u540d", (Object)this.GetSelectFile());
        if (inputString != null && !"/".equals(inputString.trim())) {
            String newGroup;
            String oldGroup = this.GetSelectFile();
            if (Db.renameGroup(oldGroup, newGroup = this.parseFile2("/" + inputString)) > 0) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u79fb\u52a8\u6210\u529f!");
                this.refreshMenuItemClick(e);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u79fb\u52a8\u5931\u8d25 \u8bf7\u68c0\u67e5\u65b0\u7ec4\u662f\u5426\u5b58\u5728 \u63a7\u5236\u53f0\u662f\u5426\u6709\u62a5\u9519!");
            }
        } else {
            Log.error("\u7528\u6237\u672a\u8f93\u5165\u6570\u636e");
        }
    }

    protected void refreshMenuItemClick(ActionEvent e) {
        this.removeAll();
        this.AddNote("/");
        Db.getAllGroup().forEach(id -> this.AddNote(id.toString()));
    }

    protected void deleteGroupMenuItemClick(ActionEvent e) {
        String groupId = this.GetSelectFile();
        if (groupId != null && !"/".equals(groupId.trim())) {
            if (0 != GOptionPane.showConfirmDialog(MainActivity.getMainActivityFrame(), String.format("\u786e\u5b9a\u5220\u9664\u7ec4\uff1a%s \u5417?", groupId, "\u8b66\u544a", 0))) {
                return;
            }
            if (Db.removeGroup(groupId, "/") > 0) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u6210\u529f! \u6210\u5458\u5df2\u79fb\u52a8\u5230 / ");
                this.refreshMenuItemClick(e);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u5931\u8d25 \u8bf7\u68c0\u67e5\u7ec4\u662f\u5426\u5b58\u5728 \u63a7\u5236\u53f0\u662f\u5426\u6709\u62a5\u9519!");
            }
        } else {
            Log.error("group\u662f\u7a7a\u7684");
        }
    }

    protected void copyPathMenuItemClick(ActionEvent e) {
        String groupId = this.GetSelectFile();
        if (groupId != null && !"/".equals(groupId.trim())) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(groupId), null);
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u590d\u5236\u6210\u529f");
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "group\u662f\u7a7a\u7684");
        }
    }

    protected void deleteCurrentGroupAndDeleteWebshellMenuItemClick(ActionEvent e) {
        String groupId = this.GetSelectFile();
        if (groupId != null && !"/".equals(groupId.trim())) {
            if (0 != GOptionPane.showConfirmDialog(MainActivity.getMainActivityFrame(), String.format(EasyI18N.getI18nString("\u786e\u5b9a\u5220\u9664\u7ec4\uff1a%s \u5e76\u5220\u9664\u6240\u6709\u7ec4\u6210\u5458(\u5305\u62ec\u5b50\u7ec4)\u5417?"), groupId, "\u8b66\u544a", 0))) {
                return;
            }
            Db.removeShellByGroup(groupId);
            if (Db.removeGroup(groupId, "/") > 0) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u6210\u529f! \u5b50\u7ec4\u4e0e\u6210\u5458\u5df2\u5168\u90e8\u5220\u9664");
                this.refreshMenuItemClick(e);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u5931\u8d25 \u8bf7\u68c0\u67e5\u7ec4\u662f\u5426\u5b58\u5728 \u63a7\u5236\u53f0\u662f\u5426\u6709\u62a5\u9519!");
            }
        } else {
            Log.error("group\u662f\u7a7a\u7684");
        }
    }

    protected void deleteCurrentGroupDontDeleteWebshellMenuItemClick(ActionEvent e) {
        this.deleteGroupMenuItemClick(e);
    }

    public String getSelectedGroupName() {
        String groupId = this.GetSelectFile();
        return groupId;
    }
}

