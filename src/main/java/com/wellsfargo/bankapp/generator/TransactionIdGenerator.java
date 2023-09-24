package com.wellsfargo.bankapp.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.Random;
public class TransactionIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Random random = new Random();
        long min = 10000000000L;
        long max = 99999999999L;
        long id = min + ((long) (random.nextDouble() * (max - min)));
        return id;
    }
}
