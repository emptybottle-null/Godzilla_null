/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.ParameterizedCompletionInsertionInfo;

public interface ParameterizedCompletion
extends Completion {
    public String getDefinitionString();

    public Parameter getParam(int var1);

    public int getParamCount();

    public ParameterizedCompletionInsertionInfo getInsertionInfo(JTextComponent var1, boolean var2);

    public boolean getShowParameterToolTip();

    public static class Parameter {
        private String name;
        private Object type;
        private String desc;
        private boolean isEndParam;

        public Parameter(Object type, String name) {
            this(type, name, false);
        }

        public Parameter(Object type, String name, boolean endParam) {
            this.name = name;
            this.type = type;
            this.isEndParam = endParam;
        }

        public String getDescription() {
            return this.desc;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type == null ? null : this.type.toString();
        }

        public Object getTypeObject() {
            return this.type;
        }

        public boolean isEndParam() {
            return this.isEndParam;
        }

        public void setDescription(String desc) {
            this.desc = desc;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.getType() != null) {
                sb.append(this.getType());
            }
            if (this.getName() != null) {
                if (this.getType() != null) {
                    sb.append(' ');
                }
                sb.append(this.getName());
            }
            return sb.toString();
        }
    }
}

