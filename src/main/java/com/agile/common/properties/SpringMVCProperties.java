package com.agile.common.properties;

import com.agile.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
@Properties(prefix = "agile")
public class SpringMVCProperties {
    private FileConfigProperty upload;

    public FileConfigProperty getUpload() {
        return upload;
    }

    public void setUpload(FileConfigProperty upload) {
        this.upload = upload;
    }
}
