package com.karna.jobms.job;

import com.karna.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }


    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
        public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id){
        if(jobService.findJobById(id)!=null)
            return new ResponseEntity<>(jobService.findJobById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Long id){
        if(jobService.deleteById(id))
            return ResponseEntity.ok("Jobs deleted successfully");
        else
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job){
        boolean updated= jobService.updateJob(id,job);
        if(updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity("Job not found", HttpStatus.NOT_FOUND);
    }




}
