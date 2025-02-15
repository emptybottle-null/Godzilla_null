/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.EasyI18N;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.functions;

public class ImageShowDialog
extends JDialog {
    private JPanel panel = new JPanel(new BorderLayout());
    private JLabel imageLabel;

    private ImageShowDialog(Frame owner, ImageIcon imageIcon, String title, int width, int height) {
        super(owner, title, true);
        this.imageLabel = new JLabel(imageIcon);
        this.panel.add(this.imageLabel);
        this.add(this.panel);
        functions.setWindowSize(this, width, height);
        this.setLocationRelativeTo(owner);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon, String title, int width, int height) {
        width += 50;
        height += 50;
        if (title == null || title.trim().length() < 1) {
            title = String.format("image info Width:%s Height:%s", imageIcon.getIconWidth(), imageIcon.getIconHeight());
        }
        ImageShowDialog imageShowDialog = new ImageShowDialog(owner, imageIcon, title, width, height);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon, String title) {
        ImageShowDialog.showImageDiaolog(owner, imageIcon, title, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    public static void showImageDiaolog(ImageIcon imageIcon, String title) {
        ImageShowDialog.showImageDiaolog(null, imageIcon, title);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon) {
        ImageShowDialog.showImageDiaolog(owner, imageIcon, null);
    }

    public static void showImageDiaolog(ImageIcon imageIcon) {
        ImageShowDialog.showImageDiaolog(null, imageIcon);
    }
}

