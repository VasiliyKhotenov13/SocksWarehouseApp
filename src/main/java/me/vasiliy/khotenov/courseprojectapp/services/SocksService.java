package me.vasiliy.khotenov.courseprojectapp.services;

import org.springframework.stereotype.Service;
import me.vasiliy.khotenov.courseprojectapp.models.*;
import me.vasiliy.khotenov.courseprojectapp.repository.StorageUnitRepository;
import me.vasiliy.khotenov.courseprojectapp.repository.TransactionRepository;


@Service
public class SocksService {

    private final TransactionRepository transactionRepository;

    private final StorageUnitRepository storageUnitRepository;

    public SocksService(TransactionRepository transactionRepository, StorageUnitRepository storageUnitRepository) {
        this.transactionRepository = transactionRepository;
        this.storageUnitRepository = storageUnitRepository;
    }

    public int findByCottonPartLessThan(SocksColor socksColor,
                                        SocksSize socksSize,
                                        int cottonMin) {
        return storageUnitRepository.findByCottonPartLessThan(socksColor, socksSize, cottonMin);
    }

    public int findByCottonPartMoreThan(SocksColor socksColor,
                                        SocksSize socksSize,
                                        int cottonMax) {
        return storageUnitRepository.findByCottonPartMoreThan(socksColor, socksSize, cottonMax);
    }

    public boolean addToStorage(SocksColor socksColor,
                                SocksSize socksSize,
                                int cottonPart,
                                int quantity) {
        transactionRepository.addSTransaction(cottonPart,
                socksColor,
                socksSize,
                quantity,
                TransactionsType.INCOMING);
        return storageUnitRepository.addInStorageUnitRepository(cottonPart,
                socksColor,
                socksSize,
                quantity);
    }

    public boolean delete(SocksColor socksColor,
                          SocksSize socksSize,
                          int cottonPart,
                          int quantity) {
        transactionRepository.addSTransaction(cottonPart, socksColor, socksSize, quantity, TransactionsType.CANCELLATION);
        return storageUnitRepository.delete(cottonPart,
                socksColor,
                socksSize,
                quantity);
    }

    public boolean releaseFromStorage(SocksColor socksColor,
                                      SocksSize socksSize,
                                      int cottonPart,
                                      int quantity) {
        transactionRepository.addSTransaction(cottonPart, socksColor, socksSize, quantity, TransactionsType.OUTGOING);
        return storageUnitRepository.outFromStorage(cottonPart,
                socksColor,
                socksSize,
                quantity);
    }


}
