package com.portfolio.showcase_spring.Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AddressService {
    private final AddressRepo addressRepo;
    private final JmsTemplate jmsTemplate;

    private final static int SLEEP_TIME = 500;
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepo addressRepo, JmsTemplate jmsTemplate) {
        this.addressRepo = addressRepo;
        this.jmsTemplate = jmsTemplate;
    }

    public Iterable<AddressEntity> findAll() {
        return addressRepo.findAllByOrderByIdAsc();

    }

    public void initVacantNumber(AddressEntity address) {
        if (address.getVacantCount() == null) {
            address.setVacantCount(new Random().nextInt(5, 10));
        }
    }

    private void modifyVacantNumber(Long addressId, int quantity) {
        var address = addressRepo.findById(addressId).orElse(null);
        if (address == null) {
            logger.warn("Coud not find {}", addressId);
            return;
        }
        addressRepo.modifyVacantCount(address.getId(), quantity);
    }

    public void save(AddressEntity address) {
        addressRepo.save(address);
    }

//    @JmsListener(destination = "rooms.order")
//    public void test(String message) {
//        var parts = message.split(":");
//        var addressId = Long.parseLong(parts[0]);
//        var quantity = Integer.parseInt(parts[1]);
//        decreaseVacantNumber(addressId, quantity);
//        logger.info("{} test: {}:{}", Thread.currentThread().getId(), addressId, quantity);
//    }

    @JmsListener(destination = "rooms.order")
    public void consumeOrder(String message) {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var parts = message.split(":");
        var addressId = Long.parseLong(parts[0]);
        var quantity = Integer.parseInt(parts[1]);
        modifyVacantNumber(addressId, quantity);
        logger.info("{} consumed: {}:{}", Thread.currentThread().getId(), addressId, quantity);
    }

    public void publishOrder(Long addressId, int quantity) {
        var address = addressRepo.findById(addressId).orElse(null);
        if (address == null) return;

        jmsTemplate.convertAndSend("rooms.order", address.getId() + ":" + quantity, message -> {
            message.setStringProperty("JMSXGroupID", addressId.toString());
            return message;
        });
        logger.info("{} Published: {}:{}", Thread.currentThread().getId(), address.getId(), quantity);
    }
}

