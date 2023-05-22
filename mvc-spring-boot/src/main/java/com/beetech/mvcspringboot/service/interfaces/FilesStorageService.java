package com.beetech.mvcspringboot.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * The interface Files storage service.
 */
public interface FilesStorageService {
    /**
     * Init folder upload
     */
    public void init();

    /**
     * Save.
     *
     * @param file the file
     * @return the string
     */
    public String save(MultipartFile file);

    /**
     * Load resource.
     *
     * @param filename the filename
     * @return the resource
     */
    public Resource load(String filename);

    /**
     * Delete all.
     */
    public void deleteAll();

    /**
     * Load all stream.
     *
     * @return the stream
     */
    public Stream<Path> loadAll();
}
