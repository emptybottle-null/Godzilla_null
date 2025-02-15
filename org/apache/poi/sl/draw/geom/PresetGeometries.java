/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.io.InputStream;
import java.util.LinkedHashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import org.apache.poi.sl.draw.binding.CTCustomGeometry2D;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.XMLHelper;

public class PresetGeometries
extends LinkedHashMap<String, CustomGeometry> {
    private static final POILogger LOG = POILogFactory.getLogger(PresetGeometries.class);
    private static final String BINDING_PACKAGE = "org.apache.poi.sl.draw.binding";
    protected static PresetGeometries _inst;

    protected PresetGeometries() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void init(InputStream is) throws XMLStreamException, JAXBException {
        XMLInputFactory staxFactory = XMLHelper.newXMLInputFactory();
        try (XMLStreamReader streamReader = staxFactory.createXMLStreamReader(new StreamSource(is));){
            streamReader.nextTag();
            JAXBContext jaxbContext = SingletonHelper.JAXB_CONTEXT;
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            long cntElem = 0L;
            while (streamReader.hasNext() && streamReader.nextTag() == 1) {
                String name = streamReader.getLocalName();
                JAXBElement<CTCustomGeometry2D> el = unmarshaller.unmarshal(streamReader, CTCustomGeometry2D.class);
                CTCustomGeometry2D cus = el.getValue();
                ++cntElem;
                if (this.containsKey(name)) {
                    LOG.log(5, "Duplicate definition of " + name);
                }
                this.put(name, new CustomGeometry(cus));
            }
        }
    }

    public static CustomGeometry convertCustomGeometry(XMLStreamReader staxReader) {
        try {
            JAXBContext jaxbContext = SingletonHelper.JAXB_CONTEXT;
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<CTCustomGeometry2D> el = unmarshaller.unmarshal(staxReader, CTCustomGeometry2D.class);
            return new CustomGeometry(el.getValue());
        } catch (JAXBException e) {
            LOG.log(7, "Unable to parse single custom geometry", e);
            return null;
        }
    }

    public static synchronized PresetGeometries getInstance() {
        if (_inst == null) {
            PresetGeometries lInst = new PresetGeometries();
            try (InputStream is = PresetGeometries.class.getResourceAsStream("presetShapeDefinitions.xml");){
                lInst.init(is);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            _inst = lInst;
        }
        return _inst;
    }

    private static class SingletonHelper {
        private static JAXBContext JAXB_CONTEXT;

        private SingletonHelper() {
        }

        static {
            try {
                JAXB_CONTEXT = JAXBContext.newInstance(PresetGeometries.BINDING_PACKAGE);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

