package com.karna.jobms.job.impl;


import com.karna.jobms.job.Job;
import com.karna.jobms.job.JobRepository;
import com.karna.jobms.job.JobService;
import com.karna.jobms.job.dto.JobDTO;
import com.karna.jobms.job.external.Company;
import com.karna.jobms.job.external.Review;
import com.karna.jobms.job.mapper.JobMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {



    private JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){

        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8082/companies/"+job.getCompanyId(), Company.class);
        List<Review> reviews = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
        }).getBody();

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
//
//        job.setId(Long.valueOf(jobId));
//    jobs.add(job);
//    jobId++;

        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
        JobDTO jobDTO = new JobDTO();
        //return jobs.stream().filter(j->j.getId().equals(id)).findFirst().orElse(null);
        Job job= jobRepository.findById(id).orElse(null);
        return convertToDto(job);


    }

    @Override
    public boolean deleteById(Long id) {
       if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

       // return jobs.removeIf(job -> job.getId().equals(id));

    }

    @Override
    public boolean updateJob(Long id, Job job) {
//        for(Job j: jobs){
//            if(j.getId().equals(id)){
//                j.setDescription(job.getDescription());
//                j.setTitle(job.getTitle());
//                j.setLocation(job.getLocation());
//                j.setMaxSalary(job.getMaxSalary());
//                j.setMinSalary(job.getMinSalary());
//                return true;
//            }
//        }
//        return false;

       Optional<Job> optionalJOb= jobRepository.findById(id);
        if(optionalJOb.isPresent()){
            Job j=optionalJOb.get();
            j.setDescription(job.getDescription());
                j.setTitle(job.getTitle());
                j.setLocation(job.getLocation());
                j.setMaxSalary(job.getMaxSalary());
                j.setMinSalary(job.getMinSalary());

                jobRepository.save(j);
                return true;
        }
return false;

    }


}
