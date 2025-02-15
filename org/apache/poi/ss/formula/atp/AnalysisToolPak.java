/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.atp.IfError;
import org.apache.poi.ss.formula.atp.MRound;
import org.apache.poi.ss.formula.atp.NetworkdaysFunction;
import org.apache.poi.ss.formula.atp.ParityFunction;
import org.apache.poi.ss.formula.atp.RandBetween;
import org.apache.poi.ss.formula.atp.WorkdayFunction;
import org.apache.poi.ss.formula.atp.YearFrac;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.functions.Bin2Dec;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.ss.formula.functions.Countifs;
import org.apache.poi.ss.formula.functions.Dec2Bin;
import org.apache.poi.ss.formula.functions.Dec2Hex;
import org.apache.poi.ss.formula.functions.Delta;
import org.apache.poi.ss.formula.functions.EDate;
import org.apache.poi.ss.formula.functions.EOMonth;
import org.apache.poi.ss.formula.functions.FactDouble;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Hex2Dec;
import org.apache.poi.ss.formula.functions.ImReal;
import org.apache.poi.ss.formula.functions.Imaginary;
import org.apache.poi.ss.formula.functions.Oct2Dec;
import org.apache.poi.ss.formula.functions.Quotient;
import org.apache.poi.ss.formula.functions.Sumifs;
import org.apache.poi.ss.formula.functions.WeekNum;
import org.apache.poi.ss.formula.udf.UDFFinder;

