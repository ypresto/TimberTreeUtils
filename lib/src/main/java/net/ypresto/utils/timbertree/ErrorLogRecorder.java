package net.ypresto.utils.timbertree;

import java.util.Arrays;

/**
 * Stacktrace recorder used by {@link net.ypresto.utils.timbertree.CrashlyticsTree}.
 */
public class ErrorLogRecorder extends Throwable {
    public ErrorLogRecorder(String detailMessage) {
        super(detailMessage);
    }
}
