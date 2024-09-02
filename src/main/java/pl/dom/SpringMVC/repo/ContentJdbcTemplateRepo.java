package pl.dom.SpringMVC.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.dom.SpringMVC.model.Content;

@Repository
public class ContentJdbcTemplateRepo {

	private final JdbcTemplate jdbcTemp;
	
	public ContentJdbcTemplateRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemp = jdbcTemplate;
	}
	
	private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Content(rs.getInt("id"),
				rs.getString("title"),
				rs.getString("desc"),
				rs.getString("status"),
				rs.getString("contentType"),
				rs.getTimestamp("dateCreated"),
				rs.getTimestamp("dateUpdated")
//				rs.getString("url")
				);
	}
		
	public List<Content> getAllContent(){
		String sql = "SELECT * FROM Content";
		List<Content> contents = jdbcTemp.query(sql, ContentJdbcTemplateRepo::mapRow);
		return contents;
	}
	
	public void createContent(String title, String desc, String status, String type) {
		String sql = "INSERT INTO Content(title, desc, status, type, dateCreated) VALUES (?, ?, ?, ?, now())";
		jdbcTemp.update(sql, title, desc, status, type);
	}
	
	public void updateContent(int id, String title, String desc, String status, String type) {
		String sql = "UPDATE Content SET title=?, desc=?, status=?, type=?, dateCreated= now() WHERE id=?";
		jdbcTemp.update(sql, title, desc, status, type, id);
	}
	
	public void deleteContent(int id) {
		String sql = "DELETE FROM Content WHERE ID = ?";
		jdbcTemp.update(sql, id);
	}
	
	public Content getContent(int id) {
		String sql = "SELECT * FROM Content WHERE id = ?";
		Content content = jdbcTemp.queryForObject(sql, ContentJdbcTemplateRepo::mapRow, new Object[] {id});
		return content;
	}
}
