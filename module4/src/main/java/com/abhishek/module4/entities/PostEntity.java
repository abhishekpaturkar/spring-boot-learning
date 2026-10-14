package com.abhishek.module4.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// To tell hibernate envers to keep the field in this class in the auditing
@Audited
public class PostEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // This field will be ignored for auditing by hibernate envers
//    @NotAudited
    private String description;

    // These methods are in AuditingEntityListener
    // Database lifecycle methods
    // We can implement our own version of auditing
    // This can also be used for any update on any other entity based on here save, update or delete
    @PrePersist
    void beforeSave() {

    }

    @PreUpdate
    void beforeUpdate() {

    }

    @PreRemove
    void beforeDelete() {

    }
}
