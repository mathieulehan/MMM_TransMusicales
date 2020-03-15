package com.example.transmusicales;

public class Artist {
    private String datasetid;
    private String recordid;
    public Fields fields;
    public Geometry geometry;
    private String record_timestamp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;

    // Getter Methods

    public String getDatasetid() {
        return datasetid;
    }

    public Artist() {

    }

    public String getRecordid() {
        return recordid;
    }

    public Artist(String datasetid, String recordid, String record_timestamp, String uid, Fields fields, Geometry geometry) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.record_timestamp = record_timestamp;
        this.uid = uid;
        this.fields = fields;
        this.geometry = geometry;
    }

    public Artist(String uid, Fields fields) {
        this.uid = uid;
        this.fields = fields;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    // Setter Methods

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }
    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getNbPersonne() {
        return this.getFields().getNbpersonne();
    }

    public void setNbPersonne(Integer nbPersonne){
        Fields f = this.getFields();
        f.setNbpersonne(String.valueOf(nbPersonne));
        this.setFields(f);
    }

    public void setMark(float rating) {
        Fields f = this.getFields();
        f.setMark(String.valueOf(rating));
        this.setFields(f);
    }

    public String getMark(){
        return this.getFields().getMark();
    }
}