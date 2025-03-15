package com.osipov.infprot.lab1.controller;

import com.osipov.infprot.lab1.dto.signature.NewSignatureRequest;
import com.osipov.infprot.lab1.dto.signature.SignatureDto;
import com.osipov.infprot.lab1.dto.signature.UpdateSignatureRequest;
import com.osipov.infprot.lab1.service.signature.SignatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signature")
@RequiredArgsConstructor
public class SignatureController {
    private final SignatureService signatureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignatureDto create(@RequestBody @Valid NewSignatureRequest request){
        return signatureService.create(request);
    }

    @GetMapping("/{signatureId}")
    public SignatureDto readById(@PathVariable Long signatureId){
        return signatureService.readById(signatureId);
    }

    @PatchMapping
    public SignatureDto update(@RequestBody @Valid UpdateSignatureRequest request){
        return signatureService.update(request);
    }

    @DeleteMapping("/{signatureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long signatureId){
        signatureService.delete(signatureId);
    }
}
