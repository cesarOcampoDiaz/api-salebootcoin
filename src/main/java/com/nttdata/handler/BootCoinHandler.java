package com.nttdata.handler;


import com.nttdata.document.BootCoin;
import com.nttdata.document.Transaction;
import com.nttdata.message.KafkaConsumer;
import com.nttdata.models.AccountYanki;
import com.nttdata.models.BankAccount;
import com.nttdata.repository.BootCoinRepository;
import com.nttdata.util.Constants;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Component
public class BootCoinHandler {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    private final BootCoinRepository transactionalRepository;
    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public BootCoinHandler(BootCoinRepository transactionalRepository) {

        this.transactionalRepository = transactionalRepository;
    }

    public Mono<ServerResponse> findBootCoin(ServerRequest serverRequest) {

        var accountItem = kafkaConsumer.getTransaction();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body((Object) kafkaConsumer.getTransaction().filter(fil -> fil.getPaymentMethod() == 1).map(
                                bootCoin -> {
                                    var a = validAccountYanki(bootCoin.getClientId());

                                    return validAccountYanki(bootCoin.getClientId()).flatMap(
                                            accountYanki -> {

                                                return transactionalRepository.save
                                                        (new Transaction
                                                                (bootCoin.getId(), bootCoin.getPaymentMethod(), accountYanki.getPhone(), bootCoin.getClientId(),
                                                                        accountYanki.getCurrencyId(), accountYanki.getAccountId(), null, bootCoin.getAmount(),
                                                                        LocalDateTime.now()));
                                            }


                                    );
                                }

                        )


                        , Transaction.class).switchIfEmpty(notFound);

    }

    public Mono<BankAccount> validAccount(String codeClient) {

        return WebClient.create(Constants.PATH_SERVICE_CLIENT)
                .get().uri(Constants.PATH_SERVICE_BANKACCOUNT.concat("client/").concat(codeClient))
                .retrieve().bodyToFlux(BankAccount.class).collectList().map(x -> {
                            return x.stream().collect(Collectors.toList()).get(0);

                        }
                );

    }

    public Mono<AccountYanki> validAccountYanki(Integer codeClient) {

        return WebClient.create(Constants.PATH_SERVICE_ACCOOUNT_YANKI)
                .get().uri(Constants.PATH_SERVICE_ACCOOUNT_YANKI_URI.concat("/client/").concat(codeClient.toString()))
                .retrieve().bodyToMono(AccountYanki.class);

    }


}
