/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.apache.log4j.xml.SAXErrorHandler;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.apache.log4j.xml.XMLWatchdog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMConfigurator
implements Configurator {
    static final String CONFIGURATION_TAG = "log4j:configuration";
    static final String OLD_CONFIGURATION_TAG = "configuration";
    static final String RENDERER_TAG = "renderer";
    private static final String THROWABLE_RENDERER_TAG = "throwableRenderer";
    static final String APPENDER_TAG = "appender";
    static final String APPENDER_REF_TAG = "appender-ref";
    static final String PARAM_TAG = "param";
    static final String LAYOUT_TAG = "layout";
    static final String CATEGORY = "category";
    static final String LOGGER = "logger";
    static final String LOGGER_REF = "logger-ref";
    static final String CATEGORY_FACTORY_TAG = "categoryFactory";
    static final String LOGGER_FACTORY_TAG = "loggerFactory";
    static final String NAME_ATTR = "name";
    static final String CLASS_ATTR = "class";
    static final String VALUE_ATTR = "value";
    static final String ROOT_TAG = "root";
    static final String ROOT_REF = "root-ref";
    static final String LEVEL_TAG = "level";
    static final String PRIORITY_TAG = "priority";
    static final String FILTER_TAG = "filter";
    static final String ERROR_HANDLER_TAG = "errorHandler";
    static final String REF_ATTR = "ref";
    static final String ADDITIVITY_ATTR = "additivity";
    static final String THRESHOLD_ATTR = "threshold";
    static final String CONFIG_DEBUG_ATTR = "configDebug";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    private static final String RESET_ATTR = "reset";
    static final String RENDERING_CLASS_ATTR = "renderingClass";
    static final String RENDERED_CLASS_ATTR = "renderedClass";
    static final String EMPTY_STR = "";
    static final Class[] ONE_STRING_PARAM = new Class[]{String.class};
    static final String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";
    Hashtable appenderBag = new Hashtable();
    Properties props;
    LoggerRepository repository;
    protected LoggerFactory catFactory = null;

    protected Appender findAppenderByName(Document doc, String appenderName) {
        Appender appender = (Appender)this.appenderBag.get(appenderName);
        if (appender != null) {
            return appender;
        }
        Element element = null;
        NodeList list = doc.getElementsByTagName(APPENDER_TAG);
        for (int t = 0; t < list.getLength(); ++t) {
            Node node = list.item(t);
            NamedNodeMap map = node.getAttributes();
            Node attrNode = map.getNamedItem(NAME_ATTR);
            if (!appenderName.equals(attrNode.getNodeValue())) continue;
            element = (Element)node;
            break;
        }
        if (element == null) {
            LogLog.error("No appender named [" + appenderName + "] could be found.");
            return null;
        }
        appender = this.parseAppender(element);
        if (appender != null) {
            this.appenderBag.put(appenderName, appender);
        }
        return appender;
    }

    protected Appender findAppenderByReference(Element appenderRef) {
        String appenderName = this.subst(appenderRef.getAttribute(REF_ATTR));
        Document doc = appenderRef.getOwnerDocument();
        return this.findAppenderByName(doc, appenderName);
    }

    private static void parseUnrecognizedElement(Object instance, Element element, Properties props) throws Exception {
        boolean recognized = false;
        if (instance instanceof UnrecognizedElementHandler) {
            recognized = ((UnrecognizedElementHandler)instance).parseUnrecognizedElement(element, props);
        }
        if (!recognized) {
            LogLog.warn("Unrecognized element " + element.getNodeName());
        }
    }

    private static void quietParseUnrecognizedElement(Object instance, Element element, Properties props) {
        try {
            DOMConfigurator.parseUnrecognizedElement(instance, element, props);
        } catch (Exception ex) {
            if (ex instanceof InterruptedException || ex instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Error in extension content: ", ex);
        }
    }

    protected Appender parseAppender(Element appenderElement) {
        String className = this.subst(appenderElement.getAttribute(CLASS_ATTR));
        LogLog.debug("Class name: [" + className + ']');
        try {
            Object instance = Loader.loadClass(className).newInstance();
            Appender appender = (Appender)instance;
            PropertySetter propSetter = new PropertySetter(appender);
            appender.setName(this.subst(appenderElement.getAttribute(NAME_ATTR)));
            NodeList children = appenderElement.getChildNodes();
            int length = children.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = children.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                if (currentElement.getTagName().equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                if (currentElement.getTagName().equals(LAYOUT_TAG)) {
                    appender.setLayout(this.parseLayout(currentElement));
                    continue;
                }
                if (currentElement.getTagName().equals(FILTER_TAG)) {
                    this.parseFilters(currentElement, appender);
                    continue;
                }
                if (currentElement.getTagName().equals(ERROR_HANDLER_TAG)) {
                    this.parseErrorHandler(currentElement, appender);
                    continue;
                }
                if (currentElement.getTagName().equals(APPENDER_REF_TAG)) {
                    String refName = this.subst(currentElement.getAttribute(REF_ATTR));
                    if (appender instanceof AppenderAttachable) {
                        AppenderAttachable aa = (AppenderAttachable)((Object)appender);
                        LogLog.debug("Attaching appender named [" + refName + "] to appender named [" + appender.getName() + "].");
                        aa.addAppender(this.findAppenderByReference(currentElement));
                        continue;
                    }
                    LogLog.error("Requesting attachment of appender named [" + refName + "] to appender named [" + appender.getName() + "] which does not implement org.apache.log4j.spi.AppenderAttachable.");
                    continue;
                }
                DOMConfigurator.parseUnrecognizedElement(instance, currentElement, this.props);
            }
            propSetter.activate();
            return appender;
        } catch (Exception oops) {
            if (oops instanceof InterruptedException || oops instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create an Appender. Reported error follows.", oops);
            return null;
        }
    }

    protected void parseErrorHandler(Element element, Appender appender) {
        ErrorHandler eh = (ErrorHandler)OptionConverter.instantiateByClassName(this.subst(element.getAttribute(CLASS_ATTR)), ErrorHandler.class, null);
        if (eh != null) {
            eh.setAppender(appender);
            PropertySetter propSetter = new PropertySetter(eh);
            NodeList children = element.getChildNodes();
            int length = children.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = children.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                String tagName = currentElement.getTagName();
                if (tagName.equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                if (tagName.equals(APPENDER_REF_TAG)) {
                    eh.setBackupAppender(this.findAppenderByReference(currentElement));
                    continue;
                }
                if (tagName.equals(LOGGER_REF)) {
                    String loggerName = currentElement.getAttribute(REF_ATTR);
                    Logger logger = this.catFactory == null ? this.repository.getLogger(loggerName) : this.repository.getLogger(loggerName, this.catFactory);
                    eh.setLogger(logger);
                    continue;
                }
                if (tagName.equals(ROOT_REF)) {
                    Logger root = this.repository.getRootLogger();
                    eh.setLogger(root);
                    continue;
                }
                DOMConfigurator.quietParseUnrecognizedElement(eh, currentElement, this.props);
            }
            propSetter.activate();
            appender.setErrorHandler(eh);
        }
    }

    protected void parseFilters(Element element, Appender appender) {
        String clazz = this.subst(element.getAttribute(CLASS_ATTR));
        Filter filter = (Filter)OptionConverter.instantiateByClassName(clazz, Filter.class, null);
        if (filter != null) {
            PropertySetter propSetter = new PropertySetter(filter);
            NodeList children = element.getChildNodes();
            int length = children.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = children.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                String tagName = currentElement.getTagName();
                if (tagName.equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                DOMConfigurator.quietParseUnrecognizedElement(filter, currentElement, this.props);
            }
            propSetter.activate();
            LogLog.debug("Adding filter of type [" + filter.getClass() + "] to appender named [" + appender.getName() + "].");
            appender.addFilter(filter);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void parseCategory(Element loggerElement) {
        Logger cat;
        String catName = this.subst(loggerElement.getAttribute(NAME_ATTR));
        String className = this.subst(loggerElement.getAttribute(CLASS_ATTR));
        if (EMPTY_STR.equals(className)) {
            LogLog.debug("Retreiving an instance of org.apache.log4j.Logger.");
            cat = this.catFactory == null ? this.repository.getLogger(catName) : this.repository.getLogger(catName, this.catFactory);
        } else {
            LogLog.debug("Desired logger sub-class: [" + className + ']');
            try {
                Class clazz = Loader.loadClass(className);
                Method getInstanceMethod = clazz.getMethod("getLogger", ONE_STRING_PARAM);
                cat = (Logger)getInstanceMethod.invoke(null, catName);
            } catch (InvocationTargetException oops) {
                if (oops.getTargetException() instanceof InterruptedException || oops.getTargetException() instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("Could not retrieve category [" + catName + "]. Reported error follows.", oops);
                return;
            } catch (Exception oops) {
                LogLog.error("Could not retrieve category [" + catName + "]. Reported error follows.", oops);
                return;
            }
        }
        Logger logger = cat;
        synchronized (logger) {
            boolean additivity = OptionConverter.toBoolean(this.subst(loggerElement.getAttribute(ADDITIVITY_ATTR)), true);
            LogLog.debug("Setting [" + cat.getName() + "] additivity to [" + additivity + "].");
            cat.setAdditivity(additivity);
            this.parseChildrenOfLoggerElement(loggerElement, cat, false);
        }
    }

    protected void parseCategoryFactory(Element factoryElement) {
        String className = this.subst(factoryElement.getAttribute(CLASS_ATTR));
        if (EMPTY_STR.equals(className)) {
            LogLog.error("Category Factory tag class attribute not found.");
            LogLog.debug("No Category Factory configured.");
        } else {
            LogLog.debug("Desired category factory: [" + className + ']');
            Object factory = OptionConverter.instantiateByClassName(className, LoggerFactory.class, null);
            if (factory instanceof LoggerFactory) {
                this.catFactory = (LoggerFactory)factory;
            } else {
                LogLog.error("Category Factory class " + className + " does not implement org.apache.log4j.LoggerFactory");
            }
            PropertySetter propSetter = new PropertySetter(factory);
            Element currentElement = null;
            Node currentNode = null;
            NodeList children = factoryElement.getChildNodes();
            int length = children.getLength();
            for (int loop = 0; loop < length; ++loop) {
                currentNode = children.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                currentElement = (Element)currentNode;
                if (currentElement.getTagName().equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                DOMConfigurator.quietParseUnrecognizedElement(factory, currentElement, this.props);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void parseRoot(Element rootElement) {
        Logger root;
        Logger logger = root = this.repository.getRootLogger();
        synchronized (logger) {
            this.parseChildrenOfLoggerElement(rootElement, root, true);
        }
    }

    protected void parseChildrenOfLoggerElement(Element catElement, Logger cat, boolean isRoot) {
        PropertySetter propSetter = new PropertySetter(cat);
        cat.removeAllAppenders();
        NodeList children = catElement.getChildNodes();
        int length = children.getLength();
        for (int loop = 0; loop < length; ++loop) {
            Node currentNode = children.item(loop);
            if (currentNode.getNodeType() != 1) continue;
            Element currentElement = (Element)currentNode;
            String tagName = currentElement.getTagName();
            if (tagName.equals(APPENDER_REF_TAG)) {
                Element appenderRef = (Element)currentNode;
                Appender appender = this.findAppenderByReference(appenderRef);
                String refName = this.subst(appenderRef.getAttribute(REF_ATTR));
                if (appender != null) {
                    LogLog.debug("Adding appender named [" + refName + "] to category [" + cat.getName() + "].");
                } else {
                    LogLog.debug("Appender named [" + refName + "] not found.");
                }
                cat.addAppender(appender);
                continue;
            }
            if (tagName.equals(LEVEL_TAG)) {
                this.parseLevel(currentElement, cat, isRoot);
                continue;
            }
            if (tagName.equals(PRIORITY_TAG)) {
                this.parseLevel(currentElement, cat, isRoot);
                continue;
            }
            if (tagName.equals(PARAM_TAG)) {
                this.setParameter(currentElement, propSetter);
                continue;
            }
            DOMConfigurator.quietParseUnrecognizedElement(cat, currentElement, this.props);
        }
        propSetter.activate();
    }

    protected Layout parseLayout(Element layout_element) {
        String className = this.subst(layout_element.getAttribute(CLASS_ATTR));
        LogLog.debug("Parsing layout of class: \"" + className + "\"");
        try {
            Object instance = Loader.loadClass(className).newInstance();
            Layout layout = (Layout)instance;
            PropertySetter propSetter = new PropertySetter(layout);
            NodeList params = layout_element.getChildNodes();
            int length = params.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = params.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                String tagName = currentElement.getTagName();
                if (tagName.equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                DOMConfigurator.parseUnrecognizedElement(instance, currentElement, this.props);
            }
            propSetter.activate();
            return layout;
        } catch (Exception oops) {
            if (oops instanceof InterruptedException || oops instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create the Layout. Reported error follows.", oops);
            return null;
        }
    }

    protected void parseRenderer(Element element) {
        String renderingClass = this.subst(element.getAttribute(RENDERING_CLASS_ATTR));
        String renderedClass = this.subst(element.getAttribute(RENDERED_CLASS_ATTR));
        if (this.repository instanceof RendererSupport) {
            RendererMap.addRenderer((RendererSupport)((Object)this.repository), renderedClass, renderingClass);
        }
    }

    protected ThrowableRenderer parseThrowableRenderer(Element element) {
        String className = this.subst(element.getAttribute(CLASS_ATTR));
        LogLog.debug("Parsing throwableRenderer of class: \"" + className + "\"");
        try {
            Object instance = Loader.loadClass(className).newInstance();
            ThrowableRenderer tr = (ThrowableRenderer)instance;
            PropertySetter propSetter = new PropertySetter(tr);
            NodeList params = element.getChildNodes();
            int length = params.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = params.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                String tagName = currentElement.getTagName();
                if (tagName.equals(PARAM_TAG)) {
                    this.setParameter(currentElement, propSetter);
                    continue;
                }
                DOMConfigurator.parseUnrecognizedElement(instance, currentElement, this.props);
            }
            propSetter.activate();
            return tr;
        } catch (Exception oops) {
            if (oops instanceof InterruptedException || oops instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create the ThrowableRenderer. Reported error follows.", oops);
            return null;
        }
    }

    protected void parseLevel(Element element, Logger logger, boolean isRoot) {
        String catName = logger.getName();
        if (isRoot) {
            catName = ROOT_TAG;
        }
        String priStr = this.subst(element.getAttribute(VALUE_ATTR));
        LogLog.debug("Level value for " + catName + " is  [" + priStr + "].");
        if ("inherited".equalsIgnoreCase(priStr) || "null".equalsIgnoreCase(priStr)) {
            if (isRoot) {
                LogLog.error("Root level cannot be inherited. Ignoring directive.");
            } else {
                logger.setLevel(null);
            }
        } else {
            String className = this.subst(element.getAttribute(CLASS_ATTR));
            if (EMPTY_STR.equals(className)) {
                logger.setLevel(OptionConverter.toLevel(priStr, Level.DEBUG));
            } else {
                LogLog.debug("Desired Level sub-class: [" + className + ']');
                try {
                    Class clazz = Loader.loadClass(className);
                    Method toLevelMethod = clazz.getMethod("toLevel", ONE_STRING_PARAM);
                    Level pri = (Level)toLevelMethod.invoke(null, priStr);
                    logger.setLevel(pri);
                } catch (Exception oops) {
                    if (oops instanceof InterruptedException || oops instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.error("Could not create level [" + priStr + "]. Reported error follows.", oops);
                    return;
                }
            }
        }
        LogLog.debug(catName + " level set to " + logger.getLevel());
    }

    protected void setParameter(Element elem, PropertySetter propSetter) {
        String name = this.subst(elem.getAttribute(NAME_ATTR));
        String value = elem.getAttribute(VALUE_ATTR);
        value = this.subst(OptionConverter.convertSpecialChars(value));
        propSetter.setProperty(name, value);
    }

    public static void configure(Element element) {
        DOMConfigurator configurator = new DOMConfigurator();
        configurator.doConfigure(element, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String configFilename) {
        DOMConfigurator.configureAndWatch(configFilename, 60000L);
    }

    public static void configureAndWatch(String configFilename, long delay) {
        XMLWatchdog xdog = new XMLWatchdog(configFilename);
        xdog.setDelay(delay);
        xdog.start();
    }

    public void doConfigure(final String filename, LoggerRepository repository) {
        ParseAction action = new ParseAction(){

            public Document parse(DocumentBuilder parser) throws SAXException, IOException {
                return parser.parse(new File(filename));
            }

            public String toString() {
                return "file [" + filename + "]";
            }
        };
        this.doConfigure(action, repository);
    }

    public void doConfigure(final URL url, LoggerRepository repository) {
        ParseAction action = new ParseAction(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public Document parse(DocumentBuilder parser) throws SAXException, IOException {
                URLConnection uConn = url.openConnection();
                uConn.setUseCaches(false);
                InputStream stream = uConn.getInputStream();
                try {
                    InputSource src = new InputSource(stream);
                    src.setSystemId(url.toString());
                    Document document = parser.parse(src);
                    return document;
                } finally {
                    stream.close();
                }
            }

            public String toString() {
                return "url [" + url.toString() + "]";
            }
        };
        this.doConfigure(action, repository);
    }

    public void doConfigure(final InputStream inputStream, LoggerRepository repository) throws FactoryConfigurationError {
        ParseAction action = new ParseAction(){

            public Document parse(DocumentBuilder parser) throws SAXException, IOException {
                InputSource inputSource = new InputSource(inputStream);
                inputSource.setSystemId("dummy://log4j.dtd");
                return parser.parse(inputSource);
            }

            public String toString() {
                return "input stream [" + inputStream.toString() + "]";
            }
        };
        this.doConfigure(action, repository);
    }

    public void doConfigure(final Reader reader, LoggerRepository repository) throws FactoryConfigurationError {
        ParseAction action = new ParseAction(){

            public Document parse(DocumentBuilder parser) throws SAXException, IOException {
                InputSource inputSource = new InputSource(reader);
                inputSource.setSystemId("dummy://log4j.dtd");
                return parser.parse(inputSource);
            }

            public String toString() {
                return "reader [" + reader.toString() + "]";
            }
        };
        this.doConfigure(action, repository);
    }

    protected void doConfigure(final InputSource inputSource, LoggerRepository repository) throws FactoryConfigurationError {
        if (inputSource.getSystemId() == null) {
            inputSource.setSystemId("dummy://log4j.dtd");
        }
        ParseAction action = new ParseAction(){

            public Document parse(DocumentBuilder parser) throws SAXException, IOException {
                return parser.parse(inputSource);
            }

            public String toString() {
                return "input source [" + inputSource.toString() + "]";
            }
        };
        this.doConfigure(action, repository);
    }

    private final void doConfigure(ParseAction action, LoggerRepository repository) throws FactoryConfigurationError {
        DocumentBuilderFactory dbf = null;
        this.repository = repository;
        try {
            LogLog.debug("System property is :" + OptionConverter.getSystemProperty(dbfKey, null));
            dbf = DocumentBuilderFactory.newInstance();
            LogLog.debug("Standard DocumentBuilderFactory search succeded.");
            LogLog.debug("DocumentBuilderFactory is: " + dbf.getClass().getName());
        } catch (FactoryConfigurationError fce) {
            Exception e = fce.getException();
            LogLog.debug("Could not instantiate a DocumentBuilderFactory.", e);
            throw fce;
        }
        try {
            dbf.setValidating(true);
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            docBuilder.setErrorHandler(new SAXErrorHandler());
            docBuilder.setEntityResolver(new Log4jEntityResolver());
            Document doc = action.parse(docBuilder);
            this.parse(doc.getDocumentElement());
        } catch (Exception e) {
            if (e instanceof InterruptedException || e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not parse " + action.toString() + ".", e);
        }
    }

    public void doConfigure(Element element, LoggerRepository repository) {
        this.repository = repository;
        this.parse(element);
    }

    public static void configure(String filename) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(filename, LogManager.getLoggerRepository());
    }

    public static void configure(URL url) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }

    protected void parse(Element element) {
        int loop;
        String confDebug;
        String rootElementName = element.getTagName();
        if (!rootElementName.equals(CONFIGURATION_TAG)) {
            if (rootElementName.equals(OLD_CONFIGURATION_TAG)) {
                LogLog.warn("The <configuration> element has been deprecated.");
                LogLog.warn("Use the <log4j:configuration> element instead.");
            } else {
                LogLog.error("DOM element is - not a <log4j:configuration> element.");
                return;
            }
        }
        String debugAttrib = this.subst(element.getAttribute(INTERNAL_DEBUG_ATTR));
        LogLog.debug("debug attribute= \"" + debugAttrib + "\".");
        if (!debugAttrib.equals(EMPTY_STR) && !debugAttrib.equals("null")) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(debugAttrib, true));
        } else {
            LogLog.debug("Ignoring debug attribute.");
        }
        String resetAttrib = this.subst(element.getAttribute(RESET_ATTR));
        LogLog.debug("reset attribute= \"" + resetAttrib + "\".");
        if (!EMPTY_STR.equals(resetAttrib) && OptionConverter.toBoolean(resetAttrib, false)) {
            this.repository.resetConfiguration();
        }
        if (!(confDebug = this.subst(element.getAttribute(CONFIG_DEBUG_ATTR))).equals(EMPTY_STR) && !confDebug.equals("null")) {
            LogLog.warn("The \"configDebug\" attribute is deprecated.");
            LogLog.warn("Use the \"debug\" attribute instead.");
            LogLog.setInternalDebugging(OptionConverter.toBoolean(confDebug, true));
        }
        String thresholdStr = this.subst(element.getAttribute(THRESHOLD_ATTR));
        LogLog.debug("Threshold =\"" + thresholdStr + "\".");
        if (!EMPTY_STR.equals(thresholdStr) && !"null".equals(thresholdStr)) {
            this.repository.setThreshold(thresholdStr);
        }
        String tagName = null;
        Element currentElement = null;
        Node currentNode = null;
        NodeList children = element.getChildNodes();
        int length = children.getLength();
        for (loop = 0; loop < length; ++loop) {
            currentNode = children.item(loop);
            if (currentNode.getNodeType() != 1 || !(tagName = (currentElement = (Element)currentNode).getTagName()).equals(CATEGORY_FACTORY_TAG) && !tagName.equals(LOGGER_FACTORY_TAG)) continue;
            this.parseCategoryFactory(currentElement);
        }
        for (loop = 0; loop < length; ++loop) {
            currentNode = children.item(loop);
            if (currentNode.getNodeType() != 1) continue;
            currentElement = (Element)currentNode;
            tagName = currentElement.getTagName();
            if (tagName.equals(CATEGORY) || tagName.equals(LOGGER)) {
                this.parseCategory(currentElement);
                continue;
            }
            if (tagName.equals(ROOT_TAG)) {
                this.parseRoot(currentElement);
                continue;
            }
            if (tagName.equals(RENDERER_TAG)) {
                this.parseRenderer(currentElement);
                continue;
            }
            if (tagName.equals(THROWABLE_RENDERER_TAG)) {
                ThrowableRenderer tr;
                if (!(this.repository instanceof ThrowableRendererSupport) || (tr = this.parseThrowableRenderer(currentElement)) == null) continue;
                ((ThrowableRendererSupport)((Object)this.repository)).setThrowableRenderer(tr);
                continue;
            }
            if (tagName.equals(APPENDER_TAG) || tagName.equals(CATEGORY_FACTORY_TAG) || tagName.equals(LOGGER_FACTORY_TAG)) continue;
            DOMConfigurator.quietParseUnrecognizedElement(this.repository, currentElement, this.props);
        }
    }

    protected String subst(String value) {
        return DOMConfigurator.subst(value, this.props);
    }

    public static String subst(String value, Properties props) {
        try {
            return OptionConverter.substVars(value, props);
        } catch (IllegalArgumentException e) {
            LogLog.warn("Could not perform variable substitution.", e);
            return value;
        }
    }

    public static void setParameter(Element elem, PropertySetter propSetter, Properties props) {
        String name = DOMConfigurator.subst(elem.getAttribute(NAME_ATTR), props);
        String value = elem.getAttribute(VALUE_ATTR);
        value = DOMConfigurator.subst(OptionConverter.convertSpecialChars(value), props);
        propSetter.setProperty(name, value);
    }

    public static Object parseElement(Element element, Properties props, Class expectedClass) throws Exception {
        String clazz = DOMConfigurator.subst(element.getAttribute(CLASS_ATTR), props);
        Object instance = OptionConverter.instantiateByClassName(clazz, expectedClass, null);
        if (instance != null) {
            PropertySetter propSetter = new PropertySetter(instance);
            NodeList children = element.getChildNodes();
            int length = children.getLength();
            for (int loop = 0; loop < length; ++loop) {
                Node currentNode = children.item(loop);
                if (currentNode.getNodeType() != 1) continue;
                Element currentElement = (Element)currentNode;
                String tagName = currentElement.getTagName();
                if (tagName.equals(PARAM_TAG)) {
                    DOMConfigurator.setParameter(currentElement, propSetter, props);
                    continue;
                }
                DOMConfigurator.parseUnrecognizedElement(instance, currentElement, props);
            }
            return instance;
        }
        return null;
    }

    private static interface ParseAction {
        public Document parse(DocumentBuilder var1) throws SAXException, IOException;
    }
}

