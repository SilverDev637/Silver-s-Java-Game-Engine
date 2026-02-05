package io.silverdev637.sjge;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Log {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private Log() {
        // avoid instances
    }

    public static void info(Object message) {
        log(Level.INFO, message);
    }

    public static void warn(Object message) {
        log(Level.WARN, message);
    }

    public static void done(Object message) {
        log(Level.DONE, message);
    }

    public static void debug(Object message) {
		log(Level.DEBUG, message);
    }
    
    public static void error(String message) {
        error(message, null);
    }

    public static void error(String message, Throwable t) {
        log(Level.ERROR, message);

        if (t != null) {
            t.printStackTrace(System.out);
        }
    }

    
    public static void print(Object message) {
    	System.out.println("");
    }

    private static void log(Level level, Object message) {
        String time = LocalTime.now().format(TIME_FORMAT);
        String thread = Thread.currentThread().getName();

        System.out.printf(
                "[%s] [%s/%s]: %s%n",
                time,
                thread,
                level,
                String.valueOf(message)
        );
    }

    private enum Level {
        INFO, WARN, DONE, DEBUG, ERROR
    }
}
