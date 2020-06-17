package it.italiancoders.demobatch.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> messages) throws Exception {
        log.info("new write [{}]", messages);

        for (String msg : messages) {
        }
    }

}
