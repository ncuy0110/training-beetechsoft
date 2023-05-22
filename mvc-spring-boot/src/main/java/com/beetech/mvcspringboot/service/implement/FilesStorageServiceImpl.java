package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.repository.FileRepository;
import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import com.beetech.mvcspringboot.utils.FileNameEditor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
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
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
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
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
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
            throw new RuntimeException(e.getMessage());
        }
    }
}
