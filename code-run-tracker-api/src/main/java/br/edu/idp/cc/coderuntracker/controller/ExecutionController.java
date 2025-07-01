package br.edu.idp.cc.coderuntracker.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.idp.cc.coderuntracker.model.Execution;
import br.edu.idp.cc.coderuntracker.repository.ExecutionRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/executions")
public class ExecutionController {

    private final ExecutionRepository executionRepository;

    public ExecutionController(ExecutionRepository executionRepository){
        this.executionRepository = executionRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerExecution(@RequestBody Execution request, HttpServletRequest httpRequest) {

        try {
            Execution exec = new Execution();

            exec.setInstitution(request.getInstitution());
            exec.setCourse(request.getCourse());
            exec.setSubject(request.getSubject());
            exec.setSemester(request.getSemester());
            exec.setStudentId(request.getStudentId());
            exec.setTaskCode(request.getTaskCode());
            exec.setLocalUser(request.getLocalUser());
            exec.setExecutedAt(request.getExecutedAt() != null ? request.getExecutedAt() : LocalDateTime.now());
            exec.setIpAddress(httpRequest.getRemoteAddr());
        
            executionRepository.save(exec);            

            return ResponseEntity.ok("Execution successfully registered.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }

        

    }
}