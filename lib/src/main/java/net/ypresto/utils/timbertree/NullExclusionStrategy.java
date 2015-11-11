package net.ypresto.utils.timbertree;

class NullExclusionStrategy implements ExclusionStrategy {
    static final NullExclusionStrategy INSTANCE = new NullExclusionStrategy();

    private NullExclusionStrategy() {
    }

    @Override
    public boolean shouldSkipForLog(int priority, String tag, String message, Throwable t) {
        return false;
    }
}
