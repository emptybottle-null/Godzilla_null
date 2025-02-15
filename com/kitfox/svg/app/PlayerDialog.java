/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app;

import com.kitfox.svg.app.PlayerThread;
import com.kitfox.svg.app.PlayerThreadListener;
import com.kitfox.svg.app.SVGPlayer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerDialog
extends JDialog
implements PlayerThreadListener {
    public static final long serialVersionUID = 1L;
    PlayerThread thread;
    final SVGPlayer parent;
    private JButton bn_playBack;
    private JButton bn_playFwd;
    private JButton bn_stop;
    private JButton bn_time0;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTextField text_curTime;
    private JTextField text_timeStep;

    public PlayerDialog(SVGPlayer parent) {
        super((Frame)parent, false);
        this.initComponents();
        this.parent = parent;
        this.thread = new PlayerThread();
        this.thread.addListener(this);
        this.text_timeStepActionPerformed(null);
    }

    @Override
    public void updateTime(double curTime, double timeStep, int playState) {
        if (playState == 0) {
            return;
        }
        this.text_curTime.setText("" + (float)curTime);
        this.parent.updateTime(curTime);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.bn_playBack = new JButton();
        this.bn_stop = new JButton();
        this.bn_playFwd = new JButton();
        this.jPanel2 = new JPanel();
        this.jPanel3 = new JPanel();
        this.jLabel1 = new JLabel();
        this.text_curTime = new JTextField();
        this.bn_time0 = new JButton();
        this.jPanel4 = new JPanel();
        this.jLabel2 = new JLabel();
        this.text_timeStep = new JTextField();
        this.setDefaultCloseOperation(2);
        this.setTitle("Player");
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosed(WindowEvent evt) {
                PlayerDialog.this.formWindowClosed(evt);
            }
        });
        this.bn_playBack.setText("<");
        this.bn_playBack.setToolTipText("Play backwards");
        this.bn_playBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.bn_playBackActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.bn_playBack);
        this.bn_stop.setText("||");
        this.bn_stop.setToolTipText("Stop playback");
        this.bn_stop.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.bn_stopActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.bn_stop);
        this.bn_playFwd.setText(">");
        this.bn_playFwd.setToolTipText("Play Forwards");
        this.bn_playFwd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.bn_playFwdActionPerformed(evt);
            }
        });
        this.jPanel1.add(this.bn_playFwd);
        this.getContentPane().add((Component)this.jPanel1, "North");
        this.jPanel2.setLayout(new BoxLayout(this.jPanel2, 1));
        this.jLabel1.setText("Cur Time");
        this.jPanel3.add(this.jLabel1);
        this.text_curTime.setHorizontalAlignment(2);
        this.text_curTime.setText("0");
        this.text_curTime.setPreferredSize(new Dimension(100, 21));
        this.text_curTime.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.text_curTimeActionPerformed(evt);
            }
        });
        this.text_curTime.addFocusListener(new FocusAdapter(){

            @Override
            public void focusLost(FocusEvent evt) {
                PlayerDialog.this.text_curTimeFocusLost(evt);
            }
        });
        this.jPanel3.add(this.text_curTime);
        this.bn_time0.setText("Time 0");
        this.bn_time0.setToolTipText("Reset time to first frame");
        this.bn_time0.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.bn_time0ActionPerformed(evt);
            }
        });
        this.jPanel3.add(this.bn_time0);
        this.jPanel2.add(this.jPanel3);
        this.jLabel2.setText("Frames Per Second");
        this.jPanel4.add(this.jLabel2);
        this.text_timeStep.setHorizontalAlignment(4);
        this.text_timeStep.setText("60");
        this.text_timeStep.setPreferredSize(new Dimension(100, 21));
        this.text_timeStep.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PlayerDialog.this.text_timeStepActionPerformed(evt);
            }
        });
        this.text_timeStep.addFocusListener(new FocusAdapter(){

            @Override
            public void focusLost(FocusEvent evt) {
                PlayerDialog.this.text_timeStepFocusLost(evt);
            }
        });
        this.jPanel4.add(this.text_timeStep);
        this.jPanel2.add(this.jPanel4);
        this.getContentPane().add((Component)this.jPanel2, "Center");
        this.pack();
    }

    private void bn_time0ActionPerformed(ActionEvent evt) {
        this.thread.setCurTime(0.0);
    }

    private void bn_playFwdActionPerformed(ActionEvent evt) {
        this.thread.setPlayState(1);
    }

    private void bn_stopActionPerformed(ActionEvent evt) {
        this.thread.setPlayState(0);
    }

    private void bn_playBackActionPerformed(ActionEvent evt) {
        this.thread.setPlayState(2);
    }

    private void formWindowClosed(WindowEvent evt) {
    }

    private void text_timeStepFocusLost(FocusEvent evt) {
        this.text_timeStepActionPerformed(null);
    }

    private void text_timeStepActionPerformed(ActionEvent evt) {
        try {
            int val = Integer.parseInt(this.text_timeStep.getText());
            this.thread.setTimeStep(1.0 / (double)val);
        } catch (Exception val) {
            // empty catch block
        }
        double d = this.thread.getTimeStep();
        String newStrn = "" + (int)(1.0 / d);
        if (newStrn.equals(this.text_timeStep.getText())) {
            return;
        }
        this.text_timeStep.setText(newStrn);
    }

    private void text_curTimeActionPerformed(ActionEvent evt) {
        try {
            double val = Double.parseDouble(this.text_curTime.getText());
            this.thread.setCurTime(val);
        } catch (Exception val) {
            // empty catch block
        }
        double d = this.thread.getCurTime();
        this.text_curTime.setText("" + (float)d);
        this.text_timeStepActionPerformed(null);
    }

    private void text_curTimeFocusLost(FocusEvent evt) {
        this.text_curTimeActionPerformed(null);
    }
}

