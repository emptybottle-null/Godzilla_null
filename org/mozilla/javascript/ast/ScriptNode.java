/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.Symbol;

public class ScriptNode
extends Scope {
    private int encodedSourceStart = -1;
    private int encodedSourceEnd = -1;
    private String sourceName;
    private String encodedSource;
    private int endLineno = -1;
    private List<FunctionNode> functions;
    private List<RegExpLiteral> regexps;
    private List<FunctionNode> EMPTY_LIST = Collections.emptyList();
    private List<Symbol> symbols = new ArrayList<Symbol>(4);
    private int paramCount = 0;
    private String[] variableNames;
    private boolean[] isConsts;
    private Object compilerData;
    private int tempNumber = 0;

    public ScriptNode() {
        this.top = this;
        this.type = 136;
    }

    public ScriptNode(int pos) {
        super(pos);
        this.top = this;
        this.type = 136;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getEncodedSourceStart() {
        return this.encodedSourceStart;
    }

    public void setEncodedSourceStart(int start) {
        this.encodedSourceStart = start;
    }

    public int getEncodedSourceEnd() {
        return this.encodedSourceEnd;
    }

    public void setEncodedSourceEnd(int end) {
        this.encodedSourceEnd = end;
    }

    public void setEncodedSourceBounds(int start, int end) {
        this.encodedSourceStart = start;
        this.encodedSourceEnd = end;
    }

    public void setEncodedSource(String encodedSource) {
        this.encodedSource = encodedSource;
    }

    public String getEncodedSource() {
        return this.encodedSource;
    }

    public int getBaseLineno() {
        return this.lineno;
    }

    public void setBaseLineno(int lineno) {
        if (lineno < 0 || this.lineno >= 0) {
            ScriptNode.codeBug();
        }
        this.lineno = lineno;
    }

    public int getEndLineno() {
        return this.endLineno;
    }

    public void setEndLineno(int lineno) {
        if (lineno < 0 || this.endLineno >= 0) {
            ScriptNode.codeBug();
        }
        this.endLineno = lineno;
    }

    public int getFunctionCount() {
        return this.functions == null ? 0 : this.functions.size();
    }

    public FunctionNode getFunctionNode(int i) {
        return this.functions.get(i);
    }

    public List<FunctionNode> getFunctions() {
        return this.functions == null ? this.EMPTY_LIST : this.functions;
    }

    public int addFunction(FunctionNode fnNode) {
        if (fnNode == null) {
            ScriptNode.codeBug();
        }
        if (this.functions == null) {
            this.functions = new ArrayList<FunctionNode>();
        }
        this.functions.add(fnNode);
        return this.functions.size() - 1;
    }

    public int getRegexpCount() {
        return this.regexps == null ? 0 : this.regexps.size();
    }

    public String getRegexpString(int index) {
        return this.regexps.get(index).getValue();
    }

    public String getRegexpFlags(int index) {
        return this.regexps.get(index).getFlags();
    }

    public void addRegExp(RegExpLiteral re) {
        if (re == null) {
            ScriptNode.codeBug();
        }
        if (this.regexps == null) {
            this.regexps = new ArrayList<RegExpLiteral>();
        }
        this.regexps.add(re);
        re.putIntProp(4, this.regexps.size() - 1);
    }

    public int getIndexForNameNode(Node nameNode) {
        Scope node;
        if (this.variableNames == null) {
            ScriptNode.codeBug();
        }
        Symbol symbol = (node = nameNode.getScope()) == null ? null : node.getSymbol(((Name)nameNode).getIdentifier());
        return symbol == null ? -1 : symbol.getIndex();
    }

    public String getParamOrVarName(int index) {
        if (this.variableNames == null) {
            ScriptNode.codeBug();
        }
        return this.variableNames[index];
    }

    public int getParamCount() {
        return this.paramCount;
    }

    public int getParamAndVarCount() {
        if (this.variableNames == null) {
            ScriptNode.codeBug();
        }
        return this.symbols.size();
    }

    public String[] getParamAndVarNames() {
        if (this.variableNames == null) {
            ScriptNode.codeBug();
        }
        return this.variableNames;
    }

    public boolean[] getParamAndVarConst() {
        if (this.variableNames == null) {
            ScriptNode.codeBug();
        }
        return this.isConsts;
    }

    void addSymbol(Symbol symbol) {
        if (this.variableNames != null) {
            ScriptNode.codeBug();
        }
        if (symbol.getDeclType() == 87) {
            ++this.paramCount;
        }
        this.symbols.add(symbol);
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public void flattenSymbolTable(boolean flattenAllTables) {
        if (!flattenAllTables) {
            ArrayList<Symbol> newSymbols = new ArrayList<Symbol>();
            if (this.symbolTable != null) {
                for (int i = 0; i < this.symbols.size(); ++i) {
                    Symbol symbol = this.symbols.get(i);
                    if (symbol.getContainingTable() != this) continue;
                    newSymbols.add(symbol);
                }
            }
            this.symbols = newSymbols;
        }
        this.variableNames = new String[this.symbols.size()];
        this.isConsts = new boolean[this.symbols.size()];
        for (int i = 0; i < this.symbols.size(); ++i) {
            Symbol symbol = this.symbols.get(i);
            this.variableNames[i] = symbol.getName();
            this.isConsts[i] = symbol.getDeclType() == 154;
            symbol.setIndex(i);
        }
    }

    public Object getCompilerData() {
        return this.compilerData;
    }

    public void setCompilerData(Object data) {
        this.assertNotNull(data);
        if (this.compilerData != null) {
            throw new IllegalStateException();
        }
        this.compilerData = data;
    }

    public String getNextTempName() {
        return "$" + this.tempNumber++;
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            for (Node kid : this) {
                ((AstNode)kid).visit(v);
            }
        }
    }
}

