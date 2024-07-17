package com.project.employeesinformationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.project.employeesinformationsystem.entities.Job;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepo;
    Job job1;
    Job job2;

    @BeforeEach
    void setUp() {
        job1 = new Job("Testing");
        job2 = new Job("Software Trainee");
        jobRepo.save(job1);
        jobRepo.save(job2);
    }

    @AfterEach
    void tearDown() {
        jobRepo.deleteAll();
    }

    @Test
    void testFindByJobTitle() {
        Job foundJobs = jobRepo.findByJobTitle(job1.getJobTitle());
        assertThat(foundJobs).isNotNull();
        assertThat(foundJobs.getJobTitle()).isEqualTo(job1.getJobTitle());
    }   
}
