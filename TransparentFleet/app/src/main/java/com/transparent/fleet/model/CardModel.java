package com.transparent.fleet.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class CardModel extends BaseObservable implements Parcelable {
    private String name;
    private String pinno;
    private String cardno;


    public CardModel(){}


    protected CardModel(Parcel in) {
        name = in.readString();
        pinno = in.readString();
        cardno = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pinno);
        dest.writeString(cardno);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardModel> CREATOR = new Creator<CardModel>() {
        @Override
        public CardModel createFromParcel(Parcel in) {
            return new CardModel(in);
        }

        @Override
        public CardModel[] newArray(int size) {
            return new CardModel[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    @Bindable
    public String getPinno() {
        return pinno;
    }

    public void setPinno(String pinno) {
        this.pinno = pinno;
        notifyChange();

    }

    @Bindable
    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
        notifyChange();

    }
}
