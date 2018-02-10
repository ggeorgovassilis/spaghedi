package spaghedi.controllers;

import java.net.URI;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spaghedi.persistence.Agent;
import spaghedi.persistence.AgentDao;

@RestController
@RequestMapping(path="/agents")
@Transactional
public class AgentController {

	@Autowired
	AgentDao agents;
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public ResponseEntity<Agent> create(@RequestBody Agent dto) {
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Agent agent = mapper.map(dto, Agent.class);
		agent = agents.saveAndFlush(agent);
		dto = mapper.map(agent, Agent.class);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(location).body(dto);
	}

	@RequestMapping(path="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Agent> create(@PathVariable long id, @RequestBody Agent dto) {
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Agent agent = mapper.map(dto, Agent.class);
		agent = agents.saveAndFlush(agent);
		dto = mapper.map(agent, Agent.class);
		return ResponseEntity.accepted().body(dto);
	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Agent read(@PathVariable("id") long id) {
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Agent agent = agents.getOne(id);
		Agent dto = mapper.map(agent, Agent.class);
		return dto;
	}
}
