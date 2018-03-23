package com.simbirsoft.igorverbkin.androidtraineeeducation.task4.model.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class CheckSpaceUtil {

    public static long getAvailableSpaceInMB() {
        return getAvailableInternalMemorySize() / (1024 * 1024);
    }

    private static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return availableBlocks * blockSize;
    }

}
