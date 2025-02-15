/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import org.fife.ui.rtextarea.RTextArea;

public abstract class RecordableTextAction
extends TextAction {
    private boolean isRecordable;

    public RecordableTextAction(String text) {
        this(text, null, null, null, null);
    }

    public RecordableTextAction(String text, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
        super(text);
        this.putValue("SmallIcon", icon);
        this.putValue("ShortDescription", desc);
        this.putValue("AcceleratorKey", accelerator);
        this.putValue("MnemonicKey", mnemonic);
        this.setRecordable(true);
    }

    @Override
    public final void actionPerformed(ActionEvent e) {
        JTextComponent textComponent = this.getTextComponent(e);
        if (textComponent instanceof RTextArea) {
            RTextArea textArea = (RTextArea)textComponent;
            if (RTextArea.isRecordingMacro() && this.isRecordable()) {
                int mod = e.getModifiers();
                String macroID = this.getMacroID();
                if (!"default-typed".equals(macroID) || (mod & 8) == 0 && (mod & 2) == 0 && (mod & 4) == 0) {
                    String command = e.getActionCommand();
                    RTextArea.addToCurrentMacro(macroID, command);
                }
            }
            this.actionPerformedImpl(e, textArea);
        }
    }

    public abstract void actionPerformedImpl(ActionEvent var1, RTextArea var2);

    public KeyStroke getAccelerator() {
        return (KeyStroke)this.getValue("AcceleratorKey");
    }

    public String getDescription() {
        return (String)this.getValue("ShortDescription");
    }

    public Icon getIcon() {
        return (Icon)this.getValue("SmallIcon");
    }

    public abstract String getMacroID();

    public int getMnemonic() {
        Integer i = (Integer)this.getValue("MnemonicKey");
        return i != null ? i : -1;
    }

    public String getName() {
        return (String)this.getValue("Name");
    }

    public boolean isRecordable() {
        return this.isRecordable;
    }

    public void setAccelerator(KeyStroke accelerator) {
        this.putValue("AcceleratorKey", accelerator);
    }

    public void setMnemonic(char mnemonic) {
        this.setMnemonic(Integer.valueOf(mnemonic));
    }

    public void setMnemonic(Integer mnemonic) {
        this.putValue("MnemonicKey", mnemonic);
    }

    public void setName(String name) {
        this.putValue("Name", name);
    }

    public void setProperties(ResourceBundle msg, String keyRoot) {
        this.setName(msg.getString(keyRoot + ".Name"));
        this.setMnemonic(msg.getString(keyRoot + ".Mnemonic").charAt(0));
        this.setShortDescription(msg.getString(keyRoot + ".Desc"));
    }

    public void setRecordable(boolean recordable) {
        this.isRecordable = recordable;
    }

    public void setShortDescription(String shortDesc) {
        this.putValue("ShortDescription", shortDesc);
    }
}

