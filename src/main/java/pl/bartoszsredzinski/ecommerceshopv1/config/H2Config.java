package pl.bartoszsredzinski.ecommerceshopv1.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author Bartosz Średziński
 */
@Configuration
public class H2Config{

    /**
     * @return Server h2Server
     * @throws SQLException
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException{
        return Server.createTcpServer("-tcp");
    }
}
