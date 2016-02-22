TimberTreeUtils
===============

Set of [timber](https://github.com/JakeWharton/timber) trees for
[Crashlytics](https://fabric.io/kits/android/crashlytics) and debugging.


Usage
----

This library only contains `Timber.Tree` implementations. Just plant it to Timber.

```java
Timber.plant(new CrashlyticsLogExceptionTree())
```


Trees
----

### CrashlyticsLogExceptionTree (Default log level: ERROR)

Sends non-fatal exception to Crashlytics with `Crashlytics.logException()`.

If no throwable is passed, it generates stack trace from caller of Timber.e() or etc.
NOTE: Stack trace elements of timber code are automatically removed before sent.

### CrashlyticsLogTree (Default log level: WARN)

Records log to Crashlytics with `Crashlytics.log()`.

Recorded logs will be shown in each Crashes/Non-Fatals report.

### ThrowErrorTree (Default log level: ERROR)

Throws LogPriorityExceededError (extends Error) if log level exceeds specified level.

Useful when dogfooding, debugging, quality assurance. DON'T plant() this on production environment.


Filtering logs
----

### Specifying minimum log level

```java
CrashlyticsLogTree tree = new CrashlyticsLogTree(Log.INFO);
```

### Excluding log by custom logic

```java
ThrowErrorTree tree = new ThrowErrorTree(Log.ERROR, new LogExclusionStrategy() {
    @Override
    public boolean shouldSkipLog(int priority, String tag, String message, Throwable t) {
        return message.startsWith("NO_FAIL_FAST");
    }
});
```


Installation
----

Available from jCenter.

Gradle:

```groovy
dependencies {
    compile 'net.ypresto.timbertreeutils:timbertreeutils:1.0.0'
}
```


LICENSE
----

```
Copyright (C) 2015 Yuya Tanaka

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
