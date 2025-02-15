/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.AddNode;
import com.graphbuilder.math.DivNode;
import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionParseException;
import com.graphbuilder.math.FuncNode;
import com.graphbuilder.math.MultNode;
import com.graphbuilder.math.PowNode;
import com.graphbuilder.math.SubNode;
import com.graphbuilder.math.ValNode;
import com.graphbuilder.math.VarNode;
import com.graphbuilder.struc.Stack;

public class ExpressionTree {
    private ExpressionTree() {
    }

    public static Expression parse(String s) {
        if (s == null) {
            throw new ExpressionParseException("Expression string cannot be null.", -1);
        }
        return ExpressionTree.build(s, 0);
    }

    private static Expression build(String s, int indexErrorOffset) {
        if (s.trim().length() == 0) {
            return null;
        }
        Stack s1 = new Stack();
        Stack s2 = new Stack();
        boolean term = true;
        boolean signed = false;
        boolean negate = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n') continue;
            if (term) {
                if (c == '(') {
                    if (negate) {
                        throw new ExpressionParseException("Open bracket found after negate.", i);
                    }
                    s2.push("(");
                    continue;
                }
                if (!(signed || c != '+' && c != '-')) {
                    signed = true;
                    if (c != '-') continue;
                    negate = true;
                    continue;
                }
                if (c >= '0' && c <= '9' || c == '.') {
                    int j;
                    for (j = i + 1; j < s.length(); ++j) {
                        c = s.charAt(j);
                        if (c >= '0' && c <= '9' || c == '.') {
                            continue;
                        }
                        if (c != 'e' && c != 'E') break;
                        if (++j < s.length()) {
                            c = s.charAt(j);
                            if (c != '+' && c != '-' && (c < '0' || c > '9')) {
                                throw new ExpressionParseException("Expected digit, plus sign or minus sign but found: " + String.valueOf(c), j + indexErrorOffset);
                            }
                            ++j;
                        }
                        while (j < s.length() && (c = s.charAt(j)) >= '0' && c <= '9') {
                            ++j;
                        }
                        break block3;
                    }
                    double d = 0.0;
                    String _d = s.substring(i, j);
                    try {
                        d = Double.parseDouble(_d);
                    } catch (Throwable t) {
                        throw new ExpressionParseException("Improperly formatted value: " + _d, i + indexErrorOffset);
                    }
                    if (negate) {
                        d = -d;
                    }
                    s1.push(new ValNode(d));
                    i = j - 1;
                    negate = false;
                    term = false;
                    signed = false;
                    continue;
                }
                if (c != ',' && c != ')' && c != '^' && c != '*' && c != '/' && c != '+' && c != '-') {
                    int j;
                    for (j = i + 1; j < s.length() && (c = s.charAt(j)) != ',' && c != ' ' && c != '\t' && c != '\n' && c != '(' && c != ')' && c != '^' && c != '*' && c != '/' && c != '+' && c != '-'; ++j) {
                    }
                    if (j < s.length()) {
                        int k = j;
                        while ((c == ' ' || c == '\t' || c == '\n') && ++k != s.length()) {
                            c = s.charAt(k);
                        }
                        if (c == '(') {
                            Expression o;
                            FuncNode fn = new FuncNode(s.substring(i, j), negate);
                            int b = 1;
                            int kOld = k + 1;
                            while (b != 0) {
                                if (++k >= s.length()) {
                                    throw new ExpressionParseException("Missing function close bracket.", i + indexErrorOffset);
                                }
                                c = s.charAt(k);
                                if (c == ')') {
                                    --b;
                                    continue;
                                }
                                if (c == '(') {
                                    ++b;
                                    continue;
                                }
                                if (c != ',' || b != 1) continue;
                                o = ExpressionTree.build(s.substring(kOld, k), kOld);
                                if (o == null) {
                                    throw new ExpressionParseException("Incomplete function.", kOld + indexErrorOffset);
                                }
                                fn.add(o);
                                kOld = k + 1;
                            }
                            o = ExpressionTree.build(s.substring(kOld, k), kOld);
                            if (o == null) {
                                if (fn.numChildren() > 0) {
                                    throw new ExpressionParseException("Incomplete function.", kOld + indexErrorOffset);
                                }
                            } else {
                                fn.add(o);
                            }
                            s1.push(fn);
                            i = k;
                        } else {
                            s1.push(new VarNode(s.substring(i, j), negate));
                            i = k - 1;
                        }
                    } else {
                        s1.push(new VarNode(s.substring(i, j), negate));
                        i = j - 1;
                    }
                    negate = false;
                    term = false;
                    signed = false;
                    continue;
                }
                throw new ExpressionParseException("Unexpected character: " + String.valueOf(c), i + indexErrorOffset);
            }
            if (c == ')') {
                Stack s3 = new Stack();
                Stack s4 = new Stack();
                while (true) {
                    if (s2.isEmpty()) {
                        throw new ExpressionParseException("Missing open bracket.", i + indexErrorOffset);
                    }
                    Object o = s2.pop();
                    if (o.equals("(")) break;
                    s3.addToTail(s1.pop());
                    s4.addToTail(o);
                }
                s3.addToTail(s1.pop());
                s1.push(ExpressionTree.build(s3, s4));
                continue;
            }
            if (c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
                term = true;
                s2.push(String.valueOf(c));
                continue;
            }
            throw new ExpressionParseException("Expected operator or close bracket but found: " + String.valueOf(c), i + indexErrorOffset);
        }
        if (s1.size() != s2.size() + 1) {
            throw new ExpressionParseException("Incomplete expression.", indexErrorOffset + s.length());
        }
        return ExpressionTree.build(s1, s2);
    }

    private static Expression build(Stack s1, Stack s2) {
        Object o2;
        Object o1;
        Object o;
        Stack s3 = new Stack();
        Stack s4 = new Stack();
        while (!s2.isEmpty()) {
            o = s2.removeTail();
            o1 = s1.removeTail();
            o2 = s1.removeTail();
            if (o.equals("^")) {
                s1.addToTail(new PowNode((Expression)o1, (Expression)o2));
                continue;
            }
            s1.addToTail(o2);
            s4.push(o);
            s3.push(o1);
        }
        s3.push(s1.pop());
        while (!s4.isEmpty()) {
            o = s4.removeTail();
            o1 = s3.removeTail();
            o2 = s3.removeTail();
            if (o.equals("*")) {
                s3.addToTail(new MultNode((Expression)o1, (Expression)o2));
                continue;
            }
            if (o.equals("/")) {
                s3.addToTail(new DivNode((Expression)o1, (Expression)o2));
                continue;
            }
            s3.addToTail(o2);
            s2.push(o);
            s1.push(o1);
        }
        s1.push(s3.pop());
        while (!s2.isEmpty()) {
            o = s2.removeTail();
            o1 = s1.removeTail();
            o2 = s1.removeTail();
            if (o.equals("+")) {
                s1.addToTail(new AddNode((Expression)o1, (Expression)o2));
                continue;
            }
            if (o.equals("-")) {
                s1.addToTail(new SubNode((Expression)o1, (Expression)o2));
                continue;
            }
            throw new ExpressionParseException("Unknown operator: " + o, -1);
        }
        return (Expression)s1.pop();
    }
}

