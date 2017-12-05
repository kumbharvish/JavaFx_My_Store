/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopbilling.fx.model;

import java.time.LocalDate;

/**
 *
 * @author Dinesh
 */
public class AccountLookupCriteria {
    private Customer customer;
    private LocalDate entriesSince;
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer cust) {
        customer = cust;
    }
    
    public LocalDate getEntriesSince() {
        return entriesSince;
    }
    
    public void setEntriesSince(LocalDate date) {
        entriesSince = date;
    }
}
