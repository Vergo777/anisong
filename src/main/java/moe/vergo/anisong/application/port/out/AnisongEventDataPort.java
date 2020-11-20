package moe.vergo.anisong.application.port.out;

import moe.vergo.anisong.domain.AnisongEvent;

import java.util.List;

public interface AnisongEventDataPort {
    List<AnisongEvent> getAnisongEventData();
}
