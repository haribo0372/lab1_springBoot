package com.osipov.infprot.lab1.entity;

import com.osipov.infprot.lab1.entity.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "signature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Signature extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "objectName")
    private String objectName;

    @Column(name = "first8Bytes")
    @Lob
    private byte[] first8Bytes;

    @Column(name = "signatureHash")
    private String signatureHash;

    @Column(name = "signatureTailLength")
    private Integer signatureTailLength;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "startOffset")
    private Integer startOffset;

    @Column(name = "endOffset")
    private Integer endOffset;
}