package net.ypresto.utils.timbertree;

class NullLogExclusionStrategy implements LogExclusionStrategy {
    static final NullLogExclusionStrategy INSTANCE = new NullLogExclusionStrategy();

    private NullLogExclusionStrategy() {
    }

    @Override
    public boolean shouldSkipForLog(int priority, String tag, String message, Throwable t) {
        return false;
    }
}
