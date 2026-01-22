package com.example.IdentityService.service;

import com.example.IdentityService.entities.Phone;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductAuditService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Number> getRevisions(Long productId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.getRevisions(Phone.class, productId);
    }

    @Transactional
    public Phone getPhoneAtRevision(Long phoneId, Number revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(Phone.class, phoneId, revision);
    }
}
