/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app;

import com.kitfox.svg.app.SVGPlayer;
import com.kitfox.svg.app.SVGViewer;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame
extends JFrame {
    public static final long serialVersionUID = 1L;
    private JButton bn_quit;
    private JButton bn_svgViewer;
    private JButton bn_svgViewer1;
    private JPanel jPanel1;
    private JPanel jPanel2;

    public MainFrame() {
        this.initComponents();
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.bn_svgViewer = new JButton();
        this.bn_svgViewer1 = new JButton();
        this.jPanel2 = new JPanel();
        this.bn_quit = new JButton();
        this.setTitle("SVG Salamander - Application Launcher");
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                MainFrame.this.exitForm(evt);
            }
        });
        this.jPanel1.setLayout(new BoxLayout(this.jPanel1, 1));
        this.bn_svgViewer.setText("SVG Viewer (No animation)");
        this.bn_svgViewer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MainFrame.this.bn_svgViewerActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.bn_svgViewer);
        this.bn_svgViewer1.setText("SVG Player (Animation)");
        this.bn_svgViewer1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MainFrame.this.bn_svgViewer1ActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.bn_svgViewer1);
        this.getContentPane().add((Component)this.jPanel1, "Center");
        this.bn_quit.setText("Quit");
        this.bn_quit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                MainFrame.this.bn_quitActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.bn_quit);
        this.getContentPane().add((Component)this.jPanel2, "South");
        this.pack();
    }

    private void bn_svgViewer1ActionPerformed(ActionEvent evt) {
        SVGPlayer.main(null);
        this.close();
    }

    private void bn_svgViewerActionPerformed(ActionEvent evt) {
        SVGViewer.main(null);
        this.close();
    }

    private void bn_quitActionPerformed(ActionEvent evt) {
        this.exitForm(null);
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    private void close() {
        this.setVisible(false);
        this.dispose();
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}

