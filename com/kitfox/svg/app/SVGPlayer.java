/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGDisplayPanel;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.app.PlayerDialog;
import com.kitfox.svg.app.VersionDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.AccessControlException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;

public class SVGPlayer
extends JFrame {
    public static final long serialVersionUID = 1L;
    SVGDisplayPanel svgDisplayPanel = new SVGDisplayPanel();
    final PlayerDialog playerDialog;
    SVGUniverse universe;
    final JFileChooser fileChooser;
    private JCheckBoxMenuItem CheckBoxMenuItem_anonInputStream;
    private JCheckBoxMenuItem cmCheck_verbose;
    private JMenuItem cm_800x600;
    private JMenuItem cm_about;
    private JMenuItem cm_loadFile;
    private JMenuItem cm_loadUrl;
    private JMenuItem cm_player;
    private JMenuBar jMenuBar1;
    private JSeparator jSeparator2;
    private JMenu menu_file;
    private JMenu menu_help;
    private JMenu menu_window;
    private JScrollPane scrollPane_svgArea;

    public SVGPlayer() {
        JFileChooser fc = null;
        try {
            fc = new JFileChooser();
            fc.setFileFilter(new FileFilter(){
                final Matcher matchLevelFile = Pattern.compile(".*\\.svg[z]?").matcher("");

                @Override
                public boolean accept(File file) {
                    if (file.isDirectory()) {
                        return true;
                    }
                    this.matchLevelFile.reset(file.getName());
                    return this.matchLevelFile.matches();
                }

                @Override
                public String getDescription() {
                    return "SVG file (*.svg, *.svgz)";
                }
            });
        } catch (AccessControlException accessControlException) {
            // empty catch block
        }
        this.fileChooser = fc;
        this.initComponents();
        this.setSize(800, 600);
        this.svgDisplayPanel.setBgColor(Color.white);
        this.svgDisplayPanel.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent evt) {
                SVGDiagram diagram = SVGPlayer.this.svgDisplayPanel.getDiagram();
                if (diagram == null) {
                    return;
                }
                System.out.println("Picking at cursor (" + evt.getX() + ", " + evt.getY() + ")");
                try {
                    List<List<SVGElement>> paths = diagram.pick(new Point2D.Float(evt.getX(), evt.getY()), null);
                    for (int i = 0; i < paths.size(); ++i) {
                        System.out.println(SVGPlayer.this.pathToString(paths.get(i)));
                    }
                } catch (SVGException ex) {
                    Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not pick", ex);
                }
            }
        });
        this.svgDisplayPanel.setPreferredSize(this.getSize());
        this.scrollPane_svgArea.setViewportView(this.svgDisplayPanel);
        this.playerDialog = new PlayerDialog(this);
    }

    private String pathToString(List<SVGElement> path) {
        if (path.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(path.get(0));
        for (int i = 1; i < path.size(); ++i) {
            sb.append("/");
            sb.append(path.get(i).getId());
        }
        return sb.toString();
    }

    public void updateTime(double curTime) {
        try {
            if (this.universe != null) {
                this.universe.setCurTime(curTime);
                this.universe.updateTime();
                this.repaint();
            }
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }

    private void loadURL(URL url) {
        boolean verbose = this.cmCheck_verbose.isSelected();
        this.universe = new SVGUniverse();
        this.universe.setVerbose(verbose);
        SVGDiagram diagram = null;
        if (!this.CheckBoxMenuItem_anonInputStream.isSelected()) {
            URI uri = this.universe.loadSVG(url);
            if (verbose) {
                System.err.println(uri.toString());
            }
            diagram = this.universe.getDiagram(uri);
        } else {
            try {
                InputStream is = url.openStream();
                URI uri = this.universe.loadSVG(is, "defaultName");
                if (verbose) {
                    System.err.println(uri.toString());
                }
                diagram = this.universe.getDiagram(uri);
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
            }
        }
        this.svgDisplayPanel.setDiagram(diagram);
        this.repaint();
    }

    private void initComponents() {
        this.scrollPane_svgArea = new JScrollPane();
        this.jMenuBar1 = new JMenuBar();
        this.menu_file = new JMenu();
        this.cm_loadFile = new JMenuItem();
        this.cm_loadUrl = new JMenuItem();
        this.menu_window = new JMenu();
        this.cm_player = new JMenuItem();
        this.jSeparator2 = new JSeparator();
        this.cm_800x600 = new JMenuItem();
        this.CheckBoxMenuItem_anonInputStream = new JCheckBoxMenuItem();
        this.cmCheck_verbose = new JCheckBoxMenuItem();
        this.menu_help = new JMenu();
        this.cm_about = new JMenuItem();
        this.setTitle("SVG Player - Salamander Project");
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                SVGPlayer.this.exitForm(evt);
            }
        });
        this.getContentPane().add((Component)this.scrollPane_svgArea, "Center");
        this.menu_file.setMnemonic('f');
        this.menu_file.setText("File");
        this.cm_loadFile.setMnemonic('l');
        this.cm_loadFile.setText("Load File...");
        this.cm_loadFile.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cm_loadFileActionPerformed(evt);
            }
        });
        this.menu_file.add(this.cm_loadFile);
        this.cm_loadUrl.setText("Load URL...");
        this.cm_loadUrl.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cm_loadUrlActionPerformed(evt);
            }
        });
        this.menu_file.add(this.cm_loadUrl);
        this.jMenuBar1.add(this.menu_file);
        this.menu_window.setText("Window");
        this.cm_player.setText("Player");
        this.cm_player.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cm_playerActionPerformed(evt);
            }
        });
        this.menu_window.add(this.cm_player);
        this.menu_window.add(this.jSeparator2);
        this.cm_800x600.setText("800 x 600");
        this.cm_800x600.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cm_800x600ActionPerformed(evt);
            }
        });
        this.menu_window.add(this.cm_800x600);
        this.CheckBoxMenuItem_anonInputStream.setText("Anonymous Input Stream");
        this.menu_window.add(this.CheckBoxMenuItem_anonInputStream);
        this.cmCheck_verbose.setText("Verbose");
        this.cmCheck_verbose.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cmCheck_verboseActionPerformed(evt);
            }
        });
        this.menu_window.add(this.cmCheck_verbose);
        this.jMenuBar1.add(this.menu_window);
        this.menu_help.setText("Help");
        this.cm_about.setText("About...");
        this.cm_about.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                SVGPlayer.this.cm_aboutActionPerformed(evt);
            }
        });
        this.menu_help.add(this.cm_about);
        this.jMenuBar1.add(this.menu_help);
        this.setJMenuBar(this.jMenuBar1);
        this.pack();
    }

    private void cm_loadUrlActionPerformed(ActionEvent evt) {
        String urlStrn = JOptionPane.showInputDialog(this, (Object)"Enter URL of SVG file");
        if (urlStrn == null) {
            return;
        }
        try {
            URL url = new URL(URLEncoder.encode(urlStrn, "UTF-8"));
            this.loadURL(url);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }

    private void cmCheck_verboseActionPerformed(ActionEvent evt) {
    }

    private void cm_playerActionPerformed(ActionEvent evt) {
        this.playerDialog.setVisible(true);
    }

    private void cm_aboutActionPerformed(ActionEvent evt) {
        VersionDialog dia = new VersionDialog((Frame)this, true, this.cmCheck_verbose.isSelected());
        dia.setVisible(true);
    }

    private void cm_800x600ActionPerformed(ActionEvent evt) {
        this.setSize(800, 600);
    }

    private void cm_loadFileActionPerformed(ActionEvent evt) {
        boolean verbose = this.cmCheck_verbose.isSelected();
        try {
            int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == 0) {
                File chosenFile = this.fileChooser.getSelectedFile();
                URL url = chosenFile.toURI().toURL();
                this.loadURL(url);
            }
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new SVGPlayer().setVisible(true);
    }

    public void updateTime(double curTime, double timeStep, int playState) {
    }
}

