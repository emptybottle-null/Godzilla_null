/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Dimension;
import java.io.InputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.ScopeProvider;
import org.mozilla.javascript.tools.debugger.SourceProvider;
import org.mozilla.javascript.tools.debugger.SwingGui;
import org.mozilla.javascript.tools.shell.Global;

public class Main {
    private Dim dim = new Dim();
    private SwingGui debugGui;

    public Main(String title) {
        this.debugGui = new SwingGui(this.dim, title);
    }

    public JFrame getDebugFrame() {
        return this.debugGui;
    }

    public void doBreak() {
        this.dim.setBreak();
    }

    public void setBreakOnExceptions(boolean value) {
        this.dim.setBreakOnExceptions(value);
        this.debugGui.getMenubar().getBreakOnExceptions().setSelected(value);
    }

    public void setBreakOnEnter(boolean value) {
        this.dim.setBreakOnEnter(value);
        this.debugGui.getMenubar().getBreakOnEnter().setSelected(value);
    }

    public void setBreakOnReturn(boolean value) {
        this.dim.setBreakOnReturn(value);
        this.debugGui.getMenubar().getBreakOnReturn().setSelected(value);
    }

    public void clearAllBreakpoints() {
        this.dim.clearAllBreakpoints();
    }

    public void go() {
        this.dim.go();
    }

    public void setScope(Scriptable scope) {
        this.setScopeProvider(IProxy.newScopeProvider(scope));
    }

    public void setScopeProvider(ScopeProvider p) {
        this.dim.setScopeProvider(p);
    }

    public void setSourceProvider(SourceProvider sourceProvider) {
        this.dim.setSourceProvider(sourceProvider);
    }

    public void setExitAction(Runnable r) {
        this.debugGui.setExitAction(r);
    }

    public InputStream getIn() {
        return this.debugGui.getConsole().getIn();
    }

    public PrintStream getOut() {
        return this.debugGui.getConsole().getOut();
    }

    public PrintStream getErr() {
        return this.debugGui.getConsole().getErr();
    }

    public void pack() {
        this.debugGui.pack();
    }

    public void setSize(int w, int h) {
        this.debugGui.setSize(w, h);
    }

    public void setVisible(boolean flag) {
        this.debugGui.setVisible(flag);
    }

    public boolean isVisible() {
        return this.debugGui.isVisible();
    }

    public void dispose() {
        this.clearAllBreakpoints();
        this.dim.go();
        this.debugGui.dispose();
        this.dim = null;
    }

    public void attachTo(ContextFactory factory) {
        this.dim.attachTo(factory);
    }

    public void detach() {
        this.dim.detach();
    }

    public static void main(String[] args) {
        Main main = new Main("Rhino JavaScript Debugger");
        main.doBreak();
        main.setExitAction(new IProxy(1));
        System.setIn(main.getIn());
        System.setOut(main.getOut());
        System.setErr(main.getErr());
        Global global = org.mozilla.javascript.tools.shell.Main.getGlobal();
        global.setIn(main.getIn());
        global.setOut(main.getOut());
        global.setErr(main.getErr());
        main.attachTo(org.mozilla.javascript.tools.shell.Main.shellContextFactory);
        main.setScope(global);
        main.pack();
        main.setSize(600, 460);
        main.setVisible(true);
        org.mozilla.javascript.tools.shell.Main.exec(args);
    }

    public static Main mainEmbedded(String title) {
        ContextFactory factory = ContextFactory.getGlobal();
        Global global = new Global();
        global.init(factory);
        return Main.mainEmbedded(factory, global, title);
    }

    public static Main mainEmbedded(ContextFactory factory, Scriptable scope, String title) {
        return Main.mainEmbeddedImpl(factory, scope, title);
    }

    public static Main mainEmbedded(ContextFactory factory, ScopeProvider scopeProvider, String title) {
        return Main.mainEmbeddedImpl(factory, scopeProvider, title);
    }

    private static Main mainEmbeddedImpl(ContextFactory factory, Object scopeProvider, String title) {
        if (title == null) {
            title = "Rhino JavaScript Debugger (embedded usage)";
        }
        Main main = new Main(title);
        main.doBreak();
        main.setExitAction(new IProxy(1));
        main.attachTo(factory);
        if (scopeProvider instanceof ScopeProvider) {
            main.setScopeProvider((ScopeProvider)scopeProvider);
        } else {
            Scriptable scope = (Scriptable)scopeProvider;
            if (scope instanceof Global) {
                Global global = (Global)scope;
                global.setIn(main.getIn());
                global.setOut(main.getOut());
                global.setErr(main.getErr());
            }
            main.setScope(scope);
        }
        main.pack();
        main.setSize(600, 460);
        main.setVisible(true);
        return main;
    }

    @Deprecated
    public void setSize(Dimension dimension) {
        this.debugGui.setSize(dimension.width, dimension.height);
    }

    @Deprecated
    public void setOptimizationLevel(int level) {
    }

    @Deprecated
    public void contextEntered(Context cx) {
        throw new IllegalStateException();
    }

    @Deprecated
    public void contextExited(Context cx) {
        throw new IllegalStateException();
    }

    @Deprecated
    public void contextCreated(Context cx) {
        throw new IllegalStateException();
    }

    @Deprecated
    public void contextReleased(Context cx) {
        throw new IllegalStateException();
    }

    private static class IProxy
    implements Runnable,
    ScopeProvider {
        public static final int EXIT_ACTION = 1;
        public static final int SCOPE_PROVIDER = 2;
        private final int type;
        private Scriptable scope;

        public IProxy(int type) {
            this.type = type;
        }

        public static ScopeProvider newScopeProvider(Scriptable scope) {
            IProxy scopeProvider = new IProxy(2);
            scopeProvider.scope = scope;
            return scopeProvider;
        }

        @Override
        public void run() {
            if (this.type != 1) {
                Kit.codeBug();
            }
            System.exit(0);
        }

        @Override
        public Scriptable getScope() {
            if (this.type != 2) {
                Kit.codeBug();
            }
            if (this.scope == null) {
                Kit.codeBug();
            }
            return this.scope;
        }
    }
}

