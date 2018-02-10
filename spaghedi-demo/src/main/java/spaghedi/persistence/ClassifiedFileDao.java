package spaghedi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassifiedFileDao extends JpaRepository<ClassifiedFile, Long>{

	@Query("select f from ClassifiedFile f where f.title like %?1 or f.description like %?1")
	List<ClassifiedFile> search();
}
