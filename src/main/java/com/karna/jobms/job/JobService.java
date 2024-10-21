package com.karna.jobms.job;

import com.karna.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO findJobById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job job);
}
