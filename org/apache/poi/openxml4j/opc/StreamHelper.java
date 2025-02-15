/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;

public final class StreamHelper {
    private StreamHelper() {
    }

    public static boolean saveXmlInStream(Document xmlContent, OutputStream outStream) {
        try {
            Transformer trans = XMLHelper.newTransformer();
            DOMSource xmlSource = new DOMSource(xmlContent);
            StreamResult outputTarget = new StreamResult(new FilterOutputStream(outStream){

                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    this.out.write(b, off, len);
                }

                @Override
                public void close() throws IOException {
                    this.out.flush();
                }
            });
            trans.setOutputProperty("encoding", "UTF-8");
            trans.setOutputProperty("indent", "no");
            trans.setOutputProperty("standalone", "yes");
            trans.transform(xmlSource, outputTarget);
        } catch (TransformerException e) {
            return false;
        }
        return true;
    }

    public static boolean copyStream(InputStream inStream, OutputStream outStream) {
        try {
            IOUtils.copy(inStream, outStream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

