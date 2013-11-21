// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.infobip.campus.chattopush.models;

import com.infobip.campus.chattopush.models.UserModel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UserModel_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UserModel.entityManager;
    
    public static final EntityManager UserModel.entityManager() {
        EntityManager em = new UserModel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    @Transactional
    public static long UserModel.countUserModels() {
        return findAllUserModels().size();
    }
    
    @Transactional
    public static List<UserModel> UserModel.findAllUserModels() {
        return entityManager().createQuery("SELECT o FROM UserModel o", UserModel.class).getResultList();
    }
    
    @Transactional
    public static UserModel UserModel.findUserModel(Long id) {
        if (id == null) return null;
        return entityManager().find(UserModel.class, id);
    }
    
    @Transactional
    public static List<UserModel> UserModel.findUserModelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserModel o", UserModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void UserModel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UserModel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserModel attached = UserModel.findUserModel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UserModel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UserModel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UserModel UserModel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserModel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
