// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.infobip.campus.chattopush.models;

import com.infobip.campus.chattopush.models.UserModel;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect UserModel_Roo_Jpa_Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long UserModel.id;
    
    @Version
    @Column(name = "version")
    private Integer UserModel.version;
    
    public Long UserModel.getId() {
        return this.id;
    }
    
    public void UserModel.setId(Long id) {
        this.id = id;
    }
    
    public Integer UserModel.getVersion() {
        return this.version;
    }
    
    public void UserModel.setVersion(Integer version) {
        this.version = version;
    }
    
}
