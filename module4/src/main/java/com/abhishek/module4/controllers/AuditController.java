package com.abhishek.module4.controllers;

import com.abhishek.module4.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// We can create separate audit controller for each entity (eg. PostAuditContoller)
// This controller is only for admin (so we can return directly the entity)
// or we can create a dto for auditing as well

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    // Get the changes to certain post id
    @GetMapping("/posts/{postId}")
    public List<PostEntity> getPostRevisions(@PathVariable("postId") Long postId) {
        // AuditReader is used to query the revisions made to a certain entity
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = auditReader.getRevisions(PostEntity.class, postId);

        return revisions
                .stream()
                .map(revisionNo -> auditReader.find(PostEntity.class, postId, revisionNo))
                .collect(Collectors.toList());
    }

}
