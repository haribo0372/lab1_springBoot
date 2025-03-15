package com.osipov.infprot.lab1.service.signature;

import com.osipov.infprot.lab1.dto.signature.NewSignatureRequest;
import com.osipov.infprot.lab1.dto.signature.SignatureDto;
import com.osipov.infprot.lab1.dto.signature.UpdateSignatureRequest;
import com.osipov.infprot.lab1.entity.Signature;
import com.osipov.infprot.lab1.mapper.SignatureMapper;
import com.osipov.infprot.lab1.repository.SignatureRepository;
import com.osipov.infprot.lab1.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignatureServiceImpl extends BaseService<Signature> implements SignatureService {
    @Autowired
    public SignatureServiceImpl(SignatureRepository repository) {
        super(repository, "entity.Signature");
    }

    @Override
    public SignatureDto create(NewSignatureRequest signatureRequest) {
        Signature signature = SignatureMapper.fromDto(signatureRequest);
        return SignatureMapper.toDto(super.save(signature));
    }

    @Override
    public SignatureDto readById(Long id) {
        return SignatureMapper.toDto(super.findByIdBase(id));
    }

    @Override
    public SignatureDto update(UpdateSignatureRequest signatureRequest) {
        Signature signature = super.findByIdBase(signatureRequest.getId());

        if (signatureRequest.getObjectName() != null && !signatureRequest.getObjectName().isBlank())
            signature.setObjectName(signatureRequest.getObjectName());

        if (signatureRequest.getFirst8Bytes() != null && !signatureRequest.getFirst8Bytes().isBlank())
            signature.setFirst8Bytes(signatureRequest.getFirst8Bytes());

        if (signatureRequest.getSignatureHash() != null && !signatureRequest.getSignatureHash().isBlank())
            signature.setSignatureHash(signatureRequest.getSignatureHash());

        if (signatureRequest.getSignatureTailLength() != null)
            signature.setSignatureTailLength(signatureRequest.getSignatureTailLength());

        if (signatureRequest.getFileType() != null && !signatureRequest.getFileType().isBlank())
            signature.setFileType(signatureRequest.getFileType());

        if (signatureRequest.getStartOffset() != null)
            signature.setStartOffset(signatureRequest.getStartOffset());

        if (signatureRequest.getEndOffset() != null)
            signature.setEndOffset(signatureRequest.getEndOffset());

        return SignatureMapper.toDto(super.save(signature));
    }

    @Override
    public void delete(Long id) {
        super.deleteById(id);
    }
}
