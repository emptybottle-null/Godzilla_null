/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.ByteVector;
import org.springframework.asm.Label;

final class Handler {
    final Label startPc;
    final Label endPc;
    final Label handlerPc;
    final int catchType;
    final String catchTypeDescriptor;
    Handler nextHandler;

    Handler(Label startPc, Label endPc, Label handlerPc, int catchType, String catchTypeDescriptor) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
        this.catchTypeDescriptor = catchTypeDescriptor;
    }

    Handler(Handler handler, Label startPc, Label endPc) {
        this(startPc, endPc, handler.handlerPc, handler.catchType, handler.catchTypeDescriptor);
        this.nextHandler = handler.nextHandler;
    }

    static Handler removeRange(Handler firstHandler, Label start, Label end) {
        int rangeEnd;
        if (firstHandler == null) {
            return null;
        }
        firstHandler.nextHandler = Handler.removeRange(firstHandler.nextHandler, start, end);
        int handlerStart = firstHandler.startPc.bytecodeOffset;
        int handlerEnd = firstHandler.endPc.bytecodeOffset;
        int rangeStart = start.bytecodeOffset;
        int n = rangeEnd = end == null ? Integer.MAX_VALUE : end.bytecodeOffset;
        if (rangeStart >= handlerEnd || rangeEnd <= handlerStart) {
            return firstHandler;
        }
        if (rangeStart <= handlerStart) {
            if (rangeEnd >= handlerEnd) {
                return firstHandler.nextHandler;
            }
            return new Handler(firstHandler, end, firstHandler.endPc);
        }
        if (rangeEnd >= handlerEnd) {
            return new Handler(firstHandler, firstHandler.startPc, start);
        }
        firstHandler.nextHandler = new Handler(firstHandler, end, firstHandler.endPc);
        return new Handler(firstHandler, firstHandler.startPc, start);
    }

    static int getExceptionTableLength(Handler firstHandler) {
        int length = 0;
        Handler handler = firstHandler;
        while (handler != null) {
            ++length;
            handler = handler.nextHandler;
        }
        return length;
    }

    static int getExceptionTableSize(Handler firstHandler) {
        return 2 + 8 * Handler.getExceptionTableLength(firstHandler);
    }

    static void putExceptionTable(Handler firstHandler, ByteVector output) {
        output.putShort(Handler.getExceptionTableLength(firstHandler));
        Handler handler = firstHandler;
        while (handler != null) {
            output.putShort(handler.startPc.bytecodeOffset).putShort(handler.endPc.bytecodeOffset).putShort(handler.handlerPc.bytecodeOffset).putShort(handler.catchType);
            handler = handler.nextHandler;
        }
    }
}

