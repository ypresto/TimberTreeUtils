package net.ypresto.utils.timbertree;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which sends log to Crashlytics.
 *
 * @author ypresto
 * @see CrashlyticsLogExceptionTree
 */
public class CrashlyticsLogTree extends Timber.Tree {
    private final int mLogPriority;
    private final LogExclusionStrategy mLogExclusionStrategy;

    /**
     * Create instance with default log priority of WARN.
     */
    public CrashlyticsLogTree() {
        this(Log.WARN);
    }

    /**
     * @param logPriority Minimum log priority to send log. Expects one of constants defined in {@link Log}.
     */
    public CrashlyticsLogTree(int logPriority) {
        this(logPriority, NullLogExclusionStrategy.INSTANCE);
    }

    /**
     * @param logPriority          Minimum log priority to send log. Expects one of constants defined in {@link Log}.
     * @param logExclusionStrategy Strategy used to skip logging.
     */
    public CrashlyticsLogTree(int logPriority, LogExclusionStrategy logExclusionStrategy) {
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

        String messageWithTag = "[" + tag + "] " + message;
        Crashlytics.log(messageWithTag);
    }
}
