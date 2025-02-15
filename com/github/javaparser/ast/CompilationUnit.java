/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.Position;
import com.github.javaparser.Providers;
import com.github.javaparser.Range;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.CompilationUnitMetaModel;
import com.github.javaparser.metamodel.InternalProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.utils.ClassUtils;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompilationUnit
extends Node {
    private static final String JAVA_LANG = "java.lang";
    @OptionalProperty
    private PackageDeclaration packageDeclaration;
    private NodeList<ImportDeclaration> imports;
    private NodeList<TypeDeclaration<?>> types;
    @OptionalProperty
    private ModuleDeclaration module;
    @InternalProperty
    private Storage storage;

    public CompilationUnit() {
        this(null, null, new NodeList<ImportDeclaration>(), new NodeList(), null);
    }

    public CompilationUnit(String packageDeclaration) {
        this(null, new PackageDeclaration(new Name(packageDeclaration)), new NodeList<ImportDeclaration>(), new NodeList(), null);
    }

    @AllFieldsConstructor
    public CompilationUnit(PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration<?>> types, ModuleDeclaration module) {
        this(null, packageDeclaration, imports, types, module);
    }

    public CompilationUnit(TokenRange tokenRange, PackageDeclaration packageDeclaration, NodeList<ImportDeclaration> imports, NodeList<TypeDeclaration<?>> types, ModuleDeclaration module) {
        super(tokenRange);
        this.setPackageDeclaration(packageDeclaration);
        this.setImports(imports);
        this.setTypes(types);
        this.setModule(module);
        this.customInitialization();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public CompilationUnit printer(Printer printer) {
        this.setData(PRINTER_KEY, printer);
        return this;
    }

    @Override
    protected Printer getPrinter() {
        if (!this.containsData(PRINTER_KEY)) {
            Printer printer = this.createDefaultPrinter();
            this.printer(printer);
        }
        return (Printer)this.getData(PRINTER_KEY);
    }

    @Override
    protected Printer getPrinter(PrinterConfiguration config) {
        Printer printer = this.getPrinter().setConfiguration(config);
        this.printer(printer);
        return printer;
    }

    @Deprecated
    public List<Comment> getComments() {
        List<Comment> comments = this.getAllContainedComments();
        this.getComment().ifPresent(comments::add);
        return comments;
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = this.getAllContainedComments();
        this.getComment().ifPresent(comments::add);
        return comments;
    }

    public NodeList<ImportDeclaration> getImports() {
        return this.imports;
    }

    public ImportDeclaration getImport(int i) {
        return (ImportDeclaration)this.getImports().get(i);
    }

    public Optional<PackageDeclaration> getPackageDeclaration() {
        return Optional.ofNullable(this.packageDeclaration);
    }

    public NodeList<TypeDeclaration<?>> getTypes() {
        return this.types;
    }

    public TypeDeclaration<?> getType(int i) {
        return (TypeDeclaration)this.getTypes().get(i);
    }

    public CompilationUnit setImports(NodeList<ImportDeclaration> imports) {
        Utils.assertNotNull(imports);
        if (imports == this.imports) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.IMPORTS, this.imports, imports);
        if (this.imports != null) {
            this.imports.setParentNode(null);
        }
        this.imports = imports;
        this.setAsParentNodeOf(imports);
        return this;
    }

    public CompilationUnit setImport(int i, ImportDeclaration imports) {
        this.getImports().set(i, imports);
        return this;
    }

    public CompilationUnit addImport(ImportDeclaration importDeclaration) {
        if (importDeclaration.isAsterisk()) {
            this.getImports().removeIf(im -> Objects.equals(CompilationUnit.getImportPackageName(im).get(), CompilationUnit.getImportPackageName(importDeclaration).orElse(null)));
        }
        if (!this.isImplicitImport(importDeclaration) && this.getImports().stream().noneMatch(im -> im.equals(importDeclaration) || im.isAsterisk() && Objects.equals(CompilationUnit.getImportPackageName(im).get(), CompilationUnit.getImportPackageName(importDeclaration).orElse(null)))) {
            this.getImports().add(importDeclaration);
        }
        return this;
    }

    private boolean isImplicitImport(ImportDeclaration importDeclaration) {
        Optional<Name> importPackageName = CompilationUnit.getImportPackageName(importDeclaration);
        if (importPackageName.isPresent()) {
            if (StaticJavaParser.parseName(JAVA_LANG).equals(importPackageName.get())) {
                return true;
            }
            if (this.packageDeclaration != null) {
                Name currentPackageName = this.packageDeclaration.getName();
                return currentPackageName.equals(importPackageName.get());
            }
            return false;
        }
        return true;
    }

    private static Optional<Name> getImportPackageName(ImportDeclaration importDeclaration) {
        return (importDeclaration.isAsterisk() ? new Name(importDeclaration.getName(), "*") : importDeclaration.getName()).getQualifier();
    }

    public CompilationUnit setPackageDeclaration(PackageDeclaration packageDeclaration) {
        if (packageDeclaration == this.packageDeclaration) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.PACKAGE_DECLARATION, this.packageDeclaration, packageDeclaration);
        if (this.packageDeclaration != null) {
            this.packageDeclaration.setParentNode(null);
        }
        this.packageDeclaration = packageDeclaration;
        this.setAsParentNodeOf(packageDeclaration);
        return this;
    }

    public CompilationUnit setTypes(NodeList<TypeDeclaration<?>> types) {
        Utils.assertNotNull(types);
        if (types == this.types) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPES, this.types, types);
        if (this.types != null) {
            this.types.setParentNode(null);
        }
        this.types = types;
        this.setAsParentNodeOf(types);
        return this;
    }

    public CompilationUnit setType(int i, TypeDeclaration<?> type) {
        NodeList copy = new NodeList();
        copy.addAll(this.getTypes());
        this.getTypes().set(i, type);
        this.notifyPropertyChange(ObservableProperty.TYPES, copy, this.types);
        return this;
    }

    public CompilationUnit addType(TypeDeclaration<?> type) {
        NodeList copy = new NodeList();
        copy.addAll(this.getTypes());
        this.getTypes().add(type);
        this.notifyPropertyChange(ObservableProperty.TYPES, copy, this.types);
        return this;
    }

    public CompilationUnit setPackageDeclaration(String name) {
        this.setPackageDeclaration(new PackageDeclaration(StaticJavaParser.parseName(name)));
        return this;
    }

    public CompilationUnit addImport(String name) {
        return this.addImport(name, false, false);
    }

    public CompilationUnit addImport(Class<?> clazz) {
        if (clazz.isArray()) {
            return this.addImport(clazz.getComponentType());
        }
        if (ClassUtils.isPrimitiveOrWrapper(clazz) || JAVA_LANG.equals(clazz.getPackage().getName())) {
            return this;
        }
        if (clazz.isAnonymousClass() || clazz.isLocalClass()) {
            throw new IllegalArgumentException(clazz.getName() + " is an anonymous or local class therefore it can't be added with addImport");
        }
        return this.addImport(clazz.getCanonicalName());
    }

    public CompilationUnit addImport(String name, boolean isStatic, boolean isAsterisk) {
        if (name == null) {
            return this;
        }
        StringBuilder i = new StringBuilder("import ");
        if (isStatic) {
            i.append("static ");
        }
        i.append(name);
        if (isAsterisk) {
            i.append(".*");
        }
        i.append(";");
        return this.addImport(StaticJavaParser.parseImport(i.toString()));
    }

    public ClassOrInterfaceDeclaration addClass(String name) {
        return this.addClass(name, Modifier.Keyword.PUBLIC);
    }

    public ClassOrInterfaceDeclaration addClass(String name, Modifier.Keyword ... modifiers) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration(Modifier.createModifierList(modifiers), false, name);
        this.getTypes().add(classOrInterfaceDeclaration);
        return classOrInterfaceDeclaration;
    }

    public ClassOrInterfaceDeclaration addInterface(String name) {
        return this.addInterface(name, Modifier.Keyword.PUBLIC);
    }

    public ClassOrInterfaceDeclaration addInterface(String name, Modifier.Keyword ... modifiers) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration(Modifier.createModifierList(modifiers), true, name);
        this.getTypes().add(classOrInterfaceDeclaration);
        return classOrInterfaceDeclaration;
    }

    public EnumDeclaration addEnum(String name) {
        return this.addEnum(name, Modifier.Keyword.PUBLIC);
    }

    public EnumDeclaration addEnum(String name, Modifier.Keyword ... modifiers) {
        EnumDeclaration enumDeclaration = new EnumDeclaration(Modifier.createModifierList(modifiers), name);
        this.getTypes().add(enumDeclaration);
        return enumDeclaration;
    }

    public AnnotationDeclaration addAnnotationDeclaration(String name) {
        return this.addAnnotationDeclaration(name, Modifier.Keyword.PUBLIC);
    }

    public AnnotationDeclaration addAnnotationDeclaration(String name, Modifier.Keyword ... modifiers) {
        AnnotationDeclaration annotationDeclaration = new AnnotationDeclaration(Modifier.createModifierList(modifiers), name);
        this.getTypes().add(annotationDeclaration);
        return annotationDeclaration;
    }

    public Optional<ClassOrInterfaceDeclaration> getClassByName(String className) {
        return this.getTypes().stream().filter(type -> type.getNameAsString().equals(className) && type instanceof ClassOrInterfaceDeclaration && !((ClassOrInterfaceDeclaration)type).isInterface()).findFirst().map(t -> (ClassOrInterfaceDeclaration)t);
    }

    public List<ClassOrInterfaceDeclaration> getLocalDeclarationFromClassname(String className) {
        return this.findAll(ClassOrInterfaceDeclaration.class).stream().filter(cid -> cid.getFullyQualifiedName().get().endsWith(className)).collect(Collectors.toList());
    }

    public Optional<ClassOrInterfaceDeclaration> getInterfaceByName(String interfaceName) {
        return this.getTypes().stream().filter(type -> type.getNameAsString().equals(interfaceName) && type instanceof ClassOrInterfaceDeclaration && ((ClassOrInterfaceDeclaration)type).isInterface()).findFirst().map(t -> (ClassOrInterfaceDeclaration)t);
    }

    public Optional<EnumDeclaration> getEnumByName(String enumName) {
        return this.getTypes().stream().filter(type -> type.getNameAsString().equals(enumName) && type instanceof EnumDeclaration).findFirst().map(t -> (EnumDeclaration)t);
    }

    public Optional<String> getPrimaryTypeName() {
        return this.getStorage().map(Storage::getFileName).map(Utils::removeFileExtension);
    }

    public Optional<TypeDeclaration<?>> getPrimaryType() {
        return this.getPrimaryTypeName().flatMap(name -> this.getTypes().stream().filter(t -> t.getNameAsString().equals(name)).findFirst());
    }

    public Optional<AnnotationDeclaration> getAnnotationDeclarationByName(String annotationName) {
        return this.getTypes().stream().filter(type -> type.getNameAsString().equals(annotationName) && type instanceof AnnotationDeclaration).findFirst().map(t -> (AnnotationDeclaration)t);
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.imports.size(); ++i) {
            if (this.imports.get(i) != node) continue;
            this.imports.remove(i);
            return true;
        }
        if (this.module != null && node == this.module) {
            this.removeModule();
            return true;
        }
        if (this.packageDeclaration != null && node == this.packageDeclaration) {
            this.removePackageDeclaration();
            return true;
        }
        for (i = 0; i < this.types.size(); ++i) {
            if (this.types.get(i) != node) continue;
            this.types.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public CompilationUnit removePackageDeclaration() {
        return this.setPackageDeclaration((PackageDeclaration)null);
    }

    public Optional<ModuleDeclaration> getModule() {
        return Optional.ofNullable(this.module);
    }

    public CompilationUnit setModule(ModuleDeclaration module) {
        if (module == this.module) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MODULE, this.module, module);
        if (this.module != null) {
            this.module.setParentNode(null);
        }
        this.module = module;
        this.setAsParentNodeOf(module);
        return this;
    }

    public CompilationUnit removeModule() {
        return this.setModule((ModuleDeclaration)null);
    }

    public Optional<Storage> getStorage() {
        return Optional.ofNullable(this.storage);
    }

    public CompilationUnit setStorage(Path path) {
        this.storage = new Storage(this, path);
        return this;
    }

    public CompilationUnit setStorage(Path path, Charset charset) {
        this.storage = new Storage(this, path, charset);
        return this;
    }

    public ModuleDeclaration setModule(String name) {
        ModuleDeclaration module = new ModuleDeclaration(StaticJavaParser.parseName(name), false);
        this.setModule(module);
        return module;
    }

    public void recalculatePositions() {
        if (!this.getTokenRange().isPresent()) {
            throw new IllegalStateException("Can't recalculate positions without tokens.");
        }
        Position cursor = Position.HOME;
        for (JavaToken t : this.getTokenRange().get()) {
            int tokenLength = t.getKind() == JavaToken.Kind.EOF.getKind() ? 0 : t.getText().length() - 1;
            t.setRange(Range.range(cursor, cursor.right(tokenLength)));
            if (t.getCategory().isEndOfLine()) {
                cursor = cursor.nextLine();
                continue;
            }
            cursor = cursor.right(tokenLength + 1);
        }
    }

    @Override
    public CompilationUnit clone() {
        return (CompilationUnit)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CompilationUnitMetaModel getMetaModel() {
        return JavaParserMetaModel.compilationUnitMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.imports.size(); ++i) {
            if (this.imports.get(i) != node) continue;
            this.imports.set(i, (ImportDeclaration)replacementNode);
            return true;
        }
        if (this.module != null && node == this.module) {
            this.setModule((ModuleDeclaration)replacementNode);
            return true;
        }
        if (this.packageDeclaration != null && node == this.packageDeclaration) {
            this.setPackageDeclaration((PackageDeclaration)replacementNode);
            return true;
        }
        for (i = 0; i < this.types.size(); ++i) {
            if (this.types.get(i) != node) continue;
            this.types.set(i, (TypeDeclaration)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public static class Storage {
        private final CompilationUnit compilationUnit;
        private final Path path;
        private final Charset encoding;

        private Storage(CompilationUnit compilationUnit, Path path) {
            this(compilationUnit, path, Providers.UTF8);
        }

        private Storage(CompilationUnit compilationUnit, Path path, Charset encoding) {
            this.compilationUnit = compilationUnit;
            this.path = path.toAbsolutePath();
            this.encoding = encoding;
        }

        public Path getPath() {
            return this.path;
        }

        public CompilationUnit getCompilationUnit() {
            return this.compilationUnit;
        }

        public Charset getEncoding() {
            return this.encoding;
        }

        public Path getSourceRoot() {
            Optional<String> pkgAsString = this.compilationUnit.getPackageDeclaration().map(NodeWithName::getNameAsString);
            return pkgAsString.map(p -> Paths.get(CodeGenerationUtils.packageToPath(p), new String[0])).map(pkg -> CodeGenerationUtils.subtractPaths(this.getDirectory(), pkg)).orElse(this.getDirectory());
        }

        public String getFileName() {
            return this.path.getFileName().toString();
        }

        public Path getDirectory() {
            return this.path.getParent();
        }

        public void save() {
            this.save(cu -> this.compilationUnit.getPrinter().print((Node)cu));
        }

        public void save(Function<CompilationUnit, String> makeOutput) {
            this.save(makeOutput, this.encoding);
        }

        public void save(Function<CompilationUnit, String> makeOutput, Charset encoding) {
            try {
                Files.createDirectories(this.path.getParent(), new FileAttribute[0]);
                String code = makeOutput.apply(this.getCompilationUnit());
                Files.write(this.path, code.getBytes(encoding), new OpenOption[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public ParseResult<CompilationUnit> reparse(JavaParser javaParser) {
            try {
                return javaParser.parse(ParseStart.COMPILATION_UNIT, Providers.provider(this.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

