package pl.dom.SpringMVC.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.validation.Valid;
import pl.dom.SpringMVC.model.Content;
import pl.dom.SpringMVC.model.Status;
import pl.dom.SpringMVC.repo.ContentCollectionRepo;
import pl.dom.SpringMVC.repo.ContentJdbcTemplateRepo;
import pl.dom.SpringMVC.repo.ContentRepo;


@RestController
@RequestMapping(value = "/api/content")
public class ContentControler {

//	dane z pamieci
//	private final ContentCollectionRepo repo;
	
//	dane z bazy
//	private final ContentJdbcTemplateRepo repo;
	
	private final ContentRepo repo;

	@Autowired
	public ContentControler(ContentRepo repo)
	{
		this.repo = repo;
	}

//	@Autowired
//	public ContentControler(ContentJdbcTemplateRepo repo)
//	{
//		this.repo = repo;
//	}

	
	
	@GetMapping(value = "/qwe")
	public List<Content> findAll(){
		return repo.findAll();
//		return repo.getAllContent();
	}

	@GetMapping(value="/{id}")
	public Content findById(@PathVariable Integer id) {
		return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak danych") );
//		return repo.getContent(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "")
	public void saveContent(@Valid @RequestBody Content content) {
		repo.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(value="/{id}")
	public void update(@RequestBody Content content, @PathVariable Integer id) {
		if(!repo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak danych");
		}else {
			repo.save(content);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable Integer id) {
		repo.deleteById(id);
//		repo.delete(id);
//		repo.deleteContent(id);
	}

	@GetMapping(value="/filer/{keyword}")
	public List<Content> findByTitle(@PathVariable String keyword){
		return repo.findAllByTitleContains(keyword);
	}

	@GetMapping(value="/filer/status/{s}")
	public List<Content> findByStatus(@PathVariable(name = "s") Status status){
			return repo.listByStatus(status);
	}
	
}
