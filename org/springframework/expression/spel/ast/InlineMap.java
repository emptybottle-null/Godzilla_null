/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.ast.InlineList;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class InlineMap
extends SpelNodeImpl {
    @Nullable
    private TypedValue constant;

    public InlineMap(int startPos, int endPos, SpelNodeImpl ... args) {
        super(startPos, endPos, args);
        this.checkIfConstant();
    }

    private void checkIfConstant() {
        boolean isConstant = true;
        int max = this.getChildCount();
        for (int c = 0; c < max; ++c) {
            SpelNode child = this.getChild(c);
            if (child instanceof Literal) continue;
            if (child instanceof InlineList) {
                InlineList inlineList = (InlineList)child;
                if (inlineList.isConstant()) continue;
                isConstant = false;
                break;
            }
            if (child instanceof InlineMap) {
                InlineMap inlineMap = (InlineMap)child;
                if (inlineMap.isConstant()) continue;
                isConstant = false;
                break;
            }
            if (c % 2 == 0 && child instanceof PropertyOrFieldReference) continue;
            isConstant = false;
            break;
        }
        if (isConstant) {
            LinkedHashMap<Object, List<Object>> constantMap = new LinkedHashMap<Object, List<Object>>();
            int childCount = this.getChildCount();
            for (int c = 0; c < childCount; ++c) {
                SpelNode keyChild = this.getChild(c++);
                SpelNode valueChild = this.getChild(c);
                Object key = null;
                Object value = null;
                if (keyChild instanceof Literal) {
                    key = ((Literal)keyChild).getLiteralValue().getValue();
                } else if (keyChild instanceof PropertyOrFieldReference) {
                    key = ((PropertyOrFieldReference)keyChild).getName();
                } else {
                    return;
                }
                if (valueChild instanceof Literal) {
                    value = ((Literal)valueChild).getLiteralValue().getValue();
                } else if (valueChild instanceof InlineList) {
                    value = ((InlineList)valueChild).getConstantValue();
                } else if (valueChild instanceof InlineMap) {
                    value = ((InlineMap)valueChild).getConstantValue();
                }
                constantMap.put(key, (List<Object>)value);
            }
            this.constant = new TypedValue(Collections.unmodifiableMap(constantMap));
        }
    }

    @Override
    public TypedValue getValueInternal(ExpressionState expressionState) throws EvaluationException {
        if (this.constant != null) {
            return this.constant;
        }
        LinkedHashMap<Object, Object> returnValue = new LinkedHashMap<Object, Object>();
        int childcount = this.getChildCount();
        for (int c = 0; c < childcount; ++c) {
            SpelNode keyChild = this.getChild(c++);
            Object key = null;
            if (keyChild instanceof PropertyOrFieldReference) {
                PropertyOrFieldReference reference = (PropertyOrFieldReference)keyChild;
                key = reference.getName();
            } else {
                key = keyChild.getValue(expressionState);
            }
            Object value = this.getChild(c).getValue(expressionState);
            returnValue.put(key, value);
        }
        return new TypedValue(returnValue);
    }

    @Override
    public String toStringAST() {
        StringBuilder sb = new StringBuilder("{");
        int count = this.getChildCount();
        for (int c = 0; c < count; ++c) {
            if (c > 0) {
                sb.append(',');
            }
            sb.append(this.getChild(c++).toStringAST());
            sb.append(':');
            sb.append(this.getChild(c).toStringAST());
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean isConstant() {
        return this.constant != null;
    }

    @Nullable
    public Map<Object, Object> getConstantValue() {
        Assert.state(this.constant != null, "No constant");
        return (Map)this.constant.getValue();
    }
}

