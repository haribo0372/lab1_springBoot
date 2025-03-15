package com.osipov.infprot.lab1.service.signature;

import com.osipov.infprot.lab1.dto.signature.NewSignatureRequest;
import com.osipov.infprot.lab1.dto.signature.SignatureDto;
import com.osipov.infprot.lab1.dto.signature.UpdateSignatureRequest;

public interface SignatureService {
    SignatureDto create(NewSignatureRequest signatureRequest);

    SignatureDto readById(Long id);

    SignatureDto update(UpdateSignatureRequest signatureRequest);

    void delete(Long id);
}
