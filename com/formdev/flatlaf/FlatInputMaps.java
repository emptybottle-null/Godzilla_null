/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.util.SystemInfo;
import java.util.function.BooleanSupplier;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.InputMapUIResource;

class FlatInputMaps {
    FlatInputMaps() {
    }

    static void initInputMaps(UIDefaults defaults) {
        FlatInputMaps.initBasicInputMaps(defaults);
        FlatInputMaps.initTextComponentInputMaps(defaults);
        if (SystemInfo.isMacOS) {
            FlatInputMaps.initMacInputMaps(defaults);
        }
    }

    private static void initBasicInputMaps(UIDefaults defaults) {
        defaults.put("Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[]{"SPACE", "pressed", "released SPACE", "released"}));
        FlatInputMaps.modifyInputMap(defaults, "ComboBox.ancestorInputMap", "SPACE", "spacePopup", "UP", FlatInputMaps.mac("selectPrevious2", "selectPrevious"), "DOWN", FlatInputMaps.mac("selectNext2", "selectNext"), "KP_UP", FlatInputMaps.mac("selectPrevious2", "selectPrevious"), "KP_DOWN", FlatInputMaps.mac("selectNext2", "selectNext"), FlatInputMaps.mac("alt UP", null), "togglePopup", FlatInputMaps.mac("alt DOWN", null), "togglePopup", FlatInputMaps.mac("alt KP_UP", null), "togglePopup", FlatInputMaps.mac("alt KP_DOWN", null), "togglePopup");
        if (!SystemInfo.isMacOS) {
            FlatInputMaps.modifyInputMap(defaults, "FileChooser.ancestorInputMap", "F2", "editFileName", "BACK_SPACE", "Go Up");
        }
        Object[] bindings = (Object[])defaults.get("PopupMenu.selectedWindowInputMapBindings");
        Object[] rtlBindings = (Object[])defaults.get("PopupMenu.selectedWindowInputMapBindings.RightToLeft");
        if (bindings != null && rtlBindings != null) {
            Object[] newBindings = new Object[bindings.length + rtlBindings.length];
            System.arraycopy(bindings, 0, newBindings, 0, bindings.length);
            System.arraycopy(rtlBindings, 0, newBindings, bindings.length, rtlBindings.length);
            defaults.put("PopupMenu.selectedWindowInputMapBindings.RightToLeft", newBindings);
        }
        FlatInputMaps.modifyInputMap(defaults, "TabbedPane.ancestorInputMap", "ctrl TAB", "navigateNext", "shift ctrl TAB", "navigatePrevious");
        FlatInputMaps.modifyInputMap(() -> UIManager.getBoolean("Table.consistentHomeEndKeyBehavior"), defaults, "Table.ancestorInputMap", "HOME", "selectFirstRow", "END", "selectLastRow", "shift HOME", "selectFirstRowExtendSelection", "shift END", "selectLastRowExtendSelection", FlatInputMaps.mac("ctrl HOME", null), "selectFirstColumn", FlatInputMaps.mac("ctrl END", null), "selectLastColumn", FlatInputMaps.mac("shift ctrl HOME", null), "selectFirstColumnExtendSelection", FlatInputMaps.mac("shift ctrl END", null), "selectLastColumnExtendSelection");
        if (!SystemInfo.isMacOS) {
            FlatInputMaps.modifyInputMap(defaults, "Tree.focusInputMap", "ADD", "expand", "SUBTRACT", "collapse");
        }
    }

    private static void initTextComponentInputMaps(UIDefaults defaults) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3;
        Object[] commonTextComponentBindings = new Object[]{"LEFT", "caret-backward", "RIGHT", "caret-forward", "KP_LEFT", "caret-backward", "KP_RIGHT", "caret-forward", "shift LEFT", "selection-backward", "shift RIGHT", "selection-forward", "shift KP_LEFT", "selection-backward", "shift KP_RIGHT", "selection-forward", FlatInputMaps.mac("ctrl LEFT", "alt LEFT"), "caret-previous-word", FlatInputMaps.mac("ctrl RIGHT", "alt RIGHT"), "caret-next-word", FlatInputMaps.mac("ctrl KP_LEFT", "alt KP_LEFT"), "caret-previous-word", FlatInputMaps.mac("ctrl KP_RIGHT", "alt KP_RIGHT"), "caret-next-word", FlatInputMaps.mac("ctrl shift LEFT", "shift alt LEFT"), "selection-previous-word", FlatInputMaps.mac("ctrl shift RIGHT", "shift alt RIGHT"), "selection-next-word", FlatInputMaps.mac("ctrl shift KP_LEFT", "shift alt KP_LEFT"), "selection-previous-word", FlatInputMaps.mac("ctrl shift KP_RIGHT", "shift alt KP_RIGHT"), "selection-next-word", FlatInputMaps.mac("HOME", "meta LEFT"), "caret-begin-line", FlatInputMaps.mac("END", "meta RIGHT"), "caret-end-line", FlatInputMaps.mac("shift HOME", "shift meta LEFT"), "selection-begin-line", FlatInputMaps.mac("shift END", "shift meta RIGHT"), "selection-end-line", FlatInputMaps.mac("ctrl A", "meta A"), "select-all", FlatInputMaps.mac("ctrl BACK_SLASH", "meta BACK_SLASH"), "unselect", "BACK_SPACE", "delete-previous", "shift BACK_SPACE", "delete-previous", "ctrl H", "delete-previous", "DELETE", "delete-next", FlatInputMaps.mac("ctrl BACK_SPACE", "alt BACK_SPACE"), "delete-previous-word", FlatInputMaps.mac("ctrl DELETE", "alt DELETE"), "delete-next-word", FlatInputMaps.mac("ctrl X", "meta X"), "cut-to-clipboard", FlatInputMaps.mac("ctrl C", "meta C"), "copy-to-clipboard", FlatInputMaps.mac("ctrl V", "meta V"), "paste-from-clipboard", "CUT", "cut-to-clipboard", "COPY", "copy-to-clipboard", "PASTE", "paste-from-clipboard", FlatInputMaps.mac("shift DELETE", null), "cut-to-clipboard", FlatInputMaps.mac("control INSERT", null), "copy-to-clipboard", FlatInputMaps.mac("shift INSERT", null), "paste-from-clipboard", "control shift O", "toggle-componentOrientation"};
        if (SystemInfo.isMacOS) {
            Object[] objectArray4 = new Object[58];
            objectArray4[0] = "ctrl B";
            objectArray4[1] = "caret-backward";
            objectArray4[2] = "ctrl F";
            objectArray4[3] = "caret-forward";
            objectArray4[4] = "HOME";
            objectArray4[5] = "caret-begin";
            objectArray4[6] = "END";
            objectArray4[7] = "caret-end";
            objectArray4[8] = "meta UP";
            objectArray4[9] = "caret-begin";
            objectArray4[10] = "meta DOWN";
            objectArray4[11] = "caret-end";
            objectArray4[12] = "meta KP_UP";
            objectArray4[13] = "caret-begin";
            objectArray4[14] = "meta KP_DOWN";
            objectArray4[15] = "caret-end";
            objectArray4[16] = "ctrl P";
            objectArray4[17] = "caret-begin";
            objectArray4[18] = "ctrl N";
            objectArray4[19] = "caret-end";
            objectArray4[20] = "ctrl V";
            objectArray4[21] = "caret-end";
            objectArray4[22] = "meta KP_LEFT";
            objectArray4[23] = "caret-begin-line";
            objectArray4[24] = "meta KP_RIGHT";
            objectArray4[25] = "caret-end-line";
            objectArray4[26] = "ctrl A";
            objectArray4[27] = "caret-begin-line";
            objectArray4[28] = "ctrl E";
            objectArray4[29] = "caret-end-line";
            objectArray4[30] = "shift meta UP";
            objectArray4[31] = "selection-begin";
            objectArray4[32] = "shift meta DOWN";
            objectArray4[33] = "selection-end";
            objectArray4[34] = "shift meta KP_UP";
            objectArray4[35] = "selection-begin";
            objectArray4[36] = "shift meta KP_DOWN";
            objectArray4[37] = "selection-end";
            objectArray4[38] = "shift HOME";
            objectArray4[39] = "selection-begin";
            objectArray4[40] = "shift END";
            objectArray4[41] = "selection-end";
            objectArray4[42] = "shift meta KP_LEFT";
            objectArray4[43] = "selection-begin-line";
            objectArray4[44] = "shift meta KP_RIGHT";
            objectArray4[45] = "selection-end-line";
            objectArray4[46] = "shift UP";
            objectArray4[47] = "selection-begin-line";
            objectArray4[48] = "shift DOWN";
            objectArray4[49] = "selection-end-line";
            objectArray4[50] = "shift KP_UP";
            objectArray4[51] = "selection-begin-line";
            objectArray4[52] = "shift KP_DOWN";
            objectArray4[53] = "selection-end-line";
            objectArray4[54] = "ctrl W";
            objectArray4[55] = "delete-previous-word";
            objectArray4[56] = "ctrl D";
            objectArray3 = objectArray4;
            objectArray4[57] = "delete-next";
        } else {
            objectArray3 = null;
        }
        Object[] macCommonTextComponentBindings = objectArray3;
        Object[] singleLineTextComponentBindings = new Object[]{"ENTER", "notify-field-accept"};
        if (SystemInfo.isMacOS) {
            Object[] objectArray5 = new Object[8];
            objectArray5[0] = "UP";
            objectArray5[1] = "caret-begin-line";
            objectArray5[2] = "DOWN";
            objectArray5[3] = "caret-end-line";
            objectArray5[4] = "KP_UP";
            objectArray5[5] = "caret-begin-line";
            objectArray5[6] = "KP_DOWN";
            objectArray2 = objectArray5;
            objectArray5[7] = "caret-end-line";
        } else {
            objectArray2 = null;
        }
        Object[] macSingleLineTextComponentBindings = objectArray2;
        Object[] formattedTextComponentBindings = new Object[]{"ESCAPE", "reset-field-edit", "UP", "increment", "DOWN", "decrement", "KP_UP", "increment", "KP_DOWN", "decrement"};
        Object[] passwordTextComponentBindings = new Object[]{FlatInputMaps.mac("ctrl LEFT", "alt LEFT"), "caret-begin-line", FlatInputMaps.mac("ctrl RIGHT", "alt RIGHT"), "caret-end-line", FlatInputMaps.mac("ctrl KP_LEFT", "alt KP_LEFT"), "caret-begin-line", FlatInputMaps.mac("ctrl KP_RIGHT", "alt KP_RIGHT"), "caret-end-line", FlatInputMaps.mac("ctrl shift LEFT", "shift alt LEFT"), "selection-begin-line", FlatInputMaps.mac("ctrl shift RIGHT", "shift alt RIGHT"), "selection-end-line", FlatInputMaps.mac("ctrl shift KP_LEFT", "shift alt KP_LEFT"), "selection-begin-line", FlatInputMaps.mac("ctrl shift KP_RIGHT", "shift alt KP_RIGHT"), "selection-end-line", FlatInputMaps.mac("ctrl BACK_SPACE", "alt BACK_SPACE"), null, FlatInputMaps.mac("ctrl DELETE", "alt DELETE"), null};
        Object[] multiLineTextComponentBindings = new Object[]{"UP", "caret-up", "DOWN", "caret-down", "KP_UP", "caret-up", "KP_DOWN", "caret-down", "shift UP", "selection-up", "shift DOWN", "selection-down", "shift KP_UP", "selection-up", "shift KP_DOWN", "selection-down", "PAGE_UP", "page-up", "PAGE_DOWN", "page-down", "shift PAGE_UP", "selection-page-up", "shift PAGE_DOWN", "selection-page-down", FlatInputMaps.mac("ctrl shift PAGE_UP", "shift meta PAGE_UP"), "selection-page-left", FlatInputMaps.mac("ctrl shift PAGE_DOWN", "shift meta PAGE_DOWN"), "selection-page-right", FlatInputMaps.mac("ctrl HOME", "meta UP"), "caret-begin", FlatInputMaps.mac("ctrl END", "meta DOWN"), "caret-end", FlatInputMaps.mac("ctrl shift HOME", "shift meta UP"), "selection-begin", FlatInputMaps.mac("ctrl shift END", "shift meta DOWN"), "selection-end", "ENTER", "insert-break", "TAB", "insert-tab", FlatInputMaps.mac("ctrl T", "meta T"), "next-link-action", FlatInputMaps.mac("ctrl shift T", "shift meta T"), "previous-link-action", FlatInputMaps.mac("ctrl SPACE", "meta SPACE"), "activate-link-action"};
        if (SystemInfo.isMacOS) {
            Object[] objectArray6 = new Object[14];
            objectArray6[0] = "ctrl N";
            objectArray6[1] = "caret-down";
            objectArray6[2] = "ctrl P";
            objectArray6[3] = "caret-up";
            objectArray6[4] = "shift alt UP";
            objectArray6[5] = "selection-begin-paragraph";
            objectArray6[6] = "shift alt DOWN";
            objectArray6[7] = "selection-end-paragraph";
            objectArray6[8] = "shift alt KP_UP";
            objectArray6[9] = "selection-begin-paragraph";
            objectArray6[10] = "shift alt KP_DOWN";
            objectArray6[11] = "selection-end-paragraph";
            objectArray6[12] = "ctrl V";
            objectArray = objectArray6;
            objectArray6[13] = "page-down";
        } else {
            objectArray = null;
        }
        Object[] macMultiLineTextComponentBindings = objectArray;
        defaults.put("TextField.focusInputMap", new LazyInputMapEx(commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings));
        defaults.put("FormattedTextField.focusInputMap", new LazyInputMapEx(commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings, formattedTextComponentBindings));
        defaults.put("PasswordField.focusInputMap", new LazyInputMapEx(commonTextComponentBindings, macCommonTextComponentBindings, singleLineTextComponentBindings, macSingleLineTextComponentBindings, passwordTextComponentBindings));
        LazyInputMapEx multiLineInputMap = new LazyInputMapEx(commonTextComponentBindings, macCommonTextComponentBindings, multiLineTextComponentBindings, macMultiLineTextComponentBindings);
        defaults.put("TextArea.focusInputMap", multiLineInputMap);
        defaults.put("TextPane.focusInputMap", multiLineInputMap);
        defaults.put("EditorPane.focusInputMap", multiLineInputMap);
    }

