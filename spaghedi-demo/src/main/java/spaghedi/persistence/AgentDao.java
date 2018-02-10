package spaghedi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentDao extends JpaRepository<Agent, Long>{

	Agent findByName(String name);
}
