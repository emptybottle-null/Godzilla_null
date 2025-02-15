/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.util.ResourceBundle;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RecordableTextAction;

public class RUndoManager
extends UndoManager {
    private RCompoundEdit compoundEdit;
    private RTextArea textArea;
    private int lastOffset;
    private String cantUndoText;
    private String cantRedoText;
    private int internalAtomicEditDepth;
    private static final String MSG = "org.fife.ui.rtextarea.RTextArea";

    public RUndoManager(RTextArea textArea) {
        this.textArea = textArea;
        ResourceBundle msg = ResourceBundle.getBundle(MSG);
        this.cantUndoText = msg.getString("Action.CantUndo.Name");
        this.cantRedoText = msg.getString("Action.CantRedo.Name");
    }

    public void beginInternalAtomicEdit() {
        if (++this.internalAtomicEditDepth == 1) {
            if (this.compoundEdit != null) {
                this.compoundEdit.end();
            }
            this.compoundEdit = new RCompoundEdit();
        }
    }

    public void endInternalAtomicEdit() {
        if (this.internalAtomicEditDepth > 0 && --this.internalAtomicEditDepth == 0) {
            this.addEdit(this.compoundEdit);
            this.compoundEdit.end();
            this.compoundEdit = null;
            this.updateActions();
        }
    }

    public String getCantRedoText() {
        return this.cantRedoText;
    }

    public String getCantUndoText() {
        return this.cantUndoText;
    }

    @Override
    public void redo() {
        super.redo();
        this.updateActions();
    }

    private RCompoundEdit startCompoundEdit(UndoableEdit edit) {
        this.lastOffset = this.textArea.getCaretPosition();
        this.compoundEdit = new RCompoundEdit();
        this.compoundEdit.addEdit(edit);
        this.addEdit(this.compoundEdit);
        return this.compoundEdit;
    }

    @Override
    public void undo() {
        super.undo();
        this.updateActions();
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        if (this.compoundEdit == null) {
            this.compoundEdit = this.startCompoundEdit(e.getEdit());
            this.updateActions();
            return;
        }
        if (this.internalAtomicEditDepth > 0) {
            this.compoundEdit.addEdit(e.getEdit());
            return;
        }
        int diff = this.textArea.getCaretPosition() - this.lastOffset;
        if (Math.abs(diff) <= 1) {
            this.compoundEdit.addEdit(e.getEdit());
            this.lastOffset += diff;
            return;
        }
        this.compoundEdit.end();
        this.compoundEdit = this.startCompoundEdit(e.getEdit());
    }

    public void updateActions() {
        String text;
        RecordableTextAction a = RTextArea.getAction(6);
        if (this.canUndo()) {
            a.setEnabled(true);
            text = this.getUndoPresentationName();
            a.putValue("Name", text);
            a.putValue("ShortDescription", text);
        } else if (a.isEnabled()) {
            a.setEnabled(false);
            text = this.cantUndoText;
            a.putValue("Name", text);
            a.putValue("ShortDescription", text);
        }
        a = RTextArea.getAction(4);
        if (this.canRedo()) {
            a.setEnabled(true);
            text = this.getRedoPresentationName();
            a.putValue("Name", text);
            a.putValue("ShortDescription", text);
        } else if (a.isEnabled()) {
            a.setEnabled(false);
            text = this.cantRedoText;
            a.putValue("Name", text);
            a.putValue("ShortDescription", text);
        }
    }

    class RCompoundEdit
    extends CompoundEdit {
        RCompoundEdit() {
        }

        @Override
        public String getUndoPresentationName() {
            return UIManager.getString("AbstractUndoableEdit.undoText");
        }

        @Override
        public String getRedoPresentationName() {
            return UIManager.getString("AbstractUndoableEdit.redoText");
        }

        @Override
        public boolean isInProgress() {
            return false;
        }

        @Override
        public void undo() {
            if (RUndoManager.this.compoundEdit != null) {
                RUndoManager.this.compoundEdit.end();
            }
            super.undo();
            RUndoManager.this.compoundEdit = null;
        }
    }
}

