package spaghedi.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spaghedi.persistence.Agent;
import spaghedi.persistence.ClassifiedFile;
import spaghedi.persistence.ClassifiedFileDao;

@RestController
@RequestMapping(path="/files")
@Transactional
public class ClassifiedFilesController {

	@Autowired
	ClassifiedFileDao files;
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public ResponseEntity<ClassifiedFile> createFile(@RequestBody ClassifiedFile file){
		file = files.saveAndFlush(file);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(file.getId()).toUri();
		return ResponseEntity.created(location).body(file);
	}

	@RequestMapping(path="/search", method=RequestMethod.GET)
	public List<ClassifiedFile> searchForFiles(@RequestParam("query") String query){
		return Arrays.asList(new ClassifiedFile());
	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClassifiedFile> read(@PathVariable("id") Long id){
		ClassifiedFile file = files.getOne(id);
		if (file == null)
			return ResponseEntity.notFound().build();
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		return ResponseEntity.ok(mapper.map(file, ClassifiedFile.class));
	}

	@RequestMapping(path="/{id}", method=RequestMethod.POST)
	public ResponseEntity<ClassifiedFile> update(@PathVariable("id") Long id, @RequestBody ClassifiedFile dto){
		dto = files.saveAndFlush(dto);
		return ResponseEntity.accepted().body(dto);
	}

}
