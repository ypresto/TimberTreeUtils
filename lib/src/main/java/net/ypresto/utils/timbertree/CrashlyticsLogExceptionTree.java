package net.ypresto.utils.timbertree;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which sends exception to Crashlytics.
 *
 * @author ypresto
 * @see CrashlyticsLogExceptionTree
 */
public class CrashlyticsLogExceptionTree extends Timber.Tree {
    private final int mLogPriority;
    private final LogExclusionStrategy mLogExclusionStrategy;

    /**
     * Create instance with default log priority of ERROR.
     */
    public CrashlyticsLogExceptionTree() {
        this(Log.ERROR);
    }

    /**
     * @param logPriority Minimum log priority to send exception. Expects one of constants defined in {@link Log}.
     */
    public CrashlyticsLogExceptionTree(int logPriority) {
        this(logPriority, NullLogExclusionStrategy.INSTANCE);
    }

    /**
     * @param logPriority          Minimum log priority to send exception. Expects one of constants defined in {@link Log}.
     * @param logExclusionStrategy Strategy used to skip throwing error for log.
     */
    public CrashlyticsLogExceptionTree(int logPriority, LogExclusionStrategy logExclusionStrategy) {
        // Ensure crashlytics class is available, fail-fast if not available.
        Crashlytics.class.getCanonicalName();
        mLogPriority = logPriority;
        mLogExclusionStrategy = logExclusionStrategy;
    }

    @Override
    protected boolean isLoggable(int priority) {
        return priority >= mLogPriority;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (mLogExclusionStrategy.shouldSkipForLog(priority, tag, message, t)) {
            return;
        }

        if (t != null) {
            Crashlytics.logException(t);
        } else {
            String messageWithTag = "[" + tag + "] " + message;
            Crashlytics.logException(new StackTraceRecorder(messageWithTag));
        }
    }
}
