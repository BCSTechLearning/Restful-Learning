package com.BCS.training.Start.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class AppInit implements ApplicationRunner {

//   private Logger Log = LoggerFactory.getLogger(AppInit.class);

    private SampleData sampleData;


    public void run(ApplicationArguments args) {
        log.info("Loading sample DATA into DB...");

        sampleData.createSampleData();
    }
}