/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml;

import javax.xml.parsers.SAXParserFactory;
import org.fife.rsta.ac.xml.ValidationConfig;
import org.fife.rsta.ac.xml.XmlParser;
import org.xml.sax.EntityResolver;

public class DtdValidationConfig
implements ValidationConfig {
    private EntityResolver entityResolver;

    public DtdValidationConfig(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    @Override
    public void configureParser(XmlParser parser) {
        SAXParserFactory spf = parser.getSaxParserFactory();
        spf.setValidating(true);
        spf.setSchema(null);
    }

    @Override
    public void configureHandler(XmlParser.Handler handler) {
        handler.setEntityResolver(this.entityResolver);
    }
}

