package edu.mum.cs544.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.repository.SkillRepository;

@Service
@Transactional
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	public void delete(int id) {
		Skill skill = skillRepository.findOne(id);
		skillRepository.delete(skill);
	}

	public List<Skill> findAll() {
		return skillRepository.findAll();
	}

	public Skill findById(int id) {
		return skillRepository.findOne(id);
	}

}
