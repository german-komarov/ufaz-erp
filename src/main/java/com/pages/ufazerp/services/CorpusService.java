package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.repositories.CorpusRepository;
import com.pages.ufazerp.util.dto.corpus.CreateCorpusDto;
import com.pages.ufazerp.util.dto.corpus.UpdateCorpusDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class CorpusService {
    private final CorpusRepository corpusRepository;

    public CorpusService(CorpusRepository corpusRepository) {
        this.corpusRepository = corpusRepository;
    }

    public List<Corpus> readAll() {
        return corpusRepository.findAll();
    }

    public Corpus readById(long id) throws NotFoundException {
        return corpusRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("There is no corpus(id=%d)", id)));
    }

    public Corpus createCorpus(CreateCorpusDto dto) throws ValidationException {
        if(dto.getName()==null) {
            throw new ValidationException("Corpus name cannot be null");
        }
        if(corpusRepository.findByName(dto.getName()).isPresent()) {
            throw new ValidationException(String.format("corpus(name=%s) is already exists", dto.getName()));
        }
        Corpus corpus = new Corpus();
        corpus.setName(dto.getName());
        corpus.setAddress(dto.getAddress());
        corpus = corpusRepository.save(corpus);
        return corpus;
    }

    public Corpus updateCorpus(long corpusId, UpdateCorpusDto dto) throws NotFoundException {
        Corpus corpus = readById(corpusId);
        if(dto.getName()!=null) {
            corpus.setName(dto.getName());
        }
        if(dto.getAddress()!=null) {
            corpus.setAddress(dto.getAddress());
        }
        return corpusRepository.save(corpus);
    }

    public void deleteCorpus(long corpusId) {
        if(!corpusRepository.findById(corpusId).isPresent()) {
            return;
        }
        corpusRepository.deleteById(corpusId);
    }
}
