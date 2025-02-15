/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.A;
import com.kitfox.svg.Circle;
import com.kitfox.svg.ClipPath;
import com.kitfox.svg.Defs;
import com.kitfox.svg.Desc;
import com.kitfox.svg.Ellipse;
import com.kitfox.svg.Filter;
import com.kitfox.svg.Font;
import com.kitfox.svg.FontFace;
import com.kitfox.svg.Glyph;
import com.kitfox.svg.Group;
import com.kitfox.svg.Hkern;
import com.kitfox.svg.ImageSVG;
import com.kitfox.svg.Line;
import com.kitfox.svg.LinearGradient;
import com.kitfox.svg.Marker;
import com.kitfox.svg.Metadata;
import com.kitfox.svg.MissingGlyph;
import com.kitfox.svg.Path;
import com.kitfox.svg.PatternSVG;
import com.kitfox.svg.Polygon;
import com.kitfox.svg.Polyline;
import com.kitfox.svg.RadialGradient;
import com.kitfox.svg.Rect;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.SVGRoot;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.Stop;
import com.kitfox.svg.Style;
import com.kitfox.svg.Symbol;
import com.kitfox.svg.Text;
import com.kitfox.svg.Title;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.Use;
import com.kitfox.svg.animation.Animate;
import com.kitfox.svg.animation.AnimateColor;
import com.kitfox.svg.animation.AnimateMotion;
import com.kitfox.svg.animation.AnimateTransform;
import com.kitfox.svg.animation.SetSmil;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SVGLoader
extends DefaultHandler {
    final HashMap<String, Class<?>> nodeClasses = new HashMap();
    final LinkedList<SVGElement> buildStack = new LinkedList();
    final HashSet<String> ignoreClasses = new HashSet();
    final SVGLoaderHelper helper;
    final SVGDiagram diagram;
    int skipNonSVGTagDepth = 0;
    int indent = 0;
    final boolean verbose;

    public SVGLoader(URI xmlBase, SVGUniverse universe) {
        this(xmlBase, universe, false);
    }

    public SVGLoader(URI xmlBase, SVGUniverse universe, boolean verbose) {
        this.verbose = verbose;
        this.diagram = new SVGDiagram(xmlBase, universe);
        this.nodeClasses.put("a", A.class);
        this.nodeClasses.put("animate", Animate.class);
        this.nodeClasses.put("animatecolor", AnimateColor.class);
        this.nodeClasses.put("animatemotion", AnimateMotion.class);
        this.nodeClasses.put("animatetransform", AnimateTransform.class);
        this.nodeClasses.put("circle", Circle.class);
        this.nodeClasses.put("clippath", ClipPath.class);
        this.nodeClasses.put("defs", Defs.class);
        this.nodeClasses.put("desc", Desc.class);
        this.nodeClasses.put("ellipse", Ellipse.class);
        this.nodeClasses.put("filter", Filter.class);
        this.nodeClasses.put("font", Font.class);
        this.nodeClasses.put("font-face", FontFace.class);
        this.nodeClasses.put("g", Group.class);
        this.nodeClasses.put("glyph", Glyph.class);
        this.nodeClasses.put("hkern", Hkern.class);
        this.nodeClasses.put("image", ImageSVG.class);
        this.nodeClasses.put("line", Line.class);
        this.nodeClasses.put("lineargradient", LinearGradient.class);
        this.nodeClasses.put("marker", Marker.class);
        this.nodeClasses.put("metadata", Metadata.class);
        this.nodeClasses.put("missing-glyph", MissingGlyph.class);
        this.nodeClasses.put("path", Path.class);
        this.nodeClasses.put("pattern", PatternSVG.class);
        this.nodeClasses.put("polygon", Polygon.class);
        this.nodeClasses.put("polyline", Polyline.class);
        this.nodeClasses.put("radialgradient", RadialGradient.class);
        this.nodeClasses.put("rect", Rect.class);
        this.nodeClasses.put("set", SetSmil.class);
        this.nodeClasses.put("shape", ShapeElement.class);
        this.nodeClasses.put("stop", Stop.class);
        this.nodeClasses.put("style", Style.class);
        this.nodeClasses.put("svg", SVGRoot.class);
        this.nodeClasses.put("symbol", Symbol.class);
        this.nodeClasses.put("text", Text.class);
        this.nodeClasses.put("title", Title.class);
        this.nodeClasses.put("tspan", Tspan.class);
        this.nodeClasses.put("use", Use.class);
        this.ignoreClasses.add("midpointstop");
        this.helper = new SVGLoaderHelper(xmlBase, universe, this.diagram);
    }

    private String printIndent(int indent, String indentStrn) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < indent; ++i) {
            sb.append(indentStrn);
        }
        return sb.toString();
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException {
        if (this.verbose) {
            System.err.println(this.printIndent(this.indent, " ") + "Starting parse of tag " + sName + ": " + namespaceURI);
        }
        ++this.indent;
        if (this.skipNonSVGTagDepth != 0 || !namespaceURI.equals("") && !namespaceURI.equals("http://www.w3.org/2000/svg")) {
            ++this.skipNonSVGTagDepth;
            return;
        }
        Class<?> obj = this.nodeClasses.get(sName = sName.toLowerCase());
        if (obj == null) {
            if (!this.ignoreClasses.contains(sName) && this.verbose) {
                System.err.println("SVGLoader: Could not identify tag '" + sName + "'");
            }
            return;
        }
        try {
            Class<?> cls = obj;
            SVGElement svgEle = (SVGElement)cls.newInstance();
            SVGElement parent = null;
            if (this.buildStack.size() != 0) {
                parent = this.buildStack.getLast();
            }
            svgEle.loaderStartElement(this.helper, attrs, parent);
            this.buildStack.addLast(svgEle);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not load", e);
            throw new SAXException(e);
        }
    }

    @Override
    public void endElement(String namespaceURI, String sName, String qName) throws SAXException {
        --this.indent;
        if (this.verbose) {
            System.err.println(this.printIndent(this.indent, " ") + "Ending parse of tag " + sName + ": " + namespaceURI);
        }
        if (this.skipNonSVGTagDepth != 0) {
            --this.skipNonSVGTagDepth;
            return;
        }
        Class<?> obj = this.nodeClasses.get(sName = sName.toLowerCase());
        if (obj == null) {
            return;
        }
        try {
            SVGElement svgEle = this.buildStack.removeLast();
            svgEle.loaderEndElement(this.helper);
            SVGElement parent = null;
            if (this.buildStack.size() != 0) {
                parent = this.buildStack.getLast();
            }
            if (parent != null) {
                parent.loaderAddChild(this.helper, svgEle);
            } else {
                this.diagram.setRoot((SVGRoot)svgEle);
            }
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse", e);
            throw new SAXException(e);
        }
    }

    @Override
    public void characters(char[] buf, int offset, int len) throws SAXException {
        if (this.skipNonSVGTagDepth != 0) {
            return;
        }
        if (this.buildStack.size() != 0) {
            SVGElement parent = this.buildStack.getLast();
            String s = new String(buf, offset, len);
            parent.loaderAddText(this.helper, s);
        }
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }

    public SVGDiagram getLoadedDiagram() {
        return this.diagram;
    }
}

