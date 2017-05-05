package com.martin.fruitrecycleview.bean;

import java.io.Serializable;

/**
 * Created by Martin on 2017/4/29 0029.
 */

public class Fruit implements Serializable {
    private int ID;
    private String 缩略图;
    private String 标题;
    private String 副标题;
    private String 功效;
    private String 禁忌人群;
    private String 适宜人群;
    private String 适宜体质;
    private String 禁忌体质;
    private String 介绍;
    private String PageUrl;

    public Fruit(){}

    public Fruit(int ID, String 缩略图, String 标题, String 副标题, String 功效, String 禁忌人群,
                 String 适宜人群, String 适宜体质, String 介绍, String PageUrl){
        this.ID = ID;
        this.缩略图 = 缩略图;
        this.标题 = 标题;
        this.副标题 = 副标题;
        this.功效 = 功效;
        this.禁忌人群 = 禁忌人群;
        this.适宜人群 = 适宜人群;
        this.适宜体质 = 适宜体质;
        this.介绍 = 介绍;
        this.PageUrl = PageUrl;
    }

    @Override
    public String toString() {
        return "Fruit [ID=" + ID + ", 缩略图=" + 缩略图 + ", 标题=" + 标题 + ", 副标题=" + 副标题
                + ", 功效=" + 功效 + ", 禁忌人群=" + 禁忌人群  + ", 适宜人群=" + 适宜人群
                + ", 适宜体质=" + 适宜体质 + ", 介绍=" + 介绍 + ", PageUrl=" + PageUrl + "]";
    }

    public Fruit(String 缩略图, String 标题, String 副标题, String 功效, String 禁忌人群, String 介绍){
        this.缩略图 = 缩略图;
        this.标题 = 标题;
        this.副标题 = 副标题;
        this.功效 = 功效;
        this.禁忌人群 = 禁忌人群;
        this.介绍 = 介绍;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String get缩略图() {
        return 缩略图;
    }

    public void set缩略图(String 缩略图) {
        this.缩略图 = 缩略图;
    }

    public String get标题() {
        return 标题;
    }

    public void set标题(String 标题) {
        this.标题 = 标题;
    }

    public String get副标题() {
        return 副标题;
    }

    public void set副标题(String 副标题) {
        this.副标题 = 副标题;
    }

    public String get功效() {
        return 功效;
    }

    public void set功效(String 功效) {
        this.功效 = 功效;
    }

    public String get禁忌人群() {
        return 禁忌人群;
    }

    public void set禁忌人群(String 禁忌人群) {
        this.禁忌人群 = 禁忌人群;
    }

    public String get适宜人群() {
        return 适宜人群;
    }

    public void set适宜人群(String 适宜人群) {
        this.适宜人群 = 适宜人群;
    }

    public String get适宜体质() {
        return 适宜体质;
    }

    public void set适宜体质(String 适宜体质) {
        this.适宜体质 = 适宜体质;
    }

    public String get禁忌体质() {
        return 禁忌体质;
    }

    public void set禁忌体质(String 禁忌体质) {
        this.禁忌体质 = 禁忌体质;
    }

    public String get介绍() {
        return 介绍;
    }

    public void set介绍(String 介绍) {
        this.介绍 = 介绍;
    }

    public String getPageUrl() {
        return PageUrl;
    }

    public void setPageUrl(String pageUrl) {
        PageUrl = pageUrl;
    }
}
