package spaghedi.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Agent extends BaseEntitiy{

	@Column
	protected String name;
	
	@OneToMany(mappedBy="assignedAgent")
	protected List<ClassifiedFile> files = new ArrayList<ClassifiedFile>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
