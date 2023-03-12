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

@Service
public class ForbiddenWordServiceImpl extends EntityServiceBase<ForbiddenWord> implements ForbiddenWordService, InitializingBean {

    private static Pattern censorPattern;

    @Override
    public void afterPropertiesSet() throws Exception {
       censorPattern =  compileCensorPattern();
    }

    protected ForbiddenWordServiceImpl(ForbiddenWordRepository repository) {
        super(repository);

    }

    public Iterator<ForbiddenWord> iterate(){
          return repository.findAll().iterator();
    }


    @Override
    public Message censorMessage(Message messageToCensor) {

        messageToCensor.setMessage(messageToCensor.getMessage().replaceAll(String.valueOf(censorPattern), "<censored>"));

        return messageToCensor;
    }

    @Override

    public Pattern compileCensorPattern() {
        Iterator<ForbiddenWord> iterator = iterate();
        StringBuilder patternStringBuilder = new StringBuilder();
        String helper;

        HashMap<String, String> lettersDigitsHashMap = new HashMap<String, String>(){
            {
                put("a", "@");
                put("i", "!");
                put("e", "3");
                put("g", "6");
                put("s", "\\$");
                put("l", "1");
                put("t", "7");
            }
        };


        while(iterator.hasNext()){
            helper =  iterator.next().getWord();

            for(String key : lettersDigitsHashMap.keySet()){
                try{
                    helper = helper.replaceAll(key, "("+key+"|"+lettersDigitsHashMap.get(key)+")");
                }
                catch (IllegalArgumentException e){
                    System.out.println("asd");
                }

            }
            patternStringBuilder.append(helper);
            patternStringBuilder.append("?[^(\\s|\\.)]+|");

        }
        patternStringBuilder.deleteCharAt(patternStringBuilder.length()-1);
        return Pattern.compile(String.valueOf(patternStringBuilder), Pattern.CASE_INSENSITIVE);

    }


}
