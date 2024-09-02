package pl.dom.SpringMVC.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;

@Table(value="CONTENT")
public record Content (
		
		@Id
		Integer id,
		@NotBlank
		@Column(value="TITLE")
		String title,
		String desc,
		Status status,
		@Column(value="CONTENTTYPE")
		Type contentType,
		@Column(value="DATECREATED")
		LocalDateTime dateCreated,
		@Column(value="DATEUPDATED")
		LocalDateTime dateUpdated,
		String url
		) {

	public Content(Integer int1, String string, String string2, String string3, String string4, Timestamp timestamp,
			Timestamp timestamp2) {
//		this(int1, string, string2, string3, string4, timestamp, timestamp2, "asd");
		this(int1, string, string2, Status.valueOf(string3), Type.valueOf(string4), LocalDateTime.now(), LocalDateTime.now(), "asd");
	}

}
