package edu.mum.cs544.project;

import static org.assertj.core.api.Assertions.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.mum.cs544.project.FinalProjectApplication;
import edu.mum.cs544.project.model.Skill;
import edu.mum.cs544.project.service.SkillService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalProjectApplication.class)
public class SkillTests {

  @Autowired
  private SkillService skillService;
  
  @Test
  public void test() {
    
    // Test insert
    Skill skill = new Skill();
    skill.setName("Database");
    Skill savedSkill = skillService.save(skill);
    if(savedSkill.getId() == 0) {
      fail("Failed to insert a new skill.");
    }
    
    // test delete
    skillService.delete(skill.getId());
    Skill deletedSkill = skillService.findById(skill.getId());
    if (deletedSkill != null) {
      fail("Failed to delete skill.");
    }
    
  }

}
