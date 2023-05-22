package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileAttachment, Long> {
}
