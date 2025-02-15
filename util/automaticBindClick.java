/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import core.ui.component.annotation.ButtonToMenuItem;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import util.Log;

public class automaticBindClick {
    public static void bindButtonClick(final Object fieldClass, Object eventClass) {
        try {
            Field[] fields;
            for (Field field : fields = fieldClass.getClass().getDeclaredFields()) {
                if (!field.getType().isAssignableFrom(Button.class)) continue;
                field.setAccessible(true);
                Button fieldValue = (Button)field.get(fieldClass);
                String fieldName = field.getName();
                if (fieldValue == null) continue;
                try {
                    final Method method = eventClass.getClass().getDeclaredMethod(fieldName + "Click", ActionEvent.class);
                    method.setAccessible(true);
                    if (method == null) continue;
                    fieldValue.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                method.invoke(fieldClass, e);
                            } catch (Exception e1) {
                                Log.error(e1);
                            }
                        }
                    });
                } catch (NoSuchMethodException e) {
                    Log.error(fieldName + "Click  \u672a\u5b9e\u73b0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindJButtonClick(Class fieldClass, Object fieldObject, Class eventClass, final Object eventObject) {
        try {
            Field[] fields;
            for (Field field : fields = fieldClass.getDeclaredFields()) {
                if (!field.getType().isAssignableFrom(JButton.class)) continue;
                field.setAccessible(true);
                JButton fieldValue = (JButton)field.get(fieldObject);
                String fieldName = field.getName();
                if (fieldValue == null) continue;
                try {
                    final Method method = eventClass.getDeclaredMethod(fieldName + "Click", ActionEvent.class);
                    method.setAccessible(true);
                    if (method == null) continue;
                    fieldValue.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                method.invoke(eventObject, e);
                            } catch (Exception e1) {
                                Log.error(e1);
                            }
                        }
                    });
                } catch (NoSuchMethodException e) {
                    Log.error(fieldName + "Click  \u672a\u5b9e\u73b0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindJButtonClick(Object fieldClass, Object eventClass) {
        automaticBindClick.bindJButtonClick(fieldClass.getClass(), fieldClass, eventClass.getClass(), eventClass);
    }

    public static void bindMenuItemClick(Object item, Map<String, Method> methodMap, Object eventClass) {
        MenuElement[] menuElements = ((MenuElement)item).getSubElements();
        if (methodMap == null) {
            methodMap = automaticBindClick.getMenuItemMethod(eventClass);
        }
        if (menuElements.length == 0) {
            if (item.getClass().isAssignableFrom(JMenuItem.class)) {
                Method method = methodMap.get(((JMenuItem)item).getActionCommand() + "MenuItemClick");
                automaticBindClick.addMenuItemClickEvent(item, method, eventClass);
            }
        } else {
            for (int i = 0; i < menuElements.length; ++i) {
                MenuElement menuElement = menuElements[i];
                Class<JComponent> itemClass = menuElement.getClass();
                if (itemClass.isAssignableFrom(JPopupMenu.class) || itemClass.isAssignableFrom(JMenu.class)) {
                    automaticBindClick.bindMenuItemClick(menuElement, methodMap, eventClass);
                    continue;
                }
                if (!item.getClass().isAssignableFrom(JMenuItem.class)) continue;
                Method method = methodMap.get(((JMenuItem)menuElement).getActionCommand() + "MenuItemClick");
                automaticBindClick.addMenuItemClickEvent(menuElement, method, eventClass);
            }
        }
    }

    public static void bindButtonToMenuItem(final Object fieldClass, Object eventClass, Object menu) {
        block7: {
            try {
                if (!JMenu.class.isAssignableFrom(menu.getClass()) && !JPopupMenu.class.isAssignableFrom(menu.getClass())) break block7;
                try {
                    Field[] fields;
                    for (Field field : fields = fieldClass.getClass().getDeclaredFields()) {
                        if (!field.getType().isAssignableFrom(JButton.class)) continue;
                        field.setAccessible(true);
                        JButton fieldValue = (JButton)field.get(fieldClass);
                        String fieldName = field.getName();
                        if (fieldValue == null || !field.isAnnotationPresent(ButtonToMenuItem.class)) continue;
                        ButtonToMenuItem buttonToMenuItem = field.getAnnotation(ButtonToMenuItem.class);
                        try {
                            final Method method = eventClass.getClass().getDeclaredMethod(fieldName + "Click", ActionEvent.class);
                            method.setAccessible(true);
                            if (method == null) continue;
                            Method addMethod = menu.getClass().getMethod("add", JMenuItem.class);
                            String menuItemName = fieldValue.getText();
                            JMenuItem menuItem = new JMenuItem(buttonToMenuItem.name().length() > 0 ? buttonToMenuItem.name() : menuItemName);
                            menuItem.addActionListener(new ActionListener(){

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        method.invoke(fieldClass, e);
                                    } catch (Exception e1) {
                                        Log.error(e1);
                                    }
                                }
                            });
                            addMethod.invoke(menu, menuItem);
                        } catch (NoSuchMethodException e) {
                            Log.error(fieldName + "Click  \u672a\u5b9e\u73b0");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    private static Map<String, Method> getMenuItemMethod(Object eventClass) {
        Method[] methods = eventClass.getClass().getDeclaredMethods();
        HashMap<String, Method> methodMap = new HashMap<String, Method>();
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1 || !parameterTypes[0].isAssignableFrom(ActionEvent.class) || !method.getReturnType().isAssignableFrom(Void.TYPE) || !method.getName().endsWith("MenuItemClick")) continue;
            methodMap.put(method.getName(), method);
        }
        return methodMap;
    }

    private static void addMenuItemClickEvent(Object item, final Method method, final Object eventClass) {
        if (method != null && eventClass != null && item.getClass().isAssignableFrom(JMenuItem.class)) {
            ((JMenuItem)item).addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent paramActionEvent) {
                    try {
                        method.setAccessible(true);
                        method.invoke(eventClass, paramActionEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

