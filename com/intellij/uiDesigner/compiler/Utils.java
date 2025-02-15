/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Document
 *  org.jdom.input.SAXBuilder
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.AlienFormFileException;
import com.intellij.uiDesigner.compiler.CodeGenerationException;
import com.intellij.uiDesigner.compiler.NestedFormLoader;
import com.intellij.uiDesigner.compiler.RecursiveFormNestingException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.lw.ComponentVisitor;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IContainer;
import com.intellij.uiDesigner.lw.LwNestedForm;
import com.intellij.uiDesigner.lw.LwRootContainer;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class Utils {
    public static final String FORM_NAMESPACE = "http://www.intellij.com/uidesigner/form/";
    private static final SAXParser SAX_PARSER = Utils.createParser();
    static /* synthetic */ Class class$javax$swing$JComponent;
    static /* synthetic */ Class class$javax$swing$JPanel;

    private Utils() {
    }

    private static SAXParser createParser() {
        try {
            return SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            return null;
        }
    }

    public static LwRootContainer getRootContainer(String formFileContent, PropertiesProvider provider) throws Exception {
        if (formFileContent.indexOf(FORM_NAMESPACE) == -1) {
            throw new AlienFormFileException();
        }
        Document document = new SAXBuilder().build((Reader)new StringReader(formFileContent), "UTF-8");
        LwRootContainer root = new LwRootContainer();
        root.read(document.getRootElement(), provider);
        return root;
    }

    public static LwRootContainer getRootContainer(InputStream stream, PropertiesProvider provider) throws Exception {
        Document document = new SAXBuilder().build(stream, "UTF-8");
        LwRootContainer root = new LwRootContainer();
        root.read(document.getRootElement(), provider);
        return root;
    }

    public static synchronized String getBoundClassName(String formFileContent) throws Exception {
        if (formFileContent.indexOf(FORM_NAMESPACE) == -1) {
            throw new AlienFormFileException();
        }
        final String[] className = new String[]{null};
        try {
            SAX_PARSER.parse(new InputSource(new StringReader(formFileContent)), new DefaultHandler(){

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if ("form".equals(qName)) {
                        className[0] = attributes.getValue("", "bind-to-class");
                        throw new SAXException("stop parsing");
                    }
                }
            });
        } catch (Exception exception) {
            // empty catch block
        }
        return className[0];
    }

    public static String validateJComponentClass(ClassLoader loader, String className, boolean validateConstructor) {
        Class<?> aClass;
        if (loader == null) {
            throw new IllegalArgumentException("loader cannot be null");
        }
        if (className == null) {
            throw new IllegalArgumentException("className cannot be null");
        }
        if ("com.intellij.uiDesigner.HSpacer".equals(className) || "com.intellij.uiDesigner.VSpacer".equals(className)) {
            return null;
        }
        try {
            aClass = Class.forName(className, true, loader);
        } catch (ClassNotFoundException exc) {
            return "Class \"" + className + "\"not found";
        } catch (NoClassDefFoundError exc) {
            return "Cannot load class " + className + ": " + exc.getMessage();
        } catch (ExceptionInInitializerError exc) {
            return "Cannot initialize class " + className + ": " + exc.getMessage();
        } catch (UnsupportedClassVersionError exc) {
            return "Unsupported class version error: " + className;
        }
        if (validateConstructor) {
            try {
                Constructor<?> constructor = aClass.getConstructor(new Class[0]);
                if ((constructor.getModifiers() & 1) == 0) {
                    return "Class \"" + className + "\" does not have default public constructor";
                }
            } catch (Exception exc) {
                return "Class \"" + className + "\" does not have default constructor";
            }
        }
        if (!(class$javax$swing$JComponent == null ? (class$javax$swing$JComponent = Utils.class$("javax.swing.JComponent")) : class$javax$swing$JComponent).isAssignableFrom(aClass)) {
            return "Class \"" + className + "\" is not an instance of javax.swing.JComponent";
        }
        return null;
    }

    public static void validateNestedFormLoop(String formName, NestedFormLoader nestedFormLoader) throws CodeGenerationException, RecursiveFormNestingException {
        Utils.validateNestedFormLoop(formName, nestedFormLoader, null);
    }

    public static void validateNestedFormLoop(String formName, NestedFormLoader nestedFormLoader, String targetForm) throws CodeGenerationException, RecursiveFormNestingException {
        HashSet<String> usedFormNames = new HashSet<String>();
        if (targetForm != null) {
            usedFormNames.add(targetForm);
        }
        Utils.validateNestedFormLoop(usedFormNames, formName, nestedFormLoader);
    }

    private static void validateNestedFormLoop(final Set usedFormNames, String formName, final NestedFormLoader nestedFormLoader) throws CodeGenerationException, RecursiveFormNestingException {
        LwRootContainer rootContainer;
        if (usedFormNames.contains(formName)) {
            throw new RecursiveFormNestingException();
        }
        usedFormNames.add(formName);
        try {
            rootContainer = nestedFormLoader.loadForm(formName);
        } catch (Exception e) {
            throw new CodeGenerationException(null, "Error loading nested form: " + e.getMessage());
        }
        final HashSet thisFormNestedForms = new HashSet();
        final CodeGenerationException[] validateExceptions = new CodeGenerationException[1];
        final RecursiveFormNestingException[] recursiveNestingExceptions = new RecursiveFormNestingException[1];
        rootContainer.accept(new ComponentVisitor(){

            public boolean visit(IComponent component) {
                LwNestedForm nestedForm;
                if (component instanceof LwNestedForm && !thisFormNestedForms.contains((nestedForm = (LwNestedForm)component).getFormFileName())) {
                    thisFormNestedForms.add(nestedForm.getFormFileName());
                    try {
                        Utils.validateNestedFormLoop(usedFormNames, nestedForm.getFormFileName(), nestedFormLoader);
                    } catch (RecursiveFormNestingException e) {
                        recursiveNestingExceptions[0] = e;
                        return false;
                    } catch (CodeGenerationException e) {
                        validateExceptions[0] = e;
                        return false;
                    }
                }
                return true;
            }
        });
        if (recursiveNestingExceptions[0] != null) {
            throw recursiveNestingExceptions[0];
        }
        if (validateExceptions[0] != null) {
            throw validateExceptions[0];
        }
    }

    public static String findNotEmptyPanelWithXYLayout(IComponent component) {
        if (!(component instanceof IContainer)) {
            return null;
        }
        IContainer container = (IContainer)component;
        if (container.getComponentCount() == 0) {
            return null;
        }
        if (container.isXY()) {
            return container.getId();
        }
        for (int i = 0; i < container.getComponentCount(); ++i) {
            String id = Utils.findNotEmptyPanelWithXYLayout(container.getComponent(i));
            if (id == null) continue;
            return id;
        }
        return null;
    }

    public static int getHGap(LayoutManager layout) {
        if (layout instanceof BorderLayout) {
            return ((BorderLayout)layout).getHgap();
        }
        if (layout instanceof CardLayout) {
            return ((CardLayout)layout).getHgap();
        }
        return 0;
    }

    public static int getVGap(LayoutManager layout) {
        if (layout instanceof BorderLayout) {
            return ((BorderLayout)layout).getVgap();
        }
        if (layout instanceof CardLayout) {
            return ((CardLayout)layout).getVgap();
        }
        return 0;
    }

    public static int getCustomCreateComponentCount(IContainer container) {
        final int[] result = new int[]{0};
        container.accept(new ComponentVisitor(){

            public boolean visit(IComponent c) {
                if (c.isCustomCreate()) {
                    result[0] = result[0] + 1;
                }
                return true;
            }
        });
        return result[0];
    }

    public static Class suggestReplacementClass(Class componentClass) {
        while (true) {
            if ((componentClass = componentClass.getSuperclass()).equals(class$javax$swing$JComponent == null ? Utils.class$("javax.swing.JComponent") : class$javax$swing$JComponent)) {
                return class$javax$swing$JPanel == null ? (class$javax$swing$JPanel = Utils.class$("javax.swing.JPanel")) : class$javax$swing$JPanel;
            }
            if ((componentClass.getModifiers() & 0x402) != 0) continue;
            try {
                componentClass.getConstructor(new Class[0]);
            } catch (NoSuchMethodException ex) {
                continue;
            }
            break;
        }
        return componentClass;
    }

    public static int alignFromConstraints(GridConstraints gc, boolean horizontal) {
        int fillMask;
        int anchor = gc.getAnchor();
        int fill = gc.getFill();
        int leftMask = horizontal ? 8 : 1;
        int rightMask = horizontal ? 4 : 2;
        int n = fillMask = horizontal ? 1 : 2;
        if ((fill & fillMask) != 0) {
            return 3;
        }
        if ((anchor & rightMask) != 0) {
            return 2;
        }
        if ((anchor & leftMask) != 0) {
            return 0;
        }
        return 1;
    }

    public static boolean isBoundField(IComponent component, String fieldName) {
        if (fieldName.equals(component.getBinding())) {
            return true;
        }
        if (component instanceof IContainer) {
            IContainer container = (IContainer)component;
            for (int i = 0; i < container.getComponentCount(); ++i) {
                if (!Utils.isBoundField(container.getComponent(i), fieldName)) continue;
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

