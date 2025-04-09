package com.example.springsecurity.service;

import com.example.springsecurity.entities.Phone;
import com.example.springsecurity.reposiroty.PhoneRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProductAuditTest {
    @Autowired
    private PhoneRepository phoneRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testPhoneAudit() {
        // 1️⃣ Tạo một số điện thoại mới
        Phone phone = new Phone("0123456789");
        phoneRepository.save(phone);

        // 2️⃣ Cập nhật số điện thoại
        phone.setNumber("0987654321");
        phoneRepository.save(phone);

//        // 3️⃣ Lấy danh sách các phiên bản (revisions)
//        AuditReader auditReader = AuditReaderFactory.get(entityManager);
//        List<Number> revisions = auditReader.getRevisions(Phone.class, phone.getId());
//        Assertions.assertEquals(2, revisions.size()); // Có 2 phiên bản (1 tạo, 1 cập nhật)
//
//        // 4️⃣ Kiểm tra dữ liệu ở phiên bản đầu tiên
//        Phone oldPhone = auditReader.find(Phone.class, phone.getId(), revisions.get(0));
//        Assertions.assertEquals("0123456789", oldPhone.getNumber());
//
//        // 5️⃣ Kiểm tra dữ liệu ở phiên bản thứ hai
//        Phone updatedPhone = auditReader.find(Phone.class, phone.getId(), revisions.get(1));
//        Assertions.assertEquals("0987654321", updatedPhone.getNumber());
    }
}
