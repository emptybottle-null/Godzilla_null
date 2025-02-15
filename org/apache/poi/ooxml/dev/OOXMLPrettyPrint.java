/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.dev;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class OOXMLPrettyPrint {
    private static final String XML_INDENT_AMOUNT = "{http://xml.apache.org/xslt}indent-amount";
    private final DocumentBuilder documentBuilder;

    public OOXMLPrettyPrint() {
        ZipSecureFile.setMinInflateRatio(1.0E-5);
        this.documentBuilder = DocumentHelper.newDocumentBuilder();
    }

    public static void main(String[] args) throws Exception {
        if (args.length <= 1 || args.length % 2 != 0) {
            System.err.println("Use:");
            System.err.println("\tjava OOXMLPrettyPrint [<filename> <outfilename>] ...");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i += 2) {
            File f = new File(args[i]);
            if (!f.exists()) {
                System.err.println("Error, file not found!");
                System.err.println("\t" + f);
                System.exit(2);
            }
            OOXMLPrettyPrint.handleFile(f, new File(args[i + 1]));
        }
        System.out.println("Done.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void handleFile(File file, File outFile) throws IOException {
        System.out.println("Reading zip-file " + file + " and writing pretty-printed XML to " + outFile);
        try (ZipSecureFile zipFile = ZipHelper.openZipFile(file);
             ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));){
            new OOXMLPrettyPrint().handle(zipFile, out);
        } finally {
            System.out.println();
        }
    }

    private void handle(ZipSecureFile file, ZipOutputStream out) throws IOException {
        Enumeration<ZipArchiveEntry> entries = file.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();
            String name = entry.getName();
            out.putNextEntry(new ZipEntry(name));
            try {
                if (name.endsWith(".xml") || name.endsWith(".rels")) {
                    Document document = this.documentBuilder.parse(new InputSource(file.getInputStream(entry)));
                    document.setXmlStandalone(true);
                    OOXMLPrettyPrint.pretty(document, out, 2);
                } else {
                    System.out.println("Not pretty-printing non-XML file " + name);
                    IOUtils.copy((InputStream)file.getInputStream(entry), out);
                }
            } catch (Exception e) {
                throw new IOException("While handling entry " + name, e);
            } finally {
                out.closeEntry();
            }
            System.out.print(".");
        }
    }

    private static void pretty(Document document, OutputStream outputStream, int indent) throws TransformerException {
        Transformer transformer = XMLHelper.newTransformer();
        if (indent > 0) {
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty(XML_INDENT_AMOUNT, Integer.toString(indent));
        }
        StreamResult result = new StreamResult(outputStream);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }
}

