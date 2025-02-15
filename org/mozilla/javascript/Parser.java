/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.TokenStream;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.EmptyStatement;
import org.mozilla.javascript.ast.ErrorNode;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.Symbol;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlDotQuery;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

public class Parser {
    public static final int ARGC_LIMIT = 65536;
    static final int CLEAR_TI_MASK = 65535;
    static final int TI_AFTER_EOL = 65536;
    static final int TI_CHECK_LABEL = 131072;
    CompilerEnvirons compilerEnv;
    private ErrorReporter errorReporter;
    private IdeErrorReporter errorCollector;
    private String sourceURI;
    private char[] sourceChars;
    boolean calledByCompileFunction;
    private boolean parseFinished;
    private TokenStream ts;
    private int currentFlaggedToken = 0;
    private int currentToken;
    private int syntaxErrorCount;
    private List<Comment> scannedComments;
    private Comment currentJsDocComment;
    protected int nestingOfFunction;
    private LabeledStatement currentLabel;
    private boolean inDestructuringAssignment;
    protected boolean inUseStrictDirective;
    ScriptNode currentScriptOrFn;
    Scope currentScope;
    private int endFlags;
    private boolean inForInit;
    private Map<String, LabeledStatement> labelSet;
    private List<Loop> loopSet;
    private List<Jump> loopAndSwitchSet;
    private int prevNameTokenStart;
    private String prevNameTokenString = "";
    private int prevNameTokenLineno;
    private static final int PROP_ENTRY = 1;
    private static final int GET_ENTRY = 2;
    private static final int SET_ENTRY = 4;

    public Parser() {
        this(new CompilerEnvirons());
    }

    public Parser(CompilerEnvirons compilerEnv) {
        this(compilerEnv, compilerEnv.getErrorReporter());
    }

    public Parser(CompilerEnvirons compilerEnv, ErrorReporter errorReporter) {
        this.compilerEnv = compilerEnv;
        this.errorReporter = errorReporter;
        if (errorReporter instanceof IdeErrorReporter) {
            this.errorCollector = (IdeErrorReporter)errorReporter;
        }
    }

    void addStrictWarning(String messageId, String messageArg) {
        int beg = -1;
        int end = -1;
        if (this.ts != null) {
            beg = this.ts.tokenBeg;
            end = this.ts.tokenEnd - this.ts.tokenBeg;
        }
        this.addStrictWarning(messageId, messageArg, beg, end);
    }

    void addStrictWarning(String messageId, String messageArg, int position, int length) {
        if (this.compilerEnv.isStrictMode()) {
            this.addWarning(messageId, messageArg, position, length);
        }
    }

    void addWarning(String messageId, String messageArg) {
        int beg = -1;
        int end = -1;
        if (this.ts != null) {
            beg = this.ts.tokenBeg;
            end = this.ts.tokenEnd - this.ts.tokenBeg;
        }
        this.addWarning(messageId, messageArg, beg, end);
    }

    void addWarning(String messageId, int position, int length) {
        this.addWarning(messageId, null, position, length);
    }

    void addWarning(String messageId, String messageArg, int position, int length) {
        String message = this.lookupMessage(messageId, messageArg);
        if (this.compilerEnv.reportWarningAsError()) {
            this.addError(messageId, messageArg, position, length);
        } else if (this.errorCollector != null) {
            this.errorCollector.warning(message, this.sourceURI, position, length);
        } else {
            this.errorReporter.warning(message, this.sourceURI, this.ts.getLineno(), this.ts.getLine(), this.ts.getOffset());
        }
    }

