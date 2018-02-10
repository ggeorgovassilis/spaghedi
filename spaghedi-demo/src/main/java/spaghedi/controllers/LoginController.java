package spaghedi.controllers;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spaghedi.persistence.Agent;
import spaghedi.persistence.AgentDao;
import spaghedi.persistence.SessionDTO;

@RestController
@RequestMapping(path="/login")
@Transactional
public class LoginController {

	@Autowired
	AgentDao agents;
	
	@RequestMapping(method=RequestMethod.POST, path="/")
	public ResponseEntity<SessionDTO> login(@RequestParam("agentname") String agentName) {
		Agent agent;
		if (agentName.equals("root")) {
			agent = new Agent();
			agent.setName("root");
		} else
		agent = agents.findByName(agentName);
		if (agent == null)
			return ResponseEntity.notFound().build();
		SessionDTO session = new SessionDTO();
		session.setId(UUID.randomUUID().toString());
		return ResponseEntity.accepted().body(session);
	}
}
