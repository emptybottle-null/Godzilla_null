/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer;

import com.github.javaparser.ast.ArrayCreationLevel;
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
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
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
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.nodeTypes.NodeWithTraversableScope;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithVariables;
import com.github.javaparser.ast.nodeTypes.SwitchNode;
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
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.configuration.ConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.PositionUtils;
import com.github.javaparser.utils.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class DefaultPrettyPrinterVisitor
implements VoidVisitor<Void> {
    protected final PrinterConfiguration configuration;
    protected final SourcePrinter printer;

    public DefaultPrettyPrinterVisitor(PrinterConfiguration configuration) {
        this(configuration, new SourcePrinter(configuration));
    }

    public DefaultPrettyPrinterVisitor(PrinterConfiguration configuration, SourcePrinter printer) {
        this.configuration = configuration;
        this.printer = printer;
    }

    public String toString() {
        return this.printer.toString();
    }

    protected void printModifiers(NodeList<Modifier> modifiers) {
        if (modifiers.size() > 0) {
            this.printer.print(modifiers.stream().map(Modifier::getKeyword).map(Modifier.Keyword::asString).collect(Collectors.joining(" ")) + " ");
        }
    }

    protected void printMembers(NodeList<BodyDeclaration<?>> members, Void arg) {
        for (BodyDeclaration<?> member : members) {
            this.printer.println();
            member.accept(this, arg);
            this.printer.println();
        }
    }

    protected void printMemberAnnotations(NodeList<AnnotationExpr> annotations, Void arg) {
        if (annotations.isEmpty()) {
            return;
        }
        for (AnnotationExpr a : annotations) {
            a.accept(this, arg);
            this.printer.println();
        }
    }

    protected void printAnnotations(NodeList<AnnotationExpr> annotations, boolean prefixWithASpace, Void arg) {
        if (annotations.isEmpty()) {
            return;
        }
        if (prefixWithASpace) {
            this.printer.print(" ");
        }
        for (AnnotationExpr annotation : annotations) {
            annotation.accept(this, arg);
            this.printer.print(" ");
        }
    }

    protected void printTypeArgs(NodeWithTypeArguments<?> nodeWithTypeArguments, Void arg) {
        NodeList typeArguments = nodeWithTypeArguments.getTypeArguments().orElse(null);
        if (!Utils.isNullOrEmpty(typeArguments)) {
            this.printer.print("<");
            Iterator i = typeArguments.iterator();
            while (i.hasNext()) {
                Type t = (Type)i.next();
                t.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
            this.printer.print(">");
        }
    }

    protected void printTypeParameters(NodeList<TypeParameter> args, Void arg) {
        if (!Utils.isNullOrEmpty(args)) {
            this.printer.print("<");
            Iterator<TypeParameter> i = args.iterator();
            while (i.hasNext()) {
                TypeParameter t = i.next();
                t.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
            this.printer.print(">");
        }
    }

    protected void printArguments(NodeList<Expression> args, Void arg) {
        this.printer.print("(");
        if (!Utils.isNullOrEmpty(args)) {
            boolean columnAlignParameters;
            boolean bl = columnAlignParameters = args.size() > 1 && this.getOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_PARAMETERS).isPresent();
            if (columnAlignParameters) {
                this.printer.indentWithAlignTo(this.printer.getCursor().column);
            }
            Iterator<Expression> i = args.iterator();
            while (i.hasNext()) {
                Expression e = i.next();
                e.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(",");
                if (columnAlignParameters) {
                    this.printer.println();
                    continue;
                }
                this.printer.print(" ");
            }
            if (columnAlignParameters) {
                this.printer.unindent();
            }
        }
        this.printer.print(")");
    }

    protected void printPrePostFixOptionalList(NodeList<? extends Visitable> args, Void arg, String prefix, String separator, String postfix) {
        if (!args.isEmpty()) {
            this.printer.print(prefix);
            Iterator<? extends Visitable> i = args.iterator();
            while (i.hasNext()) {
                Visitable v = i.next();
                v.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(separator);
            }
            this.printer.print(postfix);
        }
    }

    protected void printPrePostFixRequiredList(NodeList<? extends Visitable> args, Void arg, String prefix, String separator, String postfix) {
        this.printer.print(prefix);
        if (!args.isEmpty()) {
            Iterator<? extends Visitable> i = args.iterator();
            while (i.hasNext()) {
                Visitable v = i.next();
                v.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(separator);
            }
        }
        this.printer.print(postfix);
    }

    protected void printComment(Optional<Comment> comment, Void arg) {
        comment.ifPresent(c -> c.accept(this, arg));
    }

    @Override
    public void visit(CompilationUnit n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getParsed() == Node.Parsedness.UNPARSABLE) {
            this.printer.println("???");
            return;
        }
        if (n.getPackageDeclaration().isPresent()) {
            n.getPackageDeclaration().get().accept(this, arg);
        }
        n.getImports().accept(this, arg);
        if (!n.getImports().isEmpty()) {
            this.printer.println();
        }
        Iterator<TypeDeclaration<?>> i = n.getTypes().iterator();
        while (i.hasNext()) {
            i.next().accept(this, arg);
            this.printer.println();
            if (!i.hasNext()) continue;
            this.printer.println();
        }
        n.getModule().ifPresent(m -> m.accept(this, arg));
        this.printOrphanCommentsEnding(n);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printer.print("package ");
        n.getName().accept(this, arg);
        this.printer.println(";");
        this.printer.println();
        this.printOrphanCommentsEnding(n);
    }

    @Override
    public void visit(NameExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getName().accept(this, arg);
        this.printOrphanCommentsEnding(n);
    }

    @Override
    public void visit(Name n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getQualifier().isPresent()) {
            n.getQualifier().get().accept(this, arg);
            this.printer.print(".");
        }
        this.printer.print(n.getIdentifier());
        this.printOrphanCommentsEnding(n);
    }

    @Override
    public void visit(SimpleName n, Void arg) {
        this.printer.print(n.getIdentifier());
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        ClassOrInterfaceType c;
        Iterator<ClassOrInterfaceType> i;
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        if (n.isInterface()) {
            this.printer.print("interface ");
        } else {
            this.printer.print("class ");
        }
        n.getName().accept(this, arg);
        this.printTypeParameters(n.getTypeParameters(), arg);
        if (!n.getExtendedTypes().isEmpty()) {
            this.printer.print(" extends ");
            i = n.getExtendedTypes().iterator();
            while (i.hasNext()) {
                c = i.next();
                c.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        if (!n.getImplementedTypes().isEmpty()) {
            this.printer.print(" implements ");
            i = n.getImplementedTypes().iterator();
            while (i.hasNext()) {
                c = i.next();
                c.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.println(" {");
        this.printer.indent();
        if (!Utils.isNullOrEmpty(n.getMembers())) {
            this.printMembers(n.getMembers(), arg);
        }
        this.printOrphanCommentsEnding(n);
        this.printer.unindent();
        this.printer.print("}");
    }

    @Override
    public void visit(JavadocComment n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS).isPresent() && this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_JAVADOC).isPresent()) {
            this.printer.println("/**");
            String commentContent = Utils.normalizeEolInTextBlock(n.getContent(), this.getOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER).get().asString());
            String[] lines = commentContent.split("\\R");
            ArrayList<String> strippedLines = new ArrayList<String>();
            for (String line2 : lines) {
                String trimmedLine = line2.trim();
                if (trimmedLine.startsWith("*")) {
                    line2 = trimmedLine.substring(1);
                }
                line2 = Utils.trimTrailingSpaces(line2);
                strippedLines.add(line2);
            }
            boolean skippingLeadingEmptyLines = true;
            boolean prependEmptyLine = false;
            boolean prependSpace = strippedLines.stream().anyMatch(line -> !line.isEmpty() && !line.startsWith(" "));
            for (String line3 : strippedLines) {
                if (line3.isEmpty()) {
                    if (skippingLeadingEmptyLines) continue;
                    prependEmptyLine = true;
                    continue;
                }
                skippingLeadingEmptyLines = false;
                if (prependEmptyLine) {
                    this.printer.println(" *");
                    prependEmptyLine = false;
                }
                this.printer.print(" *");
                if (prependSpace) {
                    this.printer.print(" ");
                }
                this.printer.println(line3);
            }
            this.printer.println(" */");
        }
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getScope().isPresent()) {
            n.getScope().get().accept(this, arg);
            this.printer.print(".");
        }
        this.printAnnotations(n.getAnnotations(), false, arg);
        n.getName().accept(this, arg);
        if (n.isUsingDiamondOperator()) {
            this.printer.print("<>");
        } else {
            this.printTypeArgs(n, arg);
        }
    }

    @Override
    public void visit(TypeParameter n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        n.getName().accept(this, arg);
        if (!Utils.isNullOrEmpty(n.getTypeBound())) {
            this.printer.print(" extends ");
            Iterator<ClassOrInterfaceType> i = n.getTypeBound().iterator();
            while (i.hasNext()) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(" & ");
            }
        }
    }

    @Override
    public void visit(PrimitiveType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), true, arg);
        this.printer.print(n.getType().asString());
    }

    @Override
    public void visit(ArrayType n, Void arg) {
        LinkedList<ArrayType> arrayTypeBuffer = new LinkedList<ArrayType>();
        Type type = n;
        while (type instanceof ArrayType) {
            ArrayType arrayType = type;
            arrayTypeBuffer.add(arrayType);
            type = arrayType.getComponentType();
        }
        type.accept(this, arg);
        for (ArrayType arrayType : arrayTypeBuffer) {
            this.printAnnotations(arrayType.getAnnotations(), true, arg);
            this.printer.print("[]");
        }
    }

    @Override
    public void visit(ArrayCreationLevel n, Void arg) {
        this.printAnnotations(n.getAnnotations(), true, arg);
        this.printer.print("[");
        if (n.getDimension().isPresent()) {
            n.getDimension().get().accept(this, arg);
        }
        this.printer.print("]");
    }

    @Override
    public void visit(IntersectionType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        boolean isFirst = true;
        for (ReferenceType element : n.getElements()) {
            if (isFirst) {
                isFirst = false;
            } else {
                this.printer.print(" & ");
            }
            element.accept(this, arg);
        }
    }

    @Override
    public void visit(UnionType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), true, arg);
        boolean isFirst = true;
        for (ReferenceType element : n.getElements()) {
            if (isFirst) {
                isFirst = false;
            } else {
                this.printer.print(" | ");
            }
            element.accept(this, arg);
        }
    }

    @Override
    public void visit(WildcardType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        this.printer.print("?");
        if (n.getExtendedType().isPresent()) {
            this.printer.print(" extends ");
            n.getExtendedType().get().accept(this, arg);
        }
        if (n.getSuperType().isPresent()) {
            this.printer.print(" super ");
            n.getSuperType().get().accept(this, arg);
        }
    }

    @Override
    public void visit(UnknownType n, Void arg) {
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        if (!n.getVariables().isEmpty()) {
            Optional<Type> maximumCommonType = n.getMaximumCommonType();
            maximumCommonType.ifPresent(t -> t.accept(this, arg));
            if (!maximumCommonType.isPresent()) {
                this.printer.print("???");
            }
        }
        this.printer.print(" ");
        Iterator<VariableDeclarator> i = n.getVariables().iterator();
        while (i.hasNext()) {
            VariableDeclarator var = i.next();
            var.accept(this, arg);
            if (!i.hasNext()) continue;
            this.printer.print(", ");
        }
        this.printer.print(";");
    }

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getName().accept(this, arg);
        n.findAncestor(NodeWithVariables.class).ifPresent(ancestor -> ancestor.getMaximumCommonType().ifPresent(commonType -> {
            Type type = n.getType();
            ArrayType arrayType = null;
            for (int i = commonType.getArrayLevel(); i < type.getArrayLevel(); ++i) {
                arrayType = arrayType == null ? (ArrayType)type : (ArrayType)arrayType.getComponentType();
                this.printAnnotations(arrayType.getAnnotations(), true, arg);
                this.printer.print("[]");
            }
        }));
        if (n.getInitializer().isPresent()) {
            this.printer.print(" = ");
            n.getInitializer().get().accept(this, arg);
        }
    }

    @Override
    public void visit(ArrayInitializerExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("{");
        if (!Utils.isNullOrEmpty(n.getValues())) {
            this.printer.print(" ");
            Iterator<Expression> i = n.getValues().iterator();
            while (i.hasNext()) {
                Expression expr = i.next();
                expr.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
            this.printer.print(" ");
        }
        this.printOrphanCommentsEnding(n);
        this.printer.print("}");
    }

    @Override
    public void visit(VoidType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        this.printer.print("void");
    }

    @Override
    public void visit(VarType n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        this.printer.print("var");
    }

    @Override
    public void visit(Modifier n, Void arg) {
        this.printer.print(n.getKeyword().asString());
        this.printer.print(" ");
    }

    @Override
    public void visit(ArrayAccessExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getName().accept(this, arg);
        this.printer.print("[");
        n.getIndex().accept(this, arg);
        this.printer.print("]");
    }

    @Override
    public void visit(ArrayCreationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("new ");
        n.getElementType().accept(this, arg);
        for (ArrayCreationLevel level : n.getLevels()) {
            level.accept(this, arg);
        }
        if (n.getInitializer().isPresent()) {
            this.printer.print(" ");
            n.getInitializer().get().accept(this, arg);
        }
    }

    @Override
    public void visit(AssignExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getTarget().accept(this, arg);
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS).isPresent()) {
            this.printer.print(" ");
        }
        this.printer.print(n.getOperator().asString());
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS).isPresent()) {
            this.printer.print(" ");
        }
        n.getValue().accept(this, arg);
    }

    @Override
    public void visit(BinaryExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getLeft().accept(this, arg);
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS).isPresent()) {
            this.printer.print(" ");
        }
        this.printer.print(n.getOperator().asString());
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.SPACE_AROUND_OPERATORS).isPresent()) {
            this.printer.print(" ");
        }
        n.getRight().accept(this, arg);
    }

    @Override
    public void visit(CastExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("(");
        n.getType().accept(this, arg);
        this.printer.print(") ");
        n.getExpression().accept(this, arg);
    }

    @Override
    public void visit(ClassExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getType().accept(this, arg);
        this.printer.print(".class");
    }

    @Override
    public void visit(ConditionalExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getCondition().accept(this, arg);
        this.printer.print(" ? ");
        n.getThenExpr().accept(this, arg);
        this.printer.print(" : ");
        n.getElseExpr().accept(this, arg);
    }

    @Override
    public void visit(EnclosedExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("(");
        n.getInner().accept(this, arg);
        this.printer.print(")");
    }

    @Override
    public void visit(FieldAccessExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getScope().accept(this, arg);
        this.printer.print(".");
        n.getName().accept(this, arg);
    }

    @Override
    public void visit(InstanceOfExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getExpression().accept(this, arg);
        this.printer.print(" instanceof ");
        n.getType().accept(this, arg);
        if (n.getName().isPresent()) {
            this.printer.print(" ");
            n.getName().get().accept(this, arg);
        }
    }

    @Override
    public void visit(PatternExpr n, Void arg) {
        n.getType().accept(this, arg);
        this.printer.print(" ");
        n.getName().accept(this, arg);
    }

    @Override
    public void visit(CharLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("'");
        this.printer.print(n.getValue());
        this.printer.print("'");
    }

    @Override
    public void visit(DoubleLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(n.getValue());
    }

    @Override
    public void visit(IntegerLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(n.getValue());
    }

    @Override
    public void visit(LongLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(n.getValue());
    }

    @Override
    public void visit(StringLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("\"");
        this.printer.print(n.getValue());
        this.printer.print("\"");
    }

    @Override
    public void visit(TextBlockLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("\"\"\"");
        this.printer.indent();
        n.stripIndentOfLines().forEach(line -> {
            this.printer.println();
            this.printer.print((String)line);
        });
        this.printer.print("\"\"\"");
        this.printer.unindent();
    }

    @Override
    public void visit(BooleanLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(String.valueOf(n.getValue()));
    }

    @Override
    public void visit(NullLiteralExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("null");
    }

    @Override
    public void visit(ThisExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getTypeName().isPresent()) {
            n.getTypeName().get().accept(this, arg);
            this.printer.print(".");
        }
        this.printer.print("this");
    }

    @Override
    public void visit(SuperExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getTypeName().isPresent()) {
            n.getTypeName().get().accept(this, arg);
            this.printer.print(".");
        }
        this.printer.print("super");
    }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        AtomicBoolean columnAlignFirstMethodChain = new AtomicBoolean();
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.COLUMN_ALIGN_FIRST_METHOD_CHAIN).isPresent() && n.findAncestor(Statement.class).map(p -> p.isReturnStmt() || p.isThrowStmt() || p.isAssertStmt() || p.isExpressionStmt()).orElse(false).booleanValue()) {
            Node c = n;
            Optional<Node> p2 = c.getParentNode();
            while (p2.isPresent()) {
                if (!p2.filter(NodeWithTraversableScope.class::isInstance).map(NodeWithTraversableScope.class::cast).flatMap(NodeWithTraversableScope::traverseScope).map(c::equals).orElse(false).booleanValue()) break;
                c = p2.get();
                p2 = c.getParentNode();
            }
            columnAlignFirstMethodChain.set(!p2.filter(MethodCallExpr.class::isInstance).isPresent());
        }
        AtomicBoolean lastMethodInCallChain = new AtomicBoolean(true);
        if (columnAlignFirstMethodChain.get()) {
            Node node = n;
            while (node.getParentNode().filter(NodeWithTraversableScope.class::isInstance).map(NodeWithTraversableScope.class::cast).flatMap(NodeWithTraversableScope::traverseScope).map(node::equals).orElse(false).booleanValue()) {
                if (!((node = node.getParentNode().orElseThrow(AssertionError::new)) instanceof MethodCallExpr)) continue;
                lastMethodInCallChain.set(false);
                break;
            }
        }
        AtomicBoolean methodCallWithScopeInScope = new AtomicBoolean();
        if (columnAlignFirstMethodChain.get()) {
            Optional<Expression> s = n.getScope();
            while (s.filter(NodeWithTraversableScope.class::isInstance).isPresent()) {
                Optional parentScope = s.map(NodeWithTraversableScope.class::cast).flatMap(NodeWithTraversableScope::traverseScope);
                if (s.filter(MethodCallExpr.class::isInstance).isPresent() && parentScope.isPresent()) {
                    methodCallWithScopeInScope.set(true);
                    break;
                }
                s = parentScope;
            }
        }
        n.getScope().ifPresent(scope -> {
            scope.accept(this, arg);
            if (columnAlignFirstMethodChain.get()) {
                if (methodCallWithScopeInScope.get()) {
                    this.printer.println();
                } else if (!lastMethodInCallChain.get()) {
                    this.printer.reindentWithAlignToCursor();
                }
            }
            this.printer.print(".");
        });
        this.printTypeArgs(n, arg);
        n.getName().accept(this, arg);
        this.printer.duplicateIndent();
        this.printArguments(n.getArguments(), arg);
        this.printer.unindent();
        if (columnAlignFirstMethodChain.get() && methodCallWithScopeInScope.get() && lastMethodInCallChain.get()) {
            this.printer.reindentToPreviousLevel();
        }
    }

    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.hasScope()) {
            n.getScope().get().accept(this, arg);
            this.printer.print(".");
        }
        this.printer.print("new ");
        this.printTypeArgs(n, arg);
        if (!Utils.isNullOrEmpty(n.getTypeArguments().orElse(null))) {
            this.printer.print(" ");
        }
        n.getType().accept(this, arg);
        this.printArguments(n.getArguments(), arg);
        if (n.getAnonymousClassBody().isPresent()) {
            this.printer.println(" {");
            this.printer.indent();
            this.printMembers(n.getAnonymousClassBody().get(), arg);
            this.printer.unindent();
            this.printer.print("}");
        }
    }

    @Override
    public void visit(UnaryExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getOperator().isPrefix()) {
            this.printer.print(n.getOperator().asString());
        }
        n.getExpression().accept(this, arg);
        if (n.getOperator().isPostfix()) {
            this.printer.print(n.getOperator().asString());
        }
    }

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        Iterator<Node> i;
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        this.printTypeParameters(n.getTypeParameters(), arg);
        if (n.isGeneric()) {
            this.printer.print(" ");
        }
        n.getName().accept(this, arg);
        this.printer.print("(");
        if (!n.getParameters().isEmpty()) {
            i = n.getParameters().iterator();
            while (i.hasNext()) {
                Parameter p = i.next();
                p.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print(")");
        if (!Utils.isNullOrEmpty(n.getThrownExceptions())) {
            this.printer.print(" throws ");
            i = n.getThrownExceptions().iterator();
            while (i.hasNext()) {
                ReferenceType name = (ReferenceType)i.next();
                name.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print(" ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        Iterator<Node> i;
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        this.printTypeParameters(n.getTypeParameters(), arg);
        if (!Utils.isNullOrEmpty(n.getTypeParameters())) {
            this.printer.print(" ");
        }
        n.getType().accept(this, arg);
        this.printer.print(" ");
        n.getName().accept(this, arg);
        this.printer.print("(");
        n.getReceiverParameter().ifPresent(rp -> {
            rp.accept(this, arg);
            if (!Utils.isNullOrEmpty(n.getParameters())) {
                this.printer.print(", ");
            }
        });
        if (!Utils.isNullOrEmpty(n.getParameters())) {
            i = n.getParameters().iterator();
            while (i.hasNext()) {
                Parameter p = i.next();
                p.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print(")");
        if (!Utils.isNullOrEmpty(n.getThrownExceptions())) {
            this.printer.print(" throws ");
            i = n.getThrownExceptions().iterator();
            while (i.hasNext()) {
                ReferenceType name = (ReferenceType)i.next();
                name.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        if (!n.getBody().isPresent()) {
            this.printer.print(";");
        } else {
            this.printer.print(" ");
            n.getBody().get().accept(this, arg);
        }
    }

    @Override
    public void visit(Parameter n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        this.printModifiers(n.getModifiers());
        n.getType().accept(this, arg);
        if (n.isVarArgs()) {
            this.printAnnotations(n.getVarArgsAnnotations(), false, arg);
            this.printer.print("...");
        }
        if (!(n.getType() instanceof UnknownType)) {
            this.printer.print(" ");
        }
        n.getName().accept(this, arg);
    }

    @Override
    public void visit(ReceiverParameter n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printAnnotations(n.getAnnotations(), false, arg);
        n.getType().accept(this, arg);
        this.printer.print(" ");
        n.getName().accept(this, arg);
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.isThis()) {
            this.printTypeArgs(n, arg);
            this.printer.print("this");
        } else {
            if (n.getExpression().isPresent()) {
                n.getExpression().get().accept(this, arg);
                this.printer.print(".");
            }
            this.printTypeArgs(n, arg);
            this.printer.print("super");
        }
        this.printArguments(n.getArguments(), arg);
        this.printer.print(";");
    }

    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getParentNode().map(ExpressionStmt.class::isInstance).orElse(false).booleanValue()) {
            this.printMemberAnnotations(n.getAnnotations(), arg);
        } else {
            this.printAnnotations(n.getAnnotations(), false, arg);
        }
        this.printModifiers(n.getModifiers());
        if (!n.getVariables().isEmpty()) {
            n.getMaximumCommonType().ifPresent(t -> t.accept(this, arg));
        }
        this.printer.print(" ");
        Iterator<VariableDeclarator> i = n.getVariables().iterator();
        while (i.hasNext()) {
            VariableDeclarator v = i.next();
            v.accept(this, arg);
            if (!i.hasNext()) continue;
            this.printer.print(", ");
        }
    }

    @Override
    public void visit(LocalClassDeclarationStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getClassDeclaration().accept(this, arg);
    }

    @Override
    public void visit(AssertStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("assert ");
        n.getCheck().accept(this, arg);
        if (n.getMessage().isPresent()) {
            this.printer.print(" : ");
            n.getMessage().get().accept(this, arg);
        }
        this.printer.print(";");
    }

    @Override
    public void visit(BlockStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.println("{");
        if (n.getStatements() != null) {
            this.printer.indent();
            for (Statement s : n.getStatements()) {
                s.accept(this, arg);
                this.printer.println();
            }
        }
        this.printOrphanCommentsEnding(n);
        this.printer.unindent();
        this.printer.print("}");
    }

    @Override
    public void visit(LabeledStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getLabel().accept(this, arg);
        this.printer.print(": ");
        n.getStatement().accept(this, arg);
    }

    @Override
    public void visit(EmptyStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(";");
    }

    @Override
    public void visit(ExpressionStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getExpression().accept(this, arg);
        this.printer.print(";");
    }

    @Override
    public void visit(SwitchStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printSwitchNode(n, arg);
    }

    @Override
    public void visit(SwitchExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printSwitchNode(n, arg);
    }

    private void printSwitchNode(SwitchNode n, Void arg) {
        this.printComment(n.getComment(), arg);
        this.printer.print("switch(");
        n.getSelector().accept(this, arg);
        this.printer.println(") {");
        if (n.getEntries() != null) {
            this.indentIf(this.getOption(DefaultPrinterConfiguration.ConfigOption.INDENT_CASE_IN_SWITCH).isPresent());
            for (SwitchEntry e : n.getEntries()) {
                e.accept(this, arg);
            }
            this.unindentIf(this.getOption(DefaultPrinterConfiguration.ConfigOption.INDENT_CASE_IN_SWITCH).isPresent());
        }
        this.printer.print("}");
    }

    @Override
    public void visit(SwitchEntry n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (Utils.isNullOrEmpty(n.getLabels())) {
            this.printer.print("default:");
        } else {
            this.printer.print("case ");
            Iterator<Expression> i = n.getLabels().iterator();
            while (i.hasNext()) {
                Expression label = i.next();
                label.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
            this.printer.print(":");
        }
        this.printer.println();
        this.printer.indent();
        if (n.getStatements() != null) {
            for (Statement s : n.getStatements()) {
                s.accept(this, arg);
                this.printer.println();
            }
        }
        this.printer.unindent();
    }

    @Override
    public void visit(BreakStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("break");
        n.getLabel().ifPresent(l -> this.printer.print(" ").print(l.getIdentifier()));
        this.printer.print(";");
    }

    @Override
    public void visit(YieldStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("yield ");
        n.getExpression().accept(this, arg);
        this.printer.print(";");
    }

    @Override
    public void visit(ReturnStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("return");
        if (n.getExpression().isPresent()) {
            this.printer.print(" ");
            n.getExpression().get().accept(this, arg);
        }
        this.printer.print(";");
    }

    @Override
    public void visit(EnumDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        this.printer.print("enum ");
        n.getName().accept(this, arg);
        if (!n.getImplementedTypes().isEmpty()) {
            this.printer.print(" implements ");
            Iterator<ClassOrInterfaceType> i = n.getImplementedTypes().iterator();
            while (i.hasNext()) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.println(" {");
        this.printer.indent();
        if (n.getEntries().isNonEmpty()) {
            boolean alignVertically = n.getEntries().size() > this.getOption(DefaultPrinterConfiguration.ConfigOption.MAX_ENUM_CONSTANTS_TO_ALIGN_HORIZONTALLY).get().asInteger() || n.getEntries().stream().anyMatch(e -> e.getComment().isPresent());
            this.printer.println();
            Iterator<EnumConstantDeclaration> i = n.getEntries().iterator();
            while (i.hasNext()) {
                EnumConstantDeclaration e2 = i.next();
                e2.accept(this, arg);
                if (!i.hasNext()) continue;
                if (alignVertically) {
                    this.printer.println(",");
                    continue;
                }
                this.printer.print(", ");
            }
        }
        if (!n.getMembers().isEmpty()) {
            this.printer.println(";");
            this.printMembers(n.getMembers(), arg);
        } else if (!n.getEntries().isEmpty()) {
            this.printer.println();
        }
        this.printer.unindent();
        this.printer.print("}");
    }

    @Override
    public void visit(EnumConstantDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        n.getName().accept(this, arg);
        if (!n.getArguments().isEmpty()) {
            this.printArguments(n.getArguments(), arg);
        }
        if (!n.getClassBody().isEmpty()) {
            this.printer.println(" {");
            this.printer.indent();
            this.printMembers(n.getClassBody(), arg);
            this.printer.unindent();
            this.printer.println("}");
        }
    }

    @Override
    public void visit(InitializerDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.isStatic()) {
            this.printer.print("static ");
        }
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(IfStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("if (");
        n.getCondition().accept(this, arg);
        boolean thenBlock = n.getThenStmt() instanceof BlockStmt;
        if (thenBlock) {
            this.printer.print(") ");
        } else {
            this.printer.println(")");
            this.printer.indent();
        }
        n.getThenStmt().accept(this, arg);
        if (!thenBlock) {
            this.printer.unindent();
        }
        if (n.getElseStmt().isPresent()) {
            if (thenBlock) {
                this.printer.print(" ");
            } else {
                this.printer.println();
            }
            boolean elseIf = n.getElseStmt().orElse(null) instanceof IfStmt;
            boolean elseBlock = n.getElseStmt().orElse(null) instanceof BlockStmt;
            if (elseIf || elseBlock) {
                this.printer.print("else ");
            } else {
                this.printer.println("else");
                this.printer.indent();
            }
            if (n.getElseStmt().isPresent()) {
                n.getElseStmt().get().accept(this, arg);
            }
            if (!elseIf && !elseBlock) {
                this.printer.unindent();
            }
        }
    }

    @Override
    public void visit(WhileStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("while (");
        n.getCondition().accept(this, arg);
        this.printer.print(") ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(ContinueStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("continue");
        n.getLabel().ifPresent(l -> this.printer.print(" ").print(l.getIdentifier()));
        this.printer.print(";");
    }

    @Override
    public void visit(DoStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("do ");
        n.getBody().accept(this, arg);
        this.printer.print(" while (");
        n.getCondition().accept(this, arg);
        this.printer.print(");");
    }

    @Override
    public void visit(ForEachStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("for (");
        n.getVariable().accept(this, arg);
        this.printer.print(" : ");
        n.getIterable().accept(this, arg);
        this.printer.print(") ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(ForStmt n, Void arg) {
        Expression e;
        Iterator<Expression> i;
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("for (");
        if (n.getInitialization() != null) {
            i = n.getInitialization().iterator();
            while (i.hasNext()) {
                e = i.next();
                e.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print("; ");
        if (n.getCompare().isPresent()) {
            n.getCompare().get().accept(this, arg);
        }
        this.printer.print("; ");
        if (n.getUpdate() != null) {
            i = n.getUpdate().iterator();
            while (i.hasNext()) {
                e = i.next();
                e.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print(") ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(ThrowStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("throw ");
        n.getExpression().accept(this, arg);
        this.printer.print(";");
    }

    @Override
    public void visit(SynchronizedStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("synchronized (");
        n.getExpression().accept(this, arg);
        this.printer.print(") ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(TryStmt n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("try ");
        if (!n.getResources().isEmpty()) {
            this.printer.print("(");
            Iterator<Expression> resources = n.getResources().iterator();
            boolean first = true;
            while (resources.hasNext()) {
                resources.next().accept(this, arg);
                if (resources.hasNext()) {
                    this.printer.print(";");
                    this.printer.println();
                    if (first) {
                        this.printer.indent();
                    }
                }
                first = false;
            }
            if (n.getResources().size() > 1) {
                this.printer.unindent();
            }
            this.printer.print(") ");
        }
        n.getTryBlock().accept(this, arg);
        for (CatchClause c : n.getCatchClauses()) {
            c.accept(this, arg);
        }
        if (n.getFinallyBlock().isPresent()) {
            this.printer.print(" finally ");
            n.getFinallyBlock().get().accept(this, arg);
        }
    }

    @Override
    public void visit(CatchClause n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print(" catch (");
        n.getParameter().accept(this, arg);
        this.printer.print(") ");
        n.getBody().accept(this, arg);
    }

    @Override
    public void visit(AnnotationDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        this.printer.print("@interface ");
        n.getName().accept(this, arg);
        this.printer.println(" {");
        this.printer.indent();
        if (n.getMembers() != null) {
            this.printMembers(n.getMembers(), arg);
        }
        this.printer.unindent();
        this.printer.print("}");
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printMemberAnnotations(n.getAnnotations(), arg);
        this.printModifiers(n.getModifiers());
        n.getType().accept(this, arg);
        this.printer.print(" ");
        n.getName().accept(this, arg);
        this.printer.print("()");
        if (n.getDefaultValue().isPresent()) {
            this.printer.print(" default ");
            n.getDefaultValue().get().accept(this, arg);
        }
        this.printer.print(";");
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("@");
        n.getName().accept(this, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("@");
        n.getName().accept(this, arg);
        this.printer.print("(");
        n.getMemberValue().accept(this, arg);
        this.printer.print(")");
    }

    @Override
    public void visit(NormalAnnotationExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("@");
        n.getName().accept(this, arg);
        this.printer.print("(");
        if (n.getPairs() != null) {
            Iterator<MemberValuePair> i = n.getPairs().iterator();
            while (i.hasNext()) {
                MemberValuePair m = i.next();
                m.accept(this, arg);
                if (!i.hasNext()) continue;
                this.printer.print(", ");
            }
        }
        this.printer.print(")");
    }

    @Override
    public void visit(MemberValuePair n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        n.getName().accept(this, arg);
        this.printer.print(" = ");
        n.getValue().accept(this, arg);
    }

    @Override
    public void visit(LineComment n, Void arg) {
        if (!this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS).isPresent()) {
            return;
        }
        this.printer.print("// ").println(Utils.normalizeEolInTextBlock(n.getContent(), "").trim());
    }

    @Override
    public void visit(BlockComment n, Void arg) {
        if (!this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS).isPresent()) {
            return;
        }
        String commentContent = Utils.normalizeEolInTextBlock(n.getContent(), this.getOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER).get().asString());
        String[] lines = commentContent.split("\\R", -1);
        this.printer.print("/*");
        for (int i = 0; i < lines.length - 1; ++i) {
            this.printer.print(lines[i]);
            this.printer.print((String)this.getOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER).get().asValue());
        }
        this.printer.print(lines[lines.length - 1]);
        this.printer.println("*/");
    }

    @Override
    public void visit(LambdaExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        NodeList<Parameter> parameters = n.getParameters();
        boolean printPar = n.isEnclosingParameters();
        if (printPar) {
            this.printer.print("(");
        }
        Iterator<Parameter> i = parameters.iterator();
        while (i.hasNext()) {
            Parameter p = i.next();
            p.accept(this, arg);
            if (!i.hasNext()) continue;
            this.printer.print(", ");
        }
        if (printPar) {
            this.printer.print(")");
        }
        this.printer.print(" -> ");
        Statement body = n.getBody();
        if (body instanceof ExpressionStmt) {
            ((ExpressionStmt)body).getExpression().accept(this, arg);
        } else {
            body.accept(this, arg);
        }
    }

    @Override
    public void visit(MethodReferenceExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        Expression scope = n.getScope();
        String identifier = n.getIdentifier();
        if (scope != null) {
            n.getScope().accept(this, arg);
        }
        this.printer.print("::");
        this.printTypeArgs(n, arg);
        if (identifier != null) {
            this.printer.print(identifier);
        }
    }

    @Override
    public void visit(TypeExpr n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        if (n.getType() != null) {
            n.getType().accept(this, arg);
        }
    }

    @Override
    public void visit(NodeList n, Void arg) {
        if (this.getOption(DefaultPrinterConfiguration.ConfigOption.ORDER_IMPORTS).isPresent() && n.size() > 0 && n.get(0) instanceof ImportDeclaration) {
            NodeList<ImportDeclaration> modifiableList = new NodeList<ImportDeclaration>(n);
            modifiableList.sort(Comparator.comparingInt(i -> i.isStatic() ? 0 : 1).thenComparing(NodeWithName::getNameAsString));
            for (Object n2 : modifiableList) {
                ((Node)n2).accept(this, arg);
            }
        } else {
            for (Object node : n) {
                ((Node)node).accept(this, arg);
            }
        }
    }

    @Override
    public void visit(ImportDeclaration n, Void arg) {
        this.printOrphanCommentsBeforeThisChildNode(n);
        this.printComment(n.getComment(), arg);
        this.printer.print("import ");
        if (n.isStatic()) {
            this.printer.print("static ");
        }
        n.getName().accept(this, arg);
        if (n.isAsterisk()) {
            this.printer.print(".*");
        }
        this.printer.println(";");
        this.printOrphanCommentsEnding(n);
    }

    @Override
    public void visit(ModuleDeclaration n, Void arg) {
        this.printMemberAnnotations(n.getAnnotations(), arg);
        if (n.isOpen()) {
            this.printer.print("open ");
        }
        this.printer.print("module ");
        n.getName().accept(this, arg);
        this.printer.println(" {").indent();
        n.getDirectives().accept(this, arg);
        this.printer.unindent().println("}");
    }

    @Override
    public void visit(ModuleRequiresDirective n, Void arg) {
        this.printer.print("requires ");
        this.printModifiers(n.getModifiers());
        n.getName().accept(this, arg);
        this.printer.println(";");
    }

    @Override
    public void visit(ModuleExportsDirective n, Void arg) {
        this.printer.print("exports ");
        n.getName().accept(this, arg);
        this.printPrePostFixOptionalList(n.getModuleNames(), arg, " to ", ", ", "");
        this.printer.println(";");
    }

    @Override
    public void visit(ModuleProvidesDirective n, Void arg) {
        this.printer.print("provides ");
        n.getName().accept(this, arg);
        this.printPrePostFixRequiredList(n.getWith(), arg, " with ", ", ", "");
        this.printer.println(";");
    }

    @Override
    public void visit(ModuleUsesDirective n, Void arg) {
        this.printer.print("uses ");
        n.getName().accept(this, arg);
        this.printer.println(";");
    }

    @Override
    public void visit(ModuleOpensDirective n, Void arg) {
        this.printer.print("opens ");
        n.getName().accept(this, arg);
        this.printPrePostFixOptionalList(n.getModuleNames(), arg, " to ", ", ", "");
        this.printer.println(";");
    }

    @Override
    public void visit(UnparsableStmt n, Void arg) {
        this.printer.print("???;");
    }

    private void printOrphanCommentsBeforeThisChildNode(Node node) {
        int i;
        if (!this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS).isPresent()) {
            return;
        }
        if (node instanceof Comment) {
            return;
        }
        Node parent = node.getParentNode().orElse(null);
        if (parent == null) {
            return;
        }
        ArrayList<Node> everything = new ArrayList<Node>(parent.getChildNodes());
        PositionUtils.sortByBeginPosition(everything);
        int positionOfTheChild = -1;
        for (int i2 = 0; i2 < everything.size(); ++i2) {
            if (everything.get(i2) != node) continue;
            positionOfTheChild = i2;
            break;
        }
        if (positionOfTheChild == -1) {
            throw new AssertionError((Object)"I am not a child of my parent.");
        }
        int positionOfPreviousChild = -1;
        for (i = positionOfTheChild - 1; i >= 0 && positionOfPreviousChild == -1; --i) {
            if (everything.get(i) instanceof Comment) continue;
            positionOfPreviousChild = i;
        }
        for (i = positionOfPreviousChild + 1; i < positionOfTheChild; ++i) {
            Node nodeToPrint = (Node)everything.get(i);
            if (!(nodeToPrint instanceof Comment)) {
                throw new RuntimeException("Expected comment, instead " + nodeToPrint.getClass() + ". Position of previous child: " + positionOfPreviousChild + ", position of child " + positionOfTheChild);
            }
            nodeToPrint.accept(this, null);
        }
    }

    private void printOrphanCommentsEnding(Node node) {
        if (!this.getOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS).isPresent()) {
            return;
        }
        List everything = node.getChildNodes().stream().filter(n -> n.getRange().isPresent()).collect(Collectors.toList());
        PositionUtils.sortByBeginPosition(everything);
        if (everything.isEmpty()) {
            return;
        }
        int commentsAtEnd = 0;
        boolean findingComments = true;
        while (findingComments && commentsAtEnd < everything.size()) {
            Node last = (Node)everything.get(everything.size() - 1 - commentsAtEnd);
            findingComments = last instanceof Comment;
            if (!findingComments) continue;
            ++commentsAtEnd;
        }
        for (int i = 0; i < commentsAtEnd; ++i) {
            ((Node)everything.get(everything.size() - commentsAtEnd + i)).accept(this, null);
        }
    }

    private void indentIf(boolean expr) {
        if (expr) {
            this.printer.indent();
        }
    }

    private void unindentIf(boolean expr) {
        if (expr) {
            this.printer.unindent();
        }
    }

    private Optional<ConfigurationOption> getOption(DefaultPrinterConfiguration.ConfigOption cOption) {
        return this.configuration.get(new DefaultConfigurationOption(cOption));
    }
}

