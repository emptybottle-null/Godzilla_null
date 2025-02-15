/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.EasyI18N;
import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GOptionPane {
    public static final Object UNINITIALIZED_VALUE = JOptionPane.UNINITIALIZED_VALUE;
    public static final int DEFAULT_OPTION = -1;
    public static final int YES_NO_OPTION = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int OK_CANCEL_OPTION = 2;
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;
    public static final int CANCEL_OPTION = 2;
    public static final int OK_OPTION = 0;
    public static final int CLOSED_OPTION = -1;
    public static final int ERROR_MESSAGE = 0;
    public static final int INFORMATION_MESSAGE = 1;
    public static final int WARNING_MESSAGE = 2;
    public static final int QUESTION_MESSAGE = 3;
    public static final int PLAIN_MESSAGE = -1;
    public static final String ICON_PROPERTY = "icon";
    public static final String MESSAGE_PROPERTY = "message";
    public static final String VALUE_PROPERTY = "value";
    public static final String OPTIONS_PROPERTY = "options";
    public static final String INITIAL_VALUE_PROPERTY = "initialValue";
    public static final String MESSAGE_TYPE_PROPERTY = "messageType";
    public static final String OPTION_TYPE_PROPERTY = "optionType";
    public static final String SELECTION_VALUES_PROPERTY = "selectionValues";
    public static final String INITIAL_SELECTION_VALUE_PROPERTY = "initialSelectionValue";
    public static final String INPUT_VALUE_PROPERTY = "inputValue";
    public static final String WANTS_INPUT_PROPERTY = "wantsInput";

    public static String showInputDialog(Object message) throws HeadlessException {
        return GOptionPane.showInputDialog(null, message);
    }

    public static String showInputDialog(Object message, Object initialSelectionValue) {
        return GOptionPane.showInputDialog(null, message, initialSelectionValue);
    }

    public static String showInputDialog(Component parentComponent, Object message) throws HeadlessException {
        return GOptionPane.showInputDialog(parentComponent, message, GOptionPane.getString("OptionPane.inputDialogTitle", parentComponent), 3);
    }

    public static String showInputDialog(Component parentComponent, Object message, Object initialSelectionValue) {
        return (String)GOptionPane.showInputDialog(parentComponent, message, GOptionPane.getString("OptionPane.inputDialogTitle", parentComponent), 3, null, null, initialSelectionValue);
    }

    public static String showInputDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        return (String)GOptionPane.showInputDialog(parentComponent, message, title, messageType, null, null, null);
    }

    public static Object showInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) throws HeadlessException {
        title = EasyI18N.getI18nString(title);
        if (message instanceof String) {
            message = EasyI18N.getI18nString(message.toString());
        }
        return JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue);
    }

    public static void showMessageDialog(Component parentComponent, Object message) throws HeadlessException {
        GOptionPane.showMessageDialog(parentComponent, message, GOptionPane.getString("OptionPane.messageDialogTitle", parentComponent), 1);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) throws HeadlessException {
        GOptionPane.showMessageDialog(parentComponent, message, title, messageType, null);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) throws HeadlessException {
        GOptionPane.showOptionDialog(parentComponent, message, title, -1, messageType, icon, null, null);
    }

    public static int showConfirmDialog(Component parentComponent, Object message) throws HeadlessException {
        return GOptionPane.showConfirmDialog(parentComponent, message, UIManager.getString("OptionPane.titleText"), 1);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) throws HeadlessException {
        return GOptionPane.showConfirmDialog(parentComponent, message, title, optionType, 3);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) throws HeadlessException {
        return GOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, null);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) throws HeadlessException {
        return GOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, null, null);
    }

    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) throws HeadlessException {
        title = EasyI18N.getI18nString(title);
        if (message instanceof String) {
            message = EasyI18N.getI18nString(message.toString());
        }
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }

    public static void showInternalMessageDialog(Component parentComponent, Object message) {
        GOptionPane.showInternalMessageDialog(parentComponent, message, GOptionPane.getString("OptionPane.messageDialogTitle", parentComponent), 1);
    }

    public static void showInternalMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        GOptionPane.showInternalMessageDialog(parentComponent, message, title, messageType, null);
    }

    public static void showInternalMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) {
        GOptionPane.showInternalOptionDialog(parentComponent, message, title, -1, messageType, icon, null, null);
    }

    public static int showInternalConfirmDialog(Component parentComponent, Object message) {
        return GOptionPane.showInternalConfirmDialog(parentComponent, message, UIManager.getString("OptionPane.titleText"), 1);
    }

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
        return GOptionPane.showInternalConfirmDialog(parentComponent, message, title, optionType, 3);
    }

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
        return GOptionPane.showInternalConfirmDialog(parentComponent, message, title, optionType, messageType, null);
    }

    public static int showInternalConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) {
        return GOptionPane.showInternalOptionDialog(parentComponent, message, title, optionType, messageType, icon, null, null);
    }

    public static int showInternalOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        title = EasyI18N.getI18nString(title);
        if (message instanceof String) {
            message = EasyI18N.getI18nString(message.toString());
        }
        return JOptionPane.showInternalOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }

    public static String showInternalInputDialog(Component parentComponent, Object message) {
        return GOptionPane.showInternalInputDialog(parentComponent, message, GOptionPane.getString("OptionPane.inputDialogTitle", parentComponent), 3);
    }

    public static String showInternalInputDialog(Component parentComponent, Object message, String title, int messageType) {
        title = EasyI18N.getI18nString(title);
        if (message instanceof String) {
            message = EasyI18N.getI18nString(message.toString());
        }
        return (String)GOptionPane.showInternalInputDialog(parentComponent, message, title, messageType, null, null, null);
    }

    public static Object showInternalInputDialog(Component parentComponent, Object message, String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue) {
        return JOptionPane.showInternalInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue);
    }

    public static Frame getFrameForComponent(Component parentComponent) throws HeadlessException {
        return JOptionPane.getFrameForComponent(parentComponent);
    }

    public static String getString(Object key, Component c) {
        Locale l = c == null ? Locale.getDefault() : c.getLocale();
        return UIManager.getString(key, l);
    }

    public static JDesktopPane getDesktopPaneForComponent(Component parentComponent) {
        return JOptionPane.getDesktopPaneForComponent(parentComponent);
    }

    public static void setRootFrame(Frame newRootFrame) {
        JOptionPane.setRootFrame(newRootFrame);
    }

    public static Frame getRootFrame() throws HeadlessException {
        return JOptionPane.getRootFrame();
    }
}

