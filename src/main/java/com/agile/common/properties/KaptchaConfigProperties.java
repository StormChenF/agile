package com.agile.common.properties;

import com.agile.common.annotation.Properties;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2018/1/11
 */
@Properties(prefix = "agile.kaptcha")
public class KaptchaConfigProperties {
    private static String border;
    private static String borderColor;
    private static String textproducerFontColor;
    private static String textproducerFontSize;
    private static String imageWidth;
    private static String imageHeight;
    private static String textproducerCharLength;
    private static String textproducerFontNames;

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
