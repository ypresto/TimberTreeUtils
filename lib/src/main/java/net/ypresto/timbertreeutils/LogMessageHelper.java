package net.ypresto.timbertreeutils;

import android.util.Log;

class LogMessageHelper {
    public static String format(int priority, String tag, String message) {
        String messageWithTag = tag != null ? "[" + tag + "] " + message : message;
        return prefixForPriority(priority) + messageWithTag;
    }

    public static String prefixForPriority(int priority) {
        switch (priority) {
            case Log.VERBOSE:
                return "[VERBOSE] ";
            case Log.DEBUG:
                return "[DEBUG] ";
            case Log.INFO:
                return "[INFO] ";
            case Log.WARN:
                return "[WARN] ";
            case Log.ERROR:
                return "[ERROR] ";
            case Log.ASSERT:
                return "[ASSERT] ";
            default:
                return "[UNKNOWN(" + priority + ")] ";
        }
    }
}
