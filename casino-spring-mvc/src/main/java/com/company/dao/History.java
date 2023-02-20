package com.company.dao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.*;

public class History {

    @Min(value = 1, message = "Need great then 0")
    private long externalId;
    @Min(value = 1, message = "Need great then 0")
    private short providerId;
    private Date _receiveDate;
    @Size(min=1,message="required")
    private String productMetadata;

    public History(){}

    public History(
        long externalId,
        short providerId,
        String productMetadata){
        this.externalId = externalId;
        this.providerId = providerId;
        this.productMetadata = productMetadata;
    }

    public void setExternalId(long externalId){
        this.externalId = externalId;
    }
    public void setProviderId(short providerId){
        this.providerId = providerId;
    }
    public void setProductMetadata(String productMetadata){
        this.productMetadata = productMetadata;
    }

    public long getExternalId(){
        return this.externalId;
    }
    public short getProviderId(){
        return this.providerId;
    }
    public Date getReceiveDate(){
        return _receiveDate;
    }
    public String getProductMetadata(){
        return this.productMetadata;
    }
}