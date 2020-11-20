package moe.vergo.anisong.application.port;

import moe.vergo.anisong.application.port.in.QueryAnisongEventsUseCase;
import moe.vergo.anisong.application.port.out.AnisongEventDataPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppServiceConfig {

    @Bean
    public QueryAnisongEventsUseCase queryAnisongEventsUseCase(AnisongEventDataPort anisongEventDataPort) {
        return new QueryAnisongEventsService(anisongEventDataPort);
    }
}
