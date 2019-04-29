package com.crystal.pattern.factory;

/**
 * @author: Caoyue
 * @Description: 海尔冰箱
 * @Date: 2018/05/12 15:48
 */
public class HaierFridge implements Fridge {

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
        return "海尔冰箱";
    }
}
