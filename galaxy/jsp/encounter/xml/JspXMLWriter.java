/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package galaxy.jsp.encounter.xml;

import galaxy.jsp.encounter.util.Const;
import galaxy.jsp.encounter.util.Utils;
import galaxy.jsp.encounter.xml.Options;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.io.OutputFormat;
import org.dom4j.tree.NamespaceStack;
import org.dom4j.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

public class JspXMLWriter
extends XMLFilterImpl
implements LexicalHandler {
    private static final String PAD_TEXT = " ";
    protected static final String[] LEXICAL_HANDLER_NAMES = new String[]{"http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler"};
    protected static final OutputFormat DEFAULT_FORMAT = new OutputFormat();
    private boolean resolveEntityRefs = true;
    protected int lastOutputNodeType;
    private boolean lastElementClosed = false;
    protected boolean preserve = false;
    protected Writer writer;
    private NamespaceStack namespaceStack = new NamespaceStack();
    private OutputFormat format;
    private boolean escapeText = true;
    private int indentLevel = 0;
    private StringBuffer buffer = new StringBuffer();
    private boolean charsAdded = false;
    private char lastChar;
    private boolean autoFlush;
    private LexicalHandler lexicalHandler;
    private boolean showCommentsInDTDs;
    private boolean inDTD;
    private Map<String, String> namespacesMap;
    private int maximumAllowedCharacter;
    private Options options;
    public static Charset[] controllerSpaceCharsets = new Charset[]{StandardCharsets.UTF_8, StandardCharsets.ISO_8859_1, StandardCharsets.UTF_16, StandardCharsets.UTF_16BE, StandardCharsets.UTF_16LE};

    public JspXMLWriter(OutputStream out, OutputFormat format, Options options) throws UnsupportedEncodingException {
        this.format = format;
        this.writer = this.createWriter(out, format.getEncoding());
        this.autoFlush = true;
        this.namespaceStack.push(Namespace.NO_NAMESPACE);
        this.options = options;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
        this.autoFlush = false;
    }

    public void setOutputStream(OutputStream out) throws UnsupportedEncodingException {
        this.writer = this.createWriter(out, this.format.getEncoding());
        this.autoFlush = true;
    }

    public boolean isEscapeText() {
        return this.escapeText;
    }

    public void setEscapeText(boolean escapeText) {
        this.escapeText = escapeText;
    }

    public void setIndentLevel(int indentLevel) {
        this.indentLevel = indentLevel;
    }

    public int getMaximumAllowedCharacter() {
        if (this.maximumAllowedCharacter == 0) {
            this.maximumAllowedCharacter = this.defaultMaximumAllowedCharacter();
        }
        return this.maximumAllowedCharacter;
    }

    public void setMaximumAllowedCharacter(int maximumAllowedCharacter) {
        this.maximumAllowedCharacter = maximumAllowedCharacter;
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }

    public void println() throws IOException {
        this.writer.write(this.format.getLineSeparator());
    }

    public void write(Attribute attribute) throws IOException {
        this.writeAttribute(attribute);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Document doc) throws IOException {
        this.writeDeclaration();
        if (doc.getDocType() != null) {
            this.indent();
            this.writeDocType(doc.getDocType());
        }
        int size = doc.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = doc.node(i);
            this.writeNode(node);
        }
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Element element) throws IOException {
        this.writeElement(element);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(CDATA cdata) throws IOException {
        this.writeCDATA(cdata.getText());
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Comment comment) throws IOException {
        this.writeComment(comment.getText());
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(DocumentType docType) throws IOException {
        this.writeDocType(docType);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Entity entity) throws IOException {
        this.writeEntity(entity);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Namespace namespace) throws IOException {
        this.writeNamespace(namespace);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(ProcessingInstruction processingInstruction) throws IOException {
        this.writeProcessingInstruction(processingInstruction);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(String text) throws IOException {
        this.writeString(text);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Text text) throws IOException {
        this.writeString(text.getText());
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Node node) throws IOException {
        this.writeNode(node);
        if (this.autoFlush) {
            this.flush();
        }
    }

    public void write(Object object) throws IOException {
        if (object instanceof Node) {
            this.write((Node)object);
        } else if (object instanceof String) {
            this.write((String)object);
        } else if (object instanceof List) {
            List list = (List)object;
            for (Object aList : list) {
                this.write(aList);
            }
        } else if (object != null) {
            throw new IOException("Invalid object: " + object);
        }
    }

    public void writeOpen(Element element) throws IOException {
        this.writer.write("<");
        this.writer.write(element.getQualifiedName());
        this.writeNamespaces(element);
        this.writeAttributes(element);
        this.writer.write(">");
    }

    public void writeClose(Element element) throws IOException {
        this.writeClose(element.getQualifiedName());
    }

    @Override
    public void parse(InputSource source) throws IOException, SAXException {
        this.installLexicalHandler();
        super.parse(source);
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        for (String lexicalHandlerName : LEXICAL_HANDLER_NAMES) {
            if (!lexicalHandlerName.equals(name)) continue;
            this.setLexicalHandler((LexicalHandler)value);
            return;
        }
        super.setProperty(name, value);
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        for (String lexicalHandlerName : LEXICAL_HANDLER_NAMES) {
            if (!lexicalHandlerName.equals(name)) continue;
            return this.getLexicalHandler();
        }
        return super.getProperty(name);
    }

    public void setLexicalHandler(LexicalHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Null lexical handler");
        }
        this.lexicalHandler = handler;
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
    }

    @Override
    public void startDocument() throws SAXException {
        try {
            this.writeDeclaration();
            super.startDocument();
        } catch (IOException e) {
            this.handleException(e);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        if (this.autoFlush) {
            try {
                this.flush();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (this.namespacesMap == null) {
            this.namespacesMap = new HashMap<String, String>();
        }
        this.namespacesMap.put(prefix, uri);
        super.startPrefixMapping(prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        super.endPrefixMapping(prefix);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            this.charsAdded = false;
            this.writePrintln();
            this.indent();
            this.writer.write("<");
            this.writer.write(qName);
            this.writeNamespaces();
            this.writeAttributes(attributes);
            this.writer.write(">");
            ++this.indentLevel;
            this.lastOutputNodeType = 1;
            this.lastElementClosed = false;
            super.startElement(namespaceURI, localName, qName, attributes);
        } catch (IOException e) {
            this.handleException(e);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        try {
            boolean hadContent;
            this.charsAdded = false;
            --this.indentLevel;
            if (this.lastElementClosed) {
                this.writePrintln();
                this.indent();
            }
            if (hadContent = true) {
                this.writeClose(qName);
            } else {
                this.writeEmptyElementClose(qName);
            }
            this.lastOutputNodeType = 1;
            this.lastElementClosed = true;
            super.endElement(namespaceURI, localName, qName);
        } catch (IOException e) {
            this.handleException(e);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (ch == null || ch.length == 0 || length <= 0) {
            return;
        }
        try {
            String string = String.valueOf(ch, start, length);
            if (this.escapeText) {
                string = this.escapeElementEntities(string);
            }
            if (this.format.isTrimText()) {
                if (this.lastOutputNodeType == 3 && !this.charsAdded) {
                    this.writer.write(32);
                } else if (this.charsAdded && Character.isWhitespace(this.lastChar)) {
                    this.writer.write(32);
                } else if (this.lastOutputNodeType == 1 && this.format.isPadText() && this.lastElementClosed && Character.isWhitespace(ch[0])) {
                    this.writer.write(PAD_TEXT);
                }
                String delim = "";
                StringTokenizer tokens = new StringTokenizer(string);
                while (tokens.hasMoreTokens()) {
                    this.writer.write(delim);
                    this.writer.write(tokens.nextToken());
                    delim = PAD_TEXT;
                }
            } else {
                this.writer.write(string);
            }
            this.charsAdded = true;
            this.lastChar = ch[start + length - 1];
            this.lastOutputNodeType = 3;
            super.characters(ch, start, length);
        } catch (IOException e) {
            this.handleException(e);
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        try {
            this.indent();
            this.writer.write("<?");
            this.writer.write(target);
            this.writer.write(PAD_TEXT);
            this.writer.write(data);
            this.writer.write("?>");
            this.writePrintln();
            this.lastOutputNodeType = 7;
            super.processingInstruction(target, data);
        } catch (IOException e) {
            this.handleException(e);
        }
    }

    @Override
    public void notationDecl(String name, String publicID, String systemID) throws SAXException {
        super.notationDecl(name, publicID, systemID);
    }

    @Override
    public void unparsedEntityDecl(String name, String publicID, String systemID, String notationName) throws SAXException {
        super.unparsedEntityDecl(name, publicID, systemID, notationName);
    }

    @Override
    public void startDTD(String name, String publicID, String systemID) throws SAXException {
        this.inDTD = true;
        try {
            this.writeDocType(name, publicID, systemID);
        } catch (IOException e) {
            this.handleException(e);
        }
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startDTD(name, publicID, systemID);
        }
    }

    @Override
    public void endDTD() throws SAXException {
        this.inDTD = false;
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endDTD();
        }
    }

    @Override
    public void startCDATA() throws SAXException {
        try {
            this.writer.write("<![CDATA[");
        } catch (IOException e) {
            this.handleException(e);
        }
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startCDATA();
        }
    }

    @Override
    public void endCDATA() throws SAXException {
        try {
            this.writer.write("]]>");
        } catch (IOException e) {
            this.handleException(e);
        }
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endCDATA();
        }
    }

    @Override
    public void startEntity(String name) throws SAXException {
        try {
            this.writeEntityRef(name);
        } catch (IOException e) {
            this.handleException(e);
        }
        if (this.lexicalHandler != null) {
            this.lexicalHandler.startEntity(name);
        }
    }

    @Override
    public void endEntity(String name) throws SAXException {
        if (this.lexicalHandler != null) {
            this.lexicalHandler.endEntity(name);
        }
    }

    @Override
    public void comment(char[] ch, int start, int length) throws SAXException {
        if (this.showCommentsInDTDs || !this.inDTD) {
            try {
                this.charsAdded = false;
                this.writeComment(new String(ch, start, length));
            } catch (IOException e) {
                this.handleException(e);
            }
        }
        if (this.lexicalHandler != null) {
            this.lexicalHandler.comment(ch, start, length);
        }
    }

    protected void writeElement(Element element) throws IOException {
        int size = element.nodeCount();
        String qualifiedName = element.getQualifiedName();
        this.writePrintln();
        this.indent();
        this.writer.write("<");
        this.writer.write(qualifiedName);
        int previouslyDeclaredNamespaces = this.namespaceStack.size();
        Namespace ns = element.getNamespace();
        if (this.isNamespaceDeclaration(ns)) {
            this.namespaceStack.push(ns);
            this.writeNamespace(ns);
        }
        boolean textOnly = true;
        for (int i = 0; i < size; ++i) {
            Node node = element.node(i);
            if (node instanceof Namespace) {
                Namespace additional = (Namespace)node;
                if (!this.isNamespaceDeclaration(additional)) continue;
                this.namespaceStack.push(additional);
                this.writeNamespace(additional);
                continue;
            }
            if (node instanceof Element) {
                textOnly = false;
                continue;
            }
            if (!(node instanceof Comment)) continue;
            textOnly = false;
        }
        this.writeAttributes(element);
        this.lastOutputNodeType = 1;
        if (size <= 0) {
            this.writeEmptyElementClose(qualifiedName);
        } else {
            this.writer.write(">");
            if (textOnly) {
                this.writeElementContent(element);
            } else {
                ++this.indentLevel;
                this.writeElementContent(element);
                --this.indentLevel;
                this.writePrintln();
                this.indent();
            }
            this.writer.write("</");
            this.writer.write(qualifiedName);
            this.writer.write(">");
        }
        while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
            this.namespaceStack.pop();
        }
        this.lastOutputNodeType = 1;
    }

    protected final boolean isElementSpacePreserved(Element element) {
        Attribute attr = element.attribute("space");
        boolean preserveFound = this.preserve;
        if (attr != null) {
            preserveFound = "xml".equals(attr.getNamespacePrefix()) && "preserve".equals(attr.getText());
        }
        return preserveFound;
    }

    protected void writeElementContent(Element element) throws IOException {
        boolean trim = this.format.isTrimText();
        boolean oldPreserve = this.preserve;
        if (trim) {
            this.preserve = this.isElementSpacePreserved(element);
            boolean bl = trim = !this.preserve;
        }
        if (trim) {
            Node lastTextNode = null;
            StringBuilder buff = null;
            boolean textOnly = true;
            for (Node node : element.content()) {
                boolean startsWithWhitespace;
                if (node instanceof Text) {
                    if (lastTextNode == null) {
                        lastTextNode = (Text)node;
                        continue;
                    }
                    if (buff == null) {
                        buff = new StringBuilder(lastTextNode.getText());
                    }
                    buff.append(node.getText());
                    continue;
                }
                if (!textOnly && this.format.isPadText() && (startsWithWhitespace = buff != null ? StringUtils.startsWithWhitespace(buff) : (lastTextNode != null ? StringUtils.startsWithWhitespace(lastTextNode.getText()) : false))) {
                    this.writer.write(PAD_TEXT);
                }
                if (lastTextNode != null) {
                    boolean endsWithWhitespace;
                    if (buff != null) {
                        this.writeString(buff.toString());
                        buff = null;
                    } else {
                        this.writeString(lastTextNode.getText());
                    }
                    if (this.format.isPadText() && (endsWithWhitespace = buff != null ? StringUtils.endsWithWhitespace(buff) : StringUtils.endsWithWhitespace(lastTextNode.getText()))) {
                        this.writer.write(PAD_TEXT);
                    }
                    lastTextNode = null;
                }
                textOnly = false;
                this.writeNode(node);
            }
            if (lastTextNode != null) {
                boolean startsWithWhitespace;
                if (!textOnly && this.format.isPadText() && (startsWithWhitespace = buff != null ? StringUtils.startsWithWhitespace(buff) : StringUtils.startsWithWhitespace(lastTextNode.getText()))) {
                    this.writer.write(PAD_TEXT);
                }
                if (buff != null) {
                    this.writeString(buff.toString());
                    buff = null;
                } else {
                    this.writeString(lastTextNode.getText());
                }
                lastTextNode = null;
            }
        } else {
            Node lastTextNode = null;
            for (Node node : element.content()) {
                if (node instanceof Text) {
                    this.writeNode(node);
                    lastTextNode = node;
                    continue;
                }
                if (lastTextNode != null && this.format.isPadText() && StringUtils.endsWithWhitespace(lastTextNode.getText())) {
                    this.writer.write(PAD_TEXT);
                }
                this.writeNode(node);
                lastTextNode = null;
            }
        }
        this.preserve = oldPreserve;
    }

    protected void writeCDATA(String text) throws IOException {
        this.writer.write("<![CDATA[");
        if (text != null) {
            this.writer.write(text);
        }
        this.writer.write("]]>");
        this.lastOutputNodeType = 4;
    }

    protected void writeDocType(DocumentType docType) throws IOException {
        if (docType != null) {
            docType.write(this.writer);
            this.writePrintln();
        }
    }

    protected void writeNamespace(Namespace namespace) throws IOException {
        if (namespace != null) {
            this.writeNamespace(namespace.getPrefix(), namespace.getURI());
        }
    }

    protected void writeNamespaces() throws IOException {
        if (this.namespacesMap != null) {
            for (Map.Entry<String, String> entry : this.namespacesMap.entrySet()) {
                String prefix = entry.getKey();
                String uri = entry.getValue();
                this.writeNamespace(prefix, uri);
            }
            this.namespacesMap = null;
        }
    }

    protected void writeNamespace(String prefix, String uri) throws IOException {
        if (prefix != null && prefix.length() > 0) {
            this.writer.write(" xmlns:");
            this.writer.write(prefix);
            this.writer.write("=\"");
        } else {
            this.writer.write(" xmlns=\"");
        }
        this.writer.write(uri);
        this.writer.write("\"");
    }

    protected void writeNamespaces(Element element) throws IOException {
        assert (element != null);
        for (Namespace ns : element.declaredNamespaces()) {
            this.writeNamespace(ns);
            this.namespaceStack.push(ns);
        }
    }

    protected void writeProcessingInstruction(ProcessingInstruction pi) throws IOException {
        this.writer.write("<?");
        this.writer.write(pi.getName());
        this.writer.write(PAD_TEXT);
        this.writer.write(pi.getText());
        this.writer.write("?>");
        this.writePrintln();
        this.lastOutputNodeType = 7;
    }

    protected void writeString(String text) throws IOException {
        if (text != null && text.length() > 0) {
            Map<String, String> rep;
            Charset decCharset = Charset.forName(this.options.charsetDeclare);
            if (this.options.controllerSpaceReplace != null && Charset.forName(this.options.charsetMagic) == Charset.forName(this.options.charsetDeclare) && Arrays.stream(controllerSpaceCharsets).anyMatch(c -> c == decCharset) && (rep = this.options.controllerSpaceReplace) != null) {
                for (String raw : rep.keySet()) {
                    text = text.replace(raw, rep.get(raw));
                }
            }
            if (this.options.txtEscapeType.equals(Const.EscapeType.entityNormal.name())) {
                this.writer.write(Utils.EscapeText(text, false, this.options.entityEscapeType));
            } else if (this.options.txtEscapeType.equals(Const.EscapeType.entityAll.name())) {
                this.writer.write(Utils.EscapeText(text, true, this.options.entityEscapeType));
            } else if (this.options.txtEscapeType.equals(Const.EscapeType.cdata.name())) {
                this.writer.write(Utils.WrapWithCdata(text, this.options.cdataWrapCap));
            } else {
                this.writer.write(text);
            }
        }
    }

    protected void writeNodeText(Node node) throws IOException {
        String text = node.getText();
        if (text != null && text.length() > 0) {
            if (this.escapeText) {
                text = this.escapeElementEntities(text);
            }
            this.lastOutputNodeType = 3;
            this.writer.write(text);
        }
    }

    protected void writeNode(Node node) throws IOException {
        short nodeType = node.getNodeType();
        switch (nodeType) {
            case 1: {
                this.writeElement((Element)node);
                break;
            }
            case 2: {
                this.writeAttribute((Attribute)node);
                break;
            }
            case 3: {
                this.writeNodeText(node);
                break;
            }
            case 4: {
                this.writeCDATA(node.getText());
                break;
            }
            case 5: {
                this.writeEntity((Entity)node);
                break;
            }
            case 7: {
                this.writeProcessingInstruction((ProcessingInstruction)node);
                break;
            }
            case 8: {
                this.writeComment(node.getText());
                break;
            }
            case 9: {
                this.write((Document)node);
                break;
            }
            case 10: {
                this.writeDocType((DocumentType)node);
                break;
            }
            case 13: {
                break;
            }
            default: {
                throw new IOException("Invalid node type: " + node);
            }
        }
    }

    protected void installLexicalHandler() {
        XMLReader parent = this.getParent();
        if (parent == null) {
            throw new NullPointerException("No parent for filter");
        }
        for (String lexicalHandlerName : LEXICAL_HANDLER_NAMES) {
            try {
                parent.setProperty(lexicalHandlerName, this);
                break;
            } catch (SAXNotRecognizedException sAXNotRecognizedException) {
            } catch (SAXNotSupportedException sAXNotSupportedException) {
                // empty catch block
            }
        }
    }

    protected void writeDocType(String name, String publicID, String systemID) throws IOException {
        boolean hasPublic = false;
        this.writer.write("<!DOCTYPE ");
        this.writer.write(name);
        if (publicID != null && !publicID.equals("")) {
            this.writer.write(" PUBLIC \"");
            this.writer.write(publicID);
            this.writer.write("\"");
            hasPublic = true;
        }
        if (systemID != null && !systemID.equals("")) {
            if (!hasPublic) {
                this.writer.write(" SYSTEM");
            }
            this.writer.write(" \"");
            this.writer.write(systemID);
            this.writer.write("\"");
        }
        this.writer.write(">");
        this.writePrintln();
    }

    protected void writeEntity(Entity entity) throws IOException {
        if (!this.resolveEntityRefs()) {
            this.writeEntityRef(entity.getName());
        } else {
            this.writer.write(entity.getText());
        }
    }

    protected void writeEntityRef(String name) throws IOException {
        this.writer.write("&");
        this.writer.write(name);
        this.writer.write(";");
        this.lastOutputNodeType = 5;
    }

    protected void writeComment(String text) throws IOException {
        if (this.format.isNewlines()) {
            this.println();
            this.indent();
        }
        this.writer.write("<!--");
        this.writer.write(text);
        this.writer.write("-->");
        this.lastOutputNodeType = 8;
    }

    protected void writeAttributes(Element element) throws IOException {
        int size = element.attributeCount();
        for (int i = 0; i < size; ++i) {
            String attName;
            String uri;
            Attribute attribute = element.attribute(i);
            Namespace ns = attribute.getNamespace();
            if (ns != null && ns != Namespace.NO_NAMESPACE && ns != Namespace.XML_NAMESPACE) {
                String prefix = ns.getPrefix();
                uri = this.namespaceStack.getURI(prefix);
                if (!ns.getURI().equals(uri)) {
                    this.writeNamespace(ns);
                    this.namespaceStack.push(ns);
                }
            }
            if ((attName = attribute.getName()).startsWith("xmlns:")) {
                String prefix = attName.substring(6);
                if (this.namespaceStack.getNamespaceForPrefix(prefix) != null) continue;
                String uri2 = attribute.getValue();
                this.namespaceStack.push(prefix, uri2);
                this.writeNamespace(prefix, uri2);
                continue;
            }
            if (attName.equals("xmlns")) {
                if (this.namespaceStack.getDefaultNamespace() != null) continue;
                uri = attribute.getValue();
                this.namespaceStack.push(null, uri);
                this.writeNamespace(null, uri);
                continue;
            }
            char quote = this.format.getAttributeQuoteCharacter();
            this.writer.write(PAD_TEXT);
            this.writer.write(attribute.getQualifiedName());
            this.writer.write("=");
            this.writer.write(quote);
            this.writeEscapeAttributeEntities(attribute.getValue());
            this.writer.write(quote);
        }
    }

    protected void writeAttribute(Attribute attribute) throws IOException {
        this.writer.write(PAD_TEXT);
        this.writer.write(attribute.getQualifiedName());
        this.writer.write("=");
        char quote = this.format.getAttributeQuoteCharacter();
        this.writer.write(quote);
        this.writeEscapeAttributeEntities(attribute.getValue());
        this.writer.write(quote);
        this.lastOutputNodeType = 2;
    }

    protected void writeAttributes(Attributes attributes) throws IOException {
        int size = attributes.getLength();
        for (int i = 0; i < size; ++i) {
            this.writeAttribute(attributes, i);
        }
    }

    protected void writeAttribute(Attributes attributes, int index) throws IOException {
        char quote = this.format.getAttributeQuoteCharacter();
        this.writer.write(PAD_TEXT);
        this.writer.write(attributes.getQName(index));
        this.writer.write("=");
        this.writer.write(quote);
        this.writeEscapeAttributeEntities(attributes.getValue(index));
        this.writer.write(quote);
    }

    protected void indent() throws IOException {
        String indent = this.format.getIndent();
        if (indent != null && indent.length() > 0) {
            for (int i = 0; i < this.indentLevel; ++i) {
                this.writer.write(indent);
            }
        }
    }

    protected void writePrintln() throws IOException {
        if (this.format.isNewlines()) {
            this.writer.write(this.format.getLineSeparator());
        }
    }

    protected Writer createWriter(OutputStream outStream, String encoding) throws UnsupportedEncodingException {
        return new BufferedWriter(new OutputStreamWriter(outStream, encoding));
    }

    protected void writeDeclaration() throws IOException {
        String encoding = this.format.getEncoding();
        if (!this.format.isSuppressDeclaration()) {
            if (encoding.equals("UTF8")) {
                this.writer.write("<?xml version=\"1.0\"");
                if (!this.format.isOmitEncoding()) {
                    this.writer.write(" encoding=\"UTF-8\"");
                }
                this.writer.write("?>");
            } else {
                this.writer.write("<?xml version=\"1.0\"");
                if (!this.format.isOmitEncoding()) {
                    this.writer.write(" encoding=\"" + encoding + "\"");
                }
                this.writer.write("?>");
            }
            if (this.format.isNewLineAfterDeclaration()) {
                this.println();
            }
        }
    }

    protected void writeClose(String qualifiedName) throws IOException {
        this.writer.write("</");
        this.writer.write(qualifiedName);
        this.writer.write(">");
    }

    protected void writeEmptyElementClose(String qualifiedName) throws IOException {
        if (!this.format.isExpandEmptyElements()) {
            this.writer.write("/>");
        } else {
            this.writer.write("></");
            this.writer.write(qualifiedName);
            this.writer.write(">");
        }
    }

    protected boolean isExpandEmptyElements() {
        return this.format.isExpandEmptyElements();
    }

    protected String escapeElementEntities(String text) {
        int i;
        char[] block = null;
        int last = 0;
        int size = text.length();
        for (i = 0; i < size; ++i) {
            String entity = null;
            int c = text.codePointAt(i);
            switch (c) {
                case 60: {
                    entity = "&lt;";
                    break;
                }
                case 62: {
                    entity = "&gt;";
                    break;
                }
                case 38: {
                    entity = "&amp;";
                    break;
                }
                case 9: 
                case 10: 
                case 13: {
                    if (!this.preserve) break;
                    entity = String.valueOf((char)c);
                    break;
                }
                default: {
                    if (c >= 32 && !this.shouldEncodeChar(c)) break;
                    entity = "&#" + c + ";";
                }
            }
            if (entity != null) {
                if (block == null) {
                    block = text.toCharArray();
                }
                this.buffer.append(block, last, i - last);
                this.buffer.append(entity);
                last = i + 1;
                if (Character.isSupplementaryCodePoint(c)) {
                    ++last;
                }
            }
            if (!Character.isSupplementaryCodePoint(c)) continue;
            ++i;
        }
        if (last == 0) {
            return text;
        }
        if (last < size) {
            if (block == null) {
                block = text.toCharArray();
            }
            this.buffer.append(block, last, i - last);
        }
        String answer = this.buffer.toString();
        this.buffer.setLength(0);
        return answer;
    }

    protected void writeEscapeAttributeEntities(String txt) throws IOException {
        if (txt != null) {
            String escapedText = this.escapeAttributeEntities(txt);
            this.writer.write(escapedText);
        }
    }

    protected String escapeAttributeEntities(String text) {
        int i;
        if (this.options.attrEscapeType.equals(Const.EscapeType.nothing.name())) {
            return text;
        }
        char quote = this.format.getAttributeQuoteCharacter();
        char[] block = null;
        int last = 0;
        int size = text.length();
        for (i = 0; i < size; ++i) {
            String entity = null;
            int c = text.codePointAt(i);
            switch (c) {
                case 60: {
                    entity = "&lt;";
                    break;
                }
                case 62: {
                    entity = "&gt;";
                    break;
                }
                case 39: {
                    if (quote != '\'') break;
                    entity = "&apos;";
                    break;
                }
                case 34: {
                    if (quote != '\"') break;
                    entity = "&quot;";
                    break;
                }
                case 38: {
                    entity = "&amp;";
                    break;
                }
                case 9: 
                case 10: 
                case 13: {
                    break;
                }
                default: {
                    if (c >= 32 && !this.shouldEncodeChar(c) && !this.options.attrEscapeType.equals(Const.EscapeType.entityAll.name())) break;
                    entity = Utils.ToEntity(c, this.options.entityEscapeType);
                }
            }
            if (entity != null) {
                if (block == null) {
                    block = text.toCharArray();
                }
                this.buffer.append(block, last, i - last);
                this.buffer.append(entity);
                last = i + 1;
                if (Character.isSupplementaryCodePoint(c)) {
                    ++last;
                }
            }
            if (!Character.isSupplementaryCodePoint(c)) continue;
            ++i;
        }
        if (last == 0) {
            return text;
        }
        if (last < size) {
            if (block == null) {
                block = text.toCharArray();
            }
            this.buffer.append(block, last, i - last);
        }
        String answer = this.buffer.toString();
        this.buffer.setLength(0);
        return answer;
    }

    protected boolean shouldEncodeChar(int codepoint) {
        int max = this.getMaximumAllowedCharacter();
        return max > 0 && codepoint > max;
    }

    protected int defaultMaximumAllowedCharacter() {
        String encoding = this.format.getEncoding();
        if (encoding != null && encoding.equals("US-ASCII")) {
            return 127;
        }
        return -1;
    }

    protected boolean isNamespaceDeclaration(Namespace ns) {
        String uri;
        return ns != null && ns != Namespace.XML_NAMESPACE && (uri = ns.getURI()) != null && !this.namespaceStack.contains(ns);
    }

    protected void handleException(IOException e) throws SAXException {
        throw new SAXException(e);
    }

    protected OutputFormat getOutputFormat() {
        return this.format;
    }

    public boolean resolveEntityRefs() {
        return this.resolveEntityRefs;
    }

    public void setResolveEntityRefs(boolean resolve) {
        this.resolveEntityRefs = resolve;
    }
}

