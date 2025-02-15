/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Font;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoader;
import com.kitfox.svg.app.beans.SVGIcon;
import com.kitfox.svg.util.Base64InputStream;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class SVGUniverse
implements Serializable {
    public static final long serialVersionUID = 0L;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);
    final HashMap<URI, SVGDiagram> loadedDocs = new HashMap();
    final HashMap<String, Font> loadedFonts = new HashMap();
    final HashMap<URL, SoftReference<BufferedImage>> loadedImages = new HashMap();
    public static final String INPUTSTREAM_SCHEME = "svgSalamander";
    protected double curTime = 0.0;
    private boolean verbose = false;
    private boolean imageDataInlineOnly = false;
    static ThreadLocal<SAXParser> threadSAXParser = new ThreadLocal();

    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        this.changes.removePropertyChangeListener(l);
    }

    public void clear() {
        this.loadedDocs.clear();
        this.loadedFonts.clear();
        this.loadedImages.clear();
    }

    public double getCurTime() {
        return this.curTime;
    }

    public void setCurTime(double curTime) {
        double oldTime = this.curTime;
        this.curTime = curTime;
        this.changes.firePropertyChange("curTime", new Double(oldTime), new Double(curTime));
    }

    public void updateTime() throws SVGException {
        for (SVGDiagram dia : this.loadedDocs.values()) {
            dia.updateTime(this.curTime);
        }
    }

    void registerFont(Font font) {
        this.loadedFonts.put(font.getFontFace().getFontFamily(), font);
    }

    public Font getDefaultFont() {
        Iterator<Font> iterator = this.loadedFonts.values().iterator();
        if (iterator.hasNext()) {
            Font font = iterator.next();
            return font;
        }
        return null;
    }

    public Font getFont(String fontName) {
        return this.loadedFonts.get(fontName);
    }

    URL registerImage(URI imageURI) {
        String scheme = imageURI.getScheme();
        if (scheme.equals("data")) {
            String path = imageURI.getRawSchemeSpecificPart();
            int idx = path.indexOf(59);
            String mime = path.substring(0, idx);
            String content = path.substring(idx + 1);
            if (content.startsWith("base64")) {
                content = content.substring(6);
                try {
                    URL url;
                    ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
                    Base64InputStream bais = new Base64InputStream(bis);
                    BufferedImage img = ImageIO.read(bais);
                    int urlIdx = 0;
                    while (this.loadedImages.containsKey(url = new URL("inlineImage", "localhost", "img" + urlIdx))) {
                        ++urlIdx;
                    }
                    SoftReference<BufferedImage> ref = new SoftReference<BufferedImage>(img);
                    this.loadedImages.put(url, ref);
                    return url;
                } catch (IOException ex) {
                    Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not decode inline image", ex);
                }
            }
            return null;
        }
        try {
            URL url = imageURI.toURL();
            this.registerImage(url);
            return url;
        } catch (MalformedURLException ex) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Bad url", ex);
            return null;
        }
    }

    void registerImage(URL imageURL) {
        if (this.loadedImages.containsKey(imageURL)) {
            return;
        }
        try {
            SoftReference<BufferedImage> ref;
            String fileName = imageURL.getFile();
            if (".svg".equals(fileName.substring(fileName.length() - 4).toLowerCase())) {
                SVGIcon icon = new SVGIcon();
                icon.setSvgURI(imageURL.toURI());
                BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), 2);
                Graphics2D g = img.createGraphics();
                icon.paintIcon(null, (Graphics)g, 0, 0);
                g.dispose();
                ref = new SoftReference<BufferedImage>(img);
            } else {
                BufferedImage img = ImageIO.read(imageURL);
                ref = new SoftReference<BufferedImage>(img);
            }
            this.loadedImages.put(imageURL, ref);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not load image: " + imageURL, e);
        }
    }

    BufferedImage getImage(URL imageURL) {
        SoftReference<BufferedImage> ref = this.loadedImages.get(imageURL);
        if (ref == null) {
            return null;
        }
        BufferedImage img = ref.get();
        if (img == null) {
            try {
                img = ImageIO.read(imageURL);
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not load image", e);
            }
            ref = new SoftReference<BufferedImage>(img);
            this.loadedImages.put(imageURL, ref);
        }
        return img;
    }

    public SVGElement getElement(URI path) {
        return this.getElement(path, true);
    }

    public SVGElement getElement(URL path) {
        try {
            URI uri = new URI(path.toString());
            return this.getElement(uri, true);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse url " + path, e);
            return null;
        }
    }

    public SVGElement getElement(URI path, boolean loadIfAbsent) {
        try {
            String fragment;
            URI xmlBase = new URI(path.getScheme(), path.getSchemeSpecificPart(), null);
            SVGDiagram dia = this.loadedDocs.get(xmlBase);
            if (dia == null && loadIfAbsent) {
                URL url = xmlBase.toURL();
                this.loadSVG(url, false);
                dia = this.loadedDocs.get(xmlBase);
                if (dia == null) {
                    return null;
                }
            }
            return (fragment = path.getFragment()) == null ? dia.getRoot() : dia.getElement(fragment);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse path " + path, e);
            return null;
        }
    }

    public SVGDiagram getDiagram(URI xmlBase) {
        return this.getDiagram(xmlBase, true);
    }

    public SVGDiagram getDiagram(URI xmlBase, boolean loadIfAbsent) {
        if (xmlBase == null) {
            return null;
        }
        SVGDiagram dia = this.loadedDocs.get(xmlBase);
        if (dia != null || !loadIfAbsent) {
            return dia;
        }
        try {
            URL url = "jar".equals(xmlBase.getScheme()) && xmlBase.getPath() != null && !xmlBase.getPath().contains("!/") ? SVGUniverse.class.getResource(xmlBase.getPath()) : xmlBase.toURL();
            this.loadSVG(url, false);
            dia = this.loadedDocs.get(xmlBase);
            return dia;
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse", e);
            return null;
        }
    }

    private InputStream createDocumentInputStream(InputStream is) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(is);
        bin.mark(2);
        int b0 = bin.read();
        int b1 = bin.read();
        bin.reset();
        if ((b1 << 8 | b0) == 35615) {
            GZIPInputStream iis = new GZIPInputStream(bin);
            return iis;
        }
        return bin;
    }

    public URI loadSVG(URL docRoot) {
        return this.loadSVG(docRoot, false);
    }

    public URI loadSVG(URL docRoot, boolean forceLoad) {
        try {
            URI uri = new URI(docRoot.toString());
            if (this.loadedDocs.containsKey(uri) && !forceLoad) {
                return uri;
            }
            InputStream is = docRoot.openStream();
            URI result = this.loadSVG(uri, new InputSource(this.createDocumentInputStream(is)));
            is.close();
            return result;
        } catch (URISyntaxException ex) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse", ex);
        } catch (IOException e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse", e);
        }
        return null;
    }

    public URI loadSVG(InputStream is, String name) throws IOException {
        return this.loadSVG(is, name, false);
    }

    public URI loadSVG(InputStream is, String name, boolean forceLoad) throws IOException {
        URI uri = this.getStreamBuiltURI(name);
        if (uri == null) {
            return null;
        }
        if (this.loadedDocs.containsKey(uri) && !forceLoad) {
            return uri;
        }
        return this.loadSVG(uri, new InputSource(this.createDocumentInputStream(is)));
    }

    public URI loadSVG(Reader reader, String name) {
        return this.loadSVG(reader, name, false);
    }

    public URI loadSVG(Reader reader, String name, boolean forceLoad) {
        URI uri = this.getStreamBuiltURI(name);
        if (uri == null) {
            return null;
        }
        if (this.loadedDocs.containsKey(uri) && !forceLoad) {
            return uri;
        }
        return this.loadSVG(uri, new InputSource(reader));
    }

    public URI getStreamBuiltURI(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        if (name.charAt(0) != '/') {
            name = '/' + name;
        }
        try {
            return new URI(INPUTSTREAM_SCHEME, name, null);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse", e);
            return null;
        }
    }

    private XMLReader getXMLReader() throws SAXException, ParserConfigurationException {
        SAXParser saxParser = threadSAXParser.get();
        if (saxParser == null) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            saxParser = saxParserFactory.newSAXParser();
            threadSAXParser.set(saxParser);
        }
        return saxParser.getXMLReader();
    }

    protected URI loadSVG(URI xmlBase, InputSource is) {
        SVGLoader handler = new SVGLoader(xmlBase, this, this.verbose);
        this.loadedDocs.put(xmlBase, handler.getLoadedDiagram());
        try {
            XMLReader reader = this.getXMLReader();
            reader.setEntityResolver(new EntityResolver(){

                @Override
                public InputSource resolveEntity(String publicId, String systemId) {
                    return new InputSource(new ByteArrayInputStream(new byte[0]));
                }
            });
            reader.setContentHandler(handler);
            reader.parse(is);
            handler.getLoadedDiagram().updateTime(this.curTime);
            return xmlBase;
        } catch (SAXParseException sex) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Error processing " + xmlBase, sex);
            this.loadedDocs.remove(xmlBase);
            return null;
        } catch (Throwable e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not load SVG " + xmlBase, e);
            return null;
        }
    }

    public ArrayList<URI> getLoadedDocumentURIs() {
        return new ArrayList<URI>(this.loadedDocs.keySet());
    }

    public void removeDocument(URI uri) {
        this.loadedDocs.remove(uri);
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public SVGUniverse duplicate() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(this);
        os.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(bs.toByteArray());
        ObjectInputStream is = new ObjectInputStream(bin);
        SVGUniverse universe = (SVGUniverse)is.readObject();
        is.close();
        return universe;
    }

    public boolean isImageDataInlineOnly() {
        return this.imageDataInlineOnly;
    }

    public void setImageDataInlineOnly(boolean imageDataInlineOnly) {
        this.imageDataInlineOnly = imageDataInlineOnly;
    }
}

