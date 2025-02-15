/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.MyTableModel;
import org.apache.log4j.chainsaw.XMLFileHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class LoadXMLAction
extends AbstractAction {
    private static final Logger LOG = Logger.getLogger(LoadXMLAction.class);
    private final JFrame mParent;
    private final JFileChooser mChooser = new JFileChooser();
    private final XMLReader mParser;
    private final XMLFileHandler mHandler;

    LoadXMLAction(JFrame aParent, MyTableModel aModel) throws SAXException, ParserConfigurationException {
        this.mChooser.setMultiSelectionEnabled(false);
        this.mChooser.setFileSelectionMode(0);
        this.mParent = aParent;
        this.mHandler = new XMLFileHandler(aModel);
        this.mParser = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        this.mParser.setContentHandler(this.mHandler);
    }

    public void actionPerformed(ActionEvent aIgnore) {
        LOG.info("load file called");
        if (this.mChooser.showOpenDialog(this.mParent) == 0) {
            LOG.info("Need to load a file");
            File chosen = this.mChooser.getSelectedFile();
            LOG.info("loading the contents of " + chosen.getAbsolutePath());
            try {
                int num = this.loadFile(chosen.getAbsolutePath());
                JOptionPane.showMessageDialog(this.mParent, "Loaded " + num + " events.", "CHAINSAW", 1);
            } catch (Exception e) {
                LOG.warn("caught an exception loading the file", e);
                JOptionPane.showMessageDialog(this.mParent, "Error parsing file - " + e.getMessage(), "CHAINSAW", 0);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int loadFile(String aFile) throws SAXException, IOException {
        XMLReader xMLReader = this.mParser;
        synchronized (xMLReader) {
            StringBuffer buf = new StringBuffer();
            buf.append("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
            buf.append("<!DOCTYPE log4j:eventSet ");
            buf.append("[<!ENTITY data SYSTEM \"file:///");
            buf.append(aFile);
            buf.append("\">]>\n");
            buf.append("<log4j:eventSet xmlns:log4j=\"Claira\">\n");
            buf.append("&data;\n");
            buf.append("</log4j:eventSet>\n");
            InputSource is = new InputSource(new StringReader(buf.toString()));
            this.mParser.parse(is);
            return this.mHandler.getNumEvents();
        }
    }
}

