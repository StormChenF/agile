package ${repositoryPackage};

import com.agile.mvc.model.entity.${tableName}Entity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* Created by 佟盟
*/
public interface ${tableName}Repository extends JpaRepository<${tableName}Entity,${keyType}> {

}
