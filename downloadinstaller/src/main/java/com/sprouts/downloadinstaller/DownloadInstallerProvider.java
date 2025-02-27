package com.sprouts.downloadinstaller;


import androidx.core.content.FileProvider;

/**
 * 避免和其他Lib 中的FileProvider name 重复
 */
public class DownloadInstallerProvider extends FileProvider {

    /**
     * Instantiates a new Download installer provider.
     */
    public DownloadInstallerProvider() {
        super();
    }

}