package com.innowisegroup.messenger.service;

import com.innowisegroup.messenger.dto.response.AvatarResponse;
import com.innowisegroup.messenger.exception.AccessRightsException;
import com.innowisegroup.messenger.exception.EmptyFileException;
import com.innowisegroup.messenger.exception.FileNotAvailableException;
import com.innowisegroup.messenger.exception.NotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final GridFsTemplate gridFsTemplate;
    private final String keyUserId = "userId";
    private final String keyId = "_id";
    private final String keyContentType = "ContentType";

    @Autowired
    public AvatarServiceImpl(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    @Override
    public String saveAvatar(MultipartFile multipartFile, Long userId) {
        if (multipartFile.isEmpty()) {
            throw new EmptyFileException("File is empty");
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            DBObject metadata = new BasicDBObject();
            metadata.put(keyUserId, userId);
            metadata.put(keyContentType, multipartFile.getContentType());

            return gridFsTemplate.store(inputStream, multipartFile.getName(), metadata).toHexString();
        } catch (IOException exception) {
            throw new FileNotAvailableException("File " + multipartFile.getName() + " not available.", exception);
        }
    }

    @Override
    public AvatarResponse findAvatarById(Long userId, String avatarId) {
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where(keyId).is(avatarId)));

        checkAvatarExistence(gridFsFile, userId);
        checkUserAccessRightsToAvatar(gridFsFile, userId, avatarId);

        GridFsResource resource = gridFsTemplate.getResource(gridFsFile);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] content = inputStream.readAllBytes();
            String contentType = (String) gridFsFile.getMetadata().get(keyContentType);
            return new AvatarResponse(contentType, gridFsFile.getFilename(), content);
        } catch (IOException e) {
            throw new FileNotAvailableException("File not available");
        }
    }

    private void checkAvatarExistence(GridFSFile gridFsFile, Long userId) throws NotFoundException {
        if (gridFsFile == null) {
            throw new NotFoundException("Can't find image for user with id " + userId);
        }
    }

    private void checkUserAccessRightsToAvatar(GridFSFile gridFsFile, Long userId, String avatarId)
            throws NotFoundException {
        if (!gridFsFile.getMetadata().get(keyUserId).equals(userId)) {
            throw new AccessRightsException("The user (id = " + userId + ") " +
                    "does not have avatar (id = " + avatarId + ")");
        }
    }
}
