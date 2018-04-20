package edu.mum.cs544.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.service.SkillService;

/**
 * Skill Management Controller
 * 
 * @author Lwin Moe Aung
 *
 */

@Controller
@RequestMapping("/admin/skill")
public class SkillManagementController {

	@Autowired
	private SkillService skillService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("skills", skillService.findAll());
		return "admin/skill/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("skill") Skill skill) {
		String view = "redirect:/admin/skill/";
		Skill existingSkill = skillService.findById(skill.getId());
		if (existingSkill != null && skill.getId() == 0) {
			model.addAttribute("errorMsgSkill", "This skill already exists");
			view = "admin/skill/create";
			return view;
		} else {
			skillService.save(skill);
		}
		return view;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String update(Model model, @ModelAttribute("skill") Skill skill,
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			Skill updatedSkill = skillService.findById(id);
			if (updatedSkill != null) {
				updatedSkill.setName(updatedSkill.getName());
			}
			model.addAttribute("skill", updatedSkill);
		}
		return "admin/skill/create";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		skillService.delete(id);
		return "redirect:/admin/skill/";
	}

}
