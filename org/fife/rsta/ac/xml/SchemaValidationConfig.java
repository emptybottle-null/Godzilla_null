/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.fife.rsta.ac.xml.ValidationConfig;
import org.fife.rsta.ac.xml.XmlParser;
import org.xml.sax.SAXException;

public class SchemaValidationConfig
implements ValidationConfig {
    private Schema schema;

    public SchemaValidationConfig(String language, InputStream in) throws IOException {
        SchemaFactory sf = SchemaFactory.newInstance(language);
        try (BufferedInputStream bis = new BufferedInputStream(in);){
            this.schema = sf.newSchema(new StreamSource(bis));
        } catch (SAXException se) {
            se.printStackTrace();
            throw new IOException(se.toString());
        }
    }

    @Override
    public void configureParser(XmlParser parser) {
        SAXParserFactory spf = parser.getSaxParserFactory();
        spf.setValidating(false);
        if (this.schema != null) {
            spf.setSchema(this.schema);
        }
    }

    @Override
    public void configureHandler(XmlParser.Handler handler) {
        handler.setEntityResolver(null);
    }
}