public final class AnalysisToolPak
implements UDFFinder {
    public static final UDFFinder instance = new AnalysisToolPak();
    private final Map<String, FreeRefFunction> _functionsByName = this.createFunctionsMap();

    private AnalysisToolPak() {
    }

    @Override
    public FreeRefFunction findFunction(String name) {
        String prefix = "_xlfn.";
        if (name.startsWith("_xlfn.")) {
            name = name.substring("_xlfn.".length());
        }
        return this._functionsByName.get(name.toUpperCase(Locale.ROOT));
    }

    private Map<String, FreeRefFunction> createFunctionsMap() {
        HashMap<String, FreeRefFunction> m = new HashMap<String, FreeRefFunction>(108);
        AnalysisToolPak.r(m, "ACCRINT", null);
        AnalysisToolPak.r(m, "ACCRINTM", null);
        AnalysisToolPak.r(m, "AMORDEGRC", null);
        AnalysisToolPak.r(m, "AMORLINC", null);
        AnalysisToolPak.r(m, "AVERAGEIF", null);
        AnalysisToolPak.r(m, "AVERAGEIFS", null);
        AnalysisToolPak.r(m, "BAHTTEXT", null);
        AnalysisToolPak.r(m, "BESSELI", null);
        AnalysisToolPak.r(m, "BESSELJ", null);
        AnalysisToolPak.r(m, "BESSELK", null);
        AnalysisToolPak.r(m, "BESSELY", null);
        AnalysisToolPak.r(m, "BIN2DEC", Bin2Dec.instance);
        AnalysisToolPak.r(m, "BIN2HEX", null);
        AnalysisToolPak.r(m, "BIN2OCT", null);
        AnalysisToolPak.r(m, "COMPLEX", Complex.instance);
        AnalysisToolPak.r(m, "CONVERT", null);
        AnalysisToolPak.r(m, "COUNTIFS", Countifs.instance);
        AnalysisToolPak.r(m, "COUPDAYBS", null);
        AnalysisToolPak.r(m, "COUPDAYS", null);
        AnalysisToolPak.r(m, "COUPDAYSNC", null);
        AnalysisToolPak.r(m, "COUPNCD", null);
        AnalysisToolPak.r(m, "COUPNUM", null);
        AnalysisToolPak.r(m, "COUPPCD", null);
        AnalysisToolPak.r(m, "CUBEKPIMEMBER", null);
        AnalysisToolPak.r(m, "CUBEMEMBER", null);
        AnalysisToolPak.r(m, "CUBEMEMBERPROPERTY", null);
        AnalysisToolPak.r(m, "CUBERANKEDMEMBER", null);
        AnalysisToolPak.r(m, "CUBESET", null);
        AnalysisToolPak.r(m, "CUBESETCOUNT", null);
        AnalysisToolPak.r(m, "CUBEVALUE", null);
        AnalysisToolPak.r(m, "CUMIPMT", null);
        AnalysisToolPak.r(m, "CUMPRINC", null);
        AnalysisToolPak.r(m, "DEC2BIN", Dec2Bin.instance);
        AnalysisToolPak.r(m, "DEC2HEX", Dec2Hex.instance);
        AnalysisToolPak.r(m, "DEC2OCT", null);
        AnalysisToolPak.r(m, "DELTA", Delta.instance);
        AnalysisToolPak.r(m, "DISC", null);
        AnalysisToolPak.r(m, "DOLLARDE", null);
        AnalysisToolPak.r(m, "DOLLARFR", null);
        AnalysisToolPak.r(m, "DURATION", null);
        AnalysisToolPak.r(m, "EDATE", EDate.instance);
        AnalysisToolPak.r(m, "EFFECT", null);
        AnalysisToolPak.r(m, "EOMONTH", EOMonth.instance);
        AnalysisToolPak.r(m, "ERF", null);
        AnalysisToolPak.r(m, "ERFC", null);
        AnalysisToolPak.r(m, "FACTDOUBLE", FactDouble.instance);
        AnalysisToolPak.r(m, "FVSCHEDULE", null);
        AnalysisToolPak.r(m, "GCD", null);
        AnalysisToolPak.r(m, "GESTEP", null);
        AnalysisToolPak.r(m, "HEX2BIN", null);
        AnalysisToolPak.r(m, "HEX2DEC", Hex2Dec.instance);
        AnalysisToolPak.r(m, "HEX2OCT", null);
        AnalysisToolPak.r(m, "IFERROR", IfError.instance);
        AnalysisToolPak.r(m, "IMABS", null);
        AnalysisToolPak.r(m, "IMAGINARY", Imaginary.instance);
        AnalysisToolPak.r(m, "IMARGUMENT", null);
        AnalysisToolPak.r(m, "IMCONJUGATE", null);
        AnalysisToolPak.r(m, "IMCOS", null);
        AnalysisToolPak.r(m, "IMDIV", null);
        AnalysisToolPak.r(m, "IMEXP", null);
        AnalysisToolPak.r(m, "IMLN", null);
        AnalysisToolPak.r(m, "IMLOG10", null);
        AnalysisToolPak.r(m, "IMLOG2", null);
        AnalysisToolPak.r(m, "IMPOWER", null);
        AnalysisToolPak.r(m, "IMPRODUCT", null);
        AnalysisToolPak.r(m, "IMREAL", ImReal.instance);
        AnalysisToolPak.r(m, "IMSIN", null);
        AnalysisToolPak.r(m, "IMSQRT", null);
        AnalysisToolPak.r(m, "IMSUB", null);
        AnalysisToolPak.r(m, "IMSUM", null);
        AnalysisToolPak.r(m, "INTRATE", null);
        AnalysisToolPak.r(m, "ISEVEN", ParityFunction.IS_EVEN);
        AnalysisToolPak.r(m, "ISODD", ParityFunction.IS_ODD);
        AnalysisToolPak.r(m, "JIS", null);
        AnalysisToolPak.r(m, "LCM", null);
        AnalysisToolPak.r(m, "MDURATION", null);
        AnalysisToolPak.r(m, "MROUND", MRound.instance);
        AnalysisToolPak.r(m, "MULTINOMIAL", null);
        AnalysisToolPak.r(m, "NETWORKDAYS", NetworkdaysFunction.instance);
        AnalysisToolPak.r(m, "NOMINAL", null);
        AnalysisToolPak.r(m, "OCT2BIN", null);
        AnalysisToolPak.r(m, "OCT2DEC", Oct2Dec.instance);
        AnalysisToolPak.r(m, "OCT2HEX", null);
        AnalysisToolPak.r(m, "ODDFPRICE", null);
        AnalysisToolPak.r(m, "ODDFYIELD", null);
        AnalysisToolPak.r(m, "ODDLPRICE", null);
        AnalysisToolPak.r(m, "ODDLYIELD", null);
        AnalysisToolPak.r(m, "PRICE", null);
        AnalysisToolPak.r(m, "PRICEDISC", null);
        AnalysisToolPak.r(m, "PRICEMAT", null);
        AnalysisToolPak.r(m, "QUOTIENT", Quotient.instance);
        AnalysisToolPak.r(m, "RANDBETWEEN", RandBetween.instance);
        AnalysisToolPak.r(m, "RECEIVED", null);
        AnalysisToolPak.r(m, "RTD", null);
        AnalysisToolPak.r(m, "SERIESSUM", null);
        AnalysisToolPak.r(m, "SQRTPI", null);
        AnalysisToolPak.r(m, "SUMIFS", Sumifs.instance);
        AnalysisToolPak.r(m, "TBILLEQ", null);
        AnalysisToolPak.r(m, "TBILLPRICE", null);
        AnalysisToolPak.r(m, "TBILLYIELD", null);
        AnalysisToolPak.r(m, "WEEKNUM", WeekNum.instance);
        AnalysisToolPak.r(m, "WORKDAY", WorkdayFunction.instance);
        AnalysisToolPak.r(m, "XIRR", null);
        AnalysisToolPak.r(m, "XNPV", null);
        AnalysisToolPak.r(m, "YEARFRAC", YearFrac.instance);
        AnalysisToolPak.r(m, "YIELD", null);
        AnalysisToolPak.r(m, "YIELDDISC", null);
        AnalysisToolPak.r(m, "YIELDMAT", null);
        return m;
    }

    private static void r(Map<String, FreeRefFunction> m, String functionName, FreeRefFunction pFunc) {
        FreeRefFunction func = pFunc == null ? new NotImplemented(functionName) : pFunc;
        m.put(functionName, func);
    }

    public static boolean isATPFunction(String name) {
        AnalysisToolPak inst = (AnalysisToolPak)instance;
        return inst._functionsByName.containsKey(name);
    }

    public static Collection<String> getSupportedFunctionNames() {
        AnalysisToolPak inst = (AnalysisToolPak)instance;
        TreeSet<String> lst = new TreeSet<String>();
        for (Map.Entry<String, FreeRefFunction> me : inst._functionsByName.entrySet()) {
            FreeRefFunction func = me.getValue();
            if (func == null || func instanceof NotImplemented) continue;
            lst.add(me.getKey());
        }
        return Collections.unmodifiableCollection(lst);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        AnalysisToolPak inst = (AnalysisToolPak)instance;
        TreeSet<String> lst = new TreeSet<String>();
        for (Map.Entry<String, FreeRefFunction> me : inst._functionsByName.entrySet()) {
            FreeRefFunction func = me.getValue();
            if (!(func instanceof NotImplemented)) continue;
            lst.add(me.getKey());
        }
        return Collections.unmodifiableCollection(lst);
    }

    public static void registerFunction(String name, FreeRefFunction func) {
        AnalysisToolPak inst = (AnalysisToolPak)instance;
        if (!AnalysisToolPak.isATPFunction(name)) {
            FunctionMetadata metaData = FunctionMetadataRegistry.getFunctionByName(name);
            if (metaData != null) {
                throw new IllegalArgumentException(name + " is a built-in Excel function. Use FunctoinEval.registerFunction(String name, Function func) instead.");
            }
            throw new IllegalArgumentException(name + " is not a function from the Excel Analysis Toolpack.");
        }
        FreeRefFunction f = inst.findFunction(name);
        if (f != null && !(f instanceof NotImplemented)) {
            throw new IllegalArgumentException("POI already implememts " + name + ". You cannot override POI's implementations of Excel functions");
        }
        inst._functionsByName.put(name, func);
    }

    private static final class NotImplemented
    implements FreeRefFunction {
        private final String _functionName;

        public NotImplemented(String functionName) {
            this._functionName = functionName;
        }

        @Override
        public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
            throw new NotImplementedFunctionException(this._functionName);
        }
    }
}

