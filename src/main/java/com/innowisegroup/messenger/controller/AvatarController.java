package com.innowisegroup.messenger.controller;

import com.innowisegroup.messenger.dto.response.AvatarResponse;
import com.innowisegroup.messenger.service.AvatarService;
import com.innowisegroup.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("messengerAPI/v01/users/{idUser}/avatars")
public class AvatarController {
    private final AvatarService avatarService;
    private final UserService userService;

    @Autowired
    public AvatarController(AvatarService avatarService, UserService userService) {
        this.avatarService = avatarService;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveAvatar(@PathVariable(value = "idUser") Long userId,
                                             @RequestParam(value = "file") MultipartFile multipartFile) {
        String avatarId = avatarService.saveAvatar(multipartFile, userId);
        userService.setAvatarId(userId, avatarId);
    }

    @GetMapping
    public ResponseEntity<byte[]> getAvatar(@PathVariable(value = "idUser") Long userId) {
        String avatarId = userService.getAvatarId(userId);
        AvatarResponse avatarResponse = avatarService.findAvatarById(userId, avatarId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(avatarResponse.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        buildContentDispositionHeader(avatarResponse.getFileName()))
                .body(avatarResponse.getContent());
    }

    private String buildContentDispositionHeader(String fileName) {
        return ContentDisposition.attachment().filename("аватар", StandardCharsets.UTF_8)
                .build()
                .toString();
    }
}