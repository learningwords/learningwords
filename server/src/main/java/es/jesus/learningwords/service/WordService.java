package es.jesus.learningwords.service;

import es.jesus.learningwords.domain.User;
import es.jesus.learningwords.domain.Word;
import es.jesus.learningwords.repository.LanguageRepository;
import es.jesus.learningwords.repository.UserRepository;
import es.jesus.learningwords.repository.WordRepository;
import es.jesus.learningwords.shared.vo.WordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * Created by jesus on 7/09/15.
 */
@Service
public class WordService {
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WordRepository wordRepository;

    public void saveWord(WordVO wordVO){
        //initialize the database Word
        //TODO use dozer or similar
        //TODO Configure cache
        Word word = new Word();
        word.setLanguageFrom(languageRepository.findByCode("UK"));
        word.setLanguageTo(languageRepository.findByCode("ES"));
        word.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        word.setCreatedOn(Calendar.getInstance());
        word.setTextFrom(wordVO.getTextFrom());
        word.setTextTo(wordVO.getTextTo());
        word.setLearnedOn(wordVO.getLearnedOn());
        //Save
        wordRepository.save(word);
    }
}
