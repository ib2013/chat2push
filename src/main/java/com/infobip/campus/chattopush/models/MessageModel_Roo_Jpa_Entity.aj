// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.infobip.campus.chattopush.models;

import com.infobip.campus.chattopush.models.MessageModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect MessageModel_Roo_Jpa_Entity {
    
    declare @type: MessageModel: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long MessageModel.id;
    
    @Version
    @Column(name = "version")
    private Integer MessageModel.version;
    
    public Long MessageModel.getId() {
        return this.id;
    }
    
    public void MessageModel.setId(Long id) {
        this.id = id;
    }
    
    public Integer MessageModel.getVersion() {
        return this.version;
    }
    
    public void MessageModel.setVersion(Integer version) {
        this.version = version;
    }
    
}
