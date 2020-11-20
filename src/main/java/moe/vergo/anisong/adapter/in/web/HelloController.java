package moe.vergo.anisong.adapter.in.web;

import moe.vergo.anisong.application.port.in.QueryAnisongEventsUseCase;
import moe.vergo.anisong.domain.AnisongEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    private final QueryAnisongEventsUseCase queryAnisongEventsUseCase;

    public HelloController(QueryAnisongEventsUseCase queryAnisongEventsUseCase) {
        this.queryAnisongEventsUseCase = queryAnisongEventsUseCase;
    }

    @GetMapping("/events")
    public List<AnisongEvent> testApp() {
        return queryAnisongEventsUseCase.getAnisongEvents();
    }
}
