package net.ypresto.utils.timbertree;

import android.util.Log;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which throws {@link java.lang.Error} when priority of
 * log is exceeded the limit. Useful for developing or testing environment.
 *
 * @author ypresto
 */
public class FailFastTree implements Timber.Tree {
    private final int mFailFastPriority;

    /**
     * Create instance with default log priority to throw an error.
     * Default priority is ERROR.
     */
    public FailFastTree() {
        this(Log.ERROR);
    }

    /**
     * Create instance with minimum log priority to throw an error.
     *
     * @param failFastPriority Tree will throw error if priority of log is greater or equal than
     *                         specified value. Expected to be one of constants from {@link Log}.
     */
    public FailFastTree(int failFastPriority) {
        mFailFastPriority = failFastPriority;
    }

    private void assertPriority(int priority, Throwable t) {
        if (priority >= mFailFastPriority) {
            if (t != null)
                throw new LogPriorityExceededException(priority, mFailFastPriority, t);
            else
                throw new LogPriorityExceededException(priority, mFailFastPriority);
        }
    }

    @Override
    public void v(String s, Object... objects) {
        assertPriority(Log.VERBOSE, null);
    }

    @Override
    public void v(Throwable throwable, String s, Object... objects) {
        assertPriority(Log.VERBOSE, throwable);
    }

    @Override
    public void d(String s, Object... objects) {
        assertPriority(Log.DEBUG, null);
    }

    @Override
    public void d(Throwable throwable, String s, Object... objects) {
        assertPriority(Log.DEBUG, throwable);
    }

    @Override
    public void i(String s, Object... objects) {
        assertPriority(Log.INFO, null);
    }

    @Override
    public void i(Throwable throwable, String s, Object... objects) {
        assertPriority(Log.INFO, throwable);
    }

    @Override
    public void w(String s, Object... objects) {
        assertPriority(Log.WARN, null);
    }

    @Override
    public void w(Throwable throwable, String s, Object... objects) {
        assertPriority(Log.WARN, throwable);
    }

    @Override
    public void e(String s, Object... objects) {
        assertPriority(Log.ERROR, null);
    }

    @Override
    public void e(Throwable throwable, String s, Object... objects) {
        assertPriority(Log.ERROR, throwable);
    }
}
