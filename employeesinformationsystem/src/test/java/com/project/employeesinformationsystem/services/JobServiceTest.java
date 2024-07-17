package com.project.employeesinformationsystem.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.employeesinformationsystem.dto.JobDto;
import com.project.employeesinformationsystem.entities.Job;
import com.project.employeesinformationsystem.repositories.JobRepository;

@SpringBootTest
public class JobServiceTest {

    @Mock
    private JobRepository jobRepo;

    @InjectMocks
    private JobService jobService;

    private Job job;
    private JobDto jobDto;

    @Test
    void testAddJob() {
        job = new Job();
        job.setJobId(87);
        job.setJobTitle("null1");
        jobDto = new JobDto();
        jobDto.setJobId(job.getJobId());
        jobDto.setJobTitle(job.getJobTitle());
        when(jobRepo.save(job)).thenReturn(job);
        assertThat(jobService.addJob(job)).isEqualTo(jobDto);

    }

}
