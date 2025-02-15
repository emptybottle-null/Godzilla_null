/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

public class LwInspectionSuppression {
    public static final LwInspectionSuppression[] EMPTY_ARRAY = new LwInspectionSuppression[0];
    private String myInspectionId;
    private String myComponentId;

    public LwInspectionSuppression(String inspectionId, String componentId) {
        this.myInspectionId = inspectionId;
        this.myComponentId = componentId;
    }

    public String getInspectionId() {
        return this.myInspectionId;
    }

    public String getComponentId() {
        return this.myComponentId;
    }
}

