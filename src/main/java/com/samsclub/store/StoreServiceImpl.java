package com.samsclub.store;

import com.samsclub.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class StoreServiceImpl implements StoreService {


    @Autowired
    private StoreRepo storeRepo;


    @Override
    public Store saveStore(Store store) {
        return storeRepo.save(store);
    }

    @Override
    public List<Store> getAllStore() {
        return storeRepo.findAll();
    }

    @Override
    public Store getStoreById(long storeId) {
        return storeRepo.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store does not exist with the ID: " + storeId));

    }


    @Override
    public Store updateStore(long storeId, Store store) {

        Store existingStore = storeRepo.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store does not exist with the ID: " + storeId));

        existingStore.setStoreName(store.getStoreName());
        existingStore.setStoreType(store.getStoreType());
        existingStore.setStoreLocation(store.getStoreLocation());
        existingStore.setStoreContact(store.getStoreContact());
        existingStore.setOpeningDate(store.getOpeningDate());


        return storeRepo.save(existingStore);
    }


    @Override
    public void deleteStore(long storeId) {


        Store existingStore = storeRepo.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store does not exist with the ID: " + storeId));

        storeRepo.delete(existingStore);
    }


    // search methods
    @Override
    public List<Store> findByStoreNameContaining(String storeNameInfix) {
        return storeRepo.findByStoreNameContaining(storeNameInfix);
    }

    @Override
    public List<Store> findByOpeningDateEquals(Date openingDate) {
        return  storeRepo.findByOpeningDateEquals(openingDate);
    }

    @Override
    public List<Store> findByStoreTypeContaining(String storeTypeInfix) {
        return storeRepo.findByStoreTypeContaining(storeTypeInfix);
    }

    @Override
    public List<Store> findByStoreLocationContaining(String storeLocationInfix) {
        return storeRepo.findByStoreLocationContaining(storeLocationInfix);
    }


}
