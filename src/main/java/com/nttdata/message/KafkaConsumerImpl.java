package com.nttdata.message;


import com.nttdata.document.BootCoin;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaConsumerImpl implements  KafkaConsumer{

    public static Mono<BootCoin> tran;

   @KafkaListener(topics = "bootcoin",groupId = "myGroup")
   public void listenTopic(BootCoin transaction){
       System.out.println(" llego");
       tran=Mono.just(transaction);

   }

    @Override
    public Mono<BootCoin> getTransaction() {
        System.out.println("Entro getTransaction");
       return tran;
    }


}
