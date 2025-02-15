/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import com.kitfox.svg.animation.TimeBase;
import com.kitfox.svg.animation.TimeCompound;
import com.kitfox.svg.animation.TimeDiscrete;
import com.kitfox.svg.animation.TimeIndefinite;
import com.kitfox.svg.animation.TimeLookup;
import com.kitfox.svg.animation.TimeSum;
import com.kitfox.svg.animation.parser.ASTEventTime;
import com.kitfox.svg.animation.parser.ASTExpr;
import com.kitfox.svg.animation.parser.ASTIndefiniteTime;
import com.kitfox.svg.animation.parser.ASTInteger;
import com.kitfox.svg.animation.parser.ASTLiteralTime;
import com.kitfox.svg.animation.parser.ASTLookupTime;
import com.kitfox.svg.animation.parser.ASTNumber;
import com.kitfox.svg.animation.parser.ASTParamList;
import com.kitfox.svg.animation.parser.ASTSum;
import com.kitfox.svg.animation.parser.ASTTerm;
import com.kitfox.svg.animation.parser.AnimTimeParserConstants;
import com.kitfox.svg.animation.parser.AnimTimeParserTokenManager;
import com.kitfox.svg.animation.parser.AnimTimeParserTreeConstants;
import com.kitfox.svg.animation.parser.JJTAnimTimeParserState;
import com.kitfox.svg.animation.parser.Node;
import com.kitfox.svg.animation.parser.ParseException;
import com.kitfox.svg.animation.parser.SimpleCharStream;
import com.kitfox.svg.animation.parser.Token;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimTimeParser
implements AnimTimeParserTreeConstants,
AnimTimeParserConstants {
    protected JJTAnimTimeParserState jjtree = new JJTAnimTimeParserState();
    public AnimTimeParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    private int jj_gen;
    private final int[] jj_la1 = new int[11];
    private static int[] jj_la1_0;
    private final JJCalls[] jj_2_rtns = new JJCalls[1];
    private boolean jj_rescan = false;
    private int jj_gc = 0;
    private final LookaheadSuccess jj_ls = new LookaheadSuccess();
    private List<int[]> jj_expentries = new ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;
    private int[] jj_lasttokens = new int[100];
    private int jj_endpos;

    public static void main(String[] args) throws ParseException {
        StringReader reader = new StringReader("1:30 + 5ms");
        AnimTimeParser parser = new AnimTimeParser(reader);
        TimeBase tc = parser.Expr();
        System.err.println("AnimTimeParser eval to " + tc.evalTime());
        reader = new StringReader("19");
        parser.ReInit(reader);
        tc = parser.Expr();
        System.err.println("AnimTimeParser eval to " + tc.evalTime());
    }

    public final TimeBase Expr() throws ParseException {
        ASTExpr jjtn000 = new ASTExpr(0);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        ArrayList<TimeBase> list = new ArrayList<TimeBase>();
        try {
            TimeBase term;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 14: {
                    term = this.Sum();
                    list.add(term);
                    break;
                }
                default: {
                    this.jj_la1[0] = this.jj_gen;
                }
            }
            while (this.jj_2_1(2)) {
                this.jj_consume_token(15);
                term = this.Sum();
                list.add(term);
            }
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 15: {
                    this.jj_consume_token(15);
                    break;
                }
                default: {
                    this.jj_la1[1] = this.jj_gen;
                }
            }
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            switch (list.size()) {
                case 0: {
                    if ("" != null) {
                        TimeIndefinite timeIndefinite = new TimeIndefinite();
                        return timeIndefinite;
                    }
                }
                case 1: {
                    if ("" == null) break;
                    TimeBase timeBase = (TimeBase)list.get(0);
                    return timeBase;
                }
            }
            if ("" != null) {
                TimeCompound timeCompound = new TimeCompound(list);
                return timeCompound;
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TimeBase Sum() throws ParseException {
        ASTSum jjtn000 = new ASTSum(1);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        Token t = null;
        TimeBase t2 = null;
        try {
            TimeBase t1 = this.Term();
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 16: 
                case 17: {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 16: {
                            t = this.jj_consume_token(16);
                            break;
                        }
                        case 17: {
                            t = this.jj_consume_token(17);
                            break;
                        }
                        default: {
                            this.jj_la1[2] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    t2 = this.Term();
                    break;
                }
                default: {
                    this.jj_la1[3] = this.jj_gen;
                }
            }
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if (t2 == null && "" != null) {
                TimeBase timeBase = t1;
                return timeBase;
            }
            if (t.image.equals("-")) {
                if ("" != null) {
                    TimeSum timeSum = new TimeSum(t1, t2, false);
                    return timeSum;
                }
            } else if ("" != null) {
                TimeSum timeSum = new TimeSum(t1, t2, true);
                return timeSum;
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TimeBase Term() throws ParseException {
        ASTTerm jjtn000 = new ASTTerm(2);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 10: {
                    TimeIndefinite base = this.IndefiniteTime();
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    if ("" != null) {
                        TimeIndefinite timeIndefinite = base;
                        return timeIndefinite;
                    }
                    break;
                }
                case 8: 
                case 9: {
                    TimeDiscrete base = this.LiteralTime();
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    if ("" != null) {
                        TimeDiscrete timeDiscrete = base;
                        return timeDiscrete;
                    }
                    break;
                }
                case 14: {
                    TimeLookup base = this.LookupTime();
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    if ("" != null) {
                        TimeLookup timeLookup = base;
                        return timeLookup;
                    }
                    break;
                }
                case 11: 
                case 12: {
                    TimeDiscrete base = this.EventTime();
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    if ("" != null) {
                        TimeDiscrete timeDiscrete = base;
                        return timeDiscrete;
                    }
                    break;
                }
                default: {
                    this.jj_la1[4] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final TimeIndefinite IndefiniteTime() throws ParseException {
        ASTIndefiniteTime jjtn000 = new ASTIndefiniteTime(3);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(10);
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if ("" != null) {
                TimeIndefinite timeIndefinite = new TimeIndefinite();
                return timeIndefinite;
            }
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final TimeDiscrete EventTime() throws ParseException {
        ASTEventTime jjtn000 = new ASTEventTime(4);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 11: {
                    this.jj_consume_token(11);
                    break;
                }
                case 12: {
                    this.jj_consume_token(12);
                    break;
                }
                default: {
                    this.jj_la1[5] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if ("" != null) {
                TimeDiscrete timeDiscrete = new TimeDiscrete(0.0);
                return timeDiscrete;
            }
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TimeDiscrete LiteralTime() throws ParseException {
        ASTLiteralTime jjtn000 = new ASTLiteralTime(5);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        double t3 = Double.NaN;
        try {
            double t1;
            double value = t1 = this.Number();
            block2 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 18: {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 18: {
                            this.jj_consume_token(18);
                            double t2 = this.Number();
                            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                case 18: {
                                    this.jj_consume_token(18);
                                    t3 = this.Number();
                                    break;
                                }
                                default: {
                                    this.jj_la1[6] = this.jj_gen;
                                }
                            }
                            if (Double.isNaN(t3)) {
                                value = t1 * 60.0 + t2;
                                break block2;
                            }
                            value = t1 * 3600.0 + t2 * 60.0 + t3;
                            break block2;
                        }
                        case 13: {
                            Token t = this.jj_consume_token(13);
                            if (t.image.equals("ms")) {
                                value = t1 / 1000.0;
                            }
                            if (t.image.equals("min")) {
                                value = t1 * 60.0;
                            }
                            if (!t.image.equals("h")) break block2;
                            value = t1 * 3600.0;
                            break block2;
                        }
                        default: {
                            this.jj_la1[7] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                default: {
                    this.jj_la1[8] = this.jj_gen;
                }
            }
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if ("" != null) {
                TimeDiscrete timeDiscrete = new TimeDiscrete(value);
                return timeDiscrete;
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TimeLookup LookupTime() throws ParseException {
        ASTLookupTime jjtn000 = new ASTLookupTime(6);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        double paramNum = 0.0;
        try {
            Token node = this.jj_consume_token(14);
            this.jj_consume_token(19);
            Token event = this.jj_consume_token(14);
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 20: {
                    paramNum = this.ParamList();
                    break;
                }
                default: {
                    this.jj_la1[9] = this.jj_gen;
                }
            }
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if ("" != null) {
                TimeLookup timeLookup = new TimeLookup(null, node.image, event.image, "" + paramNum);
                return timeLookup;
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final double ParamList() throws ParseException {
        ASTParamList jjtn000 = new ASTParamList(7);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            this.jj_consume_token(20);
            double num = this.Number();
            this.jj_consume_token(21);
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            if ("" != null) {
                double d = num;
                return d;
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                this.jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                this.jjtree.popNode();
            }
            if (jjte000 instanceof ParseException) {
                throw (ParseException)jjte000;
            }
            if (jjte000 instanceof RuntimeException) {
                throw (RuntimeException)jjte000;
            }
            throw (Error)jjte000;
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final double Number() throws ParseException {
        ASTNumber jjtn000 = new ASTNumber(8);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 9: {
                    Token t = this.jj_consume_token(9);
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    try {
                        if ("" != null) {
                            double d = Double.parseDouble(t.image);
                            return d;
                        }
                    } catch (Exception e) {
                        Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse double '" + t.image + "'", e);
                    }
                    if ("" != null) {
                        double e = 0.0;
                        return e;
                    }
                    break;
                }
                case 8: {
                    Token t = this.jj_consume_token(8);
                    this.jjtree.closeNodeScope((Node)jjtn000, true);
                    jjtc000 = false;
                    try {
                        if ("" != null) {
                            double e = Double.parseDouble(t.image);
                            return e;
                        }
                    } catch (Exception e) {
                        Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse double '" + t.image + "'", e);
                    }
                    if ("" != null) {
                        double d = 0.0;
                        return d;
                    }
                    break;
                }
                default: {
                    this.jj_la1[10] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final int Integer() throws ParseException {
        ASTInteger jjtn000 = new ASTInteger(9);
        boolean jjtc000 = true;
        this.jjtree.openNodeScope(jjtn000);
        try {
            Token t = this.jj_consume_token(8);
            this.jjtree.closeNodeScope((Node)jjtn000, true);
            jjtc000 = false;
            try {
                if ("" != null) {
                    int n = Integer.parseInt(t.image);
                    return n;
                }
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse int '" + t.image + "'", e);
            }
            if ("" != null) {
                int n = 0;
                return n;
            }
        } finally {
            if (jjtc000) {
                this.jjtree.closeNodeScope((Node)jjtn000, true);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_1(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_1();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(0, xla);
        }
    }

    private boolean jj_3R_3() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_4()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_5()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_6()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_7()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_4() {
        return this.jj_3R_8();
    }

    private boolean jj_3R_9() {
        return this.jj_3R_12();
    }

    private boolean jj_3R_5() {
        return this.jj_3R_9();
    }

    private boolean jj_3R_12() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_13()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_14()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_13() {
        return this.jj_scan_token(9);
    }

    private boolean jj_3R_6() {
        return this.jj_3R_10();
    }

    private boolean jj_3R_7() {
        return this.jj_3R_11();
    }

    private boolean jj_3R_2() {
        return this.jj_3R_3();
    }

    private boolean jj_3R_8() {
        return this.jj_scan_token(10);
    }

    private boolean jj_3R_10() {
        return this.jj_scan_token(14);
    }

    private boolean jj_3_1() {
        if (this.jj_scan_token(15)) {
            return true;
        }
        return this.jj_3R_2();
    }

    private boolean jj_3R_14() {
        return this.jj_scan_token(8);
    }

    private boolean jj_3R_11() {
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(11)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(12)) {
                return true;
            }
        }
        return false;
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{24320, 32768, 196608, 196608, 24320, 6144, 262144, 270336, 270336, 0x100000, 768};
    }

    public AnimTimeParser(InputStream stream) {
        this(stream, null);
    }

    public AnimTimeParser(InputStream stream, Charset encoding) {
        int i;
        this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        this.token_source = new AnimTimeParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public void ReInit(InputStream stream) {
        this.ReInit(stream, null);
    }

    public void ReInit(InputStream stream, Charset encoding) {
        int i;
        this.jj_input_stream.reInit(stream, encoding, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public AnimTimeParser(Reader stream) {
        int i;
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new AnimTimeParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public void ReInit(Reader stream) {
        int i;
        if (this.jj_input_stream == null) {
            this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        } else {
            this.jj_input_stream.reInit(stream, 1, 1);
        }
        if (this.token_source == null) {
            this.token_source = new AnimTimeParserTokenManager(this.jj_input_stream);
        }
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public AnimTimeParser(AnimTimeParserTokenManager tm) {
        int i;
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public void ReInit(AnimTimeParserTokenManager tm) {
        int i;
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken = this.token;
        this.token = this.token.next != null ? this.token.next : (this.token.next = this.token_source.getNextToken());
        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            ++this.jj_gen;
            if (++this.jj_gc > 100) {
                this.jj_gc = 0;
                for (int i = 0; i < this.jj_2_rtns.length; ++i) {
                    JJCalls c = this.jj_2_rtns[i];
                    while (c != null) {
                        if (c.gen < this.jj_gen) {
                            c.first = null;
                        }
                        c = c.next;
                    }
                }
            }
            return this.token;
        }
        this.token = oldToken;
        this.jj_kind = kind;
        throw this.generateParseException();
    }

    private boolean jj_scan_token(int kind) {
        if (this.jj_scanpos == this.jj_lastpos) {
            --this.jj_la;
            if (this.jj_scanpos.next == null) {
                this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
                this.jj_lastpos = this.jj_scanpos.next;
            } else {
                this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
            }
        } else {
            this.jj_scanpos = this.jj_scanpos.next;
        }
        if (this.jj_rescan) {
            int i = 0;
            Token tok = this.token;
            while (tok != null && tok != this.jj_scanpos) {
                ++i;
                tok = tok.next;
            }
            if (tok != null) {
                this.jj_add_error_token(kind, i);
            }
        }
        if (this.jj_scanpos.kind != kind) {
            return true;
        }
        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }

    public final Token getNextToken() {
        this.token = this.token.next != null ? this.token.next : (this.token.next = this.token_source.getNextToken());
        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }

    public final Token getToken(int index) {
        Token t = this.token;
        for (int i = 0; i < index; ++i) {
            if (t.next == null) {
                t.next = this.token_source.getNextToken();
            }
            t = t.next;
        }
        return t;
    }

    private int jj_ntk_f() {
        this.jj_nt = this.token.next;
        if (this.jj_nt == null) {
            this.token.next = this.token_source.getNextToken();
            this.jj_ntk = this.token.next.kind;
            return this.jj_ntk;
        }
        this.jj_ntk = this.jj_nt.kind;
        return this.jj_ntk;
    }

    private void jj_add_error_token(int kind, int pos) {
        if (pos >= 100) {
            return;
        }
        if (pos == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = kind;
        } else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];
            for (int i = 0; i < this.jj_endpos; ++i) {
                this.jj_expentry[i] = this.jj_lasttokens[i];
            }
            for (int[] oldentry : this.jj_expentries) {
                if (oldentry.length != this.jj_expentry.length) continue;
                boolean isMatched = true;
                for (int i = 0; i < this.jj_expentry.length; ++i) {
                    if (oldentry[i] == this.jj_expentry[i]) continue;
                    isMatched = false;
                    break;
                }
                if (!isMatched) continue;
                this.jj_expentries.add(this.jj_expentry);
                break;
            }
            if (pos != 0) {
                this.jj_endpos = pos;
                this.jj_lasttokens[this.jj_endpos - 1] = kind;
            }
        }
    }

    public ParseException generateParseException() {
        int i;
        this.jj_expentries.clear();
        boolean[] la1tokens = new boolean[22];
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }
        for (i = 0; i < 11; ++i) {
            if (this.jj_la1[i] != this.jj_gen) continue;
            for (int j = 0; j < 32; ++j) {
                if ((jj_la1_0[i] & 1 << j) == 0) continue;
                la1tokens[j] = true;
            }
        }
        for (i = 0; i < 22; ++i) {
            if (!la1tokens[i]) continue;
            this.jj_expentry = new int[1];
            this.jj_expentry[0] = i;
            this.jj_expentries.add(this.jj_expentry);
        }
        this.jj_endpos = 0;
        this.jj_rescan_token();
        this.jj_add_error_token(0, 0);
        int[][] exptokseq = new int[this.jj_expentries.size()][];
        for (int i2 = 0; i2 < this.jj_expentries.size(); ++i2) {
            exptokseq[i2] = this.jj_expentries.get(i2);
        }
        return new ParseException(this.token, exptokseq, tokenImage);
    }

    public final boolean trace_enabled() {
        return false;
    }

    public final void enable_tracing() {
    }

    public final void disable_tracing() {
    }

    private void jj_rescan_token() {
        this.jj_rescan = true;
        for (int i = 0; i < 1; ++i) {
            try {
                JJCalls p = this.jj_2_rtns[i];
                do {
                    if (p.gen <= this.jj_gen) continue;
                    this.jj_la = p.arg;
                    this.jj_scanpos = p.first;
                    this.jj_lastpos = p.first;
                    switch (i) {
                        case 0: {
                            this.jj_3_1();
                        }
                    }
                } while ((p = p.next) != null);
                continue;
            } catch (LookaheadSuccess lookaheadSuccess) {
                // empty catch block
            }
        }
        this.jj_rescan = false;
    }

    private void jj_save(int index, int xla) {
        JJCalls p = this.jj_2_rtns[index];
        while (p.gen > this.jj_gen) {
            if (p.next == null) {
                p = p.next = new JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = this.jj_gen + xla - this.jj_la;
        p.first = this.token;
        p.arg = xla;
    }

    static {
        AnimTimeParser.jj_la1_init_0();
    }

    static final class JJCalls {
        int gen;
        Token first;
        int arg;
        JJCalls next;

        JJCalls() {
        }
    }

    private static final class LookaheadSuccess
    extends IllegalStateException {
        private LookaheadSuccess() {
        }
    }
}

