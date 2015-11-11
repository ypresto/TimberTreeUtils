package net.ypresto.utils.timbertree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import timber.log.Timber;

/**
 * Records stack trace without timber code. Used by {@link CrashlyticsLogTree} to purify report.
 */
class StackTraceRecorder extends Throwable {
    public StackTraceRecorder(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public Throwable fillInStackTrace() {
        super.fillInStackTrace();
        StackTraceElement[] original = getStackTrace();
        Iterator<StackTraceElement> iterator = Arrays.asList(original).iterator();
        List<StackTraceElement> filtered = new ArrayList<>();

        // heading to top of Timber stack trace
        while (iterator.hasNext()) {
            StackTraceElement stackTraceElement = iterator.next();
            if (isTimber(stackTraceElement)) {
                break;
            }
        }

        // copy all
        boolean isReachedApp = false;
        while (iterator.hasNext()) {
            StackTraceElement stackTraceElement = iterator.next();
            // skip Timber
            if (!isReachedApp && isTimber(stackTraceElement)) {
                continue;
            }
            isReachedApp = true;
            filtered.add(stackTraceElement);
        }

        setStackTrace(filtered.toArray(new StackTraceElement[filtered.size()]));
        return this;
    }

    private boolean isTimber(StackTraceElement stackTraceElement) {
        return stackTraceElement.getClassName().equals(Timber.class.getName());
    }
}
