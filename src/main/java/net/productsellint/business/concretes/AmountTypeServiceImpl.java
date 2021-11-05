package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.dataTransferObjects.concretes.AmountTypeRequest;
import net.productsellint.entities.concretes.AmountTypeEntity;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.exception.custom.CategoryNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmountTypeServiceImpl implements AmountTypeService {
    private final AmountTypeDao amountTypeDao;
    private final ModelMapper mapper;

    public AmountTypeServiceImpl(AmountTypeDao amountTypeDao, ModelMapper mapper) {
        this.amountTypeDao = amountTypeDao;
        this.mapper = mapper;
    }

    @Override
    public List<AmountTypeDto> getAll() {
        return this.amountTypeDao.findByEntityStatus(EntityStatus.ACTIVE)
            .stream()
            .map(amountTypeEntity -> mapper.map(amountTypeEntity, AmountTypeDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void add(AmountTypeRequest amountTypeRequest) {
        AmountTypeEntity amountTypeEntity = mapper.map(amountTypeRequest, AmountTypeEntity.class);
        this.amountTypeDao.save(amountTypeEntity);
    }

    @Override
    public void deleteAmountType(Integer id) {
        this.amountTypeDao.deleteAmountType(id);
    }

    @Override
    public void disableAmountType(Integer id) {
        this.amountTypeDao.disableAmountType(id);
    }

    @Override
    public void activateAmountType(Integer id) {
        this.amountTypeDao.activateAmountType(id);
    }

    @Override
    public AmountTypeDto getByType(String type) {
        return mapper.map(this.amountTypeDao.getByType(type), AmountTypeDto.class);
    }

    @Override
    public AmountTypeDto getById(Integer id) {
        return mapper.map(this.amountTypeDao.getById(id), AmountTypeDto.class);
    }

    @Override
    public AmountTypeEntity findById(Integer id) {
        AmountTypeEntity amountTypeEntity = this.amountTypeDao.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return amountTypeEntity;
    }
}
