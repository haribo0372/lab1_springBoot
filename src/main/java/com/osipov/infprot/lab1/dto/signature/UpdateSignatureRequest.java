package com.osipov.infprot.lab1.dto.signature;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSignatureRequest {
    @NotNull
    private Long id;

    private String objectName;
    private byte[] first8Bytes;
    private String signatureHash;
    private Integer signatureTailLength;
    private String fileType;
    private Integer startOffset;
    private Integer endOffset;
}