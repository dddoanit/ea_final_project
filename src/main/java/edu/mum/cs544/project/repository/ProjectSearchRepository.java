/**
 * Author: DatDoan Created Date: Apr 21, 2018
 */
package edu.mum.cs544.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import edu.mum.cs544.project.model.Project;
import edu.mum.cs544.project.model.ProjectStatusEnum;
import edu.mum.cs544.project.utils.SearchProjectParam;

@Repository
public class ProjectSearchRepository {
  @Autowired
  private EntityManager em;

  public List<Project> search(SearchProjectParam params) {
    String sql =
        "select distinct p from Project p left join p.projectSkills ps left join ps.skill s"
            + " where 1 = 1";

    if (!StringUtils.isEmpty(params.getName())) {
      sql += " and LOWER(p.name) like CONCAT('%', LOWER(:name), '%')";
    }
    if (!StringUtils.isEmpty(params.getStatus()) && !"ALL".equals(params.getStatus())) {
      sql += " and p.status = :status";
    }
    if (!StringUtils.isEmpty(params.getLocation())) {
      sql += " and LOWER(p.location) like CONCAT('%', LOWER(:location), '%')";
    }
    if (!StringUtils.isEmpty(params.getSkills())) {
      sql += " and s.id IN :skills";
    }

    TypedQuery<Project> query = em.createQuery(sql, Project.class);

    if (!StringUtils.isEmpty(params.getName())) {
      query.setParameter("name", params.getName());
    }
    if (!StringUtils.isEmpty(params.getStatus()) && !"ALL".equals(params.getStatus())) {
      query.setParameter("status", ProjectStatusEnum.valueOf(params.getStatus()));
    }
    if (!StringUtils.isEmpty(params.getLocation())) {
      query.setParameter("location", params.getLocation());
    }
    if (!StringUtils.isEmpty(params.getSkills())) {
      query.setParameter("skills", params.getSkills());
    }
    return query.getResultList();
  }
}
