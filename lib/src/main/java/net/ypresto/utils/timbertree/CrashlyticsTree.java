package net.ypresto.utils.timbertree;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which send reports to Crashlytics.
 * It reports log priority of error as non-fatal exception.
 * Also it will send custom log for
 *
 * @author ypresto
 */
public class CrashlyticsTree extends Timber.HollowTree {
    public CrashlyticsTree() {
        // Ensure crashlytics class is available, fail-fast if not available.
        Crashlytics.class.getCanonicalName();
    }

    @Override
    public void e(String message, Object... args) {
        Crashlytics.logException(new ErrorLogRecorder(message));
    }

    @Override
    public void e(Throwable t, String message, Object... args) {
        Crashlytics.log(message);
        Crashlytics.logException(t);
    }

}