    void addError(String messageId) {
        this.addError(messageId, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    void addError(String messageId, int position, int length) {
        this.addError(messageId, null, position, length);
    }

    void addError(String messageId, String messageArg) {
        this.addError(messageId, messageArg, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    void addError(String messageId, String messageArg, int position, int length) {
        ++this.syntaxErrorCount;
        String message = this.lookupMessage(messageId, messageArg);
        if (this.errorCollector != null) {
            this.errorCollector.error(message, this.sourceURI, position, length);
        } else {
            int lineno = 1;
            int offset = 1;
            String line = "";
            if (this.ts != null) {
                lineno = this.ts.getLineno();
                line = this.ts.getLine();
                offset = this.ts.getOffset();
            }
            this.errorReporter.error(message, this.sourceURI, lineno, line, offset);
        }
    }

    private void addStrictWarning(String messageId, String messageArg, int position, int length, int line, String lineSource, int lineOffset) {
        if (this.compilerEnv.isStrictMode()) {
            this.addWarning(messageId, messageArg, position, length, line, lineSource, lineOffset);
        }
    }

    private void addWarning(String messageId, String messageArg, int position, int length, int line, String lineSource, int lineOffset) {
        String message = this.lookupMessage(messageId, messageArg);
        if (this.compilerEnv.reportWarningAsError()) {
            this.addError(messageId, messageArg, position, length, line, lineSource, lineOffset);
        } else if (this.errorCollector != null) {
            this.errorCollector.warning(message, this.sourceURI, position, length);
        } else {
            this.errorReporter.warning(message, this.sourceURI, line, lineSource, lineOffset);
        }
    }

    private void addError(String messageId, String messageArg, int position, int length, int line, String lineSource, int lineOffset) {
        ++this.syntaxErrorCount;
        String message = this.lookupMessage(messageId, messageArg);
        if (this.errorCollector != null) {
            this.errorCollector.error(message, this.sourceURI, position, length);
        } else {
            this.errorReporter.error(message, this.sourceURI, line, lineSource, lineOffset);
        }
    }

    String lookupMessage(String messageId) {
        return this.lookupMessage(messageId, null);
    }

    String lookupMessage(String messageId, String messageArg) {
        return messageArg == null ? ScriptRuntime.getMessage0(messageId) : ScriptRuntime.getMessage1(messageId, messageArg);
    }

    void reportError(String messageId) {
        this.reportError(messageId, null);
    }

    void reportError(String messageId, String messageArg) {
        if (this.ts == null) {
            this.reportError(messageId, messageArg, 1, 1);
        } else {
            this.reportError(messageId, messageArg, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        }
    }

    void reportError(String messageId, int position, int length) {
        this.reportError(messageId, null, position, length);
    }

    void reportError(String messageId, String messageArg, int position, int length) {
        this.addError(messageId, position, length);
        if (!this.compilerEnv.recoverFromErrors()) {
            throw new ParserException();
        }
    }

    private int getNodeEnd(AstNode n) {
        return n.getPosition() + n.getLength();
    }

    private void recordComment(int lineno, String comment) {
        if (this.scannedComments == null) {
            this.scannedComments = new ArrayList<Comment>();
        }
        Comment commentNode = new Comment(this.ts.tokenBeg, this.ts.getTokenLength(), this.ts.commentType, comment);
        if (this.ts.commentType == Token.CommentType.JSDOC && this.compilerEnv.isRecordingLocalJsDocComments()) {
            this.currentJsDocComment = commentNode;
        }
        commentNode.setLineno(lineno);
        this.scannedComments.add(commentNode);
    }

    private Comment getAndResetJsDoc() {
        Comment saved = this.currentJsDocComment;
        this.currentJsDocComment = null;
        return saved;
    }

    private int getNumberOfEols(String comment) {
        int lines = 0;
        for (int i = comment.length() - 1; i >= 0; --i) {
            if (comment.charAt(i) != '\n') continue;
            ++lines;
        }
        return lines;
    }

    private int peekToken() throws IOException {
        if (this.currentFlaggedToken != 0) {
            return this.currentToken;
        }
        int lineno = this.ts.getLineno();
        int tt = this.ts.getToken();
        boolean sawEOL = false;
        while (tt == 1 || tt == 161) {
            if (tt == 1) {
                ++lineno;
                sawEOL = true;
            } else if (this.compilerEnv.isRecordingComments()) {
                String comment = this.ts.getAndResetCurrentComment();
                this.recordComment(lineno, comment);
                lineno += this.getNumberOfEols(comment);
            }
            tt = this.ts.getToken();
        }
        this.currentToken = tt;
        this.currentFlaggedToken = tt | (sawEOL ? 65536 : 0);
        return this.currentToken;
    }

    private int peekFlaggedToken() throws IOException {
        this.peekToken();
        return this.currentFlaggedToken;
    }

    private void consumeToken() {
        this.currentFlaggedToken = 0;
    }

    private int nextToken() throws IOException {
        int tt = this.peekToken();
        this.consumeToken();
        return tt;
    }

    private int nextFlaggedToken() throws IOException {
        this.peekToken();
        int ttFlagged = this.currentFlaggedToken;
        this.consumeToken();
        return ttFlagged;
    }

    private boolean matchToken(int toMatch) throws IOException {
        if (this.peekToken() != toMatch) {
            return false;
        }
        this.consumeToken();
        return true;
    }

    private int peekTokenOrEOL() throws IOException {
        int tt = this.peekToken();
        if ((this.currentFlaggedToken & 0x10000) != 0) {
            tt = 1;
        }
        return tt;
    }

    private boolean mustMatchToken(int toMatch, String messageId) throws IOException {
        return this.mustMatchToken(toMatch, messageId, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    }

    private boolean mustMatchToken(int toMatch, String msgId, int pos, int len) throws IOException {
        if (this.matchToken(toMatch)) {
            return true;
        }
        this.reportError(msgId, pos, len);
        return false;
    }

    private void mustHaveXML() {
        if (!this.compilerEnv.isXmlAvailable()) {
            this.reportError("msg.XML.not.available");
        }
    }

    public boolean eof() {
        return this.ts.eof();
    }

    boolean insideFunction() {
        return this.nestingOfFunction != 0;
    }

    void pushScope(Scope scope) {
        Scope parent = scope.getParentScope();
        if (parent != null) {
            if (parent != this.currentScope) {
                this.codeBug();
            }
        } else {
            this.currentScope.addChildScope(scope);
        }
        this.currentScope = scope;
    }

    void popScope() {
        this.currentScope = this.currentScope.getParentScope();
    }

    private void enterLoop(Loop loop) {
        if (this.loopSet == null) {
            this.loopSet = new ArrayList<Loop>();
        }
        this.loopSet.add(loop);
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList<Jump>();
        }
        this.loopAndSwitchSet.add(loop);
        this.pushScope(loop);
        if (this.currentLabel != null) {
            this.currentLabel.setStatement(loop);
            this.currentLabel.getFirstLabel().setLoop(loop);
            loop.setRelative(-this.currentLabel.getPosition());
        }
    }

    private void exitLoop() {
        Loop loop = this.loopSet.remove(this.loopSet.size() - 1);
        this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
        if (loop.getParent() != null) {
            loop.setRelative(loop.getParent().getPosition());
        }
        this.popScope();
    }

    private void enterSwitch(SwitchStatement node) {
        if (this.loopAndSwitchSet == null) {
            this.loopAndSwitchSet = new ArrayList<Jump>();
        }
        this.loopAndSwitchSet.add(node);
    }

    private void exitSwitch() {
        this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
    }

    public AstRoot parse(String sourceString, String sourceURI, int lineno) {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        }
        this.sourceURI = sourceURI;
        if (this.compilerEnv.isIdeMode()) {
            this.sourceChars = sourceString.toCharArray();
        }
        this.ts = new TokenStream(this, null, sourceString, lineno);
        try {
            AstRoot astRoot = this.parse();
            return astRoot;
        } catch (IOException iox) {
            throw new IllegalStateException();
        } finally {
            this.parseFinished = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public AstRoot parse(Reader sourceReader, String sourceURI, int lineno) throws IOException {
        if (this.parseFinished) {
            throw new IllegalStateException("parser reused");
        }
        if (this.compilerEnv.isIdeMode()) {
            return this.parse(this.readFully(sourceReader), sourceURI, lineno);
        }
        try {
            this.sourceURI = sourceURI;
            this.ts = new TokenStream(this, sourceReader, null, lineno);
            AstRoot astRoot = this.parse();
            return astRoot;
        } finally {
            this.parseFinished = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstRoot parse() throws IOException {
        int pos = 0;
        AstRoot root = new AstRoot(pos);
        this.currentScriptOrFn = root;
        this.currentScope = this.currentScriptOrFn;
        int baseLineno = this.ts.lineno;
        int end = pos;
        boolean inDirectivePrologue = true;
        boolean savedStrictMode = this.inUseStrictDirective;
        this.inUseStrictDirective = false;
        try {
            while (true) {
                AstNode n;
                block20: {
                    int tt;
                    if ((tt = this.peekToken()) <= 0) {
                        break;
                    }
                    if (tt == 109) {
                        this.consumeToken();
                        try {
                            n = this.function(this.calledByCompileFunction ? 2 : 1);
                            break block20;
                        } catch (ParserException e) {
                            break;
                        }
                    }
                    n = this.statement();
                    if (inDirectivePrologue) {
                        String directive = this.getDirective(n);
                        if (directive == null) {
                            inDirectivePrologue = false;
                        } else if (directive.equals("use strict")) {
                            this.inUseStrictDirective = true;
                            root.setInStrictMode(true);
                        }
                    }
                }
                end = this.getNodeEnd(n);
                root.addChildToBack(n);
                n.setParent(root);
            }
        } catch (StackOverflowError ex) {
            String msg = this.lookupMessage("msg.too.deep.parser.recursion");
            if (!this.compilerEnv.isIdeMode()) {
                throw Context.reportRuntimeError(msg, this.sourceURI, this.ts.lineno, null, 0);
            }
        } finally {
            this.inUseStrictDirective = savedStrictMode;
        }
        if (this.syntaxErrorCount != 0) {
            String msg = String.valueOf(this.syntaxErrorCount);
            msg = this.lookupMessage("msg.got.syntax.errors", msg);
            if (!this.compilerEnv.isIdeMode()) {
                throw this.errorReporter.runtimeError(msg, this.sourceURI, baseLineno, null, 0);
            }
        }
        if (this.scannedComments != null) {
            int last = this.scannedComments.size() - 1;
            end = Math.max(end, this.getNodeEnd(this.scannedComments.get(last)));
            for (Comment c : this.scannedComments) {
                root.addComment(c);
            }
        }
        root.setLength(end - pos);
        root.setSourceName(this.sourceURI);
        root.setBaseLineno(baseLineno);
        root.setEndLineno(this.ts.lineno);
        return root;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode parseFunctionBody() throws IOException {
        Block pn;
        int pos;
        boolean isExpressionClosure;
        block16: {
            isExpressionClosure = false;
            if (!this.matchToken(85)) {
                if (this.compilerEnv.getLanguageVersion() < 180) {
                    this.reportError("msg.no.brace.body");
                } else {
                    isExpressionClosure = true;
                }
            }
            ++this.nestingOfFunction;
            pos = this.ts.tokenBeg;
            pn = new Block(pos);
            boolean inDirectivePrologue = true;
            boolean savedStrictMode = this.inUseStrictDirective;
            pn.setLineno(this.ts.lineno);
            try {
                AstNode n;
                if (isExpressionClosure) {
                    n = new ReturnStatement(this.ts.lineno);
                    ((ReturnStatement)n).setReturnValue(this.assignExpr());
                    n.putProp(25, Boolean.TRUE);
                    pn.putProp(25, Boolean.TRUE);
                    pn.addStatement(n);
                    break block16;
                }
                while (true) {
                    int tt = this.peekToken();
                    switch (tt) {
                        case -1: 
                        case 0: 
                        case 86: {
                            break block16;
                        }
                        case 109: {
                            this.consumeToken();
                            n = this.function(1);
                            break;
                        }
                        default: {
                            n = this.statement();
                            if (!inDirectivePrologue) break;
                            String directive = this.getDirective(n);
                            if (directive == null) {
                                inDirectivePrologue = false;
                                break;
                            }
                            if (!directive.equals("use strict")) break;
                            this.inUseStrictDirective = true;
                        }
                    }
                    pn.addStatement(n);
                }
            } catch (ParserException e) {
            } finally {
                --this.nestingOfFunction;
                this.inUseStrictDirective = savedStrictMode;
            }
        }
        int end = this.ts.tokenEnd;
        this.getAndResetJsDoc();
        if (!isExpressionClosure && this.mustMatchToken(86, "msg.no.brace.after.body")) {
            end = this.ts.tokenEnd;
        }
        pn.setLength(end - pos);
        return pn;
    }

    private String getDirective(AstNode n) {
        AstNode e;
        if (n instanceof ExpressionStatement && (e = ((ExpressionStatement)n).getExpression()) instanceof StringLiteral) {
            return ((StringLiteral)e).getValue();
        }
        return null;
    }

    private void parseFunctionParams(FunctionNode fnNode) throws IOException {
        if (this.matchToken(88)) {
            fnNode.setRp(this.ts.tokenBeg - fnNode.getPosition());
            return;
        }
        HashMap<String, AstNode> destructuring = null;
        HashSet<String> paramNames = new HashSet<String>();
        do {
            int tt;
            if ((tt = this.peekToken()) == 83 || tt == 85) {
                AstNode expr = this.destructuringPrimaryExpr();
                this.markDestructuring(expr);
                fnNode.addParam(expr);
                if (destructuring == null) {
                    destructuring = new HashMap<String, AstNode>();
                }
                String pname = this.currentScriptOrFn.getNextTempName();
                this.defineSymbol(87, pname, false);
                destructuring.put(pname, expr);
                continue;
            }
            if (this.mustMatchToken(39, "msg.no.parm")) {
                fnNode.addParam(this.createNameNode());
                String paramName = this.ts.getString();
                this.defineSymbol(87, paramName);
                if (!this.inUseStrictDirective) continue;
                if ("eval".equals(paramName) || "arguments".equals(paramName)) {
                    this.reportError("msg.bad.id.strict", paramName);
                }
                if (paramNames.contains(paramName)) {
                    this.addError("msg.dup.param.strict", paramName);
                }
                paramNames.add(paramName);
                continue;
            }
            fnNode.addParam(this.makeErrorNode());
        } while (this.matchToken(89));
        if (destructuring != null) {
            Node destructuringNode = new Node(89);
            for (Map.Entry param : destructuring.entrySet()) {
                Node assign = this.createDestructuringAssignment(122, (Node)param.getValue(), this.createName((String)param.getKey()));
                destructuringNode.addChildToBack(assign);
            }
            fnNode.putProp(23, destructuringNode);
        }
        if (this.mustMatchToken(88, "msg.no.paren.after.parms")) {
            fnNode.setRp(this.ts.tokenBeg - fnNode.getPosition());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private FunctionNode function(int type) throws IOException {
        int lpPos;
        int syntheticType = type;
        int baseLineno = this.ts.lineno;
        int functionSourceStart = this.ts.tokenBeg;
        Name name = null;
        AstNode memberExprNode = null;
        if (this.matchToken(39)) {
            String id;
            name = this.createNameNode(true, 39);
            if (this.inUseStrictDirective && ("eval".equals(id = name.getIdentifier()) || "arguments".equals(id))) {
                this.reportError("msg.bad.id.strict", id);
            }
            if (!this.matchToken(87)) {
                if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                    Name memberExprHead = name;
                    name = null;
                    memberExprNode = this.memberExprTail(false, memberExprHead);
                }
                this.mustMatchToken(87, "msg.no.paren.parms");
            }
        } else if (!this.matchToken(87)) {
            if (this.compilerEnv.isAllowMemberExprAsFunctionName()) {
                memberExprNode = this.memberExpr(false);
            }
            this.mustMatchToken(87, "msg.no.paren.parms");
        }
        int n = lpPos = this.currentToken == 87 ? this.ts.tokenBeg : -1;
        if (memberExprNode != null) {
            syntheticType = 2;
        }
        if (syntheticType != 2 && name != null && name.length() > 0) {
            this.defineSymbol(109, name.getIdentifier());
        }
        FunctionNode fnNode = new FunctionNode(functionSourceStart, name);
        fnNode.setFunctionType(type);
        if (lpPos != -1) {
            fnNode.setLp(lpPos - functionSourceStart);
        }
        fnNode.setJsDocNode(this.getAndResetJsDoc());
        PerFunctionVariables savedVars = new PerFunctionVariables(fnNode);
        try {
            this.parseFunctionParams(fnNode);
            fnNode.setBody(this.parseFunctionBody());
            fnNode.setEncodedSourceBounds(functionSourceStart, this.ts.tokenEnd);
            fnNode.setLength(this.ts.tokenEnd - functionSourceStart);
            if (this.compilerEnv.isStrictMode() && !fnNode.getBody().hasConsistentReturnUsage()) {
                String msg = name != null && name.length() > 0 ? "msg.no.return.value" : "msg.anon.no.return.value";
                this.addStrictWarning(msg, name == null ? "" : name.getIdentifier());
            }
        } finally {
            savedVars.restore();
        }
        if (memberExprNode != null) {
            Kit.codeBug();
            fnNode.setMemberExprNode(memberExprNode);
        }
        fnNode.setSourceName(this.sourceURI);
        fnNode.setBaseLineno(baseLineno);
        fnNode.setEndLineno(this.ts.lineno);
        if (this.compilerEnv.isIdeMode()) {
            fnNode.setParentScope(this.currentScope);
        }
        return fnNode;
    }

    private AstNode statements(AstNode parent) throws IOException {
        int tt;
        if (this.currentToken != 85 && !this.compilerEnv.isIdeMode()) {
            this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        AstNode block = parent != null ? parent : new Block(pos);
        block.setLineno(this.ts.lineno);
        while ((tt = this.peekToken()) > 0 && tt != 86) {
            block.addChild(this.statement());
        }
        block.setLength(this.ts.tokenBeg - pos);
        return block;
    }

    private AstNode statements() throws IOException {
        return this.statements(null);
    }

    private ConditionData condition() throws IOException {
        ConditionData data = new ConditionData();
        if (this.mustMatchToken(87, "msg.no.paren.cond")) {
            data.lp = this.ts.tokenBeg;
        }
        data.condition = this.expr();
        if (this.mustMatchToken(88, "msg.no.paren.after.cond")) {
            data.rp = this.ts.tokenBeg;
        }
        if (data.condition instanceof Assignment) {
            this.addStrictWarning("msg.equal.as.assign", "", data.condition.getPosition(), data.condition.getLength());
        }
        return data;
    }

    private AstNode statement() throws IOException {
        int pos = this.ts.tokenBeg;
        try {
            AstNode pn = this.statementHelper();
            if (pn != null) {
                if (this.compilerEnv.isStrictMode() && !pn.hasSideEffects()) {
                    int beg = pn.getPosition();
                    beg = Math.max(beg, this.lineBeginningFor(beg));
                    this.addStrictWarning(pn instanceof EmptyStatement ? "msg.extra.trailing.semi" : "msg.no.side.effects", "", beg, this.nodeEnd(pn) - beg);
                }
                return pn;
            }
        } catch (ParserException e) {
            // empty catch block
        }
        block5: while (true) {
            int tt = this.peekTokenOrEOL();
            this.consumeToken();
            switch (tt) {
                case -1: 
                case 0: 
                case 1: 
                case 82: {
                    break block5;
                }
                default: {
                    continue block5;
                }
            }
            break;
        }
        return new EmptyStatement(pos, this.ts.tokenBeg - pos);
    }

    private AstNode statementHelper() throws IOException {
        if (this.currentLabel != null && this.currentLabel.getStatement() != null) {
            this.currentLabel = null;
        }
        AstNode pn = null;
        int tt = this.peekToken();
        int pos = this.ts.tokenBeg;
        switch (tt) {
            case 112: {
                return this.ifStatement();
            }
            case 114: {
                return this.switchStatement();
            }
            case 117: {
                return this.whileLoop();
            }
            case 118: {
                return this.doLoop();
            }
            case 119: {
                return this.forLoop();
            }
            case 81: {
                return this.tryStatement();
            }
            case 50: {
                pn = this.throwStatement();
                break;
            }
            case 120: {
                pn = this.breakStatement();
                break;
            }
            case 121: {
                pn = this.continueStatement();
                break;
            }
            case 123: {
                if (this.inUseStrictDirective) {
                    this.reportError("msg.no.with.strict");
                }
                return this.withStatement();
            }
            case 122: 
            case 154: {
                this.consumeToken();
                int lineno = this.ts.lineno;
                pn = this.variables(this.currentToken, this.ts.tokenBeg, true);
                pn.setLineno(lineno);
                break;
            }
            case 153: {
                pn = this.letStatement();
                if (pn instanceof VariableDeclaration && this.peekToken() == 82) break;
                return pn;
            }
            case 4: 
            case 72: {
                pn = this.returnOrYield(tt, false);
                break;
            }
            case 160: {
                this.consumeToken();
                pn = new KeywordLiteral(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg, tt);
                pn.setLineno(this.ts.lineno);
                break;
            }
            case 85: {
                return this.block();
            }
            case -1: {
                this.consumeToken();
                return this.makeErrorNode();
            }
            case 82: {
                this.consumeToken();
                pos = this.ts.tokenBeg;
                pn = new EmptyStatement(pos, this.ts.tokenEnd - pos);
                pn.setLineno(this.ts.lineno);
                return pn;
            }
            case 109: {
                this.consumeToken();
                return this.function(3);
            }
            case 116: {
                pn = this.defaultXmlNamespace();
                break;
            }
            case 39: {
                pn = this.nameOrLabel();
                if (pn instanceof ExpressionStatement) break;
                return pn;
            }
            default: {
                int lineno = this.ts.lineno;
                pn = new ExpressionStatement(this.expr(), !this.insideFunction());
                pn.setLineno(lineno);
            }
        }
        this.autoInsertSemicolon(pn);
        return pn;
    }

    private void autoInsertSemicolon(AstNode pn) throws IOException {
        int ttFlagged = this.peekFlaggedToken();
        int pos = pn.getPosition();
        switch (ttFlagged & 0xFFFF) {
            case 82: {
                this.consumeToken();
                pn.setLength(this.ts.tokenEnd - pos);
                break;
            }
            case -1: 
            case 0: 
            case 86: {
                this.warnMissingSemi(pos, this.nodeEnd(pn));
                break;
            }
            default: {
                if ((ttFlagged & 0x10000) == 0) {
                    this.reportError("msg.no.semi.stmt");
                    break;
                }
                this.warnMissingSemi(pos, this.nodeEnd(pn));
            }
        }
    }

    private IfStatement ifStatement() throws IOException {
        if (this.currentToken != 112) {
            this.codeBug();
        }
        this.consumeToken();
        int pos = this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        int elsePos = -1;
        ConditionData data = this.condition();
        AstNode ifTrue = this.statement();
        AstNode ifFalse = null;
        if (this.matchToken(113)) {
            elsePos = this.ts.tokenBeg - pos;
            ifFalse = this.statement();
        }
        int end = this.getNodeEnd(ifFalse != null ? ifFalse : ifTrue);
        IfStatement pn = new IfStatement(pos, end - pos);
        pn.setCondition(data.condition);
        pn.setParens(data.lp - pos, data.rp - pos);
        pn.setThenPart(ifTrue);
        pn.setElsePart(ifFalse);
        pn.setElsePosition(elsePos);
        pn.setLineno(lineno);
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private SwitchStatement switchStatement() throws IOException {
        SwitchStatement pn;
        block14: {
            if (this.currentToken != 114) {
                this.codeBug();
            }
            this.consumeToken();
            int pos = this.ts.tokenBeg;
            pn = new SwitchStatement(pos);
            if (this.mustMatchToken(87, "msg.no.paren.switch")) {
                pn.setLp(this.ts.tokenBeg - pos);
            }
            pn.setLineno(this.ts.lineno);
            AstNode discriminant = this.expr();
            pn.setExpression(discriminant);
            this.enterSwitch(pn);
            try {
                if (this.mustMatchToken(88, "msg.no.paren.after.switch")) {
                    pn.setRp(this.ts.tokenBeg - pos);
                }
                this.mustMatchToken(85, "msg.no.brace.switch");
                boolean hasDefault = false;
                while (true) {
                    int tt = this.nextToken();
                    int casePos = this.ts.tokenBeg;
                    int caseLineno = this.ts.lineno;
                    AstNode caseExpression = null;
                    switch (tt) {
                        case 86: {
                            pn.setLength(this.ts.tokenEnd - pos);
                            break block14;
                        }
                        case 115: {
                            caseExpression = this.expr();
                            this.mustMatchToken(103, "msg.no.colon.case");
                            break;
                        }
                        case 116: {
                            if (hasDefault) {
                                this.reportError("msg.double.switch.default");
                            }
                            hasDefault = true;
                            caseExpression = null;
                            this.mustMatchToken(103, "msg.no.colon.case");
                            break;
                        }
                        default: {
                            this.reportError("msg.bad.switch");
                            break block14;
                        }
                    }
                    SwitchCase caseNode = new SwitchCase(casePos);
                    caseNode.setExpression(caseExpression);
                    caseNode.setLength(this.ts.tokenEnd - pos);
                    caseNode.setLineno(caseLineno);
                    while ((tt = this.peekToken()) != 86 && tt != 115 && tt != 116 && tt != 0) {
                        caseNode.addStatement(this.statement());
                    }
                    pn.addCase(caseNode);
                }
            } finally {
                this.exitSwitch();
            }
        }
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private WhileLoop whileLoop() throws IOException {
        if (this.currentToken != 117) {
            this.codeBug();
        }
        this.consumeToken();
        int pos = this.ts.tokenBeg;
        WhileLoop pn = new WhileLoop(pos);
        pn.setLineno(this.ts.lineno);
        this.enterLoop(pn);
        try {
            ConditionData data = this.condition();
            pn.setCondition(data.condition);
            pn.setParens(data.lp - pos, data.rp - pos);
            AstNode body = this.statement();
            pn.setLength(this.getNodeEnd(body) - pos);
            pn.setBody(body);
        } finally {
            this.exitLoop();
        }
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private DoLoop doLoop() throws IOException {
        int end;
        if (this.currentToken != 118) {
            this.codeBug();
        }
        this.consumeToken();
        int pos = this.ts.tokenBeg;
        DoLoop pn = new DoLoop(pos);
        pn.setLineno(this.ts.lineno);
        this.enterLoop(pn);
        try {
            AstNode body = this.statement();
            this.mustMatchToken(117, "msg.no.while.do");
            pn.setWhilePosition(this.ts.tokenBeg - pos);
            ConditionData data = this.condition();
            pn.setCondition(data.condition);
            pn.setParens(data.lp - pos, data.rp - pos);
            end = this.getNodeEnd(body);
            pn.setBody(body);
        } finally {
            this.exitLoop();
        }
        if (this.matchToken(82)) {
            end = this.ts.tokenEnd;
        }
        pn.setLength(end - pos);
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Loop forLoop() throws IOException {
        if (this.currentToken != 119) {
            this.codeBug();
        }
        this.consumeToken();
        int forPos = this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        boolean isForEach = false;
        boolean isForIn = false;
        int eachPos = -1;
        int inPos = -1;
        int lp = -1;
        int rp = -1;
        AstNode init = null;
        AstNode cond = null;
        AstNode incr = null;
        Loop pn = null;
        Scope tempScope = new Scope();
        this.pushScope(tempScope);
        try {
            if (this.matchToken(39)) {
                if ("each".equals(this.ts.getString())) {
                    isForEach = true;
                    eachPos = this.ts.tokenBeg - forPos;
                } else {
                    this.reportError("msg.no.paren.for");
                }
            }
            if (this.mustMatchToken(87, "msg.no.paren.for")) {
                lp = this.ts.tokenBeg - forPos;
            }
            int tt = this.peekToken();
            init = this.forLoopInit(tt);
            if (this.matchToken(52)) {
                isForIn = true;
                inPos = this.ts.tokenBeg - forPos;
                cond = this.expr();
            } else {
                this.mustMatchToken(82, "msg.no.semi.for");
                if (this.peekToken() == 82) {
                    cond = new EmptyExpression(this.ts.tokenBeg, 1);
                    cond.setLineno(this.ts.lineno);
                } else {
                    cond = this.expr();
                }
                this.mustMatchToken(82, "msg.no.semi.for.cond");
                int tmpPos = this.ts.tokenEnd;
                if (this.peekToken() == 88) {
                    incr = new EmptyExpression(tmpPos, 1);
                    incr.setLineno(this.ts.lineno);
                } else {
                    incr = this.expr();
                }
            }
            if (this.mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                rp = this.ts.tokenBeg - forPos;
            }
            if (isForIn) {
                ForInLoop fis = new ForInLoop(forPos);
                if (init instanceof VariableDeclaration && ((VariableDeclaration)init).getVariables().size() > 1) {
                    this.reportError("msg.mult.index");
                }
                fis.setIterator(init);
                fis.setIteratedObject(cond);
                fis.setInPosition(inPos);
                fis.setIsForEach(isForEach);
                fis.setEachPosition(eachPos);
                pn = fis;
            } else {
                ForLoop fl = new ForLoop(forPos);
                fl.setInitializer(init);
                fl.setCondition(cond);
                fl.setIncrement(incr);
                pn = fl;
            }
            this.currentScope.replaceWith(pn);
            this.popScope();
            this.enterLoop(pn);
            try {
                AstNode body = this.statement();
                pn.setLength(this.getNodeEnd(body) - forPos);
                pn.setBody(body);
            } finally {
                this.exitLoop();
            }
        } finally {
            if (this.currentScope == tempScope) {
                this.popScope();
            }
        }
        pn.setParens(lp, rp);
        pn.setLineno(lineno);
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode forLoopInit(int tt) throws IOException {
        try {
            this.inForInit = true;
            AstNode init = null;
            if (tt == 82) {
                init = new EmptyExpression(this.ts.tokenBeg, 1);
                init.setLineno(this.ts.lineno);
            } else if (tt == 122 || tt == 153) {
                this.consumeToken();
                init = this.variables(tt, this.ts.tokenBeg, false);
            } else {
                init = this.expr();
                this.markDestructuring(init);
            }
            AstNode astNode = init;
            return astNode;
        } finally {
            this.inForInit = false;
        }
    }

    private TryStatement tryStatement() throws IOException {
        if (this.currentToken != 81) {
            this.codeBug();
        }
        this.consumeToken();
        Comment jsdocNode = this.getAndResetJsDoc();
        int tryPos = this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        int finallyPos = -1;
        if (this.peekToken() != 85) {
            this.reportError("msg.no.brace.try");
        }
        AstNode tryBlock = this.statement();
        int tryEnd = this.getNodeEnd(tryBlock);
        ArrayList<CatchClause> clauses = null;
        boolean sawDefaultCatch = false;
        int peek = this.peekToken();
        if (peek == 124) {
            while (this.matchToken(124)) {
                int catchLineNum = this.ts.lineno;
                if (sawDefaultCatch) {
                    this.reportError("msg.catch.unreachable");
                }
                int catchPos = this.ts.tokenBeg;
                int lp = -1;
                int rp = -1;
                int guardPos = -1;
                if (this.mustMatchToken(87, "msg.no.paren.catch")) {
                    lp = this.ts.tokenBeg;
                }
                this.mustMatchToken(39, "msg.bad.catchcond");
                Name varName = this.createNameNode();
                String varNameString = varName.getIdentifier();
                if (this.inUseStrictDirective && ("eval".equals(varNameString) || "arguments".equals(varNameString))) {
                    this.reportError("msg.bad.id.strict", varNameString);
                }
                AstNode catchCond = null;
                if (this.matchToken(112)) {
                    guardPos = this.ts.tokenBeg;
                    catchCond = this.expr();
                } else {
                    sawDefaultCatch = true;
                }
                if (this.mustMatchToken(88, "msg.bad.catchcond")) {
                    rp = this.ts.tokenBeg;
                }
                this.mustMatchToken(85, "msg.no.brace.catchblock");
                Block catchBlock = (Block)this.statements();
                tryEnd = this.getNodeEnd(catchBlock);
                CatchClause catchNode = new CatchClause(catchPos);
                catchNode.setVarName(varName);
                catchNode.setCatchCondition(catchCond);
                catchNode.setBody(catchBlock);
                if (guardPos != -1) {
                    catchNode.setIfPosition(guardPos - catchPos);
                }
                catchNode.setParens(lp, rp);
                catchNode.setLineno(catchLineNum);
                if (this.mustMatchToken(86, "msg.no.brace.after.body")) {
                    tryEnd = this.ts.tokenEnd;
                }
                catchNode.setLength(tryEnd - catchPos);
                if (clauses == null) {
                    clauses = new ArrayList<CatchClause>();
                }
                clauses.add(catchNode);
            }
        } else if (peek != 125) {
            this.mustMatchToken(125, "msg.try.no.catchfinally");
        }
        AstNode finallyBlock = null;
        if (this.matchToken(125)) {
            finallyPos = this.ts.tokenBeg;
            finallyBlock = this.statement();
            tryEnd = this.getNodeEnd(finallyBlock);
        }
        TryStatement pn = new TryStatement(tryPos, tryEnd - tryPos);
        pn.setTryBlock(tryBlock);
        pn.setCatchClauses(clauses);
        pn.setFinallyBlock(finallyBlock);
        if (finallyPos != -1) {
            pn.setFinallyPosition(finallyPos - tryPos);
        }
        pn.setLineno(lineno);
        if (jsdocNode != null) {
            pn.setJsDocNode(jsdocNode);
        }
        return pn;
    }

    private ThrowStatement throwStatement() throws IOException {
        if (this.currentToken != 50) {
            this.codeBug();
        }
        this.consumeToken();
        int pos = this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        if (this.peekTokenOrEOL() == 1) {
            this.reportError("msg.bad.throw.eol");
        }
        AstNode expr = this.expr();
        ThrowStatement pn = new ThrowStatement(pos, this.getNodeEnd(expr), expr);
        pn.setLineno(lineno);
        return pn;
    }

    private LabeledStatement matchJumpLabelName() throws IOException {
        LabeledStatement label = null;
        if (this.peekTokenOrEOL() == 39) {
            this.consumeToken();
            if (this.labelSet != null) {
                label = this.labelSet.get(this.ts.getString());
            }
            if (label == null) {
                this.reportError("msg.undef.label");
            }
        }
        return label;
    }

    private BreakStatement breakStatement() throws IOException {
        LabeledStatement labels;
        Jump breakTarget;
        if (this.currentToken != 120) {
            this.codeBug();
        }
        this.consumeToken();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        int end = this.ts.tokenEnd;
        Name breakLabel = null;
        if (this.peekTokenOrEOL() == 39) {
            breakLabel = this.createNameNode();
            end = this.getNodeEnd(breakLabel);
        }
        Label label = breakTarget = (labels = this.matchJumpLabelName()) == null ? null : labels.getFirstLabel();
        if (breakTarget == null && breakLabel == null) {
            if (this.loopAndSwitchSet == null || this.loopAndSwitchSet.size() == 0) {
                if (breakLabel == null) {
                    this.reportError("msg.bad.break", pos, end - pos);
                }
            } else {
                breakTarget = this.loopAndSwitchSet.get(this.loopAndSwitchSet.size() - 1);
            }
        }
        BreakStatement pn = new BreakStatement(pos, end - pos);
        pn.setBreakLabel(breakLabel);
        if (breakTarget != null) {
            pn.setBreakTarget(breakTarget);
        }
        pn.setLineno(lineno);
        return pn;
    }

    private ContinueStatement continueStatement() throws IOException {
        if (this.currentToken != 121) {
            this.codeBug();
        }
        this.consumeToken();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        int end = this.ts.tokenEnd;
        Name label = null;
        if (this.peekTokenOrEOL() == 39) {
            label = this.createNameNode();
            end = this.getNodeEnd(label);
        }
        LabeledStatement labels = this.matchJumpLabelName();
        Loop target = null;
        if (labels == null && label == null) {
            if (this.loopSet == null || this.loopSet.size() == 0) {
                this.reportError("msg.continue.outside");
            } else {
                target = this.loopSet.get(this.loopSet.size() - 1);
            }
        } else {
            if (labels == null || !(labels.getStatement() instanceof Loop)) {
                this.reportError("msg.continue.nonloop", pos, end - pos);
            }
            target = labels == null ? null : (Loop)labels.getStatement();
        }
        ContinueStatement pn = new ContinueStatement(pos, end - pos);
        if (target != null) {
            pn.setTarget(target);
        }
        pn.setLabel(label);
        pn.setLineno(lineno);
        return pn;
    }

    private WithStatement withStatement() throws IOException {
        if (this.currentToken != 123) {
            this.codeBug();
        }
        this.consumeToken();
        Comment withComment = this.getAndResetJsDoc();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        int lp = -1;
        int rp = -1;
        if (this.mustMatchToken(87, "msg.no.paren.with")) {
            lp = this.ts.tokenBeg;
        }
        AstNode obj = this.expr();
        if (this.mustMatchToken(88, "msg.no.paren.after.with")) {
            rp = this.ts.tokenBeg;
        }
        AstNode body = this.statement();
        WithStatement pn = new WithStatement(pos, this.getNodeEnd(body) - pos);
        pn.setJsDocNode(withComment);
        pn.setExpression(obj);
        pn.setStatement(body);
        pn.setParens(lp, rp);
        pn.setLineno(lineno);
        return pn;
    }

    private AstNode letStatement() throws IOException {
        if (this.currentToken != 153) {
            this.codeBug();
        }
        this.consumeToken();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        AstNode pn = this.peekToken() == 87 ? this.let(true, pos) : this.variables(153, pos, true);
        pn.setLineno(lineno);
        return pn;
    }

    private static final boolean nowAllSet(int before, int after, int mask) {
        return (before & mask) != mask && (after & mask) == mask;
    }

    private AstNode returnOrYield(int tt, boolean exprContext) throws IOException {
        AstNode ret;
        if (!this.insideFunction()) {
            this.reportError(tt == 4 ? "msg.bad.return" : "msg.bad.yield");
        }
        this.consumeToken();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        int end = this.ts.tokenEnd;
        AstNode e = null;
        switch (this.peekTokenOrEOL()) {
            case -1: 
            case 0: 
            case 1: 
            case 72: 
            case 82: 
            case 84: 
            case 86: 
            case 88: {
                break;
            }
            default: {
                e = this.expr();
                end = this.getNodeEnd(e);
            }
        }
        int before = this.endFlags;
        if (tt == 4) {
            this.endFlags |= e == null ? 2 : 4;
            ret = new ReturnStatement(pos, end - pos, e);
            if (Parser.nowAllSet(before, this.endFlags, 6)) {
                this.addStrictWarning("msg.return.inconsistent", "", pos, end - pos);
            }
        } else {
            if (!this.insideFunction()) {
                this.reportError("msg.bad.yield");
            }
            this.endFlags |= 8;
            ret = new Yield(pos, end - pos, e);
            this.setRequiresActivation();
            this.setIsGenerator();
            if (!exprContext) {
                ret = new ExpressionStatement(ret);
            }
        }
        if (this.insideFunction() && Parser.nowAllSet(before, this.endFlags, 12)) {
            Name name = ((FunctionNode)this.currentScriptOrFn).getFunctionName();
            if (name == null || name.length() == 0) {
                this.addError("msg.anon.generator.returns", "");
            } else {
                this.addError("msg.generator.returns", name.getIdentifier());
            }
        }
        ret.setLineno(lineno);
        return ret;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode block() throws IOException {
        if (this.currentToken != 85) {
            this.codeBug();
        }
        this.consumeToken();
        int pos = this.ts.tokenBeg;
        Scope block = new Scope(pos);
        block.setLineno(this.ts.lineno);
        this.pushScope(block);
        try {
            this.statements(block);
            this.mustMatchToken(86, "msg.no.brace.block");
            block.setLength(this.ts.tokenEnd - pos);
            Scope scope = block;
            return scope;
        } finally {
            this.popScope();
        }
    }

    private AstNode defaultXmlNamespace() throws IOException {
        if (this.currentToken != 116) {
            this.codeBug();
        }
        this.consumeToken();
        this.mustHaveXML();
        this.setRequiresActivation();
        int lineno = this.ts.lineno;
        int pos = this.ts.tokenBeg;
        if (!this.matchToken(39) || !"xml".equals(this.ts.getString())) {
            this.reportError("msg.bad.namespace");
        }
        if (!this.matchToken(39) || !"namespace".equals(this.ts.getString())) {
            this.reportError("msg.bad.namespace");
        }
        if (!this.matchToken(90)) {
            this.reportError("msg.bad.namespace");
        }
        AstNode e = this.expr();
        UnaryExpression dxmln = new UnaryExpression(pos, this.getNodeEnd(e) - pos);
        dxmln.setOperator(74);
        dxmln.setOperand(e);
        dxmln.setLineno(lineno);
        ExpressionStatement es = new ExpressionStatement(dxmln, true);
        return es;
    }

    private void recordLabel(Label label, LabeledStatement bundle) throws IOException {
        if (this.peekToken() != 103) {
            this.codeBug();
        }
        this.consumeToken();
        String name = label.getName();
        if (this.labelSet == null) {
            this.labelSet = new HashMap<String, LabeledStatement>();
        } else {
            LabeledStatement ls = this.labelSet.get(name);
            if (ls != null) {
                if (this.compilerEnv.isIdeMode()) {
                    Label dup = ls.getLabelByName(name);
                    this.reportError("msg.dup.label", dup.getAbsolutePosition(), dup.getLength());
                }
                this.reportError("msg.dup.label", label.getPosition(), label.getLength());
            }
        }
        bundle.addLabel(label);
        this.labelSet.put(name, bundle);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode nameOrLabel() throws IOException {
        if (this.currentToken != 39) {
            throw this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        this.currentFlaggedToken |= 0x20000;
        AstNode expr = this.expr();
        if (expr.getType() != 130) {
            ExpressionStatement n = new ExpressionStatement(expr, !this.insideFunction());
            n.lineno = expr.lineno;
            return n;
        }
        LabeledStatement bundle = new LabeledStatement(pos);
        this.recordLabel((Label)expr, bundle);
        bundle.setLineno(this.ts.lineno);
        AstNode stmt = null;
        while (this.peekToken() == 39) {
            this.currentFlaggedToken |= 0x20000;
            expr = this.expr();
            if (expr.getType() != 130) {
                stmt = new ExpressionStatement(expr, !this.insideFunction());
                this.autoInsertSemicolon(stmt);
                break;
            }
            this.recordLabel((Label)expr, bundle);
        }
        try {
            this.currentLabel = bundle;
            if (stmt == null) {
                stmt = this.statementHelper();
            }
        } finally {
            this.currentLabel = null;
            for (Label lb : bundle.getLabels()) {
                this.labelSet.remove(lb.getName());
            }
        }
        bundle.setLength(stmt.getParent() == null ? this.getNodeEnd(stmt) - pos : this.getNodeEnd(stmt));
        bundle.setStatement(stmt);
        return bundle;
    }

    private VariableDeclaration variables(int declType, int pos, boolean isStatement) throws IOException {
        int end;
        VariableDeclaration pn = new VariableDeclaration(pos);
        pn.setType(declType);
        pn.setLineno(this.ts.lineno);
        Comment varjsdocNode = this.getAndResetJsDoc();
        if (varjsdocNode != null) {
            pn.setJsDocNode(varjsdocNode);
        }
        do {
            AstNode destructuring = null;
            Name name = null;
            int tt = this.peekToken();
            int kidPos = this.ts.tokenBeg;
            end = this.ts.tokenEnd;
            if (tt == 83 || tt == 85) {
                destructuring = this.destructuringPrimaryExpr();
                end = this.getNodeEnd(destructuring);
                if (!(destructuring instanceof DestructuringForm)) {
                    this.reportError("msg.bad.assign.left", kidPos, end - kidPos);
                }
                this.markDestructuring(destructuring);
            } else {
                String id;
                this.mustMatchToken(39, "msg.bad.var");
                name = this.createNameNode();
                name.setLineno(this.ts.getLineno());
                if (this.inUseStrictDirective && ("eval".equals(id = this.ts.getString()) || "arguments".equals(this.ts.getString()))) {
                    this.reportError("msg.bad.id.strict", id);
                }
                this.defineSymbol(declType, this.ts.getString(), this.inForInit);
            }
            int lineno = this.ts.lineno;
            Comment jsdocNode = this.getAndResetJsDoc();
            AstNode init = null;
            if (this.matchToken(90)) {
                init = this.assignExpr();
                end = this.getNodeEnd(init);
            }
            VariableInitializer vi = new VariableInitializer(kidPos, end - kidPos);
            if (destructuring != null) {
                if (init == null && !this.inForInit) {
                    this.reportError("msg.destruct.assign.no.init");
                }
                vi.setTarget(destructuring);
            } else {
                vi.setTarget(name);
            }
            vi.setInitializer(init);
            vi.setType(declType);
            vi.setJsDocNode(jsdocNode);
            vi.setLineno(lineno);
            pn.addVariable(vi);
        } while (this.matchToken(89));
        pn.setLength(end - pos);
        pn.setIsStatement(isStatement);
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode let(boolean isStatement, int pos) throws IOException {
        LetNode pn = new LetNode(pos);
        pn.setLineno(this.ts.lineno);
        if (this.mustMatchToken(87, "msg.no.paren.after.let")) {
            pn.setLp(this.ts.tokenBeg - pos);
        }
        this.pushScope(pn);
        try {
            VariableDeclaration vars = this.variables(153, this.ts.tokenBeg, isStatement);
            pn.setVariables(vars);
            if (this.mustMatchToken(88, "msg.no.paren.let")) {
                pn.setRp(this.ts.tokenBeg - pos);
            }
            if (isStatement && this.peekToken() == 85) {
                this.consumeToken();
                int beg = this.ts.tokenBeg;
                AstNode stmt = this.statements();
                this.mustMatchToken(86, "msg.no.curly.let");
                stmt.setLength(this.ts.tokenEnd - beg);
                pn.setLength(this.ts.tokenEnd - pos);
                pn.setBody(stmt);
                pn.setType(153);
            } else {
                AstNode expr = this.expr();
                pn.setLength(this.getNodeEnd(expr) - pos);
                pn.setBody(expr);
                if (isStatement) {
                    ExpressionStatement es = new ExpressionStatement(pn, !this.insideFunction());
                    es.setLineno(pn.getLineno());
                    ExpressionStatement expressionStatement = es;
                    return expressionStatement;
                }
            }
        } finally {
            this.popScope();
        }
        return pn;
    }

    void defineSymbol(int declType, String name) {
        this.defineSymbol(declType, name, false);
    }

    void defineSymbol(int declType, String name, boolean ignoreNotInBlock) {
        int symDeclType;
        Scope definingScope;
        if (name == null) {
            if (this.compilerEnv.isIdeMode()) {
                return;
            }
            this.codeBug();
        }
        Symbol symbol = (definingScope = this.currentScope.getDefiningScope(name)) != null ? definingScope.getSymbol(name) : null;
        int n = symDeclType = symbol != null ? symbol.getDeclType() : -1;
        if (symbol != null && (symDeclType == 154 || declType == 154 || definingScope == this.currentScope && symDeclType == 153)) {
            this.addError(symDeclType == 154 ? "msg.const.redecl" : (symDeclType == 153 ? "msg.let.redecl" : (symDeclType == 122 ? "msg.var.redecl" : (symDeclType == 109 ? "msg.fn.redecl" : "msg.parm.redecl"))), name);
            return;
        }
        switch (declType) {
            case 153: {
                if (!ignoreNotInBlock && (this.currentScope.getType() == 112 || this.currentScope instanceof Loop)) {
                    this.addError("msg.let.decl.not.in.block");
                    return;
                }
                this.currentScope.putSymbol(new Symbol(declType, name));
                return;
            }
            case 109: 
            case 122: 
            case 154: {
                if (symbol != null) {
                    if (symDeclType == 122) {
                        this.addStrictWarning("msg.var.redecl", name);
                    } else if (symDeclType == 87) {
                        this.addStrictWarning("msg.var.hides.arg", name);
                    }
                } else {
                    this.currentScriptOrFn.putSymbol(new Symbol(declType, name));
                }
                return;
            }
            case 87: {
                if (symbol != null) {
                    this.addWarning("msg.dup.parms", name);
                }
                this.currentScriptOrFn.putSymbol(new Symbol(declType, name));
                return;
            }
        }
        throw this.codeBug();
    }

    private AstNode expr() throws IOException {
        AstNode pn = this.assignExpr();
        int pos = pn.getPosition();
        while (this.matchToken(89)) {
            int opPos = this.ts.tokenBeg;
            if (this.compilerEnv.isStrictMode() && !pn.hasSideEffects()) {
                this.addStrictWarning("msg.no.side.effects", "", pos, this.nodeEnd(pn) - pos);
            }
            if (this.peekToken() == 72) {
                this.reportError("msg.yield.parenthesized");
            }
            pn = new InfixExpression(89, pn, this.assignExpr(), opPos);
        }
        return pn;
    }

    private AstNode assignExpr() throws IOException {
        int tt = this.peekToken();
        if (tt == 72) {
            return this.returnOrYield(tt, true);
        }
        AstNode pn = this.condExpr();
        tt = this.peekToken();
        if (90 <= tt && tt <= 101) {
            this.consumeToken();
            Comment jsdocNode = this.getAndResetJsDoc();
            this.markDestructuring(pn);
            int opPos = this.ts.tokenBeg;
            pn = new Assignment(tt, pn, this.assignExpr(), opPos);
            if (jsdocNode != null) {
                pn.setJsDocNode(jsdocNode);
            }
        } else if (tt == 82 && this.currentJsDocComment != null) {
            pn.setJsDocNode(this.getAndResetJsDoc());
        }
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode condExpr() throws IOException {
        AstNode pn = this.orExpr();
        if (this.matchToken(102)) {
            AstNode ifTrue;
            int line = this.ts.lineno;
            int qmarkPos = this.ts.tokenBeg;
            int colonPos = -1;
            boolean wasInForInit = this.inForInit;
            this.inForInit = false;
            try {
                ifTrue = this.assignExpr();
            } finally {
                this.inForInit = wasInForInit;
            }
            if (this.mustMatchToken(103, "msg.no.colon.cond")) {
                colonPos = this.ts.tokenBeg;
            }
            AstNode ifFalse = this.assignExpr();
            int beg = pn.getPosition();
            int len = this.getNodeEnd(ifFalse) - beg;
            ConditionalExpression ce = new ConditionalExpression(beg, len);
            ce.setLineno(line);
            ce.setTestExpression(pn);
            ce.setTrueExpression(ifTrue);
            ce.setFalseExpression(ifFalse);
            ce.setQuestionMarkPosition(qmarkPos - beg);
            ce.setColonPosition(colonPos - beg);
            pn = ce;
        }
        return pn;
    }

    private AstNode orExpr() throws IOException {
        AstNode pn = this.andExpr();
        if (this.matchToken(104)) {
            int opPos = this.ts.tokenBeg;
            pn = new InfixExpression(104, pn, this.orExpr(), opPos);
        }
        return pn;
    }

    private AstNode andExpr() throws IOException {
        AstNode pn = this.bitOrExpr();
        if (this.matchToken(105)) {
            int opPos = this.ts.tokenBeg;
            pn = new InfixExpression(105, pn, this.andExpr(), opPos);
        }
        return pn;
    }

    private AstNode bitOrExpr() throws IOException {
        AstNode pn = this.bitXorExpr();
        while (this.matchToken(9)) {
            int opPos = this.ts.tokenBeg;
            pn = new InfixExpression(9, pn, this.bitXorExpr(), opPos);
        }
        return pn;
    }

    private AstNode bitXorExpr() throws IOException {
        AstNode pn = this.bitAndExpr();
        while (this.matchToken(10)) {
            int opPos = this.ts.tokenBeg;
            pn = new InfixExpression(10, pn, this.bitAndExpr(), opPos);
        }
        return pn;
    }

    private AstNode bitAndExpr() throws IOException {
        AstNode pn = this.eqExpr();
        while (this.matchToken(11)) {
            int opPos = this.ts.tokenBeg;
            pn = new InfixExpression(11, pn, this.eqExpr(), opPos);
        }
        return pn;
    }

    private AstNode eqExpr() throws IOException {
        AstNode pn = this.relExpr();
        block3: while (true) {
            int tt = this.peekToken();
            int opPos = this.ts.tokenBeg;
            switch (tt) {
                case 12: 
                case 13: 
                case 46: 
                case 47: {
                    this.consumeToken();
                    int parseToken = tt;
                    if (this.compilerEnv.getLanguageVersion() == 120) {
                        if (tt == 12) {
                            parseToken = 46;
                        } else if (tt == 13) {
                            parseToken = 47;
                        }
                    }
                    pn = new InfixExpression(parseToken, pn, this.relExpr(), opPos);
                    continue block3;
                }
            }
            break;
        }
        return pn;
    }

    private AstNode relExpr() throws IOException {
        AstNode pn = this.shiftExpr();
        block4: while (true) {
            int tt = this.peekToken();
            int opPos = this.ts.tokenBeg;
            switch (tt) {
                case 52: {
                    if (this.inForInit) break block4;
                }
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                case 53: {
                    this.consumeToken();
                    pn = new InfixExpression(tt, pn, this.shiftExpr(), opPos);
                    continue block4;
                }
            }
            break;
        }
        return pn;
    }

    private AstNode shiftExpr() throws IOException {
        AstNode pn = this.addExpr();
        block3: while (true) {
            int tt = this.peekToken();
            int opPos = this.ts.tokenBeg;
            switch (tt) {
                case 18: 
                case 19: 
                case 20: {
                    this.consumeToken();
                    pn = new InfixExpression(tt, pn, this.addExpr(), opPos);
                    continue block3;
                }
            }
            break;
        }
        return pn;
    }

    private AstNode addExpr() throws IOException {
        AstNode pn = this.mulExpr();
        while (true) {
            int tt = this.peekToken();
            int opPos = this.ts.tokenBeg;
            if (tt != 21 && tt != 22) break;
            this.consumeToken();
            pn = new InfixExpression(tt, pn, this.mulExpr(), opPos);
        }
        return pn;
    }

    private AstNode mulExpr() throws IOException {
        AstNode pn = this.unaryExpr();
        block3: while (true) {
            int tt = this.peekToken();
            int opPos = this.ts.tokenBeg;
            switch (tt) {
                case 23: 
                case 24: 
                case 25: {
                    this.consumeToken();
                    pn = new InfixExpression(tt, pn, this.unaryExpr(), opPos);
                    continue block3;
                }
            }
            break;
        }
        return pn;
    }

    private AstNode unaryExpr() throws IOException {
        int tt = this.peekToken();
        int line = this.ts.lineno;
        switch (tt) {
            case 26: 
            case 27: 
            case 32: 
            case 126: {
                this.consumeToken();
                UnaryExpression node = new UnaryExpression(tt, this.ts.tokenBeg, this.unaryExpr());
                node.setLineno(line);
                return node;
            }
            case 21: {
                this.consumeToken();
                UnaryExpression node = new UnaryExpression(28, this.ts.tokenBeg, this.unaryExpr());
                node.setLineno(line);
                return node;
            }
            case 22: {
                this.consumeToken();
                UnaryExpression node = new UnaryExpression(29, this.ts.tokenBeg, this.unaryExpr());
                node.setLineno(line);
                return node;
            }
            case 106: 
            case 107: {
                this.consumeToken();
                UnaryExpression expr = new UnaryExpression(tt, this.ts.tokenBeg, this.memberExpr(true));
                expr.setLineno(line);
                this.checkBadIncDec(expr);
                return expr;
            }
            case 31: {
                this.consumeToken();
                UnaryExpression node = new UnaryExpression(tt, this.ts.tokenBeg, this.unaryExpr());
                node.setLineno(line);
                return node;
            }
            case -1: {
                this.consumeToken();
                return this.makeErrorNode();
            }
            case 14: {
                if (!this.compilerEnv.isXmlAvailable()) break;
                this.consumeToken();
                return this.memberExprTail(true, this.xmlInitializer());
            }
        }
        AstNode pn = this.memberExpr(true);
        tt = this.peekTokenOrEOL();
        if (tt != 106 && tt != 107) {
            return pn;
        }
        this.consumeToken();
        UnaryExpression uexpr = new UnaryExpression(tt, this.ts.tokenBeg, pn, true);
        uexpr.setLineno(line);
        this.checkBadIncDec(uexpr);
        return uexpr;
    }

    private AstNode xmlInitializer() throws IOException {
        if (this.currentToken != 14) {
            this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        int tt = this.ts.getFirstXMLToken();
        if (tt != 145 && tt != 148) {
            this.reportError("msg.syntax");
            return this.makeErrorNode();
        }
        XmlLiteral pn = new XmlLiteral(pos);
        pn.setLineno(this.ts.lineno);
        while (true) {
            switch (tt) {
                case 145: {
                    pn.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
                    this.mustMatchToken(85, "msg.syntax");
                    int beg = this.ts.tokenBeg;
                    AstNode expr = this.peekToken() == 86 ? new EmptyExpression(beg, this.ts.tokenEnd - beg) : this.expr();
                    this.mustMatchToken(86, "msg.syntax");
                    XmlExpression xexpr = new XmlExpression(beg, expr);
                    xexpr.setIsXmlAttribute(this.ts.isXMLAttribute());
                    xexpr.setLength(this.ts.tokenEnd - beg);
                    pn.addFragment(xexpr);
                    break;
                }
                case 148: {
                    pn.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
                    return pn;
                }
                default: {
                    this.reportError("msg.syntax");
                    return this.makeErrorNode();
                }
            }
            tt = this.ts.getNextXMLToken();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private List<AstNode> argumentList() throws IOException {
        if (this.matchToken(88)) {
            return null;
        }
        ArrayList<AstNode> result = new ArrayList<AstNode>();
        boolean wasInForInit = this.inForInit;
        this.inForInit = false;
        try {
            do {
                if (this.peekToken() == 72) {
                    this.reportError("msg.yield.parenthesized");
                }
                AstNode en = this.assignExpr();
                if (this.peekToken() == 119) {
                    try {
                        result.add(this.generatorExpression(en, 0, true));
                    } catch (IOException ex) {}
                    continue;
                }
                result.add(en);
            } while (this.matchToken(89));
        } finally {
            this.inForInit = wasInForInit;
        }
        this.mustMatchToken(88, "msg.no.paren.arg");
        return result;
    }

    private AstNode memberExpr(boolean allowCallSyntax) throws IOException {
        AstNode pn;
        int tt = this.peekToken();
        int lineno = this.ts.lineno;
        if (tt != 30) {
            pn = this.primaryExpr();
        } else {
            this.consumeToken();
            int pos = this.ts.tokenBeg;
            NewExpression nx = new NewExpression(pos);
            AstNode target = this.memberExpr(false);
            int end = this.getNodeEnd(target);
            nx.setTarget(target);
            int lp = -1;
            if (this.matchToken(87)) {
                lp = this.ts.tokenBeg;
                List<AstNode> args = this.argumentList();
                if (args != null && args.size() > 65536) {
                    this.reportError("msg.too.many.constructor.args");
                }
                int rp = this.ts.tokenBeg;
                end = this.ts.tokenEnd;
                if (args != null) {
                    nx.setArguments(args);
                }
                nx.setParens(lp - pos, rp - pos);
            }
            if (this.matchToken(85)) {
                ObjectLiteral initializer = this.objectLiteral();
                end = this.getNodeEnd(initializer);
                nx.setInitializer(initializer);
            }
            nx.setLength(end - pos);
            pn = nx;
        }
        pn.setLineno(lineno);
        AstNode tail = this.memberExprTail(allowCallSyntax, pn);
        return tail;
    }

    private AstNode memberExprTail(boolean allowCallSyntax, AstNode pn) throws IOException {
        if (pn == null) {
            this.codeBug();
        }
        int pos = pn.getPosition();
        block6: while (true) {
            int tt = this.peekToken();
            switch (tt) {
                case 108: 
                case 143: {
                    int lineno = this.ts.lineno;
                    pn = this.propertyAccess(tt, pn);
                    pn.setLineno(lineno);
                    continue block6;
                }
                case 146: {
                    this.consumeToken();
                    int opPos = this.ts.tokenBeg;
                    int rp = -1;
                    int lineno = this.ts.lineno;
                    this.mustHaveXML();
                    this.setRequiresActivation();
                    AstNode filter = this.expr();
                    int end = this.getNodeEnd(filter);
                    if (this.mustMatchToken(88, "msg.no.paren")) {
                        rp = this.ts.tokenBeg;
                        end = this.ts.tokenEnd;
                    }
                    XmlDotQuery q = new XmlDotQuery(pos, end - pos);
                    q.setLeft(pn);
                    q.setRight(filter);
                    q.setOperatorPosition(opPos);
                    q.setRp(rp - pos);
                    q.setLineno(lineno);
                    pn = q;
                    continue block6;
                }
                case 83: {
                    this.consumeToken();
                    int lb = this.ts.tokenBeg;
                    int rb = -1;
                    int lineno = this.ts.lineno;
                    AstNode expr = this.expr();
                    int end = this.getNodeEnd(expr);
                    if (this.mustMatchToken(84, "msg.no.bracket.index")) {
                        rb = this.ts.tokenBeg;
                        end = this.ts.tokenEnd;
                    }
                    ElementGet g = new ElementGet(pos, end - pos);
                    g.setTarget(pn);
                    g.setElement(expr);
                    g.setParens(lb, rb);
                    g.setLineno(lineno);
                    pn = g;
                    continue block6;
                }
                case 87: {
                    if (!allowCallSyntax) break block6;
                    int lineno = this.ts.lineno;
                    this.consumeToken();
                    this.checkCallRequiresActivation(pn);
                    FunctionCall f = new FunctionCall(pos);
                    f.setTarget(pn);
                    f.setLineno(lineno);
                    f.setLp(this.ts.tokenBeg - pos);
                    List<AstNode> args = this.argumentList();
                    if (args != null && args.size() > 65536) {
                        this.reportError("msg.too.many.function.args");
                    }
                    f.setArguments(args);
                    f.setRp(this.ts.tokenBeg - pos);
                    f.setLength(this.ts.tokenEnd - pos);
                    pn = f;
                    continue block6;
                }
            }
            break;
        }
        return pn;
    }

    private AstNode propertyAccess(int tt, AstNode pn) throws IOException {
        InfixExpression result;
        if (pn == null) {
            this.codeBug();
        }
        int memberTypeFlags = 0;
        int lineno = this.ts.lineno;
        int dotPos = this.ts.tokenBeg;
        this.consumeToken();
        if (tt == 143) {
            this.mustHaveXML();
            memberTypeFlags = 4;
        }
        if (!this.compilerEnv.isXmlAvailable()) {
            int maybeName = this.nextToken();
            if (!(maybeName == 39 || this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.ts.getString()))) {
                this.reportError("msg.no.name.after.dot");
            }
            Name name = this.createNameNode(true, 33);
            PropertyGet pg = new PropertyGet(pn, name, dotPos);
            pg.setLineno(lineno);
            return pg;
        }
        AstNode ref = null;
        int token = this.nextToken();
        switch (token) {
            case 50: {
                this.saveNameTokenData(this.ts.tokenBeg, "throw", this.ts.lineno);
                ref = this.propertyName(-1, "throw", memberTypeFlags);
                break;
            }
            case 39: {
                ref = this.propertyName(-1, this.ts.getString(), memberTypeFlags);
                break;
            }
            case 23: {
                this.saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                ref = this.propertyName(-1, "*", memberTypeFlags);
                break;
            }
            case 147: {
                ref = this.attributeAccess();
                break;
            }
            default: {
                String name;
                if (this.compilerEnv.isReservedKeywordAsIdentifier() && (name = Token.keywordToName(token)) != null) {
                    this.saveNameTokenData(this.ts.tokenBeg, name, this.ts.lineno);
                    ref = this.propertyName(-1, name, memberTypeFlags);
                    break;
                }
                this.reportError("msg.no.name.after.dot");
                return this.makeErrorNode();
            }
        }
        boolean xml = ref instanceof XmlRef;
        InfixExpression infixExpression = result = xml ? new XmlMemberGet() : new PropertyGet();
        if (xml && tt == 108) {
            result.setType(108);
        }
        int pos = pn.getPosition();
        result.setPosition(pos);
        result.setLength(this.getNodeEnd(ref) - pos);
        result.setOperatorPosition(dotPos - pos);
        result.setLineno(pn.getLineno());
        result.setLeft(pn);
        result.setRight(ref);
        return result;
    }

    private AstNode attributeAccess() throws IOException {
        int tt = this.nextToken();
        int atPos = this.ts.tokenBeg;
        switch (tt) {
            case 39: {
                return this.propertyName(atPos, this.ts.getString(), 0);
            }
            case 23: {
                this.saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                return this.propertyName(atPos, "*", 0);
            }
            case 83: {
                return this.xmlElemRef(atPos, null, -1);
            }
        }
        this.reportError("msg.no.name.after.xmlAttr");
        return this.makeErrorNode();
    }

    private AstNode propertyName(int atPos, String s, int memberTypeFlags) throws IOException {
        int pos = atPos != -1 ? atPos : this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        int colonPos = -1;
        Name name = this.createNameNode(true, this.currentToken);
        Name ns = null;
        if (this.matchToken(144)) {
            ns = name;
            colonPos = this.ts.tokenBeg;
            switch (this.nextToken()) {
                case 39: {
                    name = this.createNameNode();
                    break;
                }
                case 23: {
                    this.saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
                    name = this.createNameNode(false, -1);
                    break;
                }
                case 83: {
                    return this.xmlElemRef(atPos, ns, colonPos);
                }
                default: {
                    this.reportError("msg.no.name.after.coloncolon");
                    return this.makeErrorNode();
                }
            }
        }
        if (ns == null && memberTypeFlags == 0 && atPos == -1) {
            return name;
        }
        XmlPropRef ref = new XmlPropRef(pos, this.getNodeEnd(name) - pos);
        ref.setAtPos(atPos);
        ref.setNamespace(ns);
        ref.setColonPos(colonPos);
        ref.setPropName(name);
        ref.setLineno(lineno);
        return ref;
    }

    private XmlElemRef xmlElemRef(int atPos, Name namespace, int colonPos) throws IOException {
        int lb = this.ts.tokenBeg;
        int rb = -1;
        int pos = atPos != -1 ? atPos : lb;
        AstNode expr = this.expr();
        int end = this.getNodeEnd(expr);
        if (this.mustMatchToken(84, "msg.no.bracket.index")) {
            rb = this.ts.tokenBeg;
            end = this.ts.tokenEnd;
        }
        XmlElemRef ref = new XmlElemRef(pos, end - pos);
        ref.setNamespace(namespace);
        ref.setColonPos(colonPos);
        ref.setAtPos(atPos);
        ref.setExpression(expr);
        ref.setBrackets(lb, rb);
        return ref;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode destructuringPrimaryExpr() throws IOException, ParserException {
        try {
            this.inDestructuringAssignment = true;
            AstNode astNode = this.primaryExpr();
            return astNode;
        } finally {
            this.inDestructuringAssignment = false;
        }
    }

    private AstNode primaryExpr() throws IOException {
        int ttFlagged = this.nextFlaggedToken();
        int tt = ttFlagged & 0xFFFF;
        switch (tt) {
            case 109: {
                return this.function(2);
            }
            case 83: {
                return this.arrayLiteral();
            }
            case 85: {
                return this.objectLiteral();
            }
            case 153: {
                return this.let(false, this.ts.tokenBeg);
            }
            case 87: {
                return this.parenExpr();
            }
            case 147: {
                this.mustHaveXML();
                return this.attributeAccess();
            }
            case 39: {
                return this.name(ttFlagged, tt);
            }
            case 40: {
                String s = this.ts.getString();
                if (this.inUseStrictDirective && this.ts.isNumberOctal()) {
                    this.reportError("msg.no.octal.strict");
                }
                if (this.ts.isNumberOctal()) {
                    s = "0" + s;
                }
                if (this.ts.isNumberHex()) {
                    s = "0x" + s;
                }
                return new NumberLiteral(this.ts.tokenBeg, s, this.ts.getNumber());
            }
            case 41: {
                return this.createStringLiteral();
            }
            case 24: 
            case 100: {
                this.ts.readRegExp(tt);
                int pos = this.ts.tokenBeg;
                int end = this.ts.tokenEnd;
                RegExpLiteral re = new RegExpLiteral(pos, end - pos);
                re.setValue(this.ts.getString());
                re.setFlags(this.ts.readAndClearRegExpFlags());
                return re;
            }
            case 42: 
            case 43: 
            case 44: 
            case 45: {
                int pos = this.ts.tokenBeg;
                int end = this.ts.tokenEnd;
                return new KeywordLiteral(pos, end - pos, tt);
            }
            case 127: {
                this.reportError("msg.reserved.id");
                break;
            }
            case -1: {
                break;
            }
            case 0: {
                this.reportError("msg.unexpected.eof");
                break;
            }
            default: {
                this.reportError("msg.syntax");
            }
        }
        return this.makeErrorNode();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AstNode parenExpr() throws IOException {
        boolean wasInForInit = this.inForInit;
        this.inForInit = false;
        try {
            Comment jsdocNode = this.getAndResetJsDoc();
            int lineno = this.ts.lineno;
            int begin = this.ts.tokenBeg;
            AstNode e = this.expr();
            if (this.peekToken() == 119) {
                AstNode astNode = this.generatorExpression(e, begin);
                return astNode;
            }
            ParenthesizedExpression pn = new ParenthesizedExpression(e);
            if (jsdocNode == null) {
                jsdocNode = this.getAndResetJsDoc();
            }
            if (jsdocNode != null) {
                pn.setJsDocNode(jsdocNode);
            }
            this.mustMatchToken(88, "msg.no.paren");
            pn.setLength(this.ts.tokenEnd - pn.getPosition());
            pn.setLineno(lineno);
            ParenthesizedExpression parenthesizedExpression = pn;
            return parenthesizedExpression;
        } finally {
            this.inForInit = wasInForInit;
        }
    }

    private AstNode name(int ttFlagged, int tt) throws IOException {
        String nameString = this.ts.getString();
        int namePos = this.ts.tokenBeg;
        int nameLineno = this.ts.lineno;
        if (0 != (ttFlagged & 0x20000) && this.peekToken() == 103) {
            Label label = new Label(namePos, this.ts.tokenEnd - namePos);
            label.setName(nameString);
            label.setLineno(this.ts.lineno);
            return label;
        }
        this.saveNameTokenData(namePos, nameString, nameLineno);
        if (this.compilerEnv.isXmlAvailable()) {
            return this.propertyName(-1, nameString, 0);
        }
        return this.createNameNode(true, 39);
    }

    private AstNode arrayLiteral() throws IOException {
        if (this.currentToken != 83) {
            this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        int end = this.ts.tokenEnd;
        ArrayList<AstNode> elements = new ArrayList<AstNode>();
        ArrayLiteral pn = new ArrayLiteral(pos);
        boolean after_lb_or_comma = true;
        int afterComma = -1;
        int skipCount = 0;
        while (true) {
            int tt;
            if ((tt = this.peekToken()) == 89) {
                this.consumeToken();
                afterComma = this.ts.tokenEnd;
                if (!after_lb_or_comma) {
                    after_lb_or_comma = true;
                    continue;
                }
                elements.add(new EmptyExpression(this.ts.tokenBeg, 1));
                ++skipCount;
                continue;
            }
            if (tt == 84) {
                this.consumeToken();
                end = this.ts.tokenEnd;
                pn.setDestructuringLength(elements.size() + (after_lb_or_comma ? 1 : 0));
                pn.setSkipCount(skipCount);
                if (afterComma == -1) break;
                this.warnTrailingComma(pos, elements, afterComma);
                break;
            }
            if (tt == 119 && !after_lb_or_comma && elements.size() == 1) {
                return this.arrayComprehension((AstNode)elements.get(0), pos);
            }
            if (tt == 0) {
                this.reportError("msg.no.bracket.arg");
                break;
            }
            if (!after_lb_or_comma) {
                this.reportError("msg.no.bracket.arg");
            }
            elements.add(this.assignExpr());
            after_lb_or_comma = false;
            afterComma = -1;
        }
        for (AstNode e : elements) {
            pn.addElement(e);
        }
        pn.setLength(end - pos);
        return pn;
    }

    private AstNode arrayComprehension(AstNode result, int pos) throws IOException {
        ArrayList<ArrayComprehensionLoop> loops = new ArrayList<ArrayComprehensionLoop>();
        while (this.peekToken() == 119) {
            loops.add(this.arrayComprehensionLoop());
        }
        int ifPos = -1;
        ConditionData data = null;
        if (this.peekToken() == 112) {
            this.consumeToken();
            ifPos = this.ts.tokenBeg - pos;
            data = this.condition();
        }
        this.mustMatchToken(84, "msg.no.bracket.arg");
        ArrayComprehension pn = new ArrayComprehension(pos, this.ts.tokenEnd - pos);
        pn.setResult(result);
        pn.setLoops(loops);
        if (data != null) {
            pn.setIfPosition(ifPos);
            pn.setFilter(data.condition);
            pn.setFilterLp(data.lp - pos);
            pn.setFilterRp(data.rp - pos);
        }
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ArrayComprehensionLoop arrayComprehensionLoop() throws IOException {
        if (this.nextToken() != 119) {
            this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        int eachPos = -1;
        int lp = -1;
        int rp = -1;
        int inPos = -1;
        ArrayComprehensionLoop pn = new ArrayComprehensionLoop(pos);
        this.pushScope(pn);
        try {
            if (this.matchToken(39)) {
                if (this.ts.getString().equals("each")) {
                    eachPos = this.ts.tokenBeg - pos;
                } else {
                    this.reportError("msg.no.paren.for");
                }
            }
            if (this.mustMatchToken(87, "msg.no.paren.for")) {
                lp = this.ts.tokenBeg - pos;
            }
            AstNode iter = null;
            switch (this.peekToken()) {
                case 83: 
                case 85: {
                    iter = this.destructuringPrimaryExpr();
                    this.markDestructuring(iter);
                    break;
                }
                case 39: {
                    this.consumeToken();
                    iter = this.createNameNode();
                    break;
                }
                default: {
                    this.reportError("msg.bad.var");
                }
            }
            if (iter.getType() == 39) {
                this.defineSymbol(153, this.ts.getString(), true);
            }
            if (this.mustMatchToken(52, "msg.in.after.for.name")) {
                inPos = this.ts.tokenBeg - pos;
            }
            AstNode obj = this.expr();
            if (this.mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                rp = this.ts.tokenBeg - pos;
            }
            pn.setLength(this.ts.tokenEnd - pos);
            pn.setIterator(iter);
            pn.setIteratedObject(obj);
            pn.setInPosition(inPos);
            pn.setEachPosition(eachPos);
            pn.setIsForEach(eachPos != -1);
            pn.setParens(lp, rp);
            ArrayComprehensionLoop arrayComprehensionLoop = pn;
            return arrayComprehensionLoop;
        } finally {
            this.popScope();
        }
    }

    private AstNode generatorExpression(AstNode result, int pos) throws IOException {
        return this.generatorExpression(result, pos, false);
    }

    private AstNode generatorExpression(AstNode result, int pos, boolean inFunctionParams) throws IOException {
        ArrayList<GeneratorExpressionLoop> loops = new ArrayList<GeneratorExpressionLoop>();
        while (this.peekToken() == 119) {
            loops.add(this.generatorExpressionLoop());
        }
        int ifPos = -1;
        ConditionData data = null;
        if (this.peekToken() == 112) {
            this.consumeToken();
            ifPos = this.ts.tokenBeg - pos;
            data = this.condition();
        }
        if (!inFunctionParams) {
            this.mustMatchToken(88, "msg.no.paren.let");
        }
        GeneratorExpression pn = new GeneratorExpression(pos, this.ts.tokenEnd - pos);
        pn.setResult(result);
        pn.setLoops(loops);
        if (data != null) {
            pn.setIfPosition(ifPos);
            pn.setFilter(data.condition);
            pn.setFilterLp(data.lp - pos);
            pn.setFilterRp(data.rp - pos);
        }
        return pn;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private GeneratorExpressionLoop generatorExpressionLoop() throws IOException {
        if (this.nextToken() != 119) {
            this.codeBug();
        }
        int pos = this.ts.tokenBeg;
        int lp = -1;
        int rp = -1;
        int inPos = -1;
        GeneratorExpressionLoop pn = new GeneratorExpressionLoop(pos);
        this.pushScope(pn);
        try {
            if (this.mustMatchToken(87, "msg.no.paren.for")) {
                lp = this.ts.tokenBeg - pos;
            }
            AstNode iter = null;
            switch (this.peekToken()) {
                case 83: 
                case 85: {
                    iter = this.destructuringPrimaryExpr();
                    this.markDestructuring(iter);
                    break;
                }
                case 39: {
                    this.consumeToken();
                    iter = this.createNameNode();
                    break;
                }
                default: {
                    this.reportError("msg.bad.var");
                }
            }
            if (iter.getType() == 39) {
                this.defineSymbol(153, this.ts.getString(), true);
            }
            if (this.mustMatchToken(52, "msg.in.after.for.name")) {
                inPos = this.ts.tokenBeg - pos;
            }
            AstNode obj = this.expr();
            if (this.mustMatchToken(88, "msg.no.paren.for.ctrl")) {
                rp = this.ts.tokenBeg - pos;
            }
            pn.setLength(this.ts.tokenEnd - pos);
            pn.setIterator(iter);
            pn.setIteratedObject(obj);
            pn.setInPosition(inPos);
            pn.setParens(lp, rp);
            GeneratorExpressionLoop generatorExpressionLoop = pn;
            return generatorExpressionLoop;
        } finally {
            this.popScope();
        }
    }

    private ObjectLiteral objectLiteral() throws IOException {
        int pos = this.ts.tokenBeg;
        int lineno = this.ts.lineno;
        int afterComma = -1;
        ArrayList<ObjectProperty> elems = new ArrayList<ObjectProperty>();
        HashSet<String> getterNames = null;
        HashSet<String> setterNames = null;
        if (this.inUseStrictDirective) {
            getterNames = new HashSet<String>();
            setterNames = new HashSet<String>();
        }
        Comment objJsdocNode = this.getAndResetJsDoc();
        block9: while (true) {
            String propertyName = null;
            int entryKind = 1;
            int tt = this.peekToken();
            Comment jsdocNode = this.getAndResetJsDoc();
            switch (tt) {
                case 39: {
                    boolean maybeGetterOrSetter;
                    Name name = this.createNameNode();
                    propertyName = this.ts.getString();
                    int ppos = this.ts.tokenBeg;
                    this.consumeToken();
                    int peeked = this.peekToken();
                    boolean bl = maybeGetterOrSetter = "get".equals(propertyName) || "set".equals(propertyName);
                    if (maybeGetterOrSetter && peeked != 89 && peeked != 103 && peeked != 86) {
                        boolean isGet = "get".equals(propertyName);
                        entryKind = isGet ? 2 : 4;
                        AstNode pname = this.objliteralProperty();
                        if (pname == null) {
                            propertyName = null;
                            break;
                        }
                        propertyName = this.ts.getString();
                        ObjectProperty objectProp = this.getterSetterProperty(ppos, pname, isGet);
                        pname.setJsDocNode(jsdocNode);
                        elems.add(objectProp);
                        break;
                    }
                    name.setJsDocNode(jsdocNode);
                    elems.add(this.plainProperty(name, tt));
                    break;
                }
                case 86: {
                    if (afterComma == -1) break block9;
                    this.warnTrailingComma(pos, elems, afterComma);
                    break block9;
                }
                default: {
                    AstNode pname = this.objliteralProperty();
                    if (pname == null) {
                        propertyName = null;
                        break;
                    }
                    propertyName = this.ts.getString();
                    pname.setJsDocNode(jsdocNode);
                    elems.add(this.plainProperty(pname, tt));
                }
            }
            if (this.inUseStrictDirective && propertyName != null) {
                switch (entryKind) {
                    case 1: {
                        if (getterNames.contains(propertyName) || setterNames.contains(propertyName)) {
                            this.addError("msg.dup.obj.lit.prop.strict", propertyName);
                        }
                        getterNames.add(propertyName);
                        setterNames.add(propertyName);
                        break;
                    }
                    case 2: {
                        if (getterNames.contains(propertyName)) {
                            this.addError("msg.dup.obj.lit.prop.strict", propertyName);
                        }
                        getterNames.add(propertyName);
                        break;
                    }
                    case 4: {
                        if (setterNames.contains(propertyName)) {
                            this.addError("msg.dup.obj.lit.prop.strict", propertyName);
                        }
                        setterNames.add(propertyName);
                    }
                }
            }
            this.getAndResetJsDoc();
            if (!this.matchToken(89)) break;
            afterComma = this.ts.tokenEnd;
        }
        this.mustMatchToken(86, "msg.no.brace.prop");
        ObjectLiteral pn = new ObjectLiteral(pos, this.ts.tokenEnd - pos);
        if (objJsdocNode != null) {
            pn.setJsDocNode(objJsdocNode);
        }
        pn.setElements(elems);
        pn.setLineno(lineno);
        return pn;
    }

    private AstNode objliteralProperty() throws IOException {
        AstNode pname;
        int tt = this.peekToken();
        switch (tt) {
            case 39: {
                pname = this.createNameNode();
                break;
            }
            case 41: {
                pname = this.createStringLiteral();
                break;
            }
            case 40: {
                pname = new NumberLiteral(this.ts.tokenBeg, this.ts.getString(), this.ts.getNumber());
                break;
            }
            default: {
                if (this.compilerEnv.isReservedKeywordAsIdentifier() && TokenStream.isKeyword(this.ts.getString())) {
                    pname = this.createNameNode();
                    break;
                }
                this.reportError("msg.bad.prop");
                return null;
            }
        }
        this.consumeToken();
        return pname;
    }

    private ObjectProperty plainProperty(AstNode property, int ptt) throws IOException {
        int tt = this.peekToken();
        if ((tt == 89 || tt == 86) && ptt == 39 && this.compilerEnv.getLanguageVersion() >= 180) {
            if (!this.inDestructuringAssignment) {
                this.reportError("msg.bad.object.init");
            }
            Name nn = new Name(property.getPosition(), property.getString());
            ObjectProperty pn = new ObjectProperty();
            pn.putProp(26, Boolean.TRUE);
            pn.setLeftAndRight(property, nn);
            return pn;
        }
        this.mustMatchToken(103, "msg.no.colon.prop");
        ObjectProperty pn = new ObjectProperty();
        pn.setOperatorPosition(this.ts.tokenBeg);
        pn.setLeftAndRight(property, this.assignExpr());
        return pn;
    }

    private ObjectProperty getterSetterProperty(int pos, AstNode propName, boolean isGetter) throws IOException {
        FunctionNode fn = this.function(2);
        Name name = fn.getFunctionName();
        if (name != null && name.length() != 0) {
            this.reportError("msg.bad.prop");
        }
        ObjectProperty pn = new ObjectProperty(pos);
        if (isGetter) {
            pn.setIsGetter();
            fn.setFunctionIsGetter();
        } else {
            pn.setIsSetter();
            fn.setFunctionIsSetter();
        }
        int end = this.getNodeEnd(fn);
        pn.setLeft(propName);
        pn.setRight(fn);
        pn.setLength(end - pos);
        return pn;
    }

    private Name createNameNode() {
        return this.createNameNode(false, 39);
    }

    private Name createNameNode(boolean checkActivation, int token) {
        int beg = this.ts.tokenBeg;
        String s = this.ts.getString();
        int lineno = this.ts.lineno;
        if (!"".equals(this.prevNameTokenString)) {
            beg = this.prevNameTokenStart;
            s = this.prevNameTokenString;
            lineno = this.prevNameTokenLineno;
            this.prevNameTokenStart = 0;
            this.prevNameTokenString = "";
            this.prevNameTokenLineno = 0;
        }
        if (s == null) {
            if (this.compilerEnv.isIdeMode()) {
                s = "";
            } else {
                this.codeBug();
            }
        }
        Name name = new Name(beg, s);
        name.setLineno(lineno);
        if (checkActivation) {
            this.checkActivationName(s, token);
        }
        return name;
    }

    private StringLiteral createStringLiteral() {
        int pos = this.ts.tokenBeg;
        int end = this.ts.tokenEnd;
        StringLiteral s = new StringLiteral(pos, end - pos);
        s.setLineno(this.ts.lineno);
        s.setValue(this.ts.getString());
        s.setQuoteCharacter(this.ts.getQuoteChar());
        return s;
    }

    protected void checkActivationName(String name, int token) {
        if (!this.insideFunction()) {
            return;
        }
        boolean activation = false;
        if ("arguments".equals(name) || this.compilerEnv.getActivationNames() != null && this.compilerEnv.getActivationNames().contains(name)) {
            activation = true;
        } else if ("length".equals(name) && token == 33 && this.compilerEnv.getLanguageVersion() == 120) {
            activation = true;
        }
        if (activation) {
            this.setRequiresActivation();
        }
    }

    protected void setRequiresActivation() {
        if (this.insideFunction()) {
            ((FunctionNode)this.currentScriptOrFn).setRequiresActivation();
        }
    }

    private void checkCallRequiresActivation(AstNode pn) {
        if (pn.getType() == 39 && "eval".equals(((Name)pn).getIdentifier()) || pn.getType() == 33 && "eval".equals(((PropertyGet)pn).getProperty().getIdentifier())) {
            this.setRequiresActivation();
        }
    }

    protected void setIsGenerator() {
        if (this.insideFunction()) {
            ((FunctionNode)this.currentScriptOrFn).setIsGenerator();
        }
    }

    private void checkBadIncDec(UnaryExpression expr) {
        AstNode op = this.removeParens(expr.getOperand());
        int tt = op.getType();
        if (tt != 39 && tt != 33 && tt != 36 && tt != 67 && tt != 38) {
            this.reportError(expr.getType() == 106 ? "msg.bad.incr" : "msg.bad.decr");
        }
    }

    private ErrorNode makeErrorNode() {
        ErrorNode pn = new ErrorNode(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
        pn.setLineno(this.ts.lineno);
        return pn;
    }

    private int nodeEnd(AstNode node) {
        return node.getPosition() + node.getLength();
    }

    private void saveNameTokenData(int pos, String name, int lineno) {
        this.prevNameTokenStart = pos;
        this.prevNameTokenString = name;
        this.prevNameTokenLineno = lineno;
    }

    private int lineBeginningFor(int pos) {
        if (this.sourceChars == null) {
            return -1;
        }
        if (pos <= 0) {
            return 0;
        }
        char[] buf = this.sourceChars;
        if (pos >= buf.length) {
            pos = buf.length - 1;
        }
        while (--pos >= 0) {
            char c = buf[pos];
            if (!ScriptRuntime.isJSLineTerminator(c)) continue;
            return pos + 1;
        }
        return 0;
    }

    private void warnMissingSemi(int pos, int end) {
        if (this.compilerEnv.isStrictMode()) {
            int beg;
            int[] linep = new int[2];
            String line = this.ts.getLine(end, linep);
            int n = beg = this.compilerEnv.isIdeMode() ? Math.max(pos, end - linep[1]) : pos;
            if (line != null) {
                this.addStrictWarning("msg.missing.semi", "", beg, end - beg, linep[0], line, linep[1]);
            } else {
                this.addStrictWarning("msg.missing.semi", "", beg, end - beg);
            }
        }
    }

    private void warnTrailingComma(int pos, List<?> elems, int commaPos) {
        if (this.compilerEnv.getWarnTrailingComma()) {
            if (!elems.isEmpty()) {
                pos = ((AstNode)elems.get(0)).getPosition();
            }
            pos = Math.max(pos, this.lineBeginningFor(commaPos));
            this.addWarning("msg.extra.trailing.comma", pos, commaPos - pos);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String readFully(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        try {
            int bytes_read;
            char[] cbuf = new char[1024];
            StringBuilder sb = new StringBuilder(1024);
            while ((bytes_read = in.read(cbuf, 0, 1024)) != -1) {
                sb.append(cbuf, 0, bytes_read);
            }
            String string = sb.toString();
            return string;
        } finally {
            in.close();
        }
    }

    Node createDestructuringAssignment(int type, Node left, Node right) {
        String tempName = this.currentScriptOrFn.getNextTempName();
        Node result = this.destructuringAssignmentHelper(type, left, right, tempName);
        Node comma = result.getLastChild();
        comma.addChildToBack(this.createName(tempName));
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    Node destructuringAssignmentHelper(int variableType, Node left, Node right, String tempName) {
        Scope result = this.createScopeNode(158, left.getLineno());
        result.addChildToFront(new Node(153, this.createName(39, tempName, right)));
        try {
            this.pushScope(result);
            this.defineSymbol(153, tempName, true);
        } finally {
            this.popScope();
        }
        Node comma = new Node(89);
        result.addChildToBack(comma);
        ArrayList<String> destructuringNames = new ArrayList<String>();
        boolean empty = true;
        switch (left.getType()) {
            case 65: {
                empty = this.destructuringArray((ArrayLiteral)left, variableType, tempName, comma, destructuringNames);
                break;
            }
            case 66: {
                empty = this.destructuringObject((ObjectLiteral)left, variableType, tempName, comma, destructuringNames);
                break;
            }
            case 33: 
            case 36: {
                switch (variableType) {
                    case 122: 
                    case 153: 
                    case 154: {
                        this.reportError("msg.bad.assign.left");
                    }
                }
                comma.addChildToBack(this.simpleAssignment(left, this.createName(tempName)));
                break;
            }
            default: {
                this.reportError("msg.bad.assign.left");
            }
        }
        if (empty) {
            comma.addChildToBack(this.createNumber(0.0));
        }
        result.putProp(22, destructuringNames);
        return result;
    }

    boolean destructuringArray(ArrayLiteral array, int variableType, String tempName, Node parent, List<String> destructuringNames) {
        boolean empty = true;
        int setOp = variableType == 154 ? 155 : 8;
        int index = 0;
        for (AstNode n : array.getElements()) {
            if (n.getType() == 128) {
                ++index;
                continue;
            }
            Node rightElem = new Node(36, this.createName(tempName), this.createNumber(index));
            if (n.getType() == 39) {
                String name = n.getString();
                parent.addChildToBack(new Node(setOp, this.createName(49, name, null), rightElem));
                if (variableType != -1) {
                    this.defineSymbol(variableType, name, true);
                    destructuringNames.add(name);
                }
            } else {
                parent.addChildToBack(this.destructuringAssignmentHelper(variableType, n, rightElem, this.currentScriptOrFn.getNextTempName()));
            }
            ++index;
            empty = false;
        }
        return empty;
    }

    boolean destructuringObject(ObjectLiteral node, int variableType, String tempName, Node parent, List<String> destructuringNames) {
        boolean empty = true;
        int setOp = variableType == 154 ? 155 : 8;
        for (ObjectProperty prop : node.getElements()) {
            Node s;
            int lineno = 0;
            if (this.ts != null) {
                lineno = this.ts.lineno;
            }
            AstNode id = prop.getLeft();
            Node rightElem = null;
            if (id instanceof Name) {
                s = Node.newString(((Name)id).getIdentifier());
                rightElem = new Node(33, this.createName(tempName), s);
            } else if (id instanceof StringLiteral) {
                s = Node.newString(((StringLiteral)id).getValue());
                rightElem = new Node(33, this.createName(tempName), s);
            } else if (id instanceof NumberLiteral) {
                s = this.createNumber((int)((NumberLiteral)id).getNumber());
                rightElem = new Node(36, this.createName(tempName), s);
            } else {
                throw this.codeBug();
            }
            rightElem.setLineno(lineno);
            AstNode value = prop.getRight();
            if (value.getType() == 39) {
                String name = ((Name)value).getIdentifier();
                parent.addChildToBack(new Node(setOp, this.createName(49, name, null), rightElem));
                if (variableType != -1) {
                    this.defineSymbol(variableType, name, true);
                    destructuringNames.add(name);
                }
            } else {
                parent.addChildToBack(this.destructuringAssignmentHelper(variableType, value, rightElem, this.currentScriptOrFn.getNextTempName()));
            }
            empty = false;
        }
        return empty;
    }

    protected Node createName(String name) {
        this.checkActivationName(name, 39);
        return Node.newString(39, name);
    }

    protected Node createName(int type, String name, Node child) {
        Node result = this.createName(name);
        result.setType(type);
        if (child != null) {
            result.addChildToBack(child);
        }
        return result;
    }

    protected Node createNumber(double number) {
        return Node.newNumber(number);
    }

    protected Scope createScopeNode(int token, int lineno) {
        Scope scope = new Scope();
        scope.setType(token);
        scope.setLineno(lineno);
        return scope;
    }

    protected Node simpleAssignment(Node left, Node right) {
        int nodeType = left.getType();
        switch (nodeType) {
            case 39: {
                if (this.inUseStrictDirective && "eval".equals(((Name)left).getIdentifier())) {
                    this.reportError("msg.bad.id.strict", ((Name)left).getIdentifier());
                }
                left.setType(49);
                return new Node(8, left, right);
            }
            case 33: 
            case 36: {
                int type;
                Node id;
                Node obj;
                if (left instanceof PropertyGet) {
                    obj = ((PropertyGet)left).getTarget();
                    id = ((PropertyGet)left).getProperty();
                } else if (left instanceof ElementGet) {
                    obj = ((ElementGet)left).getTarget();
                    id = ((ElementGet)left).getElement();
                } else {
                    obj = left.getFirstChild();
                    id = left.getLastChild();
                }
                if (nodeType == 33) {
                    type = 35;
                    id.setType(41);
                } else {
                    type = 37;
                }
                return new Node(type, obj, id, right);
            }
            case 67: {
                Node ref = left.getFirstChild();
                this.checkMutableReference(ref);
                return new Node(68, ref, right);
            }
        }
        throw this.codeBug();
    }

    protected void checkMutableReference(Node n) {
        int memberTypeFlags = n.getIntProp(16, 0);
        if ((memberTypeFlags & 4) != 0) {
            this.reportError("msg.bad.assign.left");
        }
    }

    protected AstNode removeParens(AstNode node) {
        while (node instanceof ParenthesizedExpression) {
            node = ((ParenthesizedExpression)node).getExpression();
        }
        return node;
    }

    void markDestructuring(AstNode node) {
        if (node instanceof DestructuringForm) {
            ((DestructuringForm)((Object)node)).setIsDestructuring(true);
        } else if (node instanceof ParenthesizedExpression) {
            this.markDestructuring(((ParenthesizedExpression)node).getExpression());
        }
    }

    private RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug("ts.cursor=" + this.ts.cursor + ", ts.tokenBeg=" + this.ts.tokenBeg + ", currentToken=" + this.currentToken);
    }

    protected class PerFunctionVariables {
        private ScriptNode savedCurrentScriptOrFn;
        private Scope savedCurrentScope;
        private int savedEndFlags;
        private boolean savedInForInit;
        private Map<String, LabeledStatement> savedLabelSet;
        private List<Loop> savedLoopSet;
        private List<Jump> savedLoopAndSwitchSet;

        PerFunctionVariables(FunctionNode fnNode) {
            this.savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
            Parser.this.currentScriptOrFn = fnNode;
            this.savedCurrentScope = Parser.this.currentScope;
            Parser.this.currentScope = fnNode;
            this.savedLabelSet = Parser.this.labelSet;
            Parser.this.labelSet = null;
            this.savedLoopSet = Parser.this.loopSet;
            Parser.this.loopSet = null;
            this.savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
            Parser.this.loopAndSwitchSet = null;
            this.savedEndFlags = Parser.this.endFlags;
            Parser.this.endFlags = 0;
            this.savedInForInit = Parser.this.inForInit;
            Parser.this.inForInit = false;
        }

        void restore() {
            Parser.this.currentScriptOrFn = this.savedCurrentScriptOrFn;
            Parser.this.currentScope = this.savedCurrentScope;
            Parser.this.labelSet = this.savedLabelSet;
            Parser.this.loopSet = this.savedLoopSet;
            Parser.this.loopAndSwitchSet = this.savedLoopAndSwitchSet;
            Parser.this.endFlags = this.savedEndFlags;
            Parser.this.inForInit = this.savedInForInit;
        }
    }

    private static class ConditionData {
        AstNode condition;
        int lp = -1;
        int rp = -1;

        private ConditionData() {
        }
    }

    private static class ParserException
    extends RuntimeException {
        static final long serialVersionUID = 5882582646773765630L;

        private ParserException() {
        }
    }
}

