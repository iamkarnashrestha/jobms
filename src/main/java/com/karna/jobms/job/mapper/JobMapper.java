package com.karna.jobms.job.mapper;

import com.karna.jobms.job.Job;
import com.karna.jobms.job.dto.JobWithCompanyDTO;
import com.karna.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(Job job, Company company){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;

    }
}
