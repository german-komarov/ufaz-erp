package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Announce;
import com.pages.ufazerp.domain.User;
import com.pages.ufazerp.repositories.AnnounceRepository;
import com.pages.ufazerp.util.dto.announce.CreateAnnounceDto;
import com.pages.ufazerp.util.dto.announce.UpdateAnnounceDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AnnounceService {

    private final AnnounceRepository announceRepository;

    public AnnounceService(AnnounceRepository announceRepository) {
        this.announceRepository = announceRepository;
    }

    public List<Announce> readAllAnnounces() {
        return announceRepository.findAll();
    }

    public Announce readById(long id) throws NotFoundException {
        return announceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("There is no announce(id=%d)", id)));
    }

    public Announce createAnnounce(CreateAnnounceDto dto) throws ValidationException {
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new ValidationException("Title cannot be null, empty or blank");
        }
        Announce announce = new Announce();
        announce.setTitle(dto.getTitle());
        announce.setText(dto.getText());
        announce.setPublishDate(LocalDateTime.now());
        announce.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return announceRepository.save(announce);
    }

    public Announce updateAnnounce(long id, UpdateAnnounceDto dto) throws NotFoundException {
        Announce announce = readById(id);
        if (!(dto.getTitle() == null || dto.getTitle().trim().isEmpty())) {
            announce.setTitle(dto.getTitle());
        }
        if(!(dto.getText() == null || dto.getText().trim().isEmpty())) {
            announce.setText(dto.getText());
        }
        return announceRepository.save(announce);
    }

    public void deleteAnnounce(long id) {
        if (!announceRepository.findById(id).isPresent()) {
            return;
        }
        announceRepository.deleteById(id);
    }
}
