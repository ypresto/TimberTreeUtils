TimberTreeUtils
===============

Includes [timber](https://github.com/JakeWharton/timber) tree for crashlytics, and debugging with forced crash.

## Trees

### CrashlyticsTree

Send non-fatal exception report when `Timber.e()` is called (ONLY SEND REPORT instead of actual crash :) , be calm).

Requires Crashlytics Android SDK.

### FailFastTree

Throws LogPriorityExceededException (extends RuntimeException) when Timber log method called which log priority exceeds specified one.

Useful when dog feeding, debug or quality assuarance. DON'T plant() this on production environment.

### LINCENSE

Public domain (refer [http://unlicense.org/]).
