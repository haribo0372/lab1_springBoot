package com.osipov.infprot.lab1.repository;

import com.osipov.infprot.lab1.entity.Signature;
import com.osipov.infprot.lab1.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SignatureRepository extends JpaRepository<Signature, Long> {
}
