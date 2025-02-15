/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.rewrite;

import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.rewrite.RewritePolicy;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.UnrecognizedElementHandler;
import org.w3c.dom.Element;

public class RewriteAppender
extends AppenderSkeleton
implements AppenderAttachable,
UnrecognizedElementHandler {
    private RewritePolicy policy;
    private final AppenderAttachableImpl appenders = new AppenderAttachableImpl();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void append(LoggingEvent event) {
        LoggingEvent rewritten = event;
        if (this.policy != null) {
            rewritten = this.policy.rewrite(event);
        }
        if (rewritten != null) {
            AppenderAttachableImpl appenderAttachableImpl = this.appenders;
            synchronized (appenderAttachableImpl) {
                this.appenders.appendLoopOnAppenders(rewritten);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addAppender(Appender newAppender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.addAppender(newAppender);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Enumeration getAllAppenders() {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.getAllAppenders();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Appender getAppender(String name) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.getAppender(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() {
        this.closed = true;
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            Enumeration iter = this.appenders.getAllAppenders();
            if (iter != null) {
                while (iter.hasMoreElements()) {
                    Object next = iter.nextElement();
                    if (!(next instanceof Appender)) continue;
                    ((Appender)next).close();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isAttached(Appender appender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            return this.appenders.isAttached(appender);
        }
    }

    public boolean requiresLayout() {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAllAppenders() {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAllAppenders();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAppender(Appender appender) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAppender(appender);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeAppender(String name) {
        AppenderAttachableImpl appenderAttachableImpl = this.appenders;
        synchronized (appenderAttachableImpl) {
            this.appenders.removeAppender(name);
        }
    }

    public void setRewritePolicy(RewritePolicy rewritePolicy) {
        this.policy = rewritePolicy;
    }

    public boolean parseUnrecognizedElement(Element element, Properties props) throws Exception {
        String nodeName = element.getNodeName();
        if ("rewritePolicy".equals(nodeName)) {
            Object rewritePolicy = DOMConfigurator.parseElement(element, props, RewritePolicy.class);
            if (rewritePolicy != null) {
                if (rewritePolicy instanceof OptionHandler) {
                    ((OptionHandler)rewritePolicy).activateOptions();
                }
                this.setRewritePolicy((RewritePolicy)rewritePolicy);
            }
            return true;
        }
        return false;
    }
}

