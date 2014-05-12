package net.ypresto.utils.timbertree;

/**
 * Exception thrown by {@link net.ypresto.utils.timbertree.FailFastTree}.
 */
public class LogPriorityExceededException extends RuntimeException {
    private static final String LOG_FORMAT = "Log priority exceeded: actual:%d >= fail-fast:%d";

    LogPriorityExceededException(int priority, int failFastPriority, Throwable throwable) {
        super(String.format(LOG_FORMAT, priority, failFastPriority), throwable);
    }

    LogPriorityExceededException(int priority, int failFastPriority) {
        super(String.format(LOG_FORMAT, priority, failFastPriority));
    }
}
