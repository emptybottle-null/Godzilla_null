/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.CategoryKey;
import org.apache.log4j.DefaultCategoryFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.ProvisionNode;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class Hierarchy
implements LoggerRepository,
RendererSupport,
ThrowableRendererSupport {
    private LoggerFactory defaultFactory;
    private Vector listeners;
    Hashtable ht = new Hashtable();
    Logger root;
    RendererMap rendererMap;
    int thresholdInt;
    Level threshold;
    boolean emittedNoAppenderWarning = false;
    boolean emittedNoResourceBundleWarning = false;
    private ThrowableRenderer throwableRenderer = null;

    public Hierarchy(Logger root) {
        this.listeners = new Vector(1);
        this.root = root;
        this.setThreshold(Level.ALL);
        this.root.setHierarchy(this);
        this.rendererMap = new RendererMap();
        this.defaultFactory = new DefaultCategoryFactory();
    }

    public void addRenderer(Class classToRender, ObjectRenderer or) {
        this.rendererMap.put(classToRender, or);
    }

    public void addHierarchyEventListener(HierarchyEventListener listener) {
        if (this.listeners.contains(listener)) {
            LogLog.warn("Ignoring attempt to add an existent listener.");
        } else {
            this.listeners.addElement(listener);
        }
    }

    public void clear() {
        this.ht.clear();
    }

    public void emitNoAppenderWarning(Category cat) {
        if (!this.emittedNoAppenderWarning) {
            LogLog.warn("No appenders could be found for logger (" + cat.getName() + ").");
            LogLog.warn("Please initialize the log4j system properly.");
            LogLog.warn("See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.");
            this.emittedNoAppenderWarning = true;
        }
    }

    public Logger exists(String name) {
        Object o = this.ht.get(new CategoryKey(name));
        if (o instanceof Logger) {
            return (Logger)o;
        }
        return null;
    }

    public void setThreshold(String levelStr) {
        Level l = Level.toLevel(levelStr, null);
        if (l != null) {
            this.setThreshold(l);
        } else {
            LogLog.warn("Could not convert [" + levelStr + "] to Level.");
        }
    }

    public void setThreshold(Level l) {
        if (l != null) {
            this.thresholdInt = l.level;
            this.threshold = l;
        }
    }

    public void fireAddAppenderEvent(Category logger, Appender appender) {
        if (this.listeners != null) {
            int size = this.listeners.size();
            for (int i = 0; i < size; ++i) {
                HierarchyEventListener listener = (HierarchyEventListener)this.listeners.elementAt(i);
                listener.addAppenderEvent(logger, appender);
            }
        }
    }

    void fireRemoveAppenderEvent(Category logger, Appender appender) {
        if (this.listeners != null) {
            int size = this.listeners.size();
            for (int i = 0; i < size; ++i) {
                HierarchyEventListener listener = (HierarchyEventListener)this.listeners.elementAt(i);
                listener.removeAppenderEvent(logger, appender);
            }
        }
    }

    public Level getThreshold() {
        return this.threshold;
    }

    public Logger getLogger(String name) {
        return this.getLogger(name, this.defaultFactory);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Logger getLogger(String name, LoggerFactory factory) {
        CategoryKey key = new CategoryKey(name);
        Hashtable hashtable = this.ht;
        synchronized (hashtable) {
            Object o = this.ht.get(key);
            if (o == null) {
                Logger logger = factory.makeNewLoggerInstance(name);
                logger.setHierarchy(this);
                this.ht.put(key, logger);
                this.updateParents(logger);
                return logger;
            }
            if (o instanceof Logger) {
                return (Logger)o;
            }
            if (o instanceof ProvisionNode) {
                Logger logger = factory.makeNewLoggerInstance(name);
                logger.setHierarchy(this);
                this.ht.put(key, logger);
                this.updateChildren((ProvisionNode)o, logger);
                this.updateParents(logger);
                return logger;
            }
            return null;
        }
    }

    public Enumeration getCurrentLoggers() {
        Vector v = new Vector(this.ht.size());
        Enumeration elems = this.ht.elements();
        while (elems.hasMoreElements()) {
            Object o = elems.nextElement();
            if (!(o instanceof Logger)) continue;
            v.addElement(o);
        }
        return v.elements();
    }

    public Enumeration getCurrentCategories() {
        return this.getCurrentLoggers();
    }

    public RendererMap getRendererMap() {
        return this.rendererMap;
    }

    public Logger getRootLogger() {
        return this.root;
    }

    public boolean isDisabled(int level) {
        return this.thresholdInt > level;
    }

    public void overrideAsNeeded(String override) {
        LogLog.warn("The Hiearchy.overrideAsNeeded method has been deprecated.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void resetConfiguration() {
        this.getRootLogger().setLevel(Level.DEBUG);
        this.root.setResourceBundle(null);
        this.setThreshold(Level.ALL);
        Hashtable hashtable = this.ht;
        synchronized (hashtable) {
            this.shutdown();
            Enumeration cats = this.getCurrentLoggers();
            while (cats.hasMoreElements()) {
                Logger c = (Logger)cats.nextElement();
                c.setLevel(null);
                c.setAdditivity(true);
                c.setResourceBundle(null);
            }
        }
        this.rendererMap.clear();
        this.throwableRenderer = null;
    }

    public void setDisableOverride(String override) {
        LogLog.warn("The Hiearchy.setDisableOverride method has been deprecated.");
    }

    public void setRenderer(Class renderedClass, ObjectRenderer renderer) {
        this.rendererMap.put(renderedClass, renderer);
    }

    public void setThrowableRenderer(ThrowableRenderer renderer) {
        this.throwableRenderer = renderer;
    }

    public ThrowableRenderer getThrowableRenderer() {
        return this.throwableRenderer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown() {
        Logger root = this.getRootLogger();
        root.closeNestedAppenders();
        Hashtable hashtable = this.ht;
        synchronized (hashtable) {
            Logger c;
            Enumeration cats = this.getCurrentLoggers();
            while (cats.hasMoreElements()) {
                c = (Logger)cats.nextElement();
                c.closeNestedAppenders();
            }
            root.removeAllAppenders();
            cats = this.getCurrentLoggers();
            while (cats.hasMoreElements()) {
                c = (Logger)cats.nextElement();
                c.removeAllAppenders();
            }
        }
    }

    private final void updateParents(Logger cat) {
        String name = cat.name;
        int length = name.length();
        boolean parentFound = false;
        int i = name.lastIndexOf(46, length - 1);
        while (i >= 0) {
            String substr = name.substring(0, i);
            CategoryKey key = new CategoryKey(substr);
            Object o = this.ht.get(key);
            if (o == null) {
                ProvisionNode pn = new ProvisionNode(cat);
                this.ht.put(key, pn);
            } else {
                if (o instanceof Category) {
                    parentFound = true;
                    cat.parent = (Category)o;
                    break;
                }
                if (o instanceof ProvisionNode) {
                    ((ProvisionNode)o).addElement(cat);
                } else {
                    IllegalStateException e = new IllegalStateException("unexpected object type " + o.getClass() + " in ht.");
                    e.printStackTrace();
                }
            }
            i = name.lastIndexOf(46, i - 1);
        }
        if (!parentFound) {
            cat.parent = this.root;
        }
    }

    private final void updateChildren(ProvisionNode pn, Logger logger) {
        int last = pn.size();
        for (int i = 0; i < last; ++i) {
            Logger l = (Logger)pn.elementAt(i);
            if (l.parent.name.startsWith(logger.name)) continue;
            logger.parent = l.parent;
            l.parent = logger;
        }
    }
}

