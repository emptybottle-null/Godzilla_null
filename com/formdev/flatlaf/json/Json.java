/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.JsonHandler;
import com.formdev.flatlaf.json.JsonParser;
import com.formdev.flatlaf.json.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static Object parse(Reader reader) throws IOException, ParseException {
        DefaultHandler handler = new DefaultHandler();
        new JsonParser(handler).parse(reader);
        return handler.getValue();
    }

    static class DefaultHandler
    extends JsonHandler<List<Object>, Map<String, Object>> {
        private Object value;

        DefaultHandler() {
        }

        @Override
        public List<Object> startArray() {
            return new ArrayList<Object>();
        }

        @Override
        public Map<String, Object> startObject() {
            return new LinkedHashMap<String, Object>();
        }

        @Override
        public void endNull() {
            this.value = "null";
        }

        @Override
        public void endBoolean(boolean bool) {
            this.value = bool ? "true" : "false";
        }

        @Override
        public void endString(String string) {
            this.value = string;
        }

        @Override
        public void endNumber(String string) {
            this.value = string;
        }

        @Override
        public void endArray(List<Object> array) {
            this.value = array;
        }

        @Override
        public void endObject(Map<String, Object> object) {
            this.value = object;
        }

        @Override
        public void endArrayValue(List<Object> array) {
            array.add(this.value);
        }

        @Override
        public void endObjectValue(Map<String, Object> object, String name) {
            object.put(name, this.value);
        }

        Object getValue() {
            return this.value;
        }
    }
}

