package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.AmountTypeService;
import net.productsellint.core.utilities.results.*;
import net.productsellint.dataAccess.abstracts.AmountTypeDao;
import net.productsellint.dataTransferObjects.concretes.AmountTypeDto;
import net.productsellint.entities.concretes.AmountTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<DataResult<List<AmountTypeDto>>> getAll() {
        return ResponseEntity.status(200).body(new SuccessDataResult<>(
            this.amountTypeDao.findAll()
            .stream()
            .map(amountTypeEntity -> mapper.map(amountTypeEntity, AmountTypeDto.class))
            .collect(Collectors.toList()),
            "Veriler Listelendi."));
    }

    public ResponseEntity<Result> add(AmountTypeDto amountTypeDto) {
        AmountTypeEntity amountTypeEntity = mapper.map(amountTypeDto, AmountTypeEntity.class);
        this.amountTypeDao.save(amountTypeEntity);
        return ResponseEntity.status(200).body(new SuccessResult("Type eklendi."));
    }

    public ResponseEntity<Result> deleteAmountType(Integer id) {
        this.amountTypeDao.deleteAmountType(id);
        return ResponseEntity.status(200).body(new SuccessResult("Type silindi."));
    }

    public ResponseEntity<Result> disableAmountType(Integer id) {
        this.amountTypeDao.disableAmountType(id);
        return ResponseEntity.status(200).body(new SuccessResult("Type devre dışı."));
    }

    public ResponseEntity<Result> activateAmountType(Integer id) {
        this.amountTypeDao.activateAmountType(id);
        return ResponseEntity.status(200).body(new SuccessResult("Type aktif."));
    }

    public ResponseEntity<DataResult<AmountTypeDto>> getByType(String type) {
        return ResponseEntity.status(200).body(new SuccessDataResult<>(
            mapper.map(this.amountTypeDao.getByType(type), AmountTypeDto.class),
            "Veriler Listelendi."));
    }
    public ResponseEntity<DataResult<AmountTypeDto>> getById(Integer id) {
        return ResponseEntity.status(200).body(new SuccessDataResult<>(
                mapper.map(this.amountTypeDao.getById(id), AmountTypeDto.class),
                "Veriler Listelendi."));
    }
}
