package com.example.pick_a_fish.Utiliy;


public class modelmodel {

    int infoIMG;
    String infoName,infoDesc;

    public modelmodel(int infoIMG, String infoName,String infoDesc){

        this.infoIMG = infoIMG;
        this.infoName = infoName;
        this.infoDesc = infoDesc;


    }


    public int getInfoIMG() {
        return infoIMG;
    }

    public void setInfoIMG(int infoIMG) {
        this.infoIMG = infoIMG;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

}

