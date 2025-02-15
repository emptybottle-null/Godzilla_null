/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import org.dom4j.Branch;
import org.dom4j.Element;
import org.dom4j.Node;

public class IllegalAddException
extends IllegalArgumentException {
    public IllegalAddException(String reason) {
        super(reason);
    }

    public IllegalAddException(Element parent, Node node, String reason) {
        super("The node \"" + node.toString() + "\" could not be added to the element \"" + parent.getQualifiedName() + "\" because: " + reason);
    }

    public IllegalAddException(Branch parent, Node node, String reason) {
        super("The node \"" + node.toString() + "\" could not be added to the branch \"" + parent.getName() + "\" because: " + reason);
    }
}

