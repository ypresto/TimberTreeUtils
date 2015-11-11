package net.ypresto.utils.timbertree;

import android.util.Log;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which throws {@link java.lang.Error} when priority of
 * log is exceeded the limit. Useful for development or test environment.
 *
 * @author ypresto
 */
public class ThrowErrorTree extends Timber.Tree {
    private final int mLogPriority;
    private final ExclusionStrategy mExclusionStrategy;

    /**
     * Create instance with default log priority of ERROR.
     */
    public ThrowErrorTree() {
        this(Log.ERROR);
    }

    /**
     * @param logPriority Minimum log priority to throw error. Expects one of constants defined in {@link Log}.
     */
    public ThrowErrorTree(int logPriority) {
        this(logPriority, NullExclusionStrategy.INSTANCE);
    }

    /**
     * @param logPriority       Minimum log priority to throw error. Expects one of constants defined in {@link Log}.
     * @param exclusionStrategy Strategy used to skip throwing error.
     */
    public ThrowErrorTree(int logPriority, ExclusionStrategy exclusionStrategy) {
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

        if (t != null) {
            throw new LogPriorityExceededError(priority, mLogPriority, t);
        } else {
            throw new LogPriorityExceededError(priority, mLogPriority);
        }
    }

}
