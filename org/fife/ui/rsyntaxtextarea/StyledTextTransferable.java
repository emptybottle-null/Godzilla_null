/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import org.fife.ui.rsyntaxtextarea.RtfToText;

class StyledTextTransferable
implements Transferable {
    private String html;
    private byte[] rtfBytes;
    private static final DataFlavor[] FLAVORS = new DataFlavor[]{DataFlavor.fragmentHtmlFlavor, new DataFlavor("text/rtf", "RTF"), DataFlavor.stringFlavor, DataFlavor.plainTextFlavor};

    StyledTextTransferable(String html, byte[] rtfBytes) {
        this.html = html;
        this.rtfBytes = rtfBytes;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(FLAVORS[0])) {
            return this.html;
        }
        if (flavor.equals(FLAVORS[1])) {
            return new ByteArrayInputStream(this.rtfBytes == null ? new byte[]{} : this.rtfBytes);
        }
        if (flavor.equals(FLAVORS[2])) {
            return this.rtfBytes == null ? "" : RtfToText.getPlainText(this.rtfBytes);
        }
        if (flavor.equals(FLAVORS[3])) {
            String text = "";
            if (this.rtfBytes != null) {
                text = RtfToText.getPlainText(this.rtfBytes);
            }
            return new StringReader(text);
        }
        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return (DataFlavor[])FLAVORS.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (DataFlavor flavor1 : FLAVORS) {
            if (!flavor.equals(flavor1)) continue;
            return true;
        }
        return false;
    }
}

