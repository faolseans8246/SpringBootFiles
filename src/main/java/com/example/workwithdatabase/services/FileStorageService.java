package com.example.workwithdatabase.services;

import com.example.workwithdatabase.fileEnums.FileStorageStatus;
import com.example.workwithdatabase.models.FileStorage;
import com.example.workwithdatabase.repositories.FileStorageRepository;
import com.example.workwithdatabase.services.baseServises.RootFileStorageService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;


@Service
public class FileStorageService implements RootFileStorageService {


    @Autowired
    private final FileStorageRepository fileStorageRepository;

    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;

        this.hashids = new Hashids(getClass().getName(), 6);
    }

    @Value("${upload.folder}")
    public String uploadFolder;

    @Override
    public void saveFiles(MultipartFile multipartFile) {

        FileStorage fileStorage = new FileStorage();

        fileStorage.setFile_name(multipartFile.getOriginalFilename());  // berilgan faylni nomini oladi
        fileStorage.setFile_format(getFormatFile(multipartFile.getOriginalFilename())); // berilgan faylni formatoni oladi M: .pdf
        fileStorage.setFile_size(multipartFile.getSize());  // Berilgan faylni o'lchamini oladi
        fileStorage.setContent_type(multipartFile.getContentType()); // Faylni qanaqa turda ekani M: Video, Photos, ...
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);

        fileStorageRepository.save(fileStorage);

        Date dates = new Date();
        File uploadFolder = new File(String.format("%s/uploaded_files/%d/%d/%d", this.uploadFolder,
                (1900 + dates.getYear()), (1 + dates.getMonth()), (dates.getDate())));

        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            System.out.println("So'ralgan papkalar yaratildi!");
        }

        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",
                (1900 + dates.getYear()),
                (1 + dates.getMonth()),
                dates.getDate(),
                fileStorage.getHashId(),
                fileStorage.getFile_format()
                ));

        fileStorageRepository.save(fileStorage);

        uploadFolder = uploadFolder.getAbsoluteFile();
        File files = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getFile_format()));

        try {
            multipartFile.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    // Formatni umumiy nomdan ajratib olish uchun ishlatildaigan funksiya
    public String getFormatFile(String kengaytma) {
        String ext = null;

        if (kengaytma != null && !kengaytma.isEmpty()) {
            int dot = kengaytma.lastIndexOf(".");

            if (dot > 0 && dot <= kengaytma.length() - 2) {
                ext = kengaytma.substring(dot + 1);
            }
        }

        return ext;
    }


    // Ma'lumotni hash ID bilan olish

    @Transactional(readOnly = true)
    public FileStorage getByHashId(String hashid) {
        return fileStorageRepository.findByHashId(hashid);
    }


    // delete files

    public void deleteFiles(String files) {

        FileStorage fileStorage = getByHashId(files);

        File fayl = new File(String.format("%s/%s", this.uploadFolder, fileStorage.getUploadPath()));

        if (fayl.delete()) {
            fileStorageRepository.delete(fileStorage);
        }

    }

}
