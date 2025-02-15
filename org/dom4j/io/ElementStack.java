/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.DispatchHandler;

class ElementStack
implements ElementPath {
    protected Element[] stack;
    protected int lastElementIndex = -1;
    private DispatchHandler handler = null;

    public ElementStack() {
        this(50);
    }

    public ElementStack(int defaultCapacity) {
        this.stack = new Element[defaultCapacity];
    }

    public void setDispatchHandler(DispatchHandler dispatchHandler) {
        this.handler = dispatchHandler;
    }

    public DispatchHandler getDispatchHandler() {
        return this.handler;
    }

    public void clear() {
        this.lastElementIndex = -1;
    }

    public Element peekElement() {
        if (this.lastElementIndex < 0) {
            return null;
        }
        return this.stack[this.lastElementIndex];
    }

    public Element popElement() {
        if (this.lastElementIndex < 0) {
            return null;
        }
        return this.stack[this.lastElementIndex--];
    }

    public void pushElement(Element element) {
        int length = this.stack.length;
        if (++this.lastElementIndex >= length) {
            this.reallocate(length * 2);
        }
        this.stack[this.lastElementIndex] = element;
    }

    protected void reallocate(int size) {
        Element[] oldStack = this.stack;
        this.stack = new Element[size];
        System.arraycopy(oldStack, 0, this.stack, 0, oldStack.length);
    }

    @Override
    public int size() {
        return this.lastElementIndex + 1;
    }

    @Override
    public Element getElement(int depth) {
        Element element;
        try {
            element = this.stack[depth];
        } catch (ArrayIndexOutOfBoundsException e) {
            element = null;
        }
        return element;
    }

    @Override
    public String getPath() {
        if (this.handler == null) {
            this.setDispatchHandler(new DispatchHandler());
        }
        return this.handler.getPath();
    }

    @Override
    public Element getCurrent() {
        return this.peekElement();
    }

    @Override
    public void addHandler(String path, ElementHandler elementHandler) {
        this.handler.addHandler(this.getHandlerPath(path), elementHandler);
    }

    @Override
    public void removeHandler(String path) {
        this.handler.removeHandler(this.getHandlerPath(path));
    }

    public boolean containsHandler(String path) {
        return this.handler.containsHandler(path);
    }

    private String getHandlerPath(String path) {
        if (this.handler == null) {
            this.setDispatchHandler(new DispatchHandler());
        }
        String handlerPath = path.startsWith("/") ? path : (this.getPath().equals("/") ? this.getPath() + path : this.getPath() + "/" + path);
        return handlerPath;
    }
}

