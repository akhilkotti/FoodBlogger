package com.nw.edu.util;

/**
 * <p>
 * Title: ProcessLog</p>
 * <p>
 * Description: The class provides the implementation for logging specific
 * messages and exceptions </p>
 * <p>
 * Copyright: Copyright (c) 2023</p>
 * <p>
 * University: North Eastern</p>
 *
 * @author Mrudhula
 * @version 1.0
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

import com.nw.edu.constants.Constants;

public class ProcessLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileName;
    private String path;
    private PrintWriter pw;
    private OutputStream logStream;
    private String logStr;

    public ProcessLog(final String filename, final String fileExtension) {
        this.fileName = filename + "_" + DateFormat.getCurrentDateFormat(new Date(), Constants.DATE_FORMAT_PATTERN_2)
                + "." + fileExtension;
        this.path = getLogPath(filename);
        setLogStream();
    }

    public String getLogPath(final String filename) {
        path = validDir(
                Constants.SYSTEM_PROPERTIES.get("user.dir") + (String) Constants.SYSTEM_PROPERTIES.get("file.separator")
                + "log" + (String) Constants.SYSTEM_PROPERTIES.get("file.separator"));
        return path;
    }

    private void setLogStream() {

        Thread.currentThread().setName(this.getClass().getName());
        // Set log & error streams
        try {
            logStream = new FileOutputStream(path + fileName, true);

            pw = new PrintWriter(logStream);

            logStr = "LOG STARTED AT " + DateFormat.datetime(new Date(), Constants.DATE_FORMAT_PATTERN_1);
            pw.println("");
            pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + logStr
                    + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            pw.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getLogDir() {
        return path;
    }

    public synchronized void log(final String msg) {
        if (pw != null) {
            pw.println(new Date() + " " + msg);
            pw.flush();
        }
    }

    public synchronized void log(final Exception ex) {
        pw.println(new Date() + " Exception raised: " + ex.toString());
        ex.printStackTrace(pw);
        pw.flush();

    }

    public synchronized void logWOD(final String msg) {
        if (pw != null) {
            pw.println(msg);
            pw.flush();
        }
    }

    public synchronized void close() {

        logStr = "LOG CLOSED AT " + DateFormat.datetime(new Date(), Constants.DATE_FORMAT_PATTERN_1);
        pw.println("");
        pw.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< " + logStr
                + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        pw.close();

    }

    private String validDir(final String s) {

        File dir = new File(s);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return s;
    }

    static public void main(String[] arg) {
        ProcessLog pl = new ProcessLog(Constants.FOOD_BLOGGER_LOG, Constants.LOG_FILE_EXTENSION);
        System.out.println(pl.path);
        System.out.println(DateFormat.getCurrentDateFormat(new Date(), Constants.DATE_FORMAT_PATTERN_2));
        pl.log("Content addded to log file.........");
        pl.close();
    }
}
