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
    ArrayList<Long> geo_point_2d = new ArrayList<Long>();
    private String cou_iso3_code;
    private String cou_is_receiving_quest;
    private String edition;
    private String cou_text_sp;
    private String cou_is_ilomember;
    private String annee;
    private String deezer;
    private String cou_text_en;
    private String origine_pays1;
    private Long premiere_date_timestamp;
    private String origine_ville1;
    private String premiere_salle;
    private String premiere_date;
    private String deuxieme_salle;
    private Long deuxieme_date_timestamp;
    private String deuxieme_date;
    private String troisieme_date;
    private Long troisieme_date_timestamp;
    private String troisieme_salle;
    private String sixieme_date;
    private String cinquieme_projet;
    private String column_47;
    private String column_45;
    private String column_46;
    private String column_48;
    private String cinquieme_date;
    private String quatrieme_date;
    private String quatrieme_projet;
    private String quatrieme_salle;
    private Long quatrieme_date_timestamp;
    private Long cinquieme_date_timestamp;
    private String sixieme_salle;
    private String troisieme_projet;
    private String cinquieme_salle;
    private Long sixieme_date_timestamp;
    private String sixieme_projet;
    private String origine_pays2;
    private String origine_ville2;
    private String deuxieme_projet;
    private String premier_projet_atm;
    private String nom_spectacle_ou_soiree;
    private String origine_pays3;
    private String origine_pays4;
    private String quatrieme_ville;
    private String origine_ville4;
    private String premiere_ville;
    private String deuxieme_ville;
    private String troisieme_ville;
    private String origine_ville3;

    public Fields() {

    }

    public Fields(String spotify, String cou_official_lang_code, String cou_onu_code, String artistes, String mark, String nbpersonne, String cou_iso2_code, ArrayList<Long> geo_point_2d, String cou_iso3_code, String cou_is_receiving_quest, String edition, String cou_text_sp, String cou_is_ilomember, String annee, String deezer, String cou_text_en, String origine_pays1, Long premiere_date_timestamp, String origine_ville1, String premiere_salle, String premiere_date, String deuxieme_salle, Long deuxieme_date_timestamp, String deuxieme_date, String troisieme_date, Long troisieme_date_timestamp, String troisieme_salle, String sixieme_date, String cinquieme_projet, String column_47, String column_45, String column_46, String column_48, String cinquieme_date, String quatrieme_date, String quatrieme_projet, String quatrieme_salle, Long quatrieme_date_timestamp, Long cinquieme_date_timestamp, String sixieme_salle, String troisieme_projet, String cinquieme_salle, Long sixieme_date_timestamp, String sixieme_projet, String origine_pays2, String origine_ville2, String deuxieme_projet, String premier_projet_atm, String nom_spectacle_ou_soiree, String origine_pays3, String origine_pays4, String quatrieme_ville, String origine_ville4, String premiere_ville, String deuxieme_ville, String troisieme_ville, String origine_ville3) {
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
        this.premiere_date_timestamp = premiere_date_timestamp;
        this.origine_ville1 = origine_ville1;
        this.premiere_salle = premiere_salle;
        this.premiere_date = premiere_date;
        this.deuxieme_salle = deuxieme_salle;
        this.deuxieme_date_timestamp = deuxieme_date_timestamp;
        this.deuxieme_date = deuxieme_date;
        this.troisieme_date = troisieme_date;
        this.troisieme_date_timestamp = troisieme_date_timestamp;
        this.troisieme_salle = troisieme_salle;
        this.sixieme_date = sixieme_date;
        this.cinquieme_projet = cinquieme_projet;
        this.column_47 = column_47;
        this.column_45 = column_45;
        this.column_46 = column_46;
        this.column_48 = column_48;
        this.cinquieme_date = cinquieme_date;
        this.quatrieme_date = quatrieme_date;
        this.quatrieme_projet = quatrieme_projet;
        this.quatrieme_salle = quatrieme_salle;
        this.quatrieme_date_timestamp = quatrieme_date_timestamp;
        this.cinquieme_date_timestamp = cinquieme_date_timestamp;
        this.sixieme_salle = sixieme_salle;
        this.troisieme_projet = troisieme_projet;
        this.cinquieme_salle = cinquieme_salle;
        this.sixieme_date_timestamp = sixieme_date_timestamp;
        this.sixieme_projet = sixieme_projet;
        this.origine_pays2 = origine_pays2;
        this.origine_ville2 = origine_ville2;
        this.deuxieme_projet = deuxieme_projet;
        this.premier_projet_atm = premier_projet_atm;
        this.nom_spectacle_ou_soiree = nom_spectacle_ou_soiree;
        this.origine_pays3 = origine_pays3;
        this.origine_pays4 = origine_pays4;
        this.quatrieme_ville = quatrieme_ville;
        this.origine_ville4 = origine_ville4;
        this.premiere_ville = premiere_ville;
        this.deuxieme_ville = deuxieme_ville;
        this.troisieme_ville = troisieme_ville;
        this.origine_ville3 = origine_ville3;
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

    public ArrayList<Long> getGeo_point_2d() {
        return geo_point_2d;
    }

    public void setGeo_point_2d(ArrayList<Long> geo_point_2d) {
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

    public Long getPremiere_date_timestamp() {
        return premiere_date_timestamp;
    }

    public void setPremiere_date_timestamp(Long premiere_date_timestamp) {
        this.premiere_date_timestamp = premiere_date_timestamp;
    }

    public String getOrigine_ville1() {
        return origine_ville1;
    }

    public void setOrigine_ville1(String origine_ville1) {
        this.origine_ville1 = origine_ville1;
    }

    public String getPremiere_salle() {
        return premiere_salle;
    }

    public void setPremiere_salle(String premiere_salle) {
        this.premiere_salle = premiere_salle;
    }

    public String getPremiere_date() {
        return premiere_date;
    }

    public void setPremiere_date(String premiere_date) {
        this.premiere_date = premiere_date;
    }

    public String getDeuxieme_salle() {
        return deuxieme_salle;
    }

    public void setDeuxieme_salle(String deuxieme_salle) {
        this.deuxieme_salle = deuxieme_salle;
    }

    public Long getDeuxieme_date_timestamp() {
        return deuxieme_date_timestamp;
    }

    public void setDeuxieme_date_timestamp(Long deuxieme_date_timestamp) {
        this.deuxieme_date_timestamp = deuxieme_date_timestamp;
    }

    public String getDeuxieme_date() {
        return deuxieme_date;
    }

    public void setDeuxieme_date(String deuxieme_date) {
        this.deuxieme_date = deuxieme_date;
    }

    public String getTroisieme_date() {
        return troisieme_date;
    }

    public void setTroisieme_date(String troisieme_date) {
        this.troisieme_date = troisieme_date;
    }

    public Long getTroisieme_date_timestamp() {
        return troisieme_date_timestamp;
    }

    public void setTroisieme_date_timestamp(Long troisieme_date_timestamp) {
        this.troisieme_date_timestamp = troisieme_date_timestamp;
    }

    public String getTroisieme_salle() {
        return troisieme_salle;
    }

    public void setTroisieme_salle(String troisieme_salle) {
        this.troisieme_salle = troisieme_salle;
    }

    public String getSixieme_date() {
        return sixieme_date;
    }

    public void setSixieme_date(String sixieme_date) {
        this.sixieme_date = sixieme_date;
    }

    public String getCinquieme_projet() {
        return cinquieme_projet;
    }

    public void setCinquieme_projet(String cinquieme_projet) {
        this.cinquieme_projet = cinquieme_projet;
    }

    public String getColumn_47() {
        return column_47;
    }

    public void setColumn_47(String column_47) {
        this.column_47 = column_47;
    }

    public String getColumn_45() {
        return column_45;
    }

    public void setColumn_45(String column_45) {
        this.column_45 = column_45;
    }

    public String getCinquieme_date() {
        return cinquieme_date;
    }

    public void setCinquieme_date(String cinquieme_date) {
        this.cinquieme_date = cinquieme_date;
    }

    public String getQuatrieme_date() {
        return quatrieme_date;
    }

    public void setQuatrieme_date(String quatrieme_date) {
        this.quatrieme_date = quatrieme_date;
    }

    public String getQuatrieme_projet() {
        return quatrieme_projet;
    }

    public void setQuatrieme_projet(String quatrieme_projet) {
        this.quatrieme_projet = quatrieme_projet;
    }

    public String getQuatrieme_salle() {
        return quatrieme_salle;
    }

    public void setQuatrieme_salle(String quatrieme_salle) {
        this.quatrieme_salle = quatrieme_salle;
    }

    public Long getQuatrieme_date_timestamp() {
        return quatrieme_date_timestamp;
    }

    public void setQuatrieme_date_timestamp(Long quatrieme_date_timestamp) {
        this.quatrieme_date_timestamp = quatrieme_date_timestamp;
    }

    public Long getCinquieme_date_timestamp() {
        return cinquieme_date_timestamp;
    }

    public void setCinquieme_date_timestamp(Long cinquieme_date_timestamp) {
        this.cinquieme_date_timestamp = cinquieme_date_timestamp;
    }

    public String getSixieme_salle() {
        return sixieme_salle;
    }

    public void setSixieme_salle(String sixieme_salle) {
        this.sixieme_salle = sixieme_salle;
    }

    public String getTroisieme_projet() {
        return troisieme_projet;
    }

    public void setTroisieme_projet(String troisieme_projet) {
        this.troisieme_projet = troisieme_projet;
    }

    public String getCinquieme_salle() {
        return cinquieme_salle;
    }

    public void setCinquieme_salle(String cinquieme_salle) {
        this.cinquieme_salle = cinquieme_salle;
    }

    public Long getSixieme_date_timestamp() {
        return sixieme_date_timestamp;
    }

    public void setSixieme_date_timestamp(Long sixieme_date_timestamp) {
        this.sixieme_date_timestamp = sixieme_date_timestamp;
    }

    public String getSixieme_projet() {
        return sixieme_projet;
    }

    public void setSixieme_projet(String sixieme_projet) {
        this.sixieme_projet = sixieme_projet;
    }

    public String getOrigine_pays2() {
        return origine_pays2;
    }

    public void setOrigine_pays2(String origine_pays2) {
        this.origine_pays2 = origine_pays2;
    }

    public String getOrigine_ville2() {
        return origine_ville2;
    }

    public void setOrigine_ville2(String origine_ville2) {
        this.origine_ville2 = origine_ville2;
    }

    public String getColumn_46() {
        return column_46;
    }

    public void setColumn_46(String column_46) {
        this.column_46 = column_46;
    }

    public String getColumn_48() {
        return column_48;
    }

    public void setColumn_48(String column_48) {
        this.column_48 = column_48;
    }

    public String getDeuxieme_projet() {
        return deuxieme_projet;
    }

    public void setDeuxieme_projet(String deuxieme_projet) {
        this.deuxieme_projet = deuxieme_projet;
    }

    public String getPremier_projet_atm() {
        return premier_projet_atm;
    }

    public void setPremier_projet_atm(String premier_projet_atm) {
        this.premier_projet_atm = premier_projet_atm;
    }

    public String getNom_spectacle_ou_soiree() {
        return nom_spectacle_ou_soiree;
    }

    public void setNom_spectacle_ou_soiree(String nom_spectacle_ou_soiree) {
        this.nom_spectacle_ou_soiree = nom_spectacle_ou_soiree;
    }

    public String getOrigine_pays3() {
        return origine_pays3;
    }

    public void setOrigine_pays3(String origine_pays3) {
        this.origine_pays3 = origine_pays3;
    }

    public String getOrigine_pays4() {
        return origine_pays4;
    }

    public void setOrigine_pays4(String origine_pays4) {
        this.origine_pays4 = origine_pays4;
    }

    public String getQuatrieme_ville() {
        return quatrieme_ville;
    }

    public void setQuatrieme_ville(String quatrieme_ville) {
        this.quatrieme_ville = quatrieme_ville;
    }

    public String getOrigine_ville4() {
        return origine_ville4;
    }

    public void setOrigine_ville4(String origine_ville4) {
        this.origine_ville4 = origine_ville4;
    }

    public String getPremiere_ville() {
        return premiere_ville;
    }

    public void setPremiere_ville(String premiere_ville) {
        this.premiere_ville = premiere_ville;
    }

    public String getDeuxieme_ville() {
        return deuxieme_ville;
    }

    public void setDeuxieme_ville(String deuxieme_ville) {
        this.deuxieme_ville = deuxieme_ville;
    }

    public String getTroisieme_ville() {
        return troisieme_ville;
    }

    public void setTroisieme_ville(String troisieme_ville) {
        this.troisieme_ville = troisieme_ville;
    }

    public String getOrigine_ville3() {
        return origine_ville3;
    }

    public void setOrigine_ville3(String origine_ville3) {
        this.origine_ville3 = origine_ville3;
    }
}
