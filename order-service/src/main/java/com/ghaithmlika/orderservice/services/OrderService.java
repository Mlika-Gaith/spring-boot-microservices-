package com.ghaithmlika.orderservice.services;


import com.ghaithmlika.orderservice.dto.InventoryResponse;
import com.ghaithmlika.orderservice.dto.OrderLineItemsDto;
import com.ghaithmlika.orderservice.dto.OrderRequest;
import com.ghaithmlika.orderservice.event.OrderPLacedEvent;
import com.ghaithmlika.orderservice.model.Order;
import com.ghaithmlika.orderservice.model.OrderLineItems;
import com.ghaithmlika.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepo;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPLacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        log.info("Calling inventory service ...");
        Span inventoryServiceLookup = tracer.nextSpan().name("Inventory service lookup");

        try(Tracer.SpanInScope ws =tracer.withSpan(inventoryServiceLookup.start())){
            InventoryResponse [] inventoryResponses = webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.
                            queryParam("skuCode", skuCodes).build())
                    .retrieve().bodyToMono(InventoryResponse[].class).block();

            boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

            if (allProductsInStock){
                orderRepo.save(order);
                kafkaTemplate.send("notificationTopic",new OrderPLacedEvent(order.getOrderNumber()));
                return "Order placed Successfully";
            }else{
                throw new IllegalArgumentException("Product is not in stock, please try again later.");
            }

        }finally{
            inventoryServiceLookup.end();
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
