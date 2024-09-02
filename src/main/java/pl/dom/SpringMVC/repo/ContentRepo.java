package pl.dom.SpringMVC.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import pl.dom.SpringMVC.model.Content;
import pl.dom.SpringMVC.model.Status;

public interface ContentRepo extends ListCrudRepository<Content, Integer> {

	
	List<Content> findAllByTitleContains(String keyword); 
	
	@Query("select * from content where status = :status")
	List<Content> listByStatus(@Param("status") Status status);
}
