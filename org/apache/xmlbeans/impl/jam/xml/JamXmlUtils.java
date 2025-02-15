/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JamService;
import org.apache.xmlbeans.impl.jam.JamServiceFactory;
import org.apache.xmlbeans.impl.jam.JamServiceParams;
import org.apache.xmlbeans.impl.jam.internal.CachedClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.JamServiceImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.xml.JamXmlReader;
import org.apache.xmlbeans.impl.jam.xml.JamXmlWriter;

public class JamXmlUtils {
    private static final JamXmlUtils INSTANCE = new JamXmlUtils();

    public static final JamXmlUtils getInstance() {
        return INSTANCE;
    }

    private JamXmlUtils() {
    }

    public JamService createService(InputStream in) throws IOException, XMLStreamException {
        if (in == null) {
            throw new IllegalArgumentException("null stream");
        }
        JamServiceFactory jsf = JamServiceFactory.getInstance();
        JamServiceParams params = jsf.createServiceParams();
        CachedClassBuilder cache = new CachedClassBuilder();
        params.addClassBuilder(cache);
        JamService out = jsf.createService(params);
        JamXmlReader reader = new JamXmlReader(cache, in, (ElementContext)((Object)params));
        reader.read();
        List<String> classNames = Arrays.asList(cache.getClassNames());
        classNames.addAll(Arrays.asList(out.getClassNames()));
        String[] nameArray = new String[classNames.size()];
        classNames.toArray(nameArray);
        ((JamServiceImpl)out).setClassNames(nameArray);
        return out;
    }

    public void toXml(JClass[] clazzes, Writer writer) throws IOException, XMLStreamException {
        if (clazzes == null) {
            throw new IllegalArgumentException("null classes");
        }
        if (writer == null) {
            throw new IllegalArgumentException("null writer");
        }
        JamXmlWriter out = new JamXmlWriter(writer);
        out.begin();
        for (int i = 0; i < clazzes.length; ++i) {
            out.write(clazzes[i]);
        }
        out.end();
    }
}

