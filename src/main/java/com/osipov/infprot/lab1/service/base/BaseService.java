package com.osipov.infprot.lab1.service.base;

import com.osipov.infprot.lab1.entity.base.BaseModel;
import com.osipov.infprot.lab1.exceptions.model.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

@Slf4j
public abstract class BaseService<T extends BaseModel> {
    protected final JpaRepository<T, Long> repository;
    protected final String nameClassForLog;

    public BaseService(JpaRepository<T, Long> repository, String nameClassForLog) {
        this.repository = repository;
        this.nameClassForLog = nameClassForLog;
    }

    protected T findByIdBase(Long id) {
        T entity = repository.findById(id).orElseThrow(
                () -> {
                    String message = format("%s{id=%d} не найдено", nameClassForLog, id);
                    log.info(message);
                    return new NotFoundException(message);
                }
        );

        log.debug("{}{id={}} получен из БД и возвращен", nameClassForLog, id);

        return entity;
    }

    protected T save(T entity) {
        T saved = repository.save(entity);
        log.debug("{}{id={}} сохранен в БД", nameClassForLog, saved.getId());
        return saved;
    }

    protected Collection<T> findAllBase(int from, int size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);
        List<T> found = repository.findAll(pageable).getContent();

        log.debug("findAll(from={}, size={}) -> Все объекты {} возвращены [size={}]",
                from, size, nameClassForLog, found.size());

        return found;
    }

    protected void deleteById(Long id) {
        repository.deleteById(id);
        log.info("{}{id={}} спешно удален", nameClassForLog, id);
    }
}
