package com.example.transmusicales;

import java.util.ArrayList;

public class Fields {
    private String spotify;
    private String cou_official_lang_code;
    private String cou_onu_code;
    private String artistes;
    private String mark;
    private String nbpersonne;
    private String cou_iso2_code;
    ArrayList<Object> geo_point_2d = new ArrayList<Object>();
    private String cou_iso3_code;
    private String cou_is_receiving_quest;
    private String edition;
    private String cou_text_sp;
    private String cou_is_ilomember;
    private String annee;
    private String deezer;
    private String cou_text_en;
    private String origine_pays1;

    public Fields() {

    }

    public Fields(String spotify, String cou_official_lang_code, String cou_onu_code, String artistes, String mark, String nbpersonne, String cou_iso2_code, ArrayList<Object> geo_point_2d, String cou_iso3_code, String cou_is_receiving_quest, String edition, String cou_text_sp, String cou_is_ilomember, String annee, String deezer, String cou_text_en, String origine_pays1) {
        this.spotify = spotify;
        this.cou_official_lang_code = cou_official_lang_code;
        this.cou_onu_code = cou_onu_code;
        this.artistes = artistes;
        this.mark = mark;
        this.nbpersonne = nbpersonne;
        this.cou_iso2_code = cou_iso2_code;
        this.geo_point_2d = geo_point_2d;
        this.cou_iso3_code = cou_iso3_code;
        this.cou_is_receiving_quest = cou_is_receiving_quest;
        this.edition = edition;
        this.cou_text_sp = cou_text_sp;
        this.cou_is_ilomember = cou_is_ilomember;
        this.annee = annee;
        this.deezer = deezer;
        this.cou_text_en = cou_text_en;
        this.origine_pays1 = origine_pays1;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public String getCou_official_lang_code() {
        return cou_official_lang_code;
    }

    public void setCou_official_lang_code(String cou_official_lang_code) {
        this.cou_official_lang_code = cou_official_lang_code;
    }

    public String getCou_onu_code() {
        return cou_onu_code;
    }

    public void setCou_onu_code(String cou_onu_code) {
        this.cou_onu_code = cou_onu_code;
    }

    public String getArtistes() {
        return artistes;
    }

    public void setArtistes(String artistes) {
        this.artistes = artistes;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(String nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public String getCou_iso2_code() {
        return cou_iso2_code;
    }

    public void setCou_iso2_code(String cou_iso2_code) {
        this.cou_iso2_code = cou_iso2_code;
    }

    public ArrayList<Object> getGeo_point_2d() {
        return geo_point_2d;
    }

    public void setGeo_point_2d(ArrayList<Object> geo_point_2d) {
        this.geo_point_2d = geo_point_2d;
    }

    public String getCou_iso3_code() {
        return cou_iso3_code;
    }

    public void setCou_iso3_code(String cou_iso3_code) {
        this.cou_iso3_code = cou_iso3_code;
    }

    public String getCou_is_receiving_quest() {
        return cou_is_receiving_quest;
    }

    public void setCou_is_receiving_quest(String cou_is_receiving_quest) {
        this.cou_is_receiving_quest = cou_is_receiving_quest;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCou_text_sp() {
        return cou_text_sp;
    }

    public void setCou_text_sp(String cou_text_sp) {
        this.cou_text_sp = cou_text_sp;
    }

    public String getCou_is_ilomember() {
        return cou_is_ilomember;
    }

    public void setCou_is_ilomember(String cou_is_ilomember) {
        this.cou_is_ilomember = cou_is_ilomember;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getDeezer() {
        return deezer;
    }

    public void setDeezer(String deezer) {
        this.deezer = deezer;
    }

    public String getCou_text_en() {
        return cou_text_en;
    }

    public void setCou_text_en(String cou_text_en) {
        this.cou_text_en = cou_text_en;
    }

    public String getOrigine_pays1() {
        return origine_pays1;
    }

    public void setOrigine_pays1(String origine_pays1) {
        this.origine_pays1 = origine_pays1;
    }
}
