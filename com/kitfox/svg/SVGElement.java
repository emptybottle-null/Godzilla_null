/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.SVGParseException;
import com.kitfox.svg.SVGRoot;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.TrackBase;
import com.kitfox.svg.animation.TrackManager;
import com.kitfox.svg.pathcmd.Arc;
import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.Cubic;
import com.kitfox.svg.pathcmd.CubicSmooth;
import com.kitfox.svg.pathcmd.Horizontal;
import com.kitfox.svg.pathcmd.LineTo;
import com.kitfox.svg.pathcmd.MoveTo;
import com.kitfox.svg.pathcmd.PathCommand;
import com.kitfox.svg.pathcmd.Quadratic;
import com.kitfox.svg.pathcmd.QuadraticSmooth;
import com.kitfox.svg.pathcmd.Terminal;
import com.kitfox.svg.pathcmd.Vertical;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.StyleSheet;
import com.kitfox.svg.xml.XMLParseUtil;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class SVGElement
implements Serializable {
    public static final long serialVersionUID = 0L;
    public static final String SVG_NS = "http://www.w3.org/2000/svg";
    protected SVGElement parent = null;
    protected final ArrayList<SVGElement> children = new ArrayList();
    protected String id = null;
    protected String cssClass = null;
    protected final HashMap<String, StyleAttribute> inlineStyles = new HashMap();
    protected final HashMap<String, StyleAttribute> presAttribs = new HashMap();
    protected URI xmlBase = null;
    protected SVGDiagram diagram;
    protected final TrackManager trackManager = new TrackManager();
    boolean dirty = true;
    LinkedList<SVGElement> contexts = new LinkedList();
    private static final Pattern TRANSFORM_PATTERN = Pattern.compile("\\w+\\([^)]*\\)");
    private static final Pattern WORD_PATTERN = Pattern.compile("([a-zA-Z]+|-?\\d+(\\.\\d+)?(e-?\\d+)?|-?\\.\\d+(e-?\\d+)?)");
    private static final Pattern COMMAND_PATTERN = Pattern.compile("([MmLlHhVvAaQqTtCcSsZz])|([-+]?((\\d*\\.\\d+)|(\\d+))([eE][-+]?\\d+)?)");

    public SVGElement() {
        this(null, null, null);
    }

    public SVGElement(String id, SVGElement parent) {
        this(id, null, parent);
    }

    public SVGElement(String id, String cssClass, SVGElement parent) {
        this.id = id;
        this.cssClass = cssClass;
        this.parent = parent;
    }

    public abstract String getTagName();

    public SVGElement getParent() {
        return this.parent;
    }

    void setParent(SVGElement parent) {
        this.parent = parent;
    }

    public List<SVGElement> getPath(List<SVGElement> retVec) {
        if (retVec == null) {
            retVec = new ArrayList<SVGElement>();
        }
        if (this.parent != null) {
            this.parent.getPath(retVec);
        }
        retVec.add(this);
        return retVec;
    }

    public List<SVGElement> getChildren(List<SVGElement> retVec) {
        if (retVec == null) {
            retVec = new ArrayList<SVGElement>();
        }
        retVec.addAll(this.children);
        return retVec;
    }

    public SVGElement getChild(String id) {
        for (SVGElement ele : this.children) {
            String eleId = ele.getId();
            if (eleId == null || !eleId.equals(id)) continue;
            return ele;
        }
        return null;
    }

    public int indexOfChild(SVGElement child) {
        return this.children.indexOf(child);
    }

    public void swapChildren(int i, int j) throws SVGException {
        if (this.children == null || i < 0 || i >= this.children.size() || j < 0 || j >= this.children.size()) {
            return;
        }
        SVGElement temp = this.children.get(i);
        this.children.set(i, this.children.get(j));
        this.children.set(j, temp);
        this.build();
    }

    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        String base;
        String className;
        this.parent = parent;
        this.diagram = helper.diagram;
        this.id = attrs.getValue("id");
        if (this.id != null && !this.id.equals("")) {
            this.id = this.id.intern();
            this.diagram.setElement(this.id, this);
        }
        this.cssClass = (className = attrs.getValue("class")) == null || className.equals("") ? null : className.intern();
        String style = attrs.getValue("style");
        if (style != null) {
            HashMap<String, StyleAttribute> hashMap = XMLParseUtil.parseStyle(style, this.inlineStyles);
        }
        if ((base = attrs.getValue("xml:base")) != null && !base.equals("")) {
            try {
                this.xmlBase = new URI(base);
            } catch (Exception e) {
                throw new SAXException(e);
            }
        }
        int numAttrs = attrs.getLength();
        for (int i = 0; i < numAttrs; ++i) {
            String name = attrs.getQName(i).intern();
            String value = attrs.getValue(i);
            this.presAttribs.put(name, new StyleAttribute(name, value == null ? null : value.intern()));
        }
    }

    public void removeAttribute(String name, int attribType) {
        switch (attribType) {
            case 0: {
                this.inlineStyles.remove(name);
                return;
            }
            case 1: {
                this.presAttribs.remove(name);
                return;
            }
        }
    }

    public void addAttribute(String name, int attribType, String value) throws SVGElementException {
        if (this.hasAttribute(name, attribType)) {
            throw new SVGElementException(this, "Attribute " + name + "(" + AnimationElement.animationElementToString(attribType) + ") already exists");
        }
        if ("id".equals(name)) {
            if (this.diagram != null) {
                this.diagram.removeElement(this.id);
                this.diagram.setElement(value, this);
            }
            this.id = value;
        }
        switch (attribType) {
            case 0: {
                this.inlineStyles.put(name, new StyleAttribute(name, value));
                return;
            }
            case 1: {
                this.presAttribs.put(name, new StyleAttribute(name, value));
                return;
            }
        }
        throw new SVGElementException(this, "Invalid attribute type " + attribType);
    }

    public boolean hasAttribute(String name, int attribType) throws SVGElementException {
        switch (attribType) {
            case 0: {
                return this.inlineStyles.containsKey(name);
            }
            case 1: {
                return this.presAttribs.containsKey(name);
            }
            case 2: {
                return this.inlineStyles.containsKey(name) || this.presAttribs.containsKey(name);
            }
        }
        throw new SVGElementException(this, "Invalid attribute type " + attribType);
    }

    public Set<String> getInlineAttributes() {
        return this.inlineStyles.keySet();
    }

    public Set<String> getPresentationAttributes() {
        return this.presAttribs.keySet();
    }

    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        this.children.add(child);
        child.parent = this;
        child.setDiagram(this.diagram);
        if (child instanceof AnimationElement) {
            this.trackManager.addTrackElement((AnimationElement)child);
        }
    }

    protected void setDiagram(SVGDiagram diagram) {
        this.diagram = diagram;
        diagram.setElement(this.id, this);
        for (SVGElement ele : this.children) {
            ele.setDiagram(diagram);
        }
    }

    public void removeChild(SVGElement child) throws SVGElementException {
        if (!this.children.contains(child)) {
            throw new SVGElementException(this, "Element does not contain child " + child);
        }
        this.children.remove(child);
    }

    public void loaderAddText(SVGLoaderHelper helper, String text) {
    }

    public void loaderEndElement(SVGLoaderHelper helper) throws SVGParseException {
    }

    protected void build() throws SVGException {
        String newId;
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("id")) && !(newId = sty.getStringValue()).equals(this.id)) {
            this.diagram.removeElement(this.id);
            this.id = newId;
            this.diagram.setElement(this.id, this);
        }
        if (this.getPres(sty.setName("class"))) {
            this.cssClass = sty.getStringValue();
        }
        if (this.getPres(sty.setName("xml:base"))) {
            this.xmlBase = sty.getURIValue();
        }
        for (int i = 0; i < this.children.size(); ++i) {
            SVGElement ele = this.children.get(i);
            ele.build();
        }
    }

    public URI getXMLBase() {
        return this.xmlBase != null ? this.xmlBase : (this.parent != null ? this.parent.getXMLBase() : this.diagram.getXMLBase());
    }

    public String getId() {
        return this.id;
    }

    protected void pushParentContext(SVGElement context) {
        this.contexts.addLast(context);
    }

    protected SVGElement popParentContext() {
        return this.contexts.removeLast();
    }

    protected SVGElement getParentContext() {
        return this.contexts.isEmpty() ? null : this.contexts.getLast();
    }

    public SVGRoot getRoot() {
        return this.parent == null ? null : this.parent.getRoot();
    }

    public boolean getStyle(StyleAttribute attrib) throws SVGException {
        return this.getStyle(attrib, true);
    }

    public void setAttribute(String name, int attribType, String value) throws SVGElementException {
        StyleAttribute styAttr;
        switch (attribType) {
            case 0: {
                styAttr = this.inlineStyles.get(name);
                break;
            }
            case 1: {
                styAttr = this.presAttribs.get(name);
                break;
            }
            case 2: {
                styAttr = this.inlineStyles.get(name);
                if (styAttr != null) break;
                styAttr = this.presAttribs.get(name);
                break;
            }
            default: {
                throw new SVGElementException(this, "Invalid attribute type " + attribType);
            }
        }
        if (styAttr == null) {
            throw new SVGElementException(this, "Could not find attribute " + name + "(" + AnimationElement.animationElementToString(attribType) + ").  Make sure to create attribute before setting it.");
        }
        if ("id".equals(styAttr.getName())) {
            if (this.diagram != null) {
                this.diagram.removeElement(this.id);
                this.diagram.setElement(value, this);
            }
            this.id = value;
        }
        styAttr.setStringValue(value);
    }

    public boolean getStyle(StyleAttribute attrib, boolean recursive) throws SVGException {
        return this.getStyle(attrib, recursive, true);
    }

    public boolean getStyle(StyleAttribute attrib, boolean recursive, boolean evalAnimation) throws SVGException {
        StyleSheet ss;
        TrackBase track;
        TrackBase track2;
        String styName = attrib.getName();
        StyleAttribute styAttr = this.inlineStyles.get(styName);
        attrib.setStringValue(styAttr == null ? "" : styAttr.getStringValue());
        if (evalAnimation && (track2 = this.trackManager.getTrack(styName, 0)) != null) {
            track2.getValue(attrib, this.diagram.getUniverse().getCurTime());
            return true;
        }
        if (styAttr != null) {
            return true;
        }
        StyleAttribute presAttr = this.presAttribs.get(styName);
        attrib.setStringValue(presAttr == null ? "" : presAttr.getStringValue());
        if (evalAnimation && (track = this.trackManager.getTrack(styName, 1)) != null) {
            track.getValue(attrib, this.diagram.getUniverse().getCurTime());
            return true;
        }
        if (presAttr != null) {
            return true;
        }
        SVGRoot root = this.getRoot();
        if (root != null && (ss = root.getStyleSheet()) != null) {
            return ss.getStyle(attrib, this.getTagName(), this.cssClass);
        }
        if (recursive) {
            SVGElement parentContext = this.getParentContext();
            if (parentContext != null) {
                return parentContext.getStyle(attrib, true);
            }
            if (this.parent != null) {
                return this.parent.getStyle(attrib, true);
            }
        }
        return false;
    }

    public StyleAttribute getStyleAbsolute(String styName) {
        return this.inlineStyles.get(styName);
    }

    public boolean getPres(StyleAttribute attrib) throws SVGException {
        String presName = attrib.getName();
        StyleAttribute presAttr = this.presAttribs.get(presName);
        attrib.setStringValue(presAttr == null ? "" : presAttr.getStringValue());
        TrackBase track = this.trackManager.getTrack(presName, 1);
        if (track != null) {
            track.getValue(attrib, this.diagram.getUniverse().getCurTime());
            return true;
        }
        return presAttr != null;
    }

    public StyleAttribute getPresAbsolute(String styName) {
        return this.presAttribs.get(styName);
    }

    protected static AffineTransform parseTransform(String val) throws SVGException {
        Matcher matchExpression = TRANSFORM_PATTERN.matcher("");
        AffineTransform retXform = new AffineTransform();
        matchExpression.reset(val);
        while (matchExpression.find()) {
            retXform.concatenate(SVGElement.parseSingleTransform(matchExpression.group()));
        }
        return retXform;
    }

    public static AffineTransform parseSingleTransform(String val) throws SVGException {
        Matcher matchWord = WORD_PATTERN.matcher("");
        AffineTransform retXform = new AffineTransform();
        matchWord.reset(val);
        if (!matchWord.find()) {
            return retXform;
        }
        String function = matchWord.group().toLowerCase();
        LinkedList<String> termList = new LinkedList<String>();
        while (matchWord.find()) {
            termList.add(matchWord.group());
        }
        double[] terms = new double[termList.size()];
        Iterator it = termList.iterator();
        int count = 0;
        while (it.hasNext()) {
            terms[count++] = XMLParseUtil.parseDouble((String)it.next());
        }
        if (function.equals("matrix")) {
            retXform.setTransform(terms[0], terms[1], terms[2], terms[3], terms[4], terms[5]);
        } else if (function.equals("translate")) {
            if (terms.length == 1) {
                retXform.setToTranslation(terms[0], 0.0);
            } else {
                retXform.setToTranslation(terms[0], terms[1]);
            }
        } else if (function.equals("scale")) {
            if (terms.length > 1) {
                retXform.setToScale(terms[0], terms[1]);
            } else {
                retXform.setToScale(terms[0], terms[0]);
            }
        } else if (function.equals("rotate")) {
            if (terms.length > 2) {
                retXform.setToRotation(Math.toRadians(terms[0]), terms[1], terms[2]);
            } else {
                retXform.setToRotation(Math.toRadians(terms[0]));
            }
        } else if (function.equals("skewx")) {
            retXform.setToShear(Math.toRadians(terms[0]), 0.0);
        } else if (function.equals("skewy")) {
            retXform.setToShear(0.0, Math.toRadians(terms[0]));
        } else {
            throw new SVGException("Unknown transform type");
        }
        return retXform;
    }

    protected static float nextFloat(LinkedList<String> l) {
        String s = l.removeFirst();
        return Float.parseFloat(s);
    }

    protected static PathCommand[] parsePathList(String list) {
        Matcher matchPathCmd = COMMAND_PATTERN.matcher(list);
        LinkedList<String> tokens = new LinkedList<String>();
        while (matchPathCmd.find()) {
            tokens.addLast(matchPathCmd.group());
        }
        boolean defaultRelative = false;
        LinkedList<Terminal> cmdList = new LinkedList<Terminal>();
        int curCmd = 90;
        while (tokens.size() != 0) {
            String curToken = (String)tokens.removeFirst();
            char initChar = curToken.charAt(0);
            if (initChar >= 'A' && initChar <= 'Z' || initChar >= 'a' && initChar <= 'z') {
                curCmd = initChar;
            } else {
                tokens.addFirst(curToken);
            }
            PathCommand cmd = null;
            switch (curCmd) {
                case 77: {
                    cmd = new MoveTo(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    curCmd = 76;
                    break;
                }
                case 109: {
                    cmd = new MoveTo(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    curCmd = 108;
                    break;
                }
                case 76: {
                    cmd = new LineTo(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 108: {
                    cmd = new LineTo(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 72: {
                    cmd = new Horizontal(false, SVGElement.nextFloat(tokens));
                    break;
                }
                case 104: {
                    cmd = new Horizontal(true, SVGElement.nextFloat(tokens));
                    break;
                }
                case 86: {
                    cmd = new Vertical(false, SVGElement.nextFloat(tokens));
                    break;
                }
                case 118: {
                    cmd = new Vertical(true, SVGElement.nextFloat(tokens));
                    break;
                }
                case 65: {
                    cmd = new Arc(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens) == 1.0f, SVGElement.nextFloat(tokens) == 1.0f, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 97: {
                    cmd = new Arc(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens) == 1.0f, SVGElement.nextFloat(tokens) == 1.0f, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 81: {
                    cmd = new Quadratic(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 113: {
                    cmd = new Quadratic(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 84: {
                    cmd = new QuadraticSmooth(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 116: {
                    cmd = new QuadraticSmooth(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 67: {
                    cmd = new Cubic(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 99: {
                    cmd = new Cubic(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 83: {
                    cmd = new CubicSmooth(false, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 115: {
                    cmd = new CubicSmooth(true, SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens), SVGElement.nextFloat(tokens));
                    break;
                }
                case 90: 
                case 122: {
                    cmd = new Terminal();
                    break;
                }
                default: {
                    throw new RuntimeException("Invalid path element");
                }
            }
            cmdList.add((Terminal)cmd);
            defaultRelative = cmd.isRelative;
        }
        PathCommand[] retArr = new PathCommand[cmdList.size()];
        cmdList.toArray(retArr);
        return retArr;
    }

    protected static GeneralPath buildPath(String text, int windingRule) {
        PathCommand[] commands = SVGElement.parsePathList(text);
        int numKnots = 2;
        for (int i = 0; i < commands.length; ++i) {
            numKnots += commands[i].getNumKnotsAdded();
        }
        GeneralPath path = new GeneralPath(windingRule, numKnots);
        BuildHistory hist = new BuildHistory();
        for (int i = 0; i < commands.length; ++i) {
            PathCommand cmd = commands[i];
            cmd.appendPath(path, hist);
        }
        return path;
    }

    public abstract boolean updateTime(double var1) throws SVGException;

    public int getNumChildren() {
        return this.children.size();
    }

    public SVGElement getChild(int i) {
        return this.children.get(i);
    }

    public double lerp(double t0, double t1, double alpha) {
        return (1.0 - alpha) * t0 + alpha * t1;
    }
}

