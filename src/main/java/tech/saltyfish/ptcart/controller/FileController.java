package tech.saltyfish.ptcart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {


    @Value("${local.file.path}")
    String fileUploadRootDir;

    public FileController() {

    }

    @PostMapping("api/file")
    public Map<String, String> uploadFile(Principal principal, @RequestParam("file") MultipartFile file) {
        Map<String, String> rs = new HashMap<>();
        String username = principal.getName();
        File convertFile = new File(fileUploadRootDir + username + "/" + file.getOriginalFilename());
        File dir = new File(fileUploadRootDir + username + '/');
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            rs.put("status", "ok");
        } catch (IOException e) {
            e.printStackTrace();
            rs.put("status", "fail");
        }
        return rs;
    }


    @GetMapping("/file/{username}/{fileName}")
    public ResponseEntity<Object> downloadFile(@PathVariable(name = "fileName") String fileName, @PathVariable String username) throws FileNotFoundException {

        File file = new File(fileUploadRootDir + username + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", fileName));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);
    }
}
