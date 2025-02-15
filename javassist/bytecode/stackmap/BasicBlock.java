/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.stackmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.MethodInfo;

public class BasicBlock {
    protected int position;
    protected int length;
    protected int incoming;
    protected BasicBlock[] exit;
    protected boolean stop;
    protected Catch toCatch;

    protected BasicBlock(int pos) {
        this.position = pos;
        this.length = 0;
        this.incoming = 0;
    }

    public static BasicBlock find(BasicBlock[] blocks, int pos) throws BadBytecode {
        for (BasicBlock b : blocks) {
            if (b.position > pos || pos >= b.position + b.length) continue;
            return b;
        }
        throw new BadBytecode("no basic block at " + pos);
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        String cname = this.getClass().getName();
        int i = cname.lastIndexOf(46);
        sbuf.append(i < 0 ? cname : cname.substring(i + 1));
        sbuf.append("[");
        this.toString2(sbuf);
        sbuf.append("]");
        return sbuf.toString();
    }

    protected void toString2(StringBuffer sbuf) {
        sbuf.append("pos=").append(this.position).append(", len=").append(this.length).append(", in=").append(this.incoming).append(", exit{");
        if (this.exit != null) {
            for (BasicBlock b : this.exit) {
                sbuf.append(b.position).append(",");
            }
        }
        sbuf.append("}, {");
        Catch th = this.toCatch;
        while (th != null) {
            sbuf.append("(").append(th.body.position).append(", ").append(th.typeIndex).append("), ");
            th = th.next;
        }
        sbuf.append("}");
    }

    public static class Maker {
        protected BasicBlock makeBlock(int pos) {
            return new BasicBlock(pos);
        }

        protected BasicBlock[] makeArray(int size) {
            return new BasicBlock[size];
        }

        private BasicBlock[] makeArray(BasicBlock b) {
            BasicBlock[] array = this.makeArray(1);
            array[0] = b;
            return array;
        }

        private BasicBlock[] makeArray(BasicBlock b1, BasicBlock b2) {
            BasicBlock[] array = this.makeArray(2);
            array[0] = b1;
            array[1] = b2;
            return array;
        }

        public BasicBlock[] make(MethodInfo minfo) throws BadBytecode {
            CodeAttribute ca = minfo.getCodeAttribute();
            if (ca == null) {
                return null;
            }
            CodeIterator ci = ca.iterator();
            return this.make(ci, 0, ci.getCodeLength(), ca.getExceptionTable());
        }

        public BasicBlock[] make(CodeIterator ci, int begin, int end, ExceptionTable et) throws BadBytecode {
            Map<Integer, Mark> marks = this.makeMarks(ci, begin, end, et);
            BasicBlock[] bb = this.makeBlocks(marks);
            this.addCatchers(bb, et);
            return bb;
        }

        private Mark makeMark(Map<Integer, Mark> table, int pos) {
            return this.makeMark0(table, pos, true, true);
        }

        private Mark makeMark(Map<Integer, Mark> table, int pos, BasicBlock[] jump, int size, boolean always) {
            Mark m = this.makeMark0(table, pos, false, false);
            m.setJump(jump, size, always);
            return m;
        }

        private Mark makeMark0(Map<Integer, Mark> table, int pos, boolean isBlockBegin, boolean isTarget) {
            Integer p = pos;
            Mark m = table.get(p);
            if (m == null) {
                m = new Mark(pos);
                table.put(p, m);
            }
            if (isBlockBegin) {
                if (m.block == null) {
                    m.block = this.makeBlock(pos);
                }
                if (isTarget) {
                    ++m.block.incoming;
                }
            }
            return m;
        }

        private Map<Integer, Mark> makeMarks(CodeIterator ci, int begin, int end, ExceptionTable et) throws BadBytecode {
            int index;
            ci.begin();
            ci.move(begin);
            HashMap<Integer, Mark> marks = new HashMap<Integer, Mark>();
            while (ci.hasNext() && (index = ci.next()) < end) {
                int op = ci.byteAt(index);
                if (153 <= op && op <= 166 || op == 198 || op == 199) {
                    Mark to = this.makeMark(marks, index + ci.s16bitAt(index + 1));
                    Mark next = this.makeMark(marks, index + 3);
                    this.makeMark(marks, index, this.makeArray(to.block, next.block), 3, false);
                    continue;
                }
                if (167 <= op && op <= 171) {
                    switch (op) {
                        case 167: {
                            this.makeGoto(marks, index, index + ci.s16bitAt(index + 1), 3);
                            break;
                        }
                        case 168: {
                            this.makeJsr(marks, index, index + ci.s16bitAt(index + 1), 3);
                            break;
                        }
                        case 169: {
                            this.makeMark(marks, index, null, 2, true);
                            break;
                        }
                        case 170: {
                            int p;
                            int pos = (index & 0xFFFFFFFC) + 4;
                            int low = ci.s32bitAt(pos + 4);
                            int high = ci.s32bitAt(pos + 8);
                            int ncases = high - low + 1;
                            BasicBlock[] to = this.makeArray(ncases + 1);
                            to[0] = this.makeMark(marks, (int)(index + ci.s32bitAt((int)pos))).block;
                            int n = p + ncases * 4;
                            int k = 1;
                            for (p = pos + 12; p < n; p += 4) {
                                to[k++] = this.makeMark(marks, (int)(index + ci.s32bitAt((int)p))).block;
                            }
                            this.makeMark(marks, index, to, n - index, true);
                            break;
                        }
                        case 171: {
                            int p;
                            int pos = (index & 0xFFFFFFFC) + 4;
                            int ncases = ci.s32bitAt(pos + 4);
                            BasicBlock[] to = this.makeArray(ncases + 1);
                            to[0] = this.makeMark(marks, (int)(index + ci.s32bitAt((int)pos))).block;
                            int n = p + ncases * 8 - 4;
                            int k = 1;
                            for (p = pos + 8 + 4; p < n; p += 8) {
                                to[k++] = this.makeMark(marks, (int)(index + ci.s32bitAt((int)p))).block;
                            }
                            this.makeMark(marks, index, to, n - index, true);
                            break;
                        }
                    }
                    continue;
                }
                if (172 <= op && op <= 177 || op == 191) {
                    this.makeMark(marks, index, null, 1, true);
                    continue;
                }
                if (op == 200) {
                    this.makeGoto(marks, index, index + ci.s32bitAt(index + 1), 5);
                    continue;
                }
                if (op == 201) {
                    this.makeJsr(marks, index, index + ci.s32bitAt(index + 1), 5);
                    continue;
                }
                if (op != 196 || ci.byteAt(index + 1) != 169) continue;
                this.makeMark(marks, index, null, 4, true);
            }
            if (et != null) {
                int i = et.size();
                while (--i >= 0) {
                    this.makeMark0(marks, et.startPc(i), true, false);
                    this.makeMark(marks, et.handlerPc(i));
                }
            }
            return marks;
        }

