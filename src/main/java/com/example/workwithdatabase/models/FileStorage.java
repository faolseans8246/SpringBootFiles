package com.example.workwithdatabase.models;

import com.example.workwithdatabase.fileEnums.FileStorageStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file_base")
public class FileStorage implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "File ID")
    private int id;

    @Column(name = "File name")
    private String file_name;

    @Column(name = "File format")
    private String file_format;

    @Column(name = "File type")
    private String content_type;

    @Column(name = "File size")
    private long file_size;

    @Column(name = "File hash ID")
    private String hashId;

    @Column(name = "File upload path")
    private String uploadPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "File status")
    private FileStorageStatus fileStorageStatus;

}
