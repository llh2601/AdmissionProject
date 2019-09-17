package ca.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.project.model.Result;

import ca.project.repository.ResultRepository;
@Service
public class ResultService {
	private ResultRepository resultRepository;
	
	public ResultService(ResultRepository resultRepository) {
		this.resultRepository = resultRepository;
	}
	
	public List<Result> findAllResults() {
	
		return (List<Result>) resultRepository.findAll();
	}
	
	public Optional<Result> findResultById(Integer id){
		return resultRepository.findById(id);
	}
	
	public void deleteResult(Integer  id) {
		resultRepository.deleteById(id);
	}
	public void saveResult(Result  result) {
		resultRepository.save(result);
	}
}
