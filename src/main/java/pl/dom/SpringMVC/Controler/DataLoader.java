package pl.dom.SpringMVC.Controler;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.dom.SpringMVC.model.Content;
import pl.dom.SpringMVC.repo.ContentRepo;

@Component
public class DataLoader implements CommandLineRunner{

	private final ContentRepo repo;
	private final ObjectMapper objectMapper;
	
	
	public DataLoader(ContentRepo repo, ObjectMapper objectMapper) {
		this.repo = repo;
		this.objectMapper = objectMapper;
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try(InputStream streamInput = TypeReference.class.getResourceAsStream("/data/content.json")){
			repo.saveAll(objectMapper.readValue(streamInput, new TypeReference<List<Content>>() {})); 
		}
	}

}
