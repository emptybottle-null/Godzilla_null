/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.util.Vector;
import org.apache.log4j.Logger;

class ProvisionNode
extends Vector {
    private static final long serialVersionUID = -4479121426311014469L;

    ProvisionNode(Logger logger) {
        this.addElement(logger);
    }
}

