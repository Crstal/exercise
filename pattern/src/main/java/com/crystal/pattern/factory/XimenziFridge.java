package com.crystal.pattern.factory;

/**
 * @author: Caoyue
 * @Description: 西门子冰箱
 * @Date: 2018/05/12 15:47
 */
public class XimenziFridge implements Fridge {

    private String color;
    private String size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getName() {
        return "西门子冰箱";
    }
}
