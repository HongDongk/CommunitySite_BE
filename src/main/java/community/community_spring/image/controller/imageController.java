package community.community_spring.image.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class imageController {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            if (imageFile.isEmpty()) {
                return ResponseEntity.badRequest().body("이미지 파일이 비어있습니다.");
            }

            String originalFilename = imageFile.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedFileName = UUID.randomUUID() + extension;

            // ✅ 실제 파일 저장 위치 (로컬 폴더)
            String uploadDirPath = System.getProperty("user.dir") + "/uploads/images/";
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            File dest = new File(uploadDirPath + savedFileName);
            imageFile.transferTo(dest);

            // ✅ 프론트에 보낼 URL
            String imageUrl = "/images/" + savedFileName;
            return ResponseEntity.ok(imageUrl);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("이미지 저장 실패: " + e.getMessage());
        }
    }
}
