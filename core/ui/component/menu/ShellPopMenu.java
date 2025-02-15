/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.menu;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ShellPopMenu {
    private final JPopupMenu shellmenu;
    private final JMenuItem copy;
    private final JMenuItem paste;
    private final JPanel p;
    private final JTextPane c;
    Clipboard clipboard;
    Transferable contents;
    Transferable tText;
    DataFlavor flavor;
    private final Document shell_doc;

    public ShellPopMenu(JPanel panel, JTextPane console) {
        this.p = panel;
        this.c = console;
        this.shell_doc = console.getDocument();
        this.shellmenu = new JPopupMenu();
        this.copy = new JMenuItem("\u590d\u5236");
        this.paste = new JMenuItem("\u7c98\u8d34");
        this.shellmenu.add(this.copy);
        this.shellmenu.add(this.paste);
        this.p.add(this.shellmenu);
        MenuAction action = new MenuAction();
        this.copy.addActionListener(action);
        this.paste.addActionListener(action);
        console.addMouseListener(new MouseL());
    }

    class MouseL
    implements MouseListener {
        MouseL() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.isMetaDown()) {
                ShellPopMenu.this.shellmenu.show(ShellPopMenu.this.c, e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class MenuAction
    implements ActionListener {
        MenuAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ShellPopMenu.this.copy) {
                String k = ShellPopMenu.this.c.getSelectedText();
                ShellPopMenu.this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                ShellPopMenu.this.tText = new StringSelection(k);
                ShellPopMenu.this.clipboard.setContents(ShellPopMenu.this.tText, null);
            } else if (e.getSource() == ShellPopMenu.this.paste) {
                ShellPopMenu.this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable clipT = ShellPopMenu.this.clipboard.getContents(null);
                if (clipT != null && clipT.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        String pastestr = (String)clipT.getTransferData(DataFlavor.stringFlavor);
                        try {
                            ShellPopMenu.this.shell_doc.insertString(ShellPopMenu.this.shell_doc.getLength(), pastestr, null);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}

