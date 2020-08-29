package com.dlab.monami;

public class ImageFormat {
    String imgPath;
    private boolean selected;

    public ImageFormat(String imgPath, boolean selected) {
        this.imgPath = imgPath;
        this.selected = selected;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}