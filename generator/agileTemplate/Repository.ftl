package ${repositoryPackage};

import com.agile.mvc.model.entity.${className}Entity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* Created by 佟盟
*/
public interface ${className}Repository extends JpaRepository<${className}Entity,${primaryKeyPropertyType}> {

}
