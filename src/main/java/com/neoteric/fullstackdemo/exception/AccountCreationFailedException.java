package com.neoteric.fullstackdemo.exception;

    public class AccountCreationFailedException extends Exception{
        public String message;
        public AccountCreationFailedException(String msg){
            this.message=msg;
   }

    }