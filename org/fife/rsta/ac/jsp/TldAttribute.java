/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.jsp;

import org.fife.rsta.ac.html.AttributeCompletion;
import org.fife.rsta.ac.jsp.JspCompletionProvider;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

class TldAttribute
extends AttributeCompletion {
    public boolean required;
    public boolean rtexprvalue;

    public TldAttribute(JspCompletionProvider provider, TldAttributeParam param) {
        super((CompletionProvider)provider, param);
    }

    public static class TldAttributeParam
    extends ParameterizedCompletion.Parameter {
        private boolean required;
        private boolean rtextprvalue;

        public TldAttributeParam(Object type, String name, boolean required, boolean rtextprvalue) {
            super(type, name);
            this.required = required;
            this.rtextprvalue = rtextprvalue;
        }

        public boolean isRequired() {
            return this.required;
        }

        public boolean getRtextprvalue() {
            return this.rtextprvalue;
        }
    }
}

