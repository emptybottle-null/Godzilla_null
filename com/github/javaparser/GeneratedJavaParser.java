/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.GeneratedJavaParserBase;
import com.github.javaparser.GeneratedJavaParserConstants;
import com.github.javaparser.GeneratedJavaParserTokenManager;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ModifierHolder;
import com.github.javaparser.ParseException;
import com.github.javaparser.Provider;
import com.github.javaparser.RangedList;
import com.github.javaparser.SimpleCharStream;
import com.github.javaparser.StringProvider;
import com.github.javaparser.Token;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.expr.SwitchExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.YieldStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.utils.Pair;
import java.util.ArrayList;
import java.util.List;

final class GeneratedJavaParser
extends GeneratedJavaParserBase
implements GeneratedJavaParserConstants {
    public GeneratedJavaParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    private boolean jj_lookingAhead = false;
    private boolean jj_semLA;
    private int jj_gen;
    private final int[] jj_la1 = new int[171];
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private static int[] jj_la1_2;
    private static int[] jj_la1_3;
    private static int[] jj_la1_4;
    private final JJCalls[] jj_2_rtns = new JJCalls[60];
    private boolean jj_rescan = false;
    private int jj_gc = 0;
    private final LookaheadSuccess jj_ls = new LookaheadSuccess();
    private List<int[]> jj_expentries = new ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;
    private int[] jj_lasttokens = new int[100];
    private int jj_endpos;

    @Override
    JavaToken token() {
        return this.token.javaToken;
    }

    void setTabSize(int size) {
        this.jj_input_stream.setTabSize(size);
    }

    @Override
    GeneratedJavaParserTokenManager getTokenSource() {
        return this.token_source;
    }

    public final CompilationUnit CompilationUnit() throws ParseException {
        block25: {
            PackageDeclaration packageDeclaration = null;
            NodeList<ImportDeclaration> imports = this.emptyNodeList();
            ImportDeclaration in = null;
            NodeList<TypeDeclaration<?>> types = this.emptyNodeList();
            TypeDeclaration tn = null;
            ModuleDeclaration module = null;
            try {
                block24: {
                    while (this.jj_2_1(2)) {
                        this.jj_consume_token(102);
                    }
                    if (this.jj_2_2(Integer.MAX_VALUE)) {
                        packageDeclaration = this.PackageDeclaration();
                    }
                    block21: while (true) {
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 11: 
                            case 19: 
                            case 22: 
                            case 26: 
                            case 29: 
                            case 36: 
                            case 39: 
                            case 41: 
                            case 45: 
                            case 46: 
                            case 47: 
                            case 50: 
                            case 51: 
                            case 54: 
                            case 58: 
                            case 62: 
                            case 68: 
                            case 71: 
                            case 74: 
                            case 102: 
                            case 105: {
                                break;
                            }
                            default: {
                                this.jj_la1[0] = this.jj_gen;
                                break block24;
                            }
                        }
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 36: {
                                in = this.ImportDeclaration();
                                imports = this.add(imports, in);
                                continue block21;
                            }
                            case 11: 
                            case 19: 
                            case 22: 
                            case 26: 
                            case 29: 
                            case 39: 
                            case 41: 
                            case 45: 
                            case 46: 
                            case 47: 
                            case 50: 
                            case 51: 
                            case 54: 
                            case 58: 
                            case 62: 
                            case 68: 
                            case 71: 
                            case 74: 
                            case 102: 
                            case 105: {
                                ModifierHolder modifier = this.Modifiers();
                                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                    case 19: 
                                    case 39: {
                                        tn = this.ClassOrInterfaceDeclaration(modifier);
                                        types = this.add(types, tn);
                                        continue block21;
                                    }
                                    case 26: {
                                        tn = this.EnumDeclaration(modifier);
                                        types = this.add(types, tn);
                                        continue block21;
                                    }
                                    case 105: {
                                        tn = this.AnnotationTypeDeclaration(modifier);
                                        types = this.add(types, tn);
                                        continue block21;
                                    }
                                    case 68: 
                                    case 71: {
                                        module = this.ModuleDeclaration(modifier);
                                        continue block21;
                                    }
                                    case 102: {
                                        this.jj_consume_token(102);
                                        continue block21;
                                    }
                                }
                                this.jj_la1[1] = this.jj_gen;
                                this.jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                        break;
                    }
                    this.jj_la1[2] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 0: {
                        this.jj_consume_token(0);
                        break;
                    }
                    case 146: {
                        this.jj_consume_token(146);
                        break;
                    }
                    default: {
                        this.jj_la1[3] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                if ("" != null) {
                    return new CompilationUnit(this.range(this.token_source.getHomeToken(), this.token()), packageDeclaration, imports, types, module);
                }
            } catch (ParseException e) {
                this.recover(0, e);
                CompilationUnit compilationUnit = new CompilationUnit(this.range(this.token_source.getHomeToken(), this.token()), null, new NodeList<ImportDeclaration>(), new NodeList(), null);
                compilationUnit.setParsed(Node.Parsedness.UNPARSABLE);
                if ("" == null) break block25;
                return compilationUnit;
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final PackageDeclaration PackageDeclaration() throws ParseException {
        NodeList<AnnotationExpr> annotations = new NodeList();
        annotations = this.Annotations();
        this.jj_consume_token(44);
        JavaToken begin = this.token();
        Name name = this.Name();
        this.jj_consume_token(102);
        if ("" != null) {
            return new PackageDeclaration(this.range(begin, this.token()), annotations, name);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ImportDeclaration ImportDeclaration() throws ParseException {
        boolean isStatic = false;
        boolean isAsterisk = false;
        this.jj_consume_token(36);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 50: {
                this.jj_consume_token(50);
                isStatic = true;
                break;
            }
            default: {
                this.jj_la1[4] = this.jj_gen;
            }
        }
        Name name = this.Name();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 104: {
                this.jj_consume_token(104);
                this.jj_consume_token(123);
                isAsterisk = true;
                break;
            }
            default: {
                this.jj_la1[5] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new ImportDeclaration(this.range(begin, this.token()), name, isStatic, isAsterisk);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ModifierHolder Modifiers() throws ParseException {
        JavaToken begin = JavaToken.INVALID;
        NodeList<Modifier> modifiers = new NodeList<Modifier>();
        NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
        block16: while (this.jj_2_3(2)) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 47: {
                    this.jj_consume_token(47);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.PUBLIC));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 50: {
                    this.jj_consume_token(50);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.STATIC));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 46: {
                    this.jj_consume_token(46);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.PROTECTED));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 45: {
                    this.jj_consume_token(45);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.PRIVATE));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 29: {
                    this.jj_consume_token(29);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.FINAL));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 11: {
                    this.jj_consume_token(11);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.ABSTRACT));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 54: {
                    this.jj_consume_token(54);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.SYNCHRONIZED));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 41: {
                    this.jj_consume_token(41);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.NATIVE));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 58: {
                    this.jj_consume_token(58);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.TRANSIENT));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 62: {
                    this.jj_consume_token(62);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.VOLATILE));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 51: {
                    this.jj_consume_token(51);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.STRICTFP));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 74: {
                    this.jj_consume_token(74);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.TRANSITIVE));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 22: {
                    this.jj_consume_token(22);
                    this.add(modifiers, new Modifier(this.tokenRange(), Modifier.Keyword.DEFAULT));
                    begin = this.orIfInvalid(begin, this.token());
                    continue block16;
                }
                case 105: {
                    AnnotationExpr ann = this.Annotation();
                    annotations = this.add(annotations, ann);
                    begin = this.orIfInvalid(begin, ann);
                    continue block16;
                }
            }
            this.jj_la1[6] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
        }
        if ("" != null) {
            return new ModifierHolder(begin, modifiers, annotations);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ClassOrInterfaceDeclaration ClassOrInterfaceDeclaration(ModifierHolder modifier) throws ParseException {
        boolean isInterface;
        RangedList<Object> typePar = new RangedList(this.emptyNodeList());
        NodeList<ClassOrInterfaceType> extList = this.emptyNodeList();
        NodeList<ClassOrInterfaceType> impList = this.emptyNodeList();
        NodeList<BodyDeclaration<?>> members = this.emptyNodeList();
        JavaToken begin = modifier.begin;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 19: {
                this.jj_consume_token(19);
                isInterface = false;
                break;
            }
            case 39: {
                this.jj_consume_token(39);
                isInterface = true;
                break;
            }
            default: {
                this.jj_la1[7] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        begin = this.orIfInvalid(begin, this.token());
        SimpleName name = this.SimpleName();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 107: {
                typePar = this.TypeParameters();
                break;
            }
            default: {
                this.jj_la1[8] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 27: {
                extList = this.ExtendsList();
                break;
            }
            default: {
                this.jj_la1[9] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 35: {
                impList = this.ImplementsList();
                break;
            }
            default: {
                this.jj_la1[10] = this.jj_gen;
            }
        }
        members = this.ClassOrInterfaceBody();
        if ("" != null) {
            return new ClassOrInterfaceDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, isInterface, name, typePar.list, extList, impList, members);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<ClassOrInterfaceType> ExtendsList() throws ParseException {
        boolean extendsMoreThanOne = false;
        NodeList<ClassOrInterfaceType> ret = new NodeList<ClassOrInterfaceType>();
        this.jj_consume_token(27);
        ClassOrInterfaceType cit = this.AnnotatedClassOrInterfaceType();
        ret.add(cit);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[11] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            cit = this.AnnotatedClassOrInterfaceType();
            ret.add(cit);
            extendsMoreThanOne = true;
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<ClassOrInterfaceType> ImplementsList() throws ParseException {
        NodeList<ClassOrInterfaceType> ret = new NodeList<ClassOrInterfaceType>();
        this.jj_consume_token(35);
        ClassOrInterfaceType cit = this.AnnotatedClassOrInterfaceType();
        ret.add(cit);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[12] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            cit = this.AnnotatedClassOrInterfaceType();
            ret.add(cit);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final EnumDeclaration EnumDeclaration(ModifierHolder modifier) throws ParseException {
        NodeList<ClassOrInterfaceType> impList = this.emptyNodeList();
        NodeList<EnumConstantDeclaration> entries = this.emptyNodeList();
        NodeList<BodyDeclaration<?>> members = this.emptyNodeList();
        JavaToken begin = modifier.begin;
        this.jj_consume_token(26);
        begin = this.orIfInvalid(begin, this.token());
        SimpleName name = this.SimpleName();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 35: {
                impList = this.ImplementsList();
                break;
            }
            default: {
                this.jj_la1[13] = this.jj_gen;
            }
        }
        this.jj_consume_token(98);
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 26: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: 
            case 105: {
                EnumConstantDeclaration entry = this.EnumConstantDeclaration();
                entries.add(entry);
                while (this.jj_2_4(2)) {
                    this.jj_consume_token(103);
                    entry = this.EnumConstantDeclaration();
                    entries.add(entry);
                }
                break;
            }
            default: {
                this.jj_la1[14] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 103: {
                this.jj_consume_token(103);
                break;
            }
            default: {
                this.jj_la1[15] = this.jj_gen;
            }
        }
        block9 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 102: {
                this.jj_consume_token(102);
                block20: while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 11: 
                        case 13: 
                        case 15: 
                        case 18: 
                        case 19: 
                        case 22: 
                        case 24: 
                        case 26: 
                        case 29: 
                        case 31: 
                        case 38: 
                        case 39: 
                        case 40: 
                        case 41: 
                        case 45: 
                        case 46: 
                        case 47: 
                        case 49: 
                        case 50: 
                        case 51: 
                        case 54: 
                        case 58: 
                        case 61: 
                        case 62: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: 
                        case 98: 
                        case 102: 
                        case 105: 
                        case 107: {
                            break;
                        }
                        default: {
                            this.jj_la1[16] = this.jj_gen;
                            break block9;
                        }
                    }
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 11: 
                        case 13: 
                        case 15: 
                        case 18: 
                        case 19: 
                        case 22: 
                        case 24: 
                        case 26: 
                        case 29: 
                        case 31: 
                        case 38: 
                        case 39: 
                        case 40: 
                        case 41: 
                        case 45: 
                        case 46: 
                        case 47: 
                        case 49: 
                        case 50: 
                        case 51: 
                        case 54: 
                        case 58: 
                        case 61: 
                        case 62: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: 
                        case 98: 
                        case 105: 
                        case 107: {
                            BodyDeclaration<?> member = this.ClassOrInterfaceBodyDeclaration();
                            members = this.add(members, member);
                            continue block20;
                        }
                        case 102: {
                            this.jj_consume_token(102);
                            continue block20;
                        }
                    }
                    break;
                }
                this.jj_la1[17] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            default: {
                this.jj_la1[18] = this.jj_gen;
            }
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new EnumDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, name, impList, entries, members);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final EnumConstantDeclaration EnumConstantDeclaration() throws ParseException {
        NodeList<AnnotationExpr> annotations = new NodeList();
        NodeList<Expression> args = this.emptyNodeList();
        NodeList<BodyDeclaration<?>> classBody = this.emptyNodeList();
        JavaToken begin = JavaToken.INVALID;
        block9: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 105: {
                    break;
                }
                default: {
                    this.jj_la1[19] = this.jj_gen;
                    break block9;
                }
            }
            AnnotationExpr ann = this.Annotation();
            annotations = this.add(annotations, ann);
            begin = this.orIfInvalid(begin, ann);
        }
        SimpleName name = this.SimpleName();
        begin = this.orIfInvalid(begin, this.token());
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 96: {
                args = this.Arguments();
                break;
            }
            default: {
                this.jj_la1[20] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 98: {
                classBody = this.ClassOrInterfaceBody();
                break;
            }
            default: {
                this.jj_la1[21] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new EnumConstantDeclaration(this.range(begin, this.token()), annotations, name, args, classBody);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final RangedList<TypeParameter> TypeParameters() throws ParseException {
        RangedList<TypeParameter> ret = new RangedList<TypeParameter>(new NodeList());
        this.jj_consume_token(107);
        ret.beginAt(this.token());
        NodeList<AnnotationExpr> annotations = this.Annotations();
        TypeParameter tp = this.TypeParameter(annotations);
        ret.add(tp);
        annotations = null;
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[22] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            annotations = this.Annotations();
            tp = this.TypeParameter(annotations);
            ret.add(tp);
            annotations = null;
        }
        this.jj_consume_token(145);
        ret.endAt(this.token());
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TypeParameter TypeParameter(NodeList<AnnotationExpr> annotations) throws ParseException {
        NodeList<ClassOrInterfaceType> typeBound = this.emptyNodeList();
        SimpleName name = this.SimpleName();
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 27: {
                typeBound = this.TypeBound();
                break;
            }
            default: {
                this.jj_la1[23] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new TypeParameter(this.range(begin, this.token()), name, typeBound, annotations);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<ClassOrInterfaceType> TypeBound() throws ParseException {
        NodeList<ClassOrInterfaceType> ret = this.emptyNodeList();
        this.jj_consume_token(27);
        ClassOrInterfaceType cit = this.AnnotatedClassOrInterfaceType();
        ret.add(cit);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 125: {
                    break;
                }
                default: {
                    this.jj_la1[24] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(125);
            cit = this.AnnotatedClassOrInterfaceType();
            ret.add(cit);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<BodyDeclaration<?>> ClassOrInterfaceBody() throws ParseException {
        NodeList<BodyDeclaration<?>> ret;
        block9: {
            ret = this.emptyNodeList();
            this.jj_consume_token(98);
            block7: while (true) {
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 11: 
                    case 13: 
                    case 15: 
                    case 18: 
                    case 19: 
                    case 22: 
                    case 24: 
                    case 26: 
                    case 29: 
                    case 31: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 49: 
                    case 50: 
                    case 51: 
                    case 54: 
                    case 58: 
                    case 61: 
                    case 62: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: 
                    case 98: 
                    case 102: 
                    case 105: 
                    case 107: {
                        break;
                    }
                    default: {
                        this.jj_la1[25] = this.jj_gen;
                        break block9;
                    }
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 11: 
                    case 13: 
                    case 15: 
                    case 18: 
                    case 19: 
                    case 22: 
                    case 24: 
                    case 26: 
                    case 29: 
                    case 31: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 49: 
                    case 50: 
                    case 51: 
                    case 54: 
                    case 58: 
                    case 61: 
                    case 62: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: 
                    case 98: 
                    case 105: 
                    case 107: {
                        BodyDeclaration<?> member = this.ClassOrInterfaceBodyDeclaration();
                        ret.add(member);
                        continue block7;
                    }
                    case 102: {
                        this.jj_consume_token(102);
                        continue block7;
                    }
                }
                break;
            }
            this.jj_la1[26] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BodyDeclaration<?> ClassOrInterfaceBodyDeclaration() throws ParseException {
        BodyDeclaration ret;
        if (this.jj_2_9(2)) {
            ret = this.InitializerDeclaration();
        } else {
            block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 11: 
                case 13: 
                case 15: 
                case 18: 
                case 19: 
                case 22: 
                case 24: 
                case 26: 
                case 29: 
                case 31: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 45: 
                case 46: 
                case 47: 
                case 49: 
                case 50: 
                case 51: 
                case 54: 
                case 58: 
                case 61: 
                case 62: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 93: 
                case 105: 
                case 107: {
                    ModifierHolder modifiers = this.Modifiers();
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 19: 
                        case 39: {
                            ret = this.ClassOrInterfaceDeclaration(modifiers);
                            break block0;
                        }
                    }
                    this.jj_la1[27] = this.jj_gen;
                    if (this.jj_2_5(Integer.MAX_VALUE)) {
                        ret = this.EnumDeclaration(modifiers);
                        break;
                    }
                    if (this.jj_2_6(Integer.MAX_VALUE)) {
                        ret = this.AnnotationTypeDeclaration(modifiers);
                        break;
                    }
                    if (this.jj_2_7(Integer.MAX_VALUE)) {
                        ret = this.ConstructorDeclaration(modifiers);
                        break;
                    }
                    if (this.jj_2_8(Integer.MAX_VALUE)) {
                        ret = this.FieldDeclaration(modifiers);
                        break;
                    }
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 13: 
                        case 15: 
                        case 18: 
                        case 24: 
                        case 26: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 49: 
                        case 51: 
                        case 61: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: 
                        case 105: 
                        case 107: {
                            ret = this.MethodDeclaration(modifiers);
                            break block0;
                        }
                    }
                    this.jj_la1[28] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
                default: {
                    this.jj_la1[29] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final FieldDeclaration FieldDeclaration(ModifierHolder modifier) throws ParseException {
        NodeList<VariableDeclarator> variables = new NodeList<VariableDeclarator>();
        Type partialType = this.Type(this.emptyNodeList());
        VariableDeclarator val = this.VariableDeclarator(partialType);
        variables.add(val);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[30] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            val = this.VariableDeclarator(partialType);
            variables.add(val);
        }
        this.jj_consume_token(102);
        JavaToken begin = this.orIfInvalid(modifier.begin, partialType);
        if ("" != null) {
            return new FieldDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, variables);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final VariableDeclarator VariableDeclarator(Type partialType) throws ParseException {
        Expression init = null;
        Pair<SimpleName, List<ArrayType.ArrayBracketPair>> id = this.VariableDeclaratorId();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 106: {
                this.jj_consume_token(106);
                init = this.VariableInitializer();
                break;
            }
            default: {
                this.jj_la1[31] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new VariableDeclarator(this.range((Node)id.a, this.token()), this.juggleArrayType(partialType, (List)id.b), (SimpleName)id.a, init);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Pair<SimpleName, List<ArrayType.ArrayBracketPair>> VariableDeclaratorId() throws ParseException {
        List<Object> arrayBracketPairs = new ArrayList(0);
        SimpleName name = this.SimpleName();
        JavaToken begin = this.token();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 100: 
                case 105: {
                    break;
                }
                default: {
                    this.jj_la1[32] = this.jj_gen;
                    break block3;
                }
            }
            ArrayType.ArrayBracketPair arrayBracketPair = this.ArrayBracketPair(ArrayType.Origin.NAME);
            arrayBracketPairs = this.add(arrayBracketPairs, arrayBracketPair);
        }
        if (this.storeTokens) {
            name.setTokenRange(name.getTokenRange().get().withEnd(this.token()));
        }
        if ("" != null) {
            return new Pair<SimpleName, List<ArrayType.ArrayBracketPair>>(name, arrayBracketPairs);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression VariableInitializer() throws ParseException {
        Expression ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 98: {
                ret = this.ArrayInitializer();
                break;
            }
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                ret = this.Expression();
                break;
            }
            default: {
                this.jj_la1[33] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ArrayInitializerExpr ArrayInitializer() throws ParseException {
        NodeList<Expression> values = this.emptyNodeList();
        this.jj_consume_token(98);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 98: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                Expression val = this.VariableInitializer();
                values = this.add(values, val);
                while (this.jj_2_10(2)) {
                    this.jj_consume_token(103);
                    val = this.VariableInitializer();
                    values = this.add(values, val);
                }
                break;
            }
            default: {
                this.jj_la1[34] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 103: {
                this.jj_consume_token(103);
                break;
            }
            default: {
                this.jj_la1[35] = this.jj_gen;
            }
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new ArrayInitializerExpr(this.range(begin, this.token()), values);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final MethodDeclaration MethodDeclaration(ModifierHolder modifier) throws ParseException {
        RangedList<Object> typeParameters = new RangedList(this.emptyNodeList());
        Pair<NodeList<Object>, Object> parameters = new Pair(this.emptyNodeList(), null);
        ArrayList<ArrayType.ArrayBracketPair> arrayBracketPairs = new ArrayList(0);
        NodeList<ReferenceType> throws_ = this.emptyNodeList();
        BlockStmt body = null;
        JavaToken begin = modifier.begin;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 107: {
                typeParameters = this.TypeParameters();
                begin = this.orIfInvalid(begin, typeParameters.range.getBegin());
                break;
            }
            default: {
                this.jj_la1[36] = this.jj_gen;
            }
        }
        NodeList<AnnotationExpr> annotations = this.Annotations();
        modifier.annotations.addAll(annotations);
        begin = this.orIfInvalid(begin, this.nodeListBegin(annotations));
        Type type = this.ResultType(this.emptyNodeList());
        begin = this.orIfInvalid(begin, type);
        SimpleName name = this.SimpleName();
        parameters = this.Parameters();
        block16: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 100: 
                case 105: {
                    break;
                }
                default: {
                    this.jj_la1[37] = this.jj_gen;
                    break block16;
                }
            }
            ArrayType.ArrayBracketPair arrayBracketPair = this.ArrayBracketPair(ArrayType.Origin.NAME);
            arrayBracketPairs = this.add(arrayBracketPairs, arrayBracketPair);
        }
        block6 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 57: {
                this.jj_consume_token(57);
                ReferenceType throwType = this.AnnotatedReferenceType();
                throws_ = this.add(throws_, throwType);
                while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 103: {
                            break;
                        }
                        default: {
                            this.jj_la1[38] = this.jj_gen;
                            break block6;
                        }
                    }
                    this.jj_consume_token(103);
                    throwType = this.AnnotatedReferenceType();
                    throws_ = this.add(throws_, throwType);
                }
            }
            default: {
                this.jj_la1[39] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 98: {
                body = this.Block();
                break;
            }
            case 102: {
                this.jj_consume_token(102);
                break;
            }
            default: {
                this.jj_la1[40] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        type = this.juggleArrayType(type, arrayBracketPairs);
        if ("" != null) {
            return new MethodDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, typeParameters.list, type, name, (NodeList)parameters.a, throws_, body, (ReceiverParameter)parameters.b);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ReferenceType AnnotatedReferenceType() throws ParseException {
        NodeList<AnnotationExpr> annotations = this.Annotations();
        ReferenceType type = this.ReferenceType(annotations);
        if ("" != null) {
            return type;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Type AnnotatedType() throws ParseException {
        NodeList<AnnotationExpr> annotations = this.Annotations();
        Type type = this.Type(annotations);
        if ("" != null) {
            return type;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Pair<NodeList<Parameter>, ReceiverParameter> Parameters() throws ParseException {
        NodeList<Object> ret = this.emptyNodeList();
        ReceiverParameter rp = null;
        this.jj_consume_token(96);
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 11: 
            case 13: 
            case 15: 
            case 18: 
            case 22: 
            case 24: 
            case 26: 
            case 29: 
            case 31: 
            case 38: 
            case 40: 
            case 41: 
            case 45: 
            case 46: 
            case 47: 
            case 49: 
            case 50: 
            case 51: 
            case 54: 
            case 58: 
            case 62: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: 
            case 105: {
                Parameter par;
                if (this.jj_2_11(Integer.MAX_VALUE)) {
                    rp = this.ReceiverParameter();
                } else {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 11: 
                        case 13: 
                        case 15: 
                        case 18: 
                        case 22: 
                        case 24: 
                        case 26: 
                        case 29: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 41: 
                        case 45: 
                        case 46: 
                        case 47: 
                        case 49: 
                        case 50: 
                        case 51: 
                        case 54: 
                        case 58: 
                        case 62: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: 
                        case 105: {
                            par = this.Parameter();
                            ret = this.add(ret, par);
                            break;
                        }
                        default: {
                            this.jj_la1[41] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 103: {
                            break;
                        }
                        default: {
                            this.jj_la1[42] = this.jj_gen;
                            break block0;
                        }
                    }
                    this.jj_consume_token(103);
                    par = this.Parameter();
                    ret = this.add(ret, par);
                }
            }
            default: {
                this.jj_la1[43] = this.jj_gen;
            }
        }
        this.jj_consume_token(97);
        if ("" != null) {
            return new Pair<NodeList<Parameter>, ReceiverParameter>(ret, rp);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Parameter> LambdaParameters() throws ParseException {
        NodeList<Parameter> ret = null;
        Parameter par = this.Parameter();
        ret = this.add(ret, par);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[44] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            par = this.Parameter();
            ret = this.add(ret, par);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Parameter> InferredLambdaParameters() throws ParseException {
        NodeList<Parameter> ret = null;
        Pair<SimpleName, List<ArrayType.ArrayBracketPair>> id = this.VariableDeclaratorId();
        ret = this.add(ret, new Parameter(this.range((Node)id.a, (Node)id.a), new NodeList<Modifier>(), this.emptyNodeList(), new UnknownType(), false, this.emptyNodeList(), (SimpleName)id.a));
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[45] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            id = this.VariableDeclaratorId();
            ret = this.add(ret, new Parameter(this.range((Node)id.a, (Node)id.a), new NodeList<Modifier>(), this.emptyNodeList(), new UnknownType(), false, this.emptyNodeList(), (SimpleName)id.a));
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Parameter Parameter() throws ParseException {
        boolean isVarArg = false;
        NodeList<AnnotationExpr> varArgAnnotations = this.emptyNodeList();
        ModifierHolder modifier = this.Modifiers();
        Type partialType = this.Type(this.emptyNodeList());
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 105: 
            case 141: {
                varArgAnnotations = this.Annotations();
                this.jj_consume_token(141);
                isVarArg = true;
                break;
            }
            default: {
                this.jj_la1[46] = this.jj_gen;
            }
        }
        Pair<SimpleName, List<ArrayType.ArrayBracketPair>> id = this.VariableDeclaratorId();
        JavaToken begin = this.orIfInvalid(modifier.begin, partialType);
        if ("" != null) {
            return new Parameter(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, this.juggleArrayType(partialType, (List)id.b), isVarArg, varArgAnnotations, (SimpleName)id.a);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ReceiverParameter ReceiverParameter() throws ParseException {
        NodeList<AnnotationExpr> annotations = this.emptyNodeList();
        annotations = this.Annotations();
        Type partialType = this.Type(this.emptyNodeList());
        Name id = this.ReceiverParameterId();
        if ("" != null) {
            return new ReceiverParameter(this.range((Node)partialType, this.token()), annotations, partialType, id);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Name ReceiverParameterId() throws ParseException {
        Name ret = null;
        if (this.jj_2_12(Integer.MAX_VALUE)) {
            ret = this.Name();
            this.jj_consume_token(104);
        }
        this.jj_consume_token(55);
        if ("" != null) {
            return new Name(this.tokenRange(), ret, this.token.image);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ConstructorDeclaration ConstructorDeclaration(ModifierHolder modifier) throws ParseException {
        RangedList<Object> typeParameters = new RangedList(this.emptyNodeList());
        Pair<NodeList<Object>, Object> parameters = new Pair(this.emptyNodeList(), null);
        NodeList<ReferenceType> throws_ = this.emptyNodeList();
        ExplicitConstructorInvocationStmt exConsInv = null;
        NodeList<Statement> stmts = this.emptyNodeList();
        JavaToken begin = modifier.begin;
        JavaToken blockBegin = JavaToken.INVALID;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 107: {
                typeParameters = this.TypeParameters();
                begin = this.orIfInvalid(begin, typeParameters.range.getBegin());
                break;
            }
            default: {
                this.jj_la1[47] = this.jj_gen;
            }
        }
        SimpleName name = this.SimpleName();
        begin = this.orIfInvalid(begin, typeParameters.range.getBegin());
        begin = this.orIfInvalid(begin, this.token());
        parameters = this.Parameters();
        block3 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 57: {
                this.jj_consume_token(57);
                ReferenceType throwType = this.AnnotatedReferenceType();
                throws_ = this.add(throws_, throwType);
                while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 103: {
                            break;
                        }
                        default: {
                            this.jj_la1[48] = this.jj_gen;
                            break block3;
                        }
                    }
                    this.jj_consume_token(103);
                    throwType = this.AnnotatedReferenceType();
                    throws_ = this.add(throws_, throwType);
                }
            }
            default: {
                this.jj_la1[49] = this.jj_gen;
            }
        }
        this.jj_consume_token(98);
        blockBegin = this.token();
        if (this.jj_2_13(Integer.MAX_VALUE)) {
            exConsInv = this.ExplicitConstructorInvocation();
        }
        stmts = this.Statements();
        this.jj_consume_token(99);
        if (exConsInv != null) {
            stmts = this.prepend(stmts, exConsInv);
        }
        if ("" != null) {
            return new ConstructorDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, typeParameters.list, name, (NodeList)parameters.a, throws_, new BlockStmt(this.range(blockBegin, this.token()), stmts), (ReceiverParameter)parameters.b);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ExplicitConstructorInvocationStmt ExplicitConstructorInvocation() throws ParseException {
        NodeList<Expression> args;
        boolean isThis = false;
        Expression expr = null;
        RangedList<Object> typeArgs = new RangedList(null);
        JavaToken begin = JavaToken.INVALID;
        if (this.jj_2_15(Integer.MAX_VALUE)) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 107: {
                    typeArgs = this.TypeArguments();
                    begin = typeArgs.range.getBegin();
                    break;
                }
                default: {
                    this.jj_la1[50] = this.jj_gen;
                }
            }
            this.jj_consume_token(55);
            begin = this.orIfInvalid(begin, this.token());
            isThis = true;
            args = this.Arguments();
            this.jj_consume_token(102);
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: 
                case 107: {
                    if (this.jj_2_14(Integer.MAX_VALUE)) {
                        expr = this.PrimaryExpressionWithoutSuperSuffix();
                        this.jj_consume_token(104);
                        begin = this.orIfInvalid(begin, expr);
                    }
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 107: {
                            typeArgs = this.TypeArguments();
                            begin = this.orIfInvalid(begin, typeArgs.range.getBegin());
                            break;
                        }
                        default: {
                            this.jj_la1[51] = this.jj_gen;
                        }
                    }
                    this.jj_consume_token(52);
                    begin = this.orIfInvalid(begin, this.token());
                    args = this.Arguments();
                    this.jj_consume_token(102);
                    break;
                }
                default: {
                    this.jj_la1[52] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return new ExplicitConstructorInvocationStmt(this.range(begin, this.token()), typeArgs.list, isThis, expr, args);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Statement> Statements() throws ParseException {
        NodeList<Statement> ret = this.emptyNodeList();
        while (this.jj_2_16(2)) {
            Statement stmt = this.BlockStatement();
            ret = this.add(ret, stmt);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final InitializerDeclaration InitializerDeclaration() throws ParseException {
        JavaToken begin = JavaToken.INVALID;
        boolean isStatic = false;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 50: {
                this.jj_consume_token(50);
                isStatic = true;
                begin = this.token();
                break;
            }
            default: {
                this.jj_la1[53] = this.jj_gen;
            }
        }
        BlockStmt body = this.Block();
        begin = this.orIfInvalid(begin, body);
        if ("" != null) {
            return new InitializerDeclaration(this.range(begin, this.token()), isStatic, body);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Type Type(NodeList<AnnotationExpr> annotations) throws ParseException {
        Type ret;
        if (this.jj_2_17(Integer.MAX_VALUE)) {
            ret = this.ReferenceType(annotations);
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 31: 
                case 38: 
                case 40: 
                case 49: {
                    ret = this.PrimitiveType(annotations);
                    break;
                }
                default: {
                    this.jj_la1[54] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ReferenceType ReferenceType(NodeList<AnnotationExpr> annotations) throws ParseException {
        Type type;
        List<Object> arrayBracketPairs = new ArrayList(0);
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 31: 
            case 38: 
            case 40: 
            case 49: {
                type = this.PrimitiveType(annotations);
                while (true) {
                    ArrayType.ArrayBracketPair arrayBracketPair = this.ArrayBracketPair(ArrayType.Origin.TYPE);
                    arrayBracketPairs = this.add(arrayBracketPairs, arrayBracketPair);
                    if (!this.jj_2_18(Integer.MAX_VALUE)) break block0;
                }
            }
            case 26: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                type = this.ClassOrInterfaceType(annotations);
                while (this.jj_2_19(Integer.MAX_VALUE)) {
                    ArrayType.ArrayBracketPair arrayBracketPair = this.ArrayBracketPair(ArrayType.Origin.TYPE);
                    arrayBracketPairs = this.add(arrayBracketPairs, arrayBracketPair);
                }
                break;
            }
            default: {
                this.jj_la1[55] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return (ReferenceType)ArrayType.wrapInArrayTypes(type, arrayBracketPairs);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ArrayType.ArrayBracketPair ArrayBracketPair(ArrayType.Origin origin) throws ParseException {
        JavaToken begin = JavaToken.INVALID;
        NodeList<AnnotationExpr> annotations = this.Annotations();
        this.jj_consume_token(100);
        begin = this.orIfInvalid(begin, this.token());
        this.jj_consume_token(101);
        if ("" != null) {
            return new ArrayType.ArrayBracketPair(this.range(begin, this.token()), origin, annotations);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final IntersectionType IntersectionType(NodeList<AnnotationExpr> annotations) throws ParseException {
        JavaToken begin = JavaToken.INVALID;
        NodeList<ReferenceType> elements = this.emptyNodeList();
        ReferenceType elementType = this.ReferenceType(annotations);
        begin = this.orIfInvalid(begin, elementType);
        elements = this.add(elements, elementType);
        this.jj_consume_token(125);
        block3: while (true) {
            elementType = this.AnnotatedReferenceType();
            elements = this.add(elements, elementType);
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 31: 
                case 38: 
                case 40: 
                case 49: 
                case 51: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 93: 
                case 105: {
                    continue block3;
                }
            }
            break;
        }
        this.jj_la1[56] = this.jj_gen;
        if ("" != null) {
            return new IntersectionType(this.range(begin, this.token()), elements);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ClassOrInterfaceType AnnotatedClassOrInterfaceType() throws ParseException {
        NodeList<AnnotationExpr> annotations = new NodeList();
        annotations = this.Annotations();
        ClassOrInterfaceType cit = this.ClassOrInterfaceType(annotations);
        if ("" != null) {
            return cit;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ClassOrInterfaceType ClassOrInterfaceType(NodeList<AnnotationExpr> firstAnnotations) throws ParseException {
        RangedList<Object> typeArgs = new RangedList(null);
        NodeList<AnnotationExpr> annotations = new NodeList();
        SimpleName name = this.SimpleName();
        JavaToken begin = this.token();
        if (this.jj_2_20(2)) {
            typeArgs = this.TypeArguments();
        }
        ClassOrInterfaceType ret = new ClassOrInterfaceType(this.range(begin, this.token()), null, name, typeArgs.list, firstAnnotations);
        typeArgs = new RangedList(null);
        while (this.jj_2_21(2)) {
            this.jj_consume_token(104);
            annotations = this.Annotations();
            name = this.SimpleName();
            if (this.jj_2_22(2)) {
                typeArgs = this.TypeArguments();
            }
            ret = new ClassOrInterfaceType(this.range(begin, this.token()), ret, name, typeArgs.list, annotations);
            typeArgs = new RangedList(null);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final RangedList<Type> TypeArguments() throws ParseException {
        RangedList<Type> ret = new RangedList<Type>(new NodeList());
        this.jj_consume_token(107);
        ret.beginAt(this.token());
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 31: 
            case 38: 
            case 40: 
            case 49: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: 
            case 105: 
            case 110: {
                Type type = this.TypeArgument();
                ret.add(type);
                while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 103: {
                            break;
                        }
                        default: {
                            this.jj_la1[57] = this.jj_gen;
                            break block0;
                        }
                    }
                    this.jj_consume_token(103);
                    type = this.TypeArgument();
                    ret.add(type);
                }
            }
            default: {
                this.jj_la1[58] = this.jj_gen;
            }
        }
        this.jj_consume_token(145);
        ret.endAt(this.token());
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Type TypeArgument() throws ParseException {
        Type ret;
        NodeList<AnnotationExpr> annotations = this.Annotations();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 31: 
            case 38: 
            case 40: 
            case 49: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                ret = this.Type(annotations);
                break;
            }
            case 110: {
                ret = this.Wildcard(annotations);
                break;
            }
            default: {
                this.jj_la1[59] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final WildcardType Wildcard(NodeList<AnnotationExpr> firstAnnotations) throws ParseException {
        ReferenceType ext = null;
        ReferenceType sup = null;
        NodeList<AnnotationExpr> annotations = new NodeList();
        this.jj_consume_token(110);
        JavaToken begin = this.token();
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 27: 
            case 52: {
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 27: {
                        this.jj_consume_token(27);
                        annotations = this.Annotations();
                        ext = this.ReferenceType(annotations);
                        break block0;
                    }
                    case 52: {
                        this.jj_consume_token(52);
                        annotations = this.Annotations();
                        sup = this.ReferenceType(annotations);
                        break block0;
                    }
                }
                this.jj_la1[60] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            default: {
                this.jj_la1[61] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new WildcardType(this.range(begin, this.token()), ext, sup, firstAnnotations);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final PrimitiveType PrimitiveType(NodeList<AnnotationExpr> annotations) throws ParseException {
        PrimitiveType ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: {
                this.jj_consume_token(13);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.BOOLEAN, annotations);
                break;
            }
            case 18: {
                this.jj_consume_token(18);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.CHAR, annotations);
                break;
            }
            case 15: {
                this.jj_consume_token(15);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.BYTE, annotations);
                break;
            }
            case 49: {
                this.jj_consume_token(49);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.SHORT, annotations);
                break;
            }
            case 38: {
                this.jj_consume_token(38);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.INT, annotations);
                break;
            }
            case 40: {
                this.jj_consume_token(40);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.LONG, annotations);
                break;
            }
            case 31: {
                this.jj_consume_token(31);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.FLOAT, annotations);
                break;
            }
            case 24: {
                this.jj_consume_token(24);
                ret = new PrimitiveType(this.tokenRange(), PrimitiveType.Primitive.DOUBLE, annotations);
                break;
            }
            default: {
                this.jj_la1[62] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Type ResultType(NodeList<AnnotationExpr> annotations) throws ParseException {
        Type ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 61: {
                this.jj_consume_token(61);
                ret = new VoidType(this.tokenRange());
                break;
            }
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 31: 
            case 38: 
            case 40: 
            case 49: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                ret = this.Type(annotations);
                break;
            }
            default: {
                this.jj_la1[63] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Name Name() throws ParseException {
        this.Identifier();
        Name ret = new Name(this.tokenRange(), null, this.token.image);
        while (this.jj_2_23(2)) {
            this.jj_consume_token(104);
            this.Identifier();
            ret = new Name(this.range((Node)ret, this.token()), ret, this.token.image);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SimpleName SimpleName() throws ParseException {
        this.Identifier();
        SimpleName ret = new SimpleName(this.tokenRange(), this.token.image);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final String Identifier() throws ParseException {
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 71: {
                this.jj_consume_token(71);
                break;
            }
            case 65: {
                this.jj_consume_token(65);
                break;
            }
            case 66: {
                this.jj_consume_token(66);
                break;
            }
            case 67: {
                this.jj_consume_token(67);
                break;
            }
            case 68: {
                this.jj_consume_token(68);
                break;
            }
            case 69: {
                this.jj_consume_token(69);
                break;
            }
            case 70: {
                this.jj_consume_token(70);
                break;
            }
            case 72: {
                this.jj_consume_token(72);
                break;
            }
            case 73: {
                this.jj_consume_token(73);
                break;
            }
            case 74: {
                this.jj_consume_token(74);
                break;
            }
            case 26: {
                this.jj_consume_token(26);
                break;
            }
            case 51: {
                this.jj_consume_token(51);
                break;
            }
            case 64: {
                this.jj_consume_token(64);
                break;
            }
            case 93: {
                this.jj_consume_token(93);
                break;
            }
            default: {
                this.jj_la1[64] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        String ret = this.token.image;
        this.setTokenKind(93);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression Expression() throws ParseException {
        Statement lambdaBody = null;
        RangedList<Object> typeArgs = new RangedList(null);
        Expression ret = this.ConditionalExpression();
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 106: 
            case 112: 
            case 130: 
            case 131: 
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 137: 
            case 138: 
            case 139: 
            case 140: 
            case 142: {
                if (this.jj_2_24(2)) {
                    AssignExpr.Operator op = this.AssignmentOperator();
                    Expression value = this.Expression();
                    ret = new AssignExpr(this.range((Node)ret, this.token()), ret, value, op);
                    break;
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 112: {
                        this.jj_consume_token(112);
                        lambdaBody = this.LambdaBody();
                        if (ret instanceof CastExpr) {
                            ret = this.generateLambda(ret, lambdaBody);
                            break block0;
                        }
                        if (ret instanceof ConditionalExpr) {
                            ConditionalExpr ce = (ConditionalExpr)ret;
                            if (ce.getElseExpr() == null) break block0;
                            ce.setElseExpr(this.generateLambda(ce.getElseExpr(), lambdaBody));
                            break block0;
                        }
                        ret = this.generateLambda(ret, lambdaBody);
                        break block0;
                    }
                    case 142: {
                        this.jj_consume_token(142);
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 107: {
                                typeArgs = this.TypeArguments();
                                break;
                            }
                            default: {
                                this.jj_la1[65] = this.jj_gen;
                            }
                        }
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 26: 
                            case 51: 
                            case 64: 
                            case 65: 
                            case 66: 
                            case 67: 
                            case 68: 
                            case 69: 
                            case 70: 
                            case 71: 
                            case 72: 
                            case 73: 
                            case 74: 
                            case 93: {
                                this.Identifier();
                                break;
                            }
                            case 42: {
                                this.jj_consume_token(42);
                                break;
                            }
                            default: {
                                this.jj_la1[66] = this.jj_gen;
                                this.jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                        ret = new MethodReferenceExpr(this.range((Node)ret, this.token()), ret, typeArgs.list, this.token.image);
                        break block0;
                    }
                    default: {
                        this.jj_la1[67] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
            }
            default: {
                this.jj_la1[68] = this.jj_gen;
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AssignExpr.Operator AssignmentOperator() throws ParseException {
        AssignExpr.Operator ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 106: {
                this.jj_consume_token(106);
                ret = AssignExpr.Operator.ASSIGN;
                break;
            }
            case 132: {
                this.jj_consume_token(132);
                ret = AssignExpr.Operator.MULTIPLY;
                break;
            }
            case 133: {
                this.jj_consume_token(133);
                ret = AssignExpr.Operator.DIVIDE;
                break;
            }
            case 137: {
                this.jj_consume_token(137);
                ret = AssignExpr.Operator.REMAINDER;
                break;
            }
            case 130: {
                this.jj_consume_token(130);
                ret = AssignExpr.Operator.PLUS;
                break;
            }
            case 131: {
                this.jj_consume_token(131);
                ret = AssignExpr.Operator.MINUS;
                break;
            }
            case 138: {
                this.jj_consume_token(138);
                ret = AssignExpr.Operator.LEFT_SHIFT;
                break;
            }
            case 139: {
                this.jj_consume_token(139);
                ret = AssignExpr.Operator.SIGNED_RIGHT_SHIFT;
                break;
            }
            case 140: {
                this.jj_consume_token(140);
                ret = AssignExpr.Operator.UNSIGNED_RIGHT_SHIFT;
                break;
            }
            case 134: {
                this.jj_consume_token(134);
                ret = AssignExpr.Operator.BINARY_AND;
                break;
            }
            case 136: {
                this.jj_consume_token(136);
                ret = AssignExpr.Operator.XOR;
                break;
            }
            case 135: {
                this.jj_consume_token(135);
                ret = AssignExpr.Operator.BINARY_OR;
                break;
            }
            default: {
                this.jj_la1[69] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ConditionalExpression() throws ParseException {
        Expression ret = this.ConditionalOrExpression();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 110: {
                this.jj_consume_token(110);
                Expression left = this.Expression();
                this.jj_consume_token(111);
                Expression right = this.ConditionalExpression();
                ret = new ConditionalExpr(this.range((Node)ret, this.token()), ret, left, right);
                break;
            }
            default: {
                this.jj_la1[70] = this.jj_gen;
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ConditionalOrExpression() throws ParseException {
        Expression ret = this.ConditionalAndExpression();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 118: {
                    break;
                }
                default: {
                    this.jj_la1[71] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(118);
            Expression right = this.ConditionalAndExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, BinaryExpr.Operator.OR);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ConditionalAndExpression() throws ParseException {
        Expression ret = this.InclusiveOrExpression();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 117: {
                    break;
                }
                default: {
                    this.jj_la1[72] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(117);
            Expression right = this.InclusiveOrExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, BinaryExpr.Operator.AND);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression InclusiveOrExpression() throws ParseException {
        Expression ret = this.ExclusiveOrExpression();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 126: {
                    break;
                }
                default: {
                    this.jj_la1[73] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(126);
            Expression right = this.ExclusiveOrExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, BinaryExpr.Operator.BINARY_OR);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ExclusiveOrExpression() throws ParseException {
        Expression ret = this.AndExpression();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 127: {
                    break;
                }
                default: {
                    this.jj_la1[74] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(127);
            Expression right = this.AndExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, BinaryExpr.Operator.XOR);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression AndExpression() throws ParseException {
        Expression ret = this.EqualityExpression();
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 125: {
                    break;
                }
                default: {
                    this.jj_la1[75] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(125);
            Expression right = this.EqualityExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, BinaryExpr.Operator.BINARY_AND);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression EqualityExpression() throws ParseException {
        Expression ret = this.InstanceOfExpression();
        block7: while (true) {
            BinaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 113: 
                case 116: {
                    break;
                }
                default: {
                    this.jj_la1[76] = this.jj_gen;
                    break block7;
                }
            }
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 113: {
                    this.jj_consume_token(113);
                    op = BinaryExpr.Operator.EQUALS;
                    break;
                }
                case 116: {
                    this.jj_consume_token(116);
                    op = BinaryExpr.Operator.NOT_EQUALS;
                    break;
                }
                default: {
                    this.jj_la1[77] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            Expression right = this.InstanceOfExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final PatternExpr PatternExpression() throws ParseException {
        ReferenceType type = this.AnnotatedReferenceType();
        SimpleName name = this.SimpleName();
        if ("" != null) {
            return new PatternExpr(this.range((Node)type, this.token()), type, name);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression InstanceOfExpression() throws ParseException {
        Expression ret = this.RelationalExpression();
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 37: {
                this.jj_consume_token(37);
                if (this.jj_2_25(Integer.MAX_VALUE)) {
                    PatternExpr pattern = this.PatternExpression();
                    ret = new InstanceOfExpr(this.range((Node)ret, this.token()), ret, pattern.getType(), pattern);
                    break;
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 13: 
                    case 15: 
                    case 18: 
                    case 24: 
                    case 26: 
                    case 31: 
                    case 38: 
                    case 40: 
                    case 49: 
                    case 51: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: 
                    case 105: {
                        ReferenceType type = this.AnnotatedReferenceType();
                        ret = new InstanceOfExpr(this.range((Node)ret, this.token()), ret, type, null);
                        break block0;
                    }
                }
                this.jj_la1[78] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            default: {
                this.jj_la1[79] = this.jj_gen;
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression RelationalExpression() throws ParseException {
        Expression ret = this.ShiftExpression();
        block9: while (true) {
            BinaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 107: 
                case 114: 
                case 115: 
                case 145: {
                    break;
                }
                default: {
                    this.jj_la1[80] = this.jj_gen;
                    break block9;
                }
            }
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 107: {
                    this.jj_consume_token(107);
                    op = BinaryExpr.Operator.LESS;
                    break;
                }
                case 145: {
                    this.jj_consume_token(145);
                    op = BinaryExpr.Operator.GREATER;
                    break;
                }
                case 115: {
                    this.jj_consume_token(115);
                    op = BinaryExpr.Operator.LESS_EQUALS;
                    break;
                }
                case 114: {
                    this.jj_consume_token(114);
                    op = BinaryExpr.Operator.GREATER_EQUALS;
                    break;
                }
                default: {
                    this.jj_la1[81] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            Expression right = this.ShiftExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ShiftExpression() throws ParseException {
        Expression ret = this.AdditiveExpression();
        while (this.jj_2_26(1)) {
            BinaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 129: {
                    this.jj_consume_token(129);
                    op = BinaryExpr.Operator.LEFT_SHIFT;
                    break;
                }
                default: {
                    this.jj_la1[82] = this.jj_gen;
                    if (this.jj_2_27(1)) {
                        this.RSIGNEDSHIFT();
                        op = BinaryExpr.Operator.SIGNED_RIGHT_SHIFT;
                        break;
                    }
                    if (this.jj_2_28(1)) {
                        this.RUNSIGNEDSHIFT();
                        op = BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT;
                        break;
                    }
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            Expression right = this.AdditiveExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression AdditiveExpression() throws ParseException {
        Expression ret = this.MultiplicativeExpression();
        block7: while (true) {
            BinaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 121: 
                case 122: {
                    break;
                }
                default: {
                    this.jj_la1[83] = this.jj_gen;
                    break block7;
                }
            }
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 121: {
                    this.jj_consume_token(121);
                    op = BinaryExpr.Operator.PLUS;
                    break;
                }
                case 122: {
                    this.jj_consume_token(122);
                    op = BinaryExpr.Operator.MINUS;
                    break;
                }
                default: {
                    this.jj_la1[84] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            Expression right = this.MultiplicativeExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression MultiplicativeExpression() throws ParseException {
        Expression ret = this.UnaryExpression();
        block8: while (true) {
            BinaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 123: 
                case 124: 
                case 128: {
                    break;
                }
                default: {
                    this.jj_la1[85] = this.jj_gen;
                    break block8;
                }
            }
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 123: {
                    this.jj_consume_token(123);
                    op = BinaryExpr.Operator.MULTIPLY;
                    break;
                }
                case 124: {
                    this.jj_consume_token(124);
                    op = BinaryExpr.Operator.DIVIDE;
                    break;
                }
                case 128: {
                    this.jj_consume_token(128);
                    op = BinaryExpr.Operator.REMAINDER;
                    break;
                }
                default: {
                    this.jj_la1[86] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            Expression right = this.UnaryExpression();
            ret = new BinaryExpr(this.range((Node)ret, this.token()), ret, right, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression UnaryExpression() throws ParseException {
        Expression ret;
        JavaToken begin = JavaToken.INVALID;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 119: {
                ret = this.PreIncrementExpression();
                break;
            }
            case 120: {
                ret = this.PreDecrementExpression();
                break;
            }
            case 121: 
            case 122: {
                UnaryExpr.Operator op;
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 121: {
                        this.jj_consume_token(121);
                        op = UnaryExpr.Operator.PLUS;
                        begin = this.token();
                        break;
                    }
                    case 122: {
                        this.jj_consume_token(122);
                        op = UnaryExpr.Operator.MINUS;
                        begin = this.token();
                        break;
                    }
                    default: {
                        this.jj_la1[87] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                ret = this.UnaryExpression();
                ret = new UnaryExpr(this.range(begin, this.token()), ret, op);
                break;
            }
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 105: 
            case 108: 
            case 109: {
                ret = this.UnaryExpressionNotPlusMinus();
                break;
            }
            default: {
                this.jj_la1[88] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PreIncrementExpression() throws ParseException {
        JavaToken begin = JavaToken.INVALID;
        this.jj_consume_token(119);
        begin = this.token();
        Expression ret = this.UnaryExpression();
        ret = new UnaryExpr(this.range(begin, this.token()), ret, UnaryExpr.Operator.PREFIX_INCREMENT);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PreDecrementExpression() throws ParseException {
        this.jj_consume_token(120);
        JavaToken begin = this.token();
        Expression ret = this.UnaryExpression();
        ret = new UnaryExpr(this.range(begin, this.token()), ret, UnaryExpr.Operator.PREFIX_DECREMENT);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression UnaryExpressionNotPlusMinus() throws ParseException {
        Expression ret;
        JavaToken begin = JavaToken.INVALID;
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 108: 
            case 109: {
                UnaryExpr.Operator op;
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 109: {
                        this.jj_consume_token(109);
                        op = UnaryExpr.Operator.BITWISE_COMPLEMENT;
                        begin = this.token();
                        break;
                    }
                    case 108: {
                        this.jj_consume_token(108);
                        op = UnaryExpr.Operator.LOGICAL_COMPLEMENT;
                        begin = this.token();
                        break;
                    }
                    default: {
                        this.jj_la1[89] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                ret = this.UnaryExpression();
                ret = new UnaryExpr(this.range(begin, this.token()), ret, op);
                break;
            }
            default: {
                this.jj_la1[90] = this.jj_gen;
                if (this.jj_2_29(Integer.MAX_VALUE)) {
                    ret = this.CastExpression();
                    break;
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 13: 
                    case 15: 
                    case 18: 
                    case 24: 
                    case 26: 
                    case 28: 
                    case 31: 
                    case 38: 
                    case 40: 
                    case 42: 
                    case 43: 
                    case 49: 
                    case 51: 
                    case 52: 
                    case 55: 
                    case 59: 
                    case 61: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 75: 
                    case 76: 
                    case 81: 
                    case 88: 
                    case 89: 
                    case 91: 
                    case 93: 
                    case 96: 
                    case 105: {
                        ret = this.PostfixExpression();
                        break block0;
                    }
                    case 53: {
                        ret = this.SwitchExpression();
                        break block0;
                    }
                }
                this.jj_la1[91] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PostfixExpression() throws ParseException {
        Expression ret = this.PrimaryExpression();
        if (this.jj_2_30(2)) {
            UnaryExpr.Operator op;
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 119: {
                    this.jj_consume_token(119);
                    op = UnaryExpr.Operator.POSTFIX_INCREMENT;
                    break;
                }
                case 120: {
                    this.jj_consume_token(120);
                    op = UnaryExpr.Operator.POSTFIX_DECREMENT;
                    break;
                }
                default: {
                    this.jj_la1[92] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
            ret = new UnaryExpr(this.range((Node)ret, this.token()), ret, op);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression CastExpression() throws ParseException {
        Expression ret;
        JavaToken begin = JavaToken.INVALID;
        NodeList<Object> typesOfMultiCast = this.emptyNodeList();
        this.jj_consume_token(96);
        begin = this.token();
        NodeList<AnnotationExpr> annotations = this.Annotations();
        if (this.jj_2_31(2)) {
            PrimitiveType primitiveType = this.PrimitiveType(annotations);
            this.jj_consume_token(97);
            ret = this.UnaryExpression();
            ret = new CastExpr(this.range(begin, this.token()), primitiveType, ret);
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 31: 
                case 38: 
                case 40: 
                case 49: 
                case 51: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 93: {
                    ReferenceType referenceType = this.ReferenceType(annotations);
                    typesOfMultiCast = this.add(typesOfMultiCast, referenceType);
                    block6: while (true) {
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 125: {
                                break;
                            }
                            default: {
                                this.jj_la1[93] = this.jj_gen;
                                break block6;
                            }
                        }
                        this.jj_consume_token(125);
                        referenceType = this.AnnotatedReferenceType();
                        typesOfMultiCast = this.add(typesOfMultiCast, referenceType);
                    }
                    this.jj_consume_token(97);
                    ret = this.UnaryExpressionNotPlusMinus();
                    if (typesOfMultiCast.size() > 1) {
                        ret = new CastExpr(this.range(begin, this.token()), new IntersectionType(this.range((Node)typesOfMultiCast.get(0), (Node)typesOfMultiCast.get(typesOfMultiCast.size() - 1)), (NodeList<ReferenceType>)typesOfMultiCast), ret);
                        break;
                    }
                    ret = new CastExpr(this.range(begin, this.token()), referenceType, ret);
                    break;
                }
                default: {
                    this.jj_la1[94] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PrimaryExpression() throws ParseException {
        Expression ret = this.PrimaryPrefix();
        while (this.jj_2_32(2)) {
            ret = this.PrimarySuffix(ret);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PrimaryExpressionWithoutSuperSuffix() throws ParseException {
        Expression ret = this.PrimaryPrefix();
        while (this.jj_2_33(Integer.MAX_VALUE)) {
            ret = this.PrimarySuffixWithoutSuper(ret);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PrimaryPrefix() throws ParseException {
        Expression ret = null;
        RangedList<Object> typeArgs = new RangedList(null);
        NodeList<Expression> args = this.emptyNodeList();
        NodeList<Parameter> params = this.emptyNodeList();
        boolean hasArgs = false;
        boolean isLambda = false;
        Object p = null;
        Object id = null;
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 28: 
            case 43: 
            case 59: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: {
                ret = this.Literal();
                break;
            }
            case 55: {
                this.jj_consume_token(55);
                ret = new ThisExpr(this.tokenRange(), null);
                break;
            }
            case 52: {
                this.jj_consume_token(52);
                ret = new SuperExpr(this.tokenRange(), null);
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 104: {
                        this.jj_consume_token(104);
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 107: {
                                typeArgs = this.TypeArguments();
                                break;
                            }
                            default: {
                                this.jj_la1[95] = this.jj_gen;
                            }
                        }
                        SimpleName name = this.SimpleName();
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 96: {
                                args = this.Arguments();
                                hasArgs = true;
                                break;
                            }
                            default: {
                                this.jj_la1[96] = this.jj_gen;
                            }
                        }
                        if (hasArgs) {
                            ret = new MethodCallExpr(this.range((Node)ret, this.token()), ret, typeArgs.list, name, args);
                            break block0;
                        }
                        ret = new FieldAccessExpr(this.range((Node)ret, this.token()), ret, this.emptyNodeList(), name);
                        break block0;
                    }
                    case 142: {
                        this.jj_consume_token(142);
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 107: {
                                typeArgs = this.TypeArguments();
                                break;
                            }
                            default: {
                                this.jj_la1[97] = this.jj_gen;
                            }
                        }
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 26: 
                            case 51: 
                            case 64: 
                            case 65: 
                            case 66: 
                            case 67: 
                            case 68: 
                            case 69: 
                            case 70: 
                            case 71: 
                            case 72: 
                            case 73: 
                            case 74: 
                            case 93: {
                                this.Identifier();
                                break;
                            }
                            case 42: {
                                this.jj_consume_token(42);
                                break;
                            }
                            default: {
                                this.jj_la1[98] = this.jj_gen;
                                this.jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                        ret = new MethodReferenceExpr(this.range((Node)ret, this.token()), ret, typeArgs.list, this.token.image);
                        break block0;
                    }
                }
                this.jj_la1[99] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            case 96: {
                this.jj_consume_token(96);
                JavaToken begin = this.token();
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 97: {
                        this.jj_consume_token(97);
                        ret = new LambdaExpr(this.range(begin, this.token()), params, new BlockStmt(), true);
                        break block0;
                    }
                }
                this.jj_la1[100] = this.jj_gen;
                if (this.jj_2_34(Integer.MAX_VALUE)) {
                    params = this.LambdaParameters();
                    this.jj_consume_token(97);
                    ret = new LambdaExpr(this.range(begin, this.token()), params, new BlockStmt(), true);
                    break;
                }
                if (this.jj_2_35(Integer.MAX_VALUE)) {
                    params = this.InferredLambdaParameters();
                    this.jj_consume_token(97);
                    ret = new LambdaExpr(this.range(begin, this.token()), params, new BlockStmt(), true);
                    break;
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 13: 
                    case 15: 
                    case 18: 
                    case 24: 
                    case 26: 
                    case 28: 
                    case 31: 
                    case 38: 
                    case 40: 
                    case 42: 
                    case 43: 
                    case 49: 
                    case 51: 
                    case 52: 
                    case 53: 
                    case 55: 
                    case 59: 
                    case 61: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 75: 
                    case 76: 
                    case 81: 
                    case 88: 
                    case 89: 
                    case 91: 
                    case 93: 
                    case 96: 
                    case 105: 
                    case 108: 
                    case 109: 
                    case 119: 
                    case 120: 
                    case 121: 
                    case 122: {
                        ret = this.Expression();
                        this.jj_consume_token(97);
                        ret = new EnclosedExpr(this.range(begin, this.token()), ret);
                        break block0;
                    }
                }
                this.jj_la1[101] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            case 42: {
                ret = this.AllocationExpression(null);
                break;
            }
            default: {
                this.jj_la1[105] = this.jj_gen;
                if (this.jj_2_36(Integer.MAX_VALUE)) {
                    Type type = this.ResultType(this.emptyNodeList());
                    this.jj_consume_token(104);
                    this.jj_consume_token(19);
                    ret = new ClassExpr(this.range((Node)type, this.token()), type);
                    break;
                }
                if (this.jj_2_37(Integer.MAX_VALUE)) {
                    Type type = this.AnnotatedType();
                    this.jj_consume_token(142);
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 107: {
                            typeArgs = this.TypeArguments();
                            break;
                        }
                        default: {
                            this.jj_la1[102] = this.jj_gen;
                        }
                    }
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 26: 
                        case 51: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: {
                            this.Identifier();
                            break;
                        }
                        case 42: {
                            this.jj_consume_token(42);
                            break;
                        }
                        default: {
                            this.jj_la1[103] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    ret = new TypeExpr(this.range((Node)type, (Node)type), type);
                    ret = new MethodReferenceExpr(this.range((Node)ret, this.token()), ret, typeArgs.list, this.token.image);
                    break;
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 26: 
                    case 51: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: {
                        SimpleName name = this.SimpleName();
                        JavaToken begin = this.token();
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 96: {
                                args = this.Arguments();
                                hasArgs = true;
                                break;
                            }
                            default: {
                                this.jj_la1[104] = this.jj_gen;
                            }
                        }
                        if (hasArgs) {
                            ret = new MethodCallExpr(this.range(begin, this.token()), null, null, name, args);
                            break block0;
                        }
                        ret = new NameExpr(name);
                        break block0;
                    }
                }
                this.jj_la1[106] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PrimarySuffix(Expression scope) throws ParseException {
        Expression ret;
        if (this.jj_2_38(2)) {
            ret = this.PrimarySuffixWithoutSuper(scope);
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 104: {
                    this.jj_consume_token(104);
                    this.jj_consume_token(52);
                    ret = new SuperExpr(this.range((Node)scope, this.token()), this.scopeToName(scope));
                    break;
                }
                default: {
                    this.jj_la1[107] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression PrimarySuffixWithoutSuper(Expression scope) throws ParseException {
        Expression ret;
        RangedList<Object> typeArgs = new RangedList(null);
        NodeList<Expression> args = this.emptyNodeList();
        boolean hasArgs = false;
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 104: {
                this.jj_consume_token(104);
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 55: {
                        this.jj_consume_token(55);
                        ret = new ThisExpr(this.range((Node)scope, this.token()), this.scopeToName(scope));
                        break block0;
                    }
                    case 42: {
                        ret = this.AllocationExpression(scope);
                        break block0;
                    }
                }
                this.jj_la1[110] = this.jj_gen;
                if (this.jj_2_39(Integer.MAX_VALUE)) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 107: {
                            typeArgs = this.TypeArguments();
                            break;
                        }
                        default: {
                            this.jj_la1[108] = this.jj_gen;
                        }
                    }
                    SimpleName name = this.SimpleName();
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 96: {
                            args = this.Arguments();
                            hasArgs = true;
                            break;
                        }
                        default: {
                            this.jj_la1[109] = this.jj_gen;
                        }
                    }
                    if (hasArgs) {
                        ret = new MethodCallExpr(this.range((Node)scope, this.token()), scope, typeArgs.list, name, args);
                        break;
                    }
                    ret = new FieldAccessExpr(this.range((Node)scope, this.token()), scope, typeArgs.list, name);
                    break;
                }
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            case 100: {
                this.jj_consume_token(100);
                ret = this.Expression();
                this.jj_consume_token(101);
                ret = new ArrayAccessExpr(this.range((Node)scope, this.token()), scope, ret);
                break;
            }
            default: {
                this.jj_la1[111] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression Literal() throws ParseException {
        Expression ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 76: {
                this.jj_consume_token(76);
                ret = new IntegerLiteralExpr(this.tokenRange(), this.token.image);
                break;
            }
            case 75: {
                this.jj_consume_token(75);
                ret = new LongLiteralExpr(this.tokenRange(), this.token.image);
                break;
            }
            case 81: {
                this.jj_consume_token(81);
                ret = new DoubleLiteralExpr(this.tokenRange(), this.token.image);
                break;
            }
            case 88: {
                this.jj_consume_token(88);
                ret = new CharLiteralExpr(this.tokenRange(), this.unquote(this.token.image));
                break;
            }
            case 89: {
                this.jj_consume_token(89);
                ret = new StringLiteralExpr(this.tokenRange(), this.unquote(this.token.image));
                break;
            }
            case 91: {
                this.jj_consume_token(91);
                ret = new TextBlockLiteralExpr(this.tokenRange(), this.unTripleQuote(this.token.image));
                break;
            }
            case 28: 
            case 59: {
                ret = this.BooleanLiteral();
                break;
            }
            case 43: {
                ret = this.NullLiteral();
                break;
            }
            default: {
                this.jj_la1[112] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression BooleanLiteral() throws ParseException {
        BooleanLiteralExpr ret;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 59: {
                this.jj_consume_token(59);
                ret = new BooleanLiteralExpr(this.tokenRange(), true);
                break;
            }
            case 28: {
                this.jj_consume_token(28);
                ret = new BooleanLiteralExpr(this.tokenRange(), false);
                break;
            }
            default: {
                this.jj_la1[113] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression NullLiteral() throws ParseException {
        this.jj_consume_token(43);
        if ("" != null) {
            return new NullLiteralExpr(this.tokenRange());
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> Arguments() throws ParseException {
        NodeList<Expression> ret = this.emptyNodeList();
        this.jj_consume_token(96);
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                ret = this.ArgumentList();
                break;
            }
            default: {
                this.jj_la1[114] = this.jj_gen;
            }
        }
        this.jj_consume_token(97);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> ArgumentList() throws ParseException {
        NodeList<Expression> ret = this.emptyNodeList();
        Expression expr = this.Expression();
        ret.add(expr);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[115] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            expr = this.Expression();
            ret.add(expr);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression AllocationExpression(Expression scope) throws ParseException {
        Expression ret;
        RangedList<Object> typeArgs = new RangedList(null);
        NodeList<BodyDeclaration<?>> anonymousBody = null;
        JavaToken begin = JavaToken.INVALID;
        NodeList<AnnotationExpr> annotations = new NodeList();
        this.jj_consume_token(42);
        begin = scope == null ? this.token() : this.orIfInvalid(begin, scope);
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 107: {
                typeArgs = this.TypeArguments();
                break;
            }
            default: {
                this.jj_la1[116] = this.jj_gen;
            }
        }
        annotations = this.Annotations();
        block3 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 31: 
            case 38: 
            case 40: 
            case 49: {
                PrimitiveType type = this.PrimitiveType(annotations);
                ret = this.ArrayCreation(begin, type);
                break;
            }
            case 26: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                ClassOrInterfaceType type = this.ClassOrInterfaceType(annotations);
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 100: 
                    case 105: {
                        ret = this.ArrayCreation(begin, type);
                        break block3;
                    }
                    case 96: {
                        NodeList<Expression> args = this.Arguments();
                        if (this.jj_2_40(2)) {
                            anonymousBody = this.ClassOrInterfaceBody();
                        }
                        ret = new ObjectCreationExpr(this.range(begin, this.token()), scope, type, typeArgs.list, args, anonymousBody);
                        break block3;
                    }
                }
                this.jj_la1[117] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            default: {
                this.jj_la1[118] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ArrayCreationExpr ArrayCreation(JavaToken begin, Type type) throws ParseException {
        Expression expr = null;
        ArrayInitializerExpr arrayInitializerExpr = null;
        NodeList<Expression> inits = this.emptyNodeList();
        ArrayList<NodeList<AnnotationExpr>> accum = new ArrayList();
        NodeList<Object> annotations = new NodeList();
        JavaToken arrayCreationLevelStart = JavaToken.INVALID;
        ArrayList<TokenRange> levelRanges = new ArrayList<TokenRange>();
        do {
            annotations = this.Annotations();
            this.jj_consume_token(100);
            arrayCreationLevelStart = annotations.isEmpty() ? this.token() : this.orIfInvalid(arrayCreationLevelStart, (Node)annotations.get(0));
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 53: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: 
                case 108: 
                case 109: 
                case 119: 
                case 120: 
                case 121: 
                case 122: {
                    expr = this.Expression();
                    break;
                }
                default: {
                    this.jj_la1[119] = this.jj_gen;
                }
            }
            accum = this.add(accum, annotations);
            inits = this.add(inits, expr);
            annotations = null;
            expr = null;
            this.jj_consume_token(101);
            levelRanges.add(this.range(arrayCreationLevelStart, this.token()));
        } while (this.jj_2_41(2));
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 98: {
                arrayInitializerExpr = this.ArrayInitializer();
                break;
            }
            default: {
                this.jj_la1[120] = this.jj_gen;
            }
        }
        if ("" != null) {
            return this.juggleArrayCreation(this.range(begin, this.token()), levelRanges, type, inits, accum, arrayInitializerExpr);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Statement Statement() throws ParseException {
        block25: {
            try {
                Statement ret;
                if (this.jj_2_42(2)) {
                    ret = this.LabeledStatement();
                } else {
                    block1 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 12: {
                            ret = this.AssertStatement();
                            break;
                        }
                        default: {
                            this.jj_la1[121] = this.jj_gen;
                            if (this.jj_2_43(3)) {
                                ret = this.YieldStatement();
                                break;
                            }
                            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                case 98: {
                                    ret = this.Block();
                                    break block1;
                                }
                                case 102: {
                                    ret = this.EmptyStatement();
                                    break block1;
                                }
                                case 13: 
                                case 15: 
                                case 18: 
                                case 24: 
                                case 26: 
                                case 28: 
                                case 31: 
                                case 38: 
                                case 40: 
                                case 42: 
                                case 43: 
                                case 49: 
                                case 51: 
                                case 52: 
                                case 55: 
                                case 59: 
                                case 61: 
                                case 64: 
                                case 65: 
                                case 66: 
                                case 67: 
                                case 68: 
                                case 69: 
                                case 70: 
                                case 71: 
                                case 72: 
                                case 73: 
                                case 74: 
                                case 75: 
                                case 76: 
                                case 81: 
                                case 88: 
                                case 89: 
                                case 91: 
                                case 93: 
                                case 96: 
                                case 105: 
                                case 119: 
                                case 120: {
                                    ret = this.StatementExpression();
                                    break block1;
                                }
                                case 53: {
                                    ret = this.SwitchStatement();
                                    break block1;
                                }
                                case 34: {
                                    ret = this.IfStatement();
                                    break block1;
                                }
                                case 63: {
                                    ret = this.WhileStatement();
                                    break block1;
                                }
                                case 23: {
                                    ret = this.DoStatement();
                                    break block1;
                                }
                                case 32: {
                                    ret = this.ForStatement();
                                    break block1;
                                }
                                case 14: {
                                    ret = this.BreakStatement();
                                    break block1;
                                }
                                case 21: {
                                    ret = this.ContinueStatement();
                                    break block1;
                                }
                                case 48: {
                                    ret = this.ReturnStatement();
                                    break block1;
                                }
                                case 56: {
                                    ret = this.ThrowStatement();
                                    break block1;
                                }
                                case 54: {
                                    ret = this.SynchronizedStatement();
                                    break block1;
                                }
                                case 60: {
                                    ret = this.TryStatement();
                                    break block1;
                                }
                            }
                            this.jj_la1[122] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                if ("" != null) {
                    return ret;
                }
            } catch (ParseException e) {
                TokenRange errorRange = this.recover(102, e);
                if ("" == null) break block25;
                return new UnparsableStmt(errorRange);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AssertStmt AssertStatement() throws ParseException {
        Expression msg = null;
        this.jj_consume_token(12);
        JavaToken begin = this.token();
        Expression check = this.Expression();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 111: {
                this.jj_consume_token(111);
                msg = this.Expression();
                break;
            }
            default: {
                this.jj_la1[123] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new AssertStmt(this.range(begin, this.token()), check, msg);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final LabeledStmt LabeledStatement() throws ParseException {
        SimpleName label = this.SimpleName();
        JavaToken begin = this.token();
        this.jj_consume_token(111);
        Statement stmt = this.Statement();
        if ("" != null) {
            return new LabeledStmt(this.range(begin, this.token()), label, stmt);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BlockStmt Block() throws ParseException {
        block3: {
            NodeList<Statement> stmts = this.emptyNodeList();
            this.jj_consume_token(98);
            JavaToken begin = this.token();
            try {
                stmts = this.Statements();
                this.jj_consume_token(99);
                if ("" != null) {
                    return new BlockStmt(this.range(begin, this.token()), stmts);
                }
            } catch (ParseException e) {
                this.recover(99, e);
                BlockStmt block = new BlockStmt(this.range(begin, this.token()), new NodeList<Statement>());
                block.setParsed(Node.Parsedness.UNPARSABLE);
                if ("" == null) break block3;
                return block;
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Statement BlockStatement() throws ParseException {
        block10: {
            try {
                Statement ret;
                if (this.jj_2_44(Integer.MAX_VALUE)) {
                    ModifierHolder modifier = this.Modifiers();
                    ClassOrInterfaceDeclaration typeDecl = this.ClassOrInterfaceDeclaration(modifier);
                    ret = new LocalClassDeclarationStmt(this.range((Node)typeDecl, this.token()), typeDecl);
                } else if (this.jj_2_45(Integer.MAX_VALUE)) {
                    VariableDeclarationExpr expr = this.VariableDeclarationExpression();
                    this.jj_consume_token(102);
                    ret = new ExpressionStmt(this.range((Node)expr, this.token()), expr);
                } else {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 12: 
                        case 13: 
                        case 14: 
                        case 15: 
                        case 18: 
                        case 21: 
                        case 23: 
                        case 24: 
                        case 26: 
                        case 28: 
                        case 31: 
                        case 32: 
                        case 34: 
                        case 38: 
                        case 40: 
                        case 42: 
                        case 43: 
                        case 48: 
                        case 49: 
                        case 51: 
                        case 52: 
                        case 53: 
                        case 54: 
                        case 55: 
                        case 56: 
                        case 59: 
                        case 60: 
                        case 61: 
                        case 63: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 75: 
                        case 76: 
                        case 81: 
                        case 88: 
                        case 89: 
                        case 91: 
                        case 93: 
                        case 96: 
                        case 98: 
                        case 102: 
                        case 105: 
                        case 119: 
                        case 120: {
                            ret = this.Statement();
                            break;
                        }
                        default: {
                            this.jj_la1[124] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                if ("" != null) {
                    return ret;
                }
            } catch (ParseException e) {
                TokenRange errorRange = this.recover(102, e);
                if ("" == null) break block10;
                return new UnparsableStmt(errorRange);
            }
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final VariableDeclarationExpr VariableDeclarationExpression() throws ParseException {
        NodeList<VariableDeclarator> variables = new NodeList<VariableDeclarator>();
        ModifierHolder modifier = this.Modifiers();
        Type partialType = this.Type(this.emptyNodeList());
        VariableDeclarator var = this.VariableDeclarator(partialType);
        variables.add(var);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[125] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            var = this.VariableDeclarator(partialType);
            variables.add(var);
        }
        JavaToken begin = this.orIfInvalid(modifier.begin, partialType);
        if ("" != null) {
            return new VariableDeclarationExpr(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, variables);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final EmptyStmt EmptyStatement() throws ParseException {
        this.jj_consume_token(102);
        if ("" != null) {
            return new EmptyStmt(this.tokenRange());
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Statement LambdaBody() throws ParseException {
        Statement n = null;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                Expression expr = this.Expression();
                n = new ExpressionStmt(this.range((Node)expr, this.token()), expr);
                break;
            }
            case 98: {
                n = this.Block();
                break;
            }
            default: {
                this.jj_la1[126] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return n;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ExpressionStmt StatementExpression() throws ParseException {
        Expression expr;
        RangedList typeArgs = new RangedList(null);
        if (this.jj_2_46(2)) {
            expr = this.PreIncrementExpression();
        } else {
            block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 120: {
                    expr = this.PreDecrementExpression();
                    break;
                }
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: {
                    expr = this.PrimaryExpression();
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 106: 
                        case 119: 
                        case 120: 
                        case 130: 
                        case 131: 
                        case 132: 
                        case 133: 
                        case 134: 
                        case 135: 
                        case 136: 
                        case 137: 
                        case 138: 
                        case 139: 
                        case 140: {
                            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                case 119: {
                                    this.jj_consume_token(119);
                                    expr = new UnaryExpr(this.range((Node)expr, this.token()), expr, UnaryExpr.Operator.POSTFIX_INCREMENT);
                                    break block0;
                                }
                                case 120: {
                                    this.jj_consume_token(120);
                                    expr = new UnaryExpr(this.range((Node)expr, this.token()), expr, UnaryExpr.Operator.POSTFIX_DECREMENT);
                                    break block0;
                                }
                                case 106: 
                                case 130: 
                                case 131: 
                                case 132: 
                                case 133: 
                                case 134: 
                                case 135: 
                                case 136: 
                                case 137: 
                                case 138: 
                                case 139: 
                                case 140: {
                                    AssignExpr.Operator op = this.AssignmentOperator();
                                    Expression value = this.Expression();
                                    expr = new AssignExpr(this.range((Node)expr, this.token()), expr, value, op);
                                    break block0;
                                }
                            }
                            this.jj_la1[127] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    this.jj_la1[128] = this.jj_gen;
                    break;
                }
                default: {
                    this.jj_la1[129] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new ExpressionStmt(this.range((Node)expr, this.token()), expr);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SwitchStmt SwitchStatement() throws ParseException {
        NodeList<SwitchEntry> entries = this.emptyNodeList();
        this.jj_consume_token(53);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        Expression selector = this.Expression();
        this.jj_consume_token(97);
        this.jj_consume_token(98);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 16: 
                case 22: {
                    break;
                }
                default: {
                    this.jj_la1[130] = this.jj_gen;
                    break block3;
                }
            }
            SwitchEntry entry = this.SwitchEntry();
            entries = this.add(entries, entry);
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new SwitchStmt(this.range(begin, this.token()), selector, entries);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SwitchExpr SwitchExpression() throws ParseException {
        NodeList<SwitchEntry> entries = this.emptyNodeList();
        this.jj_consume_token(53);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        Expression selector = this.Expression();
        this.jj_consume_token(97);
        this.jj_consume_token(98);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 16: 
                case 22: {
                    break;
                }
                default: {
                    this.jj_la1[131] = this.jj_gen;
                    break block3;
                }
            }
            SwitchEntry entry = this.SwitchEntry();
            entries = this.add(entries, entry);
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new SwitchExpr(this.range(begin, this.token()), selector, entries);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SwitchEntry SwitchEntry() throws ParseException {
        SwitchEntry ret;
        JavaToken begin;
        Expression label = null;
        NodeList<Expression> labels = this.emptyNodeList();
        NodeList<Statement> stmts = this.emptyNodeList();
        Expression expression = null;
        Statement stmt = null;
        block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 16: {
                this.jj_consume_token(16);
                begin = this.token();
                label = this.ConditionalExpression();
                labels = this.add(labels, label);
                while (true) {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 103: {
                            break;
                        }
                        default: {
                            this.jj_la1[132] = this.jj_gen;
                            break block0;
                        }
                    }
                    this.jj_consume_token(103);
                    label = this.ConditionalExpression();
                    labels = this.add(labels, label);
                }
            }
            case 22: {
                this.jj_consume_token(22);
                begin = this.token();
                break;
            }
            default: {
                this.jj_la1[133] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        block7 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 111: {
                this.jj_consume_token(111);
                stmts = this.Statements();
                ret = new SwitchEntry(this.range(begin, this.token()), labels, SwitchEntry.Type.STATEMENT_GROUP, stmts);
                break;
            }
            case 112: {
                this.jj_consume_token(112);
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 13: 
                    case 15: 
                    case 18: 
                    case 24: 
                    case 26: 
                    case 28: 
                    case 31: 
                    case 38: 
                    case 40: 
                    case 42: 
                    case 43: 
                    case 49: 
                    case 51: 
                    case 52: 
                    case 53: 
                    case 55: 
                    case 59: 
                    case 61: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 75: 
                    case 76: 
                    case 81: 
                    case 88: 
                    case 89: 
                    case 91: 
                    case 93: 
                    case 96: 
                    case 105: 
                    case 108: 
                    case 109: 
                    case 119: 
                    case 120: 
                    case 121: 
                    case 122: {
                        expression = this.Expression();
                        this.jj_consume_token(102);
                        TokenRange r = this.range(begin, this.token());
                        stmts.add(new ExpressionStmt(r, expression));
                        ret = new SwitchEntry(r, labels, SwitchEntry.Type.EXPRESSION, stmts);
                        break block7;
                    }
                    case 98: {
                        stmt = this.Block();
                        TokenRange r = this.range(begin, this.token());
                        stmts.add(stmt);
                        ret = new SwitchEntry(r, labels, SwitchEntry.Type.BLOCK, stmts);
                        break block7;
                    }
                    case 56: {
                        stmt = this.ThrowStatement();
                        TokenRange r = this.range(begin, this.token());
                        stmts.add(stmt);
                        ret = new SwitchEntry(r, labels, SwitchEntry.Type.THROWS_STATEMENT, stmts);
                        break block7;
                    }
                }
                this.jj_la1[134] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            default: {
                this.jj_la1[135] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final IfStmt IfStatement() throws ParseException {
        Statement elseStmt = null;
        this.jj_consume_token(34);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        Expression condition = this.Expression();
        this.jj_consume_token(97);
        Statement thenStmt = this.Statement();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 25: {
                this.jj_consume_token(25);
                elseStmt = this.Statement();
                break;
            }
            default: {
                this.jj_la1[136] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new IfStmt(this.range(begin, this.token()), condition, thenStmt, elseStmt);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final WhileStmt WhileStatement() throws ParseException {
        this.jj_consume_token(63);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        Expression condition = this.Expression();
        this.jj_consume_token(97);
        Statement body = this.Statement();
        if ("" != null) {
            return new WhileStmt(this.range(begin, this.token()), condition, body);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final DoStmt DoStatement() throws ParseException {
        this.jj_consume_token(23);
        JavaToken begin = this.token();
        Statement body = this.Statement();
        this.jj_consume_token(63);
        this.jj_consume_token(96);
        Expression condition = this.Expression();
        this.jj_consume_token(97);
        this.jj_consume_token(102);
        if ("" != null) {
            return new DoStmt(this.range(begin, this.token()), body, condition);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Statement ForStatement() throws ParseException {
        VariableDeclarationExpr varExpr = null;
        Expression expr = null;
        NodeList<Expression> init = this.emptyNodeList();
        NodeList<Expression> update = this.emptyNodeList();
        this.jj_consume_token(32);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        if (this.jj_2_47(Integer.MAX_VALUE)) {
            varExpr = this.VariableDeclarationExpression();
            this.jj_consume_token(111);
            expr = this.Expression();
        } else {
            block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 11: 
                case 13: 
                case 15: 
                case 18: 
                case 22: 
                case 24: 
                case 26: 
                case 28: 
                case 29: 
                case 31: 
                case 38: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 45: 
                case 46: 
                case 47: 
                case 49: 
                case 50: 
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 58: 
                case 59: 
                case 61: 
                case 62: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 102: 
                case 105: 
                case 108: 
                case 109: 
                case 119: 
                case 120: 
                case 121: 
                case 122: {
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 11: 
                        case 13: 
                        case 15: 
                        case 18: 
                        case 22: 
                        case 24: 
                        case 26: 
                        case 28: 
                        case 29: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 41: 
                        case 42: 
                        case 43: 
                        case 45: 
                        case 46: 
                        case 47: 
                        case 49: 
                        case 50: 
                        case 51: 
                        case 52: 
                        case 53: 
                        case 54: 
                        case 55: 
                        case 58: 
                        case 59: 
                        case 61: 
                        case 62: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 75: 
                        case 76: 
                        case 81: 
                        case 88: 
                        case 89: 
                        case 91: 
                        case 93: 
                        case 96: 
                        case 105: 
                        case 108: 
                        case 109: 
                        case 119: 
                        case 120: 
                        case 121: 
                        case 122: {
                            init = this.ForInit();
                            break;
                        }
                        default: {
                            this.jj_la1[137] = this.jj_gen;
                        }
                    }
                    this.jj_consume_token(102);
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 13: 
                        case 15: 
                        case 18: 
                        case 24: 
                        case 26: 
                        case 28: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 42: 
                        case 43: 
                        case 49: 
                        case 51: 
                        case 52: 
                        case 53: 
                        case 55: 
                        case 59: 
                        case 61: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 75: 
                        case 76: 
                        case 81: 
                        case 88: 
                        case 89: 
                        case 91: 
                        case 93: 
                        case 96: 
                        case 105: 
                        case 108: 
                        case 109: 
                        case 119: 
                        case 120: 
                        case 121: 
                        case 122: {
                            expr = this.Expression();
                            break;
                        }
                        default: {
                            this.jj_la1[138] = this.jj_gen;
                        }
                    }
                    this.jj_consume_token(102);
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 13: 
                        case 15: 
                        case 18: 
                        case 24: 
                        case 26: 
                        case 28: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 42: 
                        case 43: 
                        case 49: 
                        case 51: 
                        case 52: 
                        case 53: 
                        case 55: 
                        case 59: 
                        case 61: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 75: 
                        case 76: 
                        case 81: 
                        case 88: 
                        case 89: 
                        case 91: 
                        case 93: 
                        case 96: 
                        case 105: 
                        case 108: 
                        case 109: 
                        case 119: 
                        case 120: 
                        case 121: 
                        case 122: {
                            update = this.ForUpdate();
                            break block0;
                        }
                    }
                    this.jj_la1[139] = this.jj_gen;
                    break;
                }
                default: {
                    this.jj_la1[140] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        this.jj_consume_token(97);
        Statement body = this.Statement();
        if (varExpr != null && "" != null) {
            return new ForEachStmt(this.range(begin, this.token()), varExpr, expr, body);
        }
        if ("" != null) {
            return new ForStmt(this.range(begin, this.token()), init, expr, update, body);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> ForInit() throws ParseException {
        NodeList<Expression> ret;
        if (this.jj_2_48(Integer.MAX_VALUE)) {
            VariableDeclarationExpr expr = this.VariableDeclarationExpression();
            ret = new NodeList<VariableDeclarationExpr>();
            ret.add(expr);
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 53: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: 
                case 108: 
                case 109: 
                case 119: 
                case 120: 
                case 121: 
                case 122: {
                    ret = this.ExpressionList();
                    break;
                }
                default: {
                    this.jj_la1[141] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> ExpressionList() throws ParseException {
        NodeList<Expression> ret = new NodeList<Expression>();
        Expression expr = this.Expression();
        ret.add(expr);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[142] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            expr = this.Expression();
            ret.add(expr);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> ForUpdate() throws ParseException {
        NodeList<Expression> ret = this.ExpressionList();
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BreakStmt BreakStatement() throws ParseException {
        SimpleName label = null;
        this.jj_consume_token(14);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 26: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                label = this.SimpleName();
                break;
            }
            default: {
                this.jj_la1[143] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new BreakStmt(this.range(begin, this.token()), label);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final YieldStmt YieldStatement() throws ParseException {
        this.jj_consume_token(64);
        JavaToken begin = this.token();
        Expression value = this.Expression();
        this.jj_consume_token(102);
        if ("" != null) {
            return new YieldStmt(this.range(begin, this.token()), value);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ContinueStmt ContinueStatement() throws ParseException {
        SimpleName label = null;
        this.jj_consume_token(21);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 26: 
            case 51: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 93: {
                label = this.SimpleName();
                break;
            }
            default: {
                this.jj_la1[144] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new ContinueStmt(this.range(begin, this.token()), label);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ReturnStmt ReturnStatement() throws ParseException {
        Expression expr = null;
        this.jj_consume_token(48);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                expr = this.Expression();
                break;
            }
            default: {
                this.jj_la1[145] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        if ("" != null) {
            return new ReturnStmt(this.range(begin, this.token()), expr);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ThrowStmt ThrowStatement() throws ParseException {
        this.jj_consume_token(56);
        JavaToken begin = this.token();
        Expression expr = this.Expression();
        this.jj_consume_token(102);
        if ("" != null) {
            return new ThrowStmt(this.range(begin, this.token()), expr);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SynchronizedStmt SynchronizedStatement() throws ParseException {
        this.jj_consume_token(54);
        JavaToken begin = this.token();
        this.jj_consume_token(96);
        Expression expr = this.Expression();
        this.jj_consume_token(97);
        BlockStmt body = this.Block();
        if ("" != null) {
            return new SynchronizedStmt(this.range(begin, this.token()), expr, body);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TryStmt TryStatement() throws ParseException {
        NodeList<Expression> resources = this.emptyNodeList();
        BlockStmt finallyBlock = null;
        NodeList<CatchClause> catchs = this.emptyNodeList();
        NodeList<ReferenceType> exceptionTypes = this.emptyNodeList();
        this.jj_consume_token(60);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 96: {
                resources = this.ResourceSpecification();
                break;
            }
            default: {
                this.jj_la1[146] = this.jj_gen;
            }
        }
        BlockStmt tryBlock = this.Block();
        block12: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 17: {
                    break;
                }
                default: {
                    this.jj_la1[147] = this.jj_gen;
                    break block12;
                }
            }
            this.jj_consume_token(17);
            JavaToken catchBegin = this.token();
            this.jj_consume_token(96);
            ModifierHolder exceptModifier = this.Modifiers();
            JavaToken typesBegin = exceptModifier.begin;
            ReferenceType exceptionType = this.ReferenceType(this.emptyNodeList());
            exceptionTypes.add(exceptionType);
            typesBegin = this.orIfInvalid(typesBegin, this.token());
            block13: while (true) {
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 126: {
                        break;
                    }
                    default: {
                        this.jj_la1[148] = this.jj_gen;
                        break block13;
                    }
                }
                this.jj_consume_token(126);
                exceptionType = this.AnnotatedReferenceType();
                exceptionTypes.add(exceptionType);
            }
            Pair<SimpleName, List<ArrayType.ArrayBracketPair>> exceptId = this.VariableDeclaratorId();
            JavaToken paramEnd = this.token();
            this.jj_consume_token(97);
            BlockStmt catchBlock = this.Block();
            Type type = exceptionTypes.size() > 1 ? new UnionType(this.range((Node)exceptionTypes.get(0), (Node)exceptionTypes.get(exceptionTypes.size() - 1)), exceptionTypes) : (Type)exceptionTypes.get(0);
            Parameter catchType = new Parameter(this.range(typesBegin, paramEnd), exceptModifier.modifiers, exceptModifier.annotations, type, false, this.emptyNodeList(), (SimpleName)exceptId.a);
            catchs = this.add(catchs, new CatchClause(this.range(catchBegin, this.token()), catchType, catchBlock));
            exceptionTypes = this.emptyNodeList();
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 30: {
                this.jj_consume_token(30);
                finallyBlock = this.Block();
                break;
            }
            default: {
                this.jj_la1[149] = this.jj_gen;
            }
        }
        if ("" != null) {
            return new TryStmt(this.range(begin, this.token()), resources, tryBlock, catchs, finallyBlock);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> ResourceSpecification() throws ParseException {
        this.jj_consume_token(96);
        NodeList<Expression> variables = this.Resources();
        if (this.jj_2_49(2)) {
            this.jj_consume_token(102);
        }
        this.jj_consume_token(97);
        if ("" != null) {
            return variables;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<Expression> Resources() throws ParseException {
        NodeList<Expression> expressions = new NodeList<Expression>();
        Expression expr = this.Resource();
        expressions.add(expr);
        while (this.jj_2_50(2)) {
            this.jj_consume_token(102);
            expr = this.Resource();
            expressions.add(expr);
        }
        if ("" != null) {
            return expressions;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression Resource() throws ParseException {
        Expression expr;
        if (this.jj_2_51(Integer.MAX_VALUE)) {
            expr = this.VariableDeclarationExpression();
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: {
                    expr = this.PrimaryExpression();
                    break;
                }
                default: {
                    this.jj_la1[150] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return expr;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final void RUNSIGNEDSHIFT() throws ParseException {
        if (this.getToken((int)1).kind != 145 || this.getToken((int)1).realKind != 143) {
            this.jj_consume_token(-1);
            throw new ParseException();
        }
        this.jj_consume_token(145);
        this.jj_consume_token(145);
        this.jj_consume_token(145);
    }

    public final void RSIGNEDSHIFT() throws ParseException {
        if (this.getToken((int)1).kind != 145 || this.getToken((int)1).realKind != 144) {
            this.jj_consume_token(-1);
            throw new ParseException();
        }
        this.jj_consume_token(145);
        this.jj_consume_token(145);
    }

    public final NodeList<AnnotationExpr> Annotations() throws ParseException {
        NodeList<AnnotationExpr> annotations = new NodeList<AnnotationExpr>();
        while (this.jj_2_52(Integer.MAX_VALUE)) {
            AnnotationExpr annotation = this.Annotation();
            annotations = this.add(annotations, annotation);
        }
        if ("" != null) {
            return annotations;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AnnotationExpr Annotation() throws ParseException {
        AnnotationExpr ret;
        NodeList<MemberValuePair> pairs = this.emptyNodeList();
        this.jj_consume_token(105);
        JavaToken begin = this.token();
        Name name = this.Name();
        if (this.jj_2_53(Integer.MAX_VALUE)) {
            this.jj_consume_token(96);
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 26: 
                case 51: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 93: {
                    pairs = this.MemberValuePairs();
                    break;
                }
                default: {
                    this.jj_la1[151] = this.jj_gen;
                }
            }
            this.jj_consume_token(97);
            ret = new NormalAnnotationExpr(this.range(begin, this.token()), name, pairs);
        } else if (this.jj_2_54(Integer.MAX_VALUE)) {
            this.jj_consume_token(96);
            Expression memberVal = this.MemberValue();
            this.jj_consume_token(97);
            ret = new SingleMemberAnnotationExpr(this.range(begin, this.token()), name, memberVal);
        } else {
            ret = new MarkerAnnotationExpr(this.range(begin, this.token()), name);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<MemberValuePair> MemberValuePairs() throws ParseException {
        NodeList<MemberValuePair> ret = new NodeList<MemberValuePair>();
        MemberValuePair pair = this.MemberValuePair();
        ret.add(pair);
        block3: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 103: {
                    break;
                }
                default: {
                    this.jj_la1[152] = this.jj_gen;
                    break block3;
                }
            }
            this.jj_consume_token(103);
            pair = this.MemberValuePair();
            ret.add(pair);
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final MemberValuePair MemberValuePair() throws ParseException {
        SimpleName name = this.SimpleName();
        JavaToken begin = this.token();
        this.jj_consume_token(106);
        Expression value = this.MemberValue();
        if ("" != null) {
            return new MemberValuePair(this.range(begin, this.token()), name, value);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression MemberValue() throws ParseException {
        Expression ret;
        if (this.jj_2_55(Integer.MAX_VALUE)) {
            ret = this.Annotation();
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 98: {
                    ret = this.MemberValueArrayInitializer();
                    break;
                }
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 53: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: 
                case 108: 
                case 109: 
                case 119: 
                case 120: 
                case 121: 
                case 122: {
                    ret = this.ConditionalExpression();
                    break;
                }
                default: {
                    this.jj_la1[153] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression MemberValueArrayInitializer() throws ParseException {
        NodeList<Expression> ret = this.emptyNodeList();
        this.jj_consume_token(98);
        JavaToken begin = this.token();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 13: 
            case 15: 
            case 18: 
            case 24: 
            case 26: 
            case 28: 
            case 31: 
            case 38: 
            case 40: 
            case 42: 
            case 43: 
            case 49: 
            case 51: 
            case 52: 
            case 53: 
            case 55: 
            case 59: 
            case 61: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 81: 
            case 88: 
            case 89: 
            case 91: 
            case 93: 
            case 96: 
            case 98: 
            case 105: 
            case 108: 
            case 109: 
            case 119: 
            case 120: 
            case 121: 
            case 122: {
                Expression member = this.MemberValue();
                ret.add(member);
                while (this.jj_2_56(2)) {
                    this.jj_consume_token(103);
                    member = this.MemberValue();
                    ret.add(member);
                }
                break;
            }
            default: {
                this.jj_la1[154] = this.jj_gen;
            }
        }
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 103: {
                this.jj_consume_token(103);
                break;
            }
            default: {
                this.jj_la1[155] = this.jj_gen;
            }
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new ArrayInitializerExpr(this.range(begin, this.token()), ret);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AnnotationDeclaration AnnotationTypeDeclaration(ModifierHolder modifier) throws ParseException {
        NodeList<BodyDeclaration<?>> members = this.emptyNodeList();
        JavaToken begin = modifier.begin;
        this.jj_consume_token(105);
        begin = this.orIfInvalid(begin, this.token());
        this.jj_consume_token(39);
        SimpleName name = this.SimpleName();
        members = this.AnnotationTypeBody();
        if ("" != null) {
            return new AnnotationDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, name, members);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final NodeList<BodyDeclaration<?>> AnnotationTypeBody() throws ParseException {
        NodeList<BodyDeclaration<?>> ret;
        block9: {
            ret = this.emptyNodeList();
            this.jj_consume_token(98);
            block7: while (true) {
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 11: 
                    case 13: 
                    case 15: 
                    case 18: 
                    case 19: 
                    case 22: 
                    case 24: 
                    case 26: 
                    case 29: 
                    case 31: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 49: 
                    case 50: 
                    case 51: 
                    case 54: 
                    case 58: 
                    case 62: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: 
                    case 102: 
                    case 105: {
                        break;
                    }
                    default: {
                        this.jj_la1[156] = this.jj_gen;
                        break block9;
                    }
                }
                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                    case 11: 
                    case 13: 
                    case 15: 
                    case 18: 
                    case 19: 
                    case 22: 
                    case 24: 
                    case 26: 
                    case 29: 
                    case 31: 
                    case 38: 
                    case 39: 
                    case 40: 
                    case 41: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 49: 
                    case 50: 
                    case 51: 
                    case 54: 
                    case 58: 
                    case 62: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                    case 68: 
                    case 69: 
                    case 70: 
                    case 71: 
                    case 72: 
                    case 73: 
                    case 74: 
                    case 93: 
                    case 105: {
                        BodyDeclaration<?> member = this.AnnotationBodyDeclaration();
                        ret = this.addWhenNotNull(ret, member);
                        continue block7;
                    }
                    case 102: {
                        this.jj_consume_token(102);
                        continue block7;
                    }
                }
                break;
            }
            this.jj_la1[157] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BodyDeclaration<?> AnnotationBodyDeclaration() throws ParseException {
        BodyDeclaration ret;
        ModifierHolder modifier = this.Modifiers();
        if (this.jj_2_57(Integer.MAX_VALUE)) {
            ret = this.AnnotationTypeMemberDeclaration(modifier);
        } else {
            block0 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 19: 
                case 39: {
                    ret = this.ClassOrInterfaceDeclaration(modifier);
                    break;
                }
                default: {
                    this.jj_la1[158] = this.jj_gen;
                    if (this.jj_2_58(Integer.MAX_VALUE)) {
                        ret = this.EnumDeclaration(modifier);
                        break;
                    }
                    switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 105: {
                            ret = this.AnnotationTypeDeclaration(modifier);
                            break block0;
                        }
                        case 13: 
                        case 15: 
                        case 18: 
                        case 24: 
                        case 26: 
                        case 31: 
                        case 38: 
                        case 40: 
                        case 49: 
                        case 51: 
                        case 64: 
                        case 65: 
                        case 66: 
                        case 67: 
                        case 68: 
                        case 69: 
                        case 70: 
                        case 71: 
                        case 72: 
                        case 73: 
                        case 74: 
                        case 93: {
                            ret = this.FieldDeclaration(modifier);
                            break block0;
                        }
                    }
                    this.jj_la1[159] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AnnotationMemberDeclaration AnnotationTypeMemberDeclaration(ModifierHolder modifier) throws ParseException {
        Expression defaultVal = null;
        Type type = this.Type(this.emptyNodeList());
        SimpleName name = this.SimpleName();
        this.jj_consume_token(96);
        this.jj_consume_token(97);
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 22: {
                defaultVal = this.DefaultValue();
                break;
            }
            default: {
                this.jj_la1[160] = this.jj_gen;
            }
        }
        this.jj_consume_token(102);
        JavaToken begin = this.orIfInvalid(modifier.begin, type);
        if ("" != null) {
            return new AnnotationMemberDeclaration(this.range(begin, this.token()), modifier.modifiers, modifier.annotations, type, name, defaultVal);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression DefaultValue() throws ParseException {
        this.jj_consume_token(22);
        Expression ret = this.MemberValue();
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ModuleDirective ModuleDirective() throws ParseException {
        ModuleDirective directive;
        NodeList<Name> names = this.emptyNodeList();
        if (this.jj_2_59(Integer.MAX_VALUE)) {
            this.jj_consume_token(65);
            JavaToken begin = this.token();
            this.jj_consume_token(74);
            JavaToken transitiveExceptionalToken = this.token();
            this.setTokenKind(93);
            this.jj_consume_token(102);
            directive = new ModuleRequiresDirective(this.range(begin, this.token()), new NodeList<Modifier>(), new Name(this.range(transitiveExceptionalToken, transitiveExceptionalToken), null, transitiveExceptionalToken.getText()));
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 65: {
                    this.jj_consume_token(65);
                    JavaToken begin = this.token();
                    ModifierHolder modifiers = this.Modifiers();
                    Name name = this.Name();
                    this.jj_consume_token(102);
                    directive = new ModuleRequiresDirective(this.range(begin, this.token()), modifiers.modifiers, name);
                    break;
                }
                case 72: {
                    this.jj_consume_token(72);
                    JavaToken begin = this.token();
                    Name name = this.Name();
                    block7 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 66: {
                            this.jj_consume_token(66);
                            Name tmpName = this.Name();
                            names.add(tmpName);
                            while (true) {
                                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                    case 103: {
                                        break;
                                    }
                                    default: {
                                        this.jj_la1[161] = this.jj_gen;
                                        break block7;
                                    }
                                }
                                this.jj_consume_token(103);
                                tmpName = this.Name();
                                names.add(tmpName);
                            }
                        }
                        default: {
                            this.jj_la1[162] = this.jj_gen;
                        }
                    }
                    this.jj_consume_token(102);
                    directive = new ModuleExportsDirective(this.range(begin, this.token()), name, names);
                    break;
                }
                case 69: {
                    this.jj_consume_token(69);
                    JavaToken begin = this.token();
                    Name name = this.Name();
                    block13 : switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                        case 66: {
                            this.jj_consume_token(66);
                            Name tmpName = this.Name();
                            names.add(tmpName);
                            while (true) {
                                switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                                    case 103: {
                                        break;
                                    }
                                    default: {
                                        this.jj_la1[163] = this.jj_gen;
                                        break block13;
                                    }
                                }
                                this.jj_consume_token(103);
                                tmpName = this.Name();
                                names.add(tmpName);
                            }
                        }
                        default: {
                            this.jj_la1[164] = this.jj_gen;
                        }
                    }
                    this.jj_consume_token(102);
                    directive = new ModuleOpensDirective(this.range(begin, this.token()), name, names);
                    break;
                }
                case 70: {
                    this.jj_consume_token(70);
                    JavaToken begin = this.token();
                    Name name = this.Name();
                    this.jj_consume_token(102);
                    directive = new ModuleUsesDirective(this.range(begin, this.token()), name);
                    break;
                }
                case 73: {
                    this.jj_consume_token(73);
                    JavaToken begin = this.token();
                    Name name = this.Name();
                    this.jj_consume_token(67);
                    Name tmpName = this.Name();
                    names.add(tmpName);
                    block24: while (true) {
                        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                            case 103: {
                                break;
                            }
                            default: {
                                this.jj_la1[165] = this.jj_gen;
                                break block24;
                            }
                        }
                        this.jj_consume_token(103);
                        tmpName = this.Name();
                        names.add(tmpName);
                    }
                    this.jj_consume_token(102);
                    directive = new ModuleProvidesDirective(this.range(begin, this.token()), name, names);
                    break;
                }
                default: {
                    this.jj_la1[166] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        if ("" != null) {
            return directive;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ModuleDeclaration ModuleDeclaration(ModifierHolder modifier) throws ParseException {
        NodeList<ModuleDirective> directives = new NodeList();
        boolean open = false;
        JavaToken begin = modifier.begin;
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 68: {
                this.jj_consume_token(68);
                open = true;
                begin = this.orIfInvalid(begin, this.token());
                break;
            }
            default: {
                this.jj_la1[167] = this.jj_gen;
            }
        }
        this.jj_consume_token(71);
        begin = this.orIfInvalid(begin, this.token());
        Name name = this.Name();
        this.jj_consume_token(98);
        block6: while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 65: 
                case 69: 
                case 70: 
                case 72: 
                case 73: {
                    break;
                }
                default: {
                    this.jj_la1[168] = this.jj_gen;
                    break block6;
                }
            }
            ModuleDirective directive = this.ModuleDirective();
            directives = this.add(directives, directive);
        }
        this.jj_consume_token(99);
        if ("" != null) {
            return new ModuleDeclaration(this.range(begin, this.token()), modifier.annotations, name, open, directives);
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BlockStmt BlockParseStart() throws ParseException {
        BlockStmt ret = this.Block();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Statement BlockStatementParseStart() throws ParseException {
        Statement ret;
        if (this.jj_2_60(3)) {
            ret = this.BlockStatement();
        } else {
            switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
                case 13: 
                case 15: 
                case 18: 
                case 24: 
                case 26: 
                case 28: 
                case 31: 
                case 38: 
                case 40: 
                case 42: 
                case 43: 
                case 49: 
                case 51: 
                case 52: 
                case 55: 
                case 59: 
                case 61: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 81: 
                case 88: 
                case 89: 
                case 91: 
                case 93: 
                case 96: 
                case 105: 
                case 107: {
                    ret = this.ExplicitConstructorInvocation();
                    break;
                }
                default: {
                    this.jj_la1[169] = this.jj_gen;
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ImportDeclaration ImportDeclarationParseStart() throws ParseException {
        ImportDeclaration ret = this.ImportDeclaration();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Expression ExpressionParseStart() throws ParseException {
        Expression ret = this.Expression();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final AnnotationExpr AnnotationParseStart() throws ParseException {
        AnnotationExpr ret = this.Annotation();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BodyDeclaration<?> AnnotationBodyDeclarationParseStart() throws ParseException {
        BodyDeclaration<?> ret = this.AnnotationBodyDeclaration();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final BodyDeclaration<?> ClassOrInterfaceBodyDeclarationParseStart() throws ParseException {
        BodyDeclaration<?> ret = this.ClassOrInterfaceBodyDeclaration();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ClassOrInterfaceType ClassOrInterfaceTypeParseStart() throws ParseException {
        ClassOrInterfaceType ret = this.AnnotatedClassOrInterfaceType();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Type ResultTypeParseStart() throws ParseException {
        NodeList<AnnotationExpr> annotations = this.Annotations();
        Type ret = this.ResultType(annotations);
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final VariableDeclarationExpr VariableDeclarationExpressionParseStart() throws ParseException {
        VariableDeclarationExpr ret = this.VariableDeclarationExpression();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ExplicitConstructorInvocationStmt ExplicitConstructorInvocationParseStart() throws ParseException {
        ExplicitConstructorInvocationStmt ret = this.ExplicitConstructorInvocation();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Name NameParseStart() throws ParseException {
        Name ret = this.Name();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final SimpleName SimpleNameParseStart() throws ParseException {
        SimpleName ret = this.SimpleName();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final Parameter ParameterParseStart() throws ParseException {
        Parameter ret = this.Parameter();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final PackageDeclaration PackageDeclarationParseStart() throws ParseException {
        PackageDeclaration ret = this.PackageDeclaration();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TypeDeclaration<?> TypeDeclarationParseStart() throws ParseException {
        TypeDeclaration ret;
        ModifierHolder modifier = this.Modifiers();
        switch (this.jj_ntk == -1 ? this.jj_ntk_f() : this.jj_ntk) {
            case 19: 
            case 39: {
                ret = this.ClassOrInterfaceDeclaration(modifier);
                break;
            }
            case 26: {
                ret = this.EnumDeclaration(modifier);
                break;
            }
            case 105: {
                ret = this.AnnotationTypeDeclaration(modifier);
                break;
            }
            default: {
                this.jj_la1[170] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ModuleDeclaration ModuleDeclarationParseStart() throws ParseException {
        ModifierHolder modifiers = this.Modifiers();
        ModuleDeclaration ret = this.ModuleDeclaration(modifiers);
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final ModuleDirective ModuleDirectiveParseStart() throws ParseException {
        ModuleDirective ret = this.ModuleDirective();
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final TypeParameter TypeParameterParseStart() throws ParseException {
        NodeList<AnnotationExpr> annotations = this.Annotations();
        TypeParameter ret = this.TypeParameter(annotations);
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
        }
        throw new IllegalStateException("Missing return statement in function");
    }

    public final MethodDeclaration MethodDeclarationParseStart() throws ParseException {
        ModifierHolder modifier = this.Modifiers();
        MethodDeclaration ret = this.MethodDeclaration(modifier);
        this.jj_consume_token(0);
        if ("" != null) {
            return ret;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_2(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_2();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(1, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_3(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_3();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(2, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_4(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_4();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(3, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_5(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_5();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(4, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_6(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_6();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(5, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_7(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_7();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(6, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_8(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_8();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(7, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_9(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_9();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(8, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_10(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_10();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(9, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_11(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_11();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(10, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_12(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_12();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(11, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_13(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_13();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(12, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_14(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_14();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(13, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_15(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_15();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(14, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_16(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_16();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(15, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_17(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_17();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(16, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_18(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_18();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(17, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_19(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_19();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(18, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_20(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_20();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(19, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_21(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_21();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(20, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_22(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_22();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(21, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_23(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_23();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(22, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_24(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_24();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(23, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_25(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_25();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(24, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_26(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_26();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(25, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_27(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_27();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(26, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_28(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_28();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(27, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_29(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_29();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(28, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_30(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_30();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(29, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_31(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_31();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(30, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_32(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_32();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(31, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_33(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_33();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(32, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_34(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_34();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(33, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_35(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_35();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(34, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_36(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_36();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(35, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_37(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_37();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(36, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_38(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_38();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(37, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_39(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_39();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(38, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_40(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_40();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(39, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_41(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_41();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(40, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_42(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_42();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(41, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_43(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_43();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(42, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_44(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_44();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(43, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_45(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_45();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(44, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_46(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_46();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(45, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_47(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_47();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(46, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_48(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_48();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(47, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_49(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_49();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(48, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_50(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_50();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(49, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_51(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_51();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(50, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_52(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_52();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(51, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_53(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_53();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(52, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_54(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_54();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(53, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_55(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_55();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(54, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_56(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_56();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(55, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_57(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_57();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(56, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_58(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_58();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(57, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_59(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_59();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(58, xla);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean jj_2_60(int xla) {
        this.jj_la = xla;
        this.jj_scanpos = this.token;
        this.jj_lastpos = this.token;
        try {
            boolean bl = !this.jj_3_60();
            return bl;
        } catch (LookaheadSuccess ls) {
            boolean bl = true;
            return bl;
        } finally {
            this.jj_save(59, xla);
        }
    }

    private boolean jj_3R_400() {
        return this.jj_scan_token(116);
    }

    private boolean jj_3R_119() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_179()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_180()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_181()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_86() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_135()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_136()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_137()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_179() {
        return this.jj_3R_120();
    }

    private boolean jj_3R_180() {
        return this.jj_3R_216();
    }

    private boolean jj_3R_181() {
        return this.jj_3R_153();
    }

    private boolean jj_3R_94() {
        if (this.jj_3R_154()) {
            return true;
        }
        return this.jj_3R_91();
    }

    private boolean jj_3R_135() {
        if (this.jj_3R_113()) {
            return true;
        }
        return this.jj_3R_200();
    }

    private boolean jj_3_55() {
        return this.jj_scan_token(105);
    }

    private boolean jj_3R_136() {
        if (this.jj_3R_114()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_137() {
        return this.jj_3R_201();
    }

    private boolean jj_3_44() {
        if (this.jj_3R_113()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(19)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(39)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3_45() {
        return this.jj_3R_114();
    }

    private boolean jj_3R_216() {
        if (this.jj_scan_token(98)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_375()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(103)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_289() {
        Token xsp;
        if (this.jj_3R_105()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_324());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_114() {
        Token xsp;
        if (this.jj_3R_113()) {
            return true;
        }
        if (this.jj_3R_77()) {
            return true;
        }
        if (this.jj_3R_117()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_174());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_375() {
        Token xsp;
        if (this.jj_3R_119()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_56());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_324() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_105();
    }

    private boolean jj_3_56() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_119();
    }

    private boolean jj_3R_174() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_117();
    }

    private boolean jj_3R_386() {
        if (this.jj_3R_394()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_398()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_398() {
        if (this.jj_scan_token(37)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_404()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_405()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_404() {
        return this.jj_3R_94();
    }

    private boolean jj_3R_294() {
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_405() {
        return this.jj_3R_154();
    }

    private boolean jj_3R_104() {
        if (this.jj_3R_113()) {
            return true;
        }
        if (this.jj_3R_77()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_169()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_105();
    }

    private boolean jj_3_25() {
        return this.jj_3R_94();
    }

    private boolean jj_3R_364() {
        if (this.jj_scan_token(105)) {
            return true;
        }
        if (this.jj_scan_token(39)) {
            return true;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        return this.jj_3R_414();
    }

    private boolean jj_3R_169() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_scan_token(141);
    }

    private boolean jj_3R_337() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_361()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_362()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_361() {
        return this.jj_3R_93();
    }

    private boolean jj_3R_362() {
        return this.jj_3R_127();
    }

    private boolean jj_3R_394() {
        Token xsp;
        if (this.jj_3R_397()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_402());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_1() {
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_414() {
        Token xsp;
        if (this.jj_scan_token(98)) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_426());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_81() {
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_3R_77()) {
            return true;
        }
        return this.jj_3R_130();
    }

    private boolean jj_3R_402() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_406()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_407()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_408()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_409()) {
                        return true;
                    }
                }
            }
        }
        return this.jj_3R_397();
    }

    private boolean jj_3R_426() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_442()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(102)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_442() {
        return this.jj_3R_450();
    }

    private boolean jj_3R_295() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_46()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_328()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_329()) {
                    return true;
                }
            }
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_406() {
        return this.jj_scan_token(107);
    }

    private boolean jj_3_2() {
        return this.jj_3R_59();
    }

    private boolean jj_3R_407() {
        return this.jj_scan_token(145);
    }

    private boolean jj_3_46() {
        return this.jj_3R_115();
    }

    private boolean jj_3R_408() {
        return this.jj_scan_token(115);
    }

    private boolean jj_3R_328() {
        return this.jj_3R_266();
    }

    private boolean jj_3R_409() {
        return this.jj_scan_token(114);
    }

    private boolean jj_3R_329() {
        if (this.jj_3R_215()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_355()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_355() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_372()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_373()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_374()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_372() {
        return this.jj_scan_token(119);
    }

    private boolean jj_3R_130() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_187()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(55);
    }

    private boolean jj_3R_373() {
        return this.jj_scan_token(120);
    }

    private boolean jj_3R_187() {
        if (this.jj_3R_82()) {
            return true;
        }
        return this.jj_scan_token(104);
    }

    private boolean jj_3R_374() {
        if (this.jj_3R_92()) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3R_397() {
        Token xsp;
        if (this.jj_3R_401()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_26());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_12() {
        return this.jj_3R_82();
    }

    private boolean jj_3_26() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_95()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_27()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_28()) {
                    return true;
                }
            }
        }
        return this.jj_3R_401();
    }

    private boolean jj_3R_59() {
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_scan_token(44)) {
            return true;
        }
        if (this.jj_3R_82()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_450() {
        if (this.jj_3R_113()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_453()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_454()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_455()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_456()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_457()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_95() {
        return this.jj_scan_token(129);
    }

    private boolean jj_3_27() {
        return this.jj_3R_96();
    }

    private boolean jj_3R_453() {
        return this.jj_3R_458();
    }

    private boolean jj_3_28() {
        return this.jj_3R_97();
    }

    private boolean jj_3R_454() {
        return this.jj_3R_200();
    }

    private boolean jj_3R_455() {
        return this.jj_3R_363();
    }

    private boolean jj_3R_456() {
        return this.jj_3R_364();
    }

    private boolean jj_3_57() {
        if (this.jj_3R_77()) {
            return true;
        }
        if (this.jj_3R_76()) {
            return true;
        }
        return this.jj_scan_token(96);
    }

    private boolean jj_3R_457() {
        return this.jj_3R_366();
    }

    private boolean jj_3_58() {
        return this.jj_scan_token(26);
    }

    private boolean jj_3R_296() {
        Token xsp;
        if (this.jj_scan_token(53)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        if (this.jj_scan_token(98)) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_432());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_401() {
        Token xsp;
        if (this.jj_3R_403()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_410());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_365() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_381()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        if (this.jj_3R_415()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_416()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(98)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_417()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_185()) {
            return true;
        }
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_381() {
        return this.jj_3R_122();
    }

    private boolean jj_3R_410() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_423()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_424()) {
                return true;
            }
        }
        return this.jj_3R_403();
    }

    private boolean jj_3R_432() {
        return this.jj_3R_360();
    }

    private boolean jj_3R_416() {
        Token xsp;
        if (this.jj_scan_token(57)) {
            return true;
        }
        if (this.jj_3R_154()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_428());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_423() {
        return this.jj_scan_token(121);
    }

    private boolean jj_3R_424() {
        return this.jj_scan_token(122);
    }

    private boolean jj_3R_428() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_154();
    }

    private boolean jj_3R_417() {
        return this.jj_3R_83();
    }

    private boolean jj_3R_458() {
        if (this.jj_3R_77()) {
            return true;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_460()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3_13() {
        return this.jj_3R_83();
    }

    private boolean jj_3R_460() {
        return this.jj_3R_461();
    }

    private boolean jj_3R_403() {
        Token xsp;
        if (this.jj_3R_175()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_422());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_422() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_436()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_437()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_438()) {
                    return true;
                }
            }
        }
        return this.jj_3R_175();
    }

    private boolean jj_3R_436() {
        return this.jj_scan_token(123);
    }

    private boolean jj_3R_437() {
        return this.jj_scan_token(124);
    }

    private boolean jj_3R_313() {
        Token xsp;
        if (this.jj_scan_token(53)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        if (this.jj_scan_token(98)) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_336());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_438() {
        return this.jj_scan_token(128);
    }

    private boolean jj_3R_83() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_131()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_132()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_336() {
        return this.jj_3R_360();
    }

    private boolean jj_3R_113() {
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_3());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_131() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_188()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(55)) {
            return true;
        }
        if (this.jj_3R_189()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_461() {
        if (this.jj_scan_token(22)) {
            return true;
        }
        return this.jj_3R_119();
    }

    private boolean jj_3R_188() {
        return this.jj_3R_90();
    }

    private boolean jj_3_3() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_60()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_61()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_62()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_63()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_64()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_65()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_66()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_67()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_68()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3R_69()) {
                                                this.jj_scanpos = xsp;
                                                if (this.jj_3R_70()) {
                                                    this.jj_scanpos = xsp;
                                                    if (this.jj_3R_71()) {
                                                        this.jj_scanpos = xsp;
                                                        if (this.jj_3R_72()) {
                                                            this.jj_scanpos = xsp;
                                                            if (this.jj_3R_73()) {
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_132() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_190()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_191()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(52)) {
            return true;
        }
        if (this.jj_3R_189()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3_15() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_85()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(55)) {
            return true;
        }
        return this.jj_scan_token(96);
    }

    private boolean jj_3R_60() {
        return this.jj_scan_token(47);
    }

    private boolean jj_3R_190() {
        if (this.jj_3R_84()) {
            return true;
        }
        return this.jj_scan_token(104);
    }

    private boolean jj_3R_85() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_61() {
        return this.jj_scan_token(50);
    }

    private boolean jj_3R_175() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_211()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_212()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_213()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_214()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_62() {
        return this.jj_scan_token(46);
    }

    private boolean jj_3R_191() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_63() {
        return this.jj_scan_token(45);
    }

    private boolean jj_3R_64() {
        return this.jj_scan_token(29);
    }

    private boolean jj_3R_211() {
        return this.jj_3R_115();
    }

    private boolean jj_3R_65() {
        return this.jj_scan_token(11);
    }

    private boolean jj_3R_212() {
        return this.jj_3R_266();
    }

    private boolean jj_3_14() {
        if (this.jj_3R_84()) {
            return true;
        }
        return this.jj_scan_token(104);
    }

    private boolean jj_3R_66() {
        return this.jj_scan_token(54);
    }

    private boolean jj_3R_213() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_267()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_268()) {
                return true;
            }
        }
        return this.jj_3R_175();
    }

    private boolean jj_3R_67() {
        return this.jj_scan_token(41);
    }

    private boolean jj_3R_68() {
        return this.jj_scan_token(58);
    }

    private boolean jj_3R_267() {
        return this.jj_scan_token(121);
    }

    private boolean jj_3R_69() {
        return this.jj_scan_token(62);
    }

    private boolean jj_3R_268() {
        return this.jj_scan_token(122);
    }

    private boolean jj_3R_70() {
        return this.jj_scan_token(51);
    }

    private boolean jj_3R_214() {
        return this.jj_3R_205();
    }

    private boolean jj_3R_360() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_377()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_378()) {
                return true;
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_379()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_380()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_71() {
        return this.jj_scan_token(74);
    }

    private boolean jj_3R_72() {
        return this.jj_scan_token(22);
    }

    private boolean jj_3R_377() {
        Token xsp;
        if (this.jj_scan_token(16)) {
            return true;
        }
        if (this.jj_3R_153()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_387());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_73() {
        return this.jj_3R_120();
    }

    private boolean jj_3R_378() {
        return this.jj_scan_token(22);
    }

    private boolean jj_3R_387() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_153();
    }

    private boolean jj_3R_115() {
        if (this.jj_scan_token(119)) {
            return true;
        }
        return this.jj_3R_175();
    }

    private boolean jj_3R_379() {
        if (this.jj_scan_token(111)) {
            return true;
        }
        return this.jj_3R_185();
    }

    private boolean jj_3_59() {
        if (this.jj_scan_token(65)) {
            return true;
        }
        if (this.jj_scan_token(74)) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_380() {
        if (this.jj_scan_token(112)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_388()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_389()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_390()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_185() {
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_16());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_16() {
        return this.jj_3R_86();
    }

    private boolean jj_3R_388() {
        if (this.jj_3R_93()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_266() {
        if (this.jj_scan_token(120)) {
            return true;
        }
        return this.jj_3R_175();
    }

    private boolean jj_3R_389() {
        return this.jj_3R_127();
    }

    private boolean jj_3R_390() {
        return this.jj_3R_304();
    }

    private boolean jj_3R_79() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_126()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_127();
    }

    private boolean jj_3R_126() {
        return this.jj_scan_token(50);
    }

    private boolean jj_3R_200() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_233()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_234()) {
                return true;
            }
        }
        if (this.jj_3R_91()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_269()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_270()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_271()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_109();
    }

    private boolean jj_3R_233() {
        return this.jj_scan_token(19);
    }

    private boolean jj_3R_205() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_256()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_257()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_258()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_259()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_234() {
        return this.jj_scan_token(39);
    }

    private boolean jj_3R_269() {
        return this.jj_3R_122();
    }

    private boolean jj_3R_270() {
        return this.jj_3R_316();
    }

    private boolean jj_3R_256() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_310()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_311()) {
                return true;
            }
        }
        return this.jj_3R_175();
    }

    private boolean jj_3R_271() {
        return this.jj_3R_317();
    }

    private boolean jj_3R_310() {
        return this.jj_scan_token(109);
    }

    private boolean jj_3R_257() {
        return this.jj_3R_98();
    }

    private boolean jj_3R_297() {
        if (this.jj_scan_token(34)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        if (this.jj_3R_201()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_433()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_311() {
        return this.jj_scan_token(108);
    }

    private boolean jj_3R_77() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_123()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_124()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_258() {
        return this.jj_3R_312();
    }

    private boolean jj_3R_259() {
        return this.jj_3R_313();
    }

    private boolean jj_3R_123() {
        return this.jj_3R_184();
    }

    private boolean jj_3R_433() {
        if (this.jj_scan_token(25)) {
            return true;
        }
        return this.jj_3R_201();
    }

    private boolean jj_3_29() {
        return this.jj_3R_98();
    }

    private boolean jj_3R_124() {
        return this.jj_3R_101();
    }

    private boolean jj_3_17() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_87()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_88()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_87() {
        if (this.jj_3R_101()) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_scan_token(100);
    }

    private boolean jj_3R_312() {
        if (this.jj_3R_215()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_30()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_316() {
        Token xsp;
        if (this.jj_scan_token(27)) {
            return true;
        }
        if (this.jj_3R_318()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_430());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_30() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_99()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_100()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_430() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_318();
    }

    private boolean jj_3R_298() {
        if (this.jj_scan_token(63)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_201();
    }

    private boolean jj_3R_99() {
        return this.jj_scan_token(119);
    }

    private boolean jj_3R_100() {
        return this.jj_scan_token(120);
    }

    private boolean jj_3_60() {
        return this.jj_3R_86();
    }

    private boolean jj_3R_184() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_218()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_219()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_218() {
        Token xsp;
        if (this.jj_3R_101()) {
            return true;
        }
        if (this.jj_3R_273()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_273());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_299() {
        if (this.jj_scan_token(23)) {
            return true;
        }
        if (this.jj_3R_201()) {
            return true;
        }
        if (this.jj_scan_token(63)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_317() {
        Token xsp;
        if (this.jj_scan_token(35)) {
            return true;
        }
        if (this.jj_3R_318()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_431());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_98() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_31()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_157()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_219() {
        Token xsp;
        if (this.jj_3R_138()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_274());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_273() {
        return this.jj_3R_125();
    }

    private boolean jj_3R_431() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_318();
    }

    private boolean jj_3R_88() {
        return this.jj_3R_138();
    }

    private boolean jj_3R_274() {
        return this.jj_3R_125();
    }

    private boolean jj_3_31() {
        if (this.jj_3R_101()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_175();
    }

    private boolean jj_3_18() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_scan_token(100);
    }

    private boolean jj_3_19() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_scan_token(100);
    }

    private boolean jj_3R_157() {
        Token xsp;
        if (this.jj_3R_184()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_204());
        this.jj_scanpos = xsp;
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_205();
    }

    private boolean jj_3R_125() {
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_scan_token(100)) {
            return true;
        }
        return this.jj_scan_token(101);
    }

    private boolean jj_3R_204() {
        if (this.jj_scan_token(125)) {
            return true;
        }
        return this.jj_3R_154();
    }

    private boolean jj_3R_300() {
        if (this.jj_scan_token(32)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_349()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_350()) {
                return true;
            }
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_201();
    }

    private boolean jj_3R_349() {
        if (this.jj_3R_114()) {
            return true;
        }
        if (this.jj_scan_token(111)) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3R_363() {
        if (this.jj_scan_token(26)) {
            return true;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_411()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(98)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_412()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(103)) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_413()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_350() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_369()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(102)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_446()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(102)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_447()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_369() {
        return this.jj_3R_384();
    }

    private boolean jj_3R_411() {
        return this.jj_3R_317();
    }

    private boolean jj_3_47() {
        if (this.jj_3R_114()) {
            return true;
        }
        return this.jj_scan_token(111);
    }

    private boolean jj_3R_446() {
        return this.jj_3R_93();
    }

    private boolean jj_3R_447() {
        return this.jj_3R_451();
    }

    private boolean jj_3R_412() {
        Token xsp;
        if (this.jj_3R_74()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_4());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_4() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_74();
    }

    private boolean jj_3R_318() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_138();
    }

    private boolean jj_3R_413() {
        Token xsp;
        if (this.jj_scan_token(102)) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_425());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_215() {
        Token xsp;
        if (this.jj_3R_133()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_32());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_425() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_441()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(102)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_441() {
        return this.jj_3R_262();
    }

    private boolean jj_3_32() {
        return this.jj_3R_102();
    }

    private boolean jj_3R_138() {
        if (this.jj_3R_91()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_20()) {
            this.jj_scanpos = xsp;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_21());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_20() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_84() {
        Token xsp;
        if (this.jj_3R_133()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_134());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_134() {
        return this.jj_3R_103();
    }

    private boolean jj_3R_384() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_392()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_393()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3_21() {
        if (this.jj_scan_token(104)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_22()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3_22() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_392() {
        return this.jj_3R_114();
    }

    private boolean jj_3R_393() {
        return this.jj_3R_396();
    }

    private boolean jj_3_33() {
        return this.jj_3R_103();
    }

    private boolean jj_3R_74() {
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_121());
        this.jj_scanpos = xsp;
        if (this.jj_3R_91()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_439()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_440()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_121() {
        return this.jj_3R_120();
    }

    private boolean jj_3R_439() {
        return this.jj_3R_189();
    }

    private boolean jj_3R_440() {
        return this.jj_3R_109();
    }

    private boolean jj_3_48() {
        if (this.jj_3R_113()) {
            return true;
        }
        if (this.jj_3R_77()) {
            return true;
        }
        return this.jj_3R_76();
    }

    private boolean jj_3R_133() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_192()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_193()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_194()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_195()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_196()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_197()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_198()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_199()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_192() {
        return this.jj_3R_221();
    }

    private boolean jj_3R_90() {
        if (this.jj_scan_token(107)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_140()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(145);
    }

    private boolean jj_3R_193() {
        return this.jj_scan_token(55);
    }

    private boolean jj_3R_194() {
        if (this.jj_scan_token(52)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_222()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_223()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_140() {
        Token xsp;
        if (this.jj_3R_202()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_232());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_396() {
        Token xsp;
        if (this.jj_3R_93()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_459());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_222() {
        if (this.jj_scan_token(104)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_284()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_285()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_232() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_202();
    }

    private boolean jj_3R_284() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_459() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3R_285() {
        return this.jj_3R_189();
    }

    private boolean jj_3R_223() {
        if (this.jj_scan_token(142)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_286()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_287()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(42)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_286() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_287() {
        return this.jj_3R_76();
    }

    private boolean jj_3R_195() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_224()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_225()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_226()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_227()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_122() {
        Token xsp;
        if (this.jj_scan_token(107)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_3R_182()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_183());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(145);
    }

    private boolean jj_3R_202() {
        if (this.jj_3R_89()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_253()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_254()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_224() {
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_253() {
        return this.jj_3R_77();
    }

    private boolean jj_3R_225() {
        if (this.jj_3R_288()) {
            return true;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_183() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_182();
    }

    private boolean jj_3R_254() {
        return this.jj_3R_308();
    }

    private boolean jj_3R_451() {
        return this.jj_3R_396();
    }

    private boolean jj_3R_226() {
        if (this.jj_3R_289()) {
            return true;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3_34() {
        return this.jj_3R_104();
    }

    private boolean jj_3R_227() {
        if (this.jj_3R_93()) {
            return true;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_196() {
        return this.jj_3R_228();
    }

    private boolean jj_3R_197() {
        if (this.jj_3R_106()) {
            return true;
        }
        if (this.jj_scan_token(104)) {
            return true;
        }
        return this.jj_scan_token(19);
    }

    private boolean jj_3_35() {
        if (this.jj_3R_105()) {
            return true;
        }
        return this.jj_scan_token(103);
    }

    private boolean jj_3R_198() {
        if (this.jj_3R_107()) {
            return true;
        }
        if (this.jj_scan_token(142)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_229()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_230()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(42)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_301() {
        if (this.jj_scan_token(14)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_330()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_308() {
        if (this.jj_scan_token(110)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_354()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_330() {
        return this.jj_3R_91();
    }

    private boolean jj_3R_229() {
        return this.jj_3R_90();
    }

    private boolean jj_3_36() {
        if (this.jj_3R_106()) {
            return true;
        }
        if (this.jj_scan_token(104)) {
            return true;
        }
        return this.jj_scan_token(19);
    }

    private boolean jj_3R_230() {
        return this.jj_3R_76();
    }

    private boolean jj_3R_354() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_370()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_371()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_370() {
        if (this.jj_scan_token(27)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_184();
    }

    private boolean jj_3R_182() {
        if (this.jj_3R_91()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_217()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_199() {
        if (this.jj_3R_91()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_231()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_371() {
        if (this.jj_scan_token(52)) {
            return true;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_184();
    }

    private boolean jj_3_37() {
        if (this.jj_3R_107()) {
            return true;
        }
        return this.jj_scan_token(142);
    }

    private boolean jj_3R_217() {
        return this.jj_3R_272();
    }

    private boolean jj_3R_231() {
        return this.jj_3R_189();
    }

    private boolean jj_3R_112() {
        if (this.jj_scan_token(64)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_102() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_38()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_166()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3_38() {
        return this.jj_3R_103();
    }

    private boolean jj_3R_166() {
        if (this.jj_scan_token(104)) {
            return true;
        }
        return this.jj_scan_token(52);
    }

    private boolean jj_3R_101() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_158()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_159()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_160()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_161()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_162()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_163()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_164()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_165()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_302() {
        if (this.jj_scan_token(21)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_331()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_158() {
        return this.jj_scan_token(13);
    }

    private boolean jj_3R_331() {
        return this.jj_3R_91();
    }

    private boolean jj_3R_159() {
        return this.jj_scan_token(18);
    }

    private boolean jj_3R_160() {
        return this.jj_scan_token(15);
    }

    private boolean jj_3R_272() {
        Token xsp;
        if (this.jj_scan_token(27)) {
            return true;
        }
        if (this.jj_3R_318()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_319());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_161() {
        return this.jj_scan_token(49);
    }

    private boolean jj_3R_162() {
        return this.jj_scan_token(38);
    }

    private boolean jj_3R_103() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_167()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_168()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_163() {
        return this.jj_scan_token(40);
    }

    private boolean jj_3R_319() {
        if (this.jj_scan_token(125)) {
            return true;
        }
        return this.jj_3R_318();
    }

    private boolean jj_3R_164() {
        return this.jj_scan_token(31);
    }

    private boolean jj_3R_165() {
        return this.jj_scan_token(24);
    }

    private boolean jj_3R_167() {
        if (this.jj_scan_token(104)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_206()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_207()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_208()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_303() {
        if (this.jj_scan_token(48)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_332()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_332() {
        return this.jj_3R_93();
    }

    private boolean jj_3R_206() {
        return this.jj_scan_token(55);
    }

    private boolean jj_3R_207() {
        return this.jj_3R_228();
    }

    private boolean jj_3R_208() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_260()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_261()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_106() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_171()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_172()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_260() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_261() {
        return this.jj_3R_189();
    }

    private boolean jj_3R_171() {
        return this.jj_scan_token(61);
    }

    private boolean jj_3R_168() {
        if (this.jj_scan_token(100)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        return this.jj_scan_token(101);
    }

    private boolean jj_3R_172() {
        return this.jj_3R_77();
    }

    private boolean jj_3R_304() {
        if (this.jj_scan_token(56)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3_39() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_108()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_76();
    }

    private boolean jj_3R_108() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_109() {
        Token xsp;
        if (this.jj_scan_token(98)) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_173());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_82() {
        Token xsp;
        if (this.jj_3R_76()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_23());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_173() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_209()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(102)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_209() {
        return this.jj_3R_262();
    }

    private boolean jj_3R_305() {
        if (this.jj_scan_token(54)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_127();
    }

    private boolean jj_3_23() {
        if (this.jj_scan_token(104)) {
            return true;
        }
        return this.jj_3R_76();
    }

    private boolean jj_3R_221() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_276()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_277()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_278()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_279()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_280()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_281()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_282()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_283()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_276() {
        return this.jj_scan_token(76);
    }

    private boolean jj_3R_277() {
        return this.jj_scan_token(75);
    }

    private boolean jj_3R_278() {
        return this.jj_scan_token(81);
    }

    private boolean jj_3R_279() {
        return this.jj_scan_token(88);
    }

    private boolean jj_3R_91() {
        return this.jj_3R_76();
    }

    private boolean jj_3R_280() {
        return this.jj_scan_token(89);
    }

    private boolean jj_3R_281() {
        return this.jj_scan_token(91);
    }

    private boolean jj_3R_282() {
        return this.jj_3R_321();
    }

    private boolean jj_3R_283() {
        return this.jj_3R_322();
    }

    private boolean jj_3R_321() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_351()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_352()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_351() {
        return this.jj_scan_token(59);
    }

    private boolean jj_3R_262() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_9()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_314()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_352() {
        return this.jj_scan_token(28);
    }

    private boolean jj_3_9() {
        return this.jj_3R_79();
    }

    private boolean jj_3R_314() {
        if (this.jj_3R_113()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_340()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_341()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_342()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_343()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_344()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_345()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_322() {
        return this.jj_scan_token(43);
    }

    private boolean jj_3R_76() {
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(71)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(65)) {
                this.jj_scanpos = xsp;
                if (this.jj_scan_token(66)) {
                    this.jj_scanpos = xsp;
                    if (this.jj_scan_token(67)) {
                        this.jj_scanpos = xsp;
                        if (this.jj_scan_token(68)) {
                            this.jj_scanpos = xsp;
                            if (this.jj_scan_token(69)) {
                                this.jj_scanpos = xsp;
                                if (this.jj_scan_token(70)) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_scan_token(72)) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_scan_token(73)) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_scan_token(74)) {
                                                this.jj_scanpos = xsp;
                                                if (this.jj_scan_token(26)) {
                                                    this.jj_scanpos = xsp;
                                                    if (this.jj_scan_token(51)) {
                                                        this.jj_scanpos = xsp;
                                                        if (this.jj_scan_token(64)) {
                                                            this.jj_scanpos = xsp;
                                                            if (this.jj_scan_token(93)) {
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_306() {
        if (this.jj_scan_token(60)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_333()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_127()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_434()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_435()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_340() {
        return this.jj_3R_200();
    }

    private boolean jj_3R_333() {
        return this.jj_3R_356();
    }

    private boolean jj_3R_341() {
        return this.jj_3R_363();
    }

    private boolean jj_3R_342() {
        return this.jj_3R_364();
    }

    private boolean jj_3R_434() {
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_448());
        xsp = this.jj_scanpos = xsp;
        if (this.jj_3R_449()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_343() {
        return this.jj_3R_365();
    }

    private boolean jj_3R_344() {
        return this.jj_3R_366();
    }

    private boolean jj_3_5() {
        return this.jj_scan_token(26);
    }

    private boolean jj_3R_448() {
        Token xsp;
        if (this.jj_scan_token(17)) {
            return true;
        }
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_113()) {
            return true;
        }
        if (this.jj_3R_184()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_452());
        this.jj_scanpos = xsp;
        if (this.jj_3R_105()) {
            return true;
        }
        if (this.jj_scan_token(97)) {
            return true;
        }
        return this.jj_3R_127();
    }

    private boolean jj_3R_345() {
        return this.jj_3R_367();
    }

    private boolean jj_3_6() {
        if (this.jj_scan_token(105)) {
            return true;
        }
        return this.jj_scan_token(39);
    }

    private boolean jj_3_7() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_75()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_76()) {
            return true;
        }
        return this.jj_scan_token(96);
    }

    private boolean jj_3R_189() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_220()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_75() {
        return this.jj_3R_122();
    }

    private boolean jj_3_8() {
        Token xsp;
        if (this.jj_3R_77()) {
            return true;
        }
        if (this.jj_3R_76()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_78());
        xsp = this.jj_scanpos = xsp;
        if (this.jj_scan_token(103)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(106)) {
                this.jj_scanpos = xsp;
                if (this.jj_scan_token(102)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_452() {
        if (this.jj_scan_token(126)) {
            return true;
        }
        return this.jj_3R_154();
    }

    private boolean jj_3R_220() {
        return this.jj_3R_275();
    }

    private boolean jj_3R_93() {
        if (this.jj_3R_153()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_210()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_366() {
        Token xsp;
        if (this.jj_3R_77()) {
            return true;
        }
        if (this.jj_3R_117()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_418());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_435() {
        if (this.jj_scan_token(30)) {
            return true;
        }
        return this.jj_3R_127();
    }

    private boolean jj_3R_449() {
        if (this.jj_scan_token(30)) {
            return true;
        }
        return this.jj_3R_127();
    }

    private boolean jj_3R_275() {
        Token xsp;
        if (this.jj_3R_93()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_320());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_210() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_24()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_264()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_265()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_418() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_117();
    }

    private boolean jj_3R_78() {
        return this.jj_3R_125();
    }

    private boolean jj_3R_320() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3_24() {
        if (this.jj_3R_92()) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3R_264() {
        if (this.jj_scan_token(112)) {
            return true;
        }
        return this.jj_3R_337();
    }

    private boolean jj_3R_265() {
        if (this.jj_scan_token(142)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_338()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_339()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(42)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_338() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_117() {
        if (this.jj_3R_105()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_178()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_339() {
        return this.jj_3R_76();
    }

    private boolean jj_3R_356() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_385()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_49()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_178() {
        if (this.jj_scan_token(106)) {
            return true;
        }
        return this.jj_3R_80();
    }

    private boolean jj_3_49() {
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_92() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_141()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_142()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_143()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_144()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_145()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_146()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_147()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_148()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_149()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3R_150()) {
                                                this.jj_scanpos = xsp;
                                                if (this.jj_3R_151()) {
                                                    this.jj_scanpos = xsp;
                                                    if (this.jj_3R_152()) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_141() {
        return this.jj_scan_token(106);
    }

    private boolean jj_3R_142() {
        return this.jj_scan_token(132);
    }

    private boolean jj_3R_143() {
        return this.jj_scan_token(133);
    }

    private boolean jj_3R_144() {
        return this.jj_scan_token(137);
    }

    private boolean jj_3R_228() {
        if (this.jj_scan_token(42)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_290()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_291()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_292()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_145() {
        return this.jj_scan_token(130);
    }

    private boolean jj_3R_146() {
        return this.jj_scan_token(131);
    }

    private boolean jj_3R_147() {
        return this.jj_scan_token(138);
    }

    private boolean jj_3R_148() {
        return this.jj_scan_token(139);
    }

    private boolean jj_3R_290() {
        return this.jj_3R_90();
    }

    private boolean jj_3R_149() {
        return this.jj_scan_token(140);
    }

    private boolean jj_3R_150() {
        return this.jj_scan_token(134);
    }

    private boolean jj_3R_151() {
        return this.jj_scan_token(136);
    }

    private boolean jj_3R_152() {
        return this.jj_scan_token(135);
    }

    private boolean jj_3R_385() {
        Token xsp;
        if (this.jj_3R_116()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_50());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_105() {
        Token xsp;
        if (this.jj_3R_91()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_170());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_291() {
        if (this.jj_3R_101()) {
            return true;
        }
        return this.jj_3R_325();
    }

    private boolean jj_3R_292() {
        if (this.jj_3R_138()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_326()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_327()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_170() {
        return this.jj_3R_125();
    }

    private boolean jj_3R_326() {
        return this.jj_3R_325();
    }

    private boolean jj_3R_327() {
        if (this.jj_3R_189()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3_40()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_153() {
        if (this.jj_3R_203()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_263()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3_40() {
        return this.jj_3R_109();
    }

    private boolean jj_3R_263() {
        if (this.jj_scan_token(110)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        if (this.jj_scan_token(111)) {
            return true;
        }
        return this.jj_3R_153();
    }

    private boolean jj_3R_80() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_128()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_129()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_116() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_176()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_177()) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_128() {
        return this.jj_3R_186();
    }

    private boolean jj_3R_129() {
        return this.jj_3R_93();
    }

    private boolean jj_3R_176() {
        return this.jj_3R_114();
    }

    private boolean jj_3_51() {
        if (this.jj_3R_113()) {
            return true;
        }
        if (this.jj_3R_77()) {
            return true;
        }
        return this.jj_3R_117();
    }

    private boolean jj_3R_177() {
        return this.jj_3R_215();
    }

    private boolean jj_3_50() {
        if (this.jj_scan_token(102)) {
            return true;
        }
        return this.jj_3R_116();
    }

    private boolean jj_3R_325() {
        Token xsp;
        if (this.jj_3_41()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_41());
        xsp = this.jj_scanpos = xsp;
        if (this.jj_3R_353()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_203() {
        Token xsp;
        if (this.jj_3R_255()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_315());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_97() {
        this.jj_lookingAhead = true;
        this.jj_semLA = this.getToken((int)1).kind == 145 && this.getToken((int)1).realKind == 143;
        this.jj_lookingAhead = false;
        if (!this.jj_semLA || this.jj_3R_156()) {
            return true;
        }
        if (this.jj_scan_token(145)) {
            return true;
        }
        if (this.jj_scan_token(145)) {
            return true;
        }
        return this.jj_scan_token(145);
    }

    private boolean jj_3_41() {
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_scan_token(100)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_110()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(101);
    }

    private boolean jj_3R_315() {
        if (this.jj_scan_token(118)) {
            return true;
        }
        return this.jj_3R_255();
    }

    private boolean jj_3R_353() {
        return this.jj_3R_186();
    }

    private boolean jj_3R_110() {
        return this.jj_3R_93();
    }

    private boolean jj_3R_186() {
        if (this.jj_scan_token(98)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_347()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(103)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_96() {
        this.jj_lookingAhead = true;
        this.jj_semLA = this.getToken((int)1).kind == 145 && this.getToken((int)1).realKind == 144;
        this.jj_lookingAhead = false;
        if (!this.jj_semLA || this.jj_3R_155()) {
            return true;
        }
        if (this.jj_scan_token(145)) {
            return true;
        }
        return this.jj_scan_token(145);
    }

    private boolean jj_3R_156() {
        return false;
    }

    private boolean jj_3R_347() {
        Token xsp;
        if (this.jj_3R_80()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3_10());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3_10() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_80();
    }

    private boolean jj_3R_155() {
        return false;
    }

    private boolean jj_3R_89() {
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_139());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_255() {
        Token xsp;
        if (this.jj_3R_309()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_346());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_139() {
        return this.jj_3R_120();
    }

    private boolean jj_3R_346() {
        if (this.jj_scan_token(117)) {
            return true;
        }
        return this.jj_3R_309();
    }

    private boolean jj_3_52() {
        return this.jj_scan_token(105);
    }

    private boolean jj_3R_367() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_382()) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_3R_89()) {
            return true;
        }
        if (this.jj_3R_106()) {
            return true;
        }
        if (this.jj_3R_91()) {
            return true;
        }
        if (this.jj_3R_415()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_419());
        xsp = this.jj_scanpos = xsp;
        if (this.jj_3R_420()) {
            this.jj_scanpos = xsp;
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_421()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(102)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_201() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_42()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_235()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_43()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_236()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_237()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_238()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_239()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_240()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_241()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3R_242()) {
                                                this.jj_scanpos = xsp;
                                                if (this.jj_3R_243()) {
                                                    this.jj_scanpos = xsp;
                                                    if (this.jj_3R_244()) {
                                                        this.jj_scanpos = xsp;
                                                        if (this.jj_3R_245()) {
                                                            this.jj_scanpos = xsp;
                                                            if (this.jj_3R_246()) {
                                                                this.jj_scanpos = xsp;
                                                                if (this.jj_3R_247()) {
                                                                    this.jj_scanpos = xsp;
                                                                    if (this.jj_3R_248()) {
                                                                        this.jj_scanpos = xsp;
                                                                        if (this.jj_3R_249()) {
                                                                            return true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_382() {
        return this.jj_3R_122();
    }

    private boolean jj_3R_419() {
        return this.jj_3R_125();
    }

    private boolean jj_3_42() {
        return this.jj_3R_111();
    }

    private boolean jj_3R_420() {
        Token xsp;
        if (this.jj_scan_token(57)) {
            return true;
        }
        if (this.jj_3R_154()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_429());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_235() {
        return this.jj_3R_293();
    }

    private boolean jj_3R_429() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_154();
    }

    private boolean jj_3_43() {
        return this.jj_3R_112();
    }

    private boolean jj_3R_236() {
        return this.jj_3R_127();
    }

    private boolean jj_3R_421() {
        return this.jj_3R_127();
    }

    private boolean jj_3R_237() {
        return this.jj_3R_294();
    }

    private boolean jj_3R_238() {
        return this.jj_3R_295();
    }

    private boolean jj_3R_239() {
        return this.jj_3R_296();
    }

    private boolean jj_3R_240() {
        return this.jj_3R_297();
    }

    private boolean jj_3R_241() {
        return this.jj_3R_298();
    }

    private boolean jj_3R_309() {
        Token xsp;
        if (this.jj_3R_335()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_368());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_242() {
        return this.jj_3R_299();
    }

    private boolean jj_3R_243() {
        return this.jj_3R_300();
    }

    private boolean jj_3R_244() {
        return this.jj_3R_301();
    }

    private boolean jj_3R_245() {
        return this.jj_3R_302();
    }

    private boolean jj_3R_246() {
        return this.jj_3R_303();
    }

    private boolean jj_3R_247() {
        return this.jj_3R_304();
    }

    private boolean jj_3R_368() {
        if (this.jj_scan_token(126)) {
            return true;
        }
        return this.jj_3R_335();
    }

    private boolean jj_3R_248() {
        return this.jj_3R_305();
    }

    private boolean jj_3R_249() {
        return this.jj_3R_306();
    }

    private boolean jj_3R_154() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_184();
    }

    private boolean jj_3R_107() {
        if (this.jj_3R_89()) {
            return true;
        }
        return this.jj_3R_77();
    }

    private boolean jj_3R_293() {
        if (this.jj_scan_token(12)) {
            return true;
        }
        if (this.jj_3R_93()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_348()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(102);
    }

    private boolean jj_3R_120() {
        if (this.jj_scan_token(105)) {
            return true;
        }
        if (this.jj_3R_82()) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_250()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_251()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_252()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_348() {
        if (this.jj_scan_token(111)) {
            return true;
        }
        return this.jj_3R_93();
    }

    private boolean jj_3R_250() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_307()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_335() {
        Token xsp;
        if (this.jj_3R_359()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_383());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_307() {
        return this.jj_3R_334();
    }

    private boolean jj_3R_251() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        if (this.jj_3R_119()) {
            return true;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_383() {
        if (this.jj_scan_token(127)) {
            return true;
        }
        return this.jj_3R_359();
    }

    private boolean jj_3_53() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_118()) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(97)) {
                return true;
            }
        }
        return false;
    }

    private boolean jj_3R_252() {
        return false;
    }

    private boolean jj_3R_111() {
        if (this.jj_3R_91()) {
            return true;
        }
        if (this.jj_scan_token(111)) {
            return true;
        }
        return this.jj_3R_201();
    }

    private boolean jj_3R_118() {
        if (this.jj_3R_76()) {
            return true;
        }
        return this.jj_scan_token(106);
    }

    private boolean jj_3_54() {
        return this.jj_scan_token(96);
    }

    private boolean jj_3R_415() {
        if (this.jj_scan_token(96)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_427()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(97);
    }

    private boolean jj_3R_334() {
        Token xsp;
        if (this.jj_3R_357()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_358());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_427() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_443()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_444()) {
                return true;
            }
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_445());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_359() {
        Token xsp;
        if (this.jj_3R_376()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_391());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_443() {
        return this.jj_3R_81();
    }

    private boolean jj_3R_358() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_357();
    }

    private boolean jj_3R_127() {
        if (this.jj_scan_token(98)) {
            return true;
        }
        if (this.jj_3R_185()) {
            return true;
        }
        return this.jj_scan_token(99);
    }

    private boolean jj_3R_444() {
        return this.jj_3R_104();
    }

    private boolean jj_3R_445() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_104();
    }

    private boolean jj_3R_391() {
        if (this.jj_scan_token(125)) {
            return true;
        }
        return this.jj_3R_376();
    }

    private boolean jj_3_11() {
        return this.jj_3R_81();
    }

    private boolean jj_3R_357() {
        if (this.jj_3R_91()) {
            return true;
        }
        if (this.jj_scan_token(106)) {
            return true;
        }
        return this.jj_3R_119();
    }

    private boolean jj_3R_376() {
        Token xsp;
        if (this.jj_3R_386()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_395());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_288() {
        Token xsp;
        if (this.jj_3R_104()) {
            return true;
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_323());
        this.jj_scanpos = xsp;
        return false;
    }

    private boolean jj_3R_323() {
        if (this.jj_scan_token(103)) {
            return true;
        }
        return this.jj_3R_104();
    }

    private boolean jj_3R_395() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3R_399()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_400()) {
                return true;
            }
        }
        return this.jj_3R_386();
    }

    private boolean jj_3R_399() {
        return this.jj_scan_token(113);
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{608700416, 0x4080000, 608700416, 1, 0, 0, 541067264, 524288, 0, 0x8000000, 0, 0, 0, 0, 0x4000000, 0, -1521702912, -1521702912, 0, 0, 0, 0, 0, 0x8000000, 0, -1521702912, -1521702912, 524288, -2063294464, -1521702912, 0, 0, 0, -1794859008, -1794859008, 0, 0, 0, 0, 0, 0, -1522227200, 0, -1522227200, 0, 0, 0, 0, 0, 0, 0, 0, -1794859008, 0, -2130403328, -2063294464, -2063294464, 0, -2063294464, -2063294464, 0x8000000, 0x8000000, -2130403328, -2063294464, 0x4000000, 0, 0x4000000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2063294464, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1794859008, 0, 0, -1794859008, 0, 0, -2063294464, 0, 0, 0, 0x4000000, 0, 0, -1794859008, 0, 0x4000000, 0, 0x10000000, 0x4000000, 0, 0, 0, 0, 0, 0x10000000, 0x10000000, -1794859008, 0, 0, 0, -2063294464, -1794859008, 0, 4096, -1784356864, 0, -1784352768, 0, -1794859008, 0, 0, -1794859008, 0x410000, 0x410000, 0, 0x410000, -1794859008, 0, 0x2000000, -1253791744, -1794859008, -1794859008, -1253791744, -1794859008, 0, 0x4000000, 0x4000000, -1794859008, 0, 131072, 0, 0x40000000, -1794859008, 0x4000000, 0, -1794859008, -1794859008, 0, -1521702912, -1521702912, 524288, -2063294464, 0x400000, 0, 0, 0, 0, 0, 0, 0, 0, -1794859008, 0x4080000};
    }

    private static void jj_la1_init_1() {
        jj_la1_1 = new int[]{1145889424, 128, 1145889424, 0, 262144, 0, 1145889280, 128, 0, 0, 8, 0, 0, 8, 524288, 0, 1682891712, 1682891712, 0, 0, 0, 0, 0, 0, 0, 1682891712, 1682891712, 128, 537526592, 1682891712, 0, 0, 0, 683281728, 683281728, 0, 0, 0, 0, 0x2000000, 0, 1146020672, 0, 1146020672, 0, 0, 0, 0, 0, 0x2000000, 0, 0, 681184576, 262144, 131392, 655680, 655680, 0, 655680, 655680, 0x100000, 0x100000, 131392, 537526592, 524288, 0, 525312, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 655680, 32, 0, 0, 0, 0, 0, 0, 0, 0, 683281728, 0, 0, 683281728, 0, 0, 655680, 0, 0, 0, 525312, 0, 0, 683281728, 0, 525312, 0, 143657984, 524288, 0, 0, 0, 0x800400, 0, 0x8000800, 0x8000000, 683281728, 0, 0, 0, 655680, 683281728, 0, 0, -1174729403, 0, -1174729403, 0, 683281728, 0, 0, 681184576, 0, 0, 0, 0, 700058944, 0, 0, 1828646720, 683281728, 683281728, 1828646720, 683281728, 0, 524288, 524288, 683281728, 0, 0, 0, 0, 681184576, 524288, 0, 683281728, 683281728, 0, 1146020800, 1146020800, 128, 655680, 0, 0, 0, 0, 0, 0, 0, 0, 0, 681184576, 128};
    }

    private static void jj_la1_init_2() {
        jj_la1_2 = new int[]{1168, 144, 1168, 0, 0, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 536872959, 0, 536872959, 536872959, 0, 0, 0, 0, 0, 0, 0, 536872959, 536872959, 0, 536872959, 536872959, 0, 0, 0, 721559551, 721559551, 0, 0, 0, 0, 0, 0, 536872959, 0, 536872959, 0, 0, 0, 0, 0, 0, 0, 0, 721559551, 0, 0, 536872959, 536872959, 0, 536872959, 536872959, 0, 0, 0, 536872959, 536872959, 0, 536872959, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536872959, 0, 0, 0, 0, 0, 0, 0, 0, 0, 721559551, 0, 0, 721559551, 0, 0, 536872959, 0, 0, 0, 536872959, 0, 0, 721559551, 0, 536872959, 0, 184686592, 536872959, 0, 0, 0, 0, 0, 184686592, 0, 721559551, 0, 0, 0, 536872959, 721559551, 0, 0, 721559551, 0, 721559551, 0, 721559551, 0, 0, 721559551, 0, 0, 0, 0, 721559551, 0, 0, 721559551, 721559551, 721559551, 721559551, 721559551, 0, 536872959, 536872959, 721559551, 0, 0, 0, 0, 721559551, 536872959, 0, 721559551, 721559551, 0, 536872959, 536872959, 0, 536872959, 0, 0, 4, 0, 4, 0, 866, 16, 866, 721559551, 0};
    }

    private static void jj_la1_init_3() {
        jj_la1_3 = new int[]{576, 576, 576, 0, 0, 256, 512, 0, 2048, 0, 0, 128, 128, 0, 512, 128, 2628, 2628, 64, 512, 1, 4, 128, 0, 0x20000000, 2628, 2628, 0, 2560, 2560, 128, 1024, 528, 125841925, 125841925, 128, 2048, 528, 128, 0, 68, 512, 128, 512, 128, 128, 512, 2048, 128, 0, 2048, 2048, 2561, 0, 0, 0, 512, 128, 16896, 16384, 0, 0, 0, 0, 0, 2048, 0, 65536, 66560, 1024, 16384, 0x400000, 0x200000, 0x40000000, Integer.MIN_VALUE, 0x20000000, 0x120000, 0x120000, 512, 0, 788480, 788480, 0, 0x6000000, 0x6000000, 0x18000000, 0x18000000, 0x6000000, 125841921, 12288, 12288, 513, 0x1800000, 0x20000000, 0, 2048, 1, 2048, 0, 256, 2, 125841921, 2048, 0, 1, 1, 0, 256, 2048, 1, 0, 272, 0, 0, 125841921, 128, 2048, 529, 0, 125841921, 4, 0, 25166405, 32768, 25166405, 128, 125841925, 25166848, 25166848, 0x1000201, 0, 0, 128, 0, 125841925, 98304, 0, 125841921, 125841921, 125841921, 125841985, 125841921, 128, 0, 0, 125841921, 1, 0, 0x40000000, 0, 513, 0, 128, 125841925, 125841925, 128, 576, 576, 0, 512, 0, 128, 0, 128, 0, 128, 0, 0, 0, 2561, 512};
    }

    private static void jj_la1_init_4() {
        jj_la1_4 = new int[]{0, 0, 0, 262144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8192, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16384, 24572, 8188, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131072, 131072, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8188, 8188, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public GeneratedJavaParser(Provider stream) {
        int i;
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new GeneratedJavaParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 171; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public GeneratedJavaParser(String sDSL) {
        this(new StringProvider(sDSL));
    }

    public void ReInit(String sDSL) {
        this.ReInit(new StringProvider(sDSL));
    }

    @Override
    public void ReInit(Provider stream) {
        int i;
        if (this.jj_input_stream == null) {
            this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        } else {
            this.jj_input_stream.reInit(stream, 1, 1);
        }
        if (this.token_source == null) {
            this.token_source = new GeneratedJavaParserTokenManager(this.jj_input_stream);
        }
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 171; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public GeneratedJavaParser(GeneratedJavaParserTokenManager tm) {
        int i;
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 171; ++i) {
            this.jj_la1[i] = -1;
        }
        for (i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }

    public void ReInit(GeneratedJavaParserTokenManager tm) {
        int i;
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (i = 0; i < 171; ++i) {
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

    @Override
    public final Token getNextToken() {
        this.token = this.token.next != null ? this.token.next : (this.token.next = this.token_source.getNextToken());
        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }

    public final Token getToken(int index) {
        Token t = this.jj_lookingAhead ? this.jj_scanpos : this.token;
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
        boolean[] la1tokens = new boolean[147];
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }
        for (i = 0; i < 171; ++i) {
            if (this.jj_la1[i] != this.jj_gen) continue;
            for (int j = 0; j < 32; ++j) {
                if ((jj_la1_0[i] & 1 << j) != 0) {
                    la1tokens[j] = true;
                }
                if ((jj_la1_1[i] & 1 << j) != 0) {
                    la1tokens[32 + j] = true;
                }
                if ((jj_la1_2[i] & 1 << j) != 0) {
                    la1tokens[64 + j] = true;
                }
                if ((jj_la1_3[i] & 1 << j) != 0) {
                    la1tokens[96 + j] = true;
                }
                if ((jj_la1_4[i] & 1 << j) == 0) continue;
                la1tokens[128 + j] = true;
            }
        }
        for (i = 0; i < 147; ++i) {
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
        return new ParseException(this.token, exptokseq, tokenImage, this.token_source == null ? null : GeneratedJavaParserTokenManager.lexStateNames[this.token_source.curLexState]);
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
        for (int i = 0; i < 60; ++i) {
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
                            break;
                        }
                        case 1: {
                            this.jj_3_2();
                            break;
                        }
                        case 2: {
                            this.jj_3_3();
                            break;
                        }
                        case 3: {
                            this.jj_3_4();
                            break;
                        }
                        case 4: {
                            this.jj_3_5();
                            break;
                        }
                        case 5: {
                            this.jj_3_6();
                            break;
                        }
                        case 6: {
                            this.jj_3_7();
                            break;
                        }
                        case 7: {
                            this.jj_3_8();
                            break;
                        }
                        case 8: {
                            this.jj_3_9();
                            break;
                        }
                        case 9: {
                            this.jj_3_10();
                            break;
                        }
                        case 10: {
                            this.jj_3_11();
                            break;
                        }
                        case 11: {
                            this.jj_3_12();
                            break;
                        }
                        case 12: {
                            this.jj_3_13();
                            break;
                        }
                        case 13: {
                            this.jj_3_14();
                            break;
                        }
                        case 14: {
                            this.jj_3_15();
                            break;
                        }
                        case 15: {
                            this.jj_3_16();
                            break;
                        }
                        case 16: {
                            this.jj_3_17();
                            break;
                        }
                        case 17: {
                            this.jj_3_18();
                            break;
                        }
                        case 18: {
                            this.jj_3_19();
                            break;
                        }
                        case 19: {
                            this.jj_3_20();
                            break;
                        }
                        case 20: {
                            this.jj_3_21();
                            break;
                        }
                        case 21: {
                            this.jj_3_22();
                            break;
                        }
                        case 22: {
                            this.jj_3_23();
                            break;
                        }
                        case 23: {
                            this.jj_3_24();
                            break;
                        }
                        case 24: {
                            this.jj_3_25();
                            break;
                        }
                        case 25: {
                            this.jj_3_26();
                            break;
                        }
                        case 26: {
                            this.jj_3_27();
                            break;
                        }
                        case 27: {
                            this.jj_3_28();
                            break;
                        }
                        case 28: {
                            this.jj_3_29();
                            break;
                        }
                        case 29: {
                            this.jj_3_30();
                            break;
                        }
                        case 30: {
                            this.jj_3_31();
                            break;
                        }
                        case 31: {
                            this.jj_3_32();
                            break;
                        }
                        case 32: {
                            this.jj_3_33();
                            break;
                        }
                        case 33: {
                            this.jj_3_34();
                            break;
                        }
                        case 34: {
                            this.jj_3_35();
                            break;
                        }
                        case 35: {
                            this.jj_3_36();
                            break;
                        }
                        case 36: {
                            this.jj_3_37();
                            break;
                        }
                        case 37: {
                            this.jj_3_38();
                            break;
                        }
                        case 38: {
                            this.jj_3_39();
                            break;
                        }
                        case 39: {
                            this.jj_3_40();
                            break;
                        }
                        case 40: {
                            this.jj_3_41();
                            break;
                        }
                        case 41: {
                            this.jj_3_42();
                            break;
                        }
                        case 42: {
                            this.jj_3_43();
                            break;
                        }
                        case 43: {
                            this.jj_3_44();
                            break;
                        }
                        case 44: {
                            this.jj_3_45();
                            break;
                        }
                        case 45: {
                            this.jj_3_46();
                            break;
                        }
                        case 46: {
                            this.jj_3_47();
                            break;
                        }
                        case 47: {
                            this.jj_3_48();
                            break;
                        }
                        case 48: {
                            this.jj_3_49();
                            break;
                        }
                        case 49: {
                            this.jj_3_50();
                            break;
                        }
                        case 50: {
                            this.jj_3_51();
                            break;
                        }
                        case 51: {
                            this.jj_3_52();
                            break;
                        }
                        case 52: {
                            this.jj_3_53();
                            break;
                        }
                        case 53: {
                            this.jj_3_54();
                            break;
                        }
                        case 54: {
                            this.jj_3_55();
                            break;
                        }
                        case 55: {
                            this.jj_3_56();
                            break;
                        }
                        case 56: {
                            this.jj_3_57();
                            break;
                        }
                        case 57: {
                            this.jj_3_58();
                            break;
                        }
                        case 58: {
                            this.jj_3_59();
                            break;
                        }
                        case 59: {
                            this.jj_3_60();
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
        GeneratedJavaParser.jj_la1_init_0();
        GeneratedJavaParser.jj_la1_init_1();
        GeneratedJavaParser.jj_la1_init_2();
        GeneratedJavaParser.jj_la1_init_3();
        GeneratedJavaParser.jj_la1_init_4();
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

