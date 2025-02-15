/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.extractor;

import java.io.IOException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeTextVisitor;

public class XDGFVisioExtractor
extends POIXMLTextExtractor {
    protected final XmlVisioDocument document;

    public XDGFVisioExtractor(XmlVisioDocument document) {
        super(document);
        this.document = document;
    }

    public XDGFVisioExtractor(OPCPackage openPackage) throws IOException {
        this(new XmlVisioDocument(openPackage));
    }

    @Override
    public String getText() {
        ShapeTextVisitor visitor = new ShapeTextVisitor();
        for (XDGFPage page : this.document.getPages()) {
            page.getContent().visitShapes(visitor);
        }
        return visitor.getText();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XDGFVisioExtractor <filename.vsdx>");
            System.exit(1);
        }
        XDGFVisioExtractor extractor = new XDGFVisioExtractor(POIXMLDocument.openPackage(args[0]));
        System.out.println(((POITextExtractor)extractor).getText());
        extractor.close();
    }
}

