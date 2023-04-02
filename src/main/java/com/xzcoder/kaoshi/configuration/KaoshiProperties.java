package com.xzcoder.kaoshi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Objects;

/**
 * KaoshiProperties
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Configuration
@ConfigurationProperties(prefix = "kaoshi")
public class KaoshiProperties implements Serializable {

    private static KaoshiProperties INSTANCE;

    /**
     * 图片路径
     */
    private String picPath;

    /**
     * UEditor 富文本上传路径
     */
    private String ueditorRootPath;

    @PostConstruct
    public void init() {
        INSTANCE = this;
    }

    public static KaoshiProperties getInstance() {
        return Objects.requireNonNull(INSTANCE);
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getUeditorRootPath() {
        return ueditorRootPath;
    }

    public void setUeditorRootPath(String ueditorRootPath) {
        this.ueditorRootPath = ueditorRootPath;
    }
}
