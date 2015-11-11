/*
 * Copyright (C) 2015 Yuya Tanaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ypresto.utils.timbertree;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which sends log to Crashlytics.
 *
 * @author ypresto
 * @see CrashlyticsLogExceptionTree
 */
public class CrashlyticsLogTree extends Timber.Tree {
    private final int mLogPriority;
    private final LogExclusionStrategy mLogExclusionStrategy;

    /**
     * Create instance with default log priority of WARN.
     */
    public CrashlyticsLogTree() {
        this(Log.WARN);
    }

    /**
     * @param logPriority Minimum log priority to send log. Expects one of constants defined in {@link Log}.
     */
    public CrashlyticsLogTree(int logPriority) {
        this(logPriority, null);
    }

    /**
     * @param logPriority          Minimum log priority to send log. Expects one of constants defined in {@link Log}.
     * @param logExclusionStrategy Strategy used to skip logging.
     */
    public CrashlyticsLogTree(int logPriority, LogExclusionStrategy logExclusionStrategy) {
        // Ensure crashlytics class is available, fail-fast if not available.
        Crashlytics.class.getCanonicalName();
        mLogPriority = logPriority;
        mLogExclusionStrategy = logExclusionStrategy != null ? logExclusionStrategy : NullLogExclusionStrategy.INSTANCE;
    }

    @Override
    protected boolean isLoggable(int priority) {
        return priority >= mLogPriority;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (mLogExclusionStrategy.shouldSkipLog(priority, tag, message, t)) {
            return;
        }

        String messageWithTag = "[" + tag + "] " + message;
        Crashlytics.log(messageWithTag);
    }
}
