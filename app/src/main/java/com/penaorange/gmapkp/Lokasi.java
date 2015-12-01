package com.penaorange.gmapkp;

/**
 * Created by Pena Orange on 28/11/2015.
 */
public class Lokasi {
    private double lat,lang;
    private String subLocality,thoroughfare, subAdminArea;

    public Lokasi() {
    }

    public Lokasi(double lat, double lang, String subLocality, String thoroughfare, String subAdminArea) {
        this.lat = lat;
        this.lang = lang;
        this.subLocality = subLocality;
        this.thoroughfare = thoroughfare;
        this.subAdminArea = subAdminArea;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public String getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = thoroughfare;
    }

    public String getSubAdminArea() {
        return subAdminArea;
    }

    public void setSubAdminArea(String subAdminArea) {
        this.subAdminArea = subAdminArea;
    }
}
