/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.func.AbsFunction;
import com.graphbuilder.math.func.AcosFunction;
import com.graphbuilder.math.func.AcoshFunction;
import com.graphbuilder.math.func.AsinFunction;
import com.graphbuilder.math.func.AsinhFunction;
import com.graphbuilder.math.func.AtanFunction;
import com.graphbuilder.math.func.AtanhFunction;
import com.graphbuilder.math.func.AvgFunction;
import com.graphbuilder.math.func.CeilFunction;
import com.graphbuilder.math.func.CombinFunction;
import com.graphbuilder.math.func.CosFunction;
import com.graphbuilder.math.func.CoshFunction;
import com.graphbuilder.math.func.EFunction;
import com.graphbuilder.math.func.ExpFunction;
import com.graphbuilder.math.func.FactFunction;
import com.graphbuilder.math.func.FloorFunction;
import com.graphbuilder.math.func.Function;
import com.graphbuilder.math.func.LgFunction;
import com.graphbuilder.math.func.LnFunction;
import com.graphbuilder.math.func.LogFunction;
import com.graphbuilder.math.func.MaxFunction;
import com.graphbuilder.math.func.MinFunction;
import com.graphbuilder.math.func.ModFunction;
import com.graphbuilder.math.func.PiFunction;
import com.graphbuilder.math.func.PowFunction;
import com.graphbuilder.math.func.RandFunction;
import com.graphbuilder.math.func.RoundFunction;
import com.graphbuilder.math.func.SignFunction;
import com.graphbuilder.math.func.SinFunction;
import com.graphbuilder.math.func.SinhFunction;
import com.graphbuilder.math.func.SqrtFunction;
import com.graphbuilder.math.func.SumFunction;
import com.graphbuilder.math.func.TanFunction;
import com.graphbuilder.math.func.TanhFunction;

public class FuncMap {
    private String[] name = new String[50];
    private Function[] func = new Function[50];
    private int numFunc = 0;
    private boolean caseSensitive = false;

    public FuncMap() {
    }

    public FuncMap(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public void loadDefaultFunctions() {
        this.setFunction("min", new MinFunction());
        this.setFunction("max", new MaxFunction());
        this.setFunction("sum", new SumFunction());
        this.setFunction("avg", new AvgFunction());
        this.setFunction("pi", new PiFunction());
        this.setFunction("e", new EFunction());
        this.setFunction("rand", new RandFunction());
        this.setFunction("sin", new SinFunction());
        this.setFunction("cos", new CosFunction());
        this.setFunction("tan", new TanFunction());
        this.setFunction("sqrt", new SqrtFunction());
        this.setFunction("abs", new AbsFunction());
        this.setFunction("ceil", new CeilFunction());
        this.setFunction("floor", new FloorFunction());
        this.setFunction("exp", new ExpFunction());
        this.setFunction("lg", new LgFunction());
        this.setFunction("ln", new LnFunction());
        this.setFunction("sign", new SignFunction());
        this.setFunction("round", new RoundFunction());
        this.setFunction("fact", new FactFunction());
        this.setFunction("cosh", new CoshFunction());
        this.setFunction("sinh", new SinhFunction());
        this.setFunction("tanh", new TanhFunction());
        this.setFunction("acos", new AcosFunction());
        this.setFunction("asin", new AsinFunction());
        this.setFunction("atan", new AtanFunction());
        this.setFunction("acosh", new AcoshFunction());
        this.setFunction("asinh", new AsinhFunction());
        this.setFunction("atanh", new AtanhFunction());
        this.setFunction("pow", new PowFunction());
        this.setFunction("mod", new ModFunction());
        this.setFunction("combin", new CombinFunction());
        this.setFunction("log", new LogFunction());
    }

    public Function getFunction(String funcName, int numParam) {
        for (int i = 0; i < this.numFunc; ++i) {
            if (!this.func[i].acceptNumParam(numParam) || (!this.caseSensitive || !this.name[i].equals(funcName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(funcName))) continue;
            return this.func[i];
        }
        throw new RuntimeException("function not found: " + funcName + " " + numParam);
    }

    public void setFunction(String funcName, Function f) {
        if (funcName == null) {
            throw new IllegalArgumentException("function name cannot be null");
        }
        if (f == null) {
            throw new IllegalArgumentException("function cannot be null");
        }
        for (int i = 0; i < this.numFunc; ++i) {
            if ((!this.caseSensitive || !this.name[i].equals(funcName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(funcName))) continue;
            this.func[i] = f;
            return;
        }
        if (this.numFunc == this.name.length) {
            String[] tmp1 = new String[2 * this.numFunc];
            Function[] tmp2 = new Function[tmp1.length];
            for (int i = 0; i < this.numFunc; ++i) {
                tmp1[i] = this.name[i];
                tmp2[i] = this.func[i];
            }
            this.name = tmp1;
            this.func = tmp2;
        }
        this.name[this.numFunc] = funcName;
        this.func[this.numFunc] = f;
        ++this.numFunc;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public String[] getFunctionNames() {
        String[] arr = new String[this.numFunc];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = this.name[i];
        }
        return arr;
    }

    public Function[] getFunctions() {
        Function[] arr = new Function[this.numFunc];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = this.func[i];
        }
        return arr;
    }

    public void remove(String funcName) {
        for (int i = 0; i < this.numFunc; ++i) {
            if ((!this.caseSensitive || !this.name[i].equals(funcName)) && (this.caseSensitive || !this.name[i].equalsIgnoreCase(funcName))) continue;
            for (int j = i + 1; j < this.numFunc; ++j) {
                this.name[j - 1] = this.name[j];
                this.func[j - 1] = this.func[j];
            }
            --this.numFunc;
            this.name[this.numFunc] = null;
            this.func[this.numFunc] = null;
            break;
        }
    }
}

