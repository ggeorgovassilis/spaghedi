package spaghedi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import spaghedi.persistence.Agent;
import spaghedi.persistence.ClassifiedFile;
import spaghedi.persistence.SessionDTO;

public class TestFiles {

	RestTemplate rest;

	@Before
	public void setup() {
		rest = new RestTemplate();
	}

	protected SessionDTO login() {
		rest = new RestTemplate();
		ResponseEntity<SessionDTO> response = rest.postForEntity("http://localhost:8080/spaghedi-demo/login/?agentname=root", null,
				SessionDTO.class);
		SessionDTO SessionDTO = response.getBody();
		return SessionDTO;
	}

	Agent createAgent(String name, SessionDTO SessionDTO) {
		Agent agent  = new Agent();
		agent.setName(name);
		ResponseEntity<Agent> response = rest.postForEntity("http://localhost:8080/spaghedi-demo/agents/",
				agent, Agent.class, new HashedMap());
		assertEquals(201, response.getStatusCode().value());
		agent = response.getBody();
		return agent;
	}
	
	ClassifiedFile createFile(String title, Agent agent, SessionDTO SessionDTO) {
		ClassifiedFile file = new ClassifiedFile();
		file.setAssignedAgent(agent);
		file.setTitle(title);
		ResponseEntity<ClassifiedFile> response = rest.postForEntity("http://localhost:8080/spaghedi-demo/files/",
				file, ClassifiedFile.class, new HashedMap());
		assertEquals(201, response.getStatusCode().value());
		return response.getBody();
	}

	ClassifiedFile saveFile(SessionDTO SessionDTO, ClassifiedFile file) {
		ResponseEntity<ClassifiedFile> response = rest.postForEntity("http://localhost:8080/spaghedi-demo/files/"+file.getId(),
				file, ClassifiedFile.class, new HashedMap());
		assertEquals(202, response.getStatusCode().value());
		return response.getBody();
	}

	@Test
	public void test_scenario() throws Exception {
		SessionDTO session = login();
		Agent burtMacklin = createAgent("Burt Macklin", session);
		assertEquals("Burt Macklin", burtMacklin.getName());

		Agent mikeLowrey  = createAgent("Mike Lowrey", session);
		assertEquals("Mike Lowrey", mikeLowrey.getName());
		
		Agent foxMulder = createAgent("Fox Mulder", session);
		assertEquals("Fox Mulder", foxMulder.getName());

		ClassifiedFile file = createFile("X-File", burtMacklin, session);
		assertEquals("X-File", file.getTitle());
		assertEquals(burtMacklin.getName(), file.getAssignedAgent().getName());
		
		file.setDescription("The truth is out there");
		file.setTitle("X-Files");
		file.setAssignedAgent(foxMulder);
		file = saveFile(session, file);
		assertEquals("X-Files", file.getTitle());
		assertEquals("The truth is out there", file.getDescription());
		assertEquals("Fox Mulder", file.getAssignedAgent().getName());
	}
}
