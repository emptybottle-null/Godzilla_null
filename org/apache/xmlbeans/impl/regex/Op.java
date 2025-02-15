/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.util.Vector;
import org.apache.xmlbeans.impl.regex.RangeToken;
import org.apache.xmlbeans.impl.regex.Token;

class Op {
    static final int DOT = 0;
    static final int CHAR = 1;
    static final int RANGE = 3;
    static final int NRANGE = 4;
    static final int ANCHOR = 5;
    static final int STRING = 6;
    static final int CLOSURE = 7;
    static final int NONGREEDYCLOSURE = 8;
    static final int QUESTION = 9;
    static final int NONGREEDYQUESTION = 10;
    static final int UNION = 11;
    static final int CAPTURE = 15;
    static final int BACKREFERENCE = 16;
    static final int LOOKAHEAD = 20;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int LOOKBEHIND = 22;
    static final int NEGATIVELOOKBEHIND = 23;
    static final int INDEPENDENT = 24;
    static final int MODIFIER = 25;
    static final int CONDITION = 26;
    static int nofinstances = 0;
    static final boolean COUNT = false;
    int type;
    Op next = null;

    static Op createDot() {
        return new Op(0);
    }

    static CharOp createChar(int data) {
        return new CharOp(1, data);
    }

    static CharOp createAnchor(int data) {
        return new CharOp(5, data);
    }

    static CharOp createCapture(int number, Op next) {
        CharOp op = new CharOp(15, number);
        op.next = next;
        return op;
    }

    static UnionOp createUnion(int size) {
        return new UnionOp(11, size);
    }

    static ChildOp createClosure(int id) {
        return new ModifierOp(7, id, -1);
    }

    static ChildOp createNonGreedyClosure() {
        return new ChildOp(8);
    }

    static ChildOp createQuestion(boolean nongreedy) {
        return new ChildOp(nongreedy ? 10 : 9);
    }

    static RangeOp createRange(Token tok) {
        return new RangeOp(3, tok);
    }

    static ChildOp createLook(int type, Op next, Op branch) {
        ChildOp op = new ChildOp(type);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static CharOp createBackReference(int refno) {
        return new CharOp(16, refno);
    }

    static StringOp createString(String literal) {
        return new StringOp(6, literal);
    }

    static ChildOp createIndependent(Op next, Op branch) {
        ChildOp op = new ChildOp(24);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static ModifierOp createModifier(Op next, Op branch, int add, int mask) {
        ModifierOp op = new ModifierOp(25, add, mask);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    static ConditionOp createCondition(Op next, int ref, Op conditionflow, Op yesflow, Op noflow) {
        ConditionOp op = new ConditionOp(26, ref, conditionflow, yesflow, noflow);
        op.next = next;
        return op;
    }

    protected Op(int type) {
        this.type = type;
    }

    int size() {
        return 0;
    }

    Op elementAt(int index) {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    Op getChild() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    int getData() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    int getData2() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    RangeToken getToken() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    String getString() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    static class ConditionOp
    extends Op {
        int refNumber;
        Op condition;
        Op yes;
        Op no;

        ConditionOp(int type, int refno, Op conditionflow, Op yesflow, Op noflow) {
            super(type);
            this.refNumber = refno;
            this.condition = conditionflow;
            this.yes = yesflow;
            this.no = noflow;
        }
    }

    static class StringOp
    extends Op {
        String string;

        StringOp(int type, String literal) {
            super(type);
            this.string = literal;
        }

        @Override
        String getString() {
            return this.string;
        }
    }

    static class RangeOp
    extends Op {
        Token tok;

        RangeOp(int type, Token tok) {
            super(type);
            this.tok = tok;
        }

        @Override
        RangeToken getToken() {
            return (RangeToken)this.tok;
        }
    }

    static class ModifierOp
    extends ChildOp {
        int v1;
        int v2;

        ModifierOp(int type, int v1, int v2) {
            super(type);
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        int getData() {
            return this.v1;
        }

        @Override
        int getData2() {
            return this.v2;
        }
    }

    static class ChildOp
    extends Op {
        Op child;

        ChildOp(int type) {
            super(type);
        }

        void setChild(Op child) {
            this.child = child;
        }

        @Override
        Op getChild() {
            return this.child;
        }
    }

    static class UnionOp
    extends Op {
        Vector branches;

        UnionOp(int type, int size) {
            super(type);
            this.branches = new Vector(size);
        }

        void addElement(Op op) {
            this.branches.addElement(op);
        }

        @Override
        int size() {
            return this.branches.size();
        }

        @Override
        Op elementAt(int index) {
            return (Op)this.branches.elementAt(index);
        }
    }

    static class CharOp
    extends Op {
        int charData;

        CharOp(int type, int data) {
            super(type);
            this.charData = data;
        }

        @Override
        int getData() {
            return this.charData;
        }
    }
}

