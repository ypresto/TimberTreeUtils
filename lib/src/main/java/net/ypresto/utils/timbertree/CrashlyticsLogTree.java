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
    private final ExclusionStrategy mExclusionStrategy;

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
        this(logPriority, NullExclusionStrategy.INSTANCE);
    }

    /**
     * @param logPriority       Minimum log priority to send log. Expects one of constants defined in {@link Log}.
     * @param exclusionStrategy Strategy used to skip logging.
     */
    public CrashlyticsLogTree(int logPriority, ExclusionStrategy exclusionStrategy) {
        // Ensure crashlytics class is available, fail-fast if not available.
        Crashlytics.class.getCanonicalName();
        mLogPriority = logPriority;
        mExclusionStrategy = exclusionStrategy;
    }

    @Override
    protected boolean isLoggable(int priority) {
        return priority >= mLogPriority;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (mExclusionStrategy.shouldSkipForLog(priority, tag, message, t)) {
            return;
        }

        String messageWithTag = "[" + tag + "] " + message;
        Crashlytics.log(messageWithTag);
    }
}
