package com.BCS.training.Start.data;

import com.BCS.training.Start.bird.BirdObjectMother;
import com.BCS.training.Start.bird.BirdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SampleData {

    private BirdRepository birdRepository;

    void createSampleData() {
        birdRepository.save(BirdObjectMother.createCanary());
    }
}
