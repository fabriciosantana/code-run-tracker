package br.edu.idp.cc.coderuntracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.idp.cc.coderuntracker.model.Execution;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {

}
