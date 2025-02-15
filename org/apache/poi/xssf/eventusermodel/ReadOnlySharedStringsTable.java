/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ReadOnlySharedStringsTable
extends DefaultHandler
implements SharedStrings {
    protected final boolean includePhoneticRuns;
    protected int count;
    protected int uniqueCount;
    private List<String> strings;
    private StringBuilder characters;
    private boolean tIsOpen;
    private boolean inRPh;

    public ReadOnlySharedStringsTable(OPCPackage pkg) throws IOException, SAXException {
        this(pkg, true);
    }

    public ReadOnlySharedStringsTable(OPCPackage pkg, boolean includePhoneticRuns) throws IOException, SAXException {
        this.includePhoneticRuns = includePhoneticRuns;
        ArrayList<PackagePart> parts = pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
        if (parts.size() > 0) {
            PackagePart sstPart = parts.get(0);
            this.readFrom(sstPart.getInputStream());
        }
    }

    public ReadOnlySharedStringsTable(PackagePart part) throws IOException, SAXException {
        this(part, true);
    }

    public ReadOnlySharedStringsTable(PackagePart part, boolean includePhoneticRuns) throws IOException, SAXException {
        this.includePhoneticRuns = includePhoneticRuns;
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException, SAXException {
        PushbackInputStream pis = new PushbackInputStream(is, 1);
        int emptyTest = pis.read();
        if (emptyTest > -1) {
            pis.unread(emptyTest);
            InputSource sheetSource = new InputSource(pis);
            try {
                XMLReader sheetParser = XMLHelper.newXMLReader();
                sheetParser.setContentHandler(this);
                sheetParser.parse(sheetSource);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
            }
        }
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int getUniqueCount() {
        return this.uniqueCount;
    }

    @Removal(version="4.2")
    @Deprecated
    public String getEntryAt(int idx) {
        return this.strings.get(idx);
    }

    @Removal(version="4.2")
    @Deprecated
    public List<String> getItems() {
        return this.strings;
    }

    @Override
    public RichTextString getItemAt(int idx) {
        return new XSSFRichTextString(this.getEntryAt(idx));
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if (uri != null && !uri.equals("http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
            return;
        }
        if ("sst".equals(localName)) {
            String uniqueCount;
            String count = attributes.getValue("count");
            if (count != null) {
                this.count = Integer.parseInt(count);
            }
            if ((uniqueCount = attributes.getValue("uniqueCount")) != null) {
                this.uniqueCount = Integer.parseInt(uniqueCount);
            }
            this.strings = new ArrayList<String>(this.uniqueCount);
            this.characters = new StringBuilder(64);
        } else if ("si".equals(localName)) {
            this.characters.setLength(0);
        } else if ("t".equals(localName)) {
            this.tIsOpen = true;
        } else if ("rPh".equals(localName)) {
            this.inRPh = true;
            if (this.includePhoneticRuns && this.characters.length() > 0) {
                this.characters.append(" ");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        if (uri != null && !uri.equals("http://schemas.openxmlformats.org/spreadsheetml/2006/main")) {
            return;
        }
        if ("si".equals(localName)) {
            this.strings.add(this.characters.toString());
        } else if ("t".equals(localName)) {
            this.tIsOpen = false;
        } else if ("rPh".equals(localName)) {
            this.inRPh = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.tIsOpen) {
            if (this.inRPh && this.includePhoneticRuns) {
                this.characters.append(ch, start, length);
            } else if (!this.inRPh) {
                this.characters.append(ch, start, length);
            }
        }
    }
}

