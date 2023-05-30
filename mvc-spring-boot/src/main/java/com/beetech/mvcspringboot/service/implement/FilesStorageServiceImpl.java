package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import com.beetech.mvcspringboot.utils.FileNameEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * The type Files storage service.
 */
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesStorageServiceImpl.class);
    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new ServerErrorException("Could not initialize folder for upload!", e);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String save(MultipartFile file) {
        try {
            // get original filename
            String filename = Objects.requireNonNull(file.getOriginalFilename());

            filename = FileNameEditor.appendDatetime(filename);

            Files.copy(file.getInputStream(), this.root.resolve(filename));
            return filename;
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Save file error: {}", e.getMessage());
            }
            throw new ServerErrorException("Server error", e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceAccessException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new ServerErrorException("Error: ", e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try (Stream<Path> paths = Files.walk(this.root, 1)) {
            return paths.filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new ServerErrorException("Server error: ", e);
        }
    }
}
