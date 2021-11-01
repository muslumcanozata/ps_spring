package net.productsellint.business.concretes; // java web uygulamaları com. ile başlar

import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.EntityStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmountTypeServiceImpl { // interfaceden implemente edilmeli
    private final AmountTypeDao amountTypeDao;
    private final ModelMapper mapper;

    @Autowired// buna gerek yok
    public AmountTypeServiceImpl(AmountTypeDao amountTypeDao, ModelMapper mapper) {
        super();//buna gerek yok
        this.amountTypeDao = amountTypeDao;
        this.mapper = mapper;
    }

    public List<AmountTypeDto> getAll() {
        return this.amountTypeDao.findByEntityStatus(EntityStatus.ACTIVE)
            .stream()
            .map(amountTypeEntity -> mapper.map(amountTypeEntity, AmountTypeDto.class))
            .collect(Collectors.toList());
    }

    public void add(AmountTypeDto amountTypeDto) {
        AmountTypeEntity amountTypeEntity = mapper.map(amountTypeDto, AmountTypeEntity.class);
        this.amountTypeDao.save(amountTypeEntity);
    }

    public void deleteAmountType(Integer id) {
        this.amountTypeDao.deleteAmountType(id);
    }

    public void disableAmountType(Integer id) {
        this.amountTypeDao.disableAmountType(id);
    }

    public void activateAmountType(Integer id) {
        this.amountTypeDao.activateAmountType(id);
    }

    public AmountTypeDto getByType(String type) {
        return mapper.map(this.amountTypeDao.getByType(type), AmountTypeDto.class);
    }
    public AmountTypeDto getById(Integer id) {
        return mapper.map(this.amountTypeDao.getById(id), AmountTypeDto.class);
    }
}
