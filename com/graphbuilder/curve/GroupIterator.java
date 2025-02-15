/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.ControlStringParseException;
import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionParseException;
import com.graphbuilder.math.ExpressionTree;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;

public class GroupIterator {
    protected String controlString = null;
    protected int[] group = null;
    protected int index_i = 0;
    protected int count_j = 0;

    public GroupIterator(String controlString, int n) {
        if (controlString == null) {
            throw new IllegalArgumentException("control string cannot be null");
        }
        this.group = GroupIterator.parseControlString(controlString, n);
        this.controlString = controlString;
    }

    public GroupIterator(int[] group) {
        if (group == null) {
            throw new IllegalArgumentException("group array cannot be null");
        }
        if (group.length == 0) {
            throw new IllegalArgumentException("group array length cannot be 0");
        }
        if (group.length % 2 != 0) {
            throw new IllegalArgumentException("group array must have even length");
        }
        double log10 = Math.log(10.0);
        int numDigits = 0;
        int[] arr = new int[group.length];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = group[i];
            int x = arr[i];
            if (x < 0) {
                ++numDigits;
                x = -x;
            }
            numDigits += (int)(Math.log(x) / log10) + 1;
        }
        this.group = arr;
        StringBuffer sb = new StringBuffer(numDigits + arr.length / 2 + (arr.length - 1));
        sb.append(arr[0]);
        sb.append(":");
        sb.append(arr[1]);
        for (int i = 2; i < group.length; i += 2) {
            sb.append(",");
            sb.append(group[i]);
            sb.append(":");
            sb.append(group[i + 1]);
        }
        this.controlString = sb.toString();
    }

    public static int[] parseControlString(String controlString, int n) {
        String s = controlString;
        int sLength = s.length();
        int numGroups = 1;
        int b = 0;
        for (int i = 0; i < sLength; ++i) {
            char c = s.charAt(i);
            if (c == ',' && b == 0) {
                ++numGroups;
                continue;
            }
            if (c == '(') {
                ++b;
                continue;
            }
            if (c != ')') continue;
            --b;
        }
        if (b != 0) {
            throw new ControlStringParseException("round brackets do not balance");
        }
        int[] group = new int[2 * numGroups];
        int j = 0;
        int k = 0;
        int colon = -1;
        VarMap vm = new VarMap();
        FuncMap fm = new FuncMap();
        fm.loadDefaultFunctions();
        for (int i = 0; i <= sLength; ++i) {
            int c = 32;
            if (i < sLength) {
                c = s.charAt(i);
            }
            if (i == sLength || c == 44 && b == 0) {
                if (colon == -1) {
                    Expression x = GroupIterator.setVariables(s, vm, n, j, i);
                    group[k] = (int)Math.round(x.eval(vm, fm));
                    group[k + 1] = group[k];
                    k += 2;
                } else {
                    Expression x1 = GroupIterator.setVariables(s, vm, n, j, colon);
                    group[k++] = (int)Math.round(x1.eval(vm, fm));
                    Expression x2 = GroupIterator.setVariables(s, vm, n, colon + 1, i);
                    group[k++] = (int)Math.round(x2.eval(vm, fm));
                }
                j = i + 1;
                colon = -1;
                continue;
            }
            if (c == 40) {
                ++b;
                continue;
            }
            if (c == 41) {
                --b;
                continue;
            }
            if (c != 58) continue;
            colon = i;
        }
        return group;
    }

    private static Expression setVariables(String s, VarMap vm, int n, int j, int i) {
        Expression x = null;
        try {
            x = ExpressionTree.parse(s.substring(j, i));
        } catch (ExpressionParseException epe) {
            throw new ControlStringParseException("error parsing expression", j, i, epe);
        }
        if (x == null) {
            throw new ControlStringParseException("control substring is empty", j, i);
        }
        String[] v = x.getVariableNames();
        if (v.length > 1) {
            throw new ControlStringParseException("too many variables", j, i);
        }
        if (v.length == 1) {
            vm.setValue(v[0], n);
        }
        return x;
    }

    public String getControlString() {
        return this.controlString;
    }

    public int getGroupLength() {
        return this.group.length;
    }

    public int getGroupValue(int index) {
        if (index < 0 || index >= this.group.length) {
            throw new IllegalArgumentException("required: (index >= 0 && index < group.length) but: (index = " + index + ", group.length = " + this.group.length + ")");
        }
        return this.group[index];
    }

    public int getGroupSize() {
        int size = 0;
        for (int i = 0; i < this.group.length; i += 2) {
            int dif = this.group[i] - this.group[i + 1];
            if (dif < 0) {
                dif = -dif;
            }
            size += dif + 1;
        }
        return size;
    }

    public void copyGroupArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("specified array cannot be null");
        }
        if (arr.length < this.group.length) {
            throw new IllegalArgumentException("specified array is too small");
        }
        for (int i = 0; i < this.group.length; ++i) {
            arr[i] = this.group[i];
        }
    }

    public boolean hasNext() {
        return this.index_i < this.group.length;
    }

    public int next() {
        int x = this.group[this.index_i];
        int y = this.group[this.index_i + 1];
        if (x <= y) {
            if ((x += this.count_j) >= y) {
                this.count_j = 0;
                this.index_i += 2;
            } else {
                ++this.count_j;
            }
        } else if ((x -= this.count_j) <= y) {
            this.count_j = 0;
            this.index_i += 2;
        } else {
            ++this.count_j;
        }
        return x;
    }

    public void set(int index_i, int count_j) {
        if (index_i < 0) {
            throw new IllegalArgumentException("index_i >= 0 required");
        }
        if (index_i % 2 == 1) {
            throw new IllegalArgumentException("index_i must be an even number");
        }
        if (count_j < 0) {
            throw new IllegalArgumentException("count_j >= 0 required");
        }
        this.index_i = index_i;
        this.count_j = count_j;
    }

    public int index_i() {
        return this.index_i;
    }

    public int count_j() {
        return this.count_j;
    }

    public void reset() {
        this.index_i = 0;
        this.count_j = 0;
    }

    public boolean isInRange(int min, int max) {
        for (int i = 0; i < this.group.length; ++i) {
            if (this.group[i] >= min && this.group[i] < max) continue;
            return false;
        }
        return true;
    }
}

