package com.osipov.infprot.lab1.dto.signature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSignatureRequest {
    private String objectName;
    private String first8Bytes;
    private String signatureHash;
    private Integer signatureTailLength;
    private String fileType;
    private Integer startOffset;
    private Integer endOffset;
}