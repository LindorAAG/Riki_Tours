package za.co.whcb.tp2.rikitours.factories.customer;

import za.co.whcb.tp2.rikitours.domain.customer.Customer;

/**
 * Created by Game330 on 2016-10-10.
 */


public class CustomerFactory {

    public static Customer getCustomer(long id, String name, String surname, String customer_no) {
        Customer customer = new Customer.Builder()
                .id(id)
                .name(name)
                .surname(surname)
                .customer_no(customer_no)
                .build();
        return customer;

    }

//    public static Customer getCustomer(long id, String name, String surname, String email, String dob) {
//        Customer customer = new Customer.Builder()
//                .id(id)
//                .name(name)
//                .surname(surname)
//                .customer_no("")
//                .build();
//        customer.setEmail(email);
//        return customer;
//
//
//    }
}

