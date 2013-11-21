// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.infobip.campus.chattopush.models;

import com.infobip.campus.chattopush.models.MessageModel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MessageModel_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MessageModel.entityManager;
    
    public static final EntityManager MessageModel.entityManager() {
        EntityManager em = new MessageModel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    @Transactional
    public static long MessageModel.countMessageModels() {
        return findAllMessageModels().size();
    }
    
    @Transactional
    public static List<MessageModel> MessageModel.findAllMessageModels() {
        return entityManager().createQuery("SELECT o FROM MessageModel o", MessageModel.class).getResultList();
    }
    
    @Transactional
    public static MessageModel MessageModel.findMessageModel(Long id) {
        if (id == null) return null;
        return entityManager().find(MessageModel.class, id);
    }
    
    @Transactional
    public static List<MessageModel> MessageModel.findMessageModelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MessageModel o", MessageModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void MessageModel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MessageModel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MessageModel attached = MessageModel.findMessageModel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MessageModel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MessageModel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MessageModel MessageModel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MessageModel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
