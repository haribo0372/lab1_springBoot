package com.osipov.infprot.lab1.mapper;

import com.osipov.infprot.lab1.dto.signature.NewSignatureRequest;
import com.osipov.infprot.lab1.dto.signature.SignatureDto;
import com.osipov.infprot.lab1.entity.Signature;

public class SignatureMapper {
    public static SignatureDto toDto(Signature signature){
        return new SignatureDto(
                signature.getId(),
                signature.getObjectName(),
                signature.getFirst8Bytes(),
                signature.getSignatureHash(),
                signature.getSignatureTailLength(),
                signature.getFileType(),
                signature.getStartOffset(),
                signature.getEndOffset());
    }

    public static Signature fromDto(NewSignatureRequest newSignatureRequest){
        return new Signature(
                null,
                newSignatureRequest.getObjectName(),
                newSignatureRequest.getFirst8Bytes(),
                newSignatureRequest.getSignatureHash(),
                newSignatureRequest.getSignatureTailLength(),
                newSignatureRequest.getFileType(),
                newSignatureRequest.getStartOffset(),
                newSignatureRequest.getEndOffset());
    }
}
