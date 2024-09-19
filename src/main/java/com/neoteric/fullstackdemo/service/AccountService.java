package com.neoteric.fullstackdemo.service;

import com.neoteric.fullstackdemo.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

    public String oneToManyUsingHibernate(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        AdharEntity adharEntity= new AdharEntity();
        adharEntity.setName(account.getName());

        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setState(account.getMobileNumber());

        addressEntity.setMyMappedByTestEntity(adharEntity);

        addressEntityList.add(addressEntity);
        adharEntity.setAddressEntityList(addressEntityList);

        session.persist(adharEntity);
        transaction.commit();
        return String.valueOf(adharEntity.adharNumber);
    }




    public Account searchAccount(String accountNumber){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();
        Query<AccountEntity> query= session. createQuery("From AccountEntity a where a.accountNumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber",accountNumber);
        AccountEntity accountEntity=query.list().get(0);
           return null;



    }
    public String oneToManyUsingHibernateFromUI(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNUmber(account.getMobileNumber());




        List<AccountAddrerssEntity> accountAddressEntityList = new ArrayList<>();
        AccountAddrerssEntity accountAddressEntity= new AccountAddrerssEntity();


        accountAddressEntity.setAddress1(account.getAddress().getAddress1());
        accountAddressEntity.setAddress2(account.getAddress().getAddress2());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);

        accountAddressEntityList.add(accountAddressEntity);
        accountEntity.setAccountAddrerssEntityList(accountAddressEntityList);

        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();
    }


    public String createAccountUsingHibernate(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNUmber(account.getMobileNumber());
        session.persist(accountEntity);
        transaction.commit();
        return accountEntity.getAccountNumber();
    }

    public String createAccount(Account account) throws AccountCreationFailedException {
        String accountNumber=null;

        try {

            Connection connection = com.bankdemo.CreatingAccount.service.DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            accountNumber= UUID.randomUUID().toString();


            String query="insert into bank.account values('"+
                    accountNumber+"'"+","+

                    "'"+account.getName()+"'"+","+
                    "'"+account.getPan()+"'"+","+
                    "'"+account.getMobileNumber()+"'"+","
                    + account.getBalance()+")";


            //    insert into bank.account values('1234','rakesh','grc12345','7013776567',20000);

            int status=  stmt.executeUpdate(query);
            if(status==1){

                System.out.println("Account is created "+accountNumber);
            }else {
                throw new AccountCreationFailedException("Account creation is failed"+account.getPan());
            }
        }
        catch (SQLException e){
            System.out.println("Exception ocurred in sql"+e);
        }
        catch (AccountCreationFailedException e){
            System.out.println("Exception ocurred"+e);
            throw e;
        }
        return accountNumber;
    }
}