
package com.fkweather.models;


import com.google.gson.annotations.Expose;


public class Message {

    @Expose
    private Fields fields;
    @Expose
    private String model;
    @Expose
    private Integer pk;

    /**
     * 
     * @return
     *     The fields
     */
    public Fields getFields() {
        return fields;
    }

    /**
     * 
     * @param fields
     *     The fields
     */
    public void setFields(Fields fields) {
        this.fields = fields;
    }

    /**
     * 
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The pk
     */
    public Integer getPk() {
        return pk;
    }

    /**
     * 
     * @param pk
     *     The pk
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

}
