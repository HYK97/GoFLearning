package behavioral.command.apply;

import behavioral.command.after.Command;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class inSpring {

    private DataSource dataSource;

    public inSpring(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Command command) {
        /**
         * SimpleJdbcInsert -> 일종의 Command 구현체
         * */
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("command")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("name", command.getClass().getSimpleName());
        data.put("when", LocalDateTime.now());
        insert.execute(data);
    }

}
