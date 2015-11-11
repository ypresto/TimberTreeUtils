package net.ypresto.utils.timbertree;

public interface ExclusionStrategy {
    /**
     * @param priority Log priority.
     * @param tag      Tag for log.
     * @param message  Formatted log message.
     * @param t        Accompanying exceptions.
     * @return {@code true} if the log should be skipped, otherwise {@code false}.
     * @see timber.log.Timber.Tree#log(int, String, String, Throwable)
     */
    boolean shouldSkipForLog(int priority, String tag, String message, Throwable t);
}
