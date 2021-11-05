package net.productsellint.business.concretes;

import net.productsellint.business.abstracts.StockService;
import net.productsellint.dataAccess.abstracts.StockDao;
import net.productsellint.dataTransferObjects.concretes.StockDto;
import net.productsellint.dataTransferObjects.concretes.StockRequest;
import net.productsellint.entities.concretes.EntityStatus;
import net.productsellint.entities.concretes.StockEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    private final StockDao stockDao;
    private final ModelMapper mapper;

    public StockServiceImpl(StockDao stockDao, ModelMapper mapper) {
        this.stockDao = stockDao;
        this.mapper = mapper;
    }

    @Override
    public List<StockDto> getAll() {
        EntityStatus entityStatus = EntityStatus.ACTIVE;
        return this.stockDao.findByEntityStatus(entityStatus).stream().map(stockEntity -> mapper.map(stockEntity, StockDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getAll(int pageNo, int pageSize) {
        EntityStatus entityStatus = EntityStatus.ACTIVE;
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.stockDao.findByEntityStatus(entityStatus).stream().map(stockEntity -> mapper.map(stockEntity, StockDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        EntityStatus entityStatus = EntityStatus.ACTIVE;
        return this.stockDao.findByEntityStatus(entityStatus, sort).stream().map(stockEntity -> mapper.map(stockEntity, StockDto.class)).collect(Collectors.toList());
    }

    @Override
    public void add(StockRequest stockRequest) {
        EntityStatus entityStatus = EntityStatus.getStatus(stockRequest.getStatus());
        StockEntity stockEntity = new StockEntity(null, stockRequest.getStock(), entityStatus);
        this.stockDao.save(stockEntity);
    }

    @Override
    public void decreaseStock(Integer id, Float amount) {
        this.stockDao.decreaseStock(id, amount);
    }

    @Override
    public void increaseStock(Integer id, Float amount) {
        this.stockDao.increaseStock(id, amount);
    }

    @Override
    public void deleteStock(Integer id) {
        this.stockDao.deleteStock(id);
    }

    @Override
    public void activateStock(Integer id) {
        this.stockDao.activateStock(id);

    }

    @Override
    public void disableStock(Integer id) {
        this.stockDao.disableStock(id);
    }
}
