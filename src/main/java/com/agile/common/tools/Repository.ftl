package ${repositoryPackage};

import com.agile.mvc.model.entity.${tableName}Entity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
* Created by 佟盟
*/
public interface ${tableName}Repository extends PagingAndSortingRepository<${tableName}Entity,${keyType}> {

}
