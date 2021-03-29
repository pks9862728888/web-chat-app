package com.demo.repositories;

import com.demo.entities.UploadedImageAsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedImageAsFileRepository extends JpaRepository<UploadedImageAsFile, Long> {
}
