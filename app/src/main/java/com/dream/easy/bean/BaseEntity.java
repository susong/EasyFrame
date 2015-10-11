package com.dream.easy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:40
 * Description: EasyFrame
 */
public class BaseEntity implements Parcelable {
    private String id;
    private String name;

    public BaseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    protected BaseEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<BaseEntity> CREATOR = new Parcelable.Creator<BaseEntity>() {
        public BaseEntity createFromParcel(Parcel source) {
            return new BaseEntity(source);
        }

        public BaseEntity[] newArray(int size) {
            return new BaseEntity[size];
        }
    };
}
