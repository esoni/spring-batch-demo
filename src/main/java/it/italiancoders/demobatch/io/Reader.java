package it.italiancoders.demobatch.io;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Reader implements ItemReader<String> {

    private List<String> messages = new ArrayList();

    private AtomicInteger count =  new AtomicInteger(0);


    public Reader() {
        for(int i= 0; i< 50; i++) {
            messages.add("item" + i);
        }
    }

    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {
        int nC = count.addAndGet(1);

        if (nC < messages.size()) {
            Thread.sleep(100);
            log.info("new read [{}]", messages.get(nC));

            return messages.get(nC);
        }
        return null;
    }

}
