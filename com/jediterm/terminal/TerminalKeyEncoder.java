/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.util.CharUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class TerminalKeyEncoder {
    private static final int ESC = 27;
    private final Map<KeyCodeAndModifier, byte[]> myKeyCodes = new HashMap<KeyCodeAndModifier, byte[]>();
    private boolean myAltSendsEscape = true;
    private boolean myMetaSendsEscape = false;

    public TerminalKeyEncoder() {
        this.setAutoNewLine(false);
        this.arrowKeysApplicationSequences();
        this.keypadAnsiSequences();
        this.putCode(8, 127);
        this.putCode(112, 27, 79, 80);
        this.putCode(113, 27, 79, 81);
        this.putCode(114, 27, 79, 82);
        this.putCode(115, 27, 79, 83);
        this.putCode(116, 27, 91, 49, 53, 126);
        this.putCode(117, 27, 91, 49, 55, 126);
        this.putCode(118, 27, 91, 49, 56, 126);
        this.putCode(119, 27, 91, 49, 57, 126);
        this.putCode(120, 27, 91, 50, 48, 126);
        this.putCode(121, 27, 91, 50, 49, 126);
        this.putCode(122, 27, 91, 50, 51, 126, 27);
        this.putCode(123, 27, 91, 50, 52, 126, 8);
        this.putCode(155, 27, 91, 50, 126);
        this.putCode(127, 27, 91, 51, 126);
        this.putCode(33, 27, 91, 53, 126);
        this.putCode(34, 27, 91, 54, 126);
        this.putCode(36, 27, 91, 72);
        this.putCode(35, 27, 91, 70);
    }

    public void arrowKeysApplicationSequences() {
        this.putCode(38, 27, 79, 65);
        this.putCode(40, 27, 79, 66);
        this.putCode(39, 27, 79, 67);
        this.putCode(37, 27, 79, 68);
        if (UIUtil.isLinux) {
            this.putCode(new KeyCodeAndModifier(39, 2), 27, 91, 49, 59, 53, 67);
            this.putCode(new KeyCodeAndModifier(37, 2), 27, 91, 49, 59, 53, 68);
            this.putCode(new KeyCodeAndModifier(39, 8), 27, 91, 49, 59, 51, 67);
            this.putCode(new KeyCodeAndModifier(37, 8), 27, 91, 49, 59, 51, 68);
        } else {
            this.putCode(new KeyCodeAndModifier(39, 8), 27, 102);
            this.putCode(new KeyCodeAndModifier(37, 8), 27, 98);
        }
    }

    public void arrowKeysAnsiCursorSequences() {
        this.putCode(38, 27, 91, 65);
        this.putCode(40, 27, 91, 66);
        this.putCode(39, 27, 91, 67);
        this.putCode(37, 27, 91, 68);
        if (UIUtil.isMac) {
            this.putCode(new KeyCodeAndModifier(39, 8), 27, 102);
            this.putCode(new KeyCodeAndModifier(37, 8), 27, 98);
        }
    }

    public void keypadApplicationSequences() {
        this.putCode(225, 27, 79, 66);
        this.putCode(226, 27, 79, 68);
        this.putCode(227, 27, 79, 67);
        this.putCode(224, 27, 79, 65);
        this.putCode(36, 27, 79, 72);
        this.putCode(35, 27, 79, 70);
    }

    public void keypadAnsiSequences() {
        this.putCode(225, 27, 91, 66);
        this.putCode(226, 27, 91, 68);
        this.putCode(227, 27, 91, 67);
        this.putCode(224, 27, 91, 65);
        this.putCode(36, 27, 91, 72);
        this.putCode(35, 27, 91, 70);
    }

    void putCode(int code, int ... bytesAsInt) {
        this.myKeyCodes.put(new KeyCodeAndModifier(code, 0), CharUtils.makeCode(bytesAsInt));
    }

    private void putCode(@NotNull KeyCodeAndModifier key, int ... bytesAsInt) {
        if (key == null) {
            TerminalKeyEncoder.$$$reportNull$$$0(0);
        }
        this.myKeyCodes.put(key, CharUtils.makeCode(bytesAsInt));
    }

    public byte[] getCode(int key, int modifiers) {
        byte[] bytes = this.myKeyCodes.get(new KeyCodeAndModifier(key, modifiers));
        if (bytes != null) {
            return bytes;
        }
        bytes = this.myKeyCodes.get(new KeyCodeAndModifier(key, 0));
        if (bytes == null) {
            return null;
        }
        if ((this.myAltSendsEscape || this.alwaysSendEsc(key)) && (modifiers & 8) != 0) {
            return TerminalKeyEncoder.insertCodeAt(bytes, CharUtils.makeCode(27), 0);
        }
        if ((this.myMetaSendsEscape || this.alwaysSendEsc(key)) && (modifiers & 4) != 0) {
            return TerminalKeyEncoder.insertCodeAt(bytes, CharUtils.makeCode(27), 0);
        }
        if (this.isCursorKey(key)) {
            return this.getCodeWithModifiers(bytes, modifiers);
        }
        return bytes;
    }

    private boolean alwaysSendEsc(int key) {
        return this.isCursorKey(key) || key == 8;
    }

    private boolean isCursorKey(int key) {
        return key == 40 || key == 38 || key == 37 || key == 39 || key == 36 || key == 35;
    }

    private byte[] getCodeWithModifiers(byte[] bytes, int modifiers) {
        int code = TerminalKeyEncoder.modifiersToCode(modifiers);
        if (code > 0) {
            return TerminalKeyEncoder.insertCodeAt(bytes, Integer.toString(code).getBytes(), bytes.length - 1);
        }
        return bytes;
    }

    private static byte[] insertCodeAt(byte[] bytes, byte[] code, int at) {
        byte[] res = new byte[bytes.length + code.length];
        System.arraycopy(bytes, 0, res, 0, bytes.length);
        System.arraycopy(bytes, at, res, at + code.length, bytes.length - at);
        System.arraycopy(code, 0, res, at, code.length);
        return res;
    }

    private static int modifiersToCode(int modifiers) {
        int code = 0;
        if ((modifiers & 1) != 0) {
            code |= 1;
        }
        if ((modifiers & 8) != 0) {
            code |= 2;
        }
        if ((modifiers & 2) != 0) {
            code |= 4;
        }
        if ((modifiers & 4) != 0) {
            code |= 8;
        }
        return code != 0 ? code + 1 : code;
    }

    public void setAutoNewLine(boolean enabled) {
        if (enabled) {
            this.putCode(10, 13, 10);
        } else {
            this.putCode(10, 13);
        }
    }

    public void setAltSendsEscape(boolean altSendsEscape) {
        this.myAltSendsEscape = altSendsEscape;
    }

    public void setMetaSendsEscape(boolean metaSendsEscape) {
        this.myMetaSendsEscape = metaSendsEscape;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jediterm/terminal/TerminalKeyEncoder", "putCode"));
    }

    private static class KeyCodeAndModifier {
        private final int myCode;
        private final int myModifier;

        public KeyCodeAndModifier(int code, int modifier) {
            this.myCode = code;
            this.myModifier = modifier;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            KeyCodeAndModifier that = (KeyCodeAndModifier)o;
            return this.myCode == that.myCode && this.myModifier == that.myModifier;
        }

        public int hashCode() {
            return Objects.hash(this.myCode, this.myModifier);
        }
    }
}

