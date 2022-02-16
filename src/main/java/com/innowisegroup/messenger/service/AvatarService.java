package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.response.AvatarResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    String saveAvatar(MultipartFile multipartFile, Long userId);

    AvatarResponse findAvatarById(Long userId, String avatarId);
}
