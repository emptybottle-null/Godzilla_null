/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;

public class FormLayoutUtils {
    private FormLayoutUtils() {
    }

    public static String getEncodedRowSpecs(FormLayout formLayout) {
        StringBuffer result = new StringBuffer();
        for (int i = 1; i <= formLayout.getRowCount(); ++i) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append(FormLayoutUtils.getEncodedSpec(formLayout.getRowSpec(i)));
        }
        return result.toString();
    }

    public static String getEncodedColumnSpecs(FormLayout formLayout) {
        StringBuffer result = new StringBuffer();
        for (int i = 1; i <= formLayout.getColumnCount(); ++i) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append(FormLayoutUtils.getEncodedSpec(formLayout.getColumnSpec(i)));
        }
        return result.toString();
    }

    public static String getEncodedSpec(FormSpec formSpec) {
        String result = formSpec.toString();
        while (true) {
            int pos;
            if ((pos = result.indexOf("dluX")) < 0) {
                pos = result.indexOf("dluY");
            }
            if (pos < 0) break;
            result = result.substring(0, pos + 3) + result.substring(pos + 4);
        }
        return result;
    }
}

