package pl.dom.SpringMVC.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import pl.dom.SpringMVC.model.Content;
import pl.dom.SpringMVC.model.Status;
import pl.dom.SpringMVC.model.Type;

@Repository
public class ContentCollectionRepo {

	private final List<Content> contentList = new ArrayList<Content>();
	
	public List<Content> findAll(){
			return contentList;
	}
	
	public Optional<Content> findById(Integer id){
		return contentList.stream()
				.filter(i -> i.id().equals(id)).findFirst();
	}

	public void save(Content content) {
		contentList.removeIf(i -> i.id().equals(content.id()));
		contentList.add(content);
	}	
	
	@PostConstruct
	public void init() {
		Content content = new Content(null, 
				"First Post",
				"First Post description",
				Status.IDEA ,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");
		contentList.add(content);
	}

	public boolean existsById(Integer id) {
		return contentList.stream().
			anyMatch(i -> i.id().equals(id));
	}

	public void delete(Integer id) {
		contentList.removeIf(i -> i.id().equals(id));
	}


}
