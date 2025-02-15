/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

public class SharedStringsTable
extends POIXMLDocumentPart
implements SharedStrings,
Closeable {
    private final List<CTRst> strings = new ArrayList<CTRst>();
    private final Map<String, Integer> stmap = new HashMap<String, Integer>();
    protected int count;
    protected int uniqueCount;
    private SstDocument _sstDoc;
    private static final XmlOptions options = new XmlOptions();

    public SharedStringsTable() {
        this._sstDoc = SstDocument.Factory.newInstance();
        this._sstDoc.addNewSst();
    }

    public SharedStringsTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            int cnt = 0;
            this._sstDoc = SstDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTSst sst = this._sstDoc.getSst();
            this.count = (int)sst.getCount();
            this.uniqueCount = (int)sst.getUniqueCount();
            for (CTRst st : sst.getSiArray()) {
                this.stmap.put(this.xmlText(st), cnt);
                this.strings.add(st);
                ++cnt;
            }
        } catch (XmlException e) {
            throw new IOException("unable to parse shared strings table", e);
        }
    }

    protected String xmlText(CTRst st) {
        return st.xmlText(options);
    }

    @Removal(version="4.2")
    public CTRst getEntryAt(int idx) {
        return this.strings.get(idx);
    }

    @Override
    public RichTextString getItemAt(int idx) {
        return new XSSFRichTextString(this.strings.get(idx));
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
    public int addEntry(CTRst st) {
        String s = this.xmlText(st);
        ++this.count;
        if (this.stmap.containsKey(s)) {
            return this.stmap.get(s);
        }
        ++this.uniqueCount;
        CTRst newSt = this._sstDoc.getSst().addNewSi();
        newSt.set(st);
        int idx = this.strings.size();
        this.stmap.put(s, idx);
        this.strings.add(newSt);
        return idx;
    }

    public int addSharedStringItem(RichTextString string) {
        if (!(string instanceof XSSFRichTextString)) {
            throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
        }
        return this.addEntry(((XSSFRichTextString)string).getCTRst());
    }

    @Removal(version="4.2")
    public List<CTRst> getItems() {
        return Collections.unmodifiableList(this.strings);
    }

    public List<RichTextString> getSharedStringItems() {
        ArrayList<XSSFRichTextString> items = new ArrayList<XSSFRichTextString>();
        for (CTRst rst : this.strings) {
            items.add(new XSSFRichTextString(rst));
        }
        return Collections.unmodifiableList(items);
    }

    public void writeTo(OutputStream out) throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveCDataLengthThreshold(1000000);
        xmlOptions.setSaveCDataEntityCountThreshold(-1);
        CTSst sst = this._sstDoc.getSst();
        sst.setCount(this.count);
        sst.setUniqueCount(this.uniqueCount);
        this._sstDoc.save(out, xmlOptions);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        try (OutputStream out = part.getOutputStream();){
            this.writeTo(out);
        }
    }

    @Override
    public void close() throws IOException {
    }

    static {
        options.put("SAVE_INNER");
        options.put("SAVE_AGGRESSIVE_NAMESPACES");
        options.put("SAVE_USE_DEFAULT_NAMESPACE");
        options.setSaveImplicitNamespaces(Collections.singletonMap("", "http://schemas.openxmlformats.org/spreadsheetml/2006/main"));
    }
}

