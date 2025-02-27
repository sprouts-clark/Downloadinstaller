package com.sprouts.downloadinstaller;

/**
 * 下载状态
 * 未下载，下载中，下载完成，下载失败，待安装
 * <p>
 * 下载好了，有可能包有问题。所以假如安装失败要处理一下
 */
public interface UpdateStatus {
    /**
     * The constant UN_DOWNLOAD.
     */
    public final static int UN_DOWNLOAD =-1;
    /**
     * The constant DOWNLOADING.
     */
    public final static int DOWNLOADING=0;
    /**
     * The constant DOWNLOAD_ERROR.
     */
    public final static int DOWNLOAD_ERROR =1;
    /**
     * The constant UNINSTALL.
     */
    public final static int UNINSTALL=2;

}