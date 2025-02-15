/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.lang.Nullable;

public class StopWatch {
    private final String id;
    private boolean keepTaskList = true;
    private final List<TaskInfo> taskList = new ArrayList<TaskInfo>(1);
    private long startTimeNanos;
    @Nullable
    private String currentTaskName;
    @Nullable
    private TaskInfo lastTaskInfo;
    private int taskCount;
    private long totalTimeNanos;

    public StopWatch() {
        this("");
    }

    public StopWatch(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setKeepTaskList(boolean keepTaskList) {
        this.keepTaskList = keepTaskList;
    }

    public void start() throws IllegalStateException {
        this.start("");
    }

    public void start(String taskName) throws IllegalStateException {
        if (this.currentTaskName != null) {
            throw new IllegalStateException("Can't start StopWatch: it's already running");
        }
        this.currentTaskName = taskName;
        this.startTimeNanos = System.nanoTime();
    }

    public void stop() throws IllegalStateException {
        if (this.currentTaskName == null) {
            throw new IllegalStateException("Can't stop StopWatch: it's not running");
        }
        long lastTime = System.nanoTime() - this.startTimeNanos;
        this.totalTimeNanos += lastTime;
        this.lastTaskInfo = new TaskInfo(this.currentTaskName, lastTime);
        if (this.keepTaskList) {
            this.taskList.add(this.lastTaskInfo);
        }
        ++this.taskCount;
        this.currentTaskName = null;
    }

    public boolean isRunning() {
        return this.currentTaskName != null;
    }

    @Nullable
    public String currentTaskName() {
        return this.currentTaskName;
    }

    public long getLastTaskTimeNanos() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeNanos();
    }

    public long getLastTaskTimeMillis() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeMillis();
    }

    public String getLastTaskName() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task name");
        }
        return this.lastTaskInfo.getTaskName();
    }

    public TaskInfo getLastTaskInfo() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task info");
        }
        return this.lastTaskInfo;
    }

    public long getTotalTimeNanos() {
        return this.totalTimeNanos;
    }

    public long getTotalTimeMillis() {
        return StopWatch.nanosToMillis(this.totalTimeNanos);
    }

    public double getTotalTimeSeconds() {
        return StopWatch.nanosToSeconds(this.totalTimeNanos);
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public TaskInfo[] getTaskInfo() {
        if (!this.keepTaskList) {
            throw new UnsupportedOperationException("Task info is not being kept!");
        }
        return this.taskList.toArray(new TaskInfo[0]);
    }

    public String shortSummary() {
        return "StopWatch '" + this.getId() + "': running time = " + this.getTotalTimeNanos() + " ns";
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(this.shortSummary());
        sb.append('\n');
        if (!this.keepTaskList) {
            sb.append("No task info kept");
        } else {
            sb.append("---------------------------------------------\n");
            sb.append("ns         %     Task name\n");
            sb.append("---------------------------------------------\n");
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(9);
            nf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(3);
            pf.setGroupingUsed(false);
            for (TaskInfo task : this.getTaskInfo()) {
                sb.append(nf.format(task.getTimeNanos())).append("  ");
                sb.append(pf.format((double)task.getTimeNanos() / (double)this.getTotalTimeNanos())).append("  ");
                sb.append(task.getTaskName()).append('\n');
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.shortSummary());
        if (this.keepTaskList) {
            for (TaskInfo task : this.getTaskInfo()) {
                sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getTimeNanos()).append(" ns");
                long percent = Math.round(100.0 * (double)task.getTimeNanos() / (double)this.getTotalTimeNanos());
                sb.append(" = ").append(percent).append('%');
            }
        } else {
            sb.append("; no task info kept");
        }
        return sb.toString();
    }

    private static long nanosToMillis(long duration) {
        return TimeUnit.NANOSECONDS.toMillis(duration);
    }

    private static double nanosToSeconds(long duration) {
        return (double)duration / 1.0E9;
    }

    public static final class TaskInfo {
        private final String taskName;
        private final long timeNanos;

        TaskInfo(String taskName, long timeNanos) {
            this.taskName = taskName;
            this.timeNanos = timeNanos;
        }

        public String getTaskName() {
            return this.taskName;
        }

        public long getTimeNanos() {
            return this.timeNanos;
        }

        public long getTimeMillis() {
            return StopWatch.nanosToMillis(this.timeNanos);
        }

        public double getTimeSeconds() {
            return StopWatch.nanosToSeconds(this.timeNanos);
        }
    }
}

