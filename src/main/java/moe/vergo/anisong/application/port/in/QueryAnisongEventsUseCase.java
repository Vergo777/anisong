package moe.vergo.anisong.application.port.in;

import moe.vergo.anisong.domain.AnisongEvent;

import java.util.List;

public interface QueryAnisongEventsUseCase {
    List<AnisongEvent> getAnisongEvents();
}
