/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.fife.io.UnicodeReader;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Macro {
    private String name;
    private ArrayList<MacroRecord> macroRecords;
    private static final String ROOT_ELEMENT = "macro";
    private static final String MACRO_NAME = "macroName";
    private static final String ACTION = "action";
    private static final String ID = "id";
    private static final String UNTITLED_MACRO_NAME = "<Untitled>";
    private static final String FILE_ENCODING = "UTF-8";

    public Macro() {
        this(UNTITLED_MACRO_NAME);
    }

    public Macro(File file) throws IOException {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new UnicodeReader((InputStream)new FileInputStream(file), FILE_ENCODING));
            is.setEncoding(FILE_ENCODING);
            doc = db.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
            String desc = e.getMessage();
            if (desc == null) {
                desc = e.toString();
            }
            throw new IOException("Error parsing XML: " + desc);
        }
        this.macroRecords = new ArrayList();
        boolean parsedOK = this.initializeFromXMLFile(doc.getDocumentElement());
        if (!parsedOK) {
            this.name = null;
            this.macroRecords.clear();
            this.macroRecords = null;
            throw new IOException("Error parsing XML!");
        }
    }

    public Macro(String name) {
        this(name, null);
    }

    public Macro(String name, List<MacroRecord> records) {
        this.name = name;
        if (records != null) {
            this.macroRecords = new ArrayList(records.size());
            this.macroRecords.addAll(records);
        } else {
            this.macroRecords = new ArrayList(10);
        }
    }

    public void addMacroRecord(MacroRecord record) {
        if (record != null) {
            this.macroRecords.add(record);
        }
    }

    public List<MacroRecord> getMacroRecords() {
        return this.macroRecords;
    }

    public String getName() {
        return this.name;
    }

    private boolean initializeFromXMLFile(Element root) {
        NodeList childNodes = root.getChildNodes();
        int count = childNodes.getLength();
        block3: for (int i = 0; i < count; ++i) {
            Node node = childNodes.item(i);
            short type = node.getNodeType();
            switch (type) {
                case 1: {
                    String nodeName = node.getNodeName();
                    if (nodeName.equals(MACRO_NAME)) {
                        NodeList childNodes2 = node.getChildNodes();
                        this.name = UNTITLED_MACRO_NAME;
                        if (childNodes2.getLength() <= 0) continue block3;
                        node = childNodes2.item(0);
                        short type2 = node.getNodeType();
                        if (type2 != 4 && type2 != 3) {
                            return false;
                        }
                        this.name = node.getNodeValue().trim();
                        continue block3;
                    }
                    if (!nodeName.equals(ACTION)) continue block3;
                    NamedNodeMap attributes = node.getAttributes();
                    if (attributes == null || attributes.getLength() != 1) {
                        return false;
                    }
                    Node node2 = attributes.item(0);
                    MacroRecord macroRecord = new MacroRecord();
                    if (!node2.getNodeName().equals(ID)) {
                        return false;
                    }
                    macroRecord.id = node2.getNodeValue();
                    NodeList childNodes2 = node.getChildNodes();
                    int length = childNodes2.getLength();
                    if (length == 0) {
                        macroRecord.actionCommand = "";
                        this.macroRecords.add(macroRecord);
                        continue block3;
                    }
                    node = childNodes2.item(0);
                    short type2 = node.getNodeType();
                    if (type2 != 4 && type2 != 3) {
                        return false;
                    }
                    macroRecord.actionCommand = node.getNodeValue();
                    this.macroRecords.add(macroRecord);
                    continue block3;
                }
            }
        }
        return true;
    }

    public void saveToFile(File file) throws IOException {
        this.saveToFile(file.getAbsolutePath());
    }

    public void saveToFile(String fileName) throws IOException {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            DOMImplementation impl = db.getDOMImplementation();
            Document doc = impl.createDocument(null, ROOT_ELEMENT, null);
            Element rootElement = doc.getDocumentElement();
            Element nameElement = doc.createElement(MACRO_NAME);
            nameElement.appendChild(doc.createCDATASection(this.name));
            rootElement.appendChild(nameElement);
            for (MacroRecord record : this.macroRecords) {
                Element actionElement = doc.createElement(ACTION);
                actionElement.setAttribute(ID, record.id);
                if (record.actionCommand != null && record.actionCommand.length() > 0) {
                    String command = record.actionCommand;
                    for (int j = 0; j < command.length(); ++j) {
                        if (command.charAt(j) >= ' ' || j >= (command = command.substring(0, j)).length() - 1) continue;
                        command = command + command.substring(j + 1);
                    }
                    CDATASection n = doc.createCDATASection(command);
                    actionElement.appendChild(n);
                }
                rootElement.appendChild(actionElement);
            }
            StreamResult result = new StreamResult(new File(fileName));
            DOMSource source = new DOMSource(doc);
            TransformerFactory transFac = TransformerFactory.newInstance();
            Transformer transformer = transFac.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("encoding", FILE_ENCODING);
            transformer.transform(source, result);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new IOException("Error generating XML!");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    static class MacroRecord {
        String id;
        String actionCommand;

        MacroRecord() {
            this(null, null);
        }

        MacroRecord(String id, String actionCommand) {
            this.id = id;
            this.actionCommand = actionCommand;
        }
    }
}

