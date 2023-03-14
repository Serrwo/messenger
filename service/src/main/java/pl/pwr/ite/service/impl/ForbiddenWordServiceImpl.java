package pl.pwr.ite.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import pl.pwr.ite.model.entity.ForbiddenWord;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.model.repository.ForbiddenWordRepository;
import pl.pwr.ite.service.ForbiddenWordService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class ForbiddenWordServiceImpl extends EntityServiceBase<ForbiddenWord> implements ForbiddenWordService, InitializingBean {

    private static Pattern censorPattern;

    private static HashMap<String, String> lettersDigitsHashMap = new HashMap<>();

    static {
        lettersDigitsHashMap = new HashMap<>(){{
                put("a", "@");
                put("i", "!");
                put("e", "3");
                put("g", "6");
                put("s", "\\$");
                put("l", "1");
                put("t", "7");
            }};
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       censorPattern =  compileCensorPattern();
    }

    protected ForbiddenWordServiceImpl(ForbiddenWordRepository repository) {
        super(repository);
    }

    @Override
    public Iterator<ForbiddenWord> iterate(){
          return repository.findAll().iterator();
    }

    @Override
    public Message censorMessage(Message message) {
        message.setMessage(message.getMessage().replaceAll(String.valueOf(censorPattern), "<censored>"));
        return message;
    }

    private Pattern compileCensorPattern() {
        var iterator = iterate();
        var patternStringBuilder = new StringBuilder();

        while(iterator.hasNext()){
            var word =  iterator.next().getWord();
            for(String key : lettersDigitsHashMap.keySet()){
                try {
                    word = word.replaceAll(key, "(" + key + "|" + lettersDigitsHashMap.get(key) + ")");
                } catch (PatternSyntaxException ex){
                    throw new RuntimeException("Couldn't compile pattern.", ex);
                }
            }
            patternStringBuilder.append(word);
            patternStringBuilder.append("?[^(\\s|\\.)]+|");
        }
        if(patternStringBuilder.isEmpty()) {
            throw new RuntimeException("Couldn't compile censor pattern, because forbidden word collection is empty.");
        }
        patternStringBuilder.deleteCharAt(patternStringBuilder.length() - 1);
        return Pattern.compile(String.valueOf(patternStringBuilder), Pattern.CASE_INSENSITIVE);
    }


}
