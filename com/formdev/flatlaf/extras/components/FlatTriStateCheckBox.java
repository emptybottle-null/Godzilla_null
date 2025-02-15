/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

public class FlatTriStateCheckBox
extends JCheckBox {
    private State state;
    private boolean allowIndeterminate = true;
    private boolean altStateCycleOrder = UIManager.getBoolean("FlatTriStateCheckBox.altStateCycleOrder");

    public FlatTriStateCheckBox() {
        this((String)null);
    }

    public FlatTriStateCheckBox(String text) {
        this(text, State.INDETERMINATE);
    }

    public FlatTriStateCheckBox(String text, State initialState) {
        super(text);
        this.setModel(new JToggleButton.ToggleButtonModel(){

            @Override
            public boolean isSelected() {
                return FlatTriStateCheckBox.this.state != State.UNSELECTED;
            }

            @Override
            public void setSelected(boolean b) {
                FlatTriStateCheckBox.this.setState(FlatTriStateCheckBox.this.nextState(FlatTriStateCheckBox.this.state));
                this.fireStateChanged();
                this.fireItemStateChanged(new ItemEvent(this, 701, this, this.isSelected() ? 1 : 2));
            }
        });
        this.setState(initialState);
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        if (this.state == state) {
            return;
        }
        State oldState = this.state;
        this.state = state;
        this.putClientProperty("JButton.selectedState", state == State.INDETERMINATE ? "indeterminate" : null);
        this.firePropertyChange("state", (Object)oldState, (Object)state);
        this.repaint();
    }

    protected State nextState(State state) {
        if (!this.altStateCycleOrder) {
            switch (state) {
                default: {
                    return this.allowIndeterminate ? State.INDETERMINATE : State.SELECTED;
                }
                case INDETERMINATE: {
                    return State.SELECTED;
                }
                case SELECTED: 
            }
            return State.UNSELECTED;
        }
        switch (state) {
            default: {
                return State.SELECTED;
            }
            case INDETERMINATE: {
                return State.UNSELECTED;
            }
            case SELECTED: 
        }
        return this.allowIndeterminate ? State.INDETERMINATE : State.UNSELECTED;
    }

    public Boolean getChecked() {
        switch (this.state) {
            default: {
                return false;
            }
            case INDETERMINATE: {
                return null;
            }
            case SELECTED: 
        }
        return true;
    }

    public void setChecked(Boolean value) {
        this.setState(value == null ? State.INDETERMINATE : (value != false ? State.SELECTED : State.UNSELECTED));
    }

    @Override
    public void setSelected(boolean b) {
        this.setState(b ? State.SELECTED : State.UNSELECTED);
    }

    public boolean isIndeterminate() {
        return this.state == State.INDETERMINATE;
    }

    public void setIndeterminate(boolean indeterminate) {
        if (indeterminate) {
            this.setState(State.INDETERMINATE);
        } else if (this.state == State.INDETERMINATE) {
            this.setState(State.UNSELECTED);
        }
    }

    public boolean isAllowIndeterminate() {
        return this.allowIndeterminate;
    }

    public void setAllowIndeterminate(boolean allowIndeterminate) {
        this.allowIndeterminate = allowIndeterminate;
    }

    public boolean isAltStateCycleOrder() {
        return this.altStateCycleOrder;
    }

    public void setAltStateCycleOrder(boolean altStateCycleOrder) {
        this.altStateCycleOrder = altStateCycleOrder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.state == State.INDETERMINATE && !this.isIndeterminateStateSupported()) {
            this.paintIndeterminateState(g);
        }
    }

    protected void paintIndeterminateState(Graphics g) {
        g.setColor(Color.magenta);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }

    protected boolean isIndeterminateStateSupported() {
        LookAndFeel laf = UIManager.getLookAndFeel();
        return laf instanceof FlatLaf || laf.getClass().getName().equals("com.apple.laf.AquaLookAndFeel");
    }

    public static enum State {
        UNSELECTED,
        INDETERMINATE,
        SELECTED;

    }
}

