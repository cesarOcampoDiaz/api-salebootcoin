package com.nttdata.message;

import com.nttdata.document.BootCoin;
import reactor.core.publisher.Mono;

public interface KafkaConsumer {

     Mono<BootCoin> getTransaction();
}
