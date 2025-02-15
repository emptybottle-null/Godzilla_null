/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core;

import core.Db;
import core.annotation.I18NAction;
import core.annotation.NoI18N;
import java.awt.Window;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.MenuElement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import util.functions;

public class EasyI18N {
    public static final String SETING_KETY = "language";
    private static final HashMap<Class<?>, Method> actionMap = new HashMap();
    private static final Class[] parameterTypes = new Class[]{Object.class, Field.class};
    private static final Locale language = new Locale(Db.getSetingValue("language", "zh".equalsIgnoreCase(Locale.getDefault().getLanguage()) ? "zh" : "en"));
    private static final ResourceBundle bundle = ResourceBundle.getBundle("godzilla", language);

    public static void installObject(Object obj) {
        try {
            for (Class<?> objClass = obj.getClass(); !(objClass == null || objClass.getName().startsWith("java") && objClass.getName().startsWith("sun")); objClass = objClass.getSuperclass()) {
                try {
                    Field[] fields = objClass.getDeclaredFields();
                    Method actionMethod = null;
                    for (Field field : fields) {
                        if (field.getAnnotation(NoI18N.class) != null) continue;
                        field.setAccessible(true);
                        actionMethod = EasyI18N.findAction(field.getType());
                        if (actionMethod == null) continue;
                        actionMethod.setAccessible(true);
                        actionMethod.invoke(null, obj, field);
                    }
                    if (objClass.getAnnotation(NoI18N.class) != null || (actionMethod = EasyI18N.findAction(objClass)) == null) continue;
                    actionMethod.setAccessible(true);
                    actionMethod.invoke(null, obj, null);
                    continue;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getI18nString(String format, Object ... args) {
        return String.format(EasyI18N.getI18nString(format), args);
    }

    public static String getI18nString(String key) {
        if ("zh".equals(language.getLanguage())) {
            return key;
        }
        if (key != null) {
            String value = null;
            try {
                value = bundle.getString(key.trim().replace("\r\n", "\\r\\n").replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t"));
                if (value != null) {
                    value = value.replace("\\r\\n", "\r\n").replace("\\r", "\r").replace("\\n", "\n").replace("\\t", "\t");
                }
            } catch (Exception exception) {
                // empty catch block
            }
            return value == null ? key : value;
        }
        return null;
    }

    private static Method findAction(Class fieldType) {
        Method action = EasyI18N.findAction(fieldType, true);
        if (action == null) {
            action = EasyI18N.findAction(fieldType, false);
        }
        return action;
    }

    private static Method findAction(Class fieldType, boolean comparisonThis) {
        if (comparisonThis) {
            for (Class<?> clazz : actionMap.keySet()) {
                if (!fieldType.equals(clazz)) continue;
                return actionMap.get(clazz);
            }
        } else {
            for (Class<?> clazz : actionMap.keySet()) {
                if (!clazz.isAssignableFrom(fieldType)) continue;
                return actionMap.get(clazz);
            }
        }
        return null;
    }

    @I18NAction(targetClass=JLabel.class)
    public static void installJLabel(Object obj, Field targetField) throws Throwable {
        JLabel label = (JLabel)targetField.get(obj);
        if (label != null) {
            label.setText(EasyI18N.getI18nString(label.getText()));
        } else {
            targetField.set(obj, new JLabel(targetField.getName()));
        }
    }

    @I18NAction(targetClass=JMenu.class)
    public static void installJMenu(Object obj, Field targetField) throws Throwable {
        JMenu menu = (JMenu)targetField.get(obj);
        menu.setText(EasyI18N.getI18nString(menu.getText()));
        int itemCount = menu.getItemCount();
        for (int i = 0; i < itemCount; ++i) {
            JMenuItem menuItem = menu.getItem(i);
            menuItem.setText(EasyI18N.getI18nString(menuItem.getText()));
        }
    }

    @I18NAction(targetClass=JTabbedPane.class)
    public static void installJTabbedPane(Object obj, Field targetField) throws Throwable {
        JTabbedPane tabbedPane = (JTabbedPane)targetField.get(obj);
        int itemCount = tabbedPane.getTabCount();
        for (int i = 0; i < itemCount; ++i) {
            String title = tabbedPane.getTitleAt(i);
            if (title == null) continue;
            tabbedPane.setTitleAt(i, EasyI18N.getI18nString(title));
        }
    }

    @I18NAction(targetClass=JPopupMenu.class)
    public static void installJPopupMenu(Object obj, Field targetField) throws Throwable {
        MenuElement[] menuElements;
        JPopupMenu popupMenu = (JPopupMenu)targetField.get(obj);
        for (MenuElement menuElement : menuElements = popupMenu.getSubElements()) {
            if (!(menuElement instanceof JMenuItem)) continue;
            JMenuItem menuItem = (JMenuItem)menuElement;
            menuItem.setText(EasyI18N.getI18nString(menuItem.getText()));
        }
    }

    @I18NAction(targetClass=JButton.class)
    public static void installJButton(Object obj, Field targetField) throws Throwable {
        JButton button = (JButton)targetField.get(obj);
        if (button != null) {
            button.setText(EasyI18N.getI18nString(button.getText()));
        }
    }

    @I18NAction(targetClass=JCheckBox.class)
    public static void installJCheckBox(Object obj, Field targetField) throws Throwable {
        JCheckBox checkBox = (JCheckBox)targetField.get(obj);
        if (checkBox != null) {
            checkBox.setText(EasyI18N.getI18nString(checkBox.getText()));
        }
    }

    @I18NAction(targetClass=JComponent.class)
    public static void installJComponent(Object obj, Field targetField) throws Throwable {
        JComponent component = null;
        component = targetField == null ? (JComponent)obj : (JComponent)targetField.get(obj);
        if (component == null) {
            return;
        }
        Border border = component.getBorder();
        if (border instanceof TitledBorder) {
            TitledBorder titledBorder = (TitledBorder)border;
            titledBorder.setTitle(EasyI18N.getI18nString(titledBorder.getTitle()));
        }
        Method getTitleMethod = functions.getMethodByClass(component.getClass(), "getTitle", null);
        Method setTitleMethod = functions.getMethodByClass(component.getClass(), "setTitle", String.class);
        if (getTitleMethod != null && setTitleMethod != null) {
            getTitleMethod.setAccessible(true);
            setTitleMethod.setAccessible(true);
            String oldTitle = (String)getTitleMethod.invoke(obj, null);
            if (oldTitle != null) {
                setTitleMethod.invoke(obj, EasyI18N.getI18nString(oldTitle));
            }
        }
    }

    @I18NAction(targetClass=Window.class)
    public static void installWindow(Object obj, Field targetField) {
        try {
            Window component = null;
            component = targetField == null ? (Window)obj : (Window)targetField.get(obj);
            Method getTitleMethod = functions.getMethodByClass(component.getClass(), "getTitle", null);
            Method setTitleMethod = functions.getMethodByClass(component.getClass(), "setTitle", String.class);
            if (getTitleMethod != null && setTitleMethod != null) {
                getTitleMethod.setAccessible(true);
                setTitleMethod.setAccessible(true);
                String oldTitle = (String)getTitleMethod.invoke(obj, null);
                if (oldTitle != null) {
                    setTitleMethod.invoke(obj, EasyI18N.getI18nString(oldTitle));
                }
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }

    static {
        try {
            Method[] methods;
            for (Method method : methods = EasyI18N.class.getDeclaredMethods()) {
                I18NAction action;
                if (!Modifier.isStatic(method.getModifiers()) || !Arrays.equals(parameterTypes, method.getParameterTypes()) || (action = method.getDeclaredAnnotation(I18NAction.class)) == null) continue;
                actionMap.put(action.targetClass(), method);
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }
}

