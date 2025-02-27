package com.sprouts.downloadinstaller;

/**
 * 下载进度回调
 */
public interface DownloadProgressCallBack {
     /**
      * Download progress.
      *
      * @param progress the progress
      */
     void downloadProgress(int progress);

     /**
      * Download exception.
      *
      * @param e the e
      */
     void downloadException(Exception e);

     /**
      * On install start.
      */
     void onInstallStart();

}