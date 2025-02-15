/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.util.ArrayList;
import java.util.HashMap;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

class DispatchHandler
implements ElementHandler {
    private boolean atRoot = true;
    private String path = "/";
    private ArrayList<String> pathStack = new ArrayList();
    private ArrayList<ElementHandler> handlerStack = new ArrayList();
    private HashMap<String, ElementHandler> handlers = new HashMap();
    private ElementHandler defaultHandler;

    public void addHandler(String handlerPath, ElementHandler handler) {
        this.handlers.put(handlerPath, handler);
    }

    public ElementHandler removeHandler(String handlerPath) {
        return this.handlers.remove(handlerPath);
    }

    public boolean containsHandler(String handlerPath) {
        return this.handlers.containsKey(handlerPath);
    }

    public ElementHandler getHandler(String handlerPath) {
        return this.handlers.get(handlerPath);
    }

    public int getActiveHandlerCount() {
        return this.handlerStack.size();
    }

    public void setDefaultHandler(ElementHandler handler) {
        this.defaultHandler = handler;
    }

    public void resetHandlers() {
        this.atRoot = true;
        this.path = "/";
        this.pathStack.clear();
        this.handlerStack.clear();
        this.handlers.clear();
        this.defaultHandler = null;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public void onStart(ElementPath elementPath) {
        Element element = elementPath.getCurrent();
        this.pathStack.add(this.path);
        if (this.atRoot) {
            this.path = this.path + element.getName();
            this.atRoot = false;
        } else {
            this.path = this.path + "/" + element.getName();
        }
        if (this.handlers != null && this.handlers.containsKey(this.path)) {
            ElementHandler handler = this.handlers.get(this.path);
            this.handlerStack.add(handler);
            handler.onStart(elementPath);
        } else if (this.handlerStack.isEmpty() && this.defaultHandler != null) {
            this.defaultHandler.onStart(elementPath);
        }
    }

    @Override
    public void onEnd(ElementPath elementPath) {
        if (this.handlers != null && this.handlers.containsKey(this.path)) {
            ElementHandler handler = this.handlers.get(this.path);
            this.handlerStack.remove(this.handlerStack.size() - 1);
            handler.onEnd(elementPath);
        } else if (this.handlerStack.isEmpty() && this.defaultHandler != null) {
            this.defaultHandler.onEnd(elementPath);
        }
        this.path = this.pathStack.remove(this.pathStack.size() - 1);
        if (this.pathStack.size() == 0) {
            this.atRoot = true;
        }
    }
}

