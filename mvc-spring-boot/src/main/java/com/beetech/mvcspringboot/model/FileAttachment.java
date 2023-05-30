package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * The type File attachment.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "file_attachment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FileAttachment {
    /**
     * file attachment id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * path to file attachment
     */
    @Column
    @NonNull
    private String path;

    /**
     * original name
     */
    @Column
    @NonNull
    private String originalName;

    /**
     * file type
     */
    @Column
    @NonNull
    private String fileType;

    /**
     * creation time
     */
    @CreationTimestamp
    private LocalDateTime created;

    /**
     * update time
     */
    @UpdateTimestamp
    private LocalDateTime updated;

}
