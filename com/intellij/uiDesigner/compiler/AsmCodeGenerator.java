/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.ClassAdapter
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.FieldVisitor
 *  org.objectweb.asm.Label
 *  org.objectweb.asm.MethodAdapter
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.EmptyVisitor
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.CodeGenerationException;
import com.intellij.uiDesigner.compiler.ColorPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.DimensionPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.EnumPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.FlowLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.FontPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.FormErrorInfo;
import com.intellij.uiDesigner.compiler.FormLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.GridBagLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.GridLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.IconPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.InsetsPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.LayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.ListModelPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.NestedFormLoader;
import com.intellij.uiDesigner.compiler.PropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.RectanglePropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.RecursiveFormNestingException;
import com.intellij.uiDesigner.compiler.ScrollPaneLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.SimpleLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.SplitPaneLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.StringPropertyCodeGenerator;
import com.intellij.uiDesigner.compiler.TabbedPaneLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.ToolBarLayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.Utils;
import com.intellij.uiDesigner.lw.FontDescriptor;
import com.intellij.uiDesigner.lw.IButtonGroup;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwIntroComponentProperty;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwNestedForm;
import com.intellij.uiDesigner.lw.LwRootContainer;
import com.intellij.uiDesigner.lw.StringDescriptor;
import com.intellij.uiDesigner.shared.BorderType;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.EmptyVisitor;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class AsmCodeGenerator {
    private LwRootContainer myRootContainer;
    private ClassLoader myLoader;
    private ArrayList myErrors;
    private ArrayList myWarnings;
    private Map myIdToLocalMap = new HashMap();
    private static final String CONSTRUCTOR_NAME = "<init>";
    private String myClassToBind;
    private byte[] myPatchedData;
    private static Map myContainerLayoutCodeGenerators = new HashMap();
    private static Map myComponentLayoutCodeGenerators = new HashMap();
    private static Map myPropertyCodeGenerators = new HashMap();
    public static final String SETUP_METHOD_NAME = "$$$setupUI$$$";
    public static final String GET_ROOT_COMPONENT_METHOD_NAME = "$$$getRootComponent$$$";
    public static final String CREATE_COMPONENTS_METHOD_NAME = "createUIComponents";
    public static final String LOAD_LABEL_TEXT_METHOD = "$$$loadLabelText$$$";
    public static final String LOAD_BUTTON_TEXT_METHOD = "$$$loadButtonText$$$";
    private static final Type ourButtonGroupType = Type.getType((Class)(class$javax$swing$ButtonGroup == null ? (class$javax$swing$ButtonGroup = AsmCodeGenerator.class$("javax.swing.ButtonGroup")) : class$javax$swing$ButtonGroup));
    private static final Type ourBorderFactoryType = Type.getType((Class)(class$javax$swing$BorderFactory == null ? (class$javax$swing$BorderFactory = AsmCodeGenerator.class$("javax.swing.BorderFactory")) : class$javax$swing$BorderFactory));
    private static final Type ourBorderType = Type.getType((Class)(class$javax$swing$border$Border == null ? (class$javax$swing$border$Border = AsmCodeGenerator.class$("javax.swing.border.Border")) : class$javax$swing$border$Border));
    private static final Method ourCreateTitledBorderMethod = Method.getMethod((String)"javax.swing.border.TitledBorder createTitledBorder(javax.swing.border.Border,java.lang.String,int,int,java.awt.Font,java.awt.Color)");
    private NestedFormLoader myFormLoader;
    private final boolean myIgnoreCustomCreation;
    private final ClassWriter myClassWriter;
    static /* synthetic */ Class class$javax$swing$ButtonGroup;
    static /* synthetic */ Class class$javax$swing$BorderFactory;
    static /* synthetic */ Class class$javax$swing$border$Border;
    static /* synthetic */ Class class$java$awt$BorderLayout;
    static /* synthetic */ Class class$java$awt$CardLayout;
    static /* synthetic */ Class class$com$intellij$uiDesigner$lw$LwSplitPane;
    static /* synthetic */ Class class$com$intellij$uiDesigner$lw$LwTabbedPane;
    static /* synthetic */ Class class$com$intellij$uiDesigner$lw$LwScrollPane;
    static /* synthetic */ Class class$com$intellij$uiDesigner$lw$LwToolBar;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$awt$Dimension;
    static /* synthetic */ Class class$java$awt$Insets;
    static /* synthetic */ Class class$java$awt$Rectangle;
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$Font;
    static /* synthetic */ Class class$javax$swing$Icon;
    static /* synthetic */ Class class$javax$swing$ListModel;
    static /* synthetic */ Class class$javax$swing$DefaultListModel;
    static /* synthetic */ Class class$javax$swing$ComboBoxModel;
    static /* synthetic */ Class class$javax$swing$DefaultComboBoxModel;
    static /* synthetic */ Class class$javax$swing$JComponent;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Object;

    public AsmCodeGenerator(LwRootContainer rootContainer, ClassLoader loader, NestedFormLoader formLoader, boolean ignoreCustomCreation, ClassWriter classWriter) {
        this.myFormLoader = formLoader;
        this.myIgnoreCustomCreation = ignoreCustomCreation;
        if (loader == null) {
            throw new IllegalArgumentException("loader cannot be null");
        }
        if (rootContainer == null) {
            throw new IllegalArgumentException("rootContainer cannot be null");
        }
        this.myRootContainer = rootContainer;
        this.myLoader = loader;
        this.myErrors = new ArrayList();
        this.myWarnings = new ArrayList();
        this.myClassWriter = classWriter;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void patchFile(File classFile) {
        if (!classFile.exists()) {
            this.myErrors.add(new FormErrorInfo(null, "Class to bind does not exist: " + this.myRootContainer.getClassToBind()));
            return;
        }
        try {
            byte[] patchedData;
            FileInputStream fis = new FileInputStream(classFile);
            try {
                patchedData = this.patchClass(fis);
                if (patchedData == null) {
                    return;
                }
            } finally {
                fis.close();
            }
            FileOutputStream fos = new FileOutputStream(classFile);
            try {
                fos.write(patchedData);
            } finally {
                fos.close();
            }
        } catch (IOException e) {
            this.myErrors.add(new FormErrorInfo(null, "Cannot read or write class file " + classFile.getPath() + ": " + e.toString()));
        } catch (IllegalStateException e) {
            this.myErrors.add(new FormErrorInfo(null, "Unexpected data in form file when patching class " + classFile.getPath() + ": " + e.toString()));
        }
    }

    public byte[] patchClass(InputStream classStream) {
        ClassReader reader;
        this.myClassToBind = this.myRootContainer.getClassToBind();
        if (this.myClassToBind == null) {
            this.myWarnings.add(new FormErrorInfo(null, "No class to bind specified"));
            return null;
        }
        if (this.myRootContainer.getComponentCount() != 1) {
            this.myErrors.add(new FormErrorInfo(null, "There should be only one component at the top level"));
            return null;
        }
        String nonEmptyPanel = Utils.findNotEmptyPanelWithXYLayout(this.myRootContainer.getComponent(0));
        if (nonEmptyPanel != null) {
            this.myErrors.add(new FormErrorInfo(nonEmptyPanel, "There are non empty panels with XY layout. Please lay them out in a grid."));
            return null;
        }
        try {
            reader = new ClassReader(classStream);
        } catch (IOException e) {
            this.myErrors.add(new FormErrorInfo(null, "Error reading class data stream"));
            return null;
        }
        FirstPassClassVisitor visitor = new FirstPassClassVisitor();
        reader.accept((ClassVisitor)visitor, 0);
        reader.accept((ClassVisitor)new FormClassVisitor((ClassVisitor)this.myClassWriter, visitor.isExplicitSetupCall()), 0);
        this.myPatchedData = this.myClassWriter.toByteArray();
        return this.myPatchedData;
    }

    public FormErrorInfo[] getErrors() {
        return this.myErrors.toArray(new FormErrorInfo[this.myErrors.size()]);
    }

    public FormErrorInfo[] getWarnings() {
        return this.myWarnings.toArray(new FormErrorInfo[this.myWarnings.size()]);
    }

    public byte[] getPatchedData() {
        return this.myPatchedData;
    }

    static void pushPropValue(GeneratorAdapter generator, String propertyClass, Object value) {
        PropertyCodeGenerator codeGen = (PropertyCodeGenerator)myPropertyCodeGenerators.get(propertyClass);
        if (codeGen == null) {
            throw new RuntimeException("Unknown property class " + propertyClass);
        }
        codeGen.generatePushValue(generator, value);
    }

    static Class getComponentClass(String className, ClassLoader classLoader) throws CodeGenerationException {
        try {
            return Class.forName(className, false, classLoader);
        } catch (ClassNotFoundException e) {
            throw new CodeGenerationException(null, "Class not found: " + className);
        } catch (UnsupportedClassVersionError e) {
            throw new CodeGenerationException(null, "Unsupported class version error: " + className);
        }
    }

    public static Type typeFromClassName(String className) {
        return Type.getType((String)("L" + className.replace('.', '/') + ";"));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    static {
        myContainerLayoutCodeGenerators.put("GridLayoutManager", new GridLayoutCodeGenerator());
        myContainerLayoutCodeGenerators.put("GridBagLayout", new GridBagLayoutCodeGenerator());
        myContainerLayoutCodeGenerators.put("BorderLayout", new SimpleLayoutCodeGenerator(Type.getType((Class)(class$java$awt$BorderLayout == null ? (class$java$awt$BorderLayout = AsmCodeGenerator.class$("java.awt.BorderLayout")) : class$java$awt$BorderLayout))));
        myContainerLayoutCodeGenerators.put("CardLayout", new SimpleLayoutCodeGenerator(Type.getType((Class)(class$java$awt$CardLayout == null ? (class$java$awt$CardLayout = AsmCodeGenerator.class$("java.awt.CardLayout")) : class$java$awt$CardLayout))));
        myContainerLayoutCodeGenerators.put("FlowLayout", new FlowLayoutCodeGenerator());
        myComponentLayoutCodeGenerators.put(class$com$intellij$uiDesigner$lw$LwSplitPane == null ? (class$com$intellij$uiDesigner$lw$LwSplitPane = AsmCodeGenerator.class$("com.intellij.uiDesigner.lw.LwSplitPane")) : class$com$intellij$uiDesigner$lw$LwSplitPane, new SplitPaneLayoutCodeGenerator());
        myComponentLayoutCodeGenerators.put(class$com$intellij$uiDesigner$lw$LwTabbedPane == null ? (class$com$intellij$uiDesigner$lw$LwTabbedPane = AsmCodeGenerator.class$("com.intellij.uiDesigner.lw.LwTabbedPane")) : class$com$intellij$uiDesigner$lw$LwTabbedPane, new TabbedPaneLayoutCodeGenerator());
        myComponentLayoutCodeGenerators.put(class$com$intellij$uiDesigner$lw$LwScrollPane == null ? (class$com$intellij$uiDesigner$lw$LwScrollPane = AsmCodeGenerator.class$("com.intellij.uiDesigner.lw.LwScrollPane")) : class$com$intellij$uiDesigner$lw$LwScrollPane, new ScrollPaneLayoutCodeGenerator());
        myComponentLayoutCodeGenerators.put(class$com$intellij$uiDesigner$lw$LwToolBar == null ? (class$com$intellij$uiDesigner$lw$LwToolBar = AsmCodeGenerator.class$("com.intellij.uiDesigner.lw.LwToolBar")) : class$com$intellij$uiDesigner$lw$LwToolBar, new ToolBarLayoutCodeGenerator());
        myPropertyCodeGenerators.put((class$java$lang$String == null ? (class$java$lang$String = AsmCodeGenerator.class$("java.lang.String")) : class$java$lang$String).getName(), new StringPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$java$awt$Dimension == null ? (class$java$awt$Dimension = AsmCodeGenerator.class$("java.awt.Dimension")) : class$java$awt$Dimension).getName(), new DimensionPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$java$awt$Insets == null ? (class$java$awt$Insets = AsmCodeGenerator.class$("java.awt.Insets")) : class$java$awt$Insets).getName(), new InsetsPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$java$awt$Rectangle == null ? (class$java$awt$Rectangle = AsmCodeGenerator.class$("java.awt.Rectangle")) : class$java$awt$Rectangle).getName(), new RectanglePropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$java$awt$Color == null ? (class$java$awt$Color = AsmCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color).getName(), new ColorPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$java$awt$Font == null ? (class$java$awt$Font = AsmCodeGenerator.class$("java.awt.Font")) : class$java$awt$Font).getName(), new FontPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$javax$swing$Icon == null ? (class$javax$swing$Icon = AsmCodeGenerator.class$("javax.swing.Icon")) : class$javax$swing$Icon).getName(), new IconPropertyCodeGenerator());
        myPropertyCodeGenerators.put((class$javax$swing$ListModel == null ? (class$javax$swing$ListModel = AsmCodeGenerator.class$("javax.swing.ListModel")) : class$javax$swing$ListModel).getName(), new ListModelPropertyCodeGenerator(class$javax$swing$DefaultListModel == null ? (class$javax$swing$DefaultListModel = AsmCodeGenerator.class$("javax.swing.DefaultListModel")) : class$javax$swing$DefaultListModel));
        myPropertyCodeGenerators.put((class$javax$swing$ComboBoxModel == null ? (class$javax$swing$ComboBoxModel = AsmCodeGenerator.class$("javax.swing.ComboBoxModel")) : class$javax$swing$ComboBoxModel).getName(), new ListModelPropertyCodeGenerator(class$javax$swing$DefaultComboBoxModel == null ? (class$javax$swing$DefaultComboBoxModel = AsmCodeGenerator.class$("javax.swing.DefaultComboBoxModel")) : class$javax$swing$DefaultComboBoxModel));
        myPropertyCodeGenerators.put("java.lang.Enum", new EnumPropertyCodeGenerator());
    }

    private static class FirstPassClassVisitor
    extends ClassAdapter {
        private boolean myExplicitSetupCall = false;

        public FirstPassClassVisitor() {
            super((ClassVisitor)new EmptyVisitor());
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals(AsmCodeGenerator.CONSTRUCTOR_NAME)) {
                return new FirstPassConstructorVisitor();
            }
            return null;
        }

        public boolean isExplicitSetupCall() {
            return this.myExplicitSetupCall;
        }

        private class FirstPassConstructorVisitor
        extends MethodAdapter {
            public FirstPassConstructorVisitor() {
                super((MethodVisitor)new EmptyVisitor());
            }

            public void visitMethodInsn(int opcode, String owner, String name, String desc) {
                if (name.equals(AsmCodeGenerator.SETUP_METHOD_NAME)) {
                    FirstPassClassVisitor.this.myExplicitSetupCall = true;
                }
            }
        }
    }

    private class FormConstructorVisitor
    extends MethodAdapter {
        private final String myClassName;
        private final String mySuperName;
        private boolean callsSelfConstructor = false;
        private boolean mySetupCalled = false;
        private boolean mySuperCalled = false;

        public FormConstructorVisitor(MethodVisitor mv, String className, String superName) {
            super(mv);
            this.myClassName = className;
            this.mySuperName = superName;
        }

        public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            if (opcode == 180 && !this.mySetupCalled && !this.callsSelfConstructor && Utils.isBoundField(AsmCodeGenerator.this.myRootContainer, name)) {
                this.callSetupUI();
            }
            super.visitFieldInsn(opcode, owner, name, desc);
        }

        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
            if (opcode == 183 && name.equals(AsmCodeGenerator.CONSTRUCTOR_NAME)) {
                if (owner.equals(this.myClassName)) {
                    this.callsSelfConstructor = true;
                } else if (owner.equals(this.mySuperName)) {
                    this.mySuperCalled = true;
                } else if (this.mySuperCalled) {
                    this.callSetupUI();
                }
            } else if (this.mySuperCalled) {
                this.callSetupUI();
            }
            super.visitMethodInsn(opcode, owner, name, desc);
        }

        public void visitJumpInsn(int opcode, Label label) {
            if (this.mySuperCalled) {
                this.callSetupUI();
            }
            super.visitJumpInsn(opcode, label);
        }

        private void callSetupUI() {
            if (!this.mySetupCalled) {
                this.mv.visitVarInsn(25, 0);
                this.mv.visitMethodInsn(183, this.myClassName, AsmCodeGenerator.SETUP_METHOD_NAME, "()V");
                this.mySetupCalled = true;
            }
        }

        public void visitInsn(int opcode) {
            if (opcode == 177 && !this.mySetupCalled && !this.callsSelfConstructor) {
                this.callSetupUI();
            }
            super.visitInsn(opcode);
        }
    }

    class FormClassVisitor
    extends ClassAdapter {
        private String myClassName;
        private String mySuperName;
        private Map myFieldDescMap = new HashMap();
        private Map myFieldAccessMap = new HashMap();
        private boolean myHaveCreateComponentsMethod = false;
        private int myCreateComponentsAccess;
        private final boolean myExplicitSetupCall;

        public FormClassVisitor(ClassVisitor cv, boolean explicitSetupCall) {
            super(cv);
            this.myExplicitSetupCall = explicitSetupCall;
        }

        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            this.myClassName = name;
            this.mySuperName = superName;
            Iterator iterator = myPropertyCodeGenerators.values().iterator();
            while (iterator.hasNext()) {
                PropertyCodeGenerator propertyCodeGenerator = (PropertyCodeGenerator)iterator.next();
                propertyCodeGenerator.generateClassStart(this, name, AsmCodeGenerator.this.myLoader);
            }
        }

        public String getClassName() {
            return this.myClassName;
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (name.equals(AsmCodeGenerator.SETUP_METHOD_NAME) || name.equals(AsmCodeGenerator.GET_ROOT_COMPONENT_METHOD_NAME) || name.equals(AsmCodeGenerator.LOAD_BUTTON_TEXT_METHOD) || name.equals(AsmCodeGenerator.LOAD_LABEL_TEXT_METHOD)) {
                return null;
            }
            if (name.equals(AsmCodeGenerator.CREATE_COMPONENTS_METHOD_NAME) && desc.equals("()V")) {
                this.myHaveCreateComponentsMethod = true;
                this.myCreateComponentsAccess = access;
            }
            MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals(AsmCodeGenerator.CONSTRUCTOR_NAME) && !this.myExplicitSetupCall) {
                return new FormConstructorVisitor(methodVisitor, this.myClassName, this.mySuperName);
            }
            return methodVisitor;
        }

        MethodVisitor visitNewMethod(int access, String name, String desc, String signature, String[] exceptions) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            this.myFieldDescMap.put(name, desc);
            this.myFieldAccessMap.put(name, new Integer(access));
            return super.visitField(access, name, desc, signature, value);
        }

        public void visitEnd() {
            boolean haveCustomCreateComponents;
            boolean bl = haveCustomCreateComponents = Utils.getCustomCreateComponentCount(AsmCodeGenerator.this.myRootContainer) > 0 && !AsmCodeGenerator.this.myIgnoreCustomCreation;
            if (haveCustomCreateComponents && !this.myHaveCreateComponentsMethod) {
                AsmCodeGenerator.this.myErrors.add(new FormErrorInfo(null, "Form contains components with Custom Create option but no createUIComponents() method"));
            }
            Method method = Method.getMethod((String)"void $$$setupUI$$$ ()");
            GeneratorAdapter generator = new GeneratorAdapter(4098, method, null, null, this.cv);
            if (haveCustomCreateComponents && this.myHaveCreateComponentsMethod) {
                generator.visitVarInsn(25, 0);
                int opcode = this.myCreateComponentsAccess == 2 ? 183 : 182;
                generator.visitMethodInsn(opcode, this.myClassName, AsmCodeGenerator.CREATE_COMPONENTS_METHOD_NAME, "()V");
            }
            this.buildSetupMethod(generator);
            String rootBinding = AsmCodeGenerator.this.myRootContainer.getComponent(0).getBinding();
            if (rootBinding != null && this.myFieldDescMap.containsKey(rootBinding)) {
                this.buildGetRootComponenMethod();
            }
            Iterator iterator = myPropertyCodeGenerators.values().iterator();
            while (iterator.hasNext()) {
                PropertyCodeGenerator propertyCodeGenerator = (PropertyCodeGenerator)iterator.next();
                propertyCodeGenerator.generateClassEnd(this);
            }
            super.visitEnd();
        }

        private void buildGetRootComponenMethod() {
            Type componentType = Type.getType((Class)(class$javax$swing$JComponent == null ? (class$javax$swing$JComponent = AsmCodeGenerator.class$("javax.swing.JComponent")) : class$javax$swing$JComponent));
            Method method = new Method(AsmCodeGenerator.GET_ROOT_COMPONENT_METHOD_NAME, componentType, new Type[0]);
            GeneratorAdapter generator = new GeneratorAdapter(4097, method, null, null, this.cv);
            LwComponent topComponent = (LwComponent)AsmCodeGenerator.this.myRootContainer.getComponent(0);
            String binding = topComponent.getBinding();
            generator.loadThis();
            generator.getField(AsmCodeGenerator.typeFromClassName(this.myClassName), binding, Type.getType((String)((String)this.myFieldDescMap.get(binding))));
            generator.returnValue();
            generator.endMethod();
        }

        private void buildSetupMethod(GeneratorAdapter generator) {
            try {
                LwComponent topComponent = (LwComponent)AsmCodeGenerator.this.myRootContainer.getComponent(0);
                this.generateSetupCodeForComponent(topComponent, generator, -1);
                this.generateComponentReferenceProperties(topComponent, generator);
                this.generateButtonGroups(AsmCodeGenerator.this.myRootContainer, generator);
            } catch (CodeGenerationException e) {
                AsmCodeGenerator.this.myErrors.add(new FormErrorInfo(e.getComponentId(), e.getMessage()));
            }
            generator.returnValue();
            generator.endMethod();
        }

        private void generateSetupCodeForComponent(LwComponent lwComponent, GeneratorAdapter generator, int parentLocal) throws CodeGenerationException {
            LwContainer lwContainer;
            String className;
            if (lwComponent instanceof LwNestedForm) {
                LwRootContainer nestedFormContainer;
                LwNestedForm nestedForm = (LwNestedForm)lwComponent;
                if (AsmCodeGenerator.this.myFormLoader == null) {
                    throw new CodeGenerationException(null, "Attempt to compile nested form with no nested form loader specified");
                }
                try {
                    nestedFormContainer = AsmCodeGenerator.this.myFormLoader.loadForm(nestedForm.getFormFileName());
                } catch (Exception e) {
                    throw new CodeGenerationException(lwComponent.getId(), e.getMessage());
                }
                if (nestedFormContainer.getComponentCount() == 0) {
                    return;
                }
                if (nestedFormContainer.getComponent(0).getBinding() == null) {
                    throw new CodeGenerationException(lwComponent.getId(), "No binding on root component of nested form " + nestedForm.getFormFileName());
                }
                try {
                    Utils.validateNestedFormLoop(nestedForm.getFormFileName(), AsmCodeGenerator.this.myFormLoader);
                } catch (RecursiveFormNestingException e) {
                    throw new CodeGenerationException(lwComponent.getId(), "Recursive form nesting is not allowed");
                }
                className = AsmCodeGenerator.this.myFormLoader.getClassToBindName(nestedFormContainer);
            } else {
                className = this.getComponentCodeGenerator(lwComponent.getParent()).mapComponentClass(lwComponent.getComponentClassName());
            }
            Type componentType = AsmCodeGenerator.typeFromClassName(className);
            int componentLocal = generator.newLocal(componentType);
            AsmCodeGenerator.this.myIdToLocalMap.put(lwComponent.getId(), new Integer(componentLocal));
            Class componentClass = AsmCodeGenerator.getComponentClass(className, AsmCodeGenerator.this.myLoader);
            this.validateFieldBinding(lwComponent, componentClass);
            if (AsmCodeGenerator.this.myIgnoreCustomCreation) {
                boolean creatable = true;
                if ((componentClass.getModifiers() & 0x402) != 0) {
                    creatable = false;
                } else {
                    try {
                        Constructor constructor = componentClass.getConstructor(new Class[0]);
                        if ((constructor.getModifiers() & 1) == 0) {
                            creatable = false;
                        }
                    } catch (NoSuchMethodException ex) {
                        creatable = false;
                    }
                }
                if (!creatable) {
                    componentClass = Utils.suggestReplacementClass(componentClass);
                    componentType = Type.getType((Class)componentClass);
                }
            }
            if (!lwComponent.isCustomCreate() || AsmCodeGenerator.this.myIgnoreCustomCreation) {
                generator.newInstance(componentType);
                generator.dup();
                generator.invokeConstructor(componentType, Method.getMethod((String)"void <init>()"));
                generator.storeLocal(componentLocal);
                this.generateFieldBinding(lwComponent, generator, componentLocal);
            } else {
                String binding = lwComponent.getBinding();
                if (binding == null) {
                    throw new CodeGenerationException(lwComponent.getId(), "Only components bound to fields can have custom creation code");
                }
                generator.loadThis();
                generator.getField(this.getMainClassType(), binding, Type.getType((String)((String)this.myFieldDescMap.get(binding))));
                generator.storeLocal(componentLocal);
            }
            if (lwComponent instanceof LwContainer && (!(lwContainer = (LwContainer)lwComponent).isCustomCreate() || lwContainer.getComponentCount() > 0)) {
                this.getComponentCodeGenerator(lwContainer).generateContainerLayout(lwContainer, generator, componentLocal);
            }
            this.generateComponentProperties(lwComponent, componentClass, generator, componentLocal);
            if (!(lwComponent.getParent() instanceof LwRootContainer)) {
                LayoutCodeGenerator parentCodeGenerator = this.getComponentCodeGenerator(lwComponent.getParent());
                if (lwComponent instanceof LwNestedForm) {
                    componentLocal = this.getNestedFormComponent(generator, componentClass, componentLocal);
                }
                parentCodeGenerator.generateComponentLayout(lwComponent, generator, componentLocal, parentLocal);
            }
            if (lwComponent instanceof LwContainer) {
                LwContainer container = (LwContainer)lwComponent;
                this.generateBorder(container, generator, componentLocal);
                for (int i = 0; i < container.getComponentCount(); ++i) {
                    this.generateSetupCodeForComponent((LwComponent)container.getComponent(i), generator, componentLocal);
                }
            }
        }

        private int getNestedFormComponent(GeneratorAdapter generator, Class componentClass, int formLocal) throws CodeGenerationException {
            Type componentType = Type.getType((Class)(class$javax$swing$JComponent == null ? (class$javax$swing$JComponent = AsmCodeGenerator.class$("javax.swing.JComponent")) : class$javax$swing$JComponent));
            int componentLocal = generator.newLocal(componentType);
            generator.loadLocal(formLocal);
            generator.invokeVirtual(Type.getType((Class)componentClass), new Method(AsmCodeGenerator.GET_ROOT_COMPONENT_METHOD_NAME, componentType, new Type[0]));
            generator.storeLocal(componentLocal);
            return componentLocal;
        }

        private LayoutCodeGenerator getComponentCodeGenerator(LwContainer container) {
            LayoutCodeGenerator generator = (LayoutCodeGenerator)myComponentLayoutCodeGenerators.get(container.getClass());
            if (generator != null) {
                return generator;
            }
            for (LwContainer parent = container; parent != null; parent = parent.getParent()) {
                String layoutManager = parent.getLayoutManager();
                if (layoutManager == null || layoutManager.length() <= 0) continue;
                if (layoutManager.equals("FormLayout") && !myContainerLayoutCodeGenerators.containsKey("FormLayout")) {
                    myContainerLayoutCodeGenerators.put("FormLayout", new FormLayoutCodeGenerator());
                }
                if ((generator = (LayoutCodeGenerator)myContainerLayoutCodeGenerators.get(layoutManager)) == null) continue;
                return generator;
            }
            return GridLayoutCodeGenerator.INSTANCE;
        }

        private void generateComponentProperties(LwComponent lwComponent, Class componentClass, GeneratorAdapter generator, int componentLocal) throws CodeGenerationException {
            LwIntrospectedProperty[] introspectedProperties = lwComponent.getAssignedIntrospectedProperties();
            for (int i = 0; i < introspectedProperties.length; ++i) {
                Type setterArgType;
                PropertyCodeGenerator propGen;
                LwIntrospectedProperty property = introspectedProperties[i];
                if (property instanceof LwIntroComponentProperty) continue;
                String propertyClass = property.getCodeGenPropertyClassName();
                if (AsmCodeGenerator.this.myIgnoreCustomCreation) {
                    try {
                        Class<Comparable<Integer>> setterClass = propertyClass.equals((class$java$lang$Integer == null ? AsmCodeGenerator.class$("java.lang.Integer") : class$java$lang$Integer).getName()) ? Integer.TYPE : (propertyClass.equals((class$java$lang$Boolean == null ? AsmCodeGenerator.class$("java.lang.Boolean") : class$java$lang$Boolean).getName()) ? Boolean.TYPE : (propertyClass.equals((class$java$lang$Double == null ? AsmCodeGenerator.class$("java.lang.Double") : class$java$lang$Double).getName()) ? Double.TYPE : (propertyClass.equals((class$java$lang$Float == null ? AsmCodeGenerator.class$("java.lang.Float") : class$java$lang$Float).getName()) ? Float.TYPE : (propertyClass.equals((class$java$lang$Long == null ? AsmCodeGenerator.class$("java.lang.Long") : class$java$lang$Long).getName()) ? Long.TYPE : (propertyClass.equals((class$java$lang$Byte == null ? AsmCodeGenerator.class$("java.lang.Byte") : class$java$lang$Byte).getName()) ? Byte.TYPE : (propertyClass.equals((class$java$lang$Short == null ? AsmCodeGenerator.class$("java.lang.Short") : class$java$lang$Short).getName()) ? Short.TYPE : (propertyClass.equals((class$java$lang$Character == null ? AsmCodeGenerator.class$("java.lang.Character") : class$java$lang$Character).getName()) ? Character.TYPE : Class.forName(propertyClass))))))));
                        componentClass.getMethod(property.getWriteMethodName(), setterClass);
                    } catch (Exception e) {
                        continue;
                    }
                }
                if ((propGen = (PropertyCodeGenerator)myPropertyCodeGenerators.get(propertyClass)) != null && propGen.generateCustomSetValue(lwComponent, componentClass, property, generator, componentLocal, this.myClassName)) continue;
                generator.loadLocal(componentLocal);
                Object value = lwComponent.getPropertyValue(property);
                if (propertyClass.equals((class$java$lang$Integer == null ? AsmCodeGenerator.class$("java.lang.Integer") : class$java$lang$Integer).getName())) {
                    generator.push(((Integer)value).intValue());
                    setterArgType = Type.INT_TYPE;
                } else if (propertyClass.equals((class$java$lang$Boolean == null ? AsmCodeGenerator.class$("java.lang.Boolean") : class$java$lang$Boolean).getName())) {
                    generator.push(((Boolean)value).booleanValue());
                    setterArgType = Type.BOOLEAN_TYPE;
                } else if (propertyClass.equals((class$java$lang$Double == null ? AsmCodeGenerator.class$("java.lang.Double") : class$java$lang$Double).getName())) {
                    generator.push(((Double)value).doubleValue());
                    setterArgType = Type.DOUBLE_TYPE;
                } else if (propertyClass.equals((class$java$lang$Float == null ? AsmCodeGenerator.class$("java.lang.Float") : class$java$lang$Float).getName())) {
                    generator.push(((Float)value).floatValue());
                    setterArgType = Type.FLOAT_TYPE;
                } else if (propertyClass.equals((class$java$lang$Long == null ? AsmCodeGenerator.class$("java.lang.Long") : class$java$lang$Long).getName())) {
                    generator.push(((Long)value).longValue());
                    setterArgType = Type.LONG_TYPE;
                } else if (propertyClass.equals((class$java$lang$Short == null ? AsmCodeGenerator.class$("java.lang.Short") : class$java$lang$Short).getName())) {
                    generator.push(((Short)value).intValue());
                    setterArgType = Type.SHORT_TYPE;
                } else if (propertyClass.equals((class$java$lang$Byte == null ? AsmCodeGenerator.class$("java.lang.Byte") : class$java$lang$Byte).getName())) {
                    generator.push(((Byte)value).intValue());
                    setterArgType = Type.BYTE_TYPE;
                } else if (propertyClass.equals((class$java$lang$Character == null ? AsmCodeGenerator.class$("java.lang.Character") : class$java$lang$Character).getName())) {
                    generator.push((int)((Character)value).charValue());
                    setterArgType = Type.CHAR_TYPE;
                } else {
                    if (propGen == null) continue;
                    propGen.generatePushValue(generator, value);
                    setterArgType = AsmCodeGenerator.typeFromClassName(property.getPropertyClassName());
                }
                Type declaringType = property.getDeclaringClassName() != null ? AsmCodeGenerator.typeFromClassName(property.getDeclaringClassName()) : Type.getType((Class)componentClass);
                generator.invokeVirtual(declaringType, new Method(property.getWriteMethodName(), Type.VOID_TYPE, new Type[]{setterArgType}));
            }
            this.generateClientProperties(lwComponent, componentClass, generator, componentLocal);
        }

        private void generateClientProperties(LwComponent lwComponent, Class componentClass, GeneratorAdapter generator, int componentLocal) throws CodeGenerationException {
            HashMap props = lwComponent.getDelegeeClientProperties();
            Iterator iterator = props.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry e = iterator.next();
                generator.loadLocal(componentLocal);
                generator.push((String)e.getKey());
                Object value = e.getValue();
                if (value instanceof StringDescriptor) {
                    generator.push(((StringDescriptor)value).getValue());
                } else {
                    Type valueType = Type.getType(value.getClass());
                    generator.newInstance(valueType);
                    generator.dup();
                    if (value instanceof Boolean) {
                        generator.push(((Boolean)value).booleanValue());
                        generator.invokeConstructor(valueType, Method.getMethod((String)"void <init>(boolean)"));
                    } else if (value instanceof Integer) {
                        generator.push(((Integer)value).intValue());
                        generator.invokeConstructor(valueType, Method.getMethod((String)"void <init>(int)"));
                    } else if (value instanceof Double) {
                        generator.push(((Double)value).doubleValue());
                        generator.invokeConstructor(valueType, Method.getMethod((String)"void <init>(double)"));
                    } else {
                        throw new CodeGenerationException(lwComponent.getId(), "Unknown client property value type");
                    }
                }
                Type componentType = Type.getType((Class)componentClass);
                Type objectType = Type.getType((Class)(class$java$lang$Object == null ? AsmCodeGenerator.class$("java.lang.Object") : class$java$lang$Object));
                generator.invokeVirtual(componentType, new Method("putClientProperty", Type.VOID_TYPE, new Type[]{objectType, objectType}));
            }
        }

        private void generateComponentReferenceProperties(LwComponent component, GeneratorAdapter generator) throws CodeGenerationException {
            if (component instanceof LwNestedForm) {
                return;
            }
            int componentLocal = (Integer)AsmCodeGenerator.this.myIdToLocalMap.get(component.getId());
            LayoutCodeGenerator layoutCodeGenerator = this.getComponentCodeGenerator(component.getParent());
            Class componentClass = AsmCodeGenerator.getComponentClass(layoutCodeGenerator.mapComponentClass(component.getComponentClassName()), AsmCodeGenerator.this.myLoader);
            LwIntrospectedProperty[] introspectedProperties = component.getAssignedIntrospectedProperties();
            for (int i = 0; i < introspectedProperties.length; ++i) {
                Integer targetLocalInt;
                String targetId;
                LwIntrospectedProperty property = introspectedProperties[i];
                if (!(property instanceof LwIntroComponentProperty) || (targetId = (String)component.getPropertyValue(property)) == null || targetId.length() <= 0 || (targetLocalInt = (Integer)AsmCodeGenerator.this.myIdToLocalMap.get(targetId)) == null) continue;
                int targetLocal = targetLocalInt;
                generator.loadLocal(componentLocal);
                generator.loadLocal(targetLocal);
                Type declaringType = property.getDeclaringClassName() != null ? AsmCodeGenerator.typeFromClassName(property.getDeclaringClassName()) : Type.getType((Class)componentClass);
                generator.invokeVirtual(declaringType, new Method(property.getWriteMethodName(), Type.VOID_TYPE, new Type[]{AsmCodeGenerator.typeFromClassName(property.getPropertyClassName())}));
            }
            if (component instanceof LwContainer) {
                LwContainer container = (LwContainer)component;
                for (int i = 0; i < container.getComponentCount(); ++i) {
                    this.generateComponentReferenceProperties((LwComponent)container.getComponent(i), generator);
                }
            }
        }

        private void generateButtonGroups(LwRootContainer rootContainer, GeneratorAdapter generator) throws CodeGenerationException {
            IButtonGroup[] groups = rootContainer.getButtonGroups();
            if (groups.length > 0) {
                int groupLocal = generator.newLocal(ourButtonGroupType);
                for (int groupIndex = 0; groupIndex < groups.length; ++groupIndex) {
                    String[] ids = groups[groupIndex].getComponentIds();
                    if (ids.length <= 0) continue;
                    generator.newInstance(ourButtonGroupType);
                    generator.dup();
                    generator.invokeConstructor(ourButtonGroupType, Method.getMethod((String)"void <init>()"));
                    generator.storeLocal(groupLocal);
                    if (groups[groupIndex].isBound() && !AsmCodeGenerator.this.myIgnoreCustomCreation) {
                        this.validateFieldClass(groups[groupIndex].getName(), class$javax$swing$ButtonGroup == null ? AsmCodeGenerator.class$("javax.swing.ButtonGroup") : class$javax$swing$ButtonGroup, null);
                        generator.loadThis();
                        generator.loadLocal(groupLocal);
                        generator.putField(this.getMainClassType(), groups[groupIndex].getName(), ourButtonGroupType);
                    }
                    for (int i = 0; i < ids.length; ++i) {
                        Integer localInt = (Integer)AsmCodeGenerator.this.myIdToLocalMap.get(ids[i]);
                        if (localInt == null) continue;
                        generator.loadLocal(groupLocal);
                        generator.loadLocal(localInt.intValue());
                        generator.invokeVirtual(ourButtonGroupType, Method.getMethod((String)"void add(javax.swing.AbstractButton)"));
                    }
                }
            }
        }

        private void generateFieldBinding(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal) throws CodeGenerationException {
            String binding = lwComponent.getBinding();
            if (binding != null) {
                Integer access = (Integer)this.myFieldAccessMap.get(binding);
                if ((access & 8) != 0) {
                    throw new CodeGenerationException(lwComponent.getId(), "Cannot bind: field is static: " + AsmCodeGenerator.this.myClassToBind + "." + binding);
                }
                if ((access & 0x10) != 0) {
                    throw new CodeGenerationException(lwComponent.getId(), "Cannot bind: field is final: " + AsmCodeGenerator.this.myClassToBind + "." + binding);
                }
                generator.loadThis();
                generator.loadLocal(componentLocal);
                generator.putField(this.getMainClassType(), binding, Type.getType((String)((String)this.myFieldDescMap.get(binding))));
            }
        }

        private Type getMainClassType() {
            return Type.getType((String)("L" + this.myClassName + ";"));
        }

        private void validateFieldBinding(LwComponent component, Class componentClass) throws CodeGenerationException {
            String binding = component.getBinding();
            if (binding == null) {
                return;
            }
            this.validateFieldClass(binding, componentClass, component.getId());
        }

        private void validateFieldClass(String binding, Class componentClass, String componentId) throws CodeGenerationException {
            Class<?> fieldClass;
            if (!this.myFieldDescMap.containsKey(binding)) {
                throw new CodeGenerationException(componentId, "Cannot bind: field does not exist: " + AsmCodeGenerator.this.myClassToBind + "." + binding);
            }
            Type fieldType = Type.getType((String)((String)this.myFieldDescMap.get(binding)));
            if (fieldType.getSort() != 10) {
                throw new CodeGenerationException(componentId, "Cannot bind: field is of primitive type: " + AsmCodeGenerator.this.myClassToBind + "." + binding);
            }
            try {
                fieldClass = AsmCodeGenerator.this.myLoader.loadClass(fieldType.getClassName());
            } catch (ClassNotFoundException e) {
                throw new CodeGenerationException(componentId, "Class not found: " + fieldType.getClassName());
            }
            if (!fieldClass.isAssignableFrom(componentClass)) {
                throw new CodeGenerationException(componentId, "Cannot bind: Incompatible types. Cannot assign " + componentClass.getName() + " to field " + AsmCodeGenerator.this.myClassToBind + "." + binding);
            }
        }

        private void generateBorder(LwContainer container, GeneratorAdapter generator, int componentLocal) {
            BorderType borderType = container.getBorderType();
            StringDescriptor borderTitle = container.getBorderTitle();
            String borderFactoryMethodName = borderType.getBorderFactoryMethodName();
            boolean borderNone = borderType.equals(BorderType.NONE);
            if (!borderNone || borderTitle != null) {
                generator.loadLocal(componentLocal);
                if (!borderNone) {
                    if (borderType.equals(BorderType.LINE)) {
                        if (container.getBorderColor() == null) {
                            Type colorType = Type.getType((Class)(class$java$awt$Color == null ? (class$java$awt$Color = AsmCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color));
                            generator.getStatic(colorType, "black", colorType);
                        } else {
                            AsmCodeGenerator.pushPropValue(generator, (class$java$awt$Color == null ? (class$java$awt$Color = AsmCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color).getName(), container.getBorderColor());
                        }
                        generator.invokeStatic(ourBorderFactoryType, new Method(borderFactoryMethodName, ourBorderType, new Type[]{Type.getType((Class)(class$java$awt$Color == null ? (class$java$awt$Color = AsmCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color))}));
                    } else if (borderType.equals(BorderType.EMPTY) && container.getBorderSize() != null) {
                        Insets size = container.getBorderSize();
                        generator.push(size.top);
                        generator.push(size.left);
                        generator.push(size.bottom);
                        generator.push(size.right);
                        generator.invokeStatic(ourBorderFactoryType, new Method(borderFactoryMethodName, ourBorderType, new Type[]{Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE}));
                    } else {
                        generator.invokeStatic(ourBorderFactoryType, new Method(borderFactoryMethodName, ourBorderType, new Type[0]));
                    }
                } else {
                    generator.push((String)null);
                }
                this.pushBorderProperties(container, generator, borderTitle, componentLocal);
                generator.invokeStatic(ourBorderFactoryType, ourCreateTitledBorderMethod);
                generator.invokeVirtual(Type.getType((Class)(class$javax$swing$JComponent == null ? (class$javax$swing$JComponent = AsmCodeGenerator.class$("javax.swing.JComponent")) : class$javax$swing$JComponent)), Method.getMethod((String)"void setBorder(javax.swing.border.Border)"));
            }
        }

        private void pushBorderProperties(LwContainer container, GeneratorAdapter generator, StringDescriptor borderTitle, int componentLocal) {
            AsmCodeGenerator.pushPropValue(generator, "java.lang.String", borderTitle);
            generator.push(container.getBorderTitleJustification());
            generator.push(container.getBorderTitlePosition());
            FontDescriptor font = container.getBorderTitleFont();
            if (font == null) {
                generator.push((String)null);
            } else {
                FontPropertyCodeGenerator.generatePushFont(generator, componentLocal, container, font, "getFont");
            }
            if (container.getBorderTitleColor() == null) {
                generator.push((String)null);
            } else {
                AsmCodeGenerator.pushPropValue(generator, (class$java$awt$Color == null ? (class$java$awt$Color = AsmCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color).getName(), container.getBorderTitleColor());
            }
        }
    }
}

