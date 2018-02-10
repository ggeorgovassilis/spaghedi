package spaghedi.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Suspect extends BaseEntitiy {

	@Column
	protected String name;

	public List<ClassifiedFile> getFiles() {
		return files;
	}

	public void setFiles(List<ClassifiedFile> files) {
		this.files = files;
	}

	@ManyToMany
	@JoinTable(name = "suspect_file", joinColumns = @JoinColumn(name = "suspect_id"), 
			inverseJoinColumns = @JoinColumn(name = "file_id"))
	protected List<ClassifiedFile> files = new ArrayList<ClassifiedFile>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
