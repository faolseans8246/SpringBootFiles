package com.example.workwithdatabase.services.baseServises;

import com.example.workwithdatabase.models.FileStorage;
import org.springframework.web.multipart.MultipartFile;

public interface RootFileStorageService {

    public void saveFiles(MultipartFile multipartFile);

    public FileStorage getByHashId(String hashid);

    public void deleteFiles(String files);
}
