
package com.fkweather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @Expose
    private Integer like;
    @SerializedName("sending_user_link")
    @Expose
    private String sendingUserLink;
    @Expose
    private String text;
    @Expose
    private Integer dislike;
    @SerializedName("sending_user_name")
    @Expose
    private String sendingUserName;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @Expose
    private Boolean isactive;
    @Expose
    private Integer temperature;

    /**
     * 
     * @return
     *     The like
     */
    public Integer getLike() {
        return like;
    }

    /**
     * 
     * @param like
     *     The like
     */
    public void setLike(Integer like) {
        this.like = like;
    }

    /**
     * 
     * @return
     *     The sendingUserLink
     */
    public String getSendingUserLink() {
        return sendingUserLink;
    }

    /**
     * 
     * @param sendingUserLink
     *     The sending_user_link
     */
    public void setSendingUserLink(String sendingUserLink) {
        this.sendingUserLink = sendingUserLink;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The dislike
     */
    public Integer getDislike() {
        return dislike;
    }

    /**
     * 
     * @param dislike
     *     The dislike
     */
    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    /**
     * 
     * @return
     *     The sendingUserName
     */
    public String getSendingUserName() {
        return sendingUserName;
    }

    /**
     * 
     * @param sendingUserName
     *     The sending_user_name
     */
    public void setSendingUserName(String sendingUserName) {
        this.sendingUserName = sendingUserName;
    }

    /**
     * 
     * @return
     *     The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 
     * @return
     *     The isactive
     */
    public Boolean getIsactive() {
        return isactive;
    }

    /**
     * 
     * @param isactive
     *     The isactive
     */
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    /**
     * 
     * @return
     *     The temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * 
     * @param temperature
     *     The temperature
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

}
