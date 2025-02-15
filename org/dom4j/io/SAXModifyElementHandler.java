/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.ElementModifier;
import org.dom4j.io.ElementStack;
import org.dom4j.io.SAXModifyException;

class SAXModifyElementHandler
implements ElementHandler {
    private ElementModifier elemModifier;
    private Element modifiedElement;

    public SAXModifyElementHandler(ElementModifier elemModifier) {
        this.elemModifier = elemModifier;
    }

    @Override
    public void onStart(ElementPath elementPath) {
        this.modifiedElement = elementPath.getCurrent();
    }

    @Override
    public void onEnd(ElementPath elementPath) {
        try {
            Element clonedElem;
            Element origElement = elementPath.getCurrent();
            Element currentParent = origElement.getParent();
            if (currentParent != null) {
                clonedElem = (Element)origElement.clone();
                this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
                if (this.modifiedElement != null) {
                    this.modifiedElement.setParent(origElement.getParent());
                    this.modifiedElement.setDocument(origElement.getDocument());
                    int contentIndex = currentParent.indexOf(origElement);
                    currentParent.content().set(contentIndex, this.modifiedElement);
                }
                origElement.detach();
            } else if (origElement.isRootElement()) {
                clonedElem = (Element)origElement.clone();
                this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
                if (this.modifiedElement != null) {
                    this.modifiedElement.setDocument(origElement.getDocument());
                    Document doc = origElement.getDocument();
                    doc.setRootElement(this.modifiedElement);
                }
                origElement.detach();
            }
            if (elementPath instanceof ElementStack) {
                ElementStack elementStack = (ElementStack)elementPath;
                elementStack.popElement();
                elementStack.pushElement(this.modifiedElement);
            }
        } catch (Exception ex) {
            throw new SAXModifyException(ex);
        }
    }

    protected Element getModifiedElement() {
        return this.modifiedElement;
    }
}

