/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MutationEvent;

public class SignatureMarshalListener
implements EventListener,
SignatureConfig.SignatureConfigurable {
    ThreadLocal<EventTarget> target = new ThreadLocal();
    SignatureConfig signatureConfig;

    public void setEventTarget(EventTarget target) {
        this.target.set(target);
    }

    @Override
    public void handleEvent(Event e) {
        if (!(e instanceof MutationEvent)) {
            return;
        }
        MutationEvent mutEvt = (MutationEvent)e;
        EventTarget et = mutEvt.getTarget();
        if (!(et instanceof Element)) {
            return;
        }
        this.handleElement((Element)((Object)et));
    }

    public void handleElement(Element el) {
        String parentNS;
        EventTarget target = this.target.get();
        if (el.hasAttribute("Id")) {
            el.setIdAttribute("Id", true);
        }
        SignatureMarshalListener.setListener(target, this, false);
        if ("http://schemas.openxmlformats.org/package/2006/digital-signature".equals(el.getNamespaceURI()) && !"http://schemas.openxmlformats.org/package/2006/digital-signature".equals(parentNS = el.getParentNode().getNamespaceURI()) && !el.hasAttributeNS("http://www.w3.org/2000/xmlns/", "mdssi")) {
            el.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:mdssi", "http://schemas.openxmlformats.org/package/2006/digital-signature");
        }
        this.setPrefix(el);
        SignatureMarshalListener.setListener(target, this, true);
    }

    public static void setListener(EventTarget target, EventListener listener, boolean enabled) {
        String type = "DOMSubtreeModified";
        boolean useCapture = false;
        if (enabled) {
            target.addEventListener(type, listener, useCapture);
        } else {
            target.removeEventListener(type, listener, useCapture);
        }
    }

    protected void setPrefix(Node el) {
        String prefix = this.signatureConfig.getNamespacePrefixes().get(el.getNamespaceURI());
        if (prefix != null && el.getPrefix() == null) {
            el.setPrefix(prefix);
        }
        NodeList nl = el.getChildNodes();
        for (int i = 0; i < nl.getLength(); ++i) {
            this.setPrefix(nl.item(i));
        }
    }

    @Override
    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }
}

