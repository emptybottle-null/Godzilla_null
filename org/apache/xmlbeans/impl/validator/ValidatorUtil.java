/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.validator;

import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.validator.Validator;

public class ValidatorUtil {
    public static boolean validateSimpleType(SchemaType type, String value, Collection errors, PrefixResolver prefixResolver) {
        if (!type.isSimpleType() && type.getContentType() != 2) {
            assert (false);
            throw new RuntimeException("Not a simple type");
        }
        Validator validator = new Validator(type, null, type.getTypeSystem(), null, errors);
        EventImpl ev = new EventImpl(prefixResolver, value);
        validator.nextEvent(1, ev);
        validator.nextEvent(3, ev);
        validator.nextEvent(2, ev);
        return validator.isValid();
    }

    private static class EventImpl
    implements ValidatorListener.Event {
        PrefixResolver _prefixResolver;
        String _text;

        EventImpl(PrefixResolver prefixResolver, String text) {
            this._prefixResolver = prefixResolver;
            this._text = text;
        }

        @Override
        public XmlCursor getLocationAsCursor() {
            return null;
        }

        @Override
        public Location getLocation() {
            return null;
        }

        @Override
        public String getXsiType() {
            return null;
        }

        @Override
        public String getXsiNil() {
            return null;
        }

        @Override
        public String getXsiLoc() {
            return null;
        }

        @Override
        public String getXsiNoLoc() {
            return null;
        }

        @Override
        public QName getName() {
            return null;
        }

        @Override
        public String getText() {
            return this._text;
        }

        @Override
        public String getText(int wsr) {
            return XmlWhitespace.collapse(this._text, wsr);
        }

        @Override
        public boolean textIsWhitespace() {
            return false;
        }

        @Override
        public String getNamespaceForPrefix(String prefix) {
            return this._prefixResolver.getNamespaceForPrefix(prefix);
        }
    }
}

