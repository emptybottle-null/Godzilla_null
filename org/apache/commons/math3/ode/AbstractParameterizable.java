/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.ode.Parameterizable;
import org.apache.commons.math3.ode.UnknownParameterException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractParameterizable
implements Parameterizable {
    private final Collection<String> parametersNames = new ArrayList<String>();

    protected AbstractParameterizable(String ... names) {
        for (String name : names) {
            this.parametersNames.add(name);
        }
    }

    protected AbstractParameterizable(Collection<String> names) {
        this.parametersNames.addAll(names);
    }

    @Override
    public Collection<String> getParametersNames() {
        return this.parametersNames;
    }

    @Override
    public boolean isSupported(String name) {
        for (String supportedName : this.parametersNames) {
            if (!supportedName.equals(name)) continue;
            return true;
        }
        return false;
    }

    public void complainIfNotSupported(String name) throws UnknownParameterException {
        if (!this.isSupported(name)) {
            throw new UnknownParameterException(name);
        }
    }
}

