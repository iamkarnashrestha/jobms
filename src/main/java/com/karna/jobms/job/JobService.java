package com.karna.jobms.job;

import com.karna.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    JobWithCompanyDTO findJobById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job job);
}
