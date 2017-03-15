package ${package};

import com.agile.mvc.model.entity.${tableName};
import org.springframework.data.repository.PagingAndSortingRepository;

/**
* Created by 佟盟
*/
public interface ${tableName}Repository extends PagingAndSortingRepository<${tableName},${keyType}> {

}
