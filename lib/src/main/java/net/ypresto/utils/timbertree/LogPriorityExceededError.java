package net.ypresto.utils.timbertree;

/**
 * Exception thrown by {@link ThrowErrorTree}.
 */
class LogPriorityExceededError extends Error {
    private static final String LOG_FORMAT = "Log priority exceeded: actual:%d >= minimum:%d";

    LogPriorityExceededError(int priority, int failFastPriority, Throwable throwable) {
        super(String.format(LOG_FORMAT, priority, failFastPriority), throwable);
    }

    LogPriorityExceededError(int priority, int failFastPriority) {
        super(String.format(LOG_FORMAT, priority, failFastPriority));
    }
}
