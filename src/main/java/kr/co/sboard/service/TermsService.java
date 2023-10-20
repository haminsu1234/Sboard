package kr.co.sboard.service;

import kr.co.sboard.controller.entity.TermsEntity;
import kr.co.sboard.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermsService {

    @Autowired
    private TermsRepository repo;

    public TermsEntity selectTerms(){
        return repo.findById(1).get();
    }

}
