package ${repositoryPackage};

import com.agile.mvc.model.entity.${className}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
* Created by 佟盟
*/
public interface ${className}Repository extends JpaRepository<${className}Entity,${primaryKeyPropertyType}> {

}
