package com.al.markovchain.server;


/**
 * 
 * @author TonyLi 
 */
public abstract class AbstractResponse {
    private MetaData meta ;

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }
    
    public abstract Object getData();
}
