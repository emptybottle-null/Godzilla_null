/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultHighlighter;
import org.fife.rsta.ac.demo.AboutDialog;
import org.fife.rsta.ac.demo.DemoApp;
import org.fife.rsta.ac.demo.DemoRootPane;
import org.fife.rsta.ac.demo.ExtensionFileFilter;

interface Actions {

    public static class ToggleLayeredHighlightsAction
    extends AbstractAction {
        private DemoRootPane demo;

        public ToggleLayeredHighlightsAction(DemoRootPane demo) {
            this.demo = demo;
            this.putValue("Name", "Layered Selection Highlights");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultHighlighter h;
            h.setDrawsLayeredHighlights(!(h = (DefaultHighlighter)this.demo.getTextArea().getHighlighter()).getDrawsLayeredHighlights());
        }
    }

    public static class StyleAction
    extends AbstractAction {
        private DemoRootPane demo;
        private String res;
        private String style;

        public StyleAction(DemoRootPane demo, String name, String res, String style) {
            this.putValue("Name", name);
            this.demo = demo;
            this.res = res;
            this.style = style;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.demo.setText(this.res, this.style);
        }
    }

    public static class LookAndFeelAction
    extends AbstractAction {
        private UIManager.LookAndFeelInfo info;
        private DemoRootPane demo;

        public LookAndFeelAction(DemoRootPane demo, UIManager.LookAndFeelInfo info) {
            this.putValue("Name", info.getName());
            this.demo = demo;
            this.info = info;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(this.info.getClassName());
                SwingUtilities.updateComponentTreeUI(this.demo);
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class OpenAction
    extends AbstractAction {
        private static final long serialVersionUID = 1L;
        private DemoRootPane demo;
        private JFileChooser chooser;

        public OpenAction(DemoRootPane demo) {
            this.demo = demo;
            this.putValue("Name", "Open...");
            this.putValue("MnemonicKey", 79);
            int mods = demo.getToolkit().getMenuShortcutKeyMask();
            KeyStroke ks = KeyStroke.getKeyStroke(79, mods);
            this.putValue("AcceleratorKey", ks);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int rc;
            if (this.chooser == null) {
                this.chooser = new JFileChooser();
                this.chooser.setFileFilter(new ExtensionFileFilter("Java Source Files", "java"));
            }
            if ((rc = this.chooser.showOpenDialog(this.demo)) == 0) {
                this.demo.openFile(this.chooser.getSelectedFile());
            }
        }
    }

    public static class ExitAction
    extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ExitAction() {
            this.putValue("Name", "Exit");
            this.putValue("MnemonicKey", 120);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static class AboutAction
    extends AbstractAction {
        private DemoRootPane demo;

        public AboutAction(DemoRootPane demo) {
            this.demo = demo;
            this.putValue("Name", "About RSTALanguageSupport...");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDialog ad = new AboutDialog((DemoApp)SwingUtilities.getWindowAncestor(this.demo));
            ad.setLocationRelativeTo(this.demo);
            ad.setVisible(true);
        }
    }
}

