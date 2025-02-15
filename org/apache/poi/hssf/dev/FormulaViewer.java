/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.dev;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.dev.BiffViewer;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.FuncPtg;
import org.apache.poi.ss.formula.ptg.Ptg;

public class FormulaViewer {
    private String file;
    private boolean list;

    public void run() throws IOException {
        try (POIFSFileSystem fs = new POIFSFileSystem(new File(this.file), true);
             InputStream is = BiffViewer.getPOIFSInputStream(fs);){
            List<Record> records = RecordFactory.createRecords(is);
            for (Record record : records) {
                if (record.getSid() != 6) continue;
                if (this.list) {
                    this.listFormula((FormulaRecord)record);
                    continue;
                }
                this.parseFormulaRecord((FormulaRecord)record);
            }
        }
    }

    private void listFormula(FormulaRecord record) {
        int numptgs;
        String sep = "~";
        Ptg[] tokens = record.getParsedExpression();
        Ptg token = tokens[(numptgs = tokens.length) - 1];
        String numArg = token instanceof FuncPtg ? String.valueOf(numptgs - 1) : String.valueOf(-1);
        StringBuilder buf = new StringBuilder();
        if (token instanceof ExpPtg) {
            return;
        }
        buf.append(token.toFormulaString());
        buf.append(sep);
        switch (token.getPtgClass()) {
            case 0: {
                buf.append("REF");
                break;
            }
            case 32: {
                buf.append("VALUE");
                break;
            }
            case 64: {
                buf.append("ARRAY");
                break;
            }
            default: {
                FormulaViewer.throwInvalidRVAToken(token);
            }
        }
        buf.append(sep);
        if (numptgs > 1) {
            token = tokens[numptgs - 2];
            switch (token.getPtgClass()) {
                case 0: {
                    buf.append("REF");
                    break;
                }
                case 32: {
                    buf.append("VALUE");
                    break;
                }
                case 64: {
                    buf.append("ARRAY");
                    break;
                }
                default: {
                    FormulaViewer.throwInvalidRVAToken(token);
                    break;
                }
            }
        } else {
            buf.append("VALUE");
        }
        buf.append(sep);
        buf.append(numArg);
        System.out.println(buf);
    }

    public void parseFormulaRecord(FormulaRecord record) {
        System.out.println("==============================");
        System.out.print("row = " + record.getRow());
        System.out.println(", col = " + record.getColumn());
        System.out.println("value = " + record.getValue());
        System.out.print("xf = " + record.getXFIndex());
        System.out.print(", number of ptgs = " + record.getParsedExpression().length);
        System.out.println(", options = " + record.getOptions());
        System.out.println("RPN List = " + this.formulaString(record));
        System.out.println("Formula text = " + FormulaViewer.composeFormula(record));
    }

    private String formulaString(FormulaRecord record) {
        Ptg[] tokens;
        StringBuilder buf = new StringBuilder();
        for (Ptg token : tokens = record.getParsedExpression()) {
            buf.append(token.toFormulaString());
            switch (token.getPtgClass()) {
                case 0: {
                    buf.append("(R)");
                    break;
                }
                case 32: {
                    buf.append("(V)");
                    break;
                }
                case 64: {
                    buf.append("(A)");
                    break;
                }
                default: {
                    FormulaViewer.throwInvalidRVAToken(token);
                }
            }
            buf.append(' ');
        }
        return buf.toString();
    }

    private static void throwInvalidRVAToken(Ptg token) {
        throw new IllegalStateException("Invalid RVA type (" + token.getPtgClass() + "). This should never happen.");
    }

    private static String composeFormula(FormulaRecord record) {
        return HSSFFormulaParser.toFormulaString(null, record.getParsedExpression());
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length > 2 || args[0].equals("--help")) {
            System.out.println("FormulaViewer .8 proof that the devil lies in the details (or just in BIFF8 files in general)");
            System.out.println("usage: Give me a big fat file name");
        } else if (args[0].equals("--listFunctions")) {
            FormulaViewer viewer = new FormulaViewer();
            viewer.setFile(args[1]);
            viewer.setList(true);
            viewer.run();
        } else {
            FormulaViewer viewer = new FormulaViewer();
            viewer.setFile(args[0]);
            viewer.run();
        }
    }
}

