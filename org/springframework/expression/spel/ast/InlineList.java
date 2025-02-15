/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class InlineList
extends SpelNodeImpl {
    @Nullable
    private TypedValue constant;

    public InlineList(int startPos, int endPos, SpelNodeImpl ... args) {
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
                continue;
            }
            isConstant = false;
        }
        if (isConstant) {
            ArrayList<Object> constantList = new ArrayList<Object>();
            int childcount = this.getChildCount();
            for (int c = 0; c < childcount; ++c) {
                SpelNode child = this.getChild(c);
                if (child instanceof Literal) {
                    constantList.add(((Literal)child).getLiteralValue().getValue());
                    continue;
                }
                if (!(child instanceof InlineList)) continue;
                constantList.add(((InlineList)child).getConstantValue());
            }
            this.constant = new TypedValue(Collections.unmodifiableList(constantList));
        }
    }

    @Override
    public TypedValue getValueInternal(ExpressionState expressionState) throws EvaluationException {
        if (this.constant != null) {
            return this.constant;
        }
        int childCount = this.getChildCount();
        ArrayList<Object> returnValue = new ArrayList<Object>(childCount);
        for (int c = 0; c < childCount; ++c) {
            returnValue.add(this.getChild(c).getValue(expressionState));
        }
        return new TypedValue(returnValue);
    }

    @Override
    public String toStringAST() {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        int count = this.getChildCount();
        for (int c = 0; c < count; ++c) {
            sj.add(this.getChild(c).toStringAST());
        }
        return sj.toString();
    }

    public boolean isConstant() {
        return this.constant != null;
    }

    @Nullable
    public List<Object> getConstantValue() {
        Assert.state(this.constant != null, "No constant");
        return (List)this.constant.getValue();
    }

    @Override
    public boolean isCompilable() {
        return this.isConstant();
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow codeflow) {
        String constantFieldName = "inlineList$" + codeflow.nextFieldId();
        String className = codeflow.getClassName();
        codeflow.registerNewField((cw, cflow) -> cw.visitField(26, constantFieldName, "Ljava/util/List;", null, null));
        codeflow.registerNewClinit((mVisitor, cflow) -> this.generateClinitCode(className, constantFieldName, mVisitor, cflow, false));
        mv.visitFieldInsn(178, className, constantFieldName, "Ljava/util/List;");
        codeflow.pushDescriptor("Ljava/util/List");
    }

    void generateClinitCode(String clazzname, String constantFieldName, MethodVisitor mv, CodeFlow codeflow, boolean nested) {
        mv.visitTypeInsn(187, "java/util/ArrayList");
        mv.visitInsn(89);
        mv.visitMethodInsn(183, "java/util/ArrayList", "<init>", "()V", false);
        if (!nested) {
            mv.visitFieldInsn(179, clazzname, constantFieldName, "Ljava/util/List;");
        }
        int childCount = this.getChildCount();
        for (int c = 0; c < childCount; ++c) {
            if (!nested) {
                mv.visitFieldInsn(178, clazzname, constantFieldName, "Ljava/util/List;");
            } else {
                mv.visitInsn(89);
            }
            if (this.children[c] instanceof InlineList) {
                ((InlineList)this.children[c]).generateClinitCode(clazzname, constantFieldName, mv, codeflow, true);
            } else {
                this.children[c].generateCode(mv, codeflow);
                String lastDesc = codeflow.lastDescriptor();
                if (CodeFlow.isPrimitive(lastDesc)) {
                    CodeFlow.insertBoxIfNecessary(mv, lastDesc.charAt(0));
                }
            }
            mv.visitMethodInsn(185, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(87);
        }
    }
}

