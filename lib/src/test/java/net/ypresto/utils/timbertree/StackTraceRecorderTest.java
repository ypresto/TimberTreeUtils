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

import org.junit.Test;

import timber.log.Timber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StackTraceRecorderTest {
    @Test
    public void testFillInStackTrace() throws Exception {
        final StackTraceRecorder[] stackTraceRecorderArray = new StackTraceRecorder[1];
        Timber.plant(new TestRecorderTree(stackTraceRecorderArray));
        Timber.e("hoge");
        StackTraceRecorder stackTraceRecorder = stackTraceRecorderArray[0];
        assertNotNull(stackTraceRecorder);
        assertEquals("net.ypresto.utils.timbertree.StackTraceRecorderTest", stackTraceRecorder.getStackTrace()[0].getClassName());
        assertEquals("testFillInStackTrace", stackTraceRecorder.getStackTrace()[0].getMethodName());
    }

    private static class TestRecorderTree extends Timber.Tree {
        private final StackTraceRecorder[] stackTraceRecorderArray;

        public TestRecorderTree(StackTraceRecorder[] stackTraceRecorderArray) {
            this.stackTraceRecorderArray = stackTraceRecorderArray;
        }

        @Override
        protected void log(int i, String s, String s1, Throwable throwable) {
            stackTraceRecorderArray[0] = new StackTraceRecorder("hoge");
        }
    }
}
