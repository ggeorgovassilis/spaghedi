package spaghedi.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClassifiedFile extends BaseEntitiy {

	@Column
	protected String title;

	@Column
	protected String description;
	
	@ManyToMany(mappedBy="files")
	protected List<Suspect> suspects = new ArrayList<Suspect>();
	
	@ManyToOne
	@JoinColumn(name="classifiedfile_agent_id")
	protected Agent assignedAgent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public Agent getAssignedAgent() {
		return assignedAgent;
	}

	public void setAssignedAgent(Agent assignedAgent) {
		this.assignedAgent = assignedAgent;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Suspect> getSuspects() {
		return suspects;
	}

	public void setSuspects(List<Suspect> suspects) {
		this.suspects = suspects;
	}

}
