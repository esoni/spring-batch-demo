package it.italiancoders.demobatch.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class Processor implements ItemProcessor<String, String> {

    @Override
    public String process(String data) throws Exception {
        log.info("process [{}]", data);
        return data.toUpperCase();
    }

}
