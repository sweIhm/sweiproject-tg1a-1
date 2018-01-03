package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends CrudRepository<Post, Long> {

  List<Post> findAllByPublishedTrue();

  @Query(value = "SELECT DISTINCT POST.post_id, POST.title, POST.text, POST.author, POST.email,"
      + " POST.published, POST.views FROM POST JOIN (SELECT pk1.post_id FROM POST_KEYWORD as pk1 "
      + "JOIN KEYWORD as k1 ON pk1.keyword_id = k1.keyword_id WHERE NOT EXISTS ("
      + "SELECT k2.keyword_id FROM KEYWORD as k2 WHERE k2.content IN (?1) AND NOT EXISTS ("
      + "SELECT pk3.post_id FROM POST_KEYWORD as pk3 WHERE pk1.post_id = pk3.post_id "
      + "AND k2.keyword_id = pk3.keyword_id ))) as found ON POST.post_id = found.post_id;",
      nativeQuery = true)
  List<Post> searchFor(List<String> list);

  @Transactional
  @Modifying
  @Query(value = "UPDATE Post p SET p.views = p.views + 1 WHERE p.id = ?1")
  void increaseViewCount(Long id);

  @Query(value = "SELECT p.post_id, p.author, p.email, p.published, p.text, p.title, p.views "
      + "FROM Post p JOIN (SELECT p.post_id, "
      + "((p.views + 1) * 10 * (count(c.post_id) + 1)) * (count(c.email) + 1) as val FROM Post as p"
      + " LEFT JOIN Comment as c ON p.post_id = c.post_id WHERE p.published = true "
      + "GROUP BY p.post_id) as stats ON p.post_id = stats.post_id "
      + "ORDER BY stats.val DESC LIMIT 5;", nativeQuery = true)
  List<Post> trending();

  @Query(value = "SELECT p.post_id, p.author, p.email, p.published, p.text, p.title, p.views "
      + "FROM Post p JOIN (SELECT p.post_id, ((p.views + 1) * 10 * (count(c.post_id) + 1))"
      + " * (count(c.email) + 1) as val FROM (SELECT p.post_id, p.views, p.published "
      + "FROM Post as p JOIN (SELECT kp.post_id, kp.keyword_id FROM Post_Keyword as kp "
      + "JOIN Keyword as k ON kp.keyword_id = k.keyword_id WHERE k.content = ?) as k "
      + "ON p.post_id = k.post_id) as p LEFT JOIN Comment as c ON p.post_id = c.post_id "
      + "WHERE p.published = true GROUP BY p.post_id) as stats ON p.post_id = stats.post_id "
      + "ORDER BY stats.val DESC LIMIT 5", nativeQuery = true)
  List<Post> trending(String keyword);
}