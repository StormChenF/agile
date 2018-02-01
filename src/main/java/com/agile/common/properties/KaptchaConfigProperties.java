package com.agile.common.properties;

import com.agile.common.annotation.Properties;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2018/1/11
 */
@Properties(prefix = "agile.kaptcha")
public class KaptchaConfigProperties {
    private String border;
    private String borderColor;
    private String textproducerFontColor;
    private String textproducerFontSize;
    private String imageWidth;
    private String imageHeight;
    private String textproducerCharLength;
    private String textproducerFontNames;

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getTextproducerFontColor() {
        return textproducerFontColor;
    }

    public void setTextproducerFontColor(String textproducerFontColor) {
        this.textproducerFontColor = textproducerFontColor;
    }

    public String getTextproducerFontSize() {
        return textproducerFontSize;
    }

    public void setTextproducerFontSize(String textproducerFontSize) {
        this.textproducerFontSize = textproducerFontSize;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getTextproducerCharLength() {
        return textproducerCharLength;
    }

    public void setTextproducerCharLength(String textproducerCharLength) {
        this.textproducerCharLength = textproducerCharLength;
    }

    public String getTextproducerFontNames() {
        return textproducerFontNames;
    }

    public void setTextproducerFontNames(String textproducerFontNames) {
        this.textproducerFontNames = textproducerFontNames;
    }
}
