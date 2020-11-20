package moe.vergo.anisong.application.port;

import moe.vergo.anisong.application.port.in.QueryAnisongEventsUseCase;
import moe.vergo.anisong.application.port.out.AnisongEventDataPort;
import moe.vergo.anisong.domain.AnisongEvent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QueryAnisongEventsService implements QueryAnisongEventsUseCase {
    private final AnisongEventDataPort anisongEventDataPort;

    public QueryAnisongEventsService(AnisongEventDataPort anisongEventDataPort) {
        this.anisongEventDataPort = anisongEventDataPort;
    }

    @Override
    public List<AnisongEvent> getAnisongEvents() {
        return anisongEventDataPort.getAnisongEventData().stream()
                .sorted(Comparator.comparing(AnisongEvent::getStartTime))
                .collect(Collectors.toList());
    }
}
