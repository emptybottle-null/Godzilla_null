/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.property;

import java.util.List;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;
import org.apache.poi.poifs.property.RootProperty;

final class PropertyFactory {
    private PropertyFactory() {
    }

    static void convertToProperties(byte[] data, List<Property> properties) {
        int property_count = data.length / 128;
        int offset = 0;
        for (int k = 0; k < property_count; ++k) {
            switch (data[offset + 66]) {
                case 1: {
                    properties.add(new DirectoryProperty(properties.size(), data, offset));
                    break;
                }
                case 2: {
                    properties.add(new DocumentProperty(properties.size(), data, offset));
                    break;
                }
                case 5: {
                    properties.add(new RootProperty(properties.size(), data, offset));
                    break;
                }
                default: {
                    properties.add(null);
                }
            }
            offset += 128;
        }
    }
}

