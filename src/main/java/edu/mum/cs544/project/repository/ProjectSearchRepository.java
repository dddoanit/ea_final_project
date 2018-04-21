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
    // if (!StringUtils.isEmpty(genreShow) && !"ALL".equals(genreShow)) {
    // sql += " and tv.genre = :genreShow";
    // }
    // if (ratingShow >= 1) {
    // sql += " and tv.rating = :ratingShow";
    // }
    // if (!StringUtils.isEmpty(nameArtis)) {
    // sql += " and LOWER(artist.name) like CONCAT('%', LOWER(:nameArtis), '%')";
    // }
    // if (!StringUtils.isEmpty(nameCharacter)) {
    // sql += " and LOWER(character.name) like CONCAT('%', LOWER(:nameCharacter), '%')";
    // }
    // if (!StringUtils.isEmpty(nameDirector)) {
    // sql += " and LOWER(director.name) like CONCAT('%', LOWER(:nameDirector), '%')";
    // }
    //
    // TypedQuery<TvShow> query = em.createQuery(sql, TvShow.class);
    //
    // if (!StringUtils.isEmpty(nameShow)) {
    // query.setParameter("nameShow", nameShow);
    // }
    // if (!StringUtils.isEmpty(genreShow) && !"ALL".equals(genreShow)) {
    // query.setParameter("genreShow", TvShow.Genre.valueOf(genreShow));
    // }
    // if (ratingShow >= 1) {
    // query.setParameter("genreShow", genreShow);
    // }
    // if (!StringUtils.isEmpty(nameArtis)) {
    // query.setParameter("nameArtis", nameArtis);
    // }
    // if (!StringUtils.isEmpty(nameCharacter)) {
    // query.setParameter("nameCharacter", nameCharacter);
    // }
    // if (!StringUtils.isEmpty(nameDirector)) {
    // query.setParameter("nameDirector", nameDirector);
    // }
    TypedQuery<Project> query = em.createQuery(sql, Project.class);
    return query.getResultList();
  }
}
