/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PrasanthKumar
 */
public class FieldValidator {

    private String errorDetail="";

    public void setErrorDetail(String errorDetail) {
        this.errorDetail += errorDetail;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public boolean phoneValidation(String phoneNo) {
        boolean status = false;
        
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) {
            status=true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            status= true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            status= true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            status= true;
        } //return false if nothing matches the input
        else {
            status= false;
        }
        if(phoneNo.equalsIgnoreCase(""))
            status = true;
        if(status == false){            
            this.setErrorDetail("| PHONE NUMBER IS NOT VALID |");
        }
        return status;
    }

    public boolean emailValidation(String email) {
        boolean status = false;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) email);
        status = matcher.matches();
        
        if(email.equalsIgnoreCase(""))
            status = true;
        
        if(status == false){            
            this.setErrorDetail("| EMAIL ID IS NOT VALID |");
        }
        return status;
    }

    public boolean firstnameValidation(String text) {
        boolean status = false;
        if (text.length() < 30) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| FIRST NAME IS TOO BIG |");
        }
        return status;
    }
    public boolean lastnameValidation(String text) {
        boolean status = false;
        if (text.length() < 30) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| LAST NAME IS TOO BIG |");
        }
        return status;
    }

    public boolean serviceDescValidation(String text) {
        boolean status = false;
        if (text.length() < 100) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| SERVICE DESCRIPTION IS TOO BIG |");
        }
        return status;
    }

    public boolean addressValidation(String text) {
        boolean status = false;
        if (text.length() < 100) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| ADDRESS IS TOO BIG |");
        }
        return status;
    }

    public boolean cityValidation(String text) {
        boolean status = false;
        if (text.length() < 20) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| CITY VALUE IS TOO BIG |");
        }
        return status;
    }

    public boolean stateValidation(String text) {
        boolean status = false;
        if (text.length() < 20) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| STATE VALUE IS TOO BIG |");
        }
        return status;
    }

    public boolean countryValidation(String text) {
        boolean status = false;
        if (text.length() < 100) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| COUNTRY VALUE IS TOO BIG |");
        }
        return status;
    }

    public boolean donationValidation(String text) {
        boolean status = false;
        
        if (text.matches("^-?\\d+$")) {
            if(text.length()<10){
                status = true;
            }
        }
        
        if(status == false){            
            this.setErrorDetail("| DONATION VALUE IS NOT VALID |");
        }
        return status;
    }

    public boolean paymentDetValidation(String text) {
        boolean status = false;
        if (text.length() < 100) {
            status = true;
        }
        
        if(status == false){            
            this.setErrorDetail("| PAYMENT DETAIL IS TOO BIG |");
        }
        return status;
    }
    
    public String doDevValidation(String firstname, String lastname, String phone, String email, String address, String city, String state, String country){
        firstnameValidation(firstname);
        lastnameValidation(lastname);
        phoneValidation(phone);
        emailValidation(email);
        addressValidation(address);
        cityValidation(city);
        stateValidation(state);
        countryValidation(country);
        return getErrorDetail();
                
    }
    
    public String doValidation(String service, String donation, String payDet){
    
        serviceDescValidation(service);
        donationValidation(donation);
        paymentDetValidation(payDet);
        return getErrorDetail();
                
    }

}
