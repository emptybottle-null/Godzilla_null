/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import java.awt.BorderLayout;
import java.awt.Component;
import java.text.MessageFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.EventDetails;
import org.apache.log4j.chainsaw.MyTableModel;

class DetailPanel
extends JPanel
implements ListSelectionListener {
    private static final Logger LOG = Logger.getLogger(DetailPanel.class);
    private static final MessageFormat FORMATTER = new MessageFormat("<b>Time:</b> <code>{0,time,medium}</code>&nbsp;&nbsp;<b>Priority:</b> <code>{1}</code>&nbsp;&nbsp;<b>Thread:</b> <code>{2}</code>&nbsp;&nbsp;<b>NDC:</b> <code>{3}</code><br><b>Logger:</b> <code>{4}</code><br><b>Location:</b> <code>{5}</code><br><b>Message:</b><pre>{6}</pre><b>Throwable:</b><pre>{7}</pre>");
    private final MyTableModel mModel;
    private final JEditorPane mDetails;

    DetailPanel(JTable aTable, MyTableModel aModel) {
        this.mModel = aModel;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Details: "));
        this.mDetails = new JEditorPane();
        this.mDetails.setEditable(false);
        this.mDetails.setContentType("text/html");
        this.add((Component)new JScrollPane(this.mDetails), "Center");
        ListSelectionModel rowSM = aTable.getSelectionModel();
        rowSM.addListSelectionListener(this);
    }

    public void valueChanged(ListSelectionEvent aEvent) {
        if (aEvent.getValueIsAdjusting()) {
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel)aEvent.getSource();
        if (lsm.isSelectionEmpty()) {
            this.mDetails.setText("Nothing selected");
        } else {
            int selectedRow = lsm.getMinSelectionIndex();
            EventDetails e = this.mModel.getEventDetails(selectedRow);
            Object[] args = new Object[]{new Date(e.getTimeStamp()), e.getPriority(), this.escape(e.getThreadName()), this.escape(e.getNDC()), this.escape(e.getCategoryName()), this.escape(e.getLocationDetails()), this.escape(e.getMessage()), this.escape(DetailPanel.getThrowableStrRep(e))};
            this.mDetails.setText(FORMATTER.format(args));
            this.mDetails.setCaretPosition(0);
        }
    }

    private static String getThrowableStrRep(EventDetails aEvent) {
        String[] strs = aEvent.getThrowableStrRep();
        if (strs == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; ++i) {
            sb.append(strs[i]).append("\n");
        }
        return sb.toString();
    }

    private String escape(String aStr) {
        if (aStr == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        block6: for (int i = 0; i < aStr.length(); ++i) {
            char c = aStr.charAt(i);
            switch (c) {
                case '<': {
                    buf.append("&lt;");
                    continue block6;
                }
                case '>': {
                    buf.append("&gt;");
                    continue block6;
                }
                case '\"': {
                    buf.append("&quot;");
                    continue block6;
                }
                case '&': {
                    buf.append("&amp;");
                    continue block6;
                }
                default: {
                    buf.append(c);
                }
            }
        }
        return buf.toString();
    }
}