    private static void initMacInputMaps(UIDefaults defaults) {
        FlatInputMaps.modifyInputMap(defaults, "List.focusInputMap", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl KP_UP", null, "ctrl LEFT", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl RIGHT", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl KP_UP", null, "shift ctrl LEFT", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl RIGHT", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift SPACE", null);
        FlatInputMaps.modifyInputMap(defaults, "List.focusInputMap.RightToLeft", "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl LEFT", null, "ctrl RIGHT", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl LEFT", null, "shift ctrl RIGHT", null);
        FlatInputMaps.modifyInputMap(defaults, "ScrollPane.ancestorInputMap", "END", "scrollEnd", "HOME", "scrollHome", "ctrl END", null, "ctrl HOME", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null);
        FlatInputMaps.modifyInputMap(defaults, "ScrollPane.ancestorInputMap.RightToLeft", "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null);
        FlatInputMaps.modifyInputMap(defaults, "TabbedPane.ancestorInputMap", "ctrl UP", null, "ctrl KP_UP", null);
        FlatInputMaps.modifyInputMap(defaults, "TabbedPane.focusInputMap", "ctrl DOWN", null, "ctrl KP_DOWN", null);
        FlatInputMaps.modifyInputMap(defaults, "Table.ancestorInputMap", "alt TAB", "focusHeader", "shift alt TAB", "focusHeader", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl KP_UP", null, "ctrl LEFT", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl RIGHT", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "F2", null, "F8", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl KP_UP", null, "shift ctrl LEFT", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl RIGHT", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift SPACE", null);
        FlatInputMaps.modifyInputMap(defaults, "Table.ancestorInputMap.RightToLeft", "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl LEFT", null, "ctrl RIGHT", null, "shift ctrl KP_LEFT", null, "shift ctrl KP_RIGHT", null, "shift ctrl LEFT", null, "shift ctrl RIGHT", null);
        FlatInputMaps.modifyInputMap(defaults, "Tree.focusInputMap", "LEFT", "selectParent", "RIGHT", "selectChild", "KP_LEFT", "selectParent", "KP_RIGHT", "selectChild", "shift LEFT", "selectParent", "shift RIGHT", "selectChild", "shift KP_LEFT", "selectParent", "shift KP_RIGHT", "selectChild", "alt LEFT", "selectParent", "alt RIGHT", "selectChild", "alt KP_LEFT", "selectParent", "alt KP_RIGHT", "selectChild", "shift HOME", "selectFirstExtendSelection", "shift END", "selectLastExtendSelection", "meta A", "selectAll", "meta C", "copy", "meta V", "paste", "meta X", "cut", "HOME", null, "END", null, "PAGE_UP", null, "PAGE_DOWN", null, "ctrl LEFT", null, "ctrl RIGHT", null, "ctrl KP_LEFT", null, "ctrl KP_RIGHT", null, "ctrl A", null, "ctrl BACK_SLASH", null, "ctrl C", null, "ctrl DOWN", null, "ctrl END", null, "ctrl HOME", null, "ctrl INSERT", null, "ctrl KP_DOWN", null, "ctrl KP_UP", null, "ctrl PAGE_DOWN", null, "ctrl PAGE_UP", null, "ctrl SLASH", null, "ctrl SPACE", null, "ctrl UP", null, "ctrl V", null, "ctrl X", null, "F2", null, "SPACE", null, "shift ctrl DOWN", null, "shift ctrl END", null, "shift ctrl HOME", null, "shift ctrl KP_DOWN", null, "shift ctrl KP_UP", null, "shift ctrl PAGE_DOWN", null, "shift ctrl PAGE_UP", null, "shift ctrl SPACE", null, "shift ctrl UP", null, "shift DELETE", null, "shift INSERT", null, "shift PAGE_DOWN", null, "shift PAGE_UP", null, "shift SPACE", null);
        defaults.put("Tree.focusInputMap.RightToLeft", new UIDefaults.LazyInputMap(new Object[]{"LEFT", "selectChild", "RIGHT", "selectParent", "KP_LEFT", "selectChild", "KP_RIGHT", "selectParent", "shift LEFT", "selectChild", "shift RIGHT", "selectParent", "shift KP_LEFT", "selectChild", "shift KP_RIGHT", "selectParent", "alt LEFT", "selectChild", "alt RIGHT", "selectParent", "alt KP_LEFT", "selectChild", "alt KP_RIGHT", "selectParent"}));
    }

    private static void modifyInputMap(UIDefaults defaults, String key, Object ... bindings) {
        FlatInputMaps.modifyInputMap(null, defaults, key, bindings);
    }

    private static void modifyInputMap(BooleanSupplier condition, UIDefaults defaults, String key, Object ... bindings) {
        defaults.put(key, new LazyModifyInputMap(condition, defaults.remove(key), bindings));
    }

    private static <T> T mac(T value, T macValue) {
        return SystemInfo.isMacOS ? macValue : value;
    }

    private static class LazyModifyInputMap
    implements UIDefaults.LazyValue {
        private final BooleanSupplier condition;
        private final Object baseInputMap;
        private final Object[] bindings;

        LazyModifyInputMap(BooleanSupplier condition, Object baseInputMap, Object[] bindings) {
            this.condition = condition;
            this.baseInputMap = baseInputMap;
            this.bindings = bindings;
        }

        @Override
        public Object createValue(UIDefaults table) {
            InputMap inputMap;
            InputMap inputMap2 = inputMap = this.baseInputMap instanceof UIDefaults.LazyValue ? (InputMap)((UIDefaults.LazyValue)this.baseInputMap).createValue(table) : (InputMap)this.baseInputMap;
            if (this.condition != null && !this.condition.getAsBoolean()) {
                return inputMap;
            }
            for (int i = 0; i < this.bindings.length; i += 2) {
                KeyStroke keyStroke = KeyStroke.getKeyStroke((String)this.bindings[i]);
                if (this.bindings[i + 1] != null) {
                    inputMap.put(keyStroke, this.bindings[i + 1]);
                    continue;
                }
                inputMap.remove(keyStroke);
            }
            return inputMap;
        }
    }

    private static class LazyInputMapEx
    implements UIDefaults.LazyValue {
        private final Object[][] bindingsArray;

        LazyInputMapEx(Object[] ... bindingsArray) {
            this.bindingsArray = bindingsArray;
        }

        @Override
        public Object createValue(UIDefaults table) {
            InputMapUIResource inputMap = new InputMapUIResource();
            for (Object[] bindings : this.bindingsArray) {
                LookAndFeel.loadKeyBindings(inputMap, bindings);
            }
            return inputMap;
        }
    }
}

