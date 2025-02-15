/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.Logger;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.JavaScriptFunctionDeclaration;
import org.fife.rsta.ac.js.ast.JavaScriptVariableDeclaration;
import org.fife.rsta.ac.js.ast.TypeDeclarationOptions;
import org.fife.rsta.ac.js.ast.parser.JavaScriptParser;
import org.fife.rsta.ac.js.ast.type.ArrayTypeDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JavaScriptInScriptFunctionCompletion;
import org.fife.rsta.ac.js.resolver.JavaScriptResolver;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;

public class JavaScriptAstParser
extends JavaScriptParser {
    private ArrayList<ProcessFunctionType> functions = new ArrayList();

    public JavaScriptAstParser(SourceCompletionProvider provider, int dot, TypeDeclarationOptions options) {
        super(provider, dot, options);
    }

    @Override
    public CodeBlock convertAstNodeToCodeBlock(AstRoot root, Set<Completion> set, String entered) {
        this.functions.clear();
        CodeBlock block = new CodeBlock(0);
        this.addCodeBlock(root, set, entered, block, Integer.MAX_VALUE);
        this.setFunctionValues();
        this.provider.getLanguageSupport().getJavaScriptParser().setVariablesAndFunctions(this.provider.getVariableResolver());
        return block;
    }

    private void setFunctionValues() {
        for (ProcessFunctionType type : this.functions) {
            type.dec.setTypeDeclaration(type.typeNode);
        }
    }

    private void addCodeBlock(Node parent, Set<Completion> set, String entered, CodeBlock codeBlock, int offset) {
        if (parent == null) {
            return;
        }
        for (Node child = parent.getFirstChild(); child != null; child = child.getNext()) {
            CodeBlock childBlock = codeBlock;
            if (child instanceof AstNode) {
                AstNode node = (AstNode)child;
                int start = node.getAbsolutePosition();
                childBlock = codeBlock.addChildCodeBlock(start);
                childBlock.setEndOffset(offset);
            }
            this.iterateNode((AstNode)child, set, entered, childBlock, offset);
        }
    }

    protected void iterateNode(AstNode child, Set<Completion> set, String entered, CodeBlock block, int offset) {
        if (child == null) {
            return;
        }
        Logger.log(JavaScriptHelper.convertNodeToSource(child));
        Logger.log(child.shortName());
        if (JavaScriptHelper.isInfixOnly(child)) {
            this.processInfix(child, block, set, entered, offset);
        } else {
            switch (child.getType()) {
                case 109: {
                    this.processFunctionNode(child, block, set, entered, offset);
                    break;
                }
                case 122: {
                    this.processVariableNode(child, block, set, entered, offset);
                    break;
                }
                case 119: {
                    this.processForNode(child, block, set, entered, offset);
                    break;
                }
                case 117: {
                    this.processWhileNode(child, block, set, entered, offset);
                    break;
                }
                case 129: {
                    this.addCodeBlock(child, set, entered, block, offset);
                    break;
                }
                case 90: {
                    this.reassignVariable(child, block, offset);
                    break;
                }
                case 133: {
                    this.processExpressionNode(child, block, set, entered, offset);
                    break;
                }
                case 112: {
                    this.processIfThenElse(child, block, set, entered, offset);
                    break;
                }
                case 81: {
                    this.processTryCatchNode(child, block, set, entered, offset);
                    break;
                }
                case 118: {
                    this.processDoNode(child, block, set, entered, offset);
                    break;
                }
                case 114: {
                    this.processSwitchNode(child, block, set, entered, offset);
                    break;
                }
                case 115: {
                    this.processCaseNode(child, block, set, entered, offset);
                    break;
                }
                case -1: 
                case 4: 
                case 30: 
                case 33: 
                case 38: 
                case 39: 
                case 74: 
                case 120: 
                case 121: 
                case 124: 
                case 128: 
                case 147: {
                    break;
                }
                case 134: {
                    this.processExpressionStatement(child, block, set, entered, offset);
                    break;
                }
                default: {
                    Logger.log("Unhandled: " + child.getClass() + " (\"" + child.toString() + "\":" + child.getLineno());
                }
            }
        }
    }

    private void processExpressionStatement(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        ExpressionStatement exp = (ExpressionStatement)child;
        AstNode expNode = exp.getExpression();
        this.iterateNode(expNode, set, entered, block, offset);
    }

    private void reassignVariable(AstNode assign, CodeBlock block, int locationOffSet) {
        int start;
        int offset;
        String name;
        Assignment assignNode = (Assignment)assign;
        AstNode leftNode = assignNode.getLeft();
        AstNode rightNode = assignNode.getRight();
        String string = name = leftNode.getType() == 39 ? ((Name)leftNode).getIdentifier() : null;
        if (name != null && (offset = (start = assignNode.getAbsolutePosition()) + assignNode.getLength()) <= this.dot) {
            JavaScriptVariableDeclaration dec = this.provider.getVariableResolver().findDeclaration(name, this.dot);
            if (dec != null && (dec.getCodeBlock() == null || dec.getCodeBlock().contains(this.dot))) {
                dec.setTypeDeclaration(rightNode, this.isPreProcessing());
            } else {
                this.addVariableToResolver(leftNode, rightNode, block, locationOffSet);
            }
        }
    }

    private void addVariableToResolver(AstNode name, AstNode target, CodeBlock block, int offset) {
        JavaScriptVariableDeclaration dec = this.extractVariableFromNode(name, block, offset, target);
        if (dec != null && target != null && JavaScriptHelper.canResolveVariable(name, target)) {
            dec.setTypeDeclaration(target);
        } else if (dec != null) {
            dec.setTypeDeclaration(this.provider.getTypesFactory().getDefaultTypeDeclaration());
        }
        if (dec != null && this.canAddVariable(block)) {
            if (this.isPreProcessing()) {
                block.setStartOffSet(0);
                dec.setTypeDeclarationOptions(this.options);
                this.provider.getVariableResolver().addPreProcessingVariable(dec);
            } else {
                this.provider.getVariableResolver().addLocalVariable(dec);
            }
        }
    }

    private void processCaseNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        SwitchCase switchCase = (SwitchCase)child;
        List<AstNode> statements = switchCase.getStatements();
        int start = switchCase.getAbsolutePosition();
        offset = start + switchCase.getLength();
        if (this.canProcessNode(switchCase)) {
            block = block.addChildCodeBlock(start);
            block.setEndOffset(offset);
            if (statements != null) {
                for (AstNode node : statements) {
                    this.iterateNode(node, set, entered, block, offset);
                }
            }
        }
    }

    private void processSwitchNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        List<SwitchCase> cases;
        SwitchStatement switchStatement = (SwitchStatement)child;
        if (this.canProcessNode(switchStatement) && (cases = switchStatement.getCases()) != null) {
            for (SwitchCase switchCase : cases) {
                this.iterateNode(switchCase, set, entered, block, offset);
            }
        }
    }

    private void processTryCatchNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        TryStatement tryStatement = (TryStatement)child;
        if (this.canProcessNode(tryStatement)) {
            AstNode finallyNode;
            offset = tryStatement.getTryBlock().getAbsolutePosition() + tryStatement.getTryBlock().getLength();
            this.addCodeBlock(tryStatement.getTryBlock(), set, entered, block, offset);
            for (int i = 0; i < tryStatement.getCatchClauses().size(); ++i) {
                CatchClause clause = tryStatement.getCatchClauses().get(i);
                if (!this.canProcessNode(clause)) continue;
                offset = clause.getAbsolutePosition() + clause.getLength();
                CodeBlock catchBlock = block.getParent().addChildCodeBlock(clause.getAbsolutePosition());
                catchBlock.setEndOffset(offset);
                Name target = clause.getVarName();
                JavaScriptVariableDeclaration dec = this.extractVariableFromNode(target, catchBlock, offset);
                if (dec != null) {
                    dec.setTypeDeclaration(clause);
                }
                this.addCodeBlock(clause.getBody(), set, entered, catchBlock, offset);
            }
            if (tryStatement.getFinallyBlock() != null && this.canProcessNode(finallyNode = tryStatement.getFinallyBlock())) {
                offset = finallyNode.getAbsolutePosition() + finallyNode.getLength();
                CodeBlock finallyBlock = block.getParent().addChildCodeBlock(tryStatement.getFinallyBlock().getAbsolutePosition());
                this.addCodeBlock(finallyNode, set, entered, finallyBlock, offset);
                finallyBlock.setEndOffset(offset);
            }
        }
    }

    private void processIfThenElse(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        IfStatement ifStatement = (IfStatement)child;
        if (this.canProcessNode(ifStatement)) {
            offset = ifStatement.getAbsolutePosition() + ifStatement.getLength();
            this.addCodeBlock(ifStatement.getThenPart(), set, entered, block, offset);
            AstNode elseNode = ifStatement.getElsePart();
            if (elseNode != null) {
                int start = elseNode.getAbsolutePosition();
                CodeBlock childBlock = block.addChildCodeBlock(start);
                offset = start + elseNode.getLength();
                this.iterateNode(elseNode, set, entered, childBlock, offset);
                childBlock.setEndOffset(offset);
            }
        }
    }

    private void processExpressionNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        if (child instanceof ExpressionStatement) {
            ExpressionStatement expr = (ExpressionStatement)child;
            this.iterateNode(expr.getExpression(), set, entered, block, offset);
        }
    }

    private void processWhileNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        WhileLoop loop = (WhileLoop)child;
        if (this.canProcessNode(loop)) {
            offset = loop.getAbsolutePosition() + loop.getLength();
            this.addCodeBlock(loop.getBody(), set, entered, block, offset);
        }
    }

    private void processDoNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        DoLoop loop = (DoLoop)child;
        if (this.canProcessNode(loop)) {
            offset = loop.getAbsolutePosition() + loop.getLength();
            this.addCodeBlock(loop.getBody(), set, entered, block, offset);
        }
    }

    private void processInfix(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        InfixExpression epre = (InfixExpression)child;
        AstNode target = epre.getLeft();
        if (this.canProcessNode(target)) {
            this.extractVariableFromNode(target, block, offset);
            this.addCodeBlock(epre, set, entered, block, offset);
        }
    }

    private void processFunctionNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        FunctionNode fn = (FunctionNode)child;
        String jsdoc = fn.getJsDoc();
        TypeDeclaration returnType = this.getFunctionType(fn);
        JavaScriptInScriptFunctionCompletion fc = new JavaScriptInScriptFunctionCompletion((CompletionProvider)this.provider, fn.getName(), returnType);
        fc.setShortDescription(jsdoc);
        offset = fn.getAbsolutePosition() + fn.getLength();
        if (fn.getParamCount() > 0) {
            List<AstNode> fnParams = fn.getParams();
            ArrayList<ParameterizedCompletion.Parameter> params = new ArrayList<ParameterizedCompletion.Parameter>();
            for (int i = 0; i < fn.getParamCount(); ++i) {
                String paramName = null;
                AstNode node = fnParams.get(i);
                switch (node.getType()) {
                    case 39: {
                        paramName = ((Name)node).getIdentifier();
                        break;
                    }
                }
                ParameterizedCompletion.Parameter param = new ParameterizedCompletion.Parameter(null, paramName);
                params.add(param);
                if (this.isPreProcessing() || !this.canProcessNode(fn)) continue;
                JavaScriptVariableDeclaration dec = this.extractVariableFromNode(node, block, offset);
                this.provider.getVariableResolver().addLocalVariable(dec);
            }
            fc.setParams(params);
        }
        if (this.isPreProcessing()) {
            block.setStartOffSet(0);
        }
        if (this.isPreProcessing()) {
            JavaScriptFunctionDeclaration function = this.createJavaScriptFunction(fc.getLookupName(), offset, block, returnType, fn);
            function.setTypeDeclarationOptions(this.options);
            this.provider.getVariableResolver().addPreProcessingFunction(function);
        } else {
            this.provider.getVariableResolver().addLocalFunction(this.createJavaScriptFunction(fc.getLookupName(), offset, block, returnType, fn));
        }
        this.addCodeBlock(fn.getBody(), set, entered, block, offset);
        if (entered.indexOf(46) == -1) {
            set.add(fc);
        }
    }

    private JavaScriptFunctionDeclaration createJavaScriptFunction(String lookupName, int offset, CodeBlock block, TypeDeclaration returnType, FunctionNode fn) {
        Name name = fn.getFunctionName();
        JavaScriptFunctionDeclaration function = new JavaScriptFunctionDeclaration(lookupName, offset, block, returnType);
        if (name != null) {
            int start = name.getAbsolutePosition();
            int end = start + name.getLength();
            function.setStartOffset(start);
            function.setEndOffset(end);
            function.setFunctionName(fn.getName());
        }
        return function;
    }

    private boolean canProcessNode(AstNode node) {
        int start = node.getAbsolutePosition();
        int offset = start + node.getLength();
        return this.dot >= start && this.dot < offset;
    }

    private TypeDeclaration getFunctionType(FunctionNode fn) {
        FunctionReturnVisitor visitor = new FunctionReturnVisitor();
        fn.visit(visitor);
        return visitor.getCommonReturnType();
    }

    private void processVariableNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        if (block.contains(this.dot) || this.isPreProcessing()) {
            VariableDeclaration varDec = (VariableDeclaration)child;
            List<VariableInitializer> vars = varDec.getVariables();
            for (VariableInitializer var : vars) {
                this.extractVariableFromNode(var, block, offset);
            }
        }
    }

    private void processForNode(Node child, CodeBlock block, Set<Completion> set, String entered, int offset) {
        if (child instanceof ForLoop) {
            ForLoop loop = (ForLoop)child;
            offset = loop.getAbsolutePosition() + loop.getLength();
            if (this.canProcessNode(loop)) {
                this.iterateNode(loop.getInitializer(), set, entered, block, offset);
                this.addCodeBlock(loop.getBody(), set, entered, block, offset);
            }
        } else if (child instanceof ForInLoop) {
            ForInLoop loop = (ForInLoop)child;
            offset = loop.getAbsolutePosition() + loop.getLength();
            if (this.canProcessNode(loop)) {
                VariableDeclaration vd;
                List<VariableInitializer> variables;
                AstNode iteratedObject = loop.getIteratedObject();
                AstNode iterator = loop.getIterator();
                if (iterator != null && iterator.getType() == 122 && (variables = (vd = (VariableDeclaration)iterator).getVariables()).size() == 1) {
                    VariableInitializer vi = variables.get(0);
                    if (loop.isForEach()) {
                        this.extractVariableForForEach(vi, block, offset, iteratedObject);
                    } else {
                        this.extractVariableForForIn(vi, block, offset, iteratedObject);
                    }
                }
                this.addCodeBlock(loop.getBody(), set, entered, block, offset);
            }
        }
    }

    private void extractVariableFromNode(VariableInitializer initializer, CodeBlock block, int offset) {
        AstNode target = initializer.getTarget();
        if (target != null) {
            this.addVariableToResolver(target, initializer.getInitializer(), block, offset);
        }
    }

    private void extractVariableForForEach(VariableInitializer initializer, CodeBlock block, int offset, AstNode iteratedObject) {
        JavaScriptVariableDeclaration dec;
        AstNode target = initializer.getTarget();
        if (target != null && (dec = this.extractVariableFromNode(target, block, offset)) != null && iteratedObject != null && JavaScriptHelper.canResolveVariable(target, iteratedObject)) {
            JavaScriptResolver resolver = this.provider.getJavaScriptEngine().getJavaScriptResolver(this.provider);
            if (resolver != null) {
                TypeDeclaration iteratorDec = resolver.resolveNode(iteratedObject);
                if (iteratorDec instanceof ArrayTypeDeclaration) {
                    dec.setTypeDeclaration(((ArrayTypeDeclaration)iteratorDec).getArrayType());
                } else {
                    dec.setTypeDeclaration(iteratorDec);
                }
            }
            if (this.canAddVariable(block)) {
                this.provider.getVariableResolver().addLocalVariable(dec);
            }
        }
    }

    private void extractVariableForForIn(VariableInitializer initializer, CodeBlock block, int offset, AstNode iteratedObject) {
        JavaScriptVariableDeclaration dec;
        AstNode target = initializer.getTarget();
        if (target != null && (dec = this.extractVariableFromNode(target, block, offset)) != null && iteratedObject != null && JavaScriptHelper.canResolveVariable(target, iteratedObject)) {
            JavaScriptResolver resolver = this.provider.getJavaScriptEngine().getJavaScriptResolver(this.provider);
            if (resolver != null) {
                TypeDeclaration iteratorDec = resolver.resolveNode(iteratedObject);
                if (iteratorDec instanceof ArrayTypeDeclaration) {
                    dec.setTypeDeclaration(this.provider.getTypesFactory().getTypeDeclaration("JSNumber"));
                } else {
                    dec.setTypeDeclaration(this.provider.getTypesFactory().getDefaultTypeDeclaration());
                }
            }
            if (this.canAddVariable(block)) {
                this.provider.getVariableResolver().addLocalVariable(dec);
            }
        }
    }

    private boolean canAddVariable(CodeBlock block) {
        if (!this.isPreProcessing()) {
            return true;
        }
        CodeBlock parent = block.getParent();
        return parent != null && parent.getStartOffset() == 0;
    }

    private JavaScriptVariableDeclaration extractVariableFromNode(AstNode node, CodeBlock block, int offset) {
        return this.extractVariableFromNode(node, block, offset, null);
    }

    private JavaScriptVariableDeclaration extractVariableFromNode(AstNode node, CodeBlock block, int offset, AstNode initializer) {
        JavaScriptVariableDeclaration dec = null;
        if (node != null) {
            switch (node.getType()) {
                case 39: {
                    Name name = (Name)node;
                    dec = new JavaScriptVariableDeclaration(name.getIdentifier(), offset, this.provider, block);
                    dec.setStartOffset(name.getAbsolutePosition());
                    dec.setEndOffset(name.getAbsolutePosition() + name.getLength());
                    if (initializer != null && initializer.getType() == 38) {
                        ProcessFunctionType func = new ProcessFunctionType();
                        func.dec = dec;
                        func.typeNode = initializer;
                        this.functions.add(func);
                    }
                    if (initializer != null && !JavaScriptHelper.canResolveVariable(name, initializer)) break;
                    block.addVariable(dec);
                    break;
                }
                default: {
                    Logger.log("... Unknown var target type: " + node.getClass());
                }
            }
        }
        return dec;
    }

    public SourceCompletionProvider getProvider() {
        return this.provider;
    }

    public int getDot() {
        return this.dot;
    }

    static class ProcessFunctionType {
        AstNode typeNode;
        JavaScriptVariableDeclaration dec;

        ProcessFunctionType() {
        }
    }

    private class FunctionReturnVisitor
    implements NodeVisitor {
        private ArrayList<ReturnStatement> returnStatements = new ArrayList();

        private FunctionReturnVisitor() {
        }

        @Override
        public boolean visit(AstNode node) {
            switch (node.getType()) {
                case 4: {
                    this.returnStatements.add((ReturnStatement)node);
                }
            }
            return true;
        }

        public TypeDeclaration getCommonReturnType() {
            TypeDeclaration commonType = null;
            for (ReturnStatement rs : this.returnStatements) {
                AstNode returnValue = rs.getReturnValue();
                TypeDeclaration type = JavaScriptAstParser.this.provider.getJavaScriptEngine().getJavaScriptResolver(JavaScriptAstParser.this.provider).resolveNode(returnValue);
                if (commonType == null) {
                    commonType = type;
                    continue;
                }
                if (commonType.equals(type)) continue;
                commonType = JavaScriptAstParser.this.provider.getTypesFactory().getDefaultTypeDeclaration();
                break;
            }
            return commonType;
        }
    }
}

