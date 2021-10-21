package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.entities.concretes.AmountTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmountTypeServiceImpl implements AmountTypeService {
    private final AmountTypeDao amountTypeDao;
    private final ModelMapper mapper;

    @Autowired
    public AmountTypeServiceImpl(AmountTypeDao amountTypeDao, ModelMapper mapper) {
        super();
        this.amountTypeDao = amountTypeDao;
        this.mapper = mapper;
    }

    public DataResult<List<AmountTypeDto>> getAll() {
        return new SuccessDataResult<List<AmountTypeDto>>(
            this.amountTypeDao.findAll()
            .stream()
            .map(amountTypeEntity -> mapper.map(amountTypeEntity, AmountTypeDto.class))
            .collect(Collectors.toList()),
            "Veriler Listelendi.");
    }

    public Result add(AmountTypeDto amountTypeDto) {
        AmountTypeEntity amountTypeEntity = mapper.map(amountTypeDto, AmountTypeEntity.class);
        try{
            this.amountTypeDao.save(amountTypeEntity);
            return new SuccessResult("Ürün eklendi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    public Result drop(Integer id) {
        try{
            this.amountTypeDao.deleteById(id);
            return new SuccessResult("Ürün silindi.");
        } catch (Exception e) {
            return new ErrorResult(e.toString());
        }
    }

    public DataResult<AmountTypeDto> getByType(String type) {
        return new SuccessDataResult<AmountTypeDto>(
            mapper.map(this.amountTypeDao.getByType(type), AmountTypeDto.class),
            "Veriler Listelendi.");
    }
    public DataResult<AmountTypeDto> getById(Integer id) {
        return new SuccessDataResult<AmountTypeDto>(
                mapper.map(this.amountTypeDao.getById(id), AmountTypeDto.class),
                "Veriler Listelendi.");
    }
}
