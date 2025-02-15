/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.im.InputContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import org.fife.ui.rtextarea.ClipboardHistory;
import org.fife.ui.rtextarea.RTextArea;

public class RTATextTransferHandler
extends TransferHandler {
    private JTextComponent exportComp;
    private boolean shouldRemove;
    private int p0;
    private int p1;
    private boolean withinSameComponent;

    protected DataFlavor getImportFlavor(DataFlavor[] flavors, JTextComponent c) {
        DataFlavor refFlavor = null;
        DataFlavor stringFlavor = null;
        for (DataFlavor flavor : flavors) {
            String mime = flavor.getMimeType();
            if (mime.startsWith("text/plain")) {
                return flavor;
            }
            if (refFlavor == null && mime.startsWith("application/x-java-jvm-local-objectref") && flavor.getRepresentationClass() == String.class) {
                refFlavor = flavor;
                continue;
            }
            if (stringFlavor != null || !flavor.equals(DataFlavor.stringFlavor)) continue;
            stringFlavor = flavor;
        }
        if (refFlavor != null) {
            return refFlavor;
        }
        if (stringFlavor != null) {
            return stringFlavor;
        }
        return null;
    }

    protected void handleReaderImport(Reader in, JTextComponent c) throws IOException {
        int nch;
        char[] buff = new char[1024];
        boolean lastWasCR = false;
        StringBuilder sbuff = null;
        while ((nch = in.read(buff, 0, buff.length)) != -1) {
            if (sbuff == null) {
                sbuff = new StringBuilder(nch);
            }
            int last = 0;
            block5: for (int counter = 0; counter < nch; ++counter) {
                switch (buff[counter]) {
                    case '\r': {
                        if (lastWasCR) {
                            if (counter == 0) {
                                sbuff.append('\n');
                                continue block5;
                            }
                            buff[counter - 1] = 10;
                            continue block5;
                        }
                        lastWasCR = true;
                        continue block5;
                    }
                    case '\n': {
                        if (!lastWasCR) continue block5;
                        if (counter > last + 1) {
                            sbuff.append(buff, last, counter - last - 1);
                        }
                        lastWasCR = false;
                        last = counter;
                        continue block5;
                    }
                    default: {
                        if (!lastWasCR) continue block5;
                        if (counter == 0) {
                            sbuff.append('\n');
                        } else {
                            buff[counter - 1] = 10;
                        }
                        lastWasCR = false;
                    }
                }
            }
            if (last >= nch) continue;
            if (lastWasCR) {
                if (last >= nch - 1) continue;
                sbuff.append(buff, last, nch - last - 1);
                continue;
            }
            sbuff.append(buff, last, nch - last);
        }
        if (this.withinSameComponent) {
            ((RTextArea)c).beginAtomicEdit();
        }
        if (lastWasCR) {
            sbuff.append('\n');
        }
        c.replaceSelection(sbuff != null ? sbuff.toString() : "");
    }

    @Override
    public int getSourceActions(JComponent c) {
        if (((JTextComponent)c).isEditable()) {
            return 3;
        }
        return 1;
    }

    @Override
    protected Transferable createTransferable(JComponent comp) {
        this.exportComp = (JTextComponent)comp;
        this.shouldRemove = true;
        this.p0 = this.exportComp.getSelectionStart();
        this.p1 = this.exportComp.getSelectionEnd();
        return this.p0 != this.p1 ? new TextTransferable(this.exportComp, this.p0, this.p1) : null;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (this.shouldRemove && action == 2) {
            TextTransferable t = (TextTransferable)data;
            t.removeText();
            if (this.withinSameComponent) {
                ((RTextArea)source).endAtomicEdit();
                this.withinSameComponent = false;
            }
        }
        this.exportComp = null;
        if (data instanceof TextTransferable) {
            ClipboardHistory.get().add(((TextTransferable)data).getPlainData());
        }
    }

    @Override
    public boolean importData(JComponent comp, Transferable t) {
        JTextComponent c = (JTextComponent)comp;
        boolean bl = this.withinSameComponent = c == this.exportComp;
        if (this.withinSameComponent && c.getCaretPosition() >= this.p0 && c.getCaretPosition() <= this.p1) {
            this.shouldRemove = false;
            return true;
        }
        boolean imported = false;
        DataFlavor importFlavor = this.getImportFlavor(t.getTransferDataFlavors(), c);
        if (importFlavor != null) {
            try {
                InputContext ic = c.getInputContext();
                if (ic != null) {
                    ic.endComposition();
                }
                Reader r = importFlavor.getReaderForText(t);
                this.handleReaderImport(r, c);
                imported = true;
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }
        return imported;
    }

    @Override
    public boolean canImport(JComponent comp, DataFlavor[] flavors) {
        JTextComponent c = (JTextComponent)comp;
        if (!c.isEditable() || !c.isEnabled()) {
            return false;
        }
        return this.getImportFlavor(flavors, c) != null;
    }

    static class TextTransferable
    implements Transferable {
        private Position p0;
        private Position p1;
        private JTextComponent c;
        protected String plainData;
        private static DataFlavor[] stringFlavors;
        private static DataFlavor[] plainFlavors;

        TextTransferable(JTextComponent c, int start, int end) {
            this.c = c;
            Document doc = c.getDocument();
            try {
                this.p0 = doc.createPosition(start);
                this.p1 = doc.createPosition(end);
                this.plainData = c.getSelectedText();
            } catch (BadLocationException badLocationException) {
                // empty catch block
            }
        }

        protected String getPlainData() {
            return this.plainData;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (this.isPlainFlavor(flavor)) {
                String data = this.getPlainData();
                String string = data = data == null ? "" : data;
                if (String.class.equals(flavor.getRepresentationClass())) {
                    return data;
                }
                if (Reader.class.equals(flavor.getRepresentationClass())) {
                    return new StringReader(data);
                }
                if (InputStream.class.equals(flavor.getRepresentationClass())) {
                    return new StringBufferInputStream(data);
                }
            } else if (this.isStringFlavor(flavor)) {
                String data = this.getPlainData();
                data = data == null ? "" : data;
                return data;
            }
            throw new UnsupportedFlavorException(flavor);
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            int plainCount = this.isPlainSupported() ? plainFlavors.length : 0;
            int stringCount = this.isPlainSupported() ? stringFlavors.length : 0;
            int totalCount = plainCount + stringCount;
            DataFlavor[] flavors = new DataFlavor[totalCount];
            int pos = 0;
            if (plainCount > 0) {
                System.arraycopy(plainFlavors, 0, flavors, pos, plainCount);
                pos += plainCount;
            }
            if (stringCount > 0) {
                System.arraycopy(stringFlavors, 0, flavors, pos, stringCount);
            }
            return flavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            DataFlavor[] flavors;
            for (DataFlavor dataFlavor : flavors = this.getTransferDataFlavors()) {
                if (!dataFlavor.equals(flavor)) continue;
                return true;
            }
            return false;
        }

        protected boolean isPlainFlavor(DataFlavor flavor) {
            DataFlavor[] flavors;
            for (DataFlavor dataFlavor : flavors = plainFlavors) {
                if (!dataFlavor.equals(flavor)) continue;
                return true;
            }
            return false;
        }

        protected boolean isPlainSupported() {
            return this.plainData != null;
        }

        protected boolean isStringFlavor(DataFlavor flavor) {
            DataFlavor[] flavors;
            for (DataFlavor dataFlavor : flavors = stringFlavors) {
                if (!dataFlavor.equals(flavor)) continue;
                return true;
            }
            return false;
        }

        void removeText() {
            if (this.p0 != null && this.p1 != null && this.p0.getOffset() != this.p1.getOffset()) {
                try {
                    Document doc = this.c.getDocument();
                    doc.remove(this.p0.getOffset(), this.p1.getOffset() - this.p0.getOffset());
                } catch (BadLocationException badLocationException) {
                    // empty catch block
                }
            }
        }

        static {
            try {
                plainFlavors = new DataFlavor[3];
                TextTransferable.plainFlavors[0] = new DataFlavor("text/plain;class=java.lang.String");
                TextTransferable.plainFlavors[1] = new DataFlavor("text/plain;class=java.io.Reader");
                TextTransferable.plainFlavors[2] = new DataFlavor("text/plain;charset=unicode;class=java.io.InputStream");
                stringFlavors = new DataFlavor[2];
                TextTransferable.stringFlavors[0] = new DataFlavor("application/x-java-jvm-local-objectref;class=java.lang.String");
                TextTransferable.stringFlavors[1] = DataFlavor.stringFlavor;
            } catch (ClassNotFoundException cle) {
                System.err.println("Error initializing org.fife.ui.RTATextTransferHandler");
            }
        }
    }
}

