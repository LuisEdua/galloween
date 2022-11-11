package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreatePaymentRequest;
import com.example.galloween2.controllers.dtos.responses.CreatePaymentResponse;
import com.example.galloween2.entities.Payment;
import com.example.galloween2.repositories.IPaymentRepository;
import com.example.galloween2.services.interfaces.IPaymentService;
import com.example.galloween2.services.interfaces.IReservationStatusService;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private IPaymentRepository repository;

    @Lazy
    @Autowired
    private IUserService userService;

    @Lazy
    @Autowired
    private IReservationStatusService statusService;

    @Override
    public CreatePaymentResponse create(CreatePaymentRequest request, Long user_id) {
        Payment save = repository.save(from(request, user_id));

        return from(save);
    }

    @Override
    public List<CreatePaymentResponse> list(Long id) {
        return repository.findPaymentByUserId(id)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        statusService.nullPayment(id);
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public void findByUser(Long id){
        List<Payment> payment = repository.findPaymentByUserId(id).stream().toList();
        for(int i=0; i<payment.size(); i++)
            toNull(payment.get(i));
    }

    private void toNull(Payment payment) {
        statusService.nullPayment(payment.getId());
    }

    @Override
    public Payment findById(Long id) {
        return findAndEnsureExist(id);
    }

    private Payment from (CreatePaymentRequest request, Long user_id){
        Payment payment = new Payment();
        payment.setPaymentType(request.getPaymentType());
        payment.setUser(userService.findById(user_id));
        return payment;
    }

    private CreatePaymentResponse from (Payment payment){
        CreatePaymentResponse response = new CreatePaymentResponse();
        response.setId(payment.getId());
        response.setPaymentType(payment.getPaymentType());
        return response;
    }

    private Payment findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
    }
}
