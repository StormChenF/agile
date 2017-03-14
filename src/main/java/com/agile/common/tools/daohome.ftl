package ${package};  

import import com.agile.mvc.model.entity.${tableName};
import org.springframework.data.repository.PagingAndSortingRepository;

/**
* Created by 佟盟 on 2017/1/16
*/
public interface ${tableName}Repository extends PagingAndSortingRepository<${tableName},Integer> {

}