        private void makeGoto(Map<Integer, Mark> marks, int pos, int target, int size) {
            Mark to = this.makeMark(marks, target);
            BasicBlock[] jumps = this.makeArray(to.block);
            this.makeMark(marks, pos, jumps, size, true);
        }

        protected void makeJsr(Map<Integer, Mark> marks, int pos, int target, int size) throws BadBytecode {
            throw new JsrBytecode();
        }

        private BasicBlock[] makeBlocks(Map<Integer, Mark> markTable) {
            Object[] marks = markTable.values().toArray(new Mark[markTable.size()]);
            Arrays.sort(marks);
            ArrayList<BasicBlock> blocks = new ArrayList<BasicBlock>();
            int i = 0;
            BasicBlock prev = marks.length > 0 && ((Mark)marks[0]).position == 0 && ((Mark)marks[0]).block != null ? Maker.getBBlock((Mark)marks[i++]) : this.makeBlock(0);
            blocks.add(prev);
            while (i < marks.length) {
                Object m;
                BasicBlock bb;
                if ((bb = Maker.getBBlock((Mark)(m = marks[i++]))) == null) {
                    if (prev.length > 0) {
                        prev = this.makeBlock(prev.position + prev.length);
                        blocks.add(prev);
                    }
                    prev.length = ((Mark)m).position + ((Mark)m).size - prev.position;
                    prev.exit = ((Mark)m).jump;
                    prev.stop = ((Mark)m).alwaysJmp;
                    continue;
                }
                if (prev.length == 0) {
                    prev.length = ((Mark)m).position - prev.position;
                    ++bb.incoming;
                    prev.exit = this.makeArray(bb);
                } else if (prev.position + prev.length < ((Mark)m).position) {
                    prev = this.makeBlock(prev.position + prev.length);
                    blocks.add(prev);
                    prev.length = ((Mark)m).position - prev.position;
                    prev.stop = true;
                    prev.exit = this.makeArray(bb);
                }
                blocks.add(bb);
                prev = bb;
            }
            return blocks.toArray(this.makeArray(blocks.size()));
        }

        private static BasicBlock getBBlock(Mark m) {
            BasicBlock b = m.block;
            if (b != null && m.size > 0) {
                b.exit = m.jump;
                b.length = m.size;
                b.stop = m.alwaysJmp;
            }
            return b;
        }

        private void addCatchers(BasicBlock[] blocks, ExceptionTable et) throws BadBytecode {
            if (et == null) {
                return;
            }
            int i = et.size();
            while (--i >= 0) {
                BasicBlock handler = BasicBlock.find(blocks, et.handlerPc(i));
                int start = et.startPc(i);
                int end = et.endPc(i);
                int type = et.catchType(i);
                --handler.incoming;
                for (int k = 0; k < blocks.length; ++k) {
                    BasicBlock bb = blocks[k];
                    int iPos = bb.position;
                    if (start > iPos || iPos >= end) continue;
                    bb.toCatch = new Catch(handler, type, bb.toCatch);
                    ++handler.incoming;
                }
            }
        }
    }

    static class Mark
    implements Comparable<Mark> {
        int position;
        BasicBlock block;
        BasicBlock[] jump;
        boolean alwaysJmp;
        int size;
        Catch catcher;

        Mark(int p) {
            this.position = p;
            this.block = null;
            this.jump = null;
            this.alwaysJmp = false;
            this.size = 0;
            this.catcher = null;
        }

        @Override
        public int compareTo(Mark obj) {
            if (null == obj) {
                return -1;
            }
            return this.position - obj.position;
        }

        void setJump(BasicBlock[] bb, int s, boolean always) {
            this.jump = bb;
            this.size = s;
            this.alwaysJmp = always;
        }
    }

    public static class Catch {
        public Catch next;
        public BasicBlock body;
        public int typeIndex;

        Catch(BasicBlock b, int i, Catch c) {
            this.body = b;
            this.typeIndex = i;
            this.next = c;
        }
    }

    static class JsrBytecode
    extends BadBytecode {
        private static final long serialVersionUID = 1L;

        JsrBytecode() {
            super("JSR");
        }
    }
}

