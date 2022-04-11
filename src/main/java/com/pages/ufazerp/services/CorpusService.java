package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.repositories.CorpusRepository;
import com.pages.ufazerp.util.dto.CreateCorpusDto;
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

    public Corpus createCorpus(CreateCorpusDto dto) throws ValidationException {
        if(dto.getName()==null) {
            throw new ValidationException("Corpus name cannot be null");
        }
        Corpus corpus = new Corpus();
        corpus.setName(dto.getName());
        corpus.setAddress(dto.getAddress());
        corpus = corpusRepository.save(corpus);
        return corpus;
    }
}
